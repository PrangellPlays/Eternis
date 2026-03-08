package dev.lumi.eternis.item.god_weapon.verdanthold;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SylvanOathItem extends Item implements IRelicItem {
    public SylvanOathItem(Settings settings) {
        super(settings);
    }

//    Sylvan Oath (Bow) Dominion Relic
//    Passive:
//    Arrows grow into thorn traps.

//    Ability — Woodland Call:
//    Summons wolves.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.sylvan_oath.lore").formatted(Formatting.GREEN));
    }
}
