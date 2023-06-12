/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

/**
 * Defines the settings that apply specific layers on an entity.
 */
public interface ModelLayerSettings {

    /**
     * Enables the arrow layer for this entity type.
     *
     * @return the settings instance
     */
    ModelLayerSettings arrow();

    /**
     * Enables the bee stinger layer for this entity type.
     *
     * @return the settings instance
     */
    ModelLayerSettings beeStinger();
}
