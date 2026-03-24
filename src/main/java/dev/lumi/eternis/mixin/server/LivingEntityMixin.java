package dev.lumi.eternis.mixin.server;

import dev.lumi.eternis.init.EternisDataComponents;
import dev.lumi.eternis.item.util.EnemySlainWeaponItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class})
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = {"swingHand(Lnet/minecraft/util/Hand;Z)V"}, at = {@At("HEAD")})
    private void eternis$sendWeaponKills(Hand hand, boolean fromServerPlayer, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof PlayerEntity player && player.getMainHandStack().getItem() instanceof EnemySlainWeaponItem && player.isSneaking()) {
            int entityKillCount = player.getMainHandStack().get(EternisDataComponents.ENTITY_DEATH_COUNT);
            player.sendMessage(Text.literal("Weapon is on " + entityKillCount + " Kill(s)"), true);
        }
    }
}
