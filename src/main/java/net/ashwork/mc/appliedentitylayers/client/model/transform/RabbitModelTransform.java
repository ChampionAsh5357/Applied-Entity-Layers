package net.ashwork.mc.appliedentitylayers.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.EntityModelTransform;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.ashwork.mc.appliedentitylayers.client.extension.RabbitModelExtension;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Rabbit;

import java.util.List;
import java.util.function.Function;

public class RabbitModelTransform<T extends Rabbit, M extends RabbitModel<T>> extends EntityModelTransform<T, M> {

    public RabbitModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (RabbitModelExtension) this.model;

        // Check if young
        if (this.model.young) {
            // If the head
            if (ModelTransform.isIn(part, ext.head())) {
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

        // Perform transformation
        super.transformTo(pose, part);
    }
}
