package net.ashwork.mc.appliedentitylayers.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelPartGetter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

//TODO: Document
public class ExpandedBeeStingerLayer<T extends LivingEntity, M extends EntityModel<T>> extends ExpandedStuckInBodyLayer<T, M> {

    private static final ResourceLocation BEE_STINGER_LOCATION = new ResourceLocation("textures/entity/bee/bee_stinger.png");
    private final RenderType type;

    public ExpandedBeeStingerLayer(RenderLayerParent<T, M> parent, Function<M, ModelPartGetter> getter) {
        super(parent, getter, 1);
        this.type = RenderType.entityCutoutNoCull(BEE_STINGER_LOCATION);
    }

    @Override
    protected int numStuck(T entity) {
        return entity.getStingerCount();
    }

    @Override
    protected void renderStuckItem(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float xRand, float yRand, float zRand, float partialTick) {
        // Setup rotations
        pose.mulPose(Axis.YP.rotationDegrees((float) Math.toDegrees(Math.atan2(xRand, zRand)) - 90f));
        pose.mulPose(Axis.ZP.rotationDegrees((float) Math.toDegrees(Math.atan2(yRand, Mth.sqrt(xRand * xRand + zRand * zRand)))));
        pose.mulPose(Axis.XP.rotationDegrees(45f));
        pose.scale(0.03125f, 0.03125f, 0.03125f);
        pose.translate(2.5f, 0f, 0f);

        // Get vbo
        var vbo = vao.getBuffer(this.type);

        // Render stinger
        for (int i = 0; i < 4; ++i) {
            pose.mulPose(Axis.XP.rotationDegrees(90f));
            var lastPose = pose.last().pose();
            var lastNormal = pose.last().normal();
            vbo.vertex(lastPose, -4.5f, -1f, 0f).color(255, 255, 255, 255).uv(0f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(lastNormal, 0f, 1f, 0f).endVertex();
            vbo.vertex(lastPose, 4.5f, -1f, 0f).color(255, 255, 255, 255).uv(0.125f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(lastNormal, 0f, 1f, 0f).endVertex();
            vbo.vertex(lastPose, 4.5f, 1f, 0f).color(255, 255, 255, 255).uv(0.125f, 0.0625f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(lastNormal, 0f, 1f, 0f).endVertex();
            vbo.vertex(lastPose, -4.5f, 1f, 0f).color(255, 255, 255, 255).uv(0f, 0.0625f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(lastNormal, 0f, 1f, 0f).endVertex();
        }
    }
}
