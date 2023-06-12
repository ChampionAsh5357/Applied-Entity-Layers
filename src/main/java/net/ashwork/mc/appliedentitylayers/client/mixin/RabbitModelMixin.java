/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import com.google.common.collect.ImmutableSet;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.HeadModelParts;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/**
 * A mixin applied to {@link RabbitModel}.
 */
@Mixin(RabbitModel.class)
public abstract class RabbitModelMixin implements HeadModelParts {

    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart leftEar;
    @Shadow @Final private ModelPart rightEar;
    @Shadow @Final private ModelPart nose;
    @Unique private Set<ModelPart> eamHeadParts;

    /**
     * An injection to the tail of the constructor to get the head parts of the
     * model.
     *
     * @param root the root of the model
     * @param ci a handler for managing callbacks to the original method
     */
    @Inject(at = @At("TAIL"), method = "<init>")
    private void postInit(ModelPart root, CallbackInfo ci) {
        this.eamHeadParts = ImmutableSet.of(this.head, this.leftEar, this.rightEar, this.nose);
    }

    @Override
    public Set<ModelPart> headSet() {
        return this.eamHeadParts;
    }
}
