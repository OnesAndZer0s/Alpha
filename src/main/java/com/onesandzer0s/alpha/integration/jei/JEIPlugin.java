package com.onesandzer0s.alpha.integration.jei;


import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.AItems;
import com.onesandzer0s.alpha.integration.jei.category.AlphaStonecuttingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
   private static final ResourceLocation ID = new ResourceLocation(AlphaMod.MODID, "jei_plugin");

   @Override
   public void registerCategories( IRecipeCategoryRegistration registry) {
      registry.addRecipeCategories(new AlphaStonecuttingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
   }

   @Override
   public void registerRecipes( IRecipeRegistration registration) {
      registration.addRecipes(AJEIRecipeTypes.STONECUTTING, new AJEIRecipes().getRecipes());
   }

   @Override
   public void registerRecipeCatalysts( IRecipeCatalystRegistration registration) {
      registration.addRecipeCatalyst(new ItemStack(AItems.FURNACE.get()), RecipeTypes.SMELTING);
      registration.addRecipeCatalyst(new ItemStack(AItems.STONECUTTER.get()), RecipeTypes.STONECUTTING);

      registration.addRecipeCatalyst(new ItemStack(AItems.STONECUTTER.get()), AJEIRecipeTypes.STONECUTTING);
   }

   @Override
   public ResourceLocation getPluginUid() {
      return ID;
   }
}
