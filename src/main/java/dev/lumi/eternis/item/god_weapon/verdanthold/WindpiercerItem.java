package dev.lumi.eternis.item.god_weapon.verdanthold;

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

public class WindpiercerItem extends Item implements IRelicItem {
    public WindpiercerItem(Settings settings) {
        super(settings);
    }

//    Windpiercer (Spear) Instinct Relic
//    Passive:
//    Speed I in open terrain.

//    Ability — Gale:
//    Pushes enemies away.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.windpiercer.lore").formatted(Formatting.GREEN));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0, false, false));
    }
}
