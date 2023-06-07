/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A helper for applying transformations based on data associated with the current
 * model.
 */
public interface ModelTransformations {

    /**
     * Gets a random {@link ModelPart} from the current model. When {@code null},
     * any render calls will be hidden.
     *
     * <p>It is recommended that only visible parts are selected. Otherwise,
     * something that renders may appear to be floating.
     *
     * @param random a random instance
     * @return the chosen {@link ModelPart}, or {@code null} if nothing should
     * be rendered
     */
    @Nullable
    ModelPart getRandomModelPart(RandomSource random);

    /**
     * Applies additional transformations from the {@link ModelPart} to the
     * {@link PoseStack}.
     *
     * <p>Poses should not be pushed or popped inside this method as that is
     * handled by the associated consumer.
     *
     * @param pose the pose to apply the transformations to
     * @param part the part providing the transformations
     */
    default void transformTo(PoseStack pose, ModelPart part) {}

    /**
     * A helper to get the {@link ModelTransformations} associated with a specified
     * model.
     *
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    interface ModelTransformationsGetter<T extends LivingEntity, M extends EntityModel<T>> extends Function<M, ModelTransformations> {}
}
