package com.lycaniteadditions.client.model;

import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.client.model.template.ModelTemplateQuadruped;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class modelmbielu extends ModelTemplateQuadruped {

    public modelmbielu() {
        this(1.0F);
    }

    public modelmbielu(float shadowSize) {
        this.initModel("mbielu", LycanitesMobs.modInfo, "entity/mbielu");

        this.trophyScale = 0.6F;
        this.trophyOffset = new float[] {0.0F, 0.0F, -0.4F};
    }

}
