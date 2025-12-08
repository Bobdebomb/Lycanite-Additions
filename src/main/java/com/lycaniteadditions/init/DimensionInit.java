package com.lycaniteadditions.init;

import com.lycaniteadditions.handlers.ConfigHandler;
import com.lycaniteadditions.world.Dimension_outerrifts.DimensionNetherTemp;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
    public static final DimensionType NETHER_TEMP = DimensionType.register("NetherTemp", "_nethertemp", ConfigHandler.NETHER_TEMP, DimensionNetherTemp.class, false);;

    public static void registerDimensions()
    {
        DimensionManager.registerDimension(ConfigHandler.NETHER_TEMP, NETHER_TEMP);

    }
}