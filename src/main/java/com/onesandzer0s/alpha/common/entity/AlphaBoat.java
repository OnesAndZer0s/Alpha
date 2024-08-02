package com.onesandzer0s.alpha.common.entity;

import com.google.common.collect.Lists;
import com.onesandzer0s.alpha.common.registry.AEntities;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.extensions.IForgeBoat;
import net.minecraftforge.fluids.FluidType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.IntFunction;


public class AlphaBoat extends Boat {
   private double speedMultiplier;
   private int boatPosRotationIncrements;
   private Vec3 velocity;

   public AlphaBoat( EntityType<? extends Boat> pEntityType, Level pLevel ) {
      super(pEntityType, pLevel);
   }

   public AlphaBoat( Level pLevel, double pX, double pY, double pZ ) {
      this(AEntities.BOAT.get(), pLevel);
      this.speedMultiplier = 0.07D;
      this.setPos(pX, pY, pZ);
      this.xo = pX;
      this.yo = pY;
      this.zo = pZ;
   }

   protected int getMaxPassengers() {
      return 1;
   }

   /**
    * Returns the Y offset from the entity's position for any entity riding this one.
    */
   public double getPassengersRidingOffset() {
      return -0.2D;
   }

   public Item getDropItem() {
      return AItems.BOAT.get();
   }


   @Override
   public void floatBoat() {
      double grav = -0.03999999910593033;
      double d1 = this.isNoGravity() ? 0.0 :grav;
      double d2 = 0.0;
      this.invFriction = 0.05F;
      if ( this.oldStatus == Boat.Status.IN_AIR && this.status != Boat.Status.IN_AIR && this.status != Boat.Status.ON_LAND ) {
         this.waterLevel = this.getY(1.0);
         this.setPos(this.getX(), (double) ( this.getWaterLevelAbove() - this.getBbHeight() ) + 0.101, this.getZ());
         this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.0, 1.0));
         this.lastYd = 0.0;
         this.status = Boat.Status.IN_WATER;
      }
      else {
         if ( this.status == Boat.Status.IN_WATER ) {
            d2 = ( this.waterLevel - this.getY() ) / (double) this.getBbHeight();
            this.invFriction = 0.9900000095367432F;
         }
         else if ( this.status == Boat.Status.UNDER_FLOWING_WATER ) {
            d1 = -7.0E-4;
            this.invFriction = 0.9F;
         }
         else if ( this.status == Boat.Status.UNDER_WATER ) {
            d2 = 0.009999999776482582;
            this.invFriction = 0.45F;
         }
         else if ( this.status == Boat.Status.IN_AIR ) {
            this.invFriction = 0.9F;
         }
         else if ( this.status == Boat.Status.ON_LAND ) {
            this.invFriction = this.landFriction;
            if ( this.getControllingPassenger() instanceof Player ) {
               this.landFriction /= 2.0F;
            }
         }

         Vec3 vec3 = this.getDeltaMovement();
         this.setDeltaMovement(vec3.x * (double) this.invFriction, vec3.y + d1, vec3.z * (double) this.invFriction);
         this.deltaRotation *= this.invFriction;
         if ( d2 > 0.0 ) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, ( vec31.y + d2 * 0.06153846016296973 ) * 0.75, vec31.z);
         }
      }
   }

   @Override
   public void controlBoat() {
      if ( this.isVehicle() ) {
         float f = 0.0F;
//         if (this.inputLeft) {
//            --this.deltaRotation;
//         }
//
//         if (this.inputRight) {
//            ++this.deltaRotation;
//         }
//
//         if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
//            f += 0.005F;
//         }
//
//         this.setYRot(this.getYRot() + this.deltaRotation);

         if ( this.getControllingPassenger() != null ) {

            Vec3 vec3 = this.getDeltaMovement();
            LivingEntity le = this.getControllingPassenger();

            float rot = le.getYHeadRot();

            float accen = 0.01f;

            if ( this.inputUp ) {
               vec3 = vec3.add(
                       (double) ( Mth.sin(-rot * 0.017453292F) * accen ),
                       0.0,
                       (double) ( Mth.cos(rot * 0.017453292F) * accen )
               );
            }


            if ( (this.inputLeft || this.inputRight) && (this.inputLeft != this.inputRight)) {
               float angleRot = rot + ( this.inputLeft ? -90 : 90 );

               vec3 = vec3.add(
                       (double) ( Mth.sin(-angleRot * 0.017453292F) * accen/2 ),
                       0.0,
                       (double) ( Mth.cos(angleRot * 0.017453292F) * accen/2 )
               );
            }

            this.setDeltaMovement(vec3);
            // get rotation of delta movement

            double speed = vec3.horizontalDistanceSqr();
            if (speed> 0.001D) {
               float angle = (float) ( Math.atan2(vec3.z, vec3.x) * ( 180 / Math.PI ) ) + 270;

               // slowly rotate
               this.setYRot(this.getYRot() + Mth.wrapDegrees(angle - this.getYRot()) * (float)vec3.horizontalDistanceSqr()*2.5f);
            }

            // 0.2975D
            if (speed > 0.15D)
            {
               double d2 = Math.cos((double)this.getYRot() * Math.PI / 180.0D+90);
               double d4 = Math.sin((double)this.getYRot() * Math.PI / 180.0D+90);

               for (int k = 0; (double)k < 1.0D + speed * 60.0D; ++k)
               {
                  double d5 = (double)(this.random.nextFloat() * 2.0F - 1.0F);
                  double d6 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7D;

                  if (this.random.nextBoolean())
                  {
                     double d7 = this.getX() - d2 * d5 * 0.8D + d4 * d6;
                     double d8 = this.getZ() - d4 * d5 * 0.8D - d2 * d6;
                     this.level().addParticle(ParticleTypes.SPLASH, d7, this.getY()+0.4, d8, vec3.x, vec3.y+1, vec3.z);
                  }
                  else
                  {
//                     double d24 = this.getX() + d2 + d4 * d5 * 0.7D;
//                     double d25 = this.getZ() + d4 - d2 * d5 * 0.7D;
//                     this.level().addParticle(ParticleTypes.SPLASH, d24, this.getY()+0.4, d25, vec3.x, vec3.y+1, vec3.z);
                  }
               }
            }
         }
      }
   }

   @Override
   protected void checkFallDamage( double pY, boolean pOnGround, BlockState pState, BlockPos pPos ) {
      this.lastYd = this.getDeltaMovement().y;
      if ( !this.isPassenger() ) {
         if ( pOnGround ) {
            if ( this.fallDistance > 3.0F ) {
               if ( this.status != Boat.Status.ON_LAND ) {
                  this.resetFallDistance();
                  return;
               }

               this.causeFallDamage(this.fallDistance, 1.0F, this.damageSources().fall());
               if ( !this.level().isClientSide && !this.isRemoved() ) {
                  this.kill();
                  if ( this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS) ) {
                     int j;
                     for ( j = 0; j < 3; ++j ) {
                        this.spawnAtLocation(AItems.PLANKS.get());
                     }

                     for ( j = 0; j < 2; ++j ) {
                        this.spawnAtLocation(Items.STICK);
                     }
                  }
               }
            }

            this.resetFallDistance();
         }
         else if ( !this.canBoatInFluid(this.level().getFluidState(this.blockPosition().below())) && pY < 0.0 ) {
            this.fallDistance -= (float) pY;
         }
      }

   }

   protected void clampRotation( Entity pEntityToUpdate ) {}


}
