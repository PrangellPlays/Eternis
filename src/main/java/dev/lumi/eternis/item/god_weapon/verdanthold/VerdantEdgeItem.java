package dev.lumi.eternis.item.god_weapon.verdanthold;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class VerdantEdgeItem extends Item implements IRelicItem {
    public VerdantEdgeItem(Settings settings) {
        super(settings);
    }

//    Verdant Edge (Longsword) Instinct Relic
//    Passive:
//    Standing on grass gives Regeneration I

//    Ability — Bloom:
//    Nearby flowers heal allies.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.verdant_edge.lore").formatted(Formatting.GREEN));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.getSteppingBlockState().getBlock() == Blocks.GRASS_BLOCK) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5, 0, false, false));
        }
    }
}
