package net.ashwork.mc.appliedentitylayers.api.client.model.transform;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.extension.ModelPartExtension;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// TODO: Document
public class EntityModelTransform<T extends LivingEntity, M extends EntityModel<T>> implements ModelTransform {

    protected final M model;
    private final List<ModelPart> nonEmptyParts;

    public EntityModelTransform(M model, Function<M, List<ModelPart>> nonEmptyParts) {
        this.model = model;
        this.nonEmptyParts = nonEmptyParts.apply(this.model);
    }

    @Override
    @Nullable
    public ModelPart getRandomModelPart(RandomSource random) {
        // Get only visible parts and select one
        var visibleParts = this.nonEmptyParts.stream().filter(part -> ((ModelPartExtension) (Object) part).isVisible()).toList();
        return visibleParts.get(random.nextInt(visibleParts.size()));
    }

    @Override
    public void transformTo(PoseStack pose, ModelPart part) {
        // Get all parts to apply transformations for
        List<ModelPart> transformations = new ArrayList<>();
        while (part != null) {
            // Transformations are applied from parent -> child
            transformations.add(0, part);
            part = ((ModelPartExtension) (Object) part).getParent();
        }

        // Apply transformations
        transformations.forEach(transform -> transform.translateAndRotate(pose));
    }
}
