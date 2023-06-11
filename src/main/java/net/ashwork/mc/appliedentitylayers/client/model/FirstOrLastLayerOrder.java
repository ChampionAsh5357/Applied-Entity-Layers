package net.ashwork.mc.appliedentitylayers.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

// TODO:
public interface FirstOrLastLayerOrder<T extends LivingEntity, M extends EntityModel<T>> {

    void addLayerFirst(RenderLayer<T, M> layer);

    void addLayerLast(RenderLayer<T, M> layer);
}
