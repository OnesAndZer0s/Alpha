package com.onesandzer0s.alpha.data;


import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.data.loot.ABlockLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AlphaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ADataGenerators {

   @SubscribeEvent
   public static void gatherData( GatherDataEvent event) {

      DataGenerator generator = event.getGenerator();
      PackOutput output = generator.getPackOutput();


      CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
      ExistingFileHelper fileHelper = event.getExistingFileHelper();

      generator.addProvider(event.includeServer(), new DatapackRegistryProvider(output, event.getLookupProvider()));
      generator.addProvider(event.includeServer(), new Recipes(output));

      ABlockTags blockTagsProvider = generator.addProvider(event.includeServer(), new ABlockTags(output, registries, fileHelper));
      AItemTags itemTagsProvider = generator.addProvider(event.includeServer(), new AItemTags(output, registries, blockTagsProvider.contentsGetter(), fileHelper));
      AFluidTags fluidTagsProvider = generator.addProvider(event.includeServer(), new AFluidTags(output, registries, fileHelper));
      generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), List.of(
              new LootTableProvider.SubProviderEntry(ABlockLoot::new, LootContextParamSets.BLOCK)
      )));
   }
}
