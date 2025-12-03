package com.lycaniteadditions.client.model;

import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.client.model.template.ModelTemplateQuadruped;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class modelbloodveld extends ModelTemplateQuadruped {

    public modelbloodveld() {
        this(1.0F);
    }

    public modelbloodveld(float shadowSize) {
        this.initModel("bloodveld", LycanitesMobs.modInfo, "entity/bloodveld");

        this.trophyScale = 0.8F;
        this.trophyOffset = new float[] {0.0F, -0.2F, 0.0F};
    }

    @Override
    public void animatePart(String partName, EntityLiving entity, float time, float distance, float loop, float lookY, float lookX, float scale) {
        super.animatePart(partName, entity, time, distance, loop, lookY, lookX, scale);

        // Attack:
        if(partName.equals("legleftfront") || partName.equals("legrightfront")) {
            rotate(-75.0F * this.getAttackProgress(), 0.0F, 0.0F);
        }
    }
}
