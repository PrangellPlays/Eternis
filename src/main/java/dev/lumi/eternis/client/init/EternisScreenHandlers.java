package dev.lumi.eternis.client.init;

import dev.lumi.eternis.Eternis;
import dev.lumi.eternis.block.entity.KitSelectorBlockEntity;
import dev.lumi.eternis.client.screen.KitModifyScreenHandler;
import dev.lumi.eternis.client.screen.KitSelectorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.LinkedHashMap;
import java.util.Map;

public class EternisScreenHandlers {
    protected static final Map<ScreenHandlerType<?>, Identifier> SCREEN_HANDLER_TYPES = new LinkedHashMap();
    public static final ScreenHandlerType<KitSelectorScreenHandler> KIT_SELECTOR;
    public static final ScreenHandlerType<KitModifyScreenHandler> KIT_MODIFY;

    public EternisScreenHandlers() {
    }

    protected static <T extends ScreenHandler> ScreenHandlerType<T> create(String name, ScreenHandlerType<T> screenHandlerType) {
        SCREEN_HANDLER_TYPES.put(screenHandlerType, Eternis.id(name));
        return screenHandlerType;
    }

    public static void init() {
        SCREEN_HANDLER_TYPES.forEach((screenHandlerType, id) -> {
            Registry.register(Registries.SCREEN_HANDLER, id, screenHandlerType);
        });
    }

    static {
        KIT_SELECTOR = create("kit_selector", new ExtendedScreenHandlerType<>(KitSelectorScreenHandler::new, KitSelectorScreenHandler.KitSelectorData.PACKET_CODEC));
        KIT_MODIFY = create("kit_modify", new ExtendedScreenHandlerType<>(KitModifyScreenHandler::new, KitModifyScreenHandler.KitModifyData.PACKET_CODEC));
    }
}
