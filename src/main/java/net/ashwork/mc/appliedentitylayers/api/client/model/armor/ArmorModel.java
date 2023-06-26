package net.ashwork.mc.appliedentitylayers.api.client.model.armor;

import net.minecraft.client.model.Model;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

// TODO: Document, see if implementation is logical
@FunctionalInterface
public interface ArmorModel {

    Model getAndSetup(LivingEntity entity, ItemStack stack, ArmorItem.Type type, Model parent);
}
