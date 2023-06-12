/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
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

    /**
     * Enables the layers to display on the associated entities.
     *
     * @param settings the settings to apply to the entity
     * @param types the entities to apply the settings to
     */
    @SuppressWarnings("unchecked")
    default void enableLayers(Consumer<ModelLayerSettings> settings, Supplier<? extends EntityType<? extends LivingEntity>>... types) {
        this.enableLayers(settings, Arrays.stream(types).map(Supplier::get).toArray(EntityType[]::new));
    }

    /**
     * Enables the layers to display on the associated entities.
     *
     * @param settings the settings to apply to the entity
     * @param types the entities to apply the settings to
     */
    @SuppressWarnings("unchecked")
    void enableLayers(Consumer<ModelLayerSettings> settings, EntityType<? extends LivingEntity>... types);
}
