package com.pizzalover125.pizzamod.item;

import com.pizzalover125.pizzamod.PizzaMod;
import com.pizzalover125.pizzamod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PIZZA_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PizzaMod.MOD_ID, "pizza_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PIZZA))
                            .displayName(Text.translatable("itemgroup.pizzamod.pizza_items"))
                            .entries((displayContext, entries) -> {
                                entries.add(ModItems.PIZZA);
                                entries.add(ModItems.PIZZA_SLICE);
                                entries.add(ModItems.SAUCE);
                                entries.add(ModItems.CHEESE_SLICE);
                                entries.add(ModItems.RAW_PIZZA_SLICE);
                                entries.add(ModItems.DOUGH);
                                entries.add(ModItems.TOMATO);

                            }).build());

    public static final ItemGroup PIZZA_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PizzaMod.MOD_ID, "pizza_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.CHEESE_BLOCK))
                    .displayName(Text.translatable("itemgroup.pizzamod.pizza_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CHEESE_BLOCK);
                        entries.add(ModBlocks.SAUCE_BLOCK);
                        entries.add(ModBlocks.DOUGH_BLOCK);

                    }).build());



    public static void registerItemGroups() {
        PizzaMod.LOGGER.info("Registering Item Groups for " + PizzaMod.MOD_ID);
    }
}
