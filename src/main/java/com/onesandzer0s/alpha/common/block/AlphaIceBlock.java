package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AlphaIceBlock extends IceBlock {
   public AlphaIceBlock( Properties pProperties ) {
      super(pProperties);
   }

   public static BlockState meltsInto() {
      return ABlocks.ALPHA_WATER.get().defaultBlockState();
   }

   public void playerDestroy( Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
      super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
      if ( EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pStack) == 0) {
         if (pLevel.dimensionType().ultraWarm()) {
            pLevel.removeBlock(pPos, false);
            return;
         }

         BlockState $$6 = pLevel.getBlockState(pPos.below());
         if ($$6.blocksMotion() || $$6.liquid()) {
            pLevel.setBlockAndUpdate(pPos, meltsInto());
         }
      }

   }
}
