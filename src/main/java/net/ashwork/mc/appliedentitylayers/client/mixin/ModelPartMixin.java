/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import net.ashwork.mc.appliedentitylayers.api.client.model.extension.ModelPartExtension;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

/**
 * A mixin applied to {@link ModelPart}.
 */
@Mixin(ModelPart.class)
public class ModelPartMixin implements ModelPartExtension {

    @Unique private ModelPart eamParent = null;

    /**
     * An injection to the tail of the constructor to set the children parts' parent.
     *
     * @param cubes a list of cubes for the given part
     * @param children a map of names to their corresponding child part
     * @param ci a handler for managing callbacks to the original method
     */
    @Inject(at = @At("TAIL"), method = "<init>")
    private void postInit(List<ModelPart.Cube> cubes, Map<String, ModelPart> children, CallbackInfo ci) {
        // Set the children parts' parent on init
        children.values().forEach(part -> ((ModelPartMixin) (Object) part).eamParent = ((ModelPart) (Object) this));
    }

    @Nullable
    @Override
    public ModelPart getParent() {
        return this.eamParent;
    }

    @Override
    public boolean isVisible() {
        return ((ModelPart) (Object) this).visible && (this.getParent() == null || ((ModelPartExtension) (Object) this.getParent()).isVisible());
    }
}
