/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import net.ashwork.mc.appliedentitylayers.client.extension.TurtleModelExtension;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin applied to {@link TurtleModel}.
 */
@Mixin(TurtleModel.class)
public abstract class TurtleModelMixin implements TurtleModelExtension {

    @Shadow @Final private ModelPart eggBelly;

    @Override
    public boolean eggBellyVisible() {
        return this.eggBelly.visible;
    }
}
