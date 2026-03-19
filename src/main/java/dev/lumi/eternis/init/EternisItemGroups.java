package dev.lumi.eternis.init;

import dev.lumi.eternis.Eternis;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public interface EternisItemGroups {
    ItemGroup ETERNIS_GODWEAPONS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Eternis.MOD_ID, "eternis_godweapons_group"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.eternis.eternis_godweapons_group")).icon(() -> new ItemStack(EternisItems.WINTERS_DOMINION)).entries((displayContext, entries) -> {
        //The Frostveil
        entries.add(EternisItems.FROSTBITE);
        entries.add(EternisItems.GLACIER_SPEAR);
        entries.add(EternisItems.WINTERS_DOMINION);

        //The Shadowgrove
        entries.add(EternisItems.HEARTWOOD_REAVER);

        //The Verdanthold
        entries.add(EternisItems.VERDANT_EDGE);
        entries.add(EternisItems.WINDPIERCER);
        entries.add(EternisItems.SYLVAN_OATH);

        //The Sunspire
        entries.add(EternisItems.SUNFANG);
        entries.add(EternisItems.PRIDE_TALONS);
        entries.add(EternisItems.STORM_JAVELIN);

        //The Dunebreak
        entries.add(EternisItems.DUNE_SCIMITAR);
        entries.add(EternisItems.PHARAOHS_JUDGEMENT);

        //The Cragfall
        entries.add(EternisItems.CANYON_BREAKER);

        //The Mycoria
        entries.add(EternisItems.MYCELIUM_SOVEREIGN);

        //The Ember Canopy
        entries.add(EternisItems.VENOM_REAVER);
        entries.add(EternisItems.CANOPY_BREAKER);
        entries.add(EternisItems.INFERNAL_CANOPY);

        //The Abyssforge
        entries.add(EternisItems.HELLGATE_TYRANT);

        //The Oblivion
        entries.add(EternisItems.VOID_SOVEREIGN);
    }).build());

    ItemGroup ETERNIS_DUNGEON_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Eternis.MOD_ID, "eternis_dungeon_group"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.eternis.eternis_dungeon_group")).icon(() -> new ItemStack(EternisBlocks.KILL_ZONE)).entries((displayContext, entries) -> {
        //Locker
        entries.add(EternisBlocks.LOCKER_SAVE);
        entries.add(EternisBlocks.LOCKER_RETRIEVE);

        //Kits
        entries.add(EternisBlocks.KIT_SELECTOR);

        //Plates
        entries.add(EternisBlocks.KILL_ZONE);
        entries.add(EternisBlocks.CRYO_PLATE);
        entries.add(EternisBlocks.IMPULSE_PLATE);
        entries.add(EternisBlocks.NULLSTEP_PLATE);
        entries.add(EternisBlocks.REBOUND_PLATE);
        entries.add(EternisBlocks.REDIRECT_PLATE);
        entries.add(EternisBlocks.VAULT_PLATE);

        //Dungeon Blocks
        entries.add(EternisBlocks.DUNGEON_BRICKS);
        entries.add(EternisBlocks.DUNGEON_BRICK_STAIRS);
        entries.add(EternisBlocks.DUNGEON_BRICK_SLAB);
        entries.add(EternisBlocks.DUNGEON_BRICK_WALL);

        entries.add(EternisBlocks.CRACKED_DUNGEON_BRICKS);

        entries.add(EternisBlocks.MOSSY_DUNGEON_BRICKS);
        entries.add(EternisBlocks.MOSSY_DUNGEON_BRICK_STAIRS);
        entries.add(EternisBlocks.MOSSY_DUNGEON_BRICK_SLAB);
        entries.add(EternisBlocks.MOSSY_DUNGEON_BRICK_WALL);

        entries.add(EternisBlocks.CHISELED_DUNGEON_BRICKS);

        entries.add(EternisBlocks.POLISHED_DUNGEON_BRICKS);
        entries.add(EternisBlocks.POLISHED_DUNGEON_BRICK_STAIRS);
        entries.add(EternisBlocks.POLISHED_DUNGEON_BRICK_SLAB);

        entries.add(EternisBlocks.DUNGEON_TILES);
        entries.add(EternisBlocks.DUNGEON_TILE_STAIRS);
        entries.add(EternisBlocks.DUNGEON_TILE_SLAB);
        entries.add(EternisBlocks.DUNGEON_TILE_WALL);

        entries.add(EternisBlocks.CRACKED_DUNGEON_TILES);

        entries.add(EternisBlocks.MOSSY_DUNGEON_TILES);
        entries.add(EternisBlocks.MOSSY_DUNGEON_TILE_STAIRS);
        entries.add(EternisBlocks.MOSSY_DUNGEON_TILE_SLAB);
        entries.add(EternisBlocks.MOSSY_DUNGEON_TILE_WALL);

        entries.add(EternisBlocks.DUNGEON_PILLAR);
    }).build());

    public static void init() {
    }
}
