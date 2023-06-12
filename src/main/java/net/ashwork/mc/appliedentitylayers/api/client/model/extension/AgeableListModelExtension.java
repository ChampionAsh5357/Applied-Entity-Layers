/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.extension;

/**
 * An extension interface applied to an {@link net.minecraft.client.model.AgeableListModel}.
 */
public interface AgeableListModelExtension extends HeadModelParts {

    /**
     * {@return whether the head part should be scaled an additional amount when young}
     */
    boolean scaleHead();

    /**
     * {@return the offset in voxels to translate the head parts on the y-axis when young}
     */
    float babyYHeadOffset();

    /**
     * {@return the offset in voxels to translate the head parts on the z-axis when young}
     */
    float babyZHeadOffset();

    /**
     * {@return a scalar which scales the size of the head parts when {@linkplain #scaleHead()} is {@code true}}
     */
    float babyHeadScale();

    /**
     * {@return a scalar which scales the size of the body parts when young}
     */
    float babyBodyScale();

    /**
     * {@return the offset in voxels to translate the body parts on the y-axis when young}
     */
    float babyBodyYOffset();
}
