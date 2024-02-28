package fr.ph1823.mainmenu.gui;

import fr.ph1823.mainmenu.CustomMainMenuMods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiPlayButton extends GuiButton {
    private final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(CustomMainMenuMods.MODID,"textures/gui/play.png");
    private final ResourceLocation BUTTON_HOVERED_TEXTURES = new ResourceLocation(CustomMainMenuMods.MODID,"textures/gui/play_hover.png");

    public GuiPlayButton(int btnID, int x,int y) {
        super(btnID, x, y - 46/2, 128, 46, "");
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            //FontRenderer fontrenderer = mc.fontRenderer;
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.hovered ? BUTTON_HOVERED_TEXTURES : BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height,this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
