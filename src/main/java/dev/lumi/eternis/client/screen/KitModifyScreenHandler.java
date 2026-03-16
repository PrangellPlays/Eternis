package dev.lumi.eternis.client.screen;

import dev.lumi.eternis.block.entity.KitSelectorBlockEntity;
import dev.lumi.eternis.client.init.EternisScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KitModifyScreenHandler extends ScreenHandler {
    private final KitSelectorBlockEntity blockEntity;
    private final int kitIndex;
    private final Inventory inventory;

    public KitModifyScreenHandler(int syncId, PlayerInventory playerInventory, KitModifyData data) {
        super(EternisScreenHandlers.KIT_MODIFY, syncId);
        World world = playerInventory.player.getWorld();
        this.blockEntity = (KitSelectorBlockEntity) world.getBlockEntity(data.pos());
        this.kitIndex = data.kitIndex();

        DefaultedList<ItemStack> kit = blockEntity.getKit(kitIndex);

        this.inventory = new SimpleInventory(kit.size()) {
            @Override
            public void markDirty() {
                super.markDirty();

                for (int i = 0; i < size(); i++) {
                    kit.set(i, getStack(i));
                }

                blockEntity.markDirty();
            }
        };

        for (int i = 0; i < kit.size(); i++) {
            inventory.setStack(i, kit.get(i));
        }

        // Kit inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slot = col + row * 9;
                this.addSlot(new Slot(inventory, slot, 8 + col * 18, 18 + row * 18));
            }
        }

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public record KitModifyData(BlockPos pos, int kitIndex) {
        public static final PacketCodec<RegistryByteBuf, KitModifyData> PACKET_CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC.cast(), KitModifyData::pos, PacketCodecs.INTEGER, KitModifyData::kitIndex, KitModifyData::new);
    }
}
