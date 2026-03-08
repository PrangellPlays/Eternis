package dev.lumi.eternis.item.god_weapon.sunspire;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class StormJavelinItem extends Item implements IRelicItem {
    public StormJavelinItem(Settings settings) {
        super(settings);
    }

//    Storm Javelin (Trident/Spear) Dominion Relic
//    Passive:
//    Returns when thrown.

//    Ability — Thunderstorm:
//    Forces thunder weather and strikes the hit area with lightning.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.storm_javelin.lore").formatted(Formatting.YELLOW));
    }
}
