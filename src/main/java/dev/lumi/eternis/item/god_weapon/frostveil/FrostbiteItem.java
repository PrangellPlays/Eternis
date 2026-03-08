package dev.lumi.eternis.item.god_weapon.frostveil;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class FrostbiteItem extends Item implements IRelicItem {
    public FrostbiteItem(Settings settings) {
        super(settings);
    }

//    Frostbite (Greatsword) Instinct Relic
//    Passive:
//    Hits applySlowness I

//    Ability — Blizzard:
//    Creates a short snowstorm slowing enemies.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.frostbite.lore").formatted(Formatting.AQUA));
    }
}
