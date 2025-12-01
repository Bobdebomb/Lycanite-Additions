package com.lycaniteadditions;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name(LycaniteAdditions.MODID)
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class LycaniteAdditionsPlugin implements ILateMixinLoader, IFMLLoadingPlugin {

	public LycaniteAdditionsPlugin() {

	}

	//mixin stuff here on out
	@Override
	public String[] getASMTransformerClass() {
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public List<String> getMixinConfigs() {
		return Arrays.asList("mixins.lycaniteadditions.json");
	}

	public void onMixinConfigQueued(String mixinConfig) {
		System.out.println(mixinConfig+" has been queued");
	}
}