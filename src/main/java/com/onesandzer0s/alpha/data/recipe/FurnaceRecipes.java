package com.onesandzer0s.alpha.data.recipe;

import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;

import java.util.function.Consumer;

public class FurnaceRecipes {
   public static void register( Consumer<FinishedRecipe> output) {
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.RAW_BEEF.get()), RecipeCategory.FOOD, AItems.COOKED_BEEF.get(), 0.35F, 200)
              .unlockedBy("has_raw_beef",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RAW_BEEF.get())).save(output,"alpha:cooked_beef_from_raw_beef");
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.RAW_PORKCHOP.get()), RecipeCategory.FOOD, AItems.COOKED_PORKCHOP.get(), 0.35F, 200)
              .unlockedBy("has_raw_porkchop",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RAW_PORKCHOP.get())).save(output,"alpha:cooked_porkchop_from_raw_porkchop");
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.RAW_CHICKEN.get()), RecipeCategory.FOOD, AItems.COOKED_CHICKEN.get(), 0.35F, 200)
              .unlockedBy("has_raw_chicken",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RAW_CHICKEN.get())).save(output,"alpha:cooked_chicken_from_raw_chicken");
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.RAW_FISH.get()), RecipeCategory.FOOD, AItems.COOKED_FISH.get(), 0.35F, 200)
              .unlockedBy("has_fish",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.RAW_FISH.get())).save(output,"alpha:cooked_fish_from_raw_fish");

      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.CACTUS.get()), RecipeCategory.MISC, Items.GREEN_DYE, 0.35F, 200)
              .unlockedBy("has_cactus",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.CACTUS.get())).save(output,"alpha:green_dye_from_cactus");
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(AItems.SAND.get()), RecipeCategory.MISC, AItems.GLASS.get(), 0.35F, 200)
              .unlockedBy("has_sand",  InventoryChangeTrigger.TriggerInstance.hasItems(AItems.SAND.get())).save(output,"alpha:glass_from_sand");
   }
}
