package dev.lumi.eternis;

import dev.lumi.eternis.client.screen.EntityKillCountOverlay;
import net.fabricmc.api.ClientModInitializer;

public class EternisClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityKillCountOverlay.register();
    }
}
