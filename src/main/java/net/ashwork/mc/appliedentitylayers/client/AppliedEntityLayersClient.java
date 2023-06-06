/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client;

import net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin;
import net.ashwork.mc.appliedentitylayers.client.impl.model.ModelManager;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ServiceLoader;

/**
 * The main mod class for client information within this mod.
 */
public class AppliedEntityLayersClient {

    private static final ModelManager MODEL_MANAGER = new ModelManager();

    /**
     * Initialize the client information for this mod.
     *
     * @param modBus the mod event bus
     * @param forgeBus the Forge event bus
     */
    public static void init(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(EventPriority.LOWEST, AppliedEntityLayersClient::buildModelData);
        modBus.addListener(EventPriority.LOWEST, AppliedEntityLayersClient::addLayers);
    }

    // TODO: Document
    private static void buildModelData(EntityRenderersEvent.RegisterRenderers event) {
        ServiceLoader.load(AppliedEntityLayersPlugin.class).forEach(plugin -> plugin.registerModels(MODEL_MANAGER));
        MODEL_MANAGER.build();
    }

    // TODO: Document
    private static void addLayers(EntityRenderersEvent.AddLayers event) {
        MODEL_MANAGER.applyLayers(event::getRenderer);
    }
}
