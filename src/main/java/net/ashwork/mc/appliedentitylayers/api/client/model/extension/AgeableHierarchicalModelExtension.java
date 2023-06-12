/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.extension;

/**
 * An extension interface applied to an {@link net.minecraft.client.model.AgeableHierarchicalModel}.
 */
public interface AgeableHierarchicalModelExtension {

    /**
     * {@return a scalar which scales the size of the model when young}
     */
    float youngScaleFactor();

    /**
     * {@return the offset in voxels to translate the model on the y-axis when young}
     */
    float babyYOffset();
}
