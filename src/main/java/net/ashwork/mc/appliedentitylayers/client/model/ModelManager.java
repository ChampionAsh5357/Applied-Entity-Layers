/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model;

import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelTransformations;
import net.ashwork.mc.appliedentitylayers.client.layers.ExpandedArrowLayer;
import net.ashwork.mc.appliedentitylayers.client.layers.ExpandedBeeStingerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A manager for registering and handling the layers for entities whenever the
 * renderers are constructed.
 */
public class ModelManager implements ModelRegistry {

    private Map<EntityType<? extends LivingEntity>, ModelDataBuilder<?, ?>> modelDataBuilder;
    private final Map<EntityType<? extends LivingEntity>, ModelData<?, ?>> modelData;

    /**
     * Constructs the manager for the models.
     */
    public ModelManager() {
        this.modelDataBuilder = new HashMap<>();
        this.modelData = new HashMap<>();
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void registerTransformations(EntityType<T> type, ModelTransformations.ModelTransformationsGetter<T, M> getter) {
        ModelDataBuilder<T, M> builder = this.getOrCreateBuilder(type);
        builder.partGetter(getter);
    }

    /**
     * A helper to get or create the current builder with the type parameters
     * properly cast.
     *
     * @param type the entity type the model data is for
     * @return a builder for the model data
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    @SuppressWarnings("unchecked")
    private <T extends LivingEntity, M extends EntityModel<T>> ModelDataBuilder<T, M> getOrCreateBuilder(EntityType<T> type) {
        return (ModelDataBuilder<T, M>) this.modelDataBuilder.computeIfAbsent(type, t -> new ModelDataBuilder<>());
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
     *
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private static class ModelDataBuilder<T extends LivingEntity, M extends EntityModel<T>> {

        private ModelTransformations.ModelTransformationsGetter<T, M> transformations;

        /**
         * Constructs the model data builder.
         */
        private ModelDataBuilder() {}

        /**
         * Sets the transformations getter for the model.
         *
         * @param transformations the getter which obtains the transformations from the model
         */
        public void partGetter(ModelTransformations.ModelTransformationsGetter<T, M> transformations) {
            this.transformations = transformations;
        }

        /**
         * Builds the model data.
         *
         * @return the built model data
         */
        public ModelData<T, M> build() {
            return new ModelData<>(this.transformations);
        }
    }

    /**
     * A record containing the model data associated with a given entity type.
     *
     * @param transformations the getter which obtains the transformations from the model
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private record ModelData<T extends LivingEntity, M extends EntityModel<T>>(ModelTransformations.ModelTransformationsGetter<T, M> transformations) {

        /**
         * Applies the layers to the renderer.
         *
         * @param renderer the renderer to apply the layers to.
         * @param <R> the type of the entity renderer
         */
        public <R extends LivingEntityRenderer<T, M>> void apply(R renderer) {
            if (this.transformations != null) {
                renderer.addLayer(new ExpandedArrowLayer<>(renderer, this.transformations, Minecraft.getInstance().getEntityRenderDispatcher()));
                renderer.addLayer(new ExpandedBeeStingerLayer<>(renderer, this.transformations));
            }
        }
    }
}
