package com.pizzalover125.pizzamod.block;

import com.pizzalover125.pizzamod.PizzaMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CHEESE_BLOCK = registerBlock("cheese_block",
            new Block(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.SAND)));

    public static final Block DOUGH_BLOCK = registerBlock("dough_block",
            new Block(AbstractBlock.Settings.create().strength(8f).sounds(BlockSoundGroup.WOOD)));

    public static final Block SAUCE_BLOCK = registerBlock("sauce_block",
            new Block(AbstractBlock.Settings.create().strength(2f).sounds(BlockSoundGroup.ROOTED_DIRT)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PizzaMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PizzaMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PizzaMod.LOGGER.info("Registering Mod Blocks for "+ PizzaMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.CHEESE_BLOCK);
            entries.add(ModBlocks.DOUGH_BLOCK);
        });
    }
}
