package com.lycaniteadditions.init;

import com.lycaniteadditions.world.dimension_rifts.DimensionOuterRifts;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
    public static final DimensionType OUTERRIFTS = DimensionType.register("Outer Rifts", "_outerrifts", 2, DimensionOuterRifts.class, false);

    public static void registerDimensions()
    {
        DimensionManager.registerDimension(2,OUTERRIFTS );
    }
}
