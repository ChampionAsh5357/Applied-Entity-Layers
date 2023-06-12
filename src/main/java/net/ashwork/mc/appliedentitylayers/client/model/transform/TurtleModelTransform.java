/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.AgeableListModelTransform;
import net.ashwork.mc.appliedentitylayers.client.extension.TurtleModelExtension;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Turtle;

import java.util.List;
import java.util.function.Function;

/**
 * A transform implementation for a simple {@link TurtleModel}.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public class TurtleModelTransform<T extends Turtle, M extends TurtleModel<T>> extends AgeableListModelTransform<T, M> {

    /**
     * Constructs a transform for a model.
     *
     * @param model the model the transform is applied to
     * @param nonEmptyParts a function which gets the non-empty parts of the model
     */
    public TurtleModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (TurtleModelExtension) this.model;

        if (ext.eggBellyVisible())
            pose.translate(0f, -0.08f, 0f);

        // Perform transformation
        super.transformTo(pose, part);
    }
}
