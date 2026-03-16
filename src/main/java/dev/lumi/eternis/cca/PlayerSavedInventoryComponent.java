package dev.lumi.eternis.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.Component;

import javax.swing.text.html.parser.Entity;

public class PlayerSavedInventoryComponent implements Component {
    private final PlayerEntity entity;
    private SimpleInventory savedInventory;

    public PlayerSavedInventoryComponent(PlayerEntity entity) {
        this.entity = entity;
        this.savedInventory = new SimpleInventory(200);
    }

    public boolean hasSavedInventory() {
        return !this.savedInventory.isEmpty();
    }

    public void saveStack(ItemStack itemStack) {
        this.savedInventory.addStack(itemStack.copy());
    }

    public SimpleInventory getSavedInventory() {
        return this.savedInventory;
    }

    public void resetSavedInventory() {
        this.savedInventory.clear();
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        NbtList nbtList = nbtCompound.getList("SavedInventory", 10);
        this.savedInventory.readNbtList(nbtList, wrapperLookup);
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.put("SavedInventory", this.savedInventory.toNbtList(wrapperLookup));
    }
}
