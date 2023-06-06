package net.ashwork.mc.appliedentitylayers.mixin;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.impl.model.InternalModelPartGetter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.LlamaModel;
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

@Mixin(LlamaModel.class)
public abstract class LlamaModelMixin extends EntityModel<Entity> implements InternalModelPartGetter {

    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart body;
    @Unique private Set<ModelPart> eamHeadParts, eamBodyParts;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void postInit(ModelPart root, CallbackInfo ci) {
        this.eamHeadParts = ImmutableSet.of(this.head);
        this.eamBodyParts = ImmutableSet.of(this.body);
    }

    @Override
    public void translateAndRotate(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            // If the head
            if (InternalModelPartGetter.isIn(part, this.eamHeadParts)) {
                pose.scale(0.71428573f, 0.64935064f, 0.7936508f);
                pose.translate(0f, 1.3125f, 0.22f);
            } else if (InternalModelPartGetter.isIn(part, this.eamBodyParts)) {
                // Else the body
                pose.scale(0.625f, 0.45454544f, 0.45454544f);
                pose.translate(0f, 2.0625f, 0f);
            } else {
                // Else the rest of parts
                pose.scale(0.45454544f, 0.41322312f, 0.45454544f);
                pose.translate(0f, 2.0625f, 0f);
            }
        }

        // Perform transformation
        InternalModelPartGetter.super.translateAndRotate(pose, part);
    }
}
