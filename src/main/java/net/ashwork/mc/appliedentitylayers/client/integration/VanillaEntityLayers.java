/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.integration;

import net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.AgeableHierarchicalModelTransform;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.AgeableListModelTransform;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.EntityModelTransform;
import net.ashwork.mc.appliedentitylayers.api.client.model.transform.ModelTransform;
import net.ashwork.mc.appliedentitylayers.client.extension.EntityModelExtension;
import net.ashwork.mc.appliedentitylayers.client.model.transform.CamelModelTransform;
import net.ashwork.mc.appliedentitylayers.client.model.transform.LlamaModelTransform;
import net.ashwork.mc.appliedentitylayers.client.model.transform.RabbitModelTransform;
import net.ashwork.mc.appliedentitylayers.client.model.transform.TurtleModelTransform;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.AllayModel;
import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.AxolotlModel;
import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.BeeModel;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.ChestedHorseModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.FoxModel;
import net.minecraft.client.model.FrogModel;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.GiantZombieModel;
import net.minecraft.client.model.GoatModel;
import net.minecraft.client.model.GuardianModel;
import net.minecraft.client.model.HoglinModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.LavaSlimeModel;
import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.PandaModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.PufferfishBigModel;
import net.minecraft.client.model.PufferfishMidModel;
import net.minecraft.client.model.PufferfishSmallModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.StriderModel;
import net.minecraft.client.model.TadpoleModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.TropicalFishModelB;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.WitchModel;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A plugin integration to apply entity layers to Minecraft entities.
 */
public class VanillaEntityLayers implements AppliedEntityLayersPlugin {

    @SuppressWarnings("unchecked")
    @Override
    public void registerModels(ModelRegistry registry) {
        // Register Model Transforms
        registry.registerTransformFactory(AllayModel.class, simple());
        registry.registerTransformFactory(ArmorStandModel.class, ageableList());
        registry.registerTransformFactory(AxolotlModel.class, ageableList());
        registry.registerTransformFactory(BatModel.class, simple());
        registry.registerTransformFactory(BeeModel.class, ageableList());
        registry.registerTransformFactory(BlazeModel.class, simple());
        registry.registerTransformFactory(CamelModel.class, factory(CamelModelTransform::new));
        registry.registerTransformFactory(CatModel.class, ageableList());
        registry.registerTransformFactory(ChestedHorseModel.class, ageableList());
        registry.registerTransformFactory(ChickenModel.class, ageableList());
        registry.registerTransformFactory(CodModel.class, simple());
        registry.registerTransformFactory(CowModel.class, ageableList());
        registry.registerTransformFactory(CreeperModel.class, simple());
        registry.registerTransformFactory(DolphinModel.class, simple());
        registry.registerTransformFactory(DrownedModel.class, ageableList());
        registry.registerTransformFactory(EndermanModel.class, ageableList());
        registry.registerTransformFactory(EndermiteModel.class, simple());
        registry.registerTransformFactory(FoxModel.class, ageableList());
        registry.registerTransformFactory(FrogModel.class, simple());
        registry.registerTransformFactory(GhastModel.class, simple());
        registry.registerTransformFactory(GiantZombieModel.class, ageableList());
        registry.registerTransformFactory(GoatModel.class, ageableList());
        registry.registerTransformFactory(GuardianModel.class, simple());
        registry.registerTransformFactory(HoglinModel.class, ageableList());
        registry.registerTransformFactory(HorseModel.class, ageableList());
        registry.registerTransformFactory(IllagerModel.class, simple());
        registry.registerTransformFactory(IronGolemModel.class, simple());
        registry.registerTransformFactory(LavaSlimeModel.class, simple());
        registry.registerTransformFactory(LlamaModel.class, factory(LlamaModelTransform::new));
        registry.registerTransformFactory(OcelotModel.class, ageableList());
        registry.registerTransformFactory(PandaModel.class, ageableList());
        registry.registerTransformFactory(ParrotModel.class, simple());
        registry.registerTransformFactory(PhantomModel.class, simple());
        registry.registerTransformFactory(PigModel.class, ageableList());
        registry.registerTransformFactory(PiglinModel.class, ageableList());
        registry.registerTransformFactory(PolarBearModel.class, ageableList());
        registry.registerTransformFactory(PufferfishBigModel.class, simple());
        registry.registerTransformFactory(PufferfishMidModel.class, simple());
        registry.registerTransformFactory(PufferfishSmallModel.class, simple());
        registry.registerTransformFactory(RabbitModel.class, factory(RabbitModelTransform::new));
        registry.registerTransformFactory(RavagerModel.class, simple());
        registry.registerTransformFactory(SalmonModel.class, simple());
        registry.registerTransformFactory(SheepModel.class, ageableList());
        registry.registerTransformFactory(ShulkerModel.class, simple());
        registry.registerTransformFactory(SilverfishModel.class, simple());
        registry.registerTransformFactory(SkeletonModel.class, ageableList());
        registry.registerTransformFactory(SlimeModel.class, simple());
        registry.registerTransformFactory(SnifferModel.class, ageableHierarchical());
        registry.registerTransformFactory(SnowGolemModel.class, simple());
        registry.registerTransformFactory(SpiderModel.class, simple());
        registry.registerTransformFactory(SquidModel.class, simple());
        registry.registerTransformFactory(StriderModel.class, simple());
        registry.registerTransformFactory(TadpoleModel.class, ageableList());
        registry.registerTransformFactory(TropicalFishModelA.class, simple());
        registry.registerTransformFactory(TropicalFishModelB.class, simple());
        registry.registerTransformFactory(TurtleModel.class, factory(TurtleModelTransform::new));
        registry.registerTransformFactory(VexModel.class, simple());
        registry.registerTransformFactory(VillagerModel.class, simple());
        registry.registerTransformFactory(WardenModel.class, simple());
        registry.registerTransformFactory(WitchModel.class, simple());
        registry.registerTransformFactory(WitherBossModel.class, simple());
        registry.registerTransformFactory(WolfModel.class, ageableList());
        registry.registerTransformFactory(ZombieModel.class, ageableList());
        registry.registerTransformFactory(ZombieVillagerModel.class, ageableList());

        registry.enableLayers(settings -> settings.arrow().beeStinger(),
                EntityType.ALLAY, EntityType.ARMOR_STAND, EntityType.AXOLOTL,
                EntityType.BAT, EntityType.BEE, EntityType.BLAZE,
                EntityType.CAMEL, EntityType.CAT, EntityType.CAVE_SPIDER,
                EntityType.CHICKEN, EntityType.COD, EntityType.COW,
                EntityType.CREEPER, EntityType.DOLPHIN, EntityType.DONKEY,
                EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN,
                EntityType.ENDERMITE, EntityType.EVOKER, EntityType.FOX,
                EntityType.FROG, EntityType.GHAST, EntityType.GIANT,
                EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.GUARDIAN,
                EntityType.HOGLIN, EntityType.HORSE, EntityType.HUSK,
                EntityType.ILLUSIONER, EntityType.IRON_GOLEM, EntityType.LLAMA,
                EntityType.MAGMA_CUBE, EntityType.MOOSHROOM, EntityType.MULE,
                EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT,
                EntityType.PHANTOM, EntityType.PIG, EntityType.PIGLIN,
                EntityType.PIGLIN_BRUTE, EntityType.PILLAGER, EntityType.POLAR_BEAR,
                EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.RAVAGER,
                EntityType.SALMON, EntityType.SHEEP, EntityType.SHULKER,
                EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE,
                EntityType.SLIME, EntityType.SNIFFER, EntityType.SNOW_GOLEM,
                EntityType.SPIDER, EntityType.SQUID, EntityType.STRAY,
                EntityType.STRIDER, EntityType.TADPOLE, EntityType.TRADER_LLAMA,
                EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.VEX,
                EntityType.VILLAGER, EntityType.VINDICATOR, EntityType.WANDERING_TRADER,
                EntityType.WARDEN, EntityType.WITCH, EntityType.WITHER,
                EntityType.WITHER_SKELETON, EntityType.WOLF, EntityType.ZOGLIN,
                EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER,
                EntityType.ZOMBIFIED_PIGLIN
        );
    }

    /**
     * A transform factory constructed for the specified {@link ModelTransform}.
     *
     * @param factory the factory to create the transform from
     * @return a factory to get the transform of
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private static <T extends LivingEntity, M extends EntityModel<T>> ModelTransform.Factory<T, M> factory(BiFunction<M, Function<M, List<ModelPart>>, ModelTransform> factory) {
        return model -> factory.apply(model, m -> ((EntityModelExtension) m).nonEmptyParts());
    }

    /**
     * Constructs a simple transform factory constructed for an {@link EntityModel}.
     *
     * @return a factory to get the transform of
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private static <T extends LivingEntity, M extends EntityModel<T>> ModelTransform.Factory<T, M> simple() {
        return factory(EntityModelTransform::new);
    }

    /**
     * Constructs a simple transform factory constructed for an {@link AgeableListModel}.
     *
     * @return a factory to get the transform of
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private static <T extends LivingEntity, M extends AgeableListModel<T>> ModelTransform.Factory<T, M> ageableList() {
        return factory(AgeableListModelTransform::new);
    }

    /**
     * Constructs a simple transform factory constructed for an {@link AgeableHierarchicalModel}.
     *
     * @return a factory to get the transform of
     * @param <T> the type of the living entity
     * @param <M> the type of the entity model
     */
    private static <T extends LivingEntity, M extends AgeableHierarchicalModel<T>> ModelTransform.Factory<T, M> ageableHierarchical() {
        return factory(AgeableHierarchicalModelTransform::new);
    }
}
