package com.lycaniteadditions.client.model;

import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.client.model.template.ModelTemplateBiped;


public class modelbeargon extends ModelTemplateBiped {

    // ==================================================
    //                    Constructors
    // ==================================================
    public modelbeargon() {
        this(1.0F);
    }

    public modelbeargon(float shadowSize) {
        // Load Model:
        this.initModel("beargon", LycanitesMobs.modInfo, "entity/beargon");

        // Trophy:
        this.trophyScale = 0.8F;
        this.trophyOffset = new float[] {0.0F, 0.0F, -0.4F};
        this.trophyMouthOffset = new float[] {0.0F, -0.25F, 0.0F};
    }
}
