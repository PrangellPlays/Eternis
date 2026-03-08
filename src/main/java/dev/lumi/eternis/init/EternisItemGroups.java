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

    public static void init() {
    }
}
