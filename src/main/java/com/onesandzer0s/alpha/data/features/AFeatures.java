package com.onesandzer0s.alpha.data.features;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class AFeatures {
   public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_RED_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AlphaMod.MODID, "huge_red_mushroom"));
   public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BROWN_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AlphaMod.MODID, "huge_brown_mushroom"));
   public static final ResourceKey<ConfiguredFeature<?, ?>> TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AlphaMod.MODID, "tree"));


   public static void bootstrap( BootstapContext<ConfiguredFeature<?, ?>> context ) {

      FeatureUtils.register(context,
              HUGE_BROWN_MUSHROOM,
              Feature.HUGE_BROWN_MUSHROOM,
              new HugeMushroomFeatureConfiguration(
                      BlockStateProvider.simple(ABlocks.BROWN_MUSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(true)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                      BlockStateProvider.simple(ABlocks.MUSHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                      3)
      );

      FeatureUtils.register(context,
              HUGE_RED_MUSHROOM,
              Feature.HUGE_RED_MUSHROOM,
              new HugeMushroomFeatureConfiguration(
                      BlockStateProvider.simple(ABlocks.RED_MUSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                      BlockStateProvider.simple(ABlocks.MUSHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                      2)
      );

      context.register(
              TREE,
              new ConfiguredFeature<>(
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(
                      BlockStateProvider.simple(ABlocks.LOG.get()),
                      new StraightTrunkPlacer(4, 2, 0),
                      BlockStateProvider.simple(ABlocks.LEAVES.get()),
                      new BlobFoliagePlacer(ConstantInt.of(2),
                              ConstantInt.of(0), 3),
                      new TwoLayersFeatureSize(1, 0, 1)
              ).ignoreVines().build()
      ));

   }
}
