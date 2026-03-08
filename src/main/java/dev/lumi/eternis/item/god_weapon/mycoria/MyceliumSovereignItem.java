package dev.lumi.eternis.item.god_weapon.mycoria;

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

public class MyceliumSovereignItem extends Item implements IRelicItem {
    public MyceliumSovereignItem(Settings settings) {
        super(settings);
    }

//    Mycelium Sovereign (Scepter) Instinct Relic
//    Passive:
//    Poison immunity.

//    Ability — Spore Cloud:
//    Poison area attack.

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.mycelium_sovereign.lore").formatted(Formatting.DARK_PURPLE));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isClient && player.hasStatusEffect(StatusEffects.POISON)) {
            player.removeStatusEffect(StatusEffects.POISON);
        }
    }
}
