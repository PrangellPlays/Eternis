package dev.lumi.eternis.item.god_weapon.dunebreak;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DuneScimitarItem extends Item implements IRelicItem {
    public DuneScimitarItem(Settings settings) {
        super(settings);
    }

//    Dune Scimitar (Scimitar) Instinct Relic
//    Passive:
//    Hits apply short Blindness

//    Ability — Sandstorm:
//    Creates blindness cloud.

//    Obtained from the "Buried Temple"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.dune_scimitar.lore").formatted(Formatting.GOLD));
    }
}
