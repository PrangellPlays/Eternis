package dev.lumi.eternis.block.parkour;

import com.mojang.serialization.MapCodec;
import dev.lumi.eternis.block.astract.AbstractParkourPlateBlock;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class KillZoneBlock extends AbstractParkourPlateBlock {
    public static final MapCodec<KillZoneBlock> CODEC = createCodec(KillZoneBlock::new);

    public KillZoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void applyEffect(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof PlayerEntity player) {
            if (player.isCreative()) {
                return;
            }
        }
        entity.kill();
    }

    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("lore.eternis.block.rotatable_block").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.eternis.kill_zone.desc").formatted(Formatting.DARK_GREEN));
    }
}
