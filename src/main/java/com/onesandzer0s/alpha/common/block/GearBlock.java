package com.onesandzer0s.alpha.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.onesandzer0s.alpha.common.block.entity.GearBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class GearBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
   private static final float AABB_OFFSET = 1.0F;
   private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   private static final VoxelShape DOWN_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
   private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
   private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
   private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
   private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION;
   private static final Map<Direction, VoxelShape> SHAPE_BY_DIRECTION = Util.make(Maps.newEnumMap(Direction.class), ( p_153923_) -> {
      p_153923_.put(Direction.NORTH, NORTH_AABB);
      p_153923_.put(Direction.EAST, EAST_AABB);
      p_153923_.put(Direction.SOUTH, SOUTH_AABB);
      p_153923_.put(Direction.WEST, WEST_AABB);
      p_153923_.put(Direction.UP, UP_AABB);
      p_153923_.put(Direction.DOWN, DOWN_AABB);
   });
   protected static final Direction[] DIRECTIONS = Direction.values();
   private final ImmutableMap<BlockState, VoxelShape> shapesCache;
   private final boolean canRotate;
   private final boolean canMirrorX;
   private final boolean canMirrorZ;

   @Nullable
   @Override
   public BlockEntity newBlockEntity( BlockPos pPos, BlockState pState ) {
      return new GearBlockEntity(pPos, pState);
   }

   public enum GearState implements StringRepresentable {
      CW("cw"),
      CCW("ccw"),
      NONE("none");

      private final String name;

      private GearState( String pName ) {
         this.name = pName;
      }

      public String toString() {
         return this.getSerializedName();
      }

      public String getSerializedName() {
         return this.name;
      }

      public boolean isConnected() {
         return this != NONE;
      }
   }



   private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
   private static final EnumProperty<GearState> STATE = EnumProperty.create("state", GearState.class);
   public GearBlock( Properties properties ) {
      super(properties);
      this.registerDefaultState(getDefaultMultifaceState(this.stateDefinition));
      this.shapesCache = this.getShapeForEachState(GearBlock::calculateMultifaceShape);
      this.canRotate = Direction.Plane.HORIZONTAL.stream().allMatch(this::isFaceSupported);
      this.canMirrorX = Direction.Plane.HORIZONTAL.stream().filter(Direction.Axis.X).filter(this::isFaceSupported).count() % 2L == 0L;
      this.canMirrorZ = Direction.Plane.HORIZONTAL.stream().filter(Direction.Axis.Z).filter(this::isFaceSupported).count() % 2L == 0L;
      this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(STATE, GearState.NONE));
   }

   public static Set<Direction> availableFaces(BlockState pState) {
      if (!(pState.getBlock() instanceof MultifaceBlock)) {
         return Set.of();
      } else {
         Set<Direction> set = EnumSet.noneOf(Direction.class);

         for(Direction direction : Direction.values()) {
            if (hasFace(pState, direction)) {
               set.add(direction);
            }
         }

         return set;
      }
   }


   public static Set<Direction> unpack(byte pPackedDirections) {
      Set<Direction> set = EnumSet.noneOf(Direction.class);

      for(Direction direction : Direction.values()) {
         if ((pPackedDirections & (byte)(1 << direction.ordinal())) > 0) {
            set.add(direction);
         }
      }

      return set;
   }

   public static byte pack( Collection<Direction> pDirections) {
      byte b0 = 0;

      for(Direction direction : pDirections) {
         b0 = (byte)(b0 | 1 << direction.ordinal());
      }

      return b0;
   }


   protected boolean isFaceSupported(Direction p_153921_) {
      return true;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
      for(Direction direction : DIRECTIONS) {
         if (this.isFaceSupported(direction)) {
            pBuilder.add(getFaceProperty(direction));
         }
      }
      pBuilder.add(WATERLOGGED, STATE);
   }

   public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
      return this.shapesCache.get(pState);
   }

   public boolean canSurvive( BlockState pState, LevelReader pLevel, BlockPos pPos) {
      boolean flag = false;

      for(Direction direction : DIRECTIONS) {
         if (hasFace(pState, direction)) {
            BlockPos blockpos = pPos.relative(direction);
            if (!canAttachTo(pLevel, direction, blockpos, pLevel.getBlockState(blockpos))) {
               return false;
            }

            flag = true;
         }
      }

      return flag;
   }

   public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
      return hasAnyVacantFace(pState);
   }

   @javax.annotation.Nullable
   public BlockState getStateForPlacement(BlockPlaceContext pContext) {
      Level level = pContext.getLevel();
      BlockPos blockpos = pContext.getClickedPos();
      BlockState blockstate = level.getBlockState(blockpos);
      return Arrays.stream(pContext.getNearestLookingDirections()).map(( p_153865_) -> {
         return this.getStateForPlacement(blockstate, level, blockpos, p_153865_);
      }).filter(Objects::nonNull).findFirst().orElse((BlockState)null);
   }

   public boolean isValidStateForPlacement(BlockGetter pLevel, BlockState pState, BlockPos pPos, Direction pDirection) {
      if (this.isFaceSupported(pDirection) && (!pState.is(this) || !hasFace(pState, pDirection))) {
         BlockPos blockpos = pPos.relative(pDirection);
         return canAttachTo(pLevel, pDirection, blockpos, pLevel.getBlockState(blockpos));
      } else {
         return false;
      }
   }


   @javax.annotation.Nullable
   public BlockState getStateForPlacement(BlockState pCurrentState, BlockGetter pLevel, BlockPos pPos, Direction pLookingDirection) {
      if (!this.isValidStateForPlacement(pLevel, pCurrentState, pPos, pLookingDirection)) {
         return null;
      } else {
         BlockState blockstate;
         if (pCurrentState.is(this)) {
            blockstate = pCurrentState;
         } else if (this.isWaterloggable() && pCurrentState.getFluidState().isSourceOfType(Fluids.WATER)) {
            blockstate = this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true));
         } else {
            blockstate = this.defaultBlockState();
         }

         return blockstate.setValue(getFaceProperty(pLookingDirection), Boolean.valueOf(true));
      }
   }

   /**
    * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
    * blockstate.
    * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#rotate} whenever
    * possible. Implementing/overriding is fine.
    */
   public BlockState rotate(BlockState pState, Rotation pRotation) {
      return !this.canRotate ? pState : this.mapDirections(pState, pRotation::rotate);
   }

   /**
    * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
    * blockstate.
    * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#mirror} whenever
    * possible. Implementing/overriding is fine.
    */
   public BlockState mirror(BlockState pState, Mirror pMirror) {
      if (pMirror == Mirror.FRONT_BACK && !this.canMirrorX) {
         return pState;
      } else {
         return pMirror == Mirror.LEFT_RIGHT && !this.canMirrorZ ? pState : this.mapDirections(pState, pMirror::mirror);
      }
   }

   private BlockState mapDirections(BlockState pState, Function<Direction, Direction> pDirectionalFunction) {
      BlockState blockstate = pState;

      for(Direction direction : DIRECTIONS) {
         if (this.isFaceSupported(direction)) {
            blockstate = blockstate.setValue(getFaceProperty(pDirectionalFunction.apply(direction)), pState.getValue(getFaceProperty(direction)));
         }
      }

      return blockstate;
   }

   public static boolean hasFace(BlockState pState, Direction pDirection) {
      BooleanProperty booleanproperty = getFaceProperty(pDirection);
      return pState.hasProperty(booleanproperty) && pState.getValue(booleanproperty);
   }

   public static boolean canAttachTo(BlockGetter pLevel, Direction pDirection, BlockPos pPos, BlockState pState) {
      return Block.isFaceFull(pState.getBlockSupportShape(pLevel, pPos), pDirection.getOpposite()) || Block.isFaceFull(pState.getCollisionShape(pLevel, pPos), pDirection.getOpposite());
   }

   private boolean isWaterloggable() {
      return this.stateDefinition.getProperties().contains(BlockStateProperties.WATERLOGGED);
   }

   private static BlockState removeFace(LevelAccessor level, BlockPos pPos, BlockState pState, BooleanProperty pFaceProp) {
      BlockState blockstate = pState.setValue(pFaceProp, Boolean.valueOf(false));
      return hasAnyFace(blockstate) ? blockstate : Blocks.AIR.defaultBlockState();
   }

   public static BooleanProperty getFaceProperty(Direction pDirection) {
      return PROPERTY_BY_DIRECTION.get(pDirection);
   }

   private static BlockState getDefaultMultifaceState(StateDefinition<Block, BlockState> pStateDefinition) {
      BlockState blockstate = pStateDefinition.any();

      for(BooleanProperty booleanproperty : PROPERTY_BY_DIRECTION.values()) {
         if (blockstate.hasProperty(booleanproperty)) {
            blockstate = blockstate.setValue(booleanproperty, Boolean.valueOf(false));
         }
      }

      return blockstate;
   }

   private static VoxelShape calculateMultifaceShape(BlockState p_153959_) {
      VoxelShape voxelshape = Shapes.empty();

      for(Direction direction : DIRECTIONS) {
         if (hasFace(p_153959_, direction)) {
            voxelshape = Shapes.or(voxelshape, SHAPE_BY_DIRECTION.get(direction));
         }
      }

      return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
   }

   protected static boolean hasAnyFace(BlockState pState) {
      return Arrays.stream(DIRECTIONS).anyMatch((p_221583_) -> {
         return hasFace(pState, p_221583_);
      });
   }

   private static boolean hasAnyVacantFace(BlockState pState) {
      return Arrays.stream(DIRECTIONS).anyMatch((p_221580_) -> {
         return !hasFace(pState, p_221580_);
      });
   }

   @Override
   public void updateIndirectNeighbourShapes( BlockState pState, LevelAccessor pLevel, BlockPos pPos, int pFlags, int pRecursionLeft ) {
      super.updateIndirectNeighbourShapes(pState, pLevel, pPos, pFlags, pRecursionLeft);
   }

   @Override
   public int getDirectSignal( BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection ) {
//      return super.getDirectSignal(pState, pLevel, pPos, pDirection);
      return 0;
   }

   @Override
   public int getSignal( BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection ) {
      return pState.getValue(STATE) != GearState.NONE ? 15 : 0;
   }

   private void checkCornerChangeAt( Level pLevel, BlockPos pPos ) {
      if ( pLevel.getBlockState(pPos).is(this) ) {
         pLevel.updateNeighborsAt(pPos, this);

         for ( Direction direction : Direction.values() ) {
            pLevel.updateNeighborsAt(pPos.relative(direction), this);
         }

      }
   }


   public void tick( BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom ) {
//      if (pState.getValue(POWERED) && !pLevel.hasNeighborSignal(pPos)) {
//         pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
//      }

   }

   /**
    * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
    * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
    * returns its solidified counterpart.
    * Note that this method should ideally consider only the specific direction passed in.
    */
   public BlockState updateShape( BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos ) {
      if ( pState.getValue(WATERLOGGED) ) {
         pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
      }

      if (!hasAnyFace(pState)) {
         return Blocks.AIR.defaultBlockState();
      } else {
         return hasFace(pState, pDirection) && !canAttachTo(pLevel, pDirection, pNeighborPos, pNeighborState) ? removeFace(pLevel, pPos,pState, getFaceProperty(pDirection)) : pState;
      }
   }

   public FluidState getFluidState( BlockState pState ) {
      return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
   }

   @Override
   public boolean canConnectRedstone( BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction ) {
      return true;
   }

   @Override
   public int getAnalogOutputSignal( BlockState pState, Level pLevel, BlockPos pPos ) {
      return pState.getValue(STATE) != GearState.NONE ? 15 : 0;
   }

   @Override
   public boolean hasAnalogOutputSignal( BlockState pState ) {
      return true;
   }


   public void onPlace( BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving ) {
      if ( !pOldState.is(pState.getBlock()) && !pLevel.isClientSide ) {
         this.updatePowerStrength(pLevel, pPos, pState);

         for ( Direction direction : Direction.Plane.HORIZONTAL ) {
            pLevel.updateNeighborsAt(pPos.relative(direction), this);
         }

         this.updateNeighborsOfNeighboringWires(pLevel, pPos);
      }
   }

   public void onRemove( BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving ) {
      if ( !pIsMoving && !pState.is(pNewState.getBlock()) ) {
         super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
         if ( !pLevel.isClientSide ) {
            for ( Direction direction : Direction.values() ) {
               pLevel.updateNeighborsAt(pPos.relative(direction), this);
            }

//            this.updatePowerStrength(pLevel, pPos, pState);
            this.updateNeighborsOfNeighboringWires(pLevel, pPos);
         }
      }
   }


   public RenderShape getRenderShape( BlockState pState ) {
      return RenderShape.MODEL;
   }


   private void updatePowerStrength( Level pLevel, BlockPos pPos, BlockState pState ) {
      // if it was from a normal redstone signal
//      RedStoneWireBlock
//
//      if (pLevel.hasNeighborSignal(pPos)) {
//         pLevel.setBlock(pPos, pState.setValue(STATE, GearState.CW),2);
//      } else {
//         pLevel.setBlock(pPos, pState.setValue(STATE, GearState.NONE),2);
//      }
//
//
//
//
//      if (pState.getValue(STATE) == GearState.NONE) {
//         if (pLevel.getBlockState(pPos) == pState) {
//            pLevel.setBlock(pPos, pState.setValue(STATE, GearState.CCW), 2);
//         }
//
//         Set<BlockPos> set = Sets.newHashSet();
//         set.add(pPos);
//
//         for(Direction direction : Direction.values()) {
//            set.add(pPos.relative(direction));
//         }
//
//         for(BlockPos blockpos : set) {
//            pLevel.updateNeighborsAt(blockpos, this);
//         }
//      }

   }

   private void updateNeighborsOfNeighboringWires( Level pLevel, BlockPos pPos ) {
      for ( Direction direction : Direction.Plane.HORIZONTAL ) {
         this.checkCornerChangeAt(pLevel, pPos.relative(direction));
      }

      for ( Direction direction1 : Direction.Plane.HORIZONTAL ) {
         BlockPos blockpos = pPos.relative(direction1);
         if ( pLevel.getBlockState(blockpos).isRedstoneConductor(pLevel, blockpos) ) {
            this.checkCornerChangeAt(pLevel, blockpos.above());
         }
         else {
            this.checkCornerChangeAt(pLevel, blockpos.below());
         }
      }
   }



   public void neighborChanged( BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving ) {
      if ( !pLevel.isClientSide ) {

         if (pLevel.getBlockState(pFromPos).is(this)) {
            pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(STATE, switch (pLevel.getBlockState(pFromPos).getValue(STATE)) {
               case CW:
                  yield GearState.CCW;
               case CCW:
                  yield GearState.CW;
               case NONE:
                  yield GearState.NONE;
            }));

//            this.updatePowerStrength(pLevel, pPos, pLevel.getBlockState(pPos));
         }
         else {
            if (pLevel.hasNeighborSignal(pPos)) {
               pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(STATE, GearState.CW));
            }
            else {
               pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(STATE, GearState.NONE));
            }
         }



//         if ( pState.canSurvive(pLevel, pPos) ) {
//            this.updatePowerStrength(pLevel, pPos, pState);
//         }
//         else {
//            dropResources(pState, pLevel, pPos);
//            pLevel.removeBlock(pPos, false);
//         }

      }
   }


}
