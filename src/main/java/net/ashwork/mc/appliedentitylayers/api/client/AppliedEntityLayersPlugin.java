/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client;

import net.ashwork.mc.appliedentitylayers.api.client.model.ModelRegistry;

/**
 * An interface representing an entrypoint to be loaded as a plugin for this mod.
 *
 * <p>This plugin uses {@link java.util.ServiceLoader#load(Class)} to load the
 * necessary classes. As such, the implementing class should be added to a
 * {@code META-INF/services/net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin}
 * file.
 * <pre>{@code
 * // If our full canonical path is com.example.PluginImpl, it should be
 * // added to META-INF/services/net.ashwork.mc.appliedentitylayers.api.client.AppliedEntityLayersPlugin
 * public class PluginImpl implements AppliedEntityLayersPlugin {
 *     // ...
 * }
 * }</pre>
 */
public interface AppliedEntityLayersPlugin {

    /**
     * Registers the models that will use the entity layers provided by this mod.
     *
     * @param registry the model registry
     */
    void registerModels(ModelRegistry registry);
}
