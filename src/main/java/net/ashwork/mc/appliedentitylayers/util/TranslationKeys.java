/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.util;

import net.ashwork.mc.appliedentitylayers.AppliedEntityLayers;

/**
 * A utility containing information related to the translation keys used within
 * this mod.
 */
public class TranslationKeys {

    /**
     * Private constructor.
     */
    private TranslationKeys() {
        throw new AssertionError(TranslationKeys.class.getSimpleName() + " should not be initialized.");
    }

    /**
     * A translation key representing the description of this mod's resources
     * in the {@code pack.mcmeta}.
     */
    public static final String PACK_DESCRIPTION = create("pack", "description");

    /**
     * Creates a new localization key with this mod's id and interns it for
     * any future use.
     *
     * @param type the object type the localization is representing
     * @param key the key of the object the localization is representing
     * @return a localization key
     */
    public static String create(String type, String key) {
        return (type + "." + AppliedEntityLayers.ID + "." + key).intern();
    }
}
