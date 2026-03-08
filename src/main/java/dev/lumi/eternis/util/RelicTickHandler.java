package dev.lumi.eternis.util;

import dev.lumi.eternis.item.util.IRelicItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class RelicTickHandler {
    public static void init() {
        ServerTickEvents.START_SERVER_TICK.register(RelicTickHandler::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (PlayerEntity player : world.getPlayers()) {
                //for (ItemStack stack : player.getInventory().main) {
                    if (player.getMainHandStack().getItem() instanceof IRelicItem relic) {
                        relic.onHeldTick(player.getMainHandStack(), world, player);
                    }
                //}
            }
        }
    }
}
