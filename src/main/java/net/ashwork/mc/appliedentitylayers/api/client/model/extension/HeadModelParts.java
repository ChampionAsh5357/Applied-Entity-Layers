/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.extension;

import net.minecraft.client.model.geom.ModelPart;

import java.util.Set;

/**
 * A definition of a model which has parts that represent the head.
 */
public interface HeadModelParts {

    /**
     * {@return a set of model parts that represent the head}
     */
    Set<ModelPart> headSet();
}
