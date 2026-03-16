package dev.lumi.eternis.block.entity;

import dev.lumi.eternis.init.EternisBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class KitSelectorBlockEntity extends BlockEntity {
    public static final int KIT_COUNT = 3;
    public static final int KIT_SIZE = 41;

    private final DefaultedList<ItemStack>[] kits = new DefaultedList[]{
            DefaultedList.ofSize(KIT_SIZE, ItemStack.EMPTY),
            DefaultedList.ofSize(KIT_SIZE, ItemStack.EMPTY),
            DefaultedList.ofSize(KIT_SIZE, ItemStack.EMPTY)
    };

    private final String[] kitNames = {
            "Kit 1",
            "Kit 2",
            "Kit 3"
    };

    public KitSelectorBlockEntity(BlockPos pos, BlockState state) {
        super(EternisBlockEntities.KIT_SELECTOR, pos, state);
    }

    public DefaultedList<ItemStack> getKit(int index) {
        return kits[index];
    }

    public String getKitName(int index) {
        return kitNames[index];
    }

    public void setKitName(int index, String name) {
        kitNames[index] = name;
        markDirty();
    }

    public void giveKit(PlayerEntity player, int kit) {

        for (ItemStack stack : kits[kit]) {
            if (!stack.isEmpty()) {
                player.giveItemStack(stack.copy());
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        super.writeNbt(nbt, lookup);

        for (int i = 0; i < KIT_COUNT; i++) {
            NbtList list = new NbtList();

            for (int slot = 0; slot < kits[i].size(); slot++) {
                ItemStack stack = kits[i].get(slot);

                if (!stack.isEmpty()) {
                    NbtCompound entry = new NbtCompound();
                    entry.putByte("Slot", (byte) slot);
                    entry.put("Item", stack.encode(lookup));
                    list.add(entry);
                }
            }

            nbt.put("Kit" + i, list);
            nbt.putString("Name" + i, kitNames[i]);
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        super.readNbt(nbt, lookup);
        for (int i = 0; i < KIT_COUNT; i++) {
            kits[i].clear();
            kits[i].addAll(DefaultedList.ofSize(KIT_SIZE, ItemStack.EMPTY));
            NbtList list = nbt.getList("Kit" + i, NbtElement.COMPOUND_TYPE);

            for (int j = 0; j < list.size(); j++) {
                NbtCompound entry = list.getCompound(j);
                int slot = entry.getByte("Slot") & 255;
                ItemStack stack = ItemStack.fromNbt(lookup, entry.getCompound("Item")).orElse(ItemStack.EMPTY);
                kits[i].set(slot, stack);
            }
            kitNames[i] = nbt.getString("Name" + i);
        }
    }
}
