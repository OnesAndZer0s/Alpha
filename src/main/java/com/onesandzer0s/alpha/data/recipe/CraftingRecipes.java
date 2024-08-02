package com.onesandzer0s.alpha.data.recipe;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CraftingRecipes {
   public static void register( Consumer<FinishedRecipe> output) {
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.MISSING.get(), 4)
              .pattern("ab")
              .pattern("ba")
              .define('b', Items.MAGENTA_CONCRETE)
              .define('a', Items.BLACK_CONCRETE)
              .unlockedBy("has_black_concrete", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_CONCRETE))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_missing"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.SMALL_MISSING.get(), 4)
              .pattern("aa")
              .pattern("aa")
              .define('a', AItems.MISSING.get())
                .unlockedBy("has_missing", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.MISSING.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_small_missing"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.UNUSED.get(), 4)
              .pattern("aa")
              .pattern("aa")
              .define('a', Items.MAGENTA_CONCRETE)
                .unlockedBy("has_magenta_concrete", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGENTA_CONCRETE))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_unused"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.DEBUG2.get(), 4)
              .pattern("ab")
              .pattern("cd")
              .define('a', Items.RED_CONCRETE)
              .define('b', Items.LIME_CONCRETE)
              .define('c', Items.BLUE_CONCRETE)
              .define('d', Items.YELLOW_CONCRETE)
              .unlockedBy("has_concrete", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_CONCRETE, Items.YELLOW_CONCRETE, Items.LIME_CONCRETE, Items.BLUE_CONCRETE))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_debug2"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.DEBUG.get(), 4)
              .pattern("aa")
              .pattern("aa")
              .define('a', AItems.DEBUG2.get())
              .unlockedBy("has_debug", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.DEBUG2.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_debug"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.UPDATEGAME.get(), 8)
              .pattern("aaa")
              .pattern("aba")
              .pattern("aaa")
              .define('a', Items.DIRT)
              .define('b', Items.GREEN_WOOL)
              .unlockedBy("has_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_updategame"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.NEW_NETHER_REACTOR.get(), 1)
              .pattern("aba")
              .pattern("aba")
              .pattern("aba")
              .define('a', Items.IRON_INGOT)
              .define('b', Items.DIAMOND)
              .unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_new_nether_reactor"));

      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AItems.PLANKS.get(), 4)
              .requires(AItems.LOG.get())
                .unlockedBy("has_log", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.LOG.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_planks"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.TNT.get(), 1)
              .pattern("axa")
              .pattern("xax")
              .pattern("axa")
              .define('x', AItems.SAND.get())
              .define('a', Items.GUNPOWDER)
                .unlockedBy("has_gunpowder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GUNPOWDER))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_tnt"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.CHEST.get(), 1)
              .pattern("aaa")
              .pattern("a a")
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
              .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_chest"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.RUBY_BLOCK.get(), 1)
              .pattern("aaa")
              .pattern("aaa")
              .pattern("aaa")
              .define('a', AItems.RUBY.get())
              .unlockedBy("has_ruby", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RUBY.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_ruby_block"));
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AItems.RUBY.get(), 9)
              .requires(AItems.RUBY_BLOCK.get())
              .unlockedBy("has_ruby_block", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RUBY_BLOCK.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_ruby"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.NOTE_BLOCK.get(), 1)
              .pattern("aaa")
              .pattern("axa")
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
              .define('x', Items.DIAMOND)
                .unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_note_block"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.DISPENSER.get(), 1)
              .pattern("aaa")
              .pattern("axa")
              .pattern("aba")
              .define('a', AItems.COBBLESTONE.get())
              .define('x', AItems.BOW.get())
                .define('b', Items.REDSTONE)
                .unlockedBy("has_redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_dispenser"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.FURNACE.get(), 1)
                .pattern("aaa")
                .pattern("a a")
                .pattern("aaa")
                .define('a', AItems.COBBLESTONE.get())
                .unlockedBy("has_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.COBBLESTONE.get()))
                .save(output, new ResourceLocation(AlphaMod.MODID, "craft_furnace"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.SLAB.get(), 6)
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
              .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_slab"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.STAIRS.get(), 4)
              .pattern("a  ")
              .pattern("aa ")
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_stairs"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.COBBLESTONE_SLAB.get(), 6)
              .pattern("aaa")
              .define('a', AItems.COBBLESTONE.get())
                .unlockedBy("has_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.COBBLESTONE.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_cobblestone_slab"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.COBBLESTONE_STAIRS.get(), 4)
              .pattern("a  ")
              .pattern("aa ")
              .pattern("aaa")
              .define('a', AItems.COBBLESTONE.get())
                .unlockedBy("has_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.COBBLESTONE.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_cobblestone_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.FENCE.get(), 3)
                .pattern("aba")
                .pattern("aba")
                .define('a', AItems.PLANKS.get())
                .define('b', Items.STICK)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
                .save(output, new ResourceLocation(AlphaMod.MODID, "craft_fence"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.SMOOTH_STONE_SLAB.get(), 6)
              .pattern("aaa")
              .define('a', AItems.SMOOTH_STONE.get())
                .unlockedBy("has_smooth_stone", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.SMOOTH_STONE.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_smooth_stone_slab"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.JACK_O_LANTERN.get(), 1)
              .pattern("a")
              .pattern("b")
              .define('a', AItems.CARVED_PUMPKIN.get())
              .define('b', Items.TORCH)
                .unlockedBy("has_torch", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TORCH))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_jack_o_lantern"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.DOOR.get(), 3)
              .pattern("aa")
              .pattern("aa")
              .pattern("aa")
              .define('a', AItems.PLANKS.get())
              .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_door"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.TRAPDOOR.get(), 2)
              .pattern("aaa")
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_trapdoor"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.GLASS_PANE.get(), 16)
              .pattern("aaa")
              .pattern("aaa")
              .define('a', AItems.GLASS.get())
                .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.GLASS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_glass_pane"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.BOAT.get(), 1)
              .pattern("a a")
              .pattern("aaa")
              .define('a', AItems.PLANKS.get())
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.PLANKS.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_boat"));
//      ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AItems.GEAR.get(), 8)
//              .pattern(" a ")
//              .pattern("aba")
//              .pattern(" a ")
//              .define('a', Items.IRON_NUGGET)
//              .define('b', Items.IRON_INGOT)
//              .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
//              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_gear"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.CAKE.get(), 1)
              .pattern("aaa")
              .pattern("bdb")
              .pattern("ccc")
              .define('c', AItems.WHEAT.get())
              .define('a', Items.MILK_BUCKET)
              .define('b', Items.SUGAR)
              .define('d', Items.EGG)
                .unlockedBy("has_milk_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MILK_BUCKET))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_cake"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.HAY_BLOCK.get(), 1)
              .pattern("aaa")
              .pattern("aaa")
              .pattern("aaa")
              .define('a', AItems.WHEAT.get())
              .unlockedBy("has_wheat", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.WHEAT.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_hay_block"));
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AItems.WHEAT.get(), 9)
              .requires(AItems.HAY_BLOCK.get())
                .unlockedBy("has_hay_block", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.HAY_BLOCK.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_wheat"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.BREAD.get(), 1)
              .pattern("aaa")
              .define('a', AItems.WHEAT.get())
                .unlockedBy("has_wheat", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.WHEAT.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_bread"));

      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.QUARTZ_BLOCK.get(), 1)
              .pattern("aa")
              .pattern("aa")
              .define('a', AItems.QUARTZ.get())
              .unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.QUARTZ.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_quartz_block"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AItems.QUARTZ_PILLAR.get(), 2)
              .pattern("a")
              .pattern("a")
              .define('a', AItems.QUARTZ_BLOCK.get())
              .unlockedBy("has_quartz_block", InventoryChangeTrigger.TriggerInstance.hasItems(AItems.QUARTZ_BLOCK.get()))
              .save(output, new ResourceLocation(AlphaMod.MODID, "craft_quartz_pillar"));
   }

}
