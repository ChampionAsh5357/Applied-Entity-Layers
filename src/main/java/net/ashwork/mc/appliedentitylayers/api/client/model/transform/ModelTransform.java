/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.ModelPartExtension;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * A helper for applying transformations based on data associated with the current
 * model.
 */
public interface ModelTransform {

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
     * A factory to get the {@link ModelTransform} associated with a specified
     * model.
     *
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    interface Factory<T extends LivingEntity, M extends EntityModel<T>> extends Function<M, ModelTransform> {}

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
