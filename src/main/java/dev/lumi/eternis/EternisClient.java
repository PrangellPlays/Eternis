package dev.lumi.eternis;

import dev.lumi.eternis.client.init.EternisScreenHandlers;
import dev.lumi.eternis.client.screen.EntityKillCountOverlay;
import dev.lumi.eternis.client.screen.kit.KitModifyScreen;
import dev.lumi.eternis.client.screen.kit.KitSelectorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class EternisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityKillCountOverlay.register();
        HandledScreens.register(EternisScreenHandlers.KIT_SELECTOR, KitSelectorScreen::new);
        HandledScreens.register(EternisScreenHandlers.KIT_MODIFY, KitModifyScreen::new);
    }
}
