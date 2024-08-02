package com.onesandzer0s.alpha.common.crafting;

import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.ARecipeSerializers;
import com.onesandzer0s.alpha.common.registry.ARecipeTypes;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class AlphaStonecutterRecipe extends SingleItemRecipe {

   public AlphaStonecutterRecipe( ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult ) {
      super(ARecipeTypes.ALPHA_STONECUTTER.get(), ARecipeSerializers.ALPHA_STONECUTTER.get(), pId, pGroup, pIngredient, pResult);
   }



   public boolean matches(Container pInv, Level pLevel) {
      return this.ingredient.test(pInv.getItem(0));
   }

   public ItemStack getToastSymbol() {
      return new ItemStack(ABlocks.STONECUTTER.get());
   }

}
