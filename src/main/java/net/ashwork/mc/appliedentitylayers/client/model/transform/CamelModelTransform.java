package net.ashwork.mc.appliedentitylayers.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.EntityModelTransform;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.camel.Camel;

import java.util.List;
import java.util.function.Function;

public class CamelModelTransform<T extends Camel, M extends CamelModel<T>> extends EntityModelTransform<T, M> {

    public CamelModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        // Check if young
        if (this.model.young) {
            pose.scale(0.45f, 0.45f, 0.45f);
            pose.translate(0f, 1.834375f, 0f);
        }

        // Perform transformation
        super.transformTo(pose, part);
    }
}
