package dev.lumi.eternis.item.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Interface for weapons that have passive effects or on-hit abilities.
 */
public interface IRelicItem {
    /**
     * Called every tick while the player is holding this item.
     *
     * @param stack The item stack being held
     * @param world The world
     * @param player The player holding the item
     */
    default void onHeldTick(ItemStack stack, World world, PlayerEntity player) {
        // Default: do nothing, override for passive effects
    }

    /**
     * Called when the player hits a target with this item.
     *
     * @param stack The item stack
     * @param target The entity hit
     * @param attacker The player attacking
     */
    default void onPostHit(ItemStack stack, LivingEntity target, PlayerEntity attacker) {
        // Default: do nothing, override for on-hit effects
    }
}
