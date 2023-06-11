/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin;

import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.ashwork.mc.appliedentitylayers.client.extension.EntityModelExtension;
import net.minecraft.client.model.AllayModel;
import net.minecraft.client.model.AxolotlModel;
import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.BeeModel;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.FoxModel;
import net.minecraft.client.model.FrogModel;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.GuardianModel;
import net.minecraft.client.model.HoglinModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.LavaSlimeModel;
import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.PufferfishBigModel;
import net.minecraft.client.model.PufferfishMidModel;
import net.minecraft.client.model.PufferfishSmallModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.StriderModel;
import net.minecraft.client.model.TadpoleModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.TropicalFishModelB;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

/**
 * A mixin applied to all entity models to construct the list of non-empty parts.
 */
@Mixin({
        // Minecraft
        AllayModel.class, AxolotlModel.class, BatModel.class, BeeModel.class,
        BlazeModel.class, CamelModel.class, CodModel.class, ChickenModel.class,
        CreeperModel.class, DolphinModel.class, EndermiteModel.class, FoxModel.class,
        FrogModel.class, GhastModel.class, GuardianModel.class, HoglinModel.class,
        HorseModel.class, HumanoidModel.class, IllagerModel.class, IronGolemModel.class, LavaSlimeModel.class,
        LlamaModel.class, OcelotModel.class, ParrotModel.class, PhantomModel.class,
        PufferfishBigModel.class, PufferfishMidModel.class, PufferfishSmallModel.class, RabbitModel.class,
        QuadrupedModel.class, RavagerModel.class, SalmonModel.class, ShulkerModel.class, SilverfishModel.class,
        SlimeModel.class, SnifferModel.class, SnowGolemModel.class, SpiderModel.class,
        SquidModel.class, StriderModel.class, TadpoleModel.class, TropicalFishModelA.class,
        TropicalFishModelB.class, VexModel.class, VillagerModel.class, WardenModel.class,
        WitherBossModel.class, WolfModel.class
})
public abstract class SimpleEntityModelMixin implements EntityModelExtension {

    @Unique private List<ModelPart> eamNonEmptyParts;

    /**
     * An injection to the tail of the constructors to set the non-empty parts of
     * a model.
     *
     * @param root the root of the model
     * @return the {@code root}
     */
    @ModifyVariable(at = @At("TAIL"), method = "<init>*", argsOnly = true, ordinal = 0)
    private ModelPart postInit(ModelPart root) {
        this.eamNonEmptyParts = ModelTransform.parts(root);
        return root;
    }

    @Override
    public List<ModelPart> nonEmptyParts() {
        return this.eamNonEmptyParts;
    }
}
