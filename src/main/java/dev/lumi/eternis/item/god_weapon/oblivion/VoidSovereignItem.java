package dev.lumi.eternis.item.god_weapon.oblivion;

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

public class VoidSovereignItem extends Item implements IRelicItem {
    public VoidSovereignItem(Settings settings) {
        super(settings);
    }

//    Void Sovereign (Scythe) Major Relic
//    Passive:
//    Slow Falling
//    Endermen ignore you

//    Ability — End Lock:
//    All End portals disable / enable

//    Second Ability — Void Rift:
//    Short teleport ability.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.void_sovereign.lore").formatted(Formatting.DARK_GRAY));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 5, 0, false, false));
    }
}
