package com.lycaniteadditions.world.dimension_rifts;

import com.lycaniteadditions.init.BiomeInit;
import com.lycaniteadditions.init.DimensionInit;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionOuterRifts extends WorldProvider
{
    public DimensionOuterRifts ()
    {
        this.biomeProvider= new BiomeProviderSingle(BiomeInit.OUTERRIFTS_Dimension);
    }
      @Override
    public DimensionType getDimensionType()
      {
        return DimensionInit.OUTERRIFTS;
      }

      @Override
    public IChunkGenerator createChunkGenerator()
      {
        return new ChunkGeneratorOuterRifts(world, false, world.getSeed() );
      }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
}
