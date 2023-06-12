/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.client.mixin.base;

import net.minecraft.Util;
import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A companion config plugin which stops the application of mixins to target classes
 * whose base mod is not currently loaded.
 */
public class ConditionalConfigPlugin implements IMixinConfigPlugin {

    /**
     * A map containing an easy lookup for all packages within the mods this mod
     * cares about.
     */
    private static final Map<String, Predicate<String>> MOD_PACKAGE_PREFIX = Util.make(new HashMap<>(), map -> map.put("twilightforest", s -> s.startsWith("twilightforest.")));
    private final List<Predicate<String>> blacklist;

    /**
     * Constructs the companion plugin.
     */
    public ConditionalConfigPlugin() {
        this.blacklist = new ArrayList<>();

        // Blacklist mods that aren't loaded
        MOD_PACKAGE_PREFIX.forEach((id, pred) -> {
            if (LoadingModList.get().getModFileById(id) == null)
                this.blacklist.add(pred);
        });
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return this.blacklist.stream().noneMatch(pred -> pred.test(targetClassName));
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
