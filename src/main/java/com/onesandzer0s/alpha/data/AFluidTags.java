package com.onesandzer0s.alpha.data;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class AFluidTags extends IntrinsicHolderTagsProvider<Fluid> {
   public AFluidTags( PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existingFileHelper ) {
      super(
              output,
              ForgeRegistries.Keys.FLUIDS,
              registries,
              ( Fluid attribute ) -> ForgeRegistries.FLUIDS.getResourceKey(attribute).get(),
              AlphaMod.MODID,
              existingFileHelper
      );
   }

   @Override
   protected void addTags( @Nonnull HolderLookup.Provider registries ) {
      tag(FluidTags.WATER).add(AFluids.ALPHA_WATER.get());
      tag(FluidTags.LAVA).add(AFluids.ALPHA_LAVA.get());
   }

}
