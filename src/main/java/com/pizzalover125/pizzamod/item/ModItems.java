package com.pizzalover125.pizzamod.item;

import com.pizzalover125.pizzamod.PizzaMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item PIZZA = registerItem("pizza", new Item(new Item.Settings()));
    public static final Item PIZZA_SLICE = registerItem("pizza_slice", new Item(new Item.Settings()));
    public static final Item CHEESE_SLICE = registerItem("cheese_slice", new Item(new Item.Settings()));
    public static final Item DOUGH = registerItem("dough", new Item(new Item.Settings()));
    public static final Item SAUCE = registerItem("sauce", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PizzaMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PizzaMod.LOGGER.info("Registering Mod Items for " + PizzaMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PIZZA);
            entries.add(PIZZA_SLICE);
            entries.add(CHEESE_SLICE);
            entries.add(DOUGH);
            entries.add(SAUCE);
        });
    }
}
