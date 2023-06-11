package net.ashwork.mc.appliedentitylayers.api.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

// TODO: Document
public interface ModelProvider<T extends LivingEntity, M extends EntityModel<T>, A extends EntityModel<T>> {

    A getArmorModel(LivingEntity entity, ItemStack stack, ArmorItem.Type type, M parentModel);
}
