package net.ashwork.mc.appliedentitylayers.mixin;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.impl.model.InternalModelPartGetter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(RabbitModel.class)
public abstract class RabbitModelMixin extends EntityModel<Entity> implements InternalModelPartGetter {

    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart leftEar;
    @Shadow @Final private ModelPart rightEar;
    @Shadow @Final private ModelPart nose;
    @Unique private Set<ModelPart> eamHeadParts;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void postInit(ModelPart root, CallbackInfo ci) {
        this.eamHeadParts = ImmutableSet.of(this.head, this.leftEar, this.rightEar, this.nose);
    }

    @Override
    public void translateAndRotate(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            // If the head
            if (InternalModelPartGetter.isIn(part, this.eamHeadParts)) {
                pose.scale(0.56666666f, 0.56666666f, 0.56666666f);
                pose.translate(0f, 1.375f, 0.125f);
            } else {
                // Else the rest of parts
                pose.scale(0.4f, 0.4f, 0.4f);
                pose.translate(0f, 2.25f, 0f);
            }
        } else {
            // Otherwise for the adult
            pose.scale(0.6f, 0.6f, 0.6f);
            pose.translate(0f, 1f, 0f);
        }

        // Perform transformation
        InternalModelPartGetter.super.translateAndRotate(pose, part);
    }
}
