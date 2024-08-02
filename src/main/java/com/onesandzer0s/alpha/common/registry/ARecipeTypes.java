package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.crafting.AlphaStonecutterRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ARecipeTypes {
   public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, AlphaMod.MODID);

   public static final RegistryObject<RecipeType<AlphaStonecutterRecipe>> ALPHA_STONECUTTER = RECIPE_TYPES.register("stonecutter", () -> registerRecipeType("stonecutter"));
   public static <T extends Recipe<?>> RecipeType<T> registerRecipeType( final String identifier) {
      return new RecipeType<>()
      {
         public String toString() {
            return AlphaMod.MODID + ":" + identifier;
         }
      };
   }
}
