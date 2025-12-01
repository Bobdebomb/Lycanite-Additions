package com.lycaniteadditions.mixin.lycanitesmobs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.lycaniteadditions.LycaniteAdditions;
import com.lycanitesmobs.Utilities;
import com.lycanitesmobs.core.JSONLoader;
import com.lycanitesmobs.core.info.ModInfo;
import com.lycanitesmobs.core.mobevent.MobEventManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.Map;

@Mixin(MobEventManager.class)
public abstract class JSONMobEvents_LycaniteAdditionsMixin extends JSONLoader {

    /**
     * Automatically search for mob events jsons in resources/assets/lycaniteadditions/mobevents
     * @author cdstk
     */
    @Inject(
            method = "loadAllFromJSON",
            at = @At(value = "INVOKE", target = "Lcom/lycanitesmobs/core/mobevent/MobEventManager;loadJsonObjects(Lcom/google/gson/Gson;Ljava/nio/file/Path;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", ordinal = 1),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsMobEventManager_loadAllFromJSON(ModInfo groupInfo, CallbackInfo ci, @Local Gson gson, @Local(ordinal = 1) Map<String, JsonObject> defaultMobEventJSONs){
        Path path = Utilities.getAssetPath(LycaniteAdditions.class, LycaniteAdditions.MODID, "mobevents");
        this.loadJsonObjects(gson, path, defaultMobEventJSONs, "name", "event");
    }
}
