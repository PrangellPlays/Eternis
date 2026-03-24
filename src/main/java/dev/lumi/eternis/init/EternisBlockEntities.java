package dev.lumi.eternis.init;

import dev.lumi.eternis.Eternis;
import dev.lumi.eternis.block.dungeon.entity.KitSelectorBlockEntity;
import dev.lumi.eternis.block.dungeon.waygate.WaygateBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class EternisBlockEntities {
    protected static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap();
    public static final BlockEntityType<KitSelectorBlockEntity> KIT_SELECTOR;

    public static final BlockEntityType<WaygateBlockEntity> WAYGATE;

    public EternisBlockEntities() {
    }

    protected static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntityType) {
        BLOCK_ENTITY_TYPES.put(blockEntityType, Eternis.id(name));
        return blockEntityType;
    }

    public static void init() {
        BLOCK_ENTITY_TYPES.forEach((blockEntityType, id) -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, id, blockEntityType);
        });
    }

    static {
        KIT_SELECTOR = create("kit_selector", FabricBlockEntityTypeBuilder.create(KitSelectorBlockEntity::new, EternisBlocks.KIT_SELECTOR).build());

        WAYGATE = create("waygate", FabricBlockEntityTypeBuilder.create(WaygateBlockEntity::new, EternisBlocks.WAYGATE).build());
    }
}
