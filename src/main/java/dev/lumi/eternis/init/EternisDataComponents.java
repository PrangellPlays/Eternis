package dev.lumi.eternis.init;

import com.mojang.serialization.Codec;
import dev.lumi.eternis.Eternis;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EternisDataComponents {
    public static final ComponentType<Integer> ENTITY_DEATH_COUNT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Eternis.id("enemy_death_count"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void init() {
    }
}
