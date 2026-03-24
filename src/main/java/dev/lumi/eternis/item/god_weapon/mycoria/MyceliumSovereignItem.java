package dev.lumi.eternis.item.god_weapon.mycoria;

import dev.lumi.eternis.item.util.IRelicItem;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

//    Mycelium Sovereign (Sceptre) Instinct Relic
//    Passive:
//    Poison immunity.

//    Ability — Spore Cloud:
//    Poison area attack.

//    Obtained from the "The Sporeheart"

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eternis.mycelium_sovereign.lore").formatted(Formatting.DARK_PURPLE));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(target.getWorld(), target.getX(), target.getY(), target.getZ());
        if (attacker instanceof LivingEntity livingEntity) {
            areaEffectCloudEntity.setOwner(livingEntity);
        }

        areaEffectCloudEntity.setRadius(3.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotionContents(PotionContentsComponent.DEFAULT.with(new StatusEffectInstance(StatusEffects.POISON, 500, 0)));
        target.getWorld().spawnEntity(areaEffectCloudEntity);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isClient && player.hasStatusEffect(StatusEffects.POISON)) {
            player.removeStatusEffect(StatusEffects.POISON);
        }
    }
}
