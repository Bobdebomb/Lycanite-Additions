package com.lycaniteadditions.mixin.lycanitesmobs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lycaniteadditions.LycaniteAdditions;
import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.Utilities;
import com.lycanitesmobs.core.JSONLoader;
import com.lycanitesmobs.core.info.ModInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;

@Mixin(JSONLoader.class)
public abstract class JSONLoader_LycaniteAdditionsMixin {

    // Used to prepend an "!" before every addon file name
    @Unique
    private static final HashSet<String> lycaniteAdditions$ADDON_PREFIX_JSONS = new HashSet<>();

    @Shadow(remap = false)
    public abstract void loadJsonObjects(Gson gson, Path path, Map<String, JsonObject> jsonObjectMap, String mapKey, String jsonType);

    /**
     * Automatically search for jsons in resources/assets/lycaniteadditions
     * @author cdstk
     */
    @Inject(
            method = "loadAllJson",
            at = @At(value = "INVOKE", target = "Lcom/lycanitesmobs/core/JSONLoader;loadJsonObjects(Lcom/google/gson/Gson;Ljava/nio/file/Path;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", ordinal = 1),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsJSONLoader_loadJsonObjectsCustomDefault(ModInfo groupInfo, String name, String assetPath, String mapKey, boolean loadCustom, CallbackInfo ci, @Local Gson gson, @Local(ordinal = 1) Map<String, JsonObject> defaultJsons){
        Path path = Utilities.getAssetPath(LycaniteAdditions.class, LycaniteAdditions.MODID, assetPath);
        this.loadJsonObjects(gson, path, defaultJsons, mapKey, null);
    }

    /**
     * Flag any jsons from Lycanite Additions to have "!lycaniteadditions_" added to the beginning of the file name
     * @author cdstk
     */
    @Inject(
            method = "loadJsonObjects",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsJSONLoader_loadJsonObjectsAddonFlagging(Gson gson, Path path, Map<String, JsonObject> jsonObjectMap, String mapKey, String jsonType, CallbackInfo ci, @Local(ordinal = 1) Path filePath, @Local JsonObject json){
        boolean isAddon = true;
        // I recall the Lycanites Mobs path is sometimes prepended with a "."
        for (Path subPath : filePath) {
            if (subPath.startsWith(LycanitesMobs.modid)) {
                isAddon = false;
                break;
            }
            else if(subPath.startsWith(LycaniteAdditions.MODID)) {
                break;
            }
        }

        // Only prepend if custom addition, keep replacement file names the same
        if(isAddon && !jsonObjectMap.containsKey(json.get(mapKey).getAsString())) {
            lycaniteAdditions$ADDON_PREFIX_JSONS.add(json.get(mapKey).getAsString());
        }
    }

    /**
     * Make any jsons from Lycanite Additions to have "!lycaniteadditions_" added to the beginning of the file name
     * @author cdstk
     */
    @Inject(
            method = "saveJsonObject",
            at = @At("HEAD"),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsJSONLoader_saveJsonObjectAddonFlagging(Gson gson, JsonObject jsonObject, String name, String assetPath, CallbackInfo ci, @Local(argsOnly = true, ordinal = 0) LocalRef<String> fileName){
        if(lycaniteAdditions$ADDON_PREFIX_JSONS.contains(name)) fileName.set("!" + LycaniteAdditions.MODID + "_" + name);
    }
}
