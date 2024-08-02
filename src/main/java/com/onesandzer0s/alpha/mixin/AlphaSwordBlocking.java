package com.onesandzer0s.alpha.mixin;


import com.onesandzer0s.alpha.common.item.AlphaSword;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AlphaSwordBlocking {


   @Inject(method="Lnet/minecraft/world/entity/LivingEntity;isBlocking()Z", at=@At("HEAD"), cancellable=true)
   protected void isBlocking( CallbackInfoReturnable<Boolean> cir ) {
     if ( ((LivingEntity)(Object)this).isUsingItem() && !((LivingEntity)(Object)this).getUseItem().isEmpty() && ((LivingEntity)(Object)this).getUseItem().getItem() instanceof AlphaSword ) {
        cir.setReturnValue(false);
     }
   }


   @Inject(method="Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at=@At(value= "INVOKE",target="Lnet/minecraft/world/entity/WalkAnimationState;setSpeed(F)V"), cancellable=true)
   protected void hurt( DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir ) {
     if ( ((LivingEntity)(Object)this).isUsingItem() && ! ((LivingEntity)(Object)this).getUseItem().isEmpty() && ((LivingEntity)(Object)this).getUseItem().getItem() instanceof AlphaSword ) {
        cir.cancel();
     }
   }
}
