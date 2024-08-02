package com.onesandzer0s.alpha.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.onesandzer0s.alpha.common.block.AlphaDispenser;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractProjectileDispenseBehavior.class)
public abstract class AlphaAbstractProjectileDispenseBehaviorMixin {


   @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V"))
   private void injected( BlockSource pSource, ItemStack pStack, CallbackInfoReturnable<ItemStack> cir, @Local Projectile localRef ) {
        if ( pSource.getBlockState().getBlock() instanceof AlphaDispenser ) {
           if ( localRef instanceof Arrow arrow )
              arrow.setSoundEvent(ASounds.ARROW.get());
        }
   }

   @Inject(method = "playSound", at = @At("HEAD"), cancellable = true)
   private void playSound( BlockSource pSource, CallbackInfo ci ) {
      if ( pSource.getBlockState().getBlock() instanceof AlphaDispenser ) {
         pSource.getLevel().playSound(null, pSource.getPos(), ASounds.BOW_SHOOT.get(), SoundSource.BLOCKS, 1.0F, 1.0F / ( pSource.getLevel().getRandom().nextFloat() * 0.4F + 1.2F ) + 0.5f);
         ci.cancel();
      }

   }
}
