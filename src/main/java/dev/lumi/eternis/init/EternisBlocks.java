package dev.lumi.eternis.init;

import dev.lumi.eternis.Eternis;
import dev.lumi.eternis.block.dungeon.*;
import dev.lumi.eternis.block.dungeon.dungeonblock.*;
import dev.lumi.eternis.block.dungeon.parkour.*;
import dev.lumi.eternis.block.dungeon.waygate.WaygateBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class EternisBlocks {
    protected static final Map<Block, Identifier> BLOCKS = new LinkedHashMap();
    public static final Block LOCKER_SAVE;
    public static final Block LOCKER_RETRIEVE;

    public static final Block KIT_SELECTOR;

    public static final Block KILL_ZONE;
    public static final Block CRYO_PLATE;
    public static final Block IMPULSE_PLATE;
    public static final Block NULLSTEP_PLATE;
    public static final Block REBOUND_PLATE;
    public static final Block REDIRECT_PLATE;
    public static final Block VAULT_PLATE;

    public static final Block DUNGEON_BRICKS;
    public static final Block DUNGEON_BRICK_STAIRS;
    public static final Block DUNGEON_BRICK_SLAB;
    public static final Block DUNGEON_BRICK_WALL;

    public static final Block CRACKED_DUNGEON_BRICKS;

    public static final Block MOSSY_DUNGEON_BRICKS;
    public static final Block MOSSY_DUNGEON_BRICK_STAIRS;
    public static final Block MOSSY_DUNGEON_BRICK_SLAB;
    public static final Block MOSSY_DUNGEON_BRICK_WALL;

    public static final Block CHISELED_DUNGEON_BRICKS;

    public static final Block POLISHED_DUNGEON_BRICKS;
    public static final Block POLISHED_DUNGEON_BRICK_STAIRS;
    public static final Block POLISHED_DUNGEON_BRICK_SLAB;

    public static final Block DUNGEON_TILES;
    public static final Block DUNGEON_TILE_STAIRS;
    public static final Block DUNGEON_TILE_SLAB;
    public static final Block DUNGEON_TILE_WALL;

    public static final Block CRACKED_DUNGEON_TILES;

    public static final Block MOSSY_DUNGEON_TILES;
    public static final Block MOSSY_DUNGEON_TILE_STAIRS;
    public static final Block MOSSY_DUNGEON_TILE_SLAB;
    public static final Block MOSSY_DUNGEON_TILE_WALL;

    public static final Block DUNGEON_PILLAR;

    public static final Block WAYGATE;

    public static void init() {
        BLOCKS.forEach((block, id) -> {
            Registry.register(Registries.BLOCK, id, block);
        });
    }

    protected static <T extends Block> T register(String name, T block) {
        BLOCKS.put(block, Eternis.id(name));
        return block;
    }

    protected static <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new Item.Settings());
    }

    protected static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        return registerWithItem(name, block, (b) -> {
            return new BlockItem(b, settings);
        });
    }

    protected static <T extends Block> T registerWithItem(String name, T block, Function<T, BlockItem> itemGenerator) {
        EternisItems.register(name, (BlockItem)itemGenerator.apply(block));
        return register(name, block);
    }

    public EternisBlocks() {
    }

    static {
        LOCKER_SAVE = registerWithItem("locker_save", new LockerBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(0.3F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never), LockerBlock.LockerType.SAVE));
        LOCKER_RETRIEVE = registerWithItem("locker_retrieve", new LockerBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(0.3F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never), LockerBlock.LockerType.RETRIEVE));

        KIT_SELECTOR = registerWithItem("kit_selector", new KitSelectorBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(0.3F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));

        KILL_ZONE = registerWithItem("kill_zone", new KillZoneBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        CRYO_PLATE = registerWithItem("cryo_plate", new CryoPlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        IMPULSE_PLATE = registerWithItem("impulse_plate", new ImpulsePlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        NULLSTEP_PLATE = registerWithItem("nullstep_plate", new NullStepPlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        REBOUND_PLATE = registerWithItem("rebound_plate", new ReboundPlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        REDIRECT_PLATE = registerWithItem("redirect_plate", new RedirectPlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));
        VAULT_PLATE = registerWithItem("vault_plate", new VaultPlateBlock(FabricBlockSettings.create().instrument(NoteBlockInstrument.HAT).strength(-1.0F, 3600000.0F).sounds(BlockSoundGroup.LODESTONE).nonOpaque().allowsSpawning(Blocks::never)));

        DUNGEON_BRICKS = registerWithItem("dungeon_bricks", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).luminance((state) -> 0).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_BRICK_STAIRS = registerWithItem("dungeon_brick_stairs", new UnbreakableDungeonStairsBlock(DUNGEON_BRICKS.getDefaultState(), AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_BRICK_SLAB = registerWithItem("dungeon_brick_slab", new UnbreakableDungeonSlabBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_BRICK_WALL = registerWithItem("dungeon_brick_wall", new UnbreakableDungeonWallBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        CRACKED_DUNGEON_BRICKS = registerWithItem("cracked_dungeon_bricks", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        MOSSY_DUNGEON_BRICKS = registerWithItem("mossy_dungeon_bricks", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_BRICK_STAIRS = registerWithItem("mossy_dungeon_brick_stairs", new UnbreakableDungeonStairsBlock(MOSSY_DUNGEON_BRICKS.getDefaultState(), AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_BRICK_SLAB = registerWithItem("mossy_dungeon_brick_slab", new UnbreakableDungeonSlabBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_BRICK_WALL = registerWithItem("mossy_dungeon_brick_wall", new UnbreakableDungeonWallBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        CHISELED_DUNGEON_BRICKS = registerWithItem("chiseled_dungeon_bricks", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        POLISHED_DUNGEON_BRICKS = registerWithItem("polished_dungeon_bricks", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        POLISHED_DUNGEON_BRICK_STAIRS = registerWithItem("polished_dungeon_brick_stairs", new UnbreakableDungeonStairsBlock(POLISHED_DUNGEON_BRICKS.getDefaultState(), AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        POLISHED_DUNGEON_BRICK_SLAB = registerWithItem("polished_dungeon_brick_slab", new UnbreakableDungeonSlabBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        DUNGEON_TILES = registerWithItem("dungeon_tiles", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_TILE_STAIRS = registerWithItem("dungeon_tile_stairs", new UnbreakableDungeonStairsBlock(DUNGEON_TILES.getDefaultState(), AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_TILE_SLAB = registerWithItem("dungeon_tile_slab", new UnbreakableDungeonSlabBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        DUNGEON_TILE_WALL = registerWithItem("dungeon_tile_wall", new UnbreakableDungeonWallBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        CRACKED_DUNGEON_TILES = registerWithItem("cracked_dungeon_tiles", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        MOSSY_DUNGEON_TILES = registerWithItem("mossy_dungeon_tiles", new UnbreakableDungeonBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_TILE_STAIRS = registerWithItem("mossy_dungeon_tile_stairs", new UnbreakableDungeonStairsBlock(MOSSY_DUNGEON_TILES.getDefaultState(), AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_TILE_SLAB = registerWithItem("mossy_dungeon_tile_slab", new UnbreakableDungeonSlabBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));
        MOSSY_DUNGEON_TILE_WALL = registerWithItem("mossy_dungeon_tile_wall", new UnbreakableDungeonWallBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        DUNGEON_PILLAR = registerWithItem("dungeon_pillar", new UnbreakableDungeonPillarBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).pistonBehavior(PistonBehavior.NORMAL)));

        WAYGATE = registerWithItem("waygate", new WaygateBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(-1.0f, 3600000.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).nonOpaque().pistonBehavior(PistonBehavior.IGNORE)));
    }
}
