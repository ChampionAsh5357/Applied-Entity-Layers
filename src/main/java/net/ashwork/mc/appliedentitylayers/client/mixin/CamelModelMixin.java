/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.model.BasicModelTransformations;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * A mixin applied to {@link CamelModel}.
 */
@Mixin(CamelModel.class)
public abstract class CamelModelMixin extends HierarchicalModel<Entity> implements BasicModelTransformations {

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            pose.scale(0.45f, 0.45f, 0.45f);
            pose.translate(0f, 1.834375f, 0f);
        }

        // Perform transformation
        BasicModelTransformations.super.transformTo(pose, part);
    }
}
