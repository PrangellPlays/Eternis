package dev.lumi.eternis.client.screen;

import dev.lumi.eternis.init.EternisDataComponents;
import dev.lumi.eternis.item.util.EnemySlainWeaponItem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class EntityKillCountOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        World world = client.world;
        TextRenderer textRenderer = client.textRenderer;

        int margin = 6;
        int lineHeight = 10;
        int y = margin;

        // header
        if (client.player.getMainHandStack().getItem() instanceof EnemySlainWeaponItem) {
            int entityKillCount = client.player.getMainHandStack().get(EternisDataComponents.ENTITY_DEATH_COUNT);

            double radius = 4096; // adjust depending on your use case
            Box box = new Box(
                    client.player.getX() - radius, client.player.getY() - radius, client.player.getZ() - radius,
                    client.player.getX() + radius, client.player.getY() + radius, client.player.getZ() + radius
            );

            drawContext.drawTextWithShadow(textRenderer,
                    Text.literal("Entity Kill Count = " + entityKillCount).formatted(Formatting.GOLD),
                    margin,
                    y,
                    0xFFFFFF
            );
            y += lineHeight + 2;
        }
    }

    public static void register() {
        HudRenderCallback.EVENT.register(new EntityKillCountOverlay());
    }
}
