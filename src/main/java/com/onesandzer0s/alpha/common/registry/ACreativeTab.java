package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlphaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ACreativeTab {
   public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlphaMod.MODID);

   public static final RegistryObject<CreativeModeTab> ALPHA_TAB = TABS.register("main",
           () -> CreativeModeTab.builder()
                   .title(Component.translatable("alpha.itemGroup.main"))
                   .icon(AItems.GRASS_BLOCK.get()::getDefaultInstance)
                   .build()
   );

   @SubscribeEvent
   public static void buildContents( BuildCreativeModeTabContentsEvent event ) {
      if ( event.getTabKey() != ALPHA_TAB.getKey() ) return;

      event.accept(AItems.COBBLESTONE);
      event.accept(AItems.CLAY_BLOCK);
      event.accept(AItems.DIAMOND_BLOCK);
      event.accept(AItems.IRON_BLOCK);
      event.accept(AItems.GOLD_BLOCK);
      event.accept(AItems.LAPIS_BLOCK);
      event.accept(AItems.REDSTONE_BLOCK);
      event.accept(AItems.BRICKS);
      event.accept(AItems.MOSSY_COBBLESTONE);
      event.accept(AItems.SMOOTH_STONE);
      event.accept(AItems.SMOOTH_STONE_SLAB);
      event.accept(AItems.SLAB);
      event.accept(AItems.COBBLESTONE_SLAB);
      event.accept(AItems.OBSIDIAN);
      event.accept(AItems.NETHERRACK);
      event.accept(AItems.QUARTZ_ORE);
      event.accept(AItems.SOUL_SAND);
      event.accept(AItems.GLOWSTONE);
      event.accept(AItems.LOG);
      event.accept(AItems.LEAVES);
      event.accept(AItems.GRASS_BLOCK);
      event.accept(AItems.SAND);
      event.accept(AItems.CUT_SANDSTONE);
      event.accept(AItems.CHISELED_SANDSTONE);
      event.accept(AItems.GRAVEL);
      event.accept(AItems.PLANKS);
      event.accept(AItems.SAPLING);
      event.accept(AItems.DEAD_BUSH);
      event.accept(AItems.SPONGE);
      event.accept(AItems.ICE);
      event.accept(AItems.SNOW);
      event.accept(AItems.SNOW_LAYER);
      event.accept(AItems.DANDELION);
      event.accept(AItems.ROSE);
      event.accept(AItems.PAEONIA);
      event.accept(AItems.CYAN_FLOWER);
      event.accept(AItems.BROWN_MUSHROOM);
      event.accept(AItems.RED_MUSHROOM);
      event.accept(AItems.CACTUS);
      event.accept(AItems.MELON);
      event.accept(AItems.CARVED_PUMPKIN);
      event.accept(AItems.JACK_O_LANTERN);
      event.accept(AItems.GLASS_PANE);
      event.accept(AItems.CHEST);
      event.accept(AItems.STONECUTTER);
      event.accept(AItems.GLASS);
      event.accept(AItems.TNT);
      event.accept(AItems.WHITE_CLOTH);
      event.accept(AItems.LIGHT_GRAY_CLOTH);
      event.accept(AItems.DARK_GRAY_CLOTH);
      event.accept(AItems.RED_CLOTH);
      event.accept(AItems.ORANGE_CLOTH);
      event.accept(AItems.YELLOW_CLOTH);
      event.accept(AItems.CHARTREUSE_CLOTH);
      event.accept(AItems.GREEN_CLOTH);
      event.accept(AItems.SPRING_GREEN_CLOTH);
      event.accept(AItems.CYAN_CLOTH);
      event.accept(AItems.CAPRI_CLOTH);
      event.accept(AItems.ULTRAMARINE_CLOTH);
      event.accept(AItems.VIOLET_CLOTH);
      event.accept(AItems.PURPLE_CLOTH);
      event.accept(AItems.MAGENTA_CLOTH);
      event.accept(AItems.ROSE_CLOTH);
      event.accept(AItems.DISPENSER);
      event.accept(AItems.FURNACE);
      event.accept(AItems.NOTE_BLOCK);
      event.accept(AItems.FENCE);
      event.accept(AItems.STAIRS);
      event.accept(AItems.COBBLESTONE_STAIRS);
      event.accept(AItems.TRAPDOOR);
      event.accept(AItems.APPLE);
      event.accept(AItems.BOW);
      event.accept(AItems.IRON_SWORD);
      event.accept(AItems.WOODEN_SWORD);
      event.accept(AItems.STONE_SWORD);
      event.accept(AItems.DIAMOND_SWORD);
      event.accept(AItems.GOLD_SWORD);
      event.accept(AItems.WHEAT_SEEDS);
      event.accept(AItems.WHEAT);
      event.accept(AItems.BREAD);
      event.accept(AItems.STUDDED_HELMET);
      event.accept(AItems.STUDDED_CHESTPLATE);
      event.accept(AItems.STUDDED_LEGGINGS);
      event.accept(AItems.STUDDED_BOOTS);
      event.accept(AItems.RAW_PORKCHOP);
      event.accept(AItems.COOKED_PORKCHOP);
      event.accept(AItems.DOOR);
      event.accept(AItems.WATER_BUCKET);
      event.accept(AItems.LAVA_BUCKET);
      event.accept(AItems.IRON_DOOR);
      event.accept(AItems.BOAT);
      event.accept(AItems.SUGARCANE);
      event.accept(AItems.RAW_FISH);
      event.accept(AItems.COOKED_FISH);
      event.accept(AItems.CAKE);
      event.accept(AItems.RAW_BEEF);
      event.accept(AItems.COOKED_BEEF);
      event.accept(AItems.QUARTZ);


      event.accept(AItems.RAW_CHICKEN);
      event.accept(AItems.COOKED_CHICKEN);


      event.accept(AItems.CHISELED_QUARTZ);
      event.accept(AItems.QUARTZ_BLOCK);
      event.accept(AItems.QUARTZ_PILLAR);
      event.accept(AItems.HAY_BLOCK);





      event.accept(AItems.BROWN_MUSHROOM_BLOCK);
      event.accept(AItems.RED_MUSHROOM_BLOCK);
      event.accept(AItems.MUSHROOM_STEM);


      event.accept(AItems.RUBY);
      event.accept(AItems.RUBY_BLOCK);
      event.accept(AItems.NETHER_REACTOR);
      event.accept(AItems.NEW_NETHER_REACTOR);
      event.accept(AItems.GLOWING_OBSIDIAN);
      event.accept(AItems.CRYING_OBSIDIAN);

      event.accept(AItems.MISSING);
      event.accept(AItems.SMALL_MISSING);
      event.accept(AItems.UNUSED);
      event.accept(AItems.DEBUG);
      event.accept(AItems.DEBUG2);
      event.accept(AItems.UPDATEGAME);
      event.accept(AItems.CALM_DISC);
      event.accept(AItems.DOG_DISC);
      event.accept(AItems.MOB_SPAWN_EGG);
      event.accept(AItems.PIGMAN_SPAWN_EGG);
      event.accept(AItems.ZOMBIE_PIGMAN_SPAWN_EGG);
   }


}
