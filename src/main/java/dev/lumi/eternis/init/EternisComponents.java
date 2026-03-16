package dev.lumi.eternis.init;

import dev.lumi.eternis.Eternis;
import dev.lumi.eternis.cca.KitPlayerComponent;
import dev.lumi.eternis.cca.PlayerSavedInventoryComponent;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class EternisComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public static final ComponentKey<PlayerSavedInventoryComponent> SAVED_INVENTORY = ComponentRegistry.getOrCreate(Eternis.id("saved_inventory"), PlayerSavedInventoryComponent.class);
    public static final ComponentKey<KitPlayerComponent> KIT_PLAYER = ComponentRegistry.getOrCreate(Eternis.id("kit_player"), KitPlayerComponent.class);

    public EternisComponents() {
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SAVED_INVENTORY, PlayerSavedInventoryComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(KIT_PLAYER, KitPlayerComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {

    }
}
