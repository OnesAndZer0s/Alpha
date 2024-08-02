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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.Optional;


public abstract class AlphaLavaFluid extends FlowingFluid
{
   @Override
   public Fluid getFlowing() {
      return AFluids.FLOWING_ALPHA_LAVA.get();
   }

   @Override
   public Fluid getSource() {
      return AFluids.ALPHA_LAVA.get();
   }

   @Override
   public Item getBucket() {
      return AItems.LAVA_BUCKET.get();
   }

   @Override
   public void animateTick( Level p_230606_, BlockPos p_230607_, FluidState p_230608_, RandomSource p_230609_)
   {

      if (p_230609_.nextInt(256) == 0)
      {
         p_230606_.playLocalSound((double)p_230607_.getX() + 0.5D, (double)p_230607_.getY() + 0.5D, (double)p_230607_.getZ() + 0.5D, ASounds.LAVA_AMBIENT.get(), SoundSource.BLOCKS, p_230609_.nextFloat() * 0.25F + 0.75F, p_230609_.nextFloat() + 0.5F, false);
      }
   }

   public void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
      if (pLevel.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
         int i = pRandom.nextInt(3);
         if (i > 0) {
            BlockPos blockpos = pPos;

            for(int j = 0; j < i; ++j) {
               blockpos = blockpos.offset(pRandom.nextInt(3) - 1, 1, pRandom.nextInt(3) - 1);
               if (!pLevel.isLoaded(blockpos)) {
                  return;
               }

               BlockState blockstate = pLevel.getBlockState(blockpos);
               if (blockstate.isAir()) {
                  if (this.hasFlammableNeighbours(pLevel, blockpos)) {
                     pLevel.setBlockAndUpdate(blockpos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, blockpos, pPos, Blocks.FIRE.defaultBlockState()));
                     return;
                  }
               } else if (blockstate.blocksMotion()) {
                  return;
               }
            }
         } else {
            for(int k = 0; k < 3; ++k) {
               BlockPos blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, 0, pRandom.nextInt(3) - 1);
               if (!pLevel.isLoaded(blockpos1)) {
                  return;
               }

               if (pLevel.isEmptyBlock(blockpos1.above()) && this.isFlammable(pLevel, blockpos1, Direction.UP)) {
                  pLevel.setBlockAndUpdate(blockpos1.above(), ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, blockpos1.above(), pPos, Blocks.FIRE.defaultBlockState()));
               }
            }
         }
      }

   }

   private boolean hasFlammableNeighbours(LevelReader pLevel, BlockPos pPos) {
      Direction[] var3 = Direction.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Direction direction = var3[var5];
         if (this.isFlammable(pLevel, pPos.relative(direction), direction.getOpposite())) {
            return true;
         }
      }

      return false;
   }

   /** @deprecated */
   @Deprecated
   private boolean isFlammable(LevelReader pLevel, BlockPos pPos) {
      return pPos.getY() >= pLevel.getMinBuildHeight() && pPos.getY() < pLevel.getMaxBuildHeight() && !pLevel.hasChunkAt(pPos) ? false : pLevel.getBlockState(pPos).ignitedByLava();
   }

   private boolean isFlammable(LevelReader level, BlockPos pos, Direction face) {
      return pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight() && !level.hasChunkAt(pos) ? false : level.getBlockState(pos).isFlammable(level, pos, face);
   }

   @Nullable
   @Override
   public ParticleOptions getDripParticle()
   {
      return ParticleTypes.DRIPPING_LAVA;
   }

   protected boolean canConvertToSource(Level pLevel) {
      return pLevel.getGameRules().getBoolean(GameRules.RULE_LAVA_SOURCE_CONVERSION);
   }
   @Override
   protected void beforeDestroyingBlock( LevelAccessor p_76450_, BlockPos p_76451_, BlockState p_76452_)
   {
      BlockEntity blockentity = p_76452_.hasBlockEntity() ? p_76450_.getBlockEntity(p_76451_) : null;
      Block.dropResources(p_76452_, p_76450_, p_76451_, blockentity);
   }

   @Override
   public int getSlopeFindDistance(LevelReader pLevel) {
      return pLevel.dimensionType().ultraWarm() ? 4 : 2;
   }

   @Override
   public BlockState createLegacyBlock(FluidState p_76466_)
   {
      return ABlocks.ALPHA_LAVA.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_76466_)));
   }

   @Override
   public boolean isSame(Fluid p_76456_) {
      return p_76456_ == AFluids.ALPHA_LAVA.get() || p_76456_ == AFluids.FLOWING_ALPHA_LAVA.get();
   }

   @Override
   public int getDropOff(LevelReader pLevel) {
      return pLevel.dimensionType().ultraWarm() ? 1 : 2;
   }

   @Override
   public net.minecraftforge.fluids.FluidType getFluidType()
   {
      return AFluids.ALPHA_LAVA_FLUID_TYPE.get();
   }


   private void fizz(LevelAccessor pLevel, BlockPos pPos) {
      pLevel.levelEvent(1501, pPos, 0);
   }

   protected void spreadTo(LevelAccessor pLevel, BlockPos pPos, BlockState pBlockState, Direction pDirection, FluidState pFluidState) {
      if (pDirection == Direction.DOWN) {
         FluidState fluidstate = pLevel.getFluidState(pPos);
         if (this.is(FluidTags.LAVA) && fluidstate.is(FluidTags.WATER)) {
            if (pBlockState.getBlock() instanceof LiquidBlock) {
               pLevel.setBlock(pPos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, Blocks.STONE.defaultBlockState()), 3);
            }

            this.fizz(pLevel, pPos);
            return;
         }
      }

      super.spreadTo(pLevel, pPos, pBlockState, pDirection, pFluidState);
   }

   protected boolean isRandomlyTicking() {
      return true;
   }

   @Override
   public int getTickDelay(LevelReader pLevel) {
      return pLevel.dimensionType().ultraWarm() ? 10 : 30;
   }

   public boolean canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection) {
      return pFluidState.getHeight(pBlockReader, pPos) >= 0.44444445F && pFluid.is(FluidTags.WATER);
   }

   public int getSpreadDelay(Level pLevel, BlockPos pPos, FluidState pCurrentState, FluidState pNewState) {
      int i = this.getTickDelay(pLevel);
      if (!pCurrentState.isEmpty() && !pNewState.isEmpty() && !(Boolean)pCurrentState.getValue(FALLING) && !(Boolean)pNewState.getValue(FALLING) && pNewState.getHeight(pLevel, pPos) > pCurrentState.getHeight(pLevel, pPos) && pLevel.getRandom().nextInt(4) != 0) {
         i *= 4;
      }

      return i;
   }

   @Override
   protected float getExplosionResistance() {
      return 100.0F;
   }

   @Override
   public Optional<SoundEvent> getPickupSound() {
      return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
   }

   public static class Flowing extends AlphaLavaFluid
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

   public static class Source extends AlphaLavaFluid
   {
      public int getAmount(FluidState p_76485_) {
         return 8;
      }

      public boolean isSource(FluidState p_76483_) {
         return true;
      }
   }
}