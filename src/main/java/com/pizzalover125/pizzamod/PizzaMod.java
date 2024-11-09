package com.pizzalover125.pizzamod;

import com.mojang.brigadier.CommandDispatcher;
import com.pizzalover125.pizzamod.block.ModBlocks;
import com.pizzalover125.pizzamod.item.ModItemGroups;
import com.pizzalover125.pizzamod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;

public class PizzaMod implements ModInitializer {
	public static final String MOD_ID = "pizzamod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		PizzaModCommands.registerCommands();
	}
}
