package com.onesandzer0s.alpha.data;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class AItemTags extends ItemTagsProvider
{
   public AItemTags( PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
      super(
              packOutput,
              registries,
              blockTagsProvider,
              AlphaMod.MODID,
              fileHelper
      );
   }

   @Override
   protected void addTags(@Nonnull HolderLookup.Provider registries) {
      tag(ItemTags.MUSIC_DISCS).add(AItems.CALM_DISC.get(), AItems.DOG_DISC.get());

      tag(ItemTags.LOGS).add(AItems.LOG.get());
      tag(ItemTags.PLANKS).add(AItems.PLANKS.get());
      tag(ItemTags.SWORDS).add(AItems.WOODEN_SWORD.get(), AItems.STONE_SWORD.get(), AItems.IRON_SWORD.get(), AItems.DIAMOND_SWORD.get(), AItems.GOLD_SWORD.get());
      tag(ItemTags.BOATS).add(AItems.BOAT.get());
        tag(ItemTags.FISHES).add(AItems.RAW_FISH.get());
        tag(ItemTags.FLOWERS).add(AItems.ROSE.get(), AItems.DANDELION.get(),AItems.PAEONIA.get(), AItems.CYAN_FLOWER.get());
      tag(ItemTags.SMALL_FLOWERS).add(AItems.ROSE.get(), AItems.DANDELION.get(),AItems.PAEONIA.get(), AItems.CYAN_FLOWER.get());
        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(AItems.WHEAT_SEEDS.get());
        tag(ItemTags.WOOL).add(AItems.WHITE_CLOTH.get(), AItems.LIGHT_GRAY_CLOTH.get(),AItems.DARK_GRAY_CLOTH.get(),AItems.RED_CLOTH.get(),AItems.ORANGE_CLOTH.get(),AItems.YELLOW_CLOTH.get(),AItems.CHARTREUSE_CLOTH.get(),AItems.GREEN_CLOTH.get(),AItems.SPRING_GREEN_CLOTH.get(),AItems.CYAN_CLOTH.get(),AItems.CAPRI_CLOTH.get(),AItems.ULTRAMARINE_CLOTH.get(),AItems.VIOLET_CLOTH.get(),AItems.PURPLE_CLOTH.get(),AItems.MAGENTA_CLOTH.get(),AItems.ROSE_CLOTH.get());
        tag(ItemTags.DOORS).add(AItems.DOOR.get(),AItems.IRON_DOOR.get());
        tag(ItemTags.WOODEN_FENCES).add(AItems.FENCE.get());
        tag(ItemTags.WOODEN_STAIRS).add(AItems.STAIRS.get());
        tag(ItemTags.WOODEN_SLABS).add(AItems.SMOOTH_STONE.get());
        tag(ItemTags.WOODEN_TRAPDOORS).add(AItems.TRAPDOOR.get());
      tag(ItemTags.TRAPDOORS).add(AItems.TRAPDOOR.get());
      tag(ItemTags.STONE_CRAFTING_MATERIALS).add(AItems.COBBLESTONE.get());
      tag(Tags.Items.ARMORS).add(AItems.STUDDED_BOOTS.get(),AItems.STUDDED_CHESTPLATE.get(),AItems.STUDDED_HELMET.get(),AItems.STUDDED_LEGGINGS.get());
        tag(Tags.Items.ARMORS_BOOTS).add(AItems.STUDDED_BOOTS.get());
        tag(Tags.Items.ARMORS_CHESTPLATES).add(AItems.STUDDED_CHESTPLATE.get());
        tag(Tags.Items.ARMORS_HELMETS).add(AItems.STUDDED_HELMET.get());
        tag(Tags.Items.ARMORS_LEGGINGS).add(AItems.STUDDED_LEGGINGS.get());
      tag(Tags.Items.CHESTS).add(AItems.CHEST.get());
      tag(Tags.Items.COBBLESTONE).add(AItems.COBBLESTONE.get());
      tag(Tags.Items.COBBLESTONE_MOSSY).add(AItems.MOSSY_COBBLESTONE.get());
      tag(Tags.Items.CROPS_WHEAT).add(AItems.WHEAT.get());
      tag(Tags.Items.SEEDS).add(AItems.WHEAT_SEEDS.get());
      tag(Tags.Items.SEEDS_WHEAT).add(AItems.WHEAT_SEEDS.get());
      tag(Tags.Items.FENCES).add(AItems.FENCE.get());
      tag(Tags.Items.GEMS).add(AItems.RUBY.get());
      tag(Tags.Items.GLASS).add(AItems.GLASS.get());
      tag(Tags.Items.GLASS_PANES).add(AItems.GLASS_PANE.get());
      tag(Tags.Items.GLASS_COLORLESS).add(AItems.GLASS.get());
      tag(Tags.Items.GLASS_PANES_COLORLESS).add(AItems.GLASS_PANE.get());
      tag(Tags.Items.GRAVEL).add(AItems.GRAVEL.get());
      tag(Tags.Items.SAND).add(AItems.SAND.get());
      tag(Tags.Items.SAND_COLORLESS).add(AItems.SAND.get());
      tag(Tags.Items.MUSHROOMS).add(AItems.RED_MUSHROOM.get(), AItems.BROWN_MUSHROOM.get());
      tag(Tags.Items.NETHERRACK).add(AItems.NETHERRACK.get());
      tag(Tags.Items.OBSIDIAN).add(AItems.OBSIDIAN.get(), AItems.GLOWING_OBSIDIAN.get());
   }

}
