package dev.lumi.eternis.item.util;

import dev.lumi.eternis.init.EternisDataComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnemySlainWeaponItem extends Item {
    public EnemySlainWeaponItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int entityKillCount = stack.get(EternisDataComponents.ENTITY_DEATH_COUNT);
        if (!target.isAlive() && target instanceof PlayerEntity) {
            entityKillCount += 1;
        }
        return super.postHit(stack, target, attacker);
    }
}
