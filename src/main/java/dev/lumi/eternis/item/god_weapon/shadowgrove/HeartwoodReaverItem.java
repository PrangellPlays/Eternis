package dev.lumi.eternis.item.god_weapon.shadowgrove;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class HeartwoodReaverItem extends Item implements IRelicItem {
    public HeartwoodReaverItem(Settings settings) {
        super(settings);
    }

//    Heartwood Reaver (Great Axe) Dominion Relic
//    Passive:
//    Hits apply Weakness

//    Ability — Forest Grasp:
//    Vines grow around enemies slowing them.

//    Obtained from the "The Moonshade Keep"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.heartwood_reaver.lore").formatted(Formatting.DARK_GREEN));
    }
}
