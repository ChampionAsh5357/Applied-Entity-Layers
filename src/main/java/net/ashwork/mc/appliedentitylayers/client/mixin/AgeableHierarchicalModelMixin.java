/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.model.BasicModelTransformations;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin applied to {@link AgeableHierarchicalModel}.
 */
@Mixin(AgeableHierarchicalModel.class)
public abstract class AgeableHierarchicalModelMixin extends HierarchicalModel<Entity> implements BasicModelTransformations {


    @Shadow @Final private float youngScaleFactor;
    @Shadow @Final private float bodyYOffset;

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            // If head
            pose.scale(this.youngScaleFactor, this.youngScaleFactor, this.youngScaleFactor);
            pose.translate(0f, this.bodyYOffset / 16f, 0f);
        }

        // Perform transformation
        BasicModelTransformations.super.transformTo(pose, part);
    }
}
