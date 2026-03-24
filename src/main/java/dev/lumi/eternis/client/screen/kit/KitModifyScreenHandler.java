package dev.lumi.eternis.client.screen.kit;

import dev.lumi.eternis.block.dungeon.entity.KitSelectorBlockEntity;
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

                if (blockEntity != null) {
                    blockEntity.saveKit(kitIndex, this);
                }
            }
        };

        for (int i = 0; i < kit.size(); i++) {
            inventory.setStack(i, kit.get(i).copy());
        }

        // Kit inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slot = col + row * 9 + 9;
                this.addSlot(new Slot(inventory, slot, 8 + col * 18, 21 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 78));
        }

        for (int row = 0; row < 4; row++) {
            int armorSlot = 36 + row;
            this.addSlot(new Slot(inventory, armorSlot, 173, 21 + row * 19));
        }

        this.addSlot(new Slot(inventory, 40, 192, 78));

        //Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slot = 9 + col + row * 9;
                this.addSlot(new Slot(playerInventory, slot, 8 + col * 18, 110 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 168));
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

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);

        if (!player.getWorld().isClient && blockEntity != null) {
            blockEntity.saveKit(kitIndex, inventory);
        }
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 20) {
            if (!player.getWorld().isClient && blockEntity != null) {
                blockEntity.saveKit(kitIndex, inventory);
            }
            return true;
        }

        return false;
    }

    public record KitModifyData(BlockPos pos, int kitIndex) {
        public static final PacketCodec<RegistryByteBuf, KitModifyData> PACKET_CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC.cast(), KitModifyData::pos, PacketCodecs.INTEGER, KitModifyData::kitIndex, KitModifyData::new);
    }
}
