package com.onesandzer0s.alpha.common.entity;

import com.onesandzer0s.alpha.common.registry.AEntities;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AlphaMob extends PathfinderMob {
   public float rot;
   public float timeOffs;
   public float rotA;
   public AlphaMob( EntityType<? extends AlphaMob> pEntityType, Level pLevel ) {
      super(pEntityType, pLevel);
      this.rotA = (float)(Math.random() + 1.0D) * 0.01F;
      this.timeOffs = (float)Math.random() * 1239813.0F;
      this.rot = (float)(Math.random() * 3.141592653589793D * 2.0D);
   }

   public AlphaMob(Level pLevel) {
      this(AEntities.MOB.get(), pLevel);
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
   }

   @Nullable
   @Override
   protected SoundEvent getHurtSound( DamageSource pDamageSource ) {
      if (pDamageSource.is(DamageTypes.FALL)) {
         if (this.getDeltaMovement().y < -0.1D)
            return ASounds.MOB_SMALL_FALL.get();
         return ASounds.MOB_FALL.get();
      } else {
         return ASounds.MOB_HURT.get();
      }
   }

   @Override
   public void tick() {
      super.tick();

      float xa = 0.0F;
      float ya = 0.0F;
      this.rot += this.rotA;
      this.rotA = (float)((double)this.rotA * 0.99D);
      this.rotA = (float)((double)this.rotA + (Math.random() - Math.random()) * Math.random() * Math.random() * 0.07999999821186066D);
      float speed = (this.onGround() ? 0.1F : 0.02F);
      xa = (float)Math.sin((double)this.rot)*speed;
      ya = (float)Math.cos((double)this.rot)*speed;
      this.setYBodyRot(this.getYRot());

//      this.yBodyRot = this.getDeltaMovement()

//      this.setDeltaMovement(
//              this.getDeltaMovement().add((double)xa, 0,(double)ya).scale(speed));

      if (!this.level().isClientSide()) {
         if (!this.jumping && this.onGround() && Math.random() < 0.08D)
            this.jumpFromGround();

         if (this.getDeltaMovement().horizontalDistanceSqr() > 0.0D) {

//            this.setYRot((float) (rot * (double)(180F / (float)Math.PI) - 90.0F));
         this.setYRot((float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * (double)(180F / (float)Math.PI) - 90.0F));
         }
      }


      this.addDeltaMovement(new Vec3 (xa,0,ya));
   }

}