package com.pizzalover125.pizzamod.util;

import com.pizzalover125.pizzamod.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final RegistryKey<LootTable> VILLAGE_CHEST_ID = RegistryKey.of(RegistryKeys.LOOT_TABLE,
            Identifier.of("minecraft", "chests/village/village_plains_house"));

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (lootTableSource.isBuiltin() && VILLAGE_CHEST_ID.equals(registryKey)) {
                LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(ModItems.DOUGH).weight(1));
                builder.pool(poolBuilder);
            }
        });
    }
}