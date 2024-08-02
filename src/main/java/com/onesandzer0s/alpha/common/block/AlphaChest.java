package com.onesandzer0s.alpha.common.block;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.onesandzer0s.alpha.common.block.entity.AlphaChestBlockEntity;
import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.DoubleBlockCombiner.BlockType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlphaChest extends BaseEntityBlock {
   public static final DirectionProperty FACING;
   public static final EnumProperty<ChestType> TYPE;
   public static final int EVENT_SET_OPEN_COUNT = 1;
   private static final DoubleBlockCombiner.Combiner<AlphaChestBlockEntity, Optional<Container>> CHEST_COMBINER;
   private static final DoubleBlockCombiner.Combiner<AlphaChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER;

   public AlphaChest( BlockBehaviour.Properties pProperties ) {
      super(pProperties);
      this.registerDefaultState((BlockState) ( (BlockState) ( (BlockState) ( (BlockState) this.stateDefinition.any() ).setValue(FACING, Direction.NORTH) ).setValue(TYPE, ChestType.SINGLE) ));
   }

   public static DoubleBlockCombiner.BlockType getBlockType( BlockState p_51583_ ) {
      ChestType chesttype = (ChestType) p_51583_.getValue(TYPE);
      if ( chesttype == ChestType.SINGLE ) {
         return BlockType.SINGLE;
      }
      else {
         return chesttype == ChestType.RIGHT ? BlockType.FIRST : BlockType.SECOND;
      }
   }

   public RenderShape getRenderShape( BlockState pState ) {
//      return RenderShape.ENTITYBLOCK_ANIMATED;
      return RenderShape.MODEL;
   }

   public BlockState updateShape( BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos ) {

      if ( pFacingState.is(this) && pFacing.getAxis().isHorizontal() ) {
         ChestType chesttype = (ChestType) pFacingState.getValue(TYPE);
         if ( pState.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && pState.getValue(FACING) == pFacingState.getValue(FACING) && getConnectedDirection(pFacingState) == pFacing.getOpposite() ) {
            return (BlockState) pState.setValue(TYPE, chesttype.getOpposite());
         }
      }
      else if ( getConnectedDirection(pState) == pFacing ) {
         return (BlockState) pState.setValue(TYPE, ChestType.SINGLE);
      }

      return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
   }

//   public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
//      if (pState.getValue(TYPE) == ChestType.SINGLE) {
//         return AABB;
//      } else {
//         switch (getConnectedDirection(pState)) {
//            case NORTH:
//            default:
//               return NORTH_AABB;
//            case SOUTH:
//               return SOUTH_AABB;
//            case WEST:
//               return WEST_AABB;
//            case EAST:
//               return EAST_AABB;
//         }
//      }
//   }

   public static Direction getConnectedDirection( BlockState p_51585_ ) {
      Direction direction = (Direction) p_51585_.getValue(FACING);
      return p_51585_.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
   }

   public BlockState getStateForPlacement( BlockPlaceContext pContext ) {
      ChestType chesttype = ChestType.SINGLE;
      Direction direction = pContext.getHorizontalDirection().getOpposite();
      FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
      boolean flag = pContext.isSecondaryUseActive();
      Direction direction1 = pContext.getClickedFace();
      if ( direction1.getAxis().isHorizontal() && flag ) {
         Direction direction2 = this.candidatePartnerFacing(pContext, direction1.getOpposite());
         if ( direction2 != null && direction2.getAxis() != direction1.getAxis() ) {
            direction = direction2;
            chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
         }
      }

      if ( chesttype == ChestType.SINGLE && !flag ) {
         if ( direction == this.candidatePartnerFacing(pContext, direction.getClockWise()) ) {
            chesttype = ChestType.LEFT;
         }
         else if ( direction == this.candidatePartnerFacing(pContext, direction.getCounterClockWise()) ) {
            chesttype = ChestType.RIGHT;
         }
      }

      return (BlockState) ( (BlockState) ( (BlockState) this.defaultBlockState().setValue(FACING, direction) ).setValue(TYPE, chesttype) );
   }

   public FluidState getFluidState( BlockState pState ) {
      return Fluids.EMPTY.defaultFluidState();
   }

   @Nullable
   private Direction candidatePartnerFacing( BlockPlaceContext pContext, Direction pDirection ) {
      BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(pDirection));
      return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? (Direction) blockstate.getValue(FACING) : null;
   }

   public void setPlacedBy( Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack ) {
      if ( pStack.hasCustomHoverName() ) {
         BlockEntity blockentity = pLevel.getBlockEntity(pPos);
         if ( blockentity instanceof AlphaChestBlockEntity ) {
            ( (AlphaChestBlockEntity) blockentity ).setCustomName(pStack.getHoverName());
         }
      }

   }

   public void onRemove( BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving ) {
      if ( !pState.is(pNewState.getBlock()) ) {
         BlockEntity blockentity = pLevel.getBlockEntity(pPos);
         if ( blockentity instanceof Container ) {
            Containers.dropContents(pLevel, pPos, (Container) blockentity);
            pLevel.updateNeighbourForOutputSignal(pPos, this);
         }

         super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
      }

   }

   public InteractionResult use( BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit ) {
      if ( pLevel.isClientSide ) {
         return InteractionResult.SUCCESS;
      }
      else {
         MenuProvider menuprovider = this.getMenuProvider(pState, pLevel, pPos);
         if ( menuprovider != null ) {
            pPlayer.openMenu(menuprovider);
            pPlayer.awardStat(this.getOpenChestStat());
            PiglinAi.angerNearbyPiglins(pPlayer, true);
         }

         return InteractionResult.CONSUME;
      }
   }

   protected Stat<ResourceLocation> getOpenChestStat() {
      return Stats.CUSTOM.get(Stats.OPEN_CHEST);
   }

   @Nullable
   public static Container getContainer( AlphaChest pChest, BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride ) {
      return (Container) ( (Optional) pChest.combine(pState, pLevel, pPos, pOverride).apply(CHEST_COMBINER) ).orElse((Container) null);
   }

   public DoubleBlockCombiner.NeighborCombineResult<? extends AlphaChestBlockEntity> combine( BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride ) {
      BiPredicate bipredicate = ( p_51578_, p_51579_ ) -> {
         return false;
      };

      return DoubleBlockCombiner.combineWithNeigbour(ABlockEntityTypes.CHEST.get(), AlphaChest::getBlockType, AlphaChest::getConnectedDirection, FACING, pState, pLevel, pPos, bipredicate);
   }

   @Nullable
   public MenuProvider getMenuProvider( BlockState pState, Level pLevel, BlockPos pPos ) {
      return (MenuProvider) (
              (Optional) this.combine(pState, pLevel, pPos, true)
                      .apply(MENU_PROVIDER_COMBINER) )
              .orElse((MenuProvider) null);
   }

   public BlockEntity newBlockEntity( BlockPos pPos, BlockState pState ) {
      return new AlphaChestBlockEntity(pPos, pState);
   }

   public static boolean isChestBlockedAt( LevelAccessor p_51509_, BlockPos p_51510_ ) {
      return false;
   }

   public boolean hasAnalogOutputSignal( BlockState pState ) {
      return true;
   }

   public int getAnalogOutputSignal( BlockState pBlockState, Level pLevel, BlockPos pPos ) {
      return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, pBlockState, pLevel, pPos, false));
   }

   public BlockState rotate( BlockState pState, Rotation pRotation ) {
      return (BlockState) pState.setValue(FACING, pRotation.rotate((Direction) pState.getValue(FACING)));
   }

   public BlockState mirror( BlockState pState, Mirror pMirror ) {
      BlockState rotated = pState.rotate(pMirror.getRotation((Direction) pState.getValue(FACING)));
      return pMirror == Mirror.NONE ? rotated : (BlockState) rotated.setValue(TYPE, ( (ChestType) rotated.getValue(TYPE) ).getOpposite());
   }

   protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> pBuilder ) {
      pBuilder.add(new Property[]{ FACING, TYPE });
   }

   public boolean isPathfindable( BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType ) {
      return false;
   }

   public void tick( BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom ) {
      BlockEntity blockentity = pLevel.getBlockEntity(pPos);
//      if (blockentity instanceof AlphaChestBlockEntity) {
//         ((AlphaChestBlockEntity)blockentity).recheckOpen();
//      }

   }

   static {
      FACING = HorizontalDirectionalBlock.FACING;
      TYPE = BlockStateProperties.CHEST_TYPE;
      CHEST_COMBINER = new DoubleBlockCombiner.Combiner<AlphaChestBlockEntity, Optional<Container>>() {
         public Optional<Container> acceptDouble( AlphaChestBlockEntity p_51591_, AlphaChestBlockEntity p_51592_ ) {
            return Optional.of(new CompoundContainer(p_51591_, p_51592_));
         }

         public Optional<Container> acceptSingle( AlphaChestBlockEntity p_51589_ ) {
            return Optional.of(p_51589_);
         }

         public Optional<Container> acceptNone() {
            return Optional.empty();
         }
      };
      MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<AlphaChestBlockEntity, Optional<MenuProvider>>() {
         public Optional<MenuProvider> acceptDouble( final AlphaChestBlockEntity p_51604_, final AlphaChestBlockEntity p_51605_ ) {
            final Container container = new CompoundContainer(p_51604_, p_51605_);
            return Optional.of(new MenuProvider() {
               @Nullable
               public AbstractContainerMenu createMenu( int p_51622_, Inventory p_51623_, Player p_51624_ ) {
                  if ( p_51604_.canOpen(p_51624_) && p_51605_.canOpen(p_51624_) ) {
                     p_51604_.unpackLootTable(p_51623_.player);
                     p_51605_.unpackLootTable(p_51623_.player);
                     return ChestMenu.sixRows(p_51622_, p_51623_, container);
                  }
                  else {
                     return null;
                  }
               }

               public Component getDisplayName() {
                  if ( p_51604_.hasCustomName() ) {
                     return p_51604_.getDisplayName();
                  }
                  else {
                     return (Component) ( p_51605_.hasCustomName() ? p_51605_.getDisplayName() : Component.translatable("container.chestDouble") );
                  }
               }
            });
         }

         public Optional<MenuProvider> acceptSingle( AlphaChestBlockEntity p_51602_ ) {
            return Optional.of(p_51602_);
         }

         public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
         }
      };
   }
}
