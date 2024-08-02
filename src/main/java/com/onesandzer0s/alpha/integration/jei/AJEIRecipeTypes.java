package com.onesandzer0s.alpha.integration.jei;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.crafting.AlphaStonecutterRecipe;
import mezz.jei.api.recipe.RecipeType;

public class AJEIRecipeTypes {
   public static final RecipeType<AlphaStonecutterRecipe> STONECUTTING = RecipeType.create(AlphaMod.MODID, "stonecutter", AlphaStonecutterRecipe.class);
}
