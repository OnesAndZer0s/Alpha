package com.onesandzer0s.alpha.mixin;


import com.onesandzer0s.alpha.common.registry.AFluids;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class AlphaLavaIsLava {


   @Shadow
   protected boolean firstTick;

   @Shadow
   protected Object2DoubleMap<FluidType> forgeFluidTypeHeight;


   @Overwrite
    public boolean isInLava() {
         return !this.firstTick && (this.forgeFluidTypeHeight.getDouble(ForgeMod.LAVA_TYPE.get()) > 0.0||this.forgeFluidTypeHeight.getDouble(AFluids.ALPHA_LAVA_FLUID_TYPE.get()) > 0.0);
    }
}
