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

public class InfernalCanopyItem extends Item implements IRelicItem {
    public InfernalCanopyItem(Settings settings) {
        super(settings);
    }

//    Infernal Canopy (Quick Charging Crossbow) Dominion Relic
//    Passive:
//    Gain Fire Resistance
//    Gain Speed I
//    Attacks apply Flame
//    When fighting in jungle biomes, fire lasts longer

//    Ability — Eruption Bloom:
//    Magma blocks erupt from the ground in a small radius
//    Vines grow upward around enemies

//    Enemies caught inside are:
//    Slowed
//    Set on fire

//    The magma blocks revert back after a few seconds so terrain isn’t permanently destroyed.

//    Obtained from the "The Ashroot Hollow"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.infernal_canopy.lore").formatted(Formatting.DARK_GREEN));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5, 0, false, false));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0, false, false));
    }
}
