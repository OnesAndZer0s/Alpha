package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.block.entity.AlphaFurnaceBlockEntity;
import com.onesandzer0s.alpha.common.block.entity.AlphaNetherReactorBlockEntity;
import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class AlphaNetherReactor extends BaseEntityBlock {
   public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 2);

   public AlphaNetherReactor( BlockBehaviour.Properties pProperties ) {
      super(pProperties);
      this.registerDefaultState(this.stateDefinition.any().setValue(STATE, 0));
   }

   public BlockEntity newBlockEntity( BlockPos pPos, BlockState pState) {
      return new AlphaNetherReactorBlockEntity(pPos, pState);
   }

   public BlockState getStateForPlacement( BlockPlaceContext pContext) {
      return (BlockState)this.defaultBlockState().setValue(STATE, 0);
   }

   public RenderShape getRenderShape( BlockState pState ) {
      return RenderShape.MODEL;
   }

   public InteractionResult use( BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
      if (pLevel.isClientSide) {
         return InteractionResult.PASS;
      } else {
         if (!pPlayer.getMainHandItem().isEmpty()) return InteractionResult.PASS;
         BlockEntity $$3 = pLevel.getBlockEntity(pPos);
         if ($$3 instanceof AlphaNetherReactorBlockEntity anrbe ) {
            if (anrbe.canStart(pLevel, pPos)){
               anrbe.startReactor(pLevel, pPlayer, pState, pPos);
                return InteractionResult.SUCCESS;
            } else {
               return InteractionResult.CONSUME;
            }

         }
         return InteractionResult.CONSUME;
      }
   }

   @Override
   public void animateTick( BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom ) {
      if (pState.getValue(STATE) == 1) {
            pLevel.playLocalSound(pPos.getX()+pRandom.nextInt(12)-6, pPos.getY(), pPos.getZ()+pRandom.nextInt(12)-6, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 1.0F, 1.0F, false);
      }
   }

   public void onRemove( BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving ) {
      if ( !pState.is(pNewState.getBlock()) ) {
         BlockEntity blockentity = pLevel.getBlockEntity(pPos);
         if ( blockentity instanceof Container ) {
            Containers.dropContents(pLevel, pPos, (Container) blockentity);
            pLevel.updateNeighbourForOutputSignal(pPos, this);
         }

         super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
      }

   }


   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker( Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
      return createNetherReactorTicker(pLevel, pBlockEntityType, ABlockEntityTypes.NETHER_REACTOR.get());
   }

   @Nullable
   protected static <T extends BlockEntity> BlockEntityTicker<T> createNetherReactorTicker(Level pLevel, BlockEntityType<T> pServerType, BlockEntityType<? extends AlphaNetherReactorBlockEntity> pClientType) {
      return pLevel.isClientSide ? null : createTickerHelper(pServerType, pClientType, AlphaNetherReactorBlockEntity::serverTick);
   }


   protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> pBuilder) {
      pBuilder.add(STATE);
   }

}
