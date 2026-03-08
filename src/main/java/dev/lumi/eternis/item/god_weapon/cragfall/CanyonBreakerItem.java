package dev.lumi.eternis.item.god_weapon.cragfall;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.entity.effect.StatusEffect;
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

public class CanyonBreakerItem extends Item implements IRelicItem {
    public CanyonBreakerItem(Settings settings) {
        super(settings);
    }

//    Canyon Breaker (War Pick) Instinct Relic
//    Passive:
//    Faster mining (Toggleable 3x3 mining).

//    Ability — Landslide:
//    Breaks nearby stone blocks outward.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.canopy_breaker.lore").formatted(Formatting.RED));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 5, 0, false, false));
    }
}
