package net.timeworndevs.quantumadds.entities.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;
import net.timeworndevs.quantumadds.util.RadiationData;
import org.jetbrains.annotations.Nullable;

public class MonstrocityEntity extends AnimalEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta*6.0f, 1.0f): 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }


    public MonstrocityEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createMonstrocityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, .2f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 0.5f));
        this.goalSelector.add(4, new WanderNearTargetGoal(this, 0.4f, 20));
        this.goalSelector.add(5, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.3f, 0.001f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MONSTROCITY.create(world);
    }

    private void updateAnimation() {
        if (this.idleAnimationTimeout <= 0 ) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }
    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            updateAnimation();
        }

        if (this.getTarget()!=null) {
            if (this.getTarget() instanceof PlayerEntity) {
                if (! (this.getTarget().getRecentDamageSource() == null)) {
                    if (this.getTarget().getRecentDamageSource().getSource() == this && this.isAlive()) { //check if entity attacked the player
                        RadiationData.addRad(((IEntityDataSaver) this.getTarget()), (String) Quantum.new_radiation_types.keySet().toArray()
                                [(int) Math.round(Math.abs(Math.random()*(Quantum.new_radiation_types.size()-1)))], (int) (2*(Math.random()*2400))); //add random amount of random radiation kind
                        //this.escape();
                        this.kill(); // kill the goober
                    }
                }

            }

        }
    }





    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CAT_STRAY_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }
}
