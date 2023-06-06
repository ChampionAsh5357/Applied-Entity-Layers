/*
 * Copyright (c) ChampionAsh5357
 * SPDX-License-Identifier: MIT
 */

package net.ashwork.mc.appliedentitylayers.api.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

// TODO: Document
public interface ModelRegistry {

    default <T extends LivingEntity> void registerPartGetter(EntityType<T> type) {
        this.registerPartGetter(type, model -> (ModelPartGetter) model);
    }

    <T extends LivingEntity, M extends EntityModel<T>> void registerPartGetter(EntityType<T> type, Function<M, ModelPartGetter> getter);
}
