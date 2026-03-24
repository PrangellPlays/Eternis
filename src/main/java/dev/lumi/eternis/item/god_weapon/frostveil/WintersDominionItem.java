package dev.lumi.eternis.item.god_weapon.frostveil;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class WintersDominionItem extends Item implements IRelicItem {
    public WintersDominionItem(Settings settings) {
        super(settings);
    }

//    Winter's Dominion (Warhammer) Ascendant Relic (Kill-based)
//    Passive:
//    Hits add freeze ticks

//    Evolution:
//    2 kills: attacks create snow layers
//    5 kills: ability Frozen Prison traps enemies in ice

//    Obtained from the "The Crown of Winter"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.winters_dominion.lore").formatted(Formatting.AQUA));
    }
}
