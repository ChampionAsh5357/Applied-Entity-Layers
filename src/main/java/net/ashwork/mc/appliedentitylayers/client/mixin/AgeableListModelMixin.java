/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.google.common.collect.Sets;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.AgeableListModelExtension;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

/**
 * A mixin applied to {@link AgeableListModel}.
 */
@Mixin(AgeableListModel.class)
public abstract class AgeableListModelMixin implements AgeableListModelExtension {

    @Final @Shadow private boolean scaleHead;
    @Final @Shadow private float babyYHeadOffset;
    @Final @Shadow private float babyZHeadOffset;
    @Final @Shadow private float babyHeadScale;
    @Final @Shadow private float babyBodyScale;
    @Final @Shadow private float bodyYOffset;
    @Shadow protected abstract Iterable<ModelPart> headParts();

    @Override
    public boolean scaleHead() {
        return this.scaleHead;
    }

    @Override
    public float babyYHeadOffset() {
        return this.babyYHeadOffset;
    }

    @Override
    public float babyZHeadOffset() {
        return this.babyZHeadOffset;
    }

    @Override
    public float babyHeadScale() {
        return this.babyHeadScale;
    }

    @Override
    public float babyBodyScale() {
        return this.babyBodyScale;
    }

    @Override
    public float babyBodyYOffset() {
        return this.bodyYOffset;
    }

    @Override
    public Set<ModelPart> headSet() {
        return Sets.newHashSet(this.headParts());
    }
}
