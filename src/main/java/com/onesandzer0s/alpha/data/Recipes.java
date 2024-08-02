package com.onesandzer0s.alpha.data;

import com.onesandzer0s.alpha.data.recipe.AlphaStonecutterRecipes;
import com.onesandzer0s.alpha.data.recipe.CraftingRecipes;
import com.onesandzer0s.alpha.data.recipe.FurnaceRecipes;
import com.onesandzer0s.alpha.data.recipe.StonecutterRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Recipes extends RecipeProvider
{
   public Recipes( PackOutput output) {
      super(output);
   }

   @Override
   protected void buildRecipes( Consumer<FinishedRecipe> consumer) {
      CraftingRecipes.register(consumer);
      FurnaceRecipes.register(consumer);
      AlphaStonecutterRecipes.register(consumer);
      StonecutterRecipe.register(consumer);
//      CookingRecipes.register(consumer);
//      CuttingRecipes.register(consumer);
   }
}