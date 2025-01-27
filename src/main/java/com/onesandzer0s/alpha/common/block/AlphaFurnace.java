package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.block.entity.AlphaFurnaceBlockEntity;
import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AlphaFurnace extends AbstractFurnaceBlock {

    public AlphaFurnace( Properties properties ) {
        super( properties );
    }

   public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
      return new AlphaFurnaceBlockEntity(pPos, pState);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker( Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
      return createFurnaceTicker(pLevel, pBlockEntityType, ABlockEntityTypes.FURNACE.get());
   }

   protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
      BlockEntity $$3 = pLevel.getBlockEntity(pPos);
      if ($$3 instanceof AlphaFurnaceBlockEntity) {
         pPlayer.openMenu((MenuProvider)$$3);
         pPlayer.awardStat(Stats.INTERACT_WITH_FURNACE);
      }

   }

   public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
      if ((Boolean)pState.getValue(LIT)) {
         double $$4 = (double)pPos.getX() + 0.5;
         double $$5 = (double)pPos.getY();
         double $$6 = (double)pPos.getZ() + 0.5;
         if (pRandom.nextDouble() < 0.1) {
            pLevel.playLocalSound($$4, $$5, $$6, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
         }

         Direction $$7 = (Direction)pState.getValue(FACING);
         Direction.Axis $$8 = $$7.getAxis();
         double $$9 = 0.52;
         double $$10 = pRandom.nextDouble() * 0.6 - 0.3;
         double $$11 = $$8 == Direction.Axis.X ? (double)$$7.getStepX() * 0.52 : $$10;
         double $$12 = pRandom.nextDouble() * 6.0 / 16.0;
         double $$13 = $$8 == Direction.Axis.Z ? (double)$$7.getStepZ() * 0.52 : $$10;
         pLevel.addParticle(ParticleTypes.SMOKE, $$4 + $$11, $$5 + $$12, $$6 + $$13, 0.0, 0.0, 0.0);
         pLevel.addParticle(ParticleTypes.FLAME, $$4 + $$11, $$5 + $$12, $$6 + $$13, 0.0, 0.0, 0.0);
      }
   }
}
