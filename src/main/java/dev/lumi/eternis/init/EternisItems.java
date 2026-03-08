package dev.lumi.eternis.init;

import dev.lumi.eternis.Eternis;
import dev.lumi.eternis.item.god_weapon.abyssforge.HellgateTyrantItem;
import dev.lumi.eternis.item.god_weapon.cragfall.CanyonBreakerItem;
import dev.lumi.eternis.item.god_weapon.dunebreak.DuneScimitarItem;
import dev.lumi.eternis.item.god_weapon.dunebreak.PharaohsJudgementItem;
import dev.lumi.eternis.item.god_weapon.embercanopy.CanopyBreakerItem;
import dev.lumi.eternis.item.god_weapon.embercanopy.InfernalCanopyItem;
import dev.lumi.eternis.item.god_weapon.embercanopy.VenomReaverItem;
import dev.lumi.eternis.item.god_weapon.frostveil.FrostbiteItem;
import dev.lumi.eternis.item.god_weapon.frostveil.GlacierSpearItem;
import dev.lumi.eternis.item.god_weapon.frostveil.WintersDominionItem;
import dev.lumi.eternis.item.god_weapon.mycoria.MyceliumSovereignItem;
import dev.lumi.eternis.item.god_weapon.oblivion.VoidSovereignItem;
import dev.lumi.eternis.item.god_weapon.shadowgrove.HeartwoodReaverItem;
import dev.lumi.eternis.item.god_weapon.sunspire.PrideTalonsItem;
import dev.lumi.eternis.item.god_weapon.sunspire.StormJavelinItem;
import dev.lumi.eternis.item.god_weapon.sunspire.SunfangItem;
import dev.lumi.eternis.item.god_weapon.verdanthold.SylvanOathItem;
import dev.lumi.eternis.item.god_weapon.verdanthold.VerdantEdgeItem;
import dev.lumi.eternis.item.god_weapon.verdanthold.WindpiercerItem;
import dev.lumi.eternis.item.util.EnemySlainWeaponItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;

public class EternisItems {
    protected static final Map<Item, Identifier> ITEMS = new LinkedHashMap();
    public static final Item ENEMY_SLAIN_WEAPON;

    //The Frostveil
    public static final Item FROSTBITE;
    public static final Item GLACIER_SPEAR;
    public static final Item WINTERS_DOMINION;

    //The Shadowgrove
    public static final Item HEARTWOOD_REAVER;

    //The Verdanthold
    public static final Item VERDANT_EDGE;
    public static final Item WINDPIERCER;
    public static final Item SYLVAN_OATH;

    //The Sunspire
    public static final Item SUNFANG;
    public static final Item PRIDE_TALONS;
    public static final Item STORM_JAVELIN;

    //The Dunebreak
    public static final Item DUNE_SCIMITAR;
    public static final Item PHARAOHS_JUDGEMENT;

    //The Cragfall
    public static final Item CANYON_BREAKER;

    //The Mycoria
    public static final Item MYCELIUM_SOVEREIGN;

    //The Ember Canopy
    public static final Item VENOM_REAVER;
    public static final Item CANOPY_BREAKER;
    public static final Item INFERNAL_CANOPY;

    //The Abyssforge
    public static final Item HELLGATE_TYRANT;

    //The Oblivion
    public static final Item VOID_SOVEREIGN;

    public static void init() {
        ITEMS.forEach((item, id) -> {
            Registry.register(Registries.ITEM, id, item);
        });
    }

    protected static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, Eternis.id(name));
        return item;
    }

    public EternisItems() {
    }

    static {
        ENEMY_SLAIN_WEAPON = register((String) "enemy_slain_weapon", (Item) (new EnemySlainWeaponItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Frostveil
        FROSTBITE = register((String) "frostbite", (Item) (new FrostbiteItem(new Item.Settings().rarity(Rarity.COMMON))));
        GLACIER_SPEAR = register((String) "glacier_spear", (Item) (new GlacierSpearItem(new Item.Settings().rarity(Rarity.COMMON))));
        WINTERS_DOMINION = register((String) "winters_dominion", (Item) (new WintersDominionItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Shadowgrove
        HEARTWOOD_REAVER = register((String) "heartwood_reaver", (Item) (new HeartwoodReaverItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Verdanthold
        VERDANT_EDGE = register((String) "verdant_edge", (Item) (new VerdantEdgeItem(new Item.Settings().rarity(Rarity.COMMON))));
        WINDPIERCER = register((String) "windpiercer", (Item) (new WindpiercerItem(new Item.Settings().rarity(Rarity.COMMON))));
        SYLVAN_OATH = register((String) "sylvan_oath", (Item) (new SylvanOathItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Sunspire
        SUNFANG = register((String) "sunfang", (Item) (new SunfangItem(new Item.Settings().rarity(Rarity.COMMON))));
        PRIDE_TALONS = register((String) "pride_talons", (Item) (new PrideTalonsItem(new Item.Settings().rarity(Rarity.COMMON))));
        STORM_JAVELIN = register((String) "storm_javelin", (Item) (new StormJavelinItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Dunebreak
        DUNE_SCIMITAR = register((String) "dune_scimitar", (Item) (new DuneScimitarItem(new Item.Settings().rarity(Rarity.COMMON))));
        PHARAOHS_JUDGEMENT = register((String) "pharaohs_judgement", (Item) (new PharaohsJudgementItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Cragfall
        CANYON_BREAKER = register((String) "canyon_breaker", (Item) (new CanyonBreakerItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Mycoria
        MYCELIUM_SOVEREIGN = register((String) "mycelium_sovereign", (Item) (new MyceliumSovereignItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Ember Canopy
        VENOM_REAVER = register((String) "venom_reaver", (Item) (new VenomReaverItem(new Item.Settings().rarity(Rarity.COMMON))));
        CANOPY_BREAKER = register((String) "canopy_breaker", (Item) (new CanopyBreakerItem(new Item.Settings().rarity(Rarity.COMMON))));
        INFERNAL_CANOPY = register((String) "infernal_canopy", (Item) (new InfernalCanopyItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Abyssforge
        HELLGATE_TYRANT = register((String) "hellgate_tyrant", (Item) (new HellgateTyrantItem(new Item.Settings().rarity(Rarity.COMMON))));

        //The Oblivion
        VOID_SOVEREIGN = register((String) "void_sovereign", (Item) (new VoidSovereignItem(new Item.Settings().rarity(Rarity.COMMON))));
    }
}
