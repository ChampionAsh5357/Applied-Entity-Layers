/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.integration;

import net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.minecraft.world.entity.EntityType;

/**
 * A plugin integration to apply entity layers to Minecraft entities.
 */
public class VanillaEntityLayers implements AppliedEntityLayersPlugin {

    @Override
    public void registerModels(ModelRegistry registry) {
        registry.registerTransformations(EntityType.ALLAY);
        registry.registerTransformations(EntityType.ARMOR_STAND);
        registry.registerTransformations(EntityType.AXOLOTL);
        registry.registerTransformations(EntityType.BAT);
        registry.registerTransformations(EntityType.BEE);
        registry.registerTransformations(EntityType.BLAZE);
        registry.registerTransformations(EntityType.CAMEL);
        registry.registerTransformations(EntityType.CAT);
        registry.registerTransformations(EntityType.CAVE_SPIDER);
        registry.registerTransformations(EntityType.CHICKEN);
        registry.registerTransformations(EntityType.COD);
        registry.registerTransformations(EntityType.COW);
        registry.registerTransformations(EntityType.CREEPER);
        registry.registerTransformations(EntityType.DOLPHIN);
        registry.registerTransformations(EntityType.DONKEY);
        registry.registerTransformations(EntityType.DROWNED);
        registry.registerTransformations(EntityType.ELDER_GUARDIAN);
        registry.registerTransformations(EntityType.ENDERMAN);
        registry.registerTransformations(EntityType.ENDERMITE);
        registry.registerTransformations(EntityType.EVOKER);
        registry.registerTransformations(EntityType.FOX);
        registry.registerTransformations(EntityType.FROG);
        registry.registerTransformations(EntityType.GHAST);
        registry.registerTransformations(EntityType.GIANT);
        registry.registerTransformations(EntityType.GLOW_SQUID);
        registry.registerTransformations(EntityType.GOAT);
        registry.registerTransformations(EntityType.GUARDIAN);
        registry.registerTransformations(EntityType.HOGLIN);
        registry.registerTransformations(EntityType.HORSE);
        registry.registerTransformations(EntityType.HUSK);
        registry.registerTransformations(EntityType.ILLUSIONER);
        registry.registerTransformations(EntityType.IRON_GOLEM);
        registry.registerTransformations(EntityType.LLAMA);
        registry.registerTransformations(EntityType.MAGMA_CUBE);
        registry.registerTransformations(EntityType.MOOSHROOM);
        registry.registerTransformations(EntityType.MULE);
        registry.registerTransformations(EntityType.OCELOT);
        registry.registerTransformations(EntityType.PANDA);
        registry.registerTransformations(EntityType.PARROT);
        registry.registerTransformations(EntityType.PHANTOM);
        registry.registerTransformations(EntityType.PIG);
        registry.registerTransformations(EntityType.PIGLIN);
        registry.registerTransformations(EntityType.PIGLIN_BRUTE);
        registry.registerTransformations(EntityType.PILLAGER);
        registry.registerTransformations(EntityType.POLAR_BEAR);
        registry.registerTransformations(EntityType.PUFFERFISH);
        registry.registerTransformations(EntityType.RABBIT);
        registry.registerTransformations(EntityType.RAVAGER);
        registry.registerTransformations(EntityType.SALMON);
        registry.registerTransformations(EntityType.SHEEP);
        registry.registerTransformations(EntityType.SHULKER);
        registry.registerTransformations(EntityType.SILVERFISH);
        registry.registerTransformations(EntityType.SKELETON);
        registry.registerTransformations(EntityType.SKELETON_HORSE);
        registry.registerTransformations(EntityType.SLIME);
        registry.registerTransformations(EntityType.SNIFFER);
        registry.registerTransformations(EntityType.SNOW_GOLEM);
        registry.registerTransformations(EntityType.SPIDER);
        registry.registerTransformations(EntityType.SQUID);
        registry.registerTransformations(EntityType.STRAY);
        registry.registerTransformations(EntityType.STRIDER);
        registry.registerTransformations(EntityType.TADPOLE);
        registry.registerTransformations(EntityType.TRADER_LLAMA);
        registry.registerTransformations(EntityType.TROPICAL_FISH);
        registry.registerTransformations(EntityType.TURTLE);
        registry.registerTransformations(EntityType.VEX);
        registry.registerTransformations(EntityType.VILLAGER);
        registry.registerTransformations(EntityType.VINDICATOR);
        registry.registerTransformations(EntityType.WANDERING_TRADER);
        registry.registerTransformations(EntityType.WARDEN);
        registry.registerTransformations(EntityType.WITCH);
        registry.registerTransformations(EntityType.WITHER);
        registry.registerTransformations(EntityType.WITHER_SKELETON);
        registry.registerTransformations(EntityType.WOLF);
        registry.registerTransformations(EntityType.ZOGLIN);
        registry.registerTransformations(EntityType.ZOMBIE);
        registry.registerTransformations(EntityType.ZOMBIE_HORSE);
        registry.registerTransformations(EntityType.ZOMBIE_VILLAGER);
        registry.registerTransformations(EntityType.ZOMBIFIED_PIGLIN);
    }
}
