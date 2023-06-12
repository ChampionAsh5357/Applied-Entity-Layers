/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;

import java.util.function.Function;

/**
 * An extension on {@link net.minecraft.client.renderer.entity.layers.ArrowLayer}
 * that can be applied to any entity.
 *
 * @param <T> the type of the living entity
 * @param <M> the type of the entity model
 */
public class ExpandedArrowLayer<T extends LivingEntity, M extends EntityModel<T>> extends ExpandedStuckInBodyLayer<T, M> {

    private final EntityRenderDispatcher dispatcher;

    /**
     * Constructs the arrow layer.
     *
     * @param parent the parent holding the layer
     * @param getter the getter which obtains the transforms from the model
     * @param dispatcher the dispatcher used to render an entity
     */
    public ExpandedArrowLayer(RenderLayerParent<T, M> parent, Function<RenderLayerParent<T, M>, ModelTransform> getter, EntityRenderDispatcher dispatcher) {
        super(parent, getter, 0);
        this.dispatcher = dispatcher;
    }

    @Override
    protected int numStuck(T entity) {
        return entity.getArrowCount();
    }

    @Override
    protected void renderStuckItem(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float xRand, float yRand, float zRand, float partialTick) {
        // Create arrow and set rotations
        var arrow = new Arrow(entity.level(), entity.getX(), entity.getY(), entity.getZ());
        arrow.setYRot((float) Math.toDegrees(Math.atan2(xRand, zRand)));
        arrow.setXRot((float) Math.toDegrees(Math.atan2(yRand, Mth.sqrt(xRand * xRand + zRand * zRand))));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();

        // Render arrow
        this.dispatcher.render(arrow, 0d, 0d, 0d, 0f, partialTick, pose, vao, packedLight);
    }
}
