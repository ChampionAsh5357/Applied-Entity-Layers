package net.ashwork.mc.appliedentitylayers.api.client.model.armor;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

// TODO: Document, see if implementation is logical
@FunctionalInterface
public interface ArmorRenderType {

    RenderType getType(LivingEntity entity, ItemStack stack, ArmorItem.Type type, ResourceLocation texture);
}
