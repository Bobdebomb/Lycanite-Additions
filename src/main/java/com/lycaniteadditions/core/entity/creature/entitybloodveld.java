package com.lycaniteadditions.core.entity.creature;

import com.google.common.base.Predicate;
import com.lycanitesmobs.core.entity.BaseCreatureEntity;
import com.lycanitesmobs.core.entity.RideableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.PlayerControlGoal;
import com.lycanitesmobs.core.entity.goals.targeting.FindAttackTargetGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.List;

public class entitybloodveld extends RideableCreatureEntity {

    PlayerControlGoal playerControlAI;

    // ==================================================
    //                    Constructor
    // ==================================================
    public entitybloodveld(World world) {
        super(world);

        // Setup:
        this.attribute = EnumCreatureAttribute.UNDEAD;
        this.hasAttackSound = true;
        this.spreadFire = true;
        this.setupMob();

        this.stepHeight = 1.0F;
    }

    // ========== Init AI ==========
    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(this.nextCombatGoalIndex++, new AttackMeleeGoal(this).setTargetClass(EntityPigZombie.class).setSpeed(1.5D).setDamageScale(8.0D).setRange(2.5D));
        this.tasks.addTask(this.nextCombatGoalIndex++, new AttackMeleeGoal(this).setTargetClass(EntityEnderman.class).setSpeed(1.5D).setDamageScale(8.0D).setRange(2.5D));
        this.tasks.addTask(this.nextCombatGoalIndex++, new AttackMeleeGoal(this).setSpeed(1.5D));

        this.targetTasks.addTask(this.nextFindTargetIndex++, new FindAttackTargetGoal(this).addTargets(EntityPigZombie.class));
        this.targetTasks.addTask(this.nextFindTargetIndex++, new FindAttackTargetGoal(this).addTargets(EntityEnderman.class));
    }

    @Override
    public boolean shouldCreatureGroupFlee(EntityLivingBase target) {
        return false;
    }


    // ==================================================
    //                      Updates
    // ==================================================
    // ========== Living Update ==========
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        // Become a farmed animal if removed from the Nether to another dimension, prevents natural despawning.
        if(this.getEntityWorld().getWorldType().getId() != -1)
            this.setFarmed();
    }

    public void riderEffects(EntityLivingBase rider) {
        if(rider.isPotionActive(MobEffects.WITHER))
            rider.removePotionEffect(MobEffects.WITHER);
        if(rider.isBurning())
            rider.setFire(0);
    }


    // ==================================================
    //                   Mount Ability
    // ==================================================
    @Override
    public void mountAbility(Entity rider) {
        if(this.getEntityWorld().isRemote)
            return;

        if(this.abilityToggled)
            return;
        if(this.getStamina() < this.getStaminaCost())
            return;
        this.specialAttack();
        this.applyStaminaCost();
    }

    public float getStaminaCost() {
        return 20;
    }

    public int getStaminaRecoveryWarmup() {
        return 5 * 20;
    }

    public float getStaminaRecoveryMax() {
        return 1.0F;
    }


    // ==================================================
    //                      Attacks
    // ==================================================
    // ========== Melee Attack ==========
    @Override
    public boolean attackMelee(Entity target, double damageScale) {
        if (!super.attackMelee(target, damageScale))
            return false;

        return false;
    }

    // ========== Special Attack ==========
    public void specialAttack() {
        // Withering Roar:
        double distance = 5.0D;
        List<EntityLivingBase> possibleTargets = this.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(distance, distance, distance), (Predicate<EntityLivingBase>) possibleTarget -> {
            if(!possibleTarget.isEntityAlive()
                    || possibleTarget == entitybloodveld.this
                    || entitybloodveld.this.isRidingOrBeingRiddenBy(possibleTarget)
                    || entitybloodveld.this.isOnSameTeam(possibleTarget)
                    || !entitybloodveld.this.canAttackClass(possibleTarget.getClass())
                    || !entitybloodveld.this.canAttackEntity(possibleTarget))
                return false;
            return true;
        });
        if(!possibleTargets.isEmpty()) {
            for(EntityLivingBase possibleTarget : possibleTargets) {
                boolean doDamage = true;
                if(this.getRider() instanceof EntityPlayer) {
                    if(MinecraftForge.EVENT_BUS.post(new AttackEntityEvent((EntityPlayer)this.getRider(), possibleTarget))) {
                        doDamage = false;
                    }
                }
                if(doDamage) {
                    possibleTarget.addPotionEffect(new PotionEffect(MobEffects.WITHER, 10 * 20, 0));
                }
            }
        }
        this.playAttackSound();
        this.triggerAttackCooldown();
    }


    // ==================================================
    //                     Abilities
    // ==================================================
    public boolean canBeTempted() {
        return this.isChild();
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
    public float getFallResistance() {
        return 10;
    }


    // ==================================================
    //                     Breeding
    // ==================================================
    // ========== Breeding Item ==========
    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return false; // Breeding is triggered by attacking specific mobs instead!
    }
}
