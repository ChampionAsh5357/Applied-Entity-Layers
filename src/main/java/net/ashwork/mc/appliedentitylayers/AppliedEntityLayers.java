/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers;

import net.ashwork.mc.appliedentitylayers.client.AppliedEntityLayersClient;
import net.ashwork.mc.appliedentitylayers.data.AppliedEntityLayersData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * The main mod class for this mod.
 */
@Mod(AppliedEntityLayers.ID)
public class AppliedEntityLayers {

    /**
     * The identifier of this mod.
     */
    public static final String ID = "appliedentitylayers";

    /**
     * Constructs the mod instance.
     */
    public AppliedEntityLayers() {
        // Get buses
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        var forgeBus = MinecraftForge.EVENT_BUS;

        // Setup client
        if (FMLEnvironment.dist == Dist.CLIENT)
            AppliedEntityLayersClient.init(modBus);

        // Setup data
        AppliedEntityLayersData.init(modBus);

        // Add tests
        forgeBus.addListener(AppliedEntityLayers::testModels);
    }

    // Basic test case for adding arrows and stingers to entities
    @Deprecated(forRemoval = true)
    private static void testModels(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity living) {
            living.setStingerCount(5);
            living.setArrowCount(5);
        }
    }
}
