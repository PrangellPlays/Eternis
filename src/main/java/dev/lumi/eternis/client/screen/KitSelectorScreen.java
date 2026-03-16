package dev.lumi.eternis.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lumi.eternis.Eternis;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KitSelectorScreen extends HandledScreen<KitSelectorScreenHandler> {
    private static final Identifier TEXTURE = Eternis.id("textures/gui/kit_selector.png");

    private ButtonWidget claim1;
    private ButtonWidget claim2;
    private ButtonWidget claim3;

    private ButtonWidget edit1;
    private ButtonWidget edit2;
    private ButtonWidget edit3;

    public KitSelectorScreen(KitSelectorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        int x = this.x;
        int y = this.y;

        claim1 = addDrawableChild(ButtonWidget.builder(Text.literal("Claim"), btn -> client.interactionManager.clickButton(handler.syncId, 0)).dimensions(x + 120, y + 20, 40, 20).build());
        claim2 = addDrawableChild(ButtonWidget.builder(Text.literal("Claim"), btn -> client.interactionManager.clickButton(handler.syncId, 1)).dimensions(x + 120, y + 45, 40, 20).build());
        claim3 = addDrawableChild(ButtonWidget.builder(Text.literal("Claim"), btn -> client.interactionManager.clickButton(handler.syncId, 2)).dimensions(x + 120, y + 70, 40, 20).build());

        edit1 = addDrawableChild(ButtonWidget.builder(Text.literal("Edit"), btn -> client.interactionManager.clickButton(handler.syncId, 10)).dimensions(x + 80, y + 20, 36, 20).build());
        edit2 = addDrawableChild(ButtonWidget.builder(Text.literal("Edit"), btn -> client.interactionManager.clickButton(handler.syncId, 11)).dimensions(x + 80, y + 45, 36, 20).build());
        edit3 = addDrawableChild(ButtonWidget.builder(Text.literal("Edit"), btn -> client.interactionManager.clickButton(handler.syncId, 12)).dimensions(x + 80, y + 70, 36, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
