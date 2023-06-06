/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.impl;

import net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin;
import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;
import net.minecraft.world.entity.EntityType;

// TODO: Document
public class VanillaEntityLayers implements AppliedEntityLayersPlugin {

    @Override
    public void registerModels(ModelRegistry registry) {
        registry.registerPartGetter(EntityType.ALLAY);
        registry.registerPartGetter(EntityType.ARMOR_STAND);
        registry.registerPartGetter(EntityType.AXOLOTL);
        registry.registerPartGetter(EntityType.BAT);
        registry.registerPartGetter(EntityType.BEE);
        registry.registerPartGetter(EntityType.BLAZE);
        registry.registerPartGetter(EntityType.CAMEL);
        registry.registerPartGetter(EntityType.CAT);
        registry.registerPartGetter(EntityType.CAVE_SPIDER);
        registry.registerPartGetter(EntityType.CHICKEN);
        registry.registerPartGetter(EntityType.COD);
        registry.registerPartGetter(EntityType.COW);
        registry.registerPartGetter(EntityType.CREEPER);
        registry.registerPartGetter(EntityType.DOLPHIN);
        registry.registerPartGetter(EntityType.DONKEY);
        registry.registerPartGetter(EntityType.DROWNED);
        registry.registerPartGetter(EntityType.ELDER_GUARDIAN);
        registry.registerPartGetter(EntityType.ENDERMAN);
        registry.registerPartGetter(EntityType.ENDERMITE);
        registry.registerPartGetter(EntityType.EVOKER);
        registry.registerPartGetter(EntityType.FOX);
        registry.registerPartGetter(EntityType.FROG);
        registry.registerPartGetter(EntityType.GHAST);
        registry.registerPartGetter(EntityType.GIANT);
        registry.registerPartGetter(EntityType.GLOW_SQUID);
        registry.registerPartGetter(EntityType.GOAT);
        registry.registerPartGetter(EntityType.GUARDIAN);
        registry.registerPartGetter(EntityType.HOGLIN);
        registry.registerPartGetter(EntityType.HORSE);
        registry.registerPartGetter(EntityType.HUSK);
        registry.registerPartGetter(EntityType.ILLUSIONER);
        registry.registerPartGetter(EntityType.IRON_GOLEM);
        registry.registerPartGetter(EntityType.LLAMA);
        registry.registerPartGetter(EntityType.MAGMA_CUBE);
        registry.registerPartGetter(EntityType.MOOSHROOM);
        registry.registerPartGetter(EntityType.MULE);
        registry.registerPartGetter(EntityType.OCELOT);
        registry.registerPartGetter(EntityType.PANDA);
        registry.registerPartGetter(EntityType.PARROT);
        registry.registerPartGetter(EntityType.PHANTOM);
        registry.registerPartGetter(EntityType.PIG);
        registry.registerPartGetter(EntityType.PIGLIN);
        registry.registerPartGetter(EntityType.PIGLIN_BRUTE);
        registry.registerPartGetter(EntityType.PILLAGER);
        registry.registerPartGetter(EntityType.POLAR_BEAR);
        registry.registerPartGetter(EntityType.PUFFERFISH);
        registry.registerPartGetter(EntityType.RABBIT);
        registry.registerPartGetter(EntityType.RAVAGER);
        registry.registerPartGetter(EntityType.SALMON);
        registry.registerPartGetter(EntityType.SHEEP);
        registry.registerPartGetter(EntityType.SHULKER);
        registry.registerPartGetter(EntityType.SILVERFISH);
        registry.registerPartGetter(EntityType.SKELETON);
        registry.registerPartGetter(EntityType.SKELETON_HORSE);
        registry.registerPartGetter(EntityType.SLIME);
        registry.registerPartGetter(EntityType.SNIFFER);
        registry.registerPartGetter(EntityType.SNOW_GOLEM);
        registry.registerPartGetter(EntityType.SPIDER);
        registry.registerPartGetter(EntityType.SQUID);
        registry.registerPartGetter(EntityType.STRAY);
        registry.registerPartGetter(EntityType.STRIDER);
        registry.registerPartGetter(EntityType.TADPOLE);
        registry.registerPartGetter(EntityType.TRADER_LLAMA);
        registry.registerPartGetter(EntityType.TROPICAL_FISH);
        registry.registerPartGetter(EntityType.TURTLE);
        registry.registerPartGetter(EntityType.VEX);
        registry.registerPartGetter(EntityType.VILLAGER);
        registry.registerPartGetter(EntityType.VINDICATOR);
        registry.registerPartGetter(EntityType.WANDERING_TRADER);
        registry.registerPartGetter(EntityType.WARDEN);
        registry.registerPartGetter(EntityType.WITCH);
        registry.registerPartGetter(EntityType.WITHER);
        registry.registerPartGetter(EntityType.WITHER_SKELETON);
        registry.registerPartGetter(EntityType.WOLF);
        registry.registerPartGetter(EntityType.ZOGLIN);
        registry.registerPartGetter(EntityType.ZOMBIE);
        registry.registerPartGetter(EntityType.ZOMBIE_HORSE);
        registry.registerPartGetter(EntityType.ZOMBIE_VILLAGER);
        registry.registerPartGetter(EntityType.ZOMBIFIED_PIGLIN);
    }
}
