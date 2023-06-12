/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.AgeableHierarchicalModelExtension;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.Function;

/**
 * A transform implementation for a simple {@link AgeableHierarchicalModel}.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public class AgeableHierarchicalModelTransform<T extends LivingEntity, M extends AgeableHierarchicalModel<T>> extends EntityModelTransform<T, M> {

    /**
     * Constructs a transform for a model.
     *
     * @param model the model the transform is applied to
     * @param nonEmptyParts a function which gets the non-empty parts of the model
     */
    public AgeableHierarchicalModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (AgeableHierarchicalModelExtension) this.model;

        // Check if young
        if (this.model.young) {
            // If head
            pose.scale(ext.youngScaleFactor(), ext.youngScaleFactor(), ext.youngScaleFactor());
            pose.translate(0f, ext.babyYOffset() / 16f, 0f);
        }

        // Perform transformation
        super.transformTo(pose, part);
    }
}
