package dev.lumi.eternis.block;

import net.minecraft.block.Block;

public class UnbreakableDungeonBlock extends Block {
    public UnbreakableDungeonBlock(Settings settings) {
        super(settings.strength(-1.0f, 3600000.0F).dropsNothing());
    }
}
