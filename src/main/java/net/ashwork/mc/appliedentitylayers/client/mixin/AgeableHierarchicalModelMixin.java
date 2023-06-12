/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import net.ashwork.mc.appliedentitylayers.api.client.model.extension.AgeableHierarchicalModelExtension;
import net.minecraft.client.model.AgeableHierarchicalModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin applied to {@link AgeableHierarchicalModel}.
 */
@Mixin(AgeableHierarchicalModel.class)
public abstract class AgeableHierarchicalModelMixin implements AgeableHierarchicalModelExtension {

    @Shadow @Final private float youngScaleFactor;
    @Shadow @Final private float bodyYOffset;

    @Override
    public float youngScaleFactor() {
        return this.youngScaleFactor;
    }

    @Override
    public float babyYOffset() {
        return this.bodyYOffset;
    }
}
