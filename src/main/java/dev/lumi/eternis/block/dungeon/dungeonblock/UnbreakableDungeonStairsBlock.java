package dev.lumi.eternis.block.dungeon.dungeonblock;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class UnbreakableDungeonStairsBlock extends StairsBlock {
    public UnbreakableDungeonStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings.strength(-1.0f, 3600000.0F).dropsNothing());
    }
}
