package dev.lumi.eternis.item.god_weapon.embercanopy;

import dev.lumi.eternis.item.util.EternisToolMaterials;
import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class VenomReaverItem extends SwordItem implements IRelicItem {
    public VenomReaverItem(Settings settings) {
        super(EternisToolMaterials.RELIC, settings);
    }

//    Venom Reaver (Greatsword) Ascendant Relic
//    Passive:
//    Poison on hit.

//    Evolution:
//    2 kills: poison duration increases
//    5 kills: Serpent Call spawns bogged

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.venom_reaver.lore").formatted(Formatting.DARK_GREEN));
    }
}
