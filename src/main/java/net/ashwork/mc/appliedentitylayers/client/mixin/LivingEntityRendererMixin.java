package net.ashwork.mc.appliedentitylayers.client.mixin;

import net.ashwork.mc.appliedentitylayers.client.model.FirstOrLastLayerOrder;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

// TODO: Document
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> implements FirstOrLastLayerOrder<T, M> {


    @Shadow public abstract boolean addLayer(RenderLayer<T, M> pLayer);

    @Shadow @Final protected List<RenderLayer<T, M>> layers;

    @Override
    public void addLayerFirst(RenderLayer<T, M> layer) {
        this.layers.add(0, layer);
    }

    @Override
    public void addLayerLast(RenderLayer<T, M> layer) {
        this.addLayer(layer);
    }
}
