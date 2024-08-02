package com.onesandzer0s.alpha.mixin;


import com.onesandzer0s.alpha.common.block.AlphaDispenser;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultDispenseItemBehavior.class)
public class AlphaDefaultDispenserBehaviourMixin {

   @Inject(method = "playSound", at = @At("HEAD"), cancellable = true)
   private void playSound( BlockSource pSource, CallbackInfo ci ) {
      if ( pSource.getBlockState().getBlock() instanceof AlphaDispenser ) {
         pSource.getLevel().playSound(null, pSource.getPos(), ASounds.CLICK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
         ci.cancel();
      }
   }
}
