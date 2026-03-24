package dev.lumi.eternis.block.dungeon.dungeonblock;

import net.minecraft.block.WallBlock;

public class UnbreakableDungeonWallBlock extends WallBlock {
    public UnbreakableDungeonWallBlock(Settings settings) {
        super(settings.strength(-1.0f, 3600000.0F).dropsNothing());
    }
}
