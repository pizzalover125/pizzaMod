package com.pizzalover125.pizzamod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PIZZA = new FoodComponent.Builder()
            .nutrition(12)
            .saturationModifier(1.2f)
            .build();

    public static final FoodComponent PIZZA_SLICE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
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
}
