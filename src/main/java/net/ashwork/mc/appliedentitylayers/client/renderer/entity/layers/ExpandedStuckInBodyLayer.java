package net.ashwork.mc.appliedentitylayers.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelPartGetter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

// TODO: Document, see StuckInBodyLayer
public abstract class ExpandedStuckInBodyLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private final Function<M, ModelPartGetter> getter;
    private final int offset;

    public ExpandedStuckInBodyLayer(RenderLayerParent<T, M> parent, Function<M, ModelPartGetter> getter, int offset) {
        super(parent);
        this.getter = getter;
        this.offset = offset;
    }

    protected abstract int numStuck(T entity);

    protected abstract void renderStuckItem(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float x, float y, float z, float partialTick);

    @Override
    public void render(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float walkPosition, float walkSpeed, float partialTick, float bob, float yRot, float xRot) {
        // Check if there are any stuck
        var stuck = this.numStuck(entity);
        if (stuck > 0) {
            // Create random
            var random = RandomSource.create(entity.getId() + this.offset);

            // For each stuck object
            for (int i = 0; i < stuck; ++i) {
                pose.pushPose();

                // Get random part and apply matrix
                var partGetter = this.getter.apply(this.getParentModel());
                var part = partGetter.getRandomModelPart(random);
                var cube = part.getRandomCube(random);
                partGetter.translateAndRotate(pose, part);

                // Translate to stuck position
                var xRand = random.nextFloat();
                var yRand = random.nextFloat();
                var zRand = random.nextFloat();
                pose.translate(
                        Mth.lerp(xRand, cube.minX, cube.maxX) / 16f,
                        Mth.lerp(yRand, cube.minY, cube.maxY) / 16f,
                        Mth.lerp(zRand, cube.minZ, cube.maxZ) / 16f
                );

                // Render stuck position with scaled [-1, 1] values
                this.renderStuckItem(pose, vao, packedLight, entity, -(2f * xRand - 1f), -(2f * yRand - 1f), -(2f * zRand - 1f), partialTick);
                pose.popPose();
            }
        }
    }
}
