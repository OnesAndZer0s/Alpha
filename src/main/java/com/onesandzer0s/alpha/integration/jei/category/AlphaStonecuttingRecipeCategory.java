package com.onesandzer0s.alpha.integration.jei.category;

import com.onesandzer0s.alpha.common.crafting.AlphaStonecutterRecipe;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.integration.jei.AJEIRecipeTypes;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.Blocks;

//StoneCuttingRecipeCategory
public class AlphaStonecuttingRecipeCategory implements IRecipeCategory<AlphaStonecutterRecipe> {
   public static final int width = 82;
   public static final int height = 34;

   private final IDrawable background;
   private final IDrawable icon;
   private final Component localizedName;

   public AlphaStonecuttingRecipeCategory( IGuiHelper guiHelper) {
      ResourceLocation location = new ResourceLocation(ModIds.JEI_ID, "textures/jei/gui/gui_vanilla.png");
      background = guiHelper.createDrawable(location, 0, 220, width, height);
      icon = guiHelper.createDrawableItemStack(new ItemStack(ABlocks.STONECUTTER.get()));
      localizedName = Component.translatable("gui.jei.category.stoneCutter");
   }

   @Override
   public RecipeType<AlphaStonecutterRecipe> getRecipeType() {
      return AJEIRecipeTypes.STONECUTTING;
   }

   @Override
   public Component getTitle() {
      return localizedName;
   }

   @Override
   public IDrawable getBackground() {
      return background;
   }

   @Override
   public IDrawable getIcon() {
      return icon;
   }

   @Override
   public void setRecipe( IRecipeLayoutBuilder builder, AlphaStonecutterRecipe recipe, IFocusGroup focuses) {
      builder.addSlot(RecipeIngredientRole.INPUT, 1, 9)
              .addIngredients(recipe.getIngredients().get(0));

      Minecraft minecraft = Minecraft.getInstance();
      ClientLevel level = minecraft.level;
      if (level == null) {
         throw new NullPointerException("level must not be null.");
      }
      RegistryAccess registryAccess = level.registryAccess();

      builder.addSlot(RecipeIngredientRole.OUTPUT, 61,  9)
              .addItemStack(recipe.getResultItem(registryAccess));
   }

   @Override
   public boolean isHandled(AlphaStonecutterRecipe recipe) {
      return !recipe.isSpecial();
   }
}
