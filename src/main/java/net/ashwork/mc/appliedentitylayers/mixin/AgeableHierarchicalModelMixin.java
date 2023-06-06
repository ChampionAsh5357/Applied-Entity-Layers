package net.ashwork.mc.appliedentitylayers.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.client.impl.model.InternalModelPartGetter;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AgeableHierarchicalModel.class)
public abstract class AgeableHierarchicalModelMixin extends HierarchicalModel<Entity> implements InternalModelPartGetter {


    @Shadow @Final private float youngScaleFactor;
    @Shadow @Final private float bodyYOffset;

    @Override
    public void translateAndRotate(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.young) {
            // If head
            pose.scale(this.youngScaleFactor, this.youngScaleFactor, this.youngScaleFactor);
            pose.translate(0f, this.bodyYOffset / 16f, 0f);
        }

        // Perform transformation
        InternalModelPartGetter.super.translateAndRotate(pose, part);
    }
}
