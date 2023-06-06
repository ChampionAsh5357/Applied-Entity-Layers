/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.impl.model;

import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelPartGetter;
import net.ashwork.mc.appliedentitylayers.client.renderer.entity.layers.ExpandedArrowLayer;
import net.ashwork.mc.appliedentitylayers.client.renderer.entity.layers.ExpandedBeeStingerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//TODO: Document
public class ModelManager implements ModelRegistry {

    private Map<EntityType<? extends LivingEntity>, ModelDataBuilder<?, ?>> modelDataBuilder;
    private final Map<EntityType<? extends LivingEntity>, ModelData<?, ?>> modelData;

    public ModelManager() {
        this.modelDataBuilder = new HashMap<>();
        this.modelData = new HashMap<>();
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void registerPartGetter(EntityType<T> type, Function<M, ModelPartGetter> getter) {
        ModelDataBuilder<T, M> builder = this.getOrCreateBuilder(type);
        builder.partGetter(getter);
    }

    @SuppressWarnings("unchecked")
    private <T extends LivingEntity, M extends EntityModel<T>> ModelDataBuilder<T, M> getOrCreateBuilder(EntityType<T> type) {
        return (ModelDataBuilder<T, M>) this.modelDataBuilder.computeIfAbsent(type, t -> new ModelDataBuilder<>());
    }

    public void build() {
        this.modelDataBuilder.forEach((type, builder) -> this.modelData.put(type, builder.build()));

        // Invalidate builders
        this.modelDataBuilder.clear();
        this.modelDataBuilder = null;
    }

    public void applyLayers(Function<EntityType<? extends LivingEntity>, LivingEntityRenderer<?, ?>> rendererGetter) {
        this.modelData.forEach((type, data) -> this.apply(data, rendererGetter.apply(type)));
    }

    @SuppressWarnings("unchecked")
    private <T extends LivingEntity, M extends EntityModel<T>> void apply(ModelData<?, ?> data, LivingEntityRenderer<?, ?> renderer) {
        ModelData<T, M> castData = (ModelData<T, M>) data;
        LivingEntityRenderer<T, M> castRenderer = (LivingEntityRenderer<T, M>) renderer;
        castData.apply(castRenderer);
    }

    private static class ModelDataBuilder<T extends LivingEntity, M extends EntityModel<T>> {

        private Function<M, ModelPartGetter> partGetter;

        private ModelDataBuilder() {}

        public void partGetter(Function<M, ModelPartGetter> partGetter) {
            this.partGetter = partGetter;
        }

        public ModelData<T, M> build() {
            return new ModelData<>(partGetter);
        }
    }

    private record ModelData<T extends LivingEntity, M extends EntityModel<T>>(Function<M, ModelPartGetter> partGetter) {

        public <R extends LivingEntityRenderer<T, M>> void apply(R renderer) {
            if (this.partGetter != null) {
                renderer.addLayer(new ExpandedArrowLayer<>(renderer, this.partGetter, Minecraft.getInstance().getEntityRenderDispatcher()));
                renderer.addLayer(new ExpandedBeeStingerLayer<>(renderer, this.partGetter));
            }
        }
    }
}
