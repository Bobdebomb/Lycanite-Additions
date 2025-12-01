package com.lycaniteadditions.mixin.lycanitesmobs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import com.lycaniteadditions.LycaniteAdditions;
import com.lycanitesmobs.Utilities;
import com.lycanitesmobs.core.JSONLoader;
import com.lycanitesmobs.core.dungeon.DungeonManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;
import java.util.Map;


@Mixin(DungeonManager.class)
public abstract class JSONDungeons_LycaniteAdditionsMixin extends JSONLoader {

    /**
     * Automatically search for dungeon jsons in resources/assets/lycaniteadditions/dungeons
     * @author cdstk
     */
    @Inject(
            method = "loadDungeonsFromJSON",
            at = @At(value = "INVOKE", target = "Lcom/lycanitesmobs/core/dungeon/DungeonManager;loadJsonObjects(Lcom/google/gson/Gson;Ljava/nio/file/Path;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", ordinal = 1),
            remap = false
    )
    public void lycaniteAdditions_lycanitesMobsDungeonManager_loadJsonObjects(String type, CallbackInfoReturnable<Map<String, JsonObject>> cir, @Local Gson gson, @Local(ordinal = 1) Map<String, JsonObject> defaultJsons){
        Path path = Utilities.getAssetPath(LycaniteAdditions.class, LycaniteAdditions.MODID, "dungeons/" + type);
        this.loadJsonObjects(gson, path, defaultJsons, "name", null);
    }
}
