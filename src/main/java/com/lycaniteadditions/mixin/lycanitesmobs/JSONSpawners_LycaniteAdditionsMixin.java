package com.lycaniteadditions.mixin.lycanitesmobs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.lycaniteadditions.LycaniteAdditions;
import com.lycanitesmobs.Utilities;
import com.lycanitesmobs.core.JSONLoader;
import com.lycanitesmobs.core.spawner.SpawnerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.Map;

@Mixin(SpawnerManager.class)
public abstract class JSONSpawners_LycaniteAdditionsMixin extends JSONLoader {

    /**
     * Automatically search for spawner jsons in resources/assets/lycaniteadditions/spawners
     * Automatically search for mob event spawner jsons in resources/assets/lycaniteadditions/mobevents
     * @author cdstk
     */
    @Inject(
            method = "loadAllFromJSON",
            at = @At(value = "INVOKE", target = "Lcom/lycanitesmobs/core/spawner/SpawnerManager;loadJsonObjects(Lcom/google/gson/Gson;Ljava/nio/file/Path;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", ordinal = 3),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsDungeonManager_loadJsonObjects(CallbackInfo ci, @Local Gson gson, @Local(ordinal = 1) Map<String, JsonObject> defaultSpawnerJSONs, @Local(ordinal = 2) Map<String, JsonObject> defaultMobEventsJSONs){
        Path path = Utilities.getAssetPath(LycaniteAdditions.class, LycaniteAdditions.MODID, "spawners");
        this.loadJsonObjects(gson, path, defaultSpawnerJSONs, "name", "spawner");
        path = Utilities.getAssetPath(LycaniteAdditions.class, LycaniteAdditions.MODID, "mobevents");
        this.loadJsonObjects(gson, path, defaultMobEventsJSONs, "name", "spawner");
    }
}
