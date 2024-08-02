package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.block.AlphaStonecutter;
import com.onesandzer0s.alpha.common.crafting.AlphaStonecutterRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ARecipeSerializers {
   public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AlphaMod.MODID);
   public static final RegistryObject<RecipeSerializer<AlphaStonecutterRecipe>> ALPHA_STONECUTTER = RECIPE_SERIALIZERS.register("stonecutter",()->new SingleItemRecipe.Serializer<>(AlphaStonecutterRecipe::new));
}
