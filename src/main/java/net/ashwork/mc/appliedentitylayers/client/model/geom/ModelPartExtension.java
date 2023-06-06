package net.ashwork.mc.appliedentitylayers.client.model.geom;

import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;

// TODO: Document
public interface ModelPartExtension {

    @Nullable
    ModelPart getParent();

    boolean isVisible();
}
