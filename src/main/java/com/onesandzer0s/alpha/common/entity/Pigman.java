package com.onesandzer0s.alpha.common.entity;

import com.onesandzer0s.alpha.common.registry.AEntities;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class Pigman extends PathfinderMob {
   private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);

   public Pigman( EntityType<? extends Pigman> pEntityType, Level pLevel ) {
      super(pEntityType, pLevel);
      this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
      this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
   }

   public Pigman(Level pLevel) {
      this(AEntities.PIGMAN.get(), pLevel);
   }



   protected void registerGoals() {
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
      this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
      this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
      this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
   }

   public InteractionResult mobInteract( Player pPlayer, InteractionHand pHand) {
      InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
         if (!interactionresult.consumesAction()) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            return InteractionResult.PASS;
         } else {
            return interactionresult;
         }
   }

   public void thunderHit( ServerLevel pLevel, LightningBolt pLightning) {
      if (pLevel.getDifficulty() != Difficulty.PEACEFUL && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this, EntityType.ZOMBIFIED_PIGLIN, ( timer) -> {})) {
         ZombiePigman zombiePigman = AEntities.ZOMBIE_PIGMAN.get().create(pLevel);
         if (zombiePigman != null) {
            zombiePigman.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(AItems.GOLD_SWORD.get()));
            zombiePigman.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            zombiePigman.setNoAi(this.isNoAi());
            zombiePigman.setBaby(this.isBaby());
            if (this.hasCustomName()) {
               zombiePigman.setCustomName(this.getCustomName());
               zombiePigman.setCustomNameVisible(this.isCustomNameVisible());
            }

            zombiePigman.setPersistenceRequired();
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, zombiePigman);
            pLevel.addFreshEntity(zombiePigman);
            this.discard();
         } else {
            super.thunderHit(pLevel, pLightning);
         }
      } else {
         super.thunderHit(pLevel, pLightning);
      }

   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.PIG_AMBIENT;
   }

   protected SoundEvent getHurtSound( DamageSource pDamageSource) {
      return SoundEvents.PIG_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.PIG_DEATH;
   }

   protected void playStepSound( BlockPos pPos, BlockState pBlock) {
      this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
   }


   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
   }

}