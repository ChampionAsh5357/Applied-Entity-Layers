/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

// TODO:

/**
 * A definition which allows the renderer to add layers to the front or
 * end of the list.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public interface FirstOrLastLayerOrder<T extends LivingEntity, M extends EntityModel<T>> {

    /**
     * Adds the render layer that renders before all other layers.
     *
     * @param layer the layer to apply
     */
    void addLayerFirst(RenderLayer<T, M> layer);

    /**
     * Adds the render layer that renders after all other layers.
     *
     * @param layer the layer to apply
     */
    void addLayerLast(RenderLayer<T, M> layer);
}
