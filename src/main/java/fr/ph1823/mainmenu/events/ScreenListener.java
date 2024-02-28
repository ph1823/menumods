package fr.ph1823.mainmenu.events;

import fr.ph1823.mainmenu.gui.CustomMainMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ScreenListener {

    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {
        if(event.getGui() != null && event.getGui().getClass() == GuiMainMenu.class)
            event.setGui(new CustomMainMenu());
    }
}
