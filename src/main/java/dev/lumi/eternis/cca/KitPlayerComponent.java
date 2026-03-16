package dev.lumi.eternis.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.Component;

public class KitPlayerComponent implements Component {
    private final PlayerEntity entity;
    private boolean claimed;

    public KitPlayerComponent(PlayerEntity entity) {
        this.entity = entity;
    }

    public boolean hasClaimed() {
        return claimed;
    }

    public void setClaimed(boolean value) {
        claimed = value;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        claimed = nbtCompound.getBoolean("claimed");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("claimed", claimed);
    }
}
