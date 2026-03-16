package dev.lumi.eternis.client.screen;

import dev.lumi.eternis.block.entity.KitSelectorBlockEntity;
import dev.lumi.eternis.client.init.EternisScreenHandlers;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KitSelectorScreenHandler extends ScreenHandler {
    private final KitSelectorBlockEntity blockEntity;

    public KitSelectorScreenHandler(int syncId, PlayerInventory playerInventory, KitSelectorData data) {
        super(EternisScreenHandlers.KIT_SELECTOR, syncId);
        World world = playerInventory.player.getWorld();
        this.blockEntity = (KitSelectorBlockEntity) world.getBlockEntity(data.pos());

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
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id <= 2) {
            blockEntity.giveKit(player, id);
            return true;
        }

        if (id >= 10 && id <= 12 && player.isCreative()) {
            int kit = id - 10;

            player.openHandledScreen(new ExtendedScreenHandlerFactory<KitModifyScreenHandler.KitModifyData>() {
                @Override
                public KitModifyScreenHandler.KitModifyData getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
                    return new KitModifyScreenHandler.KitModifyData(blockEntity.getPos(), kit);
                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable("container.eternis.kit_modify");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    return new KitModifyScreenHandler(syncId, inv, new KitModifyScreenHandler.KitModifyData(blockEntity.getPos(), kit));
                }
            });

            return true;
        }

        return false;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public record KitSelectorData(BlockPos pos) {
        public static final PacketCodec<RegistryByteBuf, KitSelectorData> PACKET_CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC.cast(), KitSelectorData::pos, KitSelectorData::new);
    }
}
