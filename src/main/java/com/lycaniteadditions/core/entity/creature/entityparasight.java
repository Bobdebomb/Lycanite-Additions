package com.lycaniteadditions.core.entity.creature;

import com.lycanitesmobs.core.entity.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class entityparasight extends TameableCreatureEntity implements IMob {

    private int strafeTime = 60;

    // ==================================================
    //                    Constructor
    // ==================================================
    public entityparasight(World world) {
        super(world);

        // Setup:
        this.attribute = EnumCreatureAttribute.UNDEFINED;
        this.hasAttackSound = true;
        this.spawnsInWater = true;
        this.trueSight = true;
        this.setupMob();

        // No Block Collision:
        this.noClip = true;
    }

    // ========== Init AI ==========
    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(this.nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(true));
    }


    // ==================================================
    //                      Updates
    // ==================================================
    // ========== Living Update ==========
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        // Random Strafing:
        if(!this.getEntityWorld().isRemote && this.hasAttackTarget()) {
            if(this.strafeTime-- <= 0) {
                this.strafeTime = 60 + this.getRNG().nextInt(40);
                this.strafe(this.getRNG().nextBoolean() ? -1F : 1F, 0D);
            }
        }

    }


    // ==================================================
    //                     Abilities
    // ==================================================
    @Override
    public boolean isFlying() { return true; }

    @Override
    public boolean useDirectNavigator() {
        return true;
    }

    @Override
    public boolean canEntityBeSeen(Entity target) {
        return true;
    }


    // ==================================================
    //                     Pet Control
    // ==================================================
    public boolean petControlsEnabled() { return true; }

    // ==================================================
    //                     Equipment
    // ==================================================
    @Override
    public int getNoBagSize() { return 0; }
    @Override
    public int getBagSize() { return this.creatureInfo.bagSize; }


    // ==================================================
    //                     Immunities
    // ==================================================
    @Override
    public boolean isDamageTypeApplicable(String type, DamageSource source, float damage) {
        if(type.equals("inWall")) return false;
        return super.isDamageTypeApplicable(type, source, damage);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}
