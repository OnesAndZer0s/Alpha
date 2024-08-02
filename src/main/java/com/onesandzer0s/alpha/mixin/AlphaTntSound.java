package com.onesandzer0s.alpha.mixin;

import com.onesandzer0s.alpha.common.entity.AlphaPrimedTnt;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class AlphaTntSound {
   @Shadow @Final private float radius;

   // look. this is a /really/ stupid hack.
   @ModifyArg(method= "finalizeExplosion(Z)V", at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V"))
    private SoundEvent playSound( SoundEvent pSound ) {
        if (this.radius == 4.001f ){
            return ASounds.EXPLODE.get();
        }
        return pSound;
    }
}
