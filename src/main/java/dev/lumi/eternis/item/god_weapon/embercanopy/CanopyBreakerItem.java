package dev.lumi.eternis.item.god_weapon.embercanopy;

import dev.lumi.eternis.item.util.IRelicItem;
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

public class CanopyBreakerItem extends Item implements IRelicItem {
    public CanopyBreakerItem(Settings settings) {
        super(settings);
    }

//    Canopy Breaker (War Spear) Dominion Relic
//    Passive:
//    Jump Boost in jungle.

//    Ability — Vine Snare:
//    Vines trap enemies.

//    Obtained from the "The Canopy Shrine"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.canopy_breaker.lore").formatted(Formatting.DARK_GREEN));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5, 0, false, false));
    }
}
