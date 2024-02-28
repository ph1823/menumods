package fr.ph1823.mainmenu.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CustomRenderPlayer extends RenderPlayer {
    public CustomRenderPlayer(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected boolean canRenderName(AbstractClientPlayer p_177070_1_) {
        return false;
    }

    @Override
    public void renderName(AbstractClientPlayer entity, double x, double y, double z)
    {
        double d0 = 1.0D;
        float f = NAME_TAG_RANGE;

        if (d0 < (double)(f * f))
        {
            float f22 = this.renderManager.playerViewY;
            float f1 = this.renderManager.playerViewX;
            float f2 = entity.height + 0.5F;

            String s = entity.getDisplayName().getFormattedText();
            GlStateManager.alphaFunc(516, 0.1F);
            EntityRenderer.drawNameplate(Minecraft.getMinecraft().fontRenderer, s, (float) x, (float) y + f2, (float)z, 0, f22, f1, false, false);
        }
    }
}
