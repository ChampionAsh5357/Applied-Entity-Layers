package net.ashwork.mc.appliedentitylayers.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;

// TODO: Document
public class ArmorRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public ArmorRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource vao, int packedLight, T entity, float walkPosition, float walkSpeed, float partialTick, float bob, float yRot, float xRot) {
        // Apply to all slots
        for (var type : ArmorItem.Type.values()) this.renderArmorPiece(pose, vao, entity, type, packedLight);
    }

    private void renderArmorPiece(PoseStack pose, MultiBufferSource vao, T entity, ArmorItem.Type type, int packedLight) {
        var stack = entity.getItemBySlot(type.getSlot());

        // Logic should check item first for any particular implementation
        // Then check the armor registry map
    }
}
