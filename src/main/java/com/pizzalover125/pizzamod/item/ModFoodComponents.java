package com.pizzalover125.pizzamod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PIZZA = new FoodComponent.Builder()
            .nutrition(12)
            .saturationModifier(1.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0), 0.9f)
            .build();

    public static final FoodComponent PIZZA_SLICE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 0), 0.25f)
            .build();

    public static final FoodComponent RAW_PIZZA_SLICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.5f)
            .build();

    public static final FoodComponent CHEESE_SLICE = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .build();

    public static final FoodComponent TOMATO = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.2f)
            .build();
}
