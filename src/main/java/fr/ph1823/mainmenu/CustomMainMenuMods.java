package fr.ph1823.mainmenu;

import fr.ph1823.mainmenu.events.ScreenListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = CustomMainMenuMods.MODID, name = CustomMainMenuMods.NAME, version = CustomMainMenuMods.VERSION)
public class CustomMainMenuMods
{
    public static final String MODID = "mainmenu";
    public static final String NAME = "CustomMainMenu by ph1823";
    public static final String VERSION = "1.0";

    public static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        /*LOGGER.info("Pre-init start");
        LOGGER.info("Register event start");*/
        MinecraftForge.EVENT_BUS.register(new ScreenListener());
        /*LOGGER.info("Register event finish");
        LOGGER.info("Pre-init finish");*/
    }
}
