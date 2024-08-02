package com.onesandzer0s.alpha.common.entity;

import com.onesandzer0s.alpha.common.registry.AEntities;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ZombiePigman extends ZombifiedPiglin {

   public ZombiePigman( EntityType<? extends ZombiePigman> pEntityType, Level pLevel ) {
      super(pEntityType, pLevel);
   }

   public ZombiePigman(Level pLevel) {
      this(AEntities.ZOMBIE_PIGMAN.get(), pLevel);
   }


   private void playAngerSound() {
      this.playSound(SoundEvents.ZOMBIFIED_PIGLIN_ANGRY, this.getSoundVolume() * 2.0F, this.getVoicePitch() * 1.8F);
   }

   protected SoundEvent getAmbientSound() {
      return this.isAngry() ? SoundEvents.ZOMBIFIED_PIGLIN_ANGRY : SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT;
   }

   protected SoundEvent getHurtSound( DamageSource pDamageSource) {
      return SoundEvents.ZOMBIFIED_PIGLIN_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ZOMBIFIED_PIGLIN_DEATH;
   }

   protected void populateDefaultEquipmentSlots( RandomSource pRandom, DifficultyInstance pDifficulty) {
      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(AItems.GOLD_SWORD.get()));
   }

}