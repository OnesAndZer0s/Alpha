package com.onesandzer0s.alpha.data;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.data.features.AFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DatapackRegistryProvider extends DatapackBuiltinEntriesProvider {

   public static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
//           .add(Registries.DAMAGE_TYPE, FRDamageTypes::bootstrap)
           .add(Registries.CONFIGURED_FEATURE, AFeatures::bootstrap);
//           .add(Registries.PLACED_FEATURE, APlacements::bootstrap);
//           .add(ForgeRegistries.Keys.BIOME_MODIFIERS, FRBiomeModifiers::bootstrap);

   public DatapackRegistryProvider( PackOutput output, CompletableFuture<HolderLookup.Provider> provider ) {

      super(output, provider, REGISTRIES, Set.of("minecraft", AlphaMod.MODID));
   }
}
