package com.onesandzer0s.alpha.data;

import com.mojang.datafixers.util.Pair;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ABlockTags extends IntrinsicHolderTagsProvider<Block> {
   public ABlockTags( PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existingFileHelper ) {
      super(
              output,
              ForgeRegistries.Keys.BLOCKS,
              registries,
              ( Block attribute ) -> ForgeRegistries.BLOCKS.getResourceKey(attribute).get(),
              AlphaMod.MODID,
              existingFileHelper
      );
   }

   @Override
   protected void addTags( @Nonnull HolderLookup.Provider registries ) {
      tag(BlockTags.DIRT).add(ABlocks.GRASS_BLOCK.get());
      tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
              ABlocks.COBBLESTONE.get(),
              ABlocks.MOSSY_COBBLESTONE.get(),
              ABlocks.NETHERRACK.get(),
              ABlocks.OBSIDIAN.get(),
              ABlocks.CRYING_OBSIDIAN.get(),
              ABlocks.GLOWING_OBSIDIAN.get(),
              ABlocks.STONECUTTER.get(),
              ABlocks.BRICKS.get(),
              ABlocks.RUBY_BLOCK.get(),
              ABlocks.DIAMOND_BLOCK.get(),
              ABlocks.IRON_BLOCK.get(),
              ABlocks.GOLD_BLOCK.get(),
              ABlocks.LAPIS_BLOCK.get(),
              ABlocks.REDSTONE_BLOCK.get(),
              ABlocks.DISPENSER.get(),
              ABlocks.FURNACE.get(),
              ABlocks.ICE.get(),
              ABlocks.CHISELED_QUARTZ.get(),
              ABlocks.QUARTZ_BLOCK.get(),
              ABlocks.QUARTZ_PILLAR.get(),
              ABlocks.QUARTZ_ORE.get(),
              ABlocks.DEBUG.get(),
              ABlocks.DEBUG2.get(),
              ABlocks.UNUSED.get(),
              ABlocks.QUARTZ_ORE.get(),
              ABlocks.MISSING.get(),
              ABlocks.SMALL_MISSING.get(),
              ABlocks.IRON_DOOR.get(),
              ABlocks.CUT_SANDSTONE.get(),
              ABlocks.CHISELED_SANDSTONE.get()
              );
      tag(Tags.Blocks.SANDSTONE).add(ABlocks.CHISELED_SANDSTONE.get(), ABlocks.CUT_SANDSTONE.get());
      tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
              ABlocks.OBSIDIAN.get(),
              ABlocks.CRYING_OBSIDIAN.get(),
              ABlocks.GLOWING_OBSIDIAN.get()
      );
      tag(BlockTags.IMPERMEABLE).add(
              ABlocks.GLASS.get()
      );
      tag(BlockTags.BEACON_BASE_BLOCKS).add(
              ABlocks.IRON_BLOCK.get(),
              ABlocks.GOLD_BLOCK.get(),
              ABlocks.DIAMOND_BLOCK.get(),
              ABlocks.RUBY_BLOCK.get()
      );

      tag(BlockTags.MINEABLE_WITH_AXE).add(
              ABlocks.LOG.get(),
              ABlocks.RED_MUSHROOM_BLOCK.get(),
              ABlocks.BROWN_MUSHROOM_BLOCK.get(),
              ABlocks.MUSHROOM_STEM.get(),
              ABlocks.NOTE_BLOCK.get(),
              ABlocks.CARVED_PUMPKIN.get(),
              ABlocks.JACK_O_LANTERN.get(),
              ABlocks.MELON.get(),
              ABlocks.DOOR.get(),
              ABlocks.TRAPDOOR.get(),
              ABlocks.SAPLING.get(),
              ABlocks.RED_MUSHROOM.get(),
              ABlocks.BROWN_MUSHROOM.get(),
              ABlocks.PLANKS.get(),
              ABlocks.WHEAT.get(),
              ABlocks.DEAD_BUSH.get()
      );
      tag(BlockTags.PLANKS).add(
              ABlocks.PLANKS.get()
      );
      tag(BlockTags.SWORD_EFFICIENT).add(
              ABlocks.DEAD_BUSH.get(),
              ABlocks.CARVED_PUMPKIN.get(),
              ABlocks.JACK_O_LANTERN.get(),
              ABlocks.SAPLING.get(),
              ABlocks.WHEAT.get(),
              ABlocks.SUGARCANE.get(),
              ABlocks.ROSE.get(),
              ABlocks.CYAN_FLOWER.get(),
              ABlocks.DANDELION.get(),
              ABlocks.PAEONIA.get(),
              ABlocks.BROWN_MUSHROOM.get(),
              ABlocks.RED_MUSHROOM.get(),
              ABlocks.LEAVES.get()
      );

      tag(BlockTags.FLOWERS).add(
              ABlocks.ROSE.get(),
              ABlocks.CYAN_FLOWER.get(),
              ABlocks.DANDELION.get(),
              ABlocks.PAEONIA.get()
      );
      tag(BlockTags.SMALL_FLOWERS).add(
              ABlocks.ROSE.get(),
              ABlocks.CYAN_FLOWER.get(),
              ABlocks.DANDELION.get(),
              ABlocks.PAEONIA.get()
      );

      tag(BlockTags.MAINTAINS_FARMLAND).add(ABlocks.WHEAT.get());
      tag(BlockTags.CROPS).add(ABlocks.WHEAT.get());
      tag(BlockTags.BEE_GROWABLES).add(ABlocks.WHEAT.get());

      tag(Tags.Blocks.GLASS).add(ABlocks.GLASS.get());
      tag(Tags.Blocks.GLASS_COLORLESS).add(ABlocks.GLASS.get());
      tag(Tags.Blocks.GLASS_PANES).add(ABlocks.GLASS_PANE.get());
      tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(ABlocks.GLASS_PANE.get());

      tag(BlockTags.SAPLINGS).add(
              ABlocks.SAPLING.get()
      );
      tag(BlockTags.DOORS).add(
              ABlocks.DOOR.get(),
              ABlocks.IRON_DOOR.get()
      );
      tag(BlockTags.WOODEN_DOORS).add(
              ABlocks.DOOR.get()
      );

      tag(BlockTags.TRAPDOORS).add(
              ABlocks.TRAPDOOR.get()
      );

      tag(Tags.Blocks.ORES).add(ABlocks.QUARTZ_ORE.get());
      tag(Tags.Blocks.ORES_QUARTZ).add(ABlocks.QUARTZ_ORE.get());

      tag(BlockTags.SNOW).add(
                ABlocks.SNOW.get(),
                ABlocks.SNOW_LAYER.get()
        );

      tag(BlockTags.REPLACEABLE).add(
              ABlocks.SNOW_LAYER.get()
      );

      tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
              ABlocks.GRASS_BLOCK.get(),
              ABlocks.SOUL_SAND.get(),
              ABlocks.SAND.get(),
              ABlocks.GRAVEL.get(),
              ABlocks.SNOW.get(),
              ABlocks.SNOW_LAYER.get(),
              ABlocks.CLAY_BLOCK.get(),
              ABlocks.UPDATEGAME.get()
      );
      tag(BlockTags.MINEABLE_WITH_HOE).add(
              ABlocks.SPONGE.get(),
              ABlocks.LEAVES.get(),
              ABlocks.HAY_BALE.get()

      );
      tag(BlockTags.ICE).add(
              ABlocks.ICE.get()
      );

      tag(BlockTags.FLOWER_POTS).add(
              ABlocks.POTTED_CACTUS.get(),
              ABlocks.POTTED_BROWN_MUSHROOM.get(),
              ABlocks.POTTED_RED_MUSHROOM.get(),
              ABlocks.POTTED_CYAN_FLOWER.get(),
              ABlocks.POTTED_DANDELION.get(),
              ABlocks.POTTED_ROSE.get(),
              ABlocks.POTTED_PAEONIA.get(),
              ABlocks.POTTED_SAPLING.get(),
                ABlocks.POTTED_DEAD_BUSH.get()
      );
      tag(BlockTags.INFINIBURN_END).add(ABlocks.NETHERRACK.get());
      tag(BlockTags.DRAGON_IMMUNE).add(
              ABlocks.OBSIDIAN.get(),
              ABlocks.CRYING_OBSIDIAN.get(),
              ABlocks.GLOWING_OBSIDIAN.get()
      );
      tag(BlockTags.SAND).add(ABlocks.SAND.get());
      tag(BlockTags.SNOW).add(ABlocks.SNOW.get());


      tag(Tags.Blocks.COBBLESTONE).add(ABlocks.COBBLESTONE.get(), ABlocks.MOSSY_COBBLESTONE.get());
      tag(Tags.Blocks.COBBLESTONE_MOSSY).add(ABlocks.MOSSY_COBBLESTONE.get());
      tag(Tags.Blocks.COBBLESTONE_NORMAL).add(ABlocks.COBBLESTONE.get());
      tag(Tags.Blocks.OBSIDIAN).add(ABlocks.OBSIDIAN.get(), ABlocks.GLOWING_OBSIDIAN.get());
      tag(Tags.Blocks.SAND).add(ABlocks.SAND.get());
      tag(Tags.Blocks.GRAVEL).add(ABlocks.GRAVEL.get());
      tag(Tags.Blocks.SAND_COLORLESS).add(ABlocks.SAND.get());


      tag(Tags.Blocks.NETHERRACK).add(ABlocks.NETHERRACK.get());
      tag(BlockTags.BASE_STONE_NETHER).add(ABlocks.NETHERRACK.get());

      tag(BlockTags.LOGS).add(ABlocks.LOG.get());
      tag(BlockTags.FENCES).add(ABlocks.FENCE.get());
      tag(BlockTags.WOODEN_FENCES).add(ABlocks.FENCE.get());
      tag(BlockTags.SLABS).add(ABlocks.SLAB.get(), ABlocks.COBBLESTONE_SLAB.get(), ABlocks.SMOOTH_STONE_SLAB.get());
      tag(BlockTags.WOOL).add(ABlocks.WHITE_CLOTH.get(), ABlocks.LIGHT_GRAY_CLOTH.get(), ABlocks.DARK_GRAY_CLOTH.get(), ABlocks.RED_CLOTH.get(), ABlocks.ORANGE_CLOTH.get(), ABlocks.YELLOW_CLOTH.get(), ABlocks.CHARTREUSE_CLOTH.get(), ABlocks.GREEN_CLOTH.get(), ABlocks.SPRING_GREEN_CLOTH.get(), ABlocks.CYAN_CLOTH.get(), ABlocks.CAPRI_CLOTH.get(), ABlocks.ULTRAMARINE_CLOTH.get(), ABlocks.VIOLET_CLOTH.get(), ABlocks.PURPLE_CLOTH.get(), ABlocks.MAGENTA_CLOTH.get(), ABlocks.ROSE_CLOTH.get());


      tag(BlockTags.LEAVES).add(ABlocks.LEAVES.get());
      tag(BlockTags.OAK_LOGS).add(ABlocks.LOG.get());
      tag(BlockTags.LOGS_THAT_BURN).add(ABlocks.LOG.get());
      tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(ABlocks.LOG.get());
      tag(BlockTags.SOUL_SPEED_BLOCKS).add(ABlocks.SOUL_SAND.get());
      tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(ABlocks.SOUL_SAND.get());


      tag(BlockTags.NEEDS_IRON_TOOL).add(ABlocks.RUBY_BLOCK.get(), ABlocks.DIAMOND_BLOCK.get(), ABlocks.GOLD_BLOCK.get());
      tag(BlockTags.NEEDS_STONE_TOOL).add(ABlocks.IRON_BLOCK.get(), ABlocks.LAPIS_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS).add(ABlocks.QUARTZ_BLOCK.get(), ABlocks.RUBY_BLOCK.get(), ABlocks.DIAMOND_BLOCK.get(), ABlocks.IRON_BLOCK.get(), ABlocks.GOLD_BLOCK.get(), ABlocks.LAPIS_BLOCK.get(), ABlocks.REDSTONE_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_IRON).add(ABlocks.IRON_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_GOLD).add(ABlocks.GOLD_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND).add(ABlocks.DIAMOND_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_LAPIS).add(ABlocks.LAPIS_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_REDSTONE).add(ABlocks.REDSTONE_BLOCK.get());
      tag(Tags.Blocks.STORAGE_BLOCKS_QUARTZ).add(ABlocks.QUARTZ_BLOCK.get());


   }

}
