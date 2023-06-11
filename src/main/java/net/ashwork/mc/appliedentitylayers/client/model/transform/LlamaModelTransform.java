package net.ashwork.mc.appliedentitylayers.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.EntityModelTransform;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.ashwork.mc.appliedentitylayers.client.extension.LlamaModelExtension;
import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;

import java.util.List;
import java.util.function.Function;

public class LlamaModelTransform<T extends AbstractChestedHorse, M extends LlamaModel<T>> extends EntityModelTransform<T, M> {

    public LlamaModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        super(model, nonEmptyParts);
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        var ext = (LlamaModelExtension) this.model;

        // Check if young
        if (this.model.young) {
            // If the head
            if (ModelTransform.isIn(part, ext.head())) {
                pose.scale(0.71428573f, 0.64935064f, 0.7936508f);
                pose.translate(0f, 1.3125f, 0.22f);
            } else if (ModelTransform.isIn(part, ext.body())) {
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
        super.transformTo(pose, part);
    }
}
