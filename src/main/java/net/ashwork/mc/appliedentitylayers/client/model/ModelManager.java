/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model;

import net.ashwork.mc.appliedentitylayers.api.client.model.ModelLayerSettings;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.ashwork.mc.appliedentitylayers.client.layers.ArrowRenderLayer;
import net.ashwork.mc.appliedentitylayers.client.layers.BeeStingerRenderLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A manager for registering and handling the layers for entities whenever the
 * renderers are constructed.
 */
public class ModelManager implements ModelRegistry {

    private final Map<Class<? extends EntityModel<?>>, ModelTransform.Factory<?, ?>> transformGetters;
    private Map<EntityType<? extends LivingEntity>, ModelDataBuilder> modelDataBuilder;
    private final Map<EntityType<? extends LivingEntity>, ModelData<?, ?>> modelData;


    /**
     * Constructs the manager for the models.
     */
    public ModelManager() {
        this.transformGetters = new HashMap<>();
        this.modelDataBuilder = new HashMap<>();
        this.modelData = new HashMap<>();
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void registerTransformFactory(Class<M> model, ModelTransform.Factory<T, M> factory) {
        this.transformGetters.put(model, factory);
    }

    @SafeVarargs
    @Override
    public final void enableLayers(Consumer<ModelLayerSettings> settings, EntityType<? extends LivingEntity>... types) {
        for (var type : types) settings.accept(this.modelDataBuilder.computeIfAbsent(type, t -> new ModelDataBuilder()));
    }

    /**
     * Builds the model data and clears the existing builders.
     */
    public void build() {
        // Build model data
        this.modelDataBuilder.forEach((type, builder) -> this.modelData.put(type, builder.build()));

        // Invalidate builders
        this.modelDataBuilder.clear();
        this.modelDataBuilder = null;
    }

    /**
     * Creates and applies the layers for all model data associated with the
     * entities.
     *
     * @param rendererGetter a getter which can obtain a renderer from the entity type
     */
    public void applyLayers(Function<EntityType<? extends LivingEntity>, LivingEntityRenderer<?, ?>> rendererGetter) {
        this.modelData.forEach((type, data) -> this.apply(data, rendererGetter.apply(type)));
    }

    /**
     * Applies the layers for a given model data to the renderer. This properly
     * casts all types.
     *
     * @param data the model data associated with the entity type
     * @param renderer the renderer associated with the entity type
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    @SuppressWarnings("unchecked")
    private <T extends LivingEntity, M extends EntityModel<T>> void apply(ModelData<?, ?> data, LivingEntityRenderer<?, ?> renderer) {
        ModelData<T, M> castData = (ModelData<T, M>) data;
        LivingEntityRenderer<T, M> castRenderer = (LivingEntityRenderer<T, M>) renderer;
        castData.apply(castRenderer);
    }

    /**
     * A builder for the {@link ModelData}.
     */
    private class ModelDataBuilder implements ModelLayerSettings {

        private boolean arrow = false;
        private boolean beeStinger = false;

        /**
         * Constructs the model data builder.
         */
        private ModelDataBuilder() {}

        @Override
        public ModelLayerSettings arrow() {
            this.arrow = true;
            return this;
        }

        @Override
        public ModelLayerSettings beeStinger() {
            this.beeStinger = true;
            return this;
        }

        /**
         * Builds the model data.
         *
         * @return the built model data
         */
        public <T extends LivingEntity, M extends EntityModel<T>> ModelData<T, M> build() {
            return new ModelData<>(this.arrow, this.beeStinger);
        }
    }

    /**
     * A class containing the model data associated with a given entity type.
     *
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private class ModelData<T extends LivingEntity, M extends EntityModel<T>> {

        private final boolean arrow, beeStinger;
        private final Map<M, ModelTransform> transforms;

        /**
         * Constructs a new model data.
         *
         * @param arrow whether the arrow layer should be enabled
         * @param beeStinger whether the bee stinger layer should be enabled
         */
        public ModelData(boolean arrow, boolean beeStinger) {
            this.arrow = arrow;
            this.beeStinger = beeStinger;
            this.transforms = new HashMap<>();
        }

        /**
         * Applies the layers to the renderer.
         *
         * @param renderer the renderer to apply the layers to.
         * @param <R> the type of the entity renderer
         */
        @SuppressWarnings("unchecked")
        public <R extends LivingEntityRenderer<T, M>> void apply(R renderer) {
            // Clear transforms as layers are being reapplied
            this.transforms.clear();

            // Setup basic parameters
            var order = (FirstOrLastLayerOrder<T, M>) renderer;
            Function<RenderLayerParent<T, M>, ModelTransform> getter = parent -> this.transforms.computeIfAbsent(parent.getModel(), model -> {
                ModelTransform.Factory<T, M> transformGetter = (ModelTransform.Factory<T, M>) ModelManager.this.transformGetters.get(model.getClass());
                return transformGetter == null ? null : transformGetter.apply(parent.getModel());
            });

            if (this.beeStinger) order.addLayerFirst(new BeeStingerRenderLayer<>(renderer, getter));
            if (this.arrow) order.addLayerFirst(new ArrowRenderLayer<>(renderer, getter, Minecraft.getInstance().getEntityRenderDispatcher()));
        }
    }
}
