package com.onesandzer0s.alpha.mixin;

import com.onesandzer0s.alpha.common.registry.AFluids;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiPredicate;

@Mixin(Entity.class)
public abstract class AlphaWaterSounds {

   @Shadow public abstract boolean isInFluidType( BiPredicate<FluidType, Double> predicate, boolean forAllTypes );
// getFluidTypeHeight

   @ModifyArg(method="doWaterSplashEffect", at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
    public SoundEvent doWaterSplashEffect( SoundEvent pSound ) {
        if (this.isInFluidType( (fluidType, height) -> fluidType == AFluids.ALPHA_WATER_FLUID_TYPE.get(), true) ){
            return ASounds.SPLASH.get();
        }
        return pSound;
    }


    @Inject(method="Lnet/minecraft/world/entity/Entity;updateFluidHeightAndDoFluidPushing(Lnet/minecraft/tags/TagKey;D)Z", at=@At("RETURN"), cancellable = true)
    public void updateFluidHeightAndDoFluidPushing( TagKey<Fluid> pFluidTag, double pMotionScale, CallbackInfoReturnable<Boolean> cir ) {
       if (pFluidTag == FluidTags.WATER) {
          cir.setReturnValue(cir.getReturnValue() || this.isInFluidType((fluidType, height) -> fluidType == AFluids.ALPHA_WATER_FLUID_TYPE.get(),false));
       } else if (pFluidTag == FluidTags.LAVA) {
             cir.setReturnValue(cir.getReturnValue() || this.isInFluidType((fluidType, height) -> fluidType ==AFluids.ALPHA_LAVA_FLUID_TYPE.get(),false));
          }
    }

}
