/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A registry for models which would like to use the entity layers provided by
 * this mod.
 */
public interface ModelRegistry {

    /**
     * Registers a {@link ModelTransform.Factory} for a model to apply the
     * required transforms.
     *
     * @param model the class of the model to create the transforms for
     * @param factory the factory used to create the transforms
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    <T extends LivingEntity, M extends EntityModel<T>> void registerTransformFactory(Class<M> model, ModelTransform.Factory<T, M> factory);

    // TODO: Document
    default <T extends LivingEntity> void enableLayers(Supplier<? extends EntityType<T>> type, Consumer<ModelLayerSettings> settings) {
        this.enableLayers(type.get(), settings);
    }

    // TODO: Document
    <T extends LivingEntity> void enableLayers(EntityType<T> type, Consumer<ModelLayerSettings> settings);
}
