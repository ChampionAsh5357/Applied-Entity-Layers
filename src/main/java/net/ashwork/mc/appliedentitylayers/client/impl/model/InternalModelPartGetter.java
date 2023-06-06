package net.ashwork.mc.appliedentitylayers.client.impl.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelPartGetter;
import net.ashwork.mc.appliedentitylayers.client.model.geom.ModelPartExtension;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// TODO: Document
public interface InternalModelPartGetter extends ModelPartGetter {

    List<ModelPart> nonEmptyParts();

    @Override
    default ModelPart getRandomModelPart(RandomSource random) {
        var visibleParts = this.nonEmptyParts().stream().filter(part -> ((ModelPartExtension) (Object) part).isVisible()).toList();
        return visibleParts.get(random.nextInt(visibleParts.size()));
    }

    @Override
    default void translateAndRotate(PoseStack pose, ModelPart part) {
        // Get all parts to apply transformations for
        List<ModelPart> transformations = new ArrayList<>();
        while (part != null) {
            transformations.add(0, part);
            part = ((ModelPartExtension) (Object) part).getParent();
        }

        // Apply transformations
        transformations.forEach(transform -> transform.translateAndRotate(pose));
    }

    static List<ModelPart> parts(ModelPart root) {
        return root.getAllParts().filter(part -> !part.isEmpty()).toList();
    }

    static boolean isIn(ModelPart part, Set<ModelPart> partSet) {
        while (part != null) {
            if (partSet.contains(part)) return true;
            part = ((ModelPartExtension) (Object) part).getParent();
        }
        return false;
    }
}
