/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.impl.model.InternalModelPartGetter;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CamelModel.class)
public abstract class CamelModelMixin extends HierarchicalModel<Entity> implements InternalModelPartGetter {

    @Override
    public void translateAndRotate(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            pose.scale(0.45f, 0.45f, 0.45f);
            pose.translate(0f, 1.834375f, 0f);
        }

        // Perform transformation
        InternalModelPartGetter.super.translateAndRotate(pose, part);
    }
}
