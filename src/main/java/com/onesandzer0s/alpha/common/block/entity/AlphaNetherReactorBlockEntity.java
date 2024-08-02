package com.onesandzer0s.alpha.common.block.entity;

import com.onesandzer0s.alpha.common.block.AlphaNetherReactor;
import com.onesandzer0s.alpha.common.entity.ZombiePigman;
import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlphaNetherReactorBlockEntity extends BlockEntity {
   private int currentAnimState = 0;
   private int animationTicks = 0;
   public AlphaNetherReactorBlockEntity( BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState ) {
      super(pType, pPos, pBlockState);
   }

   public AlphaNetherReactorBlockEntity( BlockPos pPos, BlockState pBlockState ) {
      this(ABlockEntityTypes.NETHER_REACTOR.get(), pPos, pBlockState);
   }


   public boolean isRunning() {
      return this.getBlockState().getValue(AlphaNetherReactor.STATE) == 1;
   }

   protected void saveAdditional( CompoundTag pTag ) {
      super.saveAdditional(pTag);
      pTag.putInt("state", this.currentAnimState);
      pTag.putInt("animTicks", this.animationTicks);
   }

   @Override
   public void load( CompoundTag pTag ) {
      super.load(pTag);
      this.currentAnimState = pTag.getInt("state");
      this.animationTicks = pTag.getInt("animTicks");
   }

   protected boolean checkState(Level level, BlockPos pos, int state){
        var stateMap = STATE.get(state);
        for (var entry : stateMap.entrySet()){
            var block = entry.getKey();
            var positions = entry.getValue();
            for (var position : positions){
                if (!level.getBlockState(pos.offset(position)).getBlock().equals(block)){
                    return false;
                }
            }
        }
        return true;
   }

   protected void setState(Level level, BlockPos pos, int state){
        var stateMap = STATE.get(state);
        for (var entry : stateMap.entrySet()){
            var block = entry.getKey();
            var positions = entry.getValue();
            for (var position : positions){
                level.setBlock(pos.offset(position), block.defaultBlockState(), 3);
            }
        }
   }

   private void updateAnimation( Level level, BlockPos pos ) {
      if (currentAnimState>=7){
         return;
      }

      if (currentAnimState == 3){
         if (level instanceof ServerLevel sLevel ){
            StructureTemplateManager structureManager = sLevel.getStructureManager();
            Optional<StructureTemplate> netherSpire = structureManager.get(new ResourceLocation("alpha:nether_spire"));
            if (netherSpire.isPresent()){
                StructureTemplate template = netherSpire.get();
                StructurePlaceSettings settings = new StructurePlaceSettings();
                BlockPos off = pos.offset(-8, -3, -8);
                template.placeInWorld(sLevel, off, off, settings, sLevel.random,2);
            }

         }
      }

      if (checkState(level, pos, currentAnimState)) {

         currentAnimState++;

         setState(level, pos, currentAnimState);
         if ( currentAnimState == 7 ) {
            level.setBlock(pos, ABlocks.NETHER_REACTOR.get().defaultBlockState().setValue(AlphaNetherReactor.STATE, 2), 3);
            level.playSound((Player)null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
         }
         if ( currentAnimState == 1 ) {
            level.setBlock(pos, ABlocks.NETHER_REACTOR.get().defaultBlockState().setValue(AlphaNetherReactor.STATE, 1), 3);
         }

      } else {
         currentAnimState = 0;
         level.setBlock(pos, ABlocks.NETHER_REACTOR.get().defaultBlockState().setValue(AlphaNetherReactor.STATE, 2), 3);
      }
   }

   protected static int getNextAnimStateTimer(int state) {
      return switch ( state ) {
         case 0 -> 40;
         case 1 -> 40;
         case 2 -> 40;
         case 3 -> 40;
         case 4 -> 800;
         case 5 -> 40;
         case 6 -> 40;
         default -> 0;
      };
   }
   public static void serverTick( Level pLevel, BlockPos pPos, BlockState pState, AlphaNetherReactorBlockEntity pBlockEntity ) {
      if ( pBlockEntity.isRunning() && pBlockEntity.currentAnimState < 7 ) {
         pBlockEntity.animationTicks++;

         // spawn pigman
         if (pLevel.getRandom().nextInt(100) == 0) {
            // pick a spot that isnt the nether reactor
            BlockPos spot = pPos;
            while (spot.distSqr(pPos) < 10) {
               spot = pPos.offset(pLevel.getRandom().nextInt(8)-4, -1, pLevel.getRandom().nextInt(8)-4);
            }
            ZombiePigman pigman = new ZombiePigman(pLevel);
            pigman.setPos(spot.getX(), spot.getY(), spot.getZ());
            pigman.finalizeSpawn((ServerLevel)pLevel, pLevel.getCurrentDifficultyAt(spot), MobSpawnType.EVENT, null, null);
            pLevel.addFreshEntity(pigman);
         }

         // convert some of the netherrack to nether quartz ore
         if (pLevel.getRandom().nextInt(10) == 0) {
            BlockPos spot = pPos.offset(pLevel.getRandom().nextInt(8)-4, -1, pLevel.getRandom().nextInt(8)-4);
            while (!pLevel.getBlockState(spot).getBlock().equals(ABlocks.NETHERRACK.get())) {
               spot = pPos.offset(pLevel.getRandom().nextInt(16)-8, pLevel.getRandom().nextInt(32)-3, pLevel.getRandom().nextInt(16)-8);
            }
            pLevel.setBlock(spot, ABlocks.QUARTZ_ORE.get().defaultBlockState(), 3);
         }

         if ( pBlockEntity.animationTicks > getNextAnimStateTimer(pBlockEntity.currentAnimState) ) {
            pBlockEntity.animationTicks = 0;
            pBlockEntity.updateAnimation(pLevel, pPos);
         }
      }
   }

   public boolean canStart( Level pLevel, BlockPos pPos ) {
      if ( isRunning() ) return false;
      if (!checkState(pLevel, pPos, 0)) return false;
      if (this.getBlockState().getValue(AlphaNetherReactor.STATE) != 0) return false;
      return true;
   }

   public void startReactor( Level pLevel, Player pPlayer, BlockState pState, BlockPos pPos ) {
      level.playSound((Player)null, pPos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
      pLevel.setBlock(pPos, pState.setValue(AlphaNetherReactor.STATE, 1), 3);
   }


   protected static final List<Map<Block, List<BlockPos>>> STATE;


   // look. don't worry about it. :|
   static {
      var GOLD_CORNERS = List.of(new BlockPos(-1, -1, -1), new BlockPos(-1, -1, 1), new BlockPos(1, -1, -1), new BlockPos(1, -1, 1));
      var BOTTOM_COBBLESTONE = List.of(new BlockPos(-1, -1, 0), new BlockPos(1, -1, 0), new BlockPos(0, -1, -1), new BlockPos(0, -1, 1), new BlockPos(0, -1, 0));
      var MIDDLE_COBBLESTONE = List.of(new BlockPos(-1, 0, -1), new BlockPos(-1, 0, 1), new BlockPos(1, 0, -1), new BlockPos(1, 0, 1));
      var TOP_COBBLESTONE = List.of(new BlockPos(-1, 1, 0), new BlockPos(1, 1, 0), new BlockPos(0, 1, -1), new BlockPos(0, 1, 1), new BlockPos(0, 1, 0));
      var AIR_CORNERS = List.of(new BlockPos(-1, 1, -1), new BlockPos(-1, 1, 1), new BlockPos(1, 1, -1), new BlockPos(1, 1, 1));
      var AIR_FACES = List.of(new BlockPos(-1, 0, 0), new BlockPos(1, 0,0), new BlockPos(0, 0, -1), new BlockPos(0, 0, 1));


      STATE = new ArrayList<>();
      // first state
      var state = new HashMap<Block, List<BlockPos>>();
      state.put(ABlocks.GOLD_BLOCK.get(), GOLD_CORNERS);
      state.put(ABlocks.COBBLESTONE.get(), Stream.of(BOTTOM_COBBLESTONE, MIDDLE_COBBLESTONE, TOP_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(Blocks.AIR, Stream.of(AIR_CORNERS, AIR_FACES).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);

      // second state
      state = new HashMap<>();
      state.put(ABlocks.GOLD_BLOCK.get(), GOLD_CORNERS);
      state.put(ABlocks.COBBLESTONE.get(), Stream.of(MIDDLE_COBBLESTONE, TOP_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), BOTTOM_COBBLESTONE);
      state.put(Blocks.AIR, Stream.of(AIR_CORNERS, AIR_FACES).flatMap(Collection::stream).collect(Collectors.toList()));
        STATE.add(state);

      // third state
      state = new HashMap<>();
      state.put(ABlocks.GOLD_BLOCK.get(), GOLD_CORNERS);
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), Stream.of(MIDDLE_COBBLESTONE, BOTTOM_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(ABlocks.COBBLESTONE.get(), TOP_COBBLESTONE);
      state.put(Blocks.AIR, Stream.of(AIR_CORNERS, AIR_FACES).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);

      // fourth state
      state = new HashMap<>();
      state.put(ABlocks.GOLD_BLOCK.get(), GOLD_CORNERS);
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), Stream.of(TOP_COBBLESTONE, MIDDLE_COBBLESTONE, BOTTOM_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(Blocks.AIR, Stream.of(AIR_CORNERS, AIR_FACES).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);

      // fifth state
      state = new HashMap<>();
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), Stream.of(GOLD_CORNERS, TOP_COBBLESTONE, MIDDLE_COBBLESTONE, BOTTOM_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(Blocks.AIR, Stream.of(AIR_CORNERS, AIR_FACES).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);

      // sixth state
      state = new HashMap<>();
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), Stream.of(GOLD_CORNERS, MIDDLE_COBBLESTONE, BOTTOM_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(ABlocks.OBSIDIAN.get(), Stream.of(AIR_CORNERS, TOP_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(Blocks.AIR, AIR_FACES);
      STATE.add(state);

      // seventh state
      state = new HashMap<>();
      state.put(ABlocks.GLOWING_OBSIDIAN.get(), Stream.of(GOLD_CORNERS, BOTTOM_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      state.put(ABlocks.OBSIDIAN.get(), Stream.of(AIR_CORNERS, TOP_COBBLESTONE, AIR_FACES, MIDDLE_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);

      // last state
      state = new HashMap<>();
      state.put(ABlocks.OBSIDIAN.get(), Stream.of(GOLD_CORNERS, BOTTOM_COBBLESTONE, AIR_CORNERS, TOP_COBBLESTONE, AIR_FACES, MIDDLE_COBBLESTONE).flatMap(Collection::stream).collect(Collectors.toList()));
      STATE.add(state);
   }

}
