/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.extension;

import net.minecraft.client.model.geom.ModelPart;

import java.util.List;

/**
 * An extension interface applied to an {@link net.minecraft.client.model.EntityModel}.
 */
public interface EntityModelExtension {

    /**
     * {@return a list of non-empty model parts}
     */
    List<ModelPart> nonEmptyParts();
}
