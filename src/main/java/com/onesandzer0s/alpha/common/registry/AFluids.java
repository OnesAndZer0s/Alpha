package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.block.AlphaLavaFluid;
import com.onesandzer0s.alpha.common.block.AlphaWaterFluid;
import com.onesandzer0s.alpha.common.fluid.AlphaLavaFluidType;
import com.onesandzer0s.alpha.common.fluid.AlphaWaterFluidType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class AFluids {
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, AlphaMod.MODID);
   public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, AlphaMod.MODID);


   public static final RegistryObject<FluidType> ALPHA_LAVA_FLUID_TYPE = FLUID_TYPES.register("alpha_lava_type", AlphaLavaFluidType::new);
   public static final RegistryObject<FlowingFluid> ALPHA_LAVA = FLUIDS.register("alpha_lava", AlphaLavaFluid.Source::new);
   public static final RegistryObject<FlowingFluid> FLOWING_ALPHA_LAVA = FLUIDS.register("flowing_alpha_lava",AlphaLavaFluid.Flowing::new);


   public static final RegistryObject<FluidType> ALPHA_WATER_FLUID_TYPE = FLUID_TYPES.register("alpha_water_type", AlphaWaterFluidType::new);
   public static final RegistryObject<FlowingFluid> ALPHA_WATER = FLUIDS.register("alpha_water", AlphaWaterFluid.Source::new);
   public static final RegistryObject<FlowingFluid> FLOWING_ALPHA_WATER = FLUIDS.register("flowing_alpha_water", AlphaWaterFluid.Flowing::new);

   public static void registerFluidInteractions()
   {

      FluidInteractionRegistry.addInteraction(AFluids.ALPHA_LAVA_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
              AFluids.ALPHA_WATER_FLUID_TYPE.get(),
              fluidState -> fluidState.isSource() ? ABlocks.OBSIDIAN.get().defaultBlockState() : ABlocks.COBBLESTONE.get().defaultBlockState()
      ));


   }
}
