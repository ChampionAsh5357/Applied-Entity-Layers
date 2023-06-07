/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin applied to {@link TurtleModel}.
 */
@Mixin(TurtleModel.class)
public abstract class TurtleModelMixin extends AgeableListModelMixin {

    @Shadow @Final private ModelPart eggBelly;

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        if (this.eggBelly.visible)
            pose.translate(0f, -0.08f, 0f);

        super.transformTo(pose, part);
    }
}
