package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.block.*;
import com.onesandzer0s.alpha.common.block.SpongeBlock;
import com.onesandzer0s.alpha.data.features.AFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ABlocks {
   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlphaMod.MODID);
//   public static final BlockSetType ALPHA = ;


   public static final RegistryObject<Block> MISSING = BLOCKS.register("missing", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
   public static final RegistryObject<Block> SMALL_MISSING = BLOCKS.register("small_missing", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
   public static final RegistryObject<Block> UNUSED = BLOCKS.register("unused", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
   public static final RegistryObject<Block> QUARTZ_ORE = BLOCKS.register("quartz_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE)));
   public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));

   public static final RegistryObject<Block> DEBUG = BLOCKS.register("debug", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
   public static final RegistryObject<Block> DEBUG2 = BLOCKS.register("debug_alt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
   public static final RegistryObject<Block> UPDATEGAME = BLOCKS.register("update_game", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
   public static final RegistryObject<Block> GEAR = BLOCKS.register("gear", () -> new GearBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

   public static final RegistryObject<Block> CUT_SANDSTONE = BLOCKS.register("cut_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
   public static final RegistryObject<Block> CHISELED_SANDSTONE = BLOCKS.register("chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));

   public static final RegistryObject<Block> CHEST = BLOCKS.register("chest", () -> new AlphaChest(BlockBehaviour.Properties.copy(Blocks.CHEST)));

   public static final RegistryObject<Block> NETHER_REACTOR = BLOCKS.register("nether_reactor", () -> new AlphaNetherReactor(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
   public static final RegistryObject<Block> NEW_NETHER_REACTOR = BLOCKS.register("new_nether_reactor", () -> new AlphaNetherReactor(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));

   public static final RegistryObject<Block> GRASS_BLOCK = BLOCKS.register("grass_block", () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

   public static final RegistryObject<Block> COBBLESTONE = BLOCKS.register("cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
   public static final RegistryObject<Block> MOSSY_COBBLESTONE = BLOCKS.register("mossy_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)));
   public static final RegistryObject<Block> NETHERRACK = BLOCKS.register("netherrack", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));
   public static final RegistryObject<Block> SOUL_SAND = BLOCKS.register("soul_sand", () -> new SoulSandBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND)));
   public static final RegistryObject<Block> LOG = BLOCKS.register("log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

   public static final RegistryObject<Block> BROWN_MUSHROOM_BLOCK = BLOCKS.register("brown_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)));
   public static final RegistryObject<Block> RED_MUSHROOM_BLOCK = BLOCKS.register("red_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
   public static final RegistryObject<Block> MUSHROOM_STEM = BLOCKS.register("mushroom_stem", () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));

   public static final RegistryObject<Block> SNOW = BLOCKS.register("snow", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));
   public static final RegistryObject<Block> SNOW_LAYER = BLOCKS.register("snow_layer", () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.SNOW).noOcclusion()));
   public static final RegistryObject<Block> LEAVES = BLOCKS.register("leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
   public static final RegistryObject<Block> GLASS = BLOCKS.register("glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));
   public static final RegistryObject<Block> GLASS_PANE = BLOCKS.register("glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE).noOcclusion()));
   public static final RegistryObject<Block> SAND = BLOCKS.register("sand", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL = BLOCKS.register("gravel", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> PLANKS = BLOCKS.register("planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
   public static final RegistryObject<Block> OBSIDIAN = BLOCKS.register("obsidian", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
   public static final RegistryObject<Block> CRYING_OBSIDIAN = BLOCKS.register("crying_obsidian", () -> new CryingObsidianBlock(BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN)){
      public void animateTick( BlockState p_221055_, Level p_221056_, BlockPos p_221057_, RandomSource p_221058_) {
         if (p_221058_.nextInt(5) == 0) {
            Direction direction = Direction.getRandom(p_221058_);
            if (direction != Direction.UP) {
               BlockPos blockpos = p_221057_.relative(direction);
               BlockState blockstate = p_221056_.getBlockState(blockpos);
               if (!p_221055_.canOcclude() || !blockstate.isFaceSturdy(p_221056_, blockpos, direction.getOpposite())) {
                  double d0 = direction.getStepX() == 0 ? p_221058_.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                  double d1 = direction.getStepY() == 0 ? p_221058_.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                  double d2 = direction.getStepZ() == 0 ? p_221058_.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                  p_221056_.addParticle(ParticleTypes.DRIPPING_WATER, (double)p_221057_.getX() + d0, (double)p_221057_.getY() + d1, (double)p_221057_.getZ() + d2, 0.0D, 0.0D, 0.0D);
               }
            }
         }
      }
   });

   public static final RegistryObject<Block> GLOWING_OBSIDIAN = BLOCKS.register("glowing_obsidian", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).lightLevel((p_221055_) -> 10)));
   public static final RegistryObject<Block> GLOWSTONE = BLOCKS.register("glowstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)));
   public static final RegistryObject<Block> BRICKS = BLOCKS.register("bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<Block> TNT = BLOCKS.register("tnt", () -> new AlphaTntBlock(BlockBehaviour.Properties.copy(Blocks.TNT)));

    public static final RegistryObject<Block> DIAMOND_BLOCK = BLOCKS.register("diamond_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> IRON_BLOCK = BLOCKS.register("iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> GOLD_BLOCK = BLOCKS.register("gold_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> LAPIS_BLOCK = BLOCKS.register("lapis_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_BLOCK)));
   public static final RegistryObject<Block> REDSTONE_BLOCK = BLOCKS.register("redstone_block", () -> new PoweredBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK)));


    public static final RegistryObject<Block> DISPENSER = BLOCKS.register("dispenser", () -> new AlphaDispenser(BlockBehaviour.Properties.copy(Blocks.DISPENSER)));
    public static final RegistryObject<Block> NOTE_BLOCK = BLOCKS.register("note_block", () -> new NoteBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)));
//    public static final RegistryObject<Block> JUKEBOX = BLOCKS.register("jukebox", () -> new AlphaJukebox(BlockBehaviour.Properties.copy(Blocks.JUKEBOX)));
    public static final RegistryObject<Block> FURNACE = BLOCKS.register("furnace", () -> new AlphaFurnace(BlockBehaviour.Properties.copy(Blocks.FURNACE)));
//   public static final RegistryObject<Block> SPAWNER = BLOCKS.register("spawner", () -> new SpawnerBlock(BlockBehaviour.Properties.copy(Blocks.SPAWNER)));


   public static final RegistryObject<Block> SPONGE = BLOCKS.register("sponge", () -> new SpongeBlock(BlockBehaviour.Properties.copy(Blocks.SPONGE)));
   public static final RegistryObject<Block> CACTUS = BLOCKS.register("cactus", () -> new CactusBlock(BlockBehaviour.Properties.copy(Blocks.CACTUS).noOcclusion().randomTicks()){
      @Override
      public boolean canSustainPlant( BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable ) {
         BlockState plant = plantable.getPlant(world, pos.relative(facing));
         PlantType type = plantable.getPlantType(world, pos.relative(facing));
         if (plant.getBlock() == ABlocks.CACTUS.get()) {
            return state.is(ABlocks.CACTUS.get()) || state.is(BlockTags.SAND);
         }
         else return super.canSustainPlant(state, world, pos, facing, plantable);
      }
   });
   public static final RegistryObject<Block> SUGARCANE = BLOCKS.register("sugar_cane", () -> new SugarCaneBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE).noOcclusion()));
   public static final RegistryObject<Block> ICE = BLOCKS.register("ice", () -> new AlphaIceBlock(BlockBehaviour.Properties.copy(Blocks.ICE).noOcclusion()));


   public static final RegistryObject<Block> WHITE_CLOTH = BLOCKS.register("white_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> LIGHT_GRAY_CLOTH = BLOCKS.register("light_gray_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> DARK_GRAY_CLOTH = BLOCKS.register("dark_gray_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> RED_CLOTH = BLOCKS.register("red_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> ORANGE_CLOTH = BLOCKS.register("orange_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> YELLOW_CLOTH = BLOCKS.register("yellow_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> CHARTREUSE_CLOTH = BLOCKS.register("chartreuse_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> GREEN_CLOTH = BLOCKS.register("green_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> SPRING_GREEN_CLOTH = BLOCKS.register("spring_green_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> CYAN_CLOTH = BLOCKS.register("cyan_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> CAPRI_CLOTH = BLOCKS.register("capri_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> ULTRAMARINE_CLOTH = BLOCKS.register("ultramarine_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> VIOLET_CLOTH = BLOCKS.register("violet_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> PURPLE_CLOTH = BLOCKS.register("purple_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> MAGENTA_CLOTH = BLOCKS.register("magenta_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
   public static final RegistryObject<Block> ROSE_CLOTH = BLOCKS.register("rose_cloth", () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

   public static final RegistryObject<Block> SLAB = BLOCKS.register("slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
   public static final RegistryObject<Block> STAIRS = BLOCKS.register("stairs", () -> new StairBlock(PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
   public static final RegistryObject<Block> SMOOTH_STONE = BLOCKS.register("smooth_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));

   public static final RegistryObject<Block> SMOOTH_STONE_SLAB = BLOCKS.register("smooth_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE_SLAB)));
   public static final RegistryObject<Block> FENCE = BLOCKS.register("fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
   public static final RegistryObject<Block> COBBLESTONE_SLAB = BLOCKS.register("cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_SLAB)));
   public static final RegistryObject<Block> COBBLESTONE_STAIRS = BLOCKS.register("cobblestone_stairs", () -> new StairBlock(COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)));
   public static final RegistryObject<Block> CARVED_PUMPKIN = BLOCKS.register("carved_pumpkin", () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN)));
   public static final RegistryObject<Block> JACK_O_LANTERN = BLOCKS.register("jack_o_lantern", () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN)));
   public static final RegistryObject<Block> MELON = BLOCKS.register("melon", () -> new MelonBlock(BlockBehaviour.Properties.copy(Blocks.MELON)));

   public static final RegistryObject<Block> DOOR = BLOCKS.register("door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion(), new BlockSetType("alpha", true, SoundType.WOOD, ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get())));
    public static final RegistryObject<Block> TRAPDOOR = BLOCKS.register("trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion(), new BlockSetType("alpha", true, SoundType.WOOD, ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get())));
    public static final RegistryObject<Block> IRON_DOOR = BLOCKS.register("iron_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_DOOR).noOcclusion(), new BlockSetType("alpha", false, SoundType.WOOD, ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get(), ASounds.DOOR_CLOSE.get(), ASounds.DOOR_OPEN.get())));

   public static final RegistryObject<Block> WHEAT = BLOCKS.register("wheat", () -> new CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()){
      protected ItemLike getBaseSeedId() {
         return AItems.WHEAT_SEEDS.get();
      }
   });
   public static final RegistryObject<Block> STONECUTTER = BLOCKS.register("stonecutter", () -> new AlphaStonecutter(BlockBehaviour.Properties.copy(Blocks.STONECUTTER)));


   public static final RegistryObject<Block> DEAD_BUSH = BLOCKS.register("dead_bush", () -> new DeadBushBlock( BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));
   public static final RegistryObject<Block> ROSE = BLOCKS.register("rose", () -> new FlowerBlock(MobEffects.CONFUSION, 5, BlockBehaviour.Properties.copy(Blocks.POPPY)));
   public static final RegistryObject<Block> DANDELION = BLOCKS.register("dandelion", () -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
   public static final RegistryObject<Block> CYAN_FLOWER = BLOCKS.register("cyan_flower", () -> new FlowerBlock(MobEffects.SATURATION, 5, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
   public static final RegistryObject<Block> PAEONIA = BLOCKS.register("paeonia", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.copy(Blocks.PEONY)));

    public static final RegistryObject<Block> ALPHA_LAVA = BLOCKS.register("alpha_lava", () -> new AlphaLiquidBlock(AFluids.ALPHA_LAVA, BlockBehaviour.Properties.of().replaceable().pushReaction(PushReaction.DESTROY).liquid().noCollission().randomTicks().noLootTable().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.EMPTY).strength(100.0F)));
   public static final RegistryObject<Block> ALPHA_WATER = BLOCKS.register("alpha_water", () -> new AlphaLiquidBlock(AFluids.ALPHA_WATER, BlockBehaviour.Properties.of().replaceable().pushReaction(PushReaction.DESTROY).liquid().noCollission().randomTicks().noLootTable().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.EMPTY).strength(100.0F)));



   public static final RegistryObject<Block> SAPLING = BLOCKS.register("sapling", () -> new SaplingBlock(
           new AbstractTreeGrower() {
              @Override
              protected @NotNull ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature( RandomSource pRandom, boolean pHasFlowers ) {
                 return AFeatures.TREE;
              }
           }, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()));
   public static final RegistryObject<Block> RED_MUSHROOM = BLOCKS.register("red_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).noOcclusion(), AFeatures.HUGE_RED_MUSHROOM));
   public static final RegistryObject<Block> BROWN_MUSHROOM = BLOCKS.register("brown_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM).noOcclusion(), AFeatures.HUGE_BROWN_MUSHROOM));




   public static final RegistryObject<Block> POTTED_CACTUS = BLOCKS.register("potted_cactus",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.CACTUS,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_SAPLING = BLOCKS.register("potted_sapling",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.SAPLING,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_ROSE = BLOCKS.register("potted_rose",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.ROSE,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_DANDELION = BLOCKS.register("potted_dandelion",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.DANDELION,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_YELLOW)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_CYAN_FLOWER = BLOCKS.register("potted_cyan_flower",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.CYAN_FLOWER,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_CYAN)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_DEAD_BUSH = BLOCKS.register("potted_dead_bush",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.DEAD_BUSH,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_BROWN)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
   public static final RegistryObject<Block> POTTED_BROWN_MUSHROOM = BLOCKS.register("potted_brown_mushroom",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.BROWN_MUSHROOM,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );
    public static final RegistryObject<Block> POTTED_RED_MUSHROOM = BLOCKS.register("potted_red_mushroom",
              () -> new FlowerPotBlock(
                     null,
                     ABlocks.RED_MUSHROOM,
                     BlockBehaviour.Properties.of()
                            .mapColor(MapColor.TERRACOTTA_RED)
                            .pushReaction(PushReaction.DESTROY)
                            .instabreak()
                            .noOcclusion()
              )
    );
   public static final RegistryObject<Block> POTTED_PAEONIA = BLOCKS.register("potted_paeonia",
           () -> new FlowerPotBlock(
                   null,
                   ABlocks.PAEONIA,
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_MAGENTA)
                           .pushReaction(PushReaction.DESTROY)
                           .instabreak()
                           .noOcclusion()
           )
   );

   public static final RegistryObject<Block> CAKE = BLOCKS.register("cake", () -> new CakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion()));
//   public static final RegistryObject<Block> CHEST = BLOCKS.register("chest", () -> new ChestBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE).noOcclusion()));
public static final RegistryObject<Block> CHISELED_QUARTZ = BLOCKS.register("chiseled_quartz", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_QUARTZ_BLOCK)));

   public static final RegistryObject<Block> QUARTZ_PILLAR = BLOCKS.register("quartz_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)));
    public static final RegistryObject<Block> QUARTZ_BLOCK = BLOCKS.register("quartz_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));

   public static final RegistryObject<Block> CLAY_BLOCK = BLOCKS.register("clay_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CLAY)));
   public static final RegistryObject<Block> HAY_BALE = BLOCKS.register("hay_block", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));


}
