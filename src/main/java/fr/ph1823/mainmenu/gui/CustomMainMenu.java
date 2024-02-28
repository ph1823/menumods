package fr.ph1823.mainmenu.gui;

import fr.ph1823.mainmenu.CustomMainMenuMods;
import fr.ph1823.mainmenu.utils.CustomRenderPlayer;
import fr.ph1823.mainmenu.utils.FakePlayer;
import fr.ph1823.mainmenu.utils.FakeWorld;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.io.IOException;

public class CustomMainMenu extends GuiScreen {

    private final ResourceLocation BACKGROUND = new ResourceLocation(CustomMainMenuMods.MODID, "textures/gui/background.png");
    private final ResourceLocation LOGO = new ResourceLocation(CustomMainMenuMods.MODID, "textures/gui/logo.png");

    @Override
    public void initGui() {
        super.initGui();

        FMLClientHandler.instance().setupServerList();

        //Remove all existing button
        this.buttonList.clear();
        this.buttonList.add(new GuiPlayButton(1, 10, (this.height/2)));
        this.buttonList.add(new ButtonCustom(2, this.width - 138, (this.height/2) - 24, I18n.format("menu.options")));
        this.buttonList.add(new ButtonCustom(3, this.width - 138, (this.height/2) - 12, I18n.format("menu.singleplayer")));
        this.buttonList.add(new ButtonCustom(4, this.width - 138, (this.height/2), I18n.format("fml.menu.mods")));
        this.buttonList.add(new ButtonCustom(5, this.width - 138, (this.height/2) + 12, I18n.format("menu.quit")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //Draw background and logo
        TextureManager textureManager = this.mc.getTextureManager();
        textureManager.bindTexture(BACKGROUND);
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 1920, 1017, this.width, this.height);

        //Draw server logo
        GlStateManager.enableAlpha();
        textureManager.bindTexture(LOGO);
        drawModalRectWithCustomSizedTexture(this.width/2 - 32, 12, 0, 0, 64, 64, 64, 64);
        GlStateManager.disableAlpha();

        World fakeWorld = new FakeWorld();
        FakePlayer fakePlayer = new FakePlayer(this.mc.getSession().getProfile(), fakeWorld);

        //Draw entity in the middle
        drawEntityOnScreen(this.width / 2, this.height/2 + 40, 40, (this.width / 2) - mouseX, (this.height /2) - 40 - mouseY, fakePlayer);

        //Draw other (like button)
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(-180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = this.mc.getRenderManager();
        CustomRenderPlayer renderPlayer = new CustomRenderPlayer(rendermanager);
        rendermanager.setPlayerViewY(180.0F);
        //rendermanager.setRenderShadow(false);
        renderPlayer.doRender((AbstractClientPlayer) ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        renderPlayer.renderName((AbstractClientPlayer) ent, 0.0D, 0.0D, 0.0D);
        //renderPlayer.setRenderOutlines(false);
        //rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.disableCull();
        GlStateManager.disableDepth();
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 1)
        {
            FMLClientHandler.instance().connectToServer(this, new ServerData("MyLife", "montanarp.datahosting.fr", false));
        }

        if (button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 3)
        {
            this.mc.displayGuiScreen(new GuiWorldSelection(this));
        }

        if (button.id == 4)
        {
            this.mc.displayGuiScreen(new net.minecraftforge.fml.client.GuiModList(this));
        }

        if (button.id == 5)
        {
            this.mc.shutdown();
        }

    }
}
