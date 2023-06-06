/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.data.client;

import net.ashwork.mc.appliedentitylayers.AppliedEntityLayers;
import net.ashwork.mc.appliedentitylayers.util.TranslationKeys;
import net.ashwork.mc.appliedentitylayers.util.SupportedLocales;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A {@link DataProvider} used to generate translations for any locales
 * specified.
 */
public class TranslationProvider implements DataProvider {

    private final Function<String, LanguageProvider> factory;
    private final Map<String, LanguageProvider> translations;

    /**
     * Constructs a new provider to generate language files for many locales.
     *
     * @param output metadata related to the output location of the generator
     */
    public TranslationProvider(PackOutput output) {
        this.factory = locale -> new LanguageProvider(output, AppliedEntityLayers.ID, locale) {
            @Override
            protected void addTranslations() {}
        };
        this.translations = new HashMap<>();
    }

    /**
     * Adds the localizations for the mod.
     */
    private void addTranslations() {
        this.localizeAll(TranslationKeys.PACK_DESCRIPTION, Map.of(
                SupportedLocales.EN_US, "Applied Entity Layers Resources"
        ));
    }

    /**
     * Localizes all names for a given key.
     *
     * @param key the key to add localizations for
     * @param localeToName a map of locales to localized names
     */
    private void localizeAll(String key, Map<String, String> localeToName) {
        this.localizeAll((provider, name) -> provider.add(key, name), localeToName);
    }

    /**
     * Localizes all names for a given provider method.
     *
     * @param nameInserter a consumer containing the {@link LanguageProvider} and
     *                     the localized name for that provider
     * @param localeToName a map of locales to localized names
     */
    private void localizeAll(BiConsumer<LanguageProvider, String> nameInserter, Map<String, String> localeToName) {
        localeToName.forEach((locale, name) -> this.forLocale(locale, provider -> nameInserter.accept(provider, name)));
    }

    /**
     * Grants access to the {@link LanguageProvider} for the specific locale to
     * add keys.
     *
     * @param locale the locale to get the localization for
     * @param provider a consumer containing the {@link LanguageProvider}
     */
    private void forLocale(String locale, Consumer<LanguageProvider> provider) {
        provider.accept(this.translations.computeIfAbsent(locale, this.factory));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        // Add translations
        this.addTranslations();

        // Add all completable futures to final future
        return CompletableFuture.allOf(
                this.translations.values().stream()
                        .map(provider -> provider.run(output))
                        .toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public String getName() {
        return "Translations: " + AppliedEntityLayers.ID;
    }
}
