package dev.lumi.eternis.datagen;

import dev.lumi.eternis.init.EternisBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.TexturedModel;

public class EternisModelProvider extends FabricModelProvider {
    public EternisModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool dungeonBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(EternisBlocks.DUNGEON_BRICKS);
        BlockStateModelGenerator.BlockTexturePool mossyDungeonBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(EternisBlocks.MOSSY_DUNGEON_BRICKS);
        BlockStateModelGenerator.BlockTexturePool polishedDungeonBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(EternisBlocks.POLISHED_DUNGEON_BRICKS);
        BlockStateModelGenerator.BlockTexturePool dungeonTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(EternisBlocks.DUNGEON_TILES);
        BlockStateModelGenerator.BlockTexturePool mossyDungeonTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(EternisBlocks.MOSSY_DUNGEON_TILES);

        dungeonBricksPool.stairs(EternisBlocks.DUNGEON_BRICK_STAIRS);
        dungeonBricksPool.slab(EternisBlocks.DUNGEON_BRICK_SLAB);
        dungeonBricksPool.wall(EternisBlocks.DUNGEON_BRICK_WALL);

        blockStateModelGenerator.registerSimpleCubeAll(EternisBlocks.CRACKED_DUNGEON_BRICKS);

        mossyDungeonBricksPool.stairs(EternisBlocks.MOSSY_DUNGEON_BRICK_STAIRS);
        mossyDungeonBricksPool.slab(EternisBlocks.MOSSY_DUNGEON_BRICK_SLAB);
        mossyDungeonBricksPool.wall(EternisBlocks.MOSSY_DUNGEON_BRICK_WALL);

        blockStateModelGenerator.registerSimpleCubeAll(EternisBlocks.CHISELED_DUNGEON_BRICKS);

        polishedDungeonBricksPool.stairs(EternisBlocks.POLISHED_DUNGEON_BRICK_STAIRS);
        polishedDungeonBricksPool.slab(EternisBlocks.POLISHED_DUNGEON_BRICK_SLAB);

        dungeonTilesPool.stairs(EternisBlocks.DUNGEON_TILE_STAIRS);
        dungeonTilesPool.slab(EternisBlocks.DUNGEON_TILE_SLAB);
        dungeonTilesPool.wall(EternisBlocks.DUNGEON_TILE_WALL);

        blockStateModelGenerator.registerSimpleCubeAll(EternisBlocks.CRACKED_DUNGEON_TILES);

        mossyDungeonTilesPool.stairs(EternisBlocks.MOSSY_DUNGEON_TILE_STAIRS);
        mossyDungeonTilesPool.slab(EternisBlocks.MOSSY_DUNGEON_TILE_SLAB);
        mossyDungeonTilesPool.wall(EternisBlocks.MOSSY_DUNGEON_TILE_WALL);

        blockStateModelGenerator.registerAxisRotated(EternisBlocks.DUNGEON_PILLAR, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
