package dev.lumi.eternis.block.dungeon.parkour;

import com.mojang.serialization.MapCodec;
import dev.lumi.eternis.block.dungeon.astract.AbstractParkourPlateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class RedirectPlateBlock extends AbstractParkourPlateBlock {
    public static final MapCodec<RedirectPlateBlock> CODEC = createCodec(RedirectPlateBlock::new);

    public RedirectPlateBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void applyEffect(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {
        if (world.isClient || !(entity instanceof LivingEntity living)) return;

        Direction dir = state.get(FACING);
        Vec3d vec = new Vec3d(dir.getOffsetX(), 0, dir.getOffsetZ()).normalize();
        Vec3d current = living.getVelocity();

        double strength = 1.8D;
        living.setVelocity(vec.x * strength, Math.max(current.y, 0.25D), vec.z * strength);
        living.velocityModified = true;
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("lore.eternis.block.rotatable_block").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.eternis.redirect_plate.desc").formatted(Formatting.DARK_GREEN));
    }
}
