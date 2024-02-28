package fr.ph1823.mainmenu.gui;

import fr.ph1823.mainmenu.CustomMainMenuMods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ButtonCustom extends GuiButton {
    public ButtonCustom(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, 128, 12, buttonText);
    }

    private final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(CustomMainMenuMods.MODID,"textures/gui/button.png");
    private final ResourceLocation BUTTON_HOVERED_TEXTURES = new ResourceLocation(CustomMainMenuMods.MODID,"textures/gui/button_hover.png");

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            //FontRenderer fontrenderer = mc.fontRenderer;
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableAlpha();
            mc.getTextureManager().bindTexture(this.hovered ? BUTTON_HOVERED_TEXTURES : BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.6F);
            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height,this.width, this.height);

            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            this.drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
            GlStateManager.disableAlpha();
        }
    }
}
