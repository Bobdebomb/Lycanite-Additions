package com.lycaniteadditions.world.test;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeTest extends Biome
{
	@SuppressWarnings("unused")
	public BiomeTest() 
	{
		super(new BiomeProperties("Test").setBaseHeight(0.2f).setTemperature(0.5f).setHeightVariation(1.8f).setWaterColor(16724639));

		fillerBlock = Blocks.MAGMA.getDefaultState();
		

	}
	
	/*@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) 
	{
		//topBlock = BlockInit.SILVER_BLOCK.getDefaultState();
		//fillerBlock = BlockInit.RANDOM_BLOCK.getDefaultState();
	}*/
	
	@Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 8781342;
	}
}