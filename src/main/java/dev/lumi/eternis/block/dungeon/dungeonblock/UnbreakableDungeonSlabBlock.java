package dev.lumi.eternis.block.dungeon.dungeonblock;

import net.minecraft.block.SlabBlock;

public class UnbreakableDungeonSlabBlock extends SlabBlock {
    public UnbreakableDungeonSlabBlock(Settings settings) {
        super(settings.strength(-1.0f, 3600000.0F).dropsNothing());
    }
}
