package dev.lumi.eternis.block.dungeon.waygate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WaygateStructureHelper {
    public static BlockPos getMasterPos(World world, BlockPos clickedPos) {
        BlockState state = world.getBlockState(clickedPos);

        if (!(state.getBlock() instanceof WaygateBlock)) {
            return null;
        }

        int partX = state.get(WaygateBlock.PART_X);
        int partY = state.get(WaygateBlock.PART_Y);
        Direction facing = state.get(WaygateBlock.FACING);

        int horizontalOffset = 1 - partX;
        int verticalOffset = 1 - partY;

        Direction right = facing.rotateYClockwise();

        return clickedPos.offset(right, horizontalOffset).up(verticalOffset);
    }

    public static WaygateBlockEntity getMasterBlockEntity(World world, BlockPos clickedPos) {
        BlockPos masterPos = getMasterPos(world, clickedPos);
        if (masterPos == null) return null;

        BlockEntity blockEntity = world.getBlockEntity(masterPos);
        if (blockEntity instanceof WaygateBlockEntity waygate) {
            return waygate;
        }

        return null;
    }

    public static List<BlockPos> getAllPartPositions(BlockPos masterPos, Direction facing) {
        List<BlockPos> positions = new ArrayList<>();
        Direction right = facing.rotateYClockwise();

        for (int partY = 0; partY < 3; partY++) {
            for (int partX = 0; partX < 3; partX++) {
                int horizontalOffset = partX - 1; // -1, 0, 1
                int verticalOffset = partY - 1;   // -1, 0, 1

                BlockPos partPos = masterPos.offset(right, horizontalOffset).up(verticalOffset);
                positions.add(partPos);
            }
        }

        return positions;
    }

    public static boolean canPlaceStructure(World world, BlockPos masterPos, Direction facing) {
        for (BlockPos partPos : getAllPartPositions(masterPos, facing)) {
            BlockState existing = world.getBlockState(partPos);

            if (!existing.isAir() && !existing.isReplaceable()) {
                return false;
            }
        }

        return true;
    }

    public static void placeStructure(World world, BlockPos masterPos, Direction facing, Block block) {
        Direction right = facing.rotateYClockwise();

        for (int partY = 0; partY < 3; partY++) {
            for (int partX = 0; partX < 3; partX++) {
                int horizontalOffset = partX - 1;
                int verticalOffset = partY - 1;

                BlockPos partPos = masterPos.offset(right, horizontalOffset).up(verticalOffset);
                BlockState partState = block.getDefaultState().with(WaygateBlock.FACING, facing).with(WaygateBlock.PART_X, partX).with(WaygateBlock.PART_Y, partY);
                world.setBlockState(partPos, partState, Block.NOTIFY_ALL);
            }
        }
    }

    public static void breakWholeStructure(World world, BlockPos anyPartPos, boolean dropItems) {
        BlockState state = world.getBlockState(anyPartPos);
        if (!(state.getBlock() instanceof WaygateBlock)) return;

        BlockPos masterPos = getMasterPos(world, anyPartPos);
        if (masterPos == null) return;

        BlockState masterState = world.getBlockState(masterPos);
        if (!(masterState.getBlock() instanceof WaygateBlock)) return;

        Direction facing = masterState.get(WaygateBlock.FACING);

        // Remove all parts directly without calling breakBlock recursively
        for (BlockPos partPos : getAllPartPositions(masterPos, facing)) {
            BlockState partState = world.getBlockState(partPos);
            if (partState.getBlock() instanceof WaygateBlock) {
                world.removeBlock(partPos, false);
            }
        }

        // If you want item drops, spawn them ONCE from master or clicked part
        if (dropItems) {
            Block.dropStacks(state, world, anyPartPos);
        }
    }
}
