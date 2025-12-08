package com.lycaniteadditions.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeInit
{
    public static final Biome OUTERRIFTS_Dimension = new BiomeOuterrifts();
    public static Biome TEST_BIOME;

    public static void registerBiomes()
{
    initBiome(OUTERRIFTS_Dimension, "outer rifts", BiomeManager.BiomeType.WARM, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.DRY);
}

    private static void initBiome(Biome outerriftsDimension, String outerRifts, BiomeManager.BiomeType biomeType, BiomeDictionary.Type spooky, BiomeDictionary.Type dense, BiomeDictionary.Type dry) {
    }


}

