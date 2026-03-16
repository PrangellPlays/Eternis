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
import net.minecraft.world.World;

import java.util.List;

public class CryoPlateBlock extends AbstractParkourPlateBlock {
    public static final MapCodec<CryoPlateBlock> CODEC = createCodec(CryoPlateBlock::new);

    public CryoPlateBlock(Settings settings) {
        super(settings.slipperiness(0.98F));
    }

    @Override
    protected void applyEffect(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof LivingEntity living) {
            Vec3d vel = living.getVelocity();

            living.setVelocity(vel.x * 0.92, vel.y, vel.z * 0.92);
            living.velocityModified = true;
        }
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("lore.eternis.block.rotatable_block").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.eternis.cryo_plate.desc").formatted(Formatting.DARK_GREEN));
    }
}
