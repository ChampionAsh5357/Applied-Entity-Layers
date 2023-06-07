/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelTransformations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * An implementation of {@link ModelTransformations} that provides the common
 * logic associated with most models.
 */
public interface BasicModelTransformations extends ModelTransformations {

    /**
     * {@return a list of non-empty model parts}
     */
    default List<ModelPart> nonEmptyParts() {
        return Collections.emptyList();
    }

    @Override
    default ModelPart getRandomModelPart(RandomSource random) {
        // If the list is empty, return null to cancel rendering
        if (this.nonEmptyParts().isEmpty()) return null;

        // Otherwise, get only visible parts and select one
        var visibleParts = this.nonEmptyParts().stream().filter(part -> ((ModelPartExtension) (Object) part).isVisible()).toList();
        return visibleParts.get(random.nextInt(visibleParts.size()));
    }

    @Override
    default void transformTo(PoseStack pose, ModelPart part) {
        // Get all parts to apply transformations for
        List<ModelPart> transformations = new ArrayList<>();
        while (part != null) {
            // Transformations are applied from parent -> child
            transformations.add(0, part);
            part = ((ModelPartExtension) (Object) part).getParent();
        }

        // Apply transformations
        transformations.forEach(transform -> transform.translateAndRotate(pose));
    }

    /**
     * Gets all non-empty parts and returns them in a list.
     *
     * @param root the root part
     * @return a list of all non-empty parts
     */
    static List<ModelPart> parts(ModelPart root) {
        return root.getAllParts().filter(part -> !part.isEmpty()).toList();
    }

    /**
     * {@return whether the part or its parents are in the provided set}
     *
     * @param part the part to check
     * @param partSet the part set to see whether the part or its parents are in
     */
    static boolean isIn(ModelPart part, Set<ModelPart> partSet) {
        while (part != null) {
            // If a part or its parent is in the list, return true
            if (partSet.contains(part)) return true;
            part = ((ModelPartExtension) (Object) part).getParent();
        }
        return false;
    }
}
