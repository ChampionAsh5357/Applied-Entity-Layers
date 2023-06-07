/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.google.common.collect.Sets;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.model.BasicModelTransformations;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin applied to {@link AgeableListModel}.
 */
@Mixin(AgeableListModel.class)
public abstract class AgeableListModelMixin extends EntityModel<Entity> implements BasicModelTransformations {

    @Final @Shadow private boolean scaleHead;
    @Final @Shadow private float babyYHeadOffset;
    @Final @Shadow private float babyZHeadOffset;
    @Final @Shadow private float babyHeadScale;
    @Final @Shadow private float babyBodyScale;
    @Final @Shadow private float bodyYOffset;
    @Shadow protected abstract Iterable<ModelPart> headParts();

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            // If head
            if (BasicModelTransformations.isIn(part, Sets.newHashSet(this.headParts()))) {
                if (this.scaleHead) {
                    var invHeadScale = 1.5f / this.babyHeadScale;
                    pose.scale(invHeadScale, invHeadScale, invHeadScale);
                }
                pose.translate(0f, this.babyYHeadOffset / 16f, this.babyZHeadOffset / 16f);
            } else {
                // Otherwise for body
                var invBodyScale = 1f / this.babyBodyScale;
                pose.scale(invBodyScale, invBodyScale, invBodyScale);
                pose.translate(0f, this.bodyYOffset / 16f, 0f);
            }
        }

        // Perform transformation
        BasicModelTransformations.super.transformTo(pose, part);
    }
}