package dev.lumi.eternis.block.dungeon.waygate;

import dev.lumi.eternis.init.EternisBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class WaygateBlockEntity extends BlockEntity {
    private String displayName = "Waygate";
    private String description = "";
    private String targetDimension = "minecraft:overworld";
    private BlockPos targetPos = BlockPos.ORIGIN;

    public WaygateBlockEntity(BlockPos pos, BlockState state) {
        super(EternisBlockEntities.WAYGATE, pos, state);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? "" : displayName;
        markDirtyAndSync();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
        markDirtyAndSync();
    }

    public String getTargetDimension() {
        return targetDimension;
    }

    public void setTargetDimension(String targetDimension) {
        this.targetDimension = targetDimension == null || targetDimension.isBlank() ? "minecraft:overworld" : targetDimension;
        markDirtyAndSync();
    }

    public BlockPos getTargetPos() {
        return targetPos;
    }

    public void setTargetPos(BlockPos targetPos) {
        this.targetPos = targetPos == null ? BlockPos.ORIGIN : targetPos;
        markDirtyAndSync();
    }

    public Identifier getTargetDimensionId() {
        return Identifier.tryParse(targetDimension);
    }

    private void markDirtyAndSync() {
        markDirty();
        if (world != null && !world.isClient()) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.putString("DisplayName", displayName);
        nbt.putString("Description", description);
        nbt.putString("TargetDimension", targetDimension);

        nbt.putInt("TargetX", targetPos.getX());
        nbt.putInt("TargetY", targetPos.getY());
        nbt.putInt("TargetZ", targetPos.getZ());
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        displayName = nbt.contains("DisplayName") ? nbt.getString("DisplayName") : "Waygate";
        description = nbt.contains("Description") ? nbt.getString("Description") : "";
        targetDimension = nbt.contains("TargetDimension") ? nbt.getString("TargetDimension") : "minecraft:overworld";

        int x = nbt.contains("TargetX") ? nbt.getInt("TargetX") : 0;
        int y = nbt.contains("TargetY") ? nbt.getInt("TargetY") : 100;
        int z = nbt.contains("TargetZ") ? nbt.getInt("TargetZ") : 0;
        targetPos = new BlockPos(x, y, z);
    }
}
