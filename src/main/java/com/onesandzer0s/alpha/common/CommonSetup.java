package com.onesandzer0s.alpha.common;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AFluids;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
   public static void setup( FMLCommonSetupEvent event) {
      event.enqueueWork(CommonSetup::registerCompostables);
      event.enqueueWork(CommonSetup::registerFlowerPotPlants);
      event.enqueueWork(AFluids::registerFluidInteractions);
   }

   public static void registerFlowerPotPlants() {
      FlowerPotBlock flowerPotBlock = (FlowerPotBlock) Blocks.FLOWER_POT;
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "sapling"), ABlocks.POTTED_SAPLING);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "rose"), ABlocks.POTTED_ROSE);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "dandelion"), ABlocks.POTTED_DANDELION);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "cyan_flower"), ABlocks.POTTED_CYAN_FLOWER);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "cactus"), ABlocks.POTTED_CACTUS);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "brown_mushroom"), ABlocks.POTTED_BROWN_MUSHROOM);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "red_mushroom"), ABlocks.POTTED_RED_MUSHROOM);
         flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "paeonia"), ABlocks.POTTED_PAEONIA);
      flowerPotBlock.addPlant(new ResourceLocation(AlphaMod.MODID, "dead_bush"), ABlocks.POTTED_DEAD_BUSH);


   }

   public static void registerCompostables() {
      ComposterBlock.COMPOSTABLES.put(AItems.RED_MUSHROOM.get(), 0.65F);
      ComposterBlock.COMPOSTABLES.put(AItems.BROWN_MUSHROOM.get(), 0.65F);
      ComposterBlock.COMPOSTABLES.put(AItems.RED_MUSHROOM_BLOCK.get(), 0.85F);
      ComposterBlock.COMPOSTABLES.put(AItems.BROWN_MUSHROOM_BLOCK.get(), 0.85F);
      ComposterBlock.COMPOSTABLES.put(AItems.MUSHROOM_STEM.get(), 0.65F);

      ComposterBlock.COMPOSTABLES.put(AItems.PAEONIA.get(), 0.65F);
      ComposterBlock.COMPOSTABLES.put(AItems.ROSE.get(), 0.65F);
      ComposterBlock.COMPOSTABLES.put(AItems.DANDELION.get(), 0.65F);
      ComposterBlock.COMPOSTABLES.put(AItems.CYAN_FLOWER.get(), 0.65F);


      ComposterBlock.COMPOSTABLES.put(AItems.CACTUS.get(), 0.5F);
      ComposterBlock.COMPOSTABLES.put(AItems.SUGARCANE.get(), 0.5F);

      ComposterBlock.COMPOSTABLES.put(AItems.SAPLING.get(), 0.3F);
      ComposterBlock.COMPOSTABLES.put(AItems.LEAVES.get(), 0.3F);

      //      ComposterBlock.COMPOSTABLES.put(AItems.GREEN_TEA_LEAVES.get(), 0.3F);
//      ComposterBlock.COMPOSTABLES.put(AItems.YELLOW_TEA_LEAVES.get(), 0.2F);
//      ComposterBlock.COMPOSTABLES.put(AItems.BLACK_TEA_LEAVES.get(), 0.1F);
//      ComposterBlock.COMPOSTABLES.put(AItems.COFFEE_BERRIES.get(), 0.3F);
//      ComposterBlock.COMPOSTABLES.put(AItems.TEA_SEEDS.get(), 0.3F);
//      ComposterBlock.COMPOSTABLES.put(AItems.ROSE_HIPS.get(), 0.3F);
//
//      ComposterBlock.COMPOSTABLES.put(AItems.GREEN_TEA_COOKIE.get(), 0.85F);
//      ComposterBlock.COMPOSTABLES.put(AItems.WILD_TEA_BUSH.get(), 0.65F);
//      ComposterBlock.COMPOSTABLES.put(AItems.COFFEE_CAKE.get(), 1.0F);
//      ComposterBlock.COMPOSTABLES.put(AItems.ROSE_HIP_PIE.get(), 1.0F);
//      ComposterBlock.COMPOSTABLES.put(AItems.COFFEE_CAKE_SLICE.get(), 0.85F);
//      ComposterBlock.COMPOSTABLES.put(AItems.ROSE_HIP_PIE_SLICE.get(), 0.85F);
   }

}
