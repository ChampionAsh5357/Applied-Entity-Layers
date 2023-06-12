/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.extension;

import net.minecraft.client.model.geom.ModelPart;

import java.util.Set;

/**
 * A definition of a model which has parts that represent the body.
 */
public interface BodyModelParts {

    /**
     * {@return a set of model parts that represent the body}
     */
    Set<ModelPart> bodySet();
}
