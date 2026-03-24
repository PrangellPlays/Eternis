package dev.lumi.eternis.util;

import dev.lumi.eternis.Eternis;
import net.minecraft.server.network.ServerPlayerEntity;

public class DungeonRules {
    public static boolean isRestrictedDungeon(ServerPlayerEntity player) {
        return player.getWorld().getRegistryKey() == Eternis.DUNGEON_DIM && !player.isCreative();
    }
}
