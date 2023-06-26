package net.ashwork.mc.appliedentitylayers.api.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

// TODO: Document, see if implementation is logical
@FunctionalInterface
public interface ArmorTexture {

    ResourceLocation getTexture(LivingEntity entity, ItemStack stack, ArmorItem.Type type, @Nullable  String suffix);
}
