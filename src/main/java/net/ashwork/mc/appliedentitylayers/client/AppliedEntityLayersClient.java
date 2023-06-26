/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client;

import net.ashwork.mc.appliedentitylayers.api.ConditionalPlugin;
import net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin;
import net.ashwork.mc.appliedentitylayers.client.model.manager.ModelManager;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

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
     */
    public static void init(IEventBus modBus) {
        modBus.addListener(EventPriority.LOWEST, AppliedEntityLayersClient::buildModelData);
        modBus.addListener(EventPriority.LOWEST, AppliedEntityLayersClient::addLayers);
    }

    /**
     * Loads all plugins and builds the data associated with the model. The data
     * is registered statically, similar to the renderer factories.
     *
     * @param event an event instance
     */
    private static void buildModelData(EntityRenderersEvent.RegisterRenderers event) {
        ServiceLoader.load(AppliedEntityLayersPlugin.class)
                // Check if plugins should be loaded
                .stream().filter(AppliedEntityLayersClient::shouldLoadPlugin)
                // Load plugin
                .map(ServiceLoader.Provider::get)
                // Register models
                .forEach(plugin -> plugin.registerModels(MODEL_MANAGER));
        MODEL_MANAGER.build();
    }

    /**
     * {@return whether the plugin should be loaded by the service loader}
     *
     * @param provider the provider containing the lazy plugin
     */
    private static boolean shouldLoadPlugin(ServiceLoader.Provider<AppliedEntityLayersPlugin> provider) {
        var type = provider.type();
        // Check if the plugin is not conditional
        // If it is conditional, check if the mod is loaded
        return !type.isAnnotationPresent(ConditionalPlugin.class) || ModList.get().isLoaded(type.getAnnotation(ConditionalPlugin.class).value());
    }

    /**
     * Applies the layers to the renderer based on the provided data for the
     * associated entity type.
     *
     * @param event an event instance
     */
    private static void addLayers(EntityRenderersEvent.AddLayers event) {
        MODEL_MANAGER.applyLayers(event::getRenderer, event.getEntityModels());
    }
}
