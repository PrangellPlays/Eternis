package dev.lumi.eternis.item.god_weapon.abyssforge;

import dev.lumi.eternis.item.util.EternisToolMaterials;
import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class HellgateTyrantItem extends SwordItem implements IRelicItem {
    public HellgateTyrantItem(Settings settings) {
        super(EternisToolMaterials.RELIC, settings);
    }

//    Hellgate Tyrant (Greatsword) Major Relic
//    Passive:
//    Fire resistance
//    Wither on hit

//    Ability — Hellgate Lock:
//    All Nether portals disable / enable

//    Second Ability — Infernal Summon:
//    Spawns blazes and wither skeletons.


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.hellgate_tyrant.lore").formatted(Formatting.DARK_RED));
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5, 0, false, false));
    }
}
