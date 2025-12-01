package com.lycaniteadditions;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LycaniteAdditions.MODID, version = LycaniteAdditions.VERSION, name = LycaniteAdditions.NAME, dependencies = "required-after:mixinbooter@[10.7,);required-after:lycanitesmobs")
public class LycaniteAdditions {
    public static final String MODID = "lycaniteadditions";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Lycanite Additions";
    public static final Logger LOGGER = LogManager.getLogger();
    public static Object modInfo;


    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", LycaniteAdditions.NAME);


    }

}
