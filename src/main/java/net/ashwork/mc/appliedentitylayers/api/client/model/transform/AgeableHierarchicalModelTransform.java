package net.ashwork.mc.appliedentitylayers.api.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.AgeableHierarchicalModelExtension;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.Function;

// TODO: Document
public class AgeableHierarchicalModelTransform<T extends LivingEntity, M extends AgeableHierarchicalModel<T>> extends EntityModelTransform<T, M> {

    public AgeableHierarchicalModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (AgeableHierarchicalModelExtension) this.model;

        // Check if young
        if (this.model.young) {
            // If head
            pose.scale(ext.youngScaleFactor(), ext.youngScaleFactor(), ext.youngScaleFactor());
            pose.translate(0f, ext.bodyYOffset() / 16f, 0f);
        }

        // Perform transformation
        super.transformTo(pose, part);
    }
}
