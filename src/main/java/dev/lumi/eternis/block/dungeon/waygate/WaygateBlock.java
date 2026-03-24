package dev.lumi.eternis.block.dungeon.waygate;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WaygateBlock extends BlockWithEntity {
    public static final MapCodec<WaygateBlock> CODEC = createCodec(WaygateBlock::new);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty PART_X = IntProperty.of("part_x", 0, 2);
    public static final IntProperty PART_Y = IntProperty.of("part_y", 0, 2);

    public WaygateBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(PART_X, 1).with(PART_Y, 0));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART_X, PART_Y);
    }

    public static boolean isMaster(BlockState state) {
        return state.contains(PART_X) && state.contains(PART_Y) && state.get(PART_X) == 1 && state.get(PART_Y) == 1;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(PART_X, 1).with(PART_Y, 0);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.isClient) return;

        Direction facing = state.get(FACING);
        BlockPos masterPos = pos.up();

        if (!WaygateStructureHelper.canPlaceStructure(world, masterPos, facing)) {
            world.breakBlock(masterPos, true);
            return;
        }

        WaygateStructureHelper.placeStructure(world, masterPos, facing, this);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        WaygateBlockEntity waygate = WaygateStructureHelper.getMasterBlockEntity(world, pos);
        if (waygate == null) return ActionResult.PASS;

        if (player.isCreativeLevelTwoOp()) {
            player.sendMessage(Text.literal("Open Waygate Edit GUI"), false);
            return ActionResult.CONSUME;
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {
            boolean success = WaygateTeleportUtil.useWaygate(serverPlayer, waygate);

            if (!success) {
                player.sendMessage(Text.literal("The Waygate Does Not Respond"), false);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            WaygateStructureHelper.breakWholeStructure(world, pos, false);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            return;
        }

        if (!world.isClient() && isMaster(state)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WaygateBlockEntity) {

            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (isMaster(state)) return new WaygateBlockEntity(pos, state);
        return null;
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return false;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 0;
    }
}
