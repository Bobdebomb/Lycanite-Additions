package com.lycaniteadditions.handlers;


import com.lycaniteadditions.commands.CommandTeleportDim;
import com.lycaniteadditions.init.DimensionInit;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@EventBusSubscriber
public class RegistryHandler 
{

	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{

		DimensionInit.registerDimensions();

	}
	


	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandTeleportDim());
	}
}