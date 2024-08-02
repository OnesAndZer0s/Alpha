package com.onesandzer0s.alpha.common.block;

import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class AlphaLiquidBlock extends LiquidBlock implements BucketPickup {

   public AlphaLiquidBlock( Supplier<? extends FlowingFluid> p_54694_, Properties p_54695_ ) {
      super(p_54694_, p_54695_);
   }
}
