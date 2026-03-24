package dev.lumi.eternis.block.dungeon.dungeonblock;

import net.minecraft.block.PillarBlock;

public class UnbreakableDungeonPillarBlock extends PillarBlock {
    public UnbreakableDungeonPillarBlock(Settings settings) {
        super(settings.strength(-1.0f, 3600000.0F).dropsNothing());
    }
}
