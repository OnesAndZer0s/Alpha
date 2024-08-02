package com.onesandzer0s.alpha.common.registry;

import com.google.common.base.Suppliers;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.item.AlphaBoatItem;
import com.onesandzer0s.alpha.common.item.AlphaBow;
import com.onesandzer0s.alpha.common.item.AlphaFoodItem;
import com.onesandzer0s.alpha.common.item.AlphaSword;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaMod.MODID);

   public static final RegistryObject<Item> MISSING = ITEMS.register("missing", () -> new BlockItem(ABlocks.MISSING.get(), new Item.Properties()));
    public static final RegistryObject<Item> SMALL_MISSING = ITEMS.register("small_missing", () -> new BlockItem(ABlocks.SMALL_MISSING.get(), new Item.Properties()));
    public static final RegistryObject<Item> UNUSED = ITEMS.register("unused", () -> new BlockItem(ABlocks.UNUSED.get(), new Item.Properties()));
   public static final RegistryObject<Item> QUARTZ_ORE = ITEMS.register("quartz_ore", () -> new BlockItem(ABlocks.QUARTZ_ORE.get(), new Item.Properties()));
   public static final RegistryObject<Item> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(ABlocks.RUBY_BLOCK.get(), new Item.Properties()));

   public static final RegistryObject<Item> DEBUG = ITEMS.register("debug", () -> new BlockItem(ABlocks.DEBUG.get(), new Item.Properties()));
   public static final RegistryObject<Item> DEBUG2 = ITEMS.register("debug_alt", () -> new BlockItem(ABlocks.DEBUG2.get(), new Item.Properties()));
   public static final RegistryObject<Item> UPDATEGAME = ITEMS.register("update_game", () -> new BlockItem(ABlocks.UPDATEGAME.get(), new Item.Properties()));
//   public static final RegistryObject<Item> GEAR = ITEMS.register("gear", () -> new BlockItem(ABlocks.GEAR.get(), new Item.Properties()));
public static final RegistryObject<Item> CHISELED_SANDSTONE = ITEMS.register("chiseled_sandstone", () -> new BlockItem(ABlocks.CHISELED_SANDSTONE.get(), new Item.Properties()));
   public static final RegistryObject<Item> CUT_SANDSTONE = ITEMS.register("cut_sandstone", () -> new BlockItem(ABlocks.CUT_SANDSTONE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GRASS_BLOCK = ITEMS.register("grass_block", () -> new BlockItem(ABlocks.GRASS_BLOCK.get(), new Item.Properties()));
   public static final RegistryObject<Item> NETHER_REACTOR = ITEMS.register("nether_reactor", () -> new BlockItem(ABlocks.NETHER_REACTOR.get(), new Item.Properties()));
   public static final RegistryObject<Item> NEW_NETHER_REACTOR = ITEMS.register("new_nether_reactor", () -> new BlockItem(ABlocks.NEW_NETHER_REACTOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> COBBLESTONE = ITEMS.register("cobblestone", () -> new BlockItem(ABlocks.COBBLESTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MOSSY_COBBLESTONE = ITEMS.register("mossy_cobblestone", () -> new BlockItem(ABlocks.MOSSY_COBBLESTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> NETHERRACK = ITEMS.register("netherrack", () -> new BlockItem(ABlocks.NETHERRACK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SOUL_SAND = ITEMS.register("soul_sand", () -> new BlockItem(ABlocks.SOUL_SAND.get(), new Item.Properties()));
    public static final RegistryObject<Item> LOG = ITEMS.register("log", () -> new BlockItem(ABlocks.LOG.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROWN_MUSHROOM_BLOCK = ITEMS.register("brown_mushroom_block", () -> new BlockItem(ABlocks.BROWN_MUSHROOM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> RED_MUSHROOM_BLOCK = ITEMS.register("red_mushroom_block", () -> new BlockItem(ABlocks.RED_MUSHROOM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUSHROOM_STEM = ITEMS.register("mushroom_stem", () -> new BlockItem(ABlocks.MUSHROOM_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> SNOW = ITEMS.register("snow", () -> new BlockItem(ABlocks.SNOW.get(), new Item.Properties()));
    public static final RegistryObject<Item> SNOW_LAYER = ITEMS.register("snow_layer", () -> new BlockItem(ABlocks.SNOW_LAYER.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEAVES = ITEMS.register("leaves", () -> new BlockItem(ABlocks.LEAVES.get(), new Item.Properties()));

   public static final RegistryObject<Item> SLAB = ITEMS.register("slab", () -> new BlockItem(ABlocks.SLAB.get(), new Item.Properties()));
   public static final RegistryObject<Item> STAIRS = ITEMS.register("stairs", () -> new BlockItem(ABlocks.STAIRS.get(), new Item.Properties()));
   public static final RegistryObject<Item> SMOOTH_STONE = ITEMS.register("smooth_stone", () -> new BlockItem(ABlocks.SMOOTH_STONE.get(), new Item.Properties()));
   public static final RegistryObject<Item> SMOOTH_STONE_SLAB = ITEMS.register("smooth_stone_slab", () -> new BlockItem(ABlocks.SMOOTH_STONE_SLAB.get(), new Item.Properties()));
   public static final RegistryObject<Item> FENCE = ITEMS.register("fence", () -> new BlockItem(ABlocks.FENCE.get(), new Item.Properties()));
   public static final RegistryObject<Item> COBBLESTONE_SLAB = ITEMS.register("cobblestone_slab", () -> new BlockItem(ABlocks.COBBLESTONE_SLAB.get(), new Item.Properties()));
   public static final RegistryObject<Item> COBBLESTONE_STAIRS = ITEMS.register("cobblestone_stairs", () -> new BlockItem(ABlocks.COBBLESTONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Item> SAND = ITEMS.register("sand", () -> new BlockItem(ABlocks.SAND.get(), new Item.Properties()));
    public static final RegistryObject<Item> GRAVEL = ITEMS.register("gravel", () -> new BlockItem(ABlocks.GRAVEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PLANKS = ITEMS.register("planks", () -> new BlockItem(ABlocks.PLANKS.get(), new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIAN = ITEMS.register("obsidian", () -> new BlockItem(ABlocks.OBSIDIAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> GLOWING_OBSIDIAN = ITEMS.register("glowing_obsidian", () -> new BlockItem(ABlocks.GLOWING_OBSIDIAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> GLOWSTONE = ITEMS.register("glowstone", () -> new BlockItem(ABlocks.GLOWSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRICKS = ITEMS.register("bricks", () -> new BlockItem(ABlocks.BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> TNT = ITEMS.register("tnt", () -> new BlockItem(ABlocks.TNT.get(), new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_BLOCK = ITEMS.register("diamond_block", () -> new BlockItem(ABlocks.DIAMOND_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_BLOCK = ITEMS.register("iron_block", () -> new BlockItem(ABlocks.IRON_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_BLOCK = ITEMS.register("gold_block", () -> new BlockItem(ABlocks.GOLD_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> LAPIS_BLOCK = ITEMS.register("lapis_block", () -> new BlockItem(ABlocks.LAPIS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_BLOCK = ITEMS.register("redstone_block", () -> new BlockItem(ABlocks.REDSTONE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> DISPENSER = ITEMS.register("dispenser", () -> new BlockItem(ABlocks.DISPENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> NOTE_BLOCK = ITEMS.register("note_block", () -> new BlockItem(ABlocks.NOTE_BLOCK.get(), new Item.Properties()));
   public static final RegistryObject<Item> FURNACE = ITEMS.register("furnace", () -> new BlockItem(ABlocks.FURNACE.get(), new Item.Properties()));
//   public static final RegistryObject<Item> JUKEBOX = ITEMS.register("jukebox", () -> new BlockItem(ABlocks.JUKEBOX.get(), new Item.Properties()));
//   public static final RegistryObject<Item> SPAWNER = ITEMS.register("spawner", () -> new BlockItem(ABlocks.SPAWNER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPONGE = ITEMS.register("sponge", () -> new BlockItem(ABlocks.SPONGE.get(), new Item.Properties()));

   public static final RegistryObject<Item> CACTUS = ITEMS.register("cactus", () -> new BlockItem(ABlocks.CACTUS.get(), new Item.Properties()));
   public static final RegistryObject<Item> SUGARCANE = ITEMS.register("sugar_cane", () -> new BlockItem(ABlocks.SUGARCANE.get(), new Item.Properties()));
   public static final RegistryObject<Item> ICE = ITEMS.register("ice", () -> new BlockItem(ABlocks.ICE.get(), new Item.Properties()));

   public static final RegistryObject<Item> CARVED_PUMPKIN = ITEMS.register("carved_pumpkin", () -> new BlockItem(ABlocks.CARVED_PUMPKIN.get(), new Item.Properties()));
   public static final RegistryObject<Item> JACK_O_LANTERN = ITEMS.register("jack_o_lantern", () -> new BlockItem(ABlocks.JACK_O_LANTERN.get(), new Item.Properties()));

   public static final RegistryObject<Item> MELON = ITEMS.register("melon", () -> new BlockItem(ABlocks.MELON.get(), new Item.Properties()));

   public static final RegistryObject<Item> DOOR = ITEMS.register("door", () -> new BlockItem(ABlocks.DOOR.get(), new Item.Properties()));
   public static final RegistryObject<Item> TRAPDOOR = ITEMS.register("trapdoor", () -> new BlockItem(ABlocks.TRAPDOOR.get(), new Item.Properties()));
   public static final RegistryObject<Item> IRON_DOOR = ITEMS.register("iron_door", () -> new BlockItem(ABlocks.IRON_DOOR.get(), new Item.Properties()));

   public static final RegistryObject<Item> STONECUTTER = ITEMS.register("stonecutter", () -> new BlockItem(ABlocks.STONECUTTER.get(), new Item.Properties()));
   public static final RegistryObject<Item> DEAD_BUSH = ITEMS.register("dead_bush", () -> new BlockItem(ABlocks.DEAD_BUSH.get(), new Item.Properties()));

    public static final RegistryObject<Item> ROSE = ITEMS.register("rose", () -> new BlockItem(ABlocks.ROSE.get(), new Item.Properties()));
   public static final RegistryObject<Item> CYAN_FLOWER = ITEMS.register("cyan_flower", () -> new BlockItem(ABlocks.CYAN_FLOWER.get(), new Item.Properties()));
   public static final RegistryObject<Item> DANDELION = ITEMS.register("dandelion", () -> new BlockItem(ABlocks.DANDELION.get(), new Item.Properties()));

   public static final RegistryObject<Item> PAEONIA = ITEMS.register("paeonia", () -> new BlockItem(ABlocks.PAEONIA.get(), new Item.Properties()));

   public static final RegistryObject<Item> SAPLING = ITEMS.register("sapling", () -> new BlockItem(ABlocks.SAPLING.get(), new Item.Properties()));
   public static final RegistryObject<Item> RED_MUSHROOM = ITEMS.register("red_mushroom", () -> new BlockItem(ABlocks.RED_MUSHROOM.get(), new Item.Properties()));
   public static final RegistryObject<Item> BROWN_MUSHROOM = ITEMS.register("brown_mushroom", () -> new BlockItem(ABlocks.BROWN_MUSHROOM.get(), new Item.Properties()));

   public static final RegistryObject<Item> CHEST = ITEMS.register("chest", () -> new BlockItem(ABlocks.CHEST.get(), new Item.Properties()));
   public static final RegistryObject<Item> WHITE_CLOTH = ITEMS.register("white_cloth", () -> new BlockItem(ABlocks.WHITE_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> LIGHT_GRAY_CLOTH = ITEMS.register("light_gray_cloth", () -> new BlockItem(ABlocks.LIGHT_GRAY_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> DARK_GRAY_CLOTH = ITEMS.register("dark_gray_cloth", () -> new BlockItem(ABlocks.DARK_GRAY_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> RED_CLOTH = ITEMS.register("red_cloth", () -> new BlockItem(ABlocks.RED_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> ORANGE_CLOTH = ITEMS.register("orange_cloth", () -> new BlockItem(ABlocks.ORANGE_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> YELLOW_CLOTH = ITEMS.register("yellow_cloth", () -> new BlockItem(ABlocks.YELLOW_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> CHARTREUSE_CLOTH = ITEMS.register("chartreuse_cloth", () -> new BlockItem(ABlocks.CHARTREUSE_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> GREEN_CLOTH = ITEMS.register("green_cloth", () -> new BlockItem(ABlocks.GREEN_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> SPRING_GREEN_CLOTH = ITEMS.register("spring_green_cloth", () -> new BlockItem(ABlocks.SPRING_GREEN_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> CYAN_CLOTH = ITEMS.register("cyan_cloth", () -> new BlockItem(ABlocks.CYAN_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> CAPRI_CLOTH = ITEMS.register("capri_cloth", () -> new BlockItem(ABlocks.CAPRI_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> ULTRAMARINE_CLOTH = ITEMS.register("ultramarine_cloth", () -> new BlockItem(ABlocks.ULTRAMARINE_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> VIOLET_CLOTH = ITEMS.register("violet_cloth", () -> new BlockItem(ABlocks.VIOLET_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> PURPLE_CLOTH = ITEMS.register("purple_cloth", () -> new BlockItem(ABlocks.PURPLE_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> MAGENTA_CLOTH = ITEMS.register("magenta_cloth", () -> new BlockItem(ABlocks.MAGENTA_CLOTH.get(), new Item.Properties()));
   public static final RegistryObject<Item> ROSE_CLOTH = ITEMS.register("rose_cloth", () -> new BlockItem(ABlocks.ROSE_CLOTH.get(), new Item.Properties()));

   public static final RegistryObject<Item> GLASS = ITEMS.register("glass", () -> new BlockItem(ABlocks.GLASS.get(), new Item.Properties()));
   public static final RegistryObject<Item> GLASS_PANE = ITEMS.register("glass_pane", () -> new BlockItem(ABlocks.GLASS_PANE.get(), new Item.Properties()));

    public static final RegistryObject<Item> CRYING_OBSIDIAN = ITEMS.register("crying_obsidian", () -> new BlockItem(ABlocks.CRYING_OBSIDIAN.get(), new Item.Properties()));
   public static final RegistryObject<Item> WHEAT_SEEDS = ITEMS.register("wheat_seeds", () -> new ItemNameBlockItem(ABlocks.WHEAT.get(), new Item.Properties()));

   public static final RegistryObject<Item> CAKE = ITEMS.register("cake", () -> new BlockItem(ABlocks.CAKE.get(), new Item.Properties().stacksTo(1)));

   public static final RegistryObject<Item> CHISELED_QUARTZ = ITEMS.register("chiseled_quartz", () -> new BlockItem(ABlocks.CHISELED_QUARTZ.get(), new Item.Properties()));
   public static final RegistryObject<Item> QUARTZ_PILLAR = ITEMS.register("quartz_pillar", () -> new BlockItem(ABlocks.QUARTZ_PILLAR.get(), new Item.Properties()));
    public static final RegistryObject<Item> QUARTZ_BLOCK = ITEMS.register("quartz_block", () -> new BlockItem(ABlocks.QUARTZ_BLOCK.get(), new Item.Properties()));

   public static final RegistryObject<Item> HAY_BLOCK = ITEMS.register("hay_block", () -> new BlockItem(ABlocks.HAY_BALE.get(), new Item.Properties()));
   public static final RegistryObject<Item> CLAY_BLOCK = ITEMS.register("clay_block", () -> new BlockItem(ABlocks.CLAY_BLOCK.get(), new Item.Properties()));


   public static final RegistryObject<Item> WHEAT = ITEMS.register("wheat", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));

   public static final RegistryObject<Item> RAW_PORKCHOP = ITEMS.register("raw_porkchop", () -> new AlphaFoodItem(new Item.Properties().food(Foods.PORKCHOP)));
   public static final RegistryObject<Item> COOKED_PORKCHOP = ITEMS.register("cooked_porkchop", () -> new AlphaFoodItem(new Item.Properties().food(Foods.COOKED_PORKCHOP)));
   public static final RegistryObject<Item> RAW_BEEF = ITEMS.register("raw_beef", () -> new AlphaFoodItem(new Item.Properties().food(Foods.BEEF)));
   public static final RegistryObject<Item> COOKED_BEEF = ITEMS.register("cooked_beef", () -> new AlphaFoodItem(new Item.Properties().food(Foods.COOKED_BEEF)));
   public static final RegistryObject<Item> RAW_CHICKEN = ITEMS.register("raw_chicken", () -> new AlphaFoodItem(new Item.Properties().food(Foods.CHICKEN)));
   public static final RegistryObject<Item> COOKED_CHICKEN = ITEMS.register("cooked_chicken", () -> new AlphaFoodItem(new Item.Properties().food(Foods.COOKED_CHICKEN)));
   public static final RegistryObject<Item> RAW_FISH = ITEMS.register("raw_fish", () -> new AlphaFoodItem(new Item.Properties().food(Foods.COD)));
   public static final RegistryObject<Item> COOKED_FISH = ITEMS.register("cooked_fish", () -> new AlphaFoodItem(new Item.Properties().food(Foods.COOKED_COD)));

   public static final RegistryObject<Item> BREAD = ITEMS.register("bread", () -> new AlphaFoodItem(new Item.Properties().food(Foods.BREAD)));
   public static final RegistryObject<Item> APPLE = ITEMS.register("apple", () -> new AlphaFoodItem(new Item.Properties().food(Foods.APPLE)));
   public static final RegistryObject<Item> QUARTZ = ITEMS.register("quartz", () -> new Item(new Item.Properties()));

   public static final RegistryObject<Item> CALM_DISC = ITEMS.register("music_disc_calm", () -> new RecordItem(1, ASounds.MUSIC_DISC_CALM, (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE), 193*20));
   public static final RegistryObject<Item> DOG_DISC = ITEMS.register("music_disc_dog", () -> new RecordItem(1, ASounds.MUSIC_DISC_DOG, (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE), 143*20));

    static Supplier<? extends FlowingFluid> LAVA_SUPPLIER = Suppliers.memoize(AFluids.ALPHA_LAVA::get);
    static Supplier<? extends FlowingFluid> WATER_SUPPLIER = Suppliers.memoize(AFluids.ALPHA_WATER::get);
   public static final RegistryObject<Item> LAVA_BUCKET = ITEMS.register("lava_bucket", () -> new BucketItem(LAVA_SUPPLIER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
   public static final RegistryObject<Item> WATER_BUCKET = ITEMS.register("water_bucket", () -> new BucketItem(WATER_SUPPLIER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


   public static final RegistryObject<Item> BOW = ITEMS.register("bow", () -> new AlphaBow(new Item.Properties().stacksTo(1)));
   public static final RegistryObject<Item> WOODEN_SWORD = ITEMS.register("wooden_sword", () -> new AlphaSword(Tiers.WOOD, new Item.Properties()));
   public static final RegistryObject<Item> STONE_SWORD = ITEMS.register("stone_sword", () -> new AlphaSword(Tiers.STONE, new Item.Properties()));
   public static final RegistryObject<Item> IRON_SWORD = ITEMS.register("iron_sword", () -> new AlphaSword(Tiers.IRON, new Item.Properties()));
   public static final RegistryObject<Item> GOLD_SWORD = ITEMS.register("gold_sword", () -> new AlphaSword(Tiers.GOLD, new Item.Properties()));
   public static final RegistryObject<Item> DIAMOND_SWORD = ITEMS.register("diamond_sword", () -> new AlphaSword(Tiers.DIAMOND,new Item.Properties()));


   public static final ArmorMaterial STUDDED = new ArmorMaterial() {

      @Override
      public int getDurabilityForType( ArmorItem.Type pType ) {
         return this.getDefenseForType(pType) * 15;
      }

      @Override
      public int getDefenseForType( ArmorItem.Type pType ) {
         return switch (pType) {
            case HELMET -> 2;
            case CHESTPLATE -> 5;
            case LEGGINGS -> 4;
            case BOOTS -> 1;
         };
      }

      @Override
      public int getEnchantmentValue() {
         return 12;
      }

      @Override
      public SoundEvent getEquipSound() {
         return SoundEvents.ARMOR_EQUIP_CHAIN;
      }

      @Override
      public Ingredient getRepairIngredient() {
         return Ingredient.EMPTY;
      }

      @Override
      public String getName() {
         return "alpha:studded";
      }

      @Override
      public float getToughness() {
         return 0;
      }

      @Override
      public float getKnockbackResistance() {
         return 0;
      }
   };
   public static final RegistryObject<Item> STUDDED_HELMET = ITEMS.register("studded_helmet", () -> new ArmorItem(STUDDED, ArmorItem.Type.HELMET, new Item.Properties()));
   public static final RegistryObject<Item> STUDDED_CHESTPLATE = ITEMS.register("studded_chestplate", () -> new ArmorItem(STUDDED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
   public static final RegistryObject<Item> STUDDED_LEGGINGS = ITEMS.register("studded_leggings", () -> new ArmorItem(STUDDED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
   public static final RegistryObject<Item> STUDDED_BOOTS = ITEMS.register("studded_boots", () -> new ArmorItem(STUDDED, ArmorItem.Type.BOOTS, new Item.Properties()));

   public static final RegistryObject<Item> BOAT = ITEMS.register("boat", () -> new AlphaBoatItem(new Item.Properties()));



   public static final RegistryObject<Item> MOB_SPAWN_EGG = ITEMS.register("mob_spawn_egg", () -> new ForgeSpawnEggItem(AEntities.MOB, 0x00A5A5,0x3B318C, new Item.Properties()));
   public static final RegistryObject<Item> PIGMAN_SPAWN_EGG = ITEMS.register("pigman_spawn_egg", () -> new ForgeSpawnEggItem(AEntities.PIGMAN,15771042, 14377823,new Item.Properties()));
   public static final RegistryObject<Item> ZOMBIE_PIGMAN_SPAWN_EGG = ITEMS.register("zombie_pigman_spawn_egg", () -> new ForgeSpawnEggItem(AEntities.ZOMBIE_PIGMAN,15373203, 5009705,new Item.Properties()));
}
