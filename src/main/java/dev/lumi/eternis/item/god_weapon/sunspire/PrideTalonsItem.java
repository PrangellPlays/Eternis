package dev.lumi.eternis.item.god_weapon.sunspire;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class PrideTalonsItem extends Item implements IRelicItem {
    public PrideTalonsItem(Settings settings) {
        super(settings);
    }

//    Pride Talons (Dual Daggers) Ascendant Relic
//    Passive:
//    Increased attack speed.

//    Evolution:
//    2 kills: gain Speed I after kill
//    5 kills: ability Hunter's Leap (long dash attack)

//    Obtained from the "The Dawn Reach"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.pride_talons.lore").formatted(Formatting.YELLOW));
    }
}
