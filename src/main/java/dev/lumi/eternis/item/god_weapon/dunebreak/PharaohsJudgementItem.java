package dev.lumi.eternis.item.god_weapon.dunebreak;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class PharaohsJudgementItem extends Item implements IRelicItem {
    public PharaohsJudgementItem(Settings settings) {
        super(settings);
    }

//    Pharaoh's Judgement (Staff) Dominion Relic
//    Passive:
//    Husks ignore you.

//    Ability — Tomb Guard:
//    Summons husks.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.pharaohs_judgement.lore").formatted(Formatting.GOLD));
    }
}
