package net.ashwork.mc.appliedentitylayers.api.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

// TODO: Document
public interface ArmorRenderer extends ArmorModel, ArmorTexture, ArmorRenderType {

    void createModels(EntityModelSet modelSet);

    default void render(PoseStack pose, MultiBufferSource vao, LivingEntity entity, ItemStack stack, ArmorItem.Type type, int packedLight, Model parent) {
        var model = this.getAndSetup(entity, stack, type, parent);
        var normVbo = vao.getBuffer(this.getType(entity, stack, type, this.getTexture(entity, stack, type, null)));
        model.renderToBuffer(pose, normVbo, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        if (stack.getItem() instanceof DyeableLeatherItem dyeable) {
            var color = dyeable.getColor(stack);
            final float r = (float)(color >> 16 & 255) / 255f, g = (float)(color >> 8 & 255) / 255f, b = (float)(color & 255) / 255f;
            model.renderToBuffer(pose, vao.getBuffer(this.getType(entity, stack, type, this.getTexture(entity, stack, type, "overlay"))), packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1f);
        }
    }
}
