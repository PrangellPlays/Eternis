package dev.lumi.eternis.client.screen.kit;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lumi.eternis.Eternis;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KitModifyScreen extends HandledScreen<KitModifyScreenHandler> {
    private static final Identifier TEXTURE = Eternis.id("textures/gui/kit_selector_modify.png");

    private TextFieldWidget nameField;
    private ButtonWidget applyButton;
    private ButtonWidget cancelButton;

    public KitModifyScreen(KitModifyScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 216;
        this.backgroundHeight = 192;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void init() {
        super.init();

        int x = this.x;
        int y = this.y;
        nameField = new TextFieldWidget(textRenderer, x + 40, y + 60, 90, 20, Text.literal("Kit Name"));
        nameField.setMaxLength(32);

        addSelectableChild(nameField);

        applyButton = addDrawableChild(ButtonWidget.builder(Text.literal("✔"), btn -> {
            client.interactionManager.clickButton(handler.syncId, 20);
            client.setScreen(null);
        }).dimensions(x + 120, y + 60, 20, 20).build());

        cancelButton = addDrawableChild(ButtonWidget.builder(Text.literal("✖"), btn -> client.setScreen(null)).dimensions(x + 145, y + 60, 20, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        nameField.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (nameField.charTyped(chr, modifiers)) {
            return true;
        }

        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (nameField.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
