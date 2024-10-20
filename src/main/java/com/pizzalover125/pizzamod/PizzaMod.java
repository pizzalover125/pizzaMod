package com.pizzalover125.pizzamod;

import com.pizzalover125.pizzamod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PizzaMod implements ModInitializer {
	public static final String MOD_ID = "pizzamod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
