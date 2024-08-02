package com.onesandzer0s.alpha.data.recipe;

import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class StonecutterRecipe {
   public static void register( Consumer<FinishedRecipe> output) {
      SingleItemRecipeBuilder.stonecutting(Ingredient.of(AItems.COBBLESTONE.get()), RecipeCategory.MISC, AItems.COBBLESTONE_SLAB.get(), 2)
              .unlockedBy("has_cobblestone",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.COBBLESTONE.get())).save(output,"minecraft:cobblestone_slabs_from_cobblestone");
      SingleItemRecipeBuilder.stonecutting(Ingredient.of(AItems.QUARTZ_BLOCK.get()), RecipeCategory.MISC, AItems.CHISELED_QUARTZ.get(), 1)
              .unlockedBy("has_chiseled_quartz",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.QUARTZ_BLOCK.get())).save(output,"minecraft:chiseled_quarts_from_quartz_block");
      SingleItemRecipeBuilder.stonecutting(Ingredient.of(AItems.QUARTZ_BLOCK.get()), RecipeCategory.MISC, AItems.QUARTZ_PILLAR.get(), 1)
              .unlockedBy("has_quartz_pillar",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.QUARTZ_BLOCK.get())).save(output,"minecraft:quartz_pillar_from_quartz_block");


   }
}
