package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.block.entity.menu.AlphaStonecutterMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMenuTypes {
   public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, AlphaMod.MODID);

   public static final RegistryObject<MenuType<AlphaStonecutterMenu>> STONECUTTER = MENU_TYPES
           .register("stonecutter", () -> IForgeMenuType.create(AlphaStonecutterMenu::new));
}
