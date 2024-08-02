package com.onesandzer0s.alpha;

import com.mojang.logging.LogUtils;
import com.onesandzer0s.alpha.client.events.ClientHandler;
import com.onesandzer0s.alpha.client.gui.AlphaStonecutterScreen;
import com.onesandzer0s.alpha.client.model.AModelLayers;
import com.onesandzer0s.alpha.common.CommonSetup;
import com.onesandzer0s.alpha.common.entity.AlphaMob;
import com.onesandzer0s.alpha.common.entity.Pigman;
import com.onesandzer0s.alpha.common.entity.ZombiePigman;
import com.onesandzer0s.alpha.common.registry.*;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AlphaMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AlphaMod
{
    public static final Map<Class, Class> MOB_CONVERSION = new HashMap(){{
        put( Pig.class, Pigman.class );
        put(ZombifiedPiglin.class, ZombiePigman.class );
        put(Zombie.class, AlphaMob.class);
    }};
    // Define mod id in a common place for everything to reference
    public static final String MODID = "alpha";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public AlphaMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ASounds.SOUNDS.register(modEventBus);

        AFluids.FLUID_TYPES.register(modEventBus);
        AFluids.FLUIDS.register(modEventBus);

        AEntities.ENTITIES.register(modEventBus);

        AItems.ITEMS.register(modEventBus);
        ABlocks.BLOCKS.register(modEventBus);
        ABlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        AMenuTypes.MENU_TYPES.register(modEventBus);
        ARecipeTypes.RECIPE_TYPES.register(modEventBus);
        ARecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);


        ACreativeTab.TABS.register(modEventBus);

        modEventBus.addListener(CommonSetup::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::setupEntityModelLayers);

        MinecraftForge.EVENT_BUS.register(this);

    }


    public void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        AModelLayers.register(event);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::init);
        event.enqueueWork(() -> MenuScreens.register(AMenuTypes.STONECUTTER.get(), AlphaStonecutterScreen::new));
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(TextureEvent::textEvent);
//        event.enqueueWork(() ->{
//            MenuScreens.register(MenuRegistry.STORAGE.get(), CraftingTerminalScreen::new);
//        });
//        try {
//            Class.forName("net.optifine.Config");
//            optifineLoaded = true;
//        } catch (Exception e) {
//            optifineLoaded = false;
//        }
    }
}
