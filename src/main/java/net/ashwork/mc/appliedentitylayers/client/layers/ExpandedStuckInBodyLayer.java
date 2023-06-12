/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An extension on {@link net.minecraft.client.renderer.entity.layers.StuckInBodyLayer}
 * that can be applied to any entity.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public abstract class ExpandedStuckInBodyLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private final Supplier<ModelTransform> getter;
    private final int offset;
    private M oParentModel;
    private ModelTransform oTransform;

    /**
     * Constructs the layer for rendering stuck objects.
     *
     * @param parent the parent holding the layer
     * @param getter the getter which obtains the transforms from the model
     * @param offset an offset to apply to the entity id when creating the random
     */
    public ExpandedStuckInBodyLayer(RenderLayerParent<T, M> parent, Function<RenderLayerParent<T, M>, ModelTransform> getter, int offset) {
        super(parent);
        this.getter = () -> getter.apply(parent);
        this.offset = offset;
        this.oParentModel = this.getParentModel();
        this.oTransform = this.getter.get();
    }

    /**
     * {@return the number of stuck item}
     *
     * @param entity the entity to check
     */
    protected abstract int numStuck(T entity);

    /**
     * Renders the item stuck in the entity. The pose is already translated to
     * the location the item is stuck.
     *
     * @param pose the pose containing the transforms of the stuck item
     * @param vao the access object to get the buffer
     * @param packedLight the light of the block and sky
     * @param entity the entity to render on
     * @param x a random x value between [-1,1] to determine the rotation
     * @param y a random y value between [-1,1] to determine the rotation
     * @param z a random z value between [-1,1] to determine the rotation
     * @param partialTick a value between [0,1] representing the in-between time of a tick on the current frame
     */
    protected abstract void renderStuckItem(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float x, float y, float z, float partialTick);

    @Override
    public void render(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float walkPosition, float walkSpeed, float partialTick, float bob, float yRot, float xRot) {
        // Check if parent model is different, and if so update
        if (this.oParentModel != this.getParentModel()) {
            this.oParentModel = this.getParentModel();
            this.oTransform = this.getter.get();
        }

        // Skip if no transform is available
        if (this.oTransform == null) return;

        // Check if there are any stuck
        var stuck = this.numStuck(entity);
        if (stuck > 0) {
            // Create random and getter
            var random = RandomSource.create(entity.getId() + this.offset);

            // For each stuck object
            for (int i = 0; i < stuck; ++i) {
                pose.pushPose();

                // Get random part
                var part = this.oTransform.getRandomModelPart(random);

                // Apply transform
                var cube = part.getRandomCube(random);
                this.oTransform.transformTo(pose, part);

                // Translate to stuck position
                var xRand = random.nextFloat();
                var yRand = random.nextFloat();
                var zRand = random.nextFloat();
                pose.translate(
                        Mth.lerp(xRand, cube.minX, cube.maxX) / 16f,
                        Mth.lerp(yRand, cube.minY, cube.maxY) / 16f,
                        Mth.lerp(zRand, cube.minZ, cube.maxZ) / 16f
                );

                // Render stuck position with scaled [-1, 1] values
                this.renderStuckItem(pose, vao, packedLight, entity, -(2f * xRand - 1f), -(2f * yRand - 1f), -(2f * zRand - 1f), partialTick);
                pose.popPose();
            }
        }
    }
}
