package dev.lumi.eternis.item.god_weapon.sunspire;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SunfangItem extends Item implements IRelicItem {
    public SunfangItem(Settings settings) {
        super(settings);
    }

//    Sunfang (Sword) Instinct Relic
//    Passive:
//    Fire Aspect in sunlight.

//    Ability — Solar Strike:
//    Call down a beam of sunlight.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.sunfang.lore").formatted(Formatting.YELLOW));
    }
}
