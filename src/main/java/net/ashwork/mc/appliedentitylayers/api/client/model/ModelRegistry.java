/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

/**
 * A registry for models which would like to use the entity layers provided by
 * this mod.
 */
public interface ModelRegistry {

    /**
     * Registers a {@link ModelTransformations.ModelTransformationsGetter} for an
     * entity to apply the necessary transformations for the given model. This
     * assumes that {@link ModelTransformations} is implemented on the model itself.
     *
     * @param type the entity type to register the transformation getter for
     * @param <T> the type of the living entity
     */
    default <T extends LivingEntity> void registerTransformations(EntityType<T> type) {
        this.registerTransformations(type, model -> (ModelTransformations) model);
    }

    /**
     * Registers a {@link ModelTransformations.ModelTransformationsGetter} for an
     * entity to apply the necessary transformations for the given model.
     *
     * @param type the entity type to register the transformation getter for
     * @param getter the getter which obtains the transformations from the model
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    <T extends LivingEntity, M extends EntityModel<T>> void registerTransformations(EntityType<T> type, ModelTransformations.ModelTransformationsGetter<T, M> getter);
}
