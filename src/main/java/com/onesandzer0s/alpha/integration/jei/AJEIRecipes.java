package com.onesandzer0s.alpha.integration.jei;

import com.onesandzer0s.alpha.common.crafting.AlphaStonecutterRecipe;
import com.onesandzer0s.alpha.common.registry.ARecipeTypes;
import com.onesandzer0s.alpha.data.recipe.AlphaStonecutterRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class AJEIRecipes {
   private final RecipeManager recipeManager;

   public AJEIRecipes() {
      Minecraft minecraft = Minecraft.getInstance();
      ClientLevel level = minecraft.level;

      if (level != null) {
         this.recipeManager = level.getRecipeManager();
      } else {
         throw new NullPointerException("minecraft world must not be null.");
      }
   }


   public List<AlphaStonecutterRecipe> getRecipes() {

      return recipeManager.getAllRecipesFor(ARecipeTypes.ALPHA_STONECUTTER.get());
   }

}
