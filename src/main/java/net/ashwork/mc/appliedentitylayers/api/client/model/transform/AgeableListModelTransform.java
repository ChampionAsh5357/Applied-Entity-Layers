/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.AgeableListModelExtension;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.Function;

/**
 * A transform implementation for a simple {@link AgeableListModel}.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public class AgeableListModelTransform<T extends LivingEntity, M extends AgeableListModel<T>> extends EntityModelTransform<T, M> {

    /**
     * Constructs a transform for a model.
     *
     * @param model the model the transform is applied to
     * @param nonEmptyParts a function which gets the non-empty parts of the model
     */
    public AgeableListModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (AgeableListModelExtension) this.model;

        // Check if young
        if (this.model.young) {
            // If head
            if (ModelTransform.isIn(part, ext.headSet())) {
                if (ext.scaleHead()) {
                    var invHeadScale = 1.5f / ext.babyHeadScale();
                    pose.scale(invHeadScale, invHeadScale, invHeadScale);
                }
                pose.translate(0f, ext.babyYHeadOffset() / 16f, ext.babyZHeadOffset() / 16f);
            } else {
                // Otherwise for body
                var invBodyScale = 1f / ext.babyBodyScale();
                pose.scale(invBodyScale, invBodyScale, invBodyScale);
                pose.translate(0f, ext.babyBodyYOffset() / 16f, 0f);
            }
        }

        // Perform transformation
        super.transformTo(pose, part);
    }
}
