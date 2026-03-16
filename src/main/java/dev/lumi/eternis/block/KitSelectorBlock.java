package dev.lumi.eternis.block;

import dev.lumi.eternis.block.entity.KitSelectorBlockEntity;
import dev.lumi.eternis.client.screen.KitSelectorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KitSelectorBlock extends Block implements BlockEntityProvider {
    public KitSelectorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KitSelectorBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof KitSelectorBlockEntity kitSelectorBlockEntity) {
            player.openHandledScreen(new ExtendedScreenHandlerFactory<KitSelectorScreenHandler.KitSelectorData>() {
                @Override
                public KitSelectorScreenHandler.KitSelectorData getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
                    return new KitSelectorScreenHandler.KitSelectorData(kitSelectorBlockEntity.getPos());
                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable("container.eternis.kit_selector");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    return new KitSelectorScreenHandler(syncId, inv, new KitSelectorScreenHandler.KitSelectorData(kitSelectorBlockEntity.getPos()));
                }
            });
        }

        return ActionResult.CONSUME;
    }
}
