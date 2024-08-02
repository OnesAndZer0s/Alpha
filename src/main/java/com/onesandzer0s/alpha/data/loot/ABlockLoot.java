package com.onesandzer0s.alpha.data.loot;

import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashSet;
import java.util.Set;

public class ABlockLoot extends BlockLootSubProvider {

   private final Set<Block> generatedLootTables = new HashSet<>();

   public ABlockLoot() {
      super(Set.of(), FeatureFlags.REGISTRY.allFlags());
   }

   @Override
   protected void generate() {
      dropSelf(ABlocks.MISSING.get());
      dropSelf(ABlocks.SMALL_MISSING.get());
      dropSelf(ABlocks.UNUSED.get());
      dropSelf(ABlocks.RUBY_BLOCK.get());
      dropSelf(ABlocks.DEBUG.get());
      dropSelf(ABlocks.DEBUG2.get());
      dropSelf(ABlocks.UPDATEGAME.get());
      dropSelf(ABlocks.CHEST.get());
      dropSelf(ABlocks.NETHER_REACTOR.get());
      dropSelf(ABlocks.NEW_NETHER_REACTOR.get());
      dropSelf(ABlocks.COBBLESTONE.get());
      dropSelf(ABlocks.MOSSY_COBBLESTONE.get());
      dropSelf(ABlocks.NETHERRACK.get());
      dropSelf(ABlocks.SOUL_SAND.get());
      dropSelf(ABlocks.LOG.get());
      dropSelf(ABlocks.SAND.get());
      dropSelf(ABlocks.PLANKS.get());
      dropSelf(ABlocks.OBSIDIAN.get());
      dropSelf(ABlocks.CRYING_OBSIDIAN.get());
      dropSelf(ABlocks.GLOWING_OBSIDIAN.get());
      dropSelf(ABlocks.BRICKS.get());
      dropSelf(ABlocks.TNT.get());
      dropSelf(ABlocks.DIAMOND_BLOCK.get());
      dropSelf(ABlocks.IRON_BLOCK.get());
      dropSelf(ABlocks.GOLD_BLOCK.get());
      dropSelf(ABlocks.LAPIS_BLOCK.get());
      dropSelf(ABlocks.REDSTONE_BLOCK.get());
      dropSelf(ABlocks.DISPENSER.get());
      dropSelf(ABlocks.NOTE_BLOCK.get());
      dropSelf(ABlocks.FURNACE.get());
      dropSelf(ABlocks.SPONGE.get());
      dropSelf(ABlocks.CACTUS.get());
      dropSelf(ABlocks.MISSING.get());
      dropSelf(ABlocks.SUGARCANE.get());
      dropSelf(ABlocks.WHITE_CLOTH.get());
      dropSelf(ABlocks.LIGHT_GRAY_CLOTH.get());
      dropSelf(ABlocks.DARK_GRAY_CLOTH.get());
      dropSelf(ABlocks.RED_CLOTH.get());
      dropSelf(ABlocks.ORANGE_CLOTH.get());
      dropSelf(ABlocks.YELLOW_CLOTH.get());
      dropSelf(ABlocks.CHARTREUSE_CLOTH.get());
      dropSelf(ABlocks.GREEN_CLOTH.get());
      dropSelf(ABlocks.SPRING_GREEN_CLOTH.get());
      dropSelf(ABlocks.CYAN_CLOTH.get());
      dropSelf(ABlocks.CAPRI_CLOTH.get());
      dropSelf(ABlocks.ULTRAMARINE_CLOTH.get());
      dropSelf(ABlocks.VIOLET_CLOTH.get());
      dropSelf(ABlocks.PURPLE_CLOTH.get());
      dropSelf(ABlocks.MAGENTA_CLOTH.get());
      dropSelf(ABlocks.ROSE_CLOTH.get());
      dropSelf(ABlocks.STAIRS.get());
      dropSelf(ABlocks.SMOOTH_STONE.get());
      dropSelf(ABlocks.FENCE.get());
      dropSelf(ABlocks.COBBLESTONE_STAIRS.get());
      dropSelf(ABlocks.CARVED_PUMPKIN.get());
      dropSelf(ABlocks.JACK_O_LANTERN.get());
      dropSelf(ABlocks.TRAPDOOR.get());
      dropSelf(ABlocks.STONECUTTER.get());
      dropSelf(ABlocks.ROSE.get());
      dropSelf(ABlocks.DANDELION.get());
      dropSelf(ABlocks.CYAN_FLOWER.get());
      dropSelf(ABlocks.PAEONIA.get());
      dropSelf(ABlocks.SAPLING.get());
      dropSelf(ABlocks.RED_MUSHROOM.get());
      dropSelf(ABlocks.BROWN_MUSHROOM.get());
      dropSelf(ABlocks.CHISELED_QUARTZ.get());
      dropSelf(ABlocks.QUARTZ_BLOCK.get());
      dropSelf(ABlocks.QUARTZ_PILLAR.get());
      dropSelf(ABlocks.HAY_BALE.get());
      dropSelf(ABlocks.CUT_SANDSTONE.get());
      dropSelf(ABlocks.CHISELED_SANDSTONE.get());


      dropPottedContents(ABlocks.POTTED_ROSE.get());
      dropPottedContents(ABlocks.POTTED_DANDELION.get());
      dropPottedContents(ABlocks.POTTED_CYAN_FLOWER.get());
      dropPottedContents(ABlocks.POTTED_PAEONIA.get());
      dropPottedContents(ABlocks.POTTED_RED_MUSHROOM.get());
      dropPottedContents(ABlocks.POTTED_BROWN_MUSHROOM.get());
      dropPottedContents(ABlocks.POTTED_SAPLING.get());
      dropPottedContents(ABlocks.POTTED_CACTUS.get());
      dropPottedContents(ABlocks.POTTED_DEAD_BUSH.get());

      add(ABlocks.DOOR.get(), ( block ) -> createDoorTable(ABlocks.DOOR.get()));
      add(ABlocks.IRON_DOOR.get(), ( block ) -> createDoorTable(ABlocks.IRON_DOOR.get()));

      dropWhenSilkTouch(ABlocks.GLASS_PANE.get());
      dropWhenSilkTouch(ABlocks.GLASS.get());
      dropWhenSilkTouch(ABlocks.ICE.get());
      dropWhenSilkTouch(ABlocks.MUSHROOM_STEM.get());


      add(ABlocks.SLAB.get(), this::createSlabItemTable);
      add(ABlocks.COBBLESTONE_SLAB.get(), this::createSlabItemTable);
      add(ABlocks.SMOOTH_STONE_SLAB.get(), this::createSlabItemTable);

      add(ABlocks.WHEAT.get(), ( block ) -> createCropDrops(block, AItems.WHEAT.get(), AItems.WHEAT_SEEDS.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))));

      add(ABlocks.QUARTZ_ORE.get(), ( block ) -> createOreDrop(block, AItems.QUARTZ.get()));

      // snow layer, gravel

//      add(ABlocks.GEAR.get(), (block)->createMultifaceBlockDrops(block, ExplosionCondition.survivesExplosion()));
      add(ABlocks.GRASS_BLOCK.get(), ( block ) -> createSingleItemTableWithSilkTouch(block, Items.DIRT));

      add(ABlocks.LEAVES.get(), createLeavesDrops(ABlocks.LEAVES.get(), ABlocks.SAPLING.get(),0.05f, 0.0625f, 0.083333336f, 0.1f).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(this.applyExplosionCondition(ABlocks.LEAVES.get(), LootItem.lootTableItem(AItems.APPLE.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F)))));

      add(ABlocks.BROWN_MUSHROOM_BLOCK.get(), ( block ) -> createMushroomBlockDrop(block, ABlocks.BROWN_MUSHROOM.get()));
      add(ABlocks.RED_MUSHROOM_BLOCK.get(), ( block ) -> createMushroomBlockDrop(block, ABlocks.RED_MUSHROOM.get()));

      add(ABlocks.SNOW.get(), ( block ) -> createSingleItemTableWithSilkTouch(block, Items.SNOWBALL, ConstantValue.exactly(4)));
      add(ABlocks.MELON.get(), ( block ) -> createSingleItemTableWithSilkTouch(block, Items.MELON, UniformGenerator.between(3, 7.0f)));
      add(ABlocks.GLOWSTONE.get(), ( block ) -> createSingleItemTableWithSilkTouch(block, Items.GLOWSTONE_DUST, UniformGenerator.between(1, 4.0f)));
      add(ABlocks.CLAY_BLOCK.get(), ( block ) -> createSingleItemTableWithSilkTouch(block, Items.CLAY_BALL, ConstantValue.exactly(4)));

   }

   @Override
   protected void add( Block block, LootTable.Builder builder ) {
      this.generatedLootTables.add(block);
      this.map.put(block.getLootTable(), builder);
   }

   @Override
   protected Iterable<Block> getKnownBlocks() {
      return generatedLootTables;
   }
}
