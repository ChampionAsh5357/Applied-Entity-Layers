/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;

// TODO: Document
public interface ModelPartGetter {

    ModelPart getRandomModelPart(RandomSource random);

    void translateAndRotate(PoseStack pose, ModelPart part);
}
