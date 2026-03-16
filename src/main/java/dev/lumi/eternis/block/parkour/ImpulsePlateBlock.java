package dev.lumi.eternis.block.parkour;

import com.mojang.serialization.MapCodec;
import dev.lumi.eternis.block.astract.AbstractParkourPlateBlock;
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
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ImpulsePlateBlock extends AbstractParkourPlateBlock {
    public static final MapCodec<ImpulsePlateBlock> CODEC = createCodec(ImpulsePlateBlock::new);

    public ImpulsePlateBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void applyEffect(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof LivingEntity living)) return;

        Vec3d look = living.getRotationVector().normalize();
        Vec3d current = living.getVelocity();

        double strength = 1.6D;
        double upward = 0.18D;

        Vec3d launch = new Vec3d(look.x * strength, upward, look.z * strength);
        living.setVelocity(current.x * 0.25D + launch.x, Math.max(current.y, launch.y), current.z * 0.25D + launch.z);
        living.velocityModified = true;
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("lore.eternis.block.rotatable_block").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.eternis.impulse_plate.desc").formatted(Formatting.DARK_GREEN));
    }
}
