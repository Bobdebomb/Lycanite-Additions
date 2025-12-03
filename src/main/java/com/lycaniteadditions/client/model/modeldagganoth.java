package com.lycaniteadditions.client.model;

import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.client.model.template.ModelTemplateBiped;
import com.lycanitesmobs.core.entity.BaseCreatureEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class modeldagganoth extends ModelTemplateBiped {

	// ==================================================
  	//                    Constructors
  	// ==================================================
    public modeldagganoth() {
        this(1.0F);
    }

    public modeldagganoth(float shadowSize) {
    	// Load Model:
    	this.initModel("dagganoth", LycanitesMobs.modInfo, "entity/dagganoth");

    	// Looking:
		this.lookHeadScaleX = 0.8F;
		this.lookHeadScaleY = 0.8F;
		this.lookNeckScaleX = 0.2F;
		this.lookNeckScaleY = 0.2F;

        // Trophy:
        this.trophyScale = 0.8F;
        this.trophyOffset = new float[] {0.0F, -0.2F, 0.0F};
    }


    
    // ==================================================
   	//                    Animate Part
   	// ==================================================
    @Override
    public void animatePart(String partName, EntityLiving entity, float time, float distance, float loop, float lookY, float lookX, float scale) {
    	if(entity instanceof BaseCreatureEntity && entity.getControllingPassenger() != null) {
			time = time * 0.25F;
			distance = distance * 0.8F;
		}
    	super.animatePart(partName, entity, time, distance * 0.5F, loop, lookY, lookX, scale);

		if(partName.equals("mouth")) {
			this.rotate((float)-Math.toDegrees(MathHelper.cos(loop * 0.2F) * 0.1F), 0.0F, 0.0F);
		}
    }
}
