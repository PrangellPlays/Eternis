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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class ReboundPlateBlock extends AbstractParkourPlateBlock {
    public static final MapCodec<ReboundPlateBlock> CODEC = createCodec(ReboundPlateBlock::new);

    public ReboundPlateBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void applyEffect(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {
        entity.handleFallDamage(entity.fallDistance, 0.0F, world.getDamageSources().fall());

        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < (double)0.0F) {
            double d = entity instanceof LivingEntity ? (double)1.0F : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("lore.eternis.block.rotatable_block").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.eternis.rebound_plate.desc").formatted(Formatting.DARK_GREEN));
    }
}
