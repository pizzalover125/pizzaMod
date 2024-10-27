package com.pizzalover125.pizzamod.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent PIZZA = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(1.2f)
            .build();

    public static final FoodComponent PIZZA_SLICE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.4f)
            .build();

    public static final FoodComponent CHEESE_SLICE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.4f)
            .build();


}
