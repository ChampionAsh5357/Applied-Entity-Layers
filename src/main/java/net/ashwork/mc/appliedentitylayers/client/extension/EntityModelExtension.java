package net.ashwork.mc.appliedentitylayers.client.extension;

import net.minecraft.client.model.geom.ModelPart;

import java.util.List;

// TODO: Document
public interface EntityModelExtension {

    List<ModelPart> nonEmptyParts();
}
