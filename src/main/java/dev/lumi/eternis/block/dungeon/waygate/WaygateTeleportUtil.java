package dev.lumi.eternis.block.dungeon.waygate;

import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class WaygateTeleportUtil {
    public static boolean useWaygate(ServerPlayerEntity player, WaygateBlockEntity sourceBe) {
        if (sourceBe.getWorld() == null || sourceBe.getWorld().isClient()) {
            return false;
        }

        MinecraftServer server = player.getServer();
        if (server == null) {
            return false;
        }

        Identifier dimId = sourceBe.getTargetDimensionId();
        if (dimId == null) {
            return false;
        }

        RegistryKey<World> worldKey = RegistryKey.of(RegistryKeys.WORLD, dimId);
        ServerWorld targetWorld = server.getWorld(worldKey);
        if (targetWorld == null) {
            return false;
        }

        BlockPos targetPos = sourceBe.getTargetPos();

        // Ensure chunk loads
        targetWorld.getChunk(targetPos);

        BlockState targetState = targetWorld.getBlockState(targetPos);

        float yaw = player.getYaw();

        // If destination is another waygate, spawn in front of it
        if (targetState.getBlock() instanceof WaygateBlock && WaygateBlock.isMaster(targetState)) {
            SpawnData spawn = findSpawnInFrontOfGate(targetWorld, targetPos, targetState);
            if (spawn == null) return false;

            player.teleport(targetWorld, spawn.x, spawn.y, spawn.z, spawn.yaw, player.getPitch());
            return true;
        }

        // Otherwise just teleport directly to stored coords + centered
        player.teleport(targetWorld, targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5, yaw, player.getPitch());
        return true;
    }

    private static SpawnData findSpawnInFrontOfGate(ServerWorld world, BlockPos masterPos, BlockState masterState) {
        Direction facing = masterState.get(WaygateBlock.FACING);

        BlockPos bottomCenter = masterPos.down();
        BlockPos frontBase = bottomCenter.offset(facing);

        BlockPos[] candidates = new BlockPos[] {
                frontBase,
                frontBase.up(),
                frontBase.up(2),
                masterPos.offset(facing),
                masterPos.offset(facing).up(),
                frontBase.offset(facing),
                frontBase.offset(facing).up()
        };

        float yaw = yawFromFacing(facing);

        for (BlockPos base : candidates) {
            if (isSafeStandingSpot(world, base)) {
                return new SpawnData(base.getX() + 0.5, base.getY(), base.getZ() + 0.5, yaw);
            }
        }

        return null;
    }

    private static boolean isSafeStandingSpot(ServerWorld world, BlockPos basePos) {
        BlockState feet = world.getBlockState(basePos);
        BlockState head = world.getBlockState(basePos.up());
        BlockState below = world.getBlockState(basePos.down());

        boolean feetClear = feet.isAir() || feet.getCollisionShape(world, basePos).isEmpty();
        boolean headClear = head.isAir() || head.getCollisionShape(world, basePos.up()).isEmpty();
        boolean hasFloor = !below.getCollisionShape(world, basePos.down()).isEmpty();

        return feetClear && headClear && hasFloor;
    }

    public static float yawFromFacing(Direction facing) {
        return switch (facing) {
            case SOUTH -> 0.0F;
            case WEST -> 90.0F;
            case NORTH -> 180.0F;
            case EAST -> -90.0F;
            default -> 0.0F;
        };
    }

    private record SpawnData(double x, double y, double z, float yaw) {
    }
}
