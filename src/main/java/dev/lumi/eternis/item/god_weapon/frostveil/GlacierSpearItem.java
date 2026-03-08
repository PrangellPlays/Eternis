package dev.lumi.eternis.item.god_weapon.frostveil;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class GlacierSpearItem extends Item implements IRelicItem {
    public GlacierSpearItem(Settings settings) {
        super(settings);
    }

//    Glacier Spear (Spear) Dominion Relic
//    Passive:
//    Freezes water when thrown.

//    Ability — Ice Wall:
//    Creates a short wall of packed ice.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.glacier_spear.lore").formatted(Formatting.AQUA));
    }
}
