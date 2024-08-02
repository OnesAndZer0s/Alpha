package com.onesandzer0s.alpha.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AlphaFoodItem extends Item {
   public AlphaFoodItem( Properties pProperties ) {
      super(pProperties);
   }

   @Override
   public int getUseDuration( ItemStack pStack ) {
      return 1;
   }
}
