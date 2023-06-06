/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.util;

import org.jetbrains.annotations.ApiStatus;

/**
 * A utility containing the supported locales of this mod.
 */
@ApiStatus.Internal
public class SupportedLocales {

    /**
     * Private constructor.
     */
    private SupportedLocales() {
        throw new AssertionError("SupportedLocales should not be initialized.");
    }

    /**
     * The English (United States) locale code.
     */
    public static final String EN_US = "en_us";
}
