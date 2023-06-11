package net.ashwork.mc.appliedentitylayers.api.client.model.extension;

import net.minecraft.client.model.geom.ModelPart;

// TODO: Document
public interface AgeableListModelExtension {

    boolean scaleHead();

    float babyYHeadOffset();

    float babyZHeadOffset();

    float babyHeadScale();

    float babyBodyScale();

    float bodyYOffset();

    Iterable<ModelPart> head();
}
