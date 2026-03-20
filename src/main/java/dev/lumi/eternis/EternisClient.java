package dev.lumi.eternis;

import dev.lumi.eternis.client.init.EternisScreenHandlers;
import dev.lumi.eternis.client.screen.EntityKillCountOverlay;
import dev.lumi.eternis.client.screen.KitModifyScreen;
import dev.lumi.eternis.client.screen.KitSelectorScreen;
import dev.lumi.eternis.init.EternisBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class EternisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityKillCountOverlay.register();
        HandledScreens.register(EternisScreenHandlers.KIT_SELECTOR, KitSelectorScreen::new);
        HandledScreens.register(EternisScreenHandlers.KIT_MODIFY, KitModifyScreen::new);
    }
}
