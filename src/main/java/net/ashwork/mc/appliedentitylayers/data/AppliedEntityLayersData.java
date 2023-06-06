/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.data;

import net.ashwork.mc.appliedentitylayers.data.client.TranslationProvider;
import net.ashwork.mc.appliedentitylayers.util.TranslationKeys;
import net.minecraft.DetectedVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The main mod class for data generation within this mod.
 */
public class AppliedEntityLayersData {

    /**
     * Initialize the data generation information for this mod.
     *
     * @param modBus the mod event bus
     */
    public static void init(IEventBus modBus) {
        modBus.addListener(AppliedEntityLayersData::gatherData);
    }

    /**
     * A listener for when providers are added to the {@link DataGenerator}.
     *
     * @param event the event instance
     */
    private static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        // Add pack metadata always
        addProvider(generator, output -> new PackMetadataGenerator(output)
                .add(PackMetadataSection.TYPE, new PackMetadataSection(
                        Component.literal(TranslationKeys.PACK_DESCRIPTION),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                        Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion))
                ))
        );

        // Include client info
        if (event.includeClient()) {
            addProvider(generator, TranslationProvider::new);
        }
    }

    /**
     * A helper to add a {@link DataProvider} to the {@link DataGenerator} using
     * the factory method. This is to avoid an ambiguity error between the two
     * methods to add a provider without needing to cast every factory.
     *
     * @param generator the generator the provider is added to
     * @param factory the factory used to create the provider
     * @param <T> the type of the provider
     */
    private static <T extends DataProvider> void addProvider(DataGenerator generator, DataProvider.Factory<T> factory) {
        generator.addProvider(true, factory);
    }
}
