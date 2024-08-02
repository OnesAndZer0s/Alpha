package com.onesandzer0s.alpha.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class AlphaSword extends SwordItem {
   public AlphaSword( Tier pTier, Properties pProperties ) {
      super(pTier, 3, 0, pProperties);
   }

   public boolean canPerformAction( ItemStack stack, ToolAction toolAction) {
      return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction) || super.canPerformAction(stack, toolAction);
   }

   public int getUseDuration(ItemStack pStack) {
      return 72000;
   }


   public UseAnim getUseAnimation( ItemStack pStack) {
      return UseAnim.BLOCK;
   }

   public InteractionResultHolder<ItemStack> use( Level pLevel, Player pPlayer, InteractionHand pHand) {
      ItemStack itemstack = pPlayer.getItemInHand(pHand);
      pPlayer.startUsingItem(pHand);
      return InteractionResultHolder.consume(itemstack);
   }
}
