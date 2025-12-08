package com.lycaniteadditions.handlers;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
	public static Configuration config;

	//Dimensions
	public static int NETHER_TEMP = 2;
	public static int OVERWORLD_TEMP = 3;
	public static int END_TEMP = 4;


	public static void init(File file) {

		String category = "Dimension IDs";
		config.addCustomCategoryComment(category, "Set IDs for each Dimension.");
		NETHER_TEMP = config.getInt("Dimension Nether Template", category, 2, 2, 500, "Dimension ID for the Nether Template");
		OVERWORLD_TEMP = config.getInt("Dimension Overworld Template", category, 3, 2, 500, "Dimension ID for the Overworld Template");
		END_TEMP = config.getInt("Dimension End Template", category, 4, 2, 500, "Dimension ID for the End Template");

	}

}