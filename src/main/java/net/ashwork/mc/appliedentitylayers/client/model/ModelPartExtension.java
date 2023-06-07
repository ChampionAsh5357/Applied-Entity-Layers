/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model;

import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;

/**
 * An extension interface applied to a {@link ModelPart}.
 */
public interface ModelPartExtension {

    /**
     * {@return the parent of this part}
     */
    @Nullable
    ModelPart getParent();

    /**
     * {@return whether the model is visible to render to the screen}
     */
    boolean isVisible();
}
