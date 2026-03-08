package dev.lumi.eternis;

import dev.lumi.eternis.init.EternisDataComponents;
import dev.lumi.eternis.init.EternisItemGroups;
import dev.lumi.eternis.init.EternisItems;
import dev.lumi.eternis.util.RelicTickHandler;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eternis implements ModInitializer {
	public static final String MOD_ID = "eternis";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		EternisDataComponents.init();
		EternisItems.init();
		EternisItemGroups.init();

		RelicTickHandler.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}