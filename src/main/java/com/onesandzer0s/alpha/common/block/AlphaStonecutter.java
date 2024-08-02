package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.block.entity.menu.AlphaStonecutterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class AlphaStonecutter extends Block {
   private static final Component CONTAINER_TITLE = Component.translatable("container.stonecutter");

   public AlphaStonecutter( Properties pProperties ) {
      super(pProperties);
   }

   @Nullable
   public MenuProvider getMenuProvider( BlockState pState, Level pLevel, BlockPos pPos) {
      return new SimpleMenuProvider(( p_57074_, p_57075_, p_57076_) -> new AlphaStonecutterMenu(p_57074_, p_57075_, ContainerLevelAccess.create(pLevel, pPos)), CONTAINER_TITLE);
   }

   public InteractionResult use( BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
      if (pLevel.isClientSide) {
         return InteractionResult.SUCCESS;
      } else {
         pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
         pPlayer.awardStat(Stats.INTERACT_WITH_STONECUTTER);
         return InteractionResult.CONSUME;
      }
   }
}
