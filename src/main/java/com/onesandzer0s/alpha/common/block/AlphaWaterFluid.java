package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AFluids;
import com.onesandzer0s.alpha.common.registry.AItems;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;
import java.util.Optional;


public abstract class AlphaWaterFluid extends FlowingFluid
{
   @Override
   public Fluid getFlowing() {
      return AFluids.FLOWING_ALPHA_WATER.get();
   }

   @Override
   public Fluid getSource() {
      return AFluids.ALPHA_WATER.get();
   }

   @Override
   public Item getBucket() {
      return AItems.WATER_BUCKET.get();
   }

   @Override
   public void animateTick( Level p_230606_, BlockPos p_230607_, FluidState p_230608_, RandomSource p_230609_)
   {
      if (p_230609_.nextInt(256) == 0)
      {
         p_230606_.playLocalSound((double)p_230607_.getX() + 0.5D, (double)p_230607_.getY() + 0.5D, (double)p_230607_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, p_230609_.nextFloat() * 0.25F + 0.75F, p_230609_.nextFloat() + 0.5F, false);
      }
   }

   @Nullable
   @Override
   public ParticleOptions getDripParticle()
   {
      return ParticleTypes.DRIPPING_WATER;
   }

   protected boolean canConvertToSource(Level pLevel) {
      return pLevel.getGameRules().getBoolean(GameRules.RULE_WATER_SOURCE_CONVERSION);
   }

   @Override
   protected void beforeDestroyingBlock( LevelAccessor p_76450_, BlockPos p_76451_, BlockState p_76452_)
   {
      BlockEntity blockentity = p_76452_.hasBlockEntity() ? p_76450_.getBlockEntity(p_76451_) : null;
      Block.dropResources(p_76452_, p_76450_, p_76451_, blockentity);
   }

   @Override
   public int getSlopeFindDistance( LevelReader p_76464_) {
      return 4;
   }

   @Override
   public BlockState createLegacyBlock(FluidState p_76466_)
   {
      return ABlocks.ALPHA_WATER.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_76466_)));
   }

   @Override
   public boolean isSame(Fluid p_76456_) {
      return p_76456_ == AFluids.ALPHA_WATER.get() || p_76456_ == AFluids.FLOWING_ALPHA_WATER.get();
   }


   @Override
   public int getDropOff(LevelReader p_76469_) {
      return 1;
   }

   @Override
   public int getTickDelay(LevelReader p_76454_) {
      return 5;
   }

   @Override
   public boolean canBeReplacedWith( FluidState p_76458_, BlockGetter p_76459_, BlockPos p_76460_, Fluid p_76461_, Direction p_76462_)
   {
      return p_76462_ == Direction.DOWN && !p_76461_.is(FluidTags.WATER);
   }

   @Override
   protected float getExplosionResistance() {
      return 100.0F;
   }

   @Override
   public Optional<SoundEvent> getPickupSound() {
      return Optional.of(SoundEvents.BUCKET_FILL);
   }

   @Override
   public net.minecraftforge.fluids.FluidType getFluidType()
   {
      return AFluids.ALPHA_WATER_FLUID_TYPE.get();
   }

   public static class Flowing extends AlphaWaterFluid
   {
      protected void createFluidStateDefinition( StateDefinition.Builder<Fluid, FluidState> p_76476_)
      {
         super.createFluidStateDefinition(p_76476_);
         p_76476_.add(LEVEL);
      }

      public int getAmount(FluidState p_76480_) {
         return p_76480_.getValue(LEVEL);
      }

      public boolean isSource(FluidState p_76478_) {
         return false;
      }
   }

   public static class Source extends AlphaWaterFluid
   {
      public int getAmount(FluidState p_76485_) {
         return 8;
      }

      public boolean isSource(FluidState p_76483_) {
         return true;
      }
   }
}