package com.onesandzer0s.alpha.common.block.entity;

import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;


public class AlphaChestBlockEntity extends RandomizableContainerBlockEntity {
   private static final int EVENT_SET_OPEN_COUNT = 1;
   private NonNullList<ItemStack> items;
   private final ContainerOpenersCounter openersCounter;
   private LazyOptional<IItemHandlerModifiable> chestHandler;

   protected AlphaChestBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
      super(pType, pPos, pBlockState);
      this.items = NonNullList.withSize(27, ItemStack.EMPTY);
      this.openersCounter = new ContainerOpenersCounter() {
         protected void onOpen( Level p_155357_, BlockPos p_155358_, BlockState p_155359_) {
            AlphaChestBlockEntity.playSound(p_155357_, p_155358_, p_155359_, ASounds.DOOR_CLOSE.get());
         }

         protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState p_155369_) {
            AlphaChestBlockEntity.playSound(p_155367_, p_155368_, p_155369_, ASounds.DOOR_OPEN.get());
         }

         @Override
         protected void openerCountChanged( Level level, BlockPos blockPos, BlockState blockState, int i, int i1 ) {
         }

         protected boolean isOwnContainer( Player p_155355_) {
            if (!(p_155355_.containerMenu instanceof ChestMenu )) {
               return false;
            } else {
               Container container = ((ChestMenu)p_155355_.containerMenu).getContainer();
               return container == AlphaChestBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(AlphaChestBlockEntity.this);
            }
         }
      };
   }

   public AlphaChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
      this(ABlockEntityTypes.CHEST.get(), pPos, pBlockState);
   }

   public int getContainerSize() {
      return 27;
   }

   protected Component getDefaultName() {
      return Component.translatable("container.chest");
   }

   public void load( CompoundTag pTag) {
      super.load(pTag);
      this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      if (!this.tryLoadLootTable(pTag)) {
         ContainerHelper.loadAllItems(pTag, this.items);
      }

   }

   protected void saveAdditional(CompoundTag pTag) {
      super.saveAdditional(pTag);
      if (!this.trySaveLootTable(pTag)) {
         ContainerHelper.saveAllItems(pTag, this.items);
      }
   }
   static void playSound(Level pLevel, BlockPos pPos, BlockState pState, SoundEvent pSound) {
      ChestType chesttype = (ChestType)pState.getValue(ChestBlock.TYPE);
      if (chesttype != ChestType.LEFT) {
         double d0 = (double)pPos.getX() + 0.5;
         double d1 = (double)pPos.getY() + 0.5;
         double d2 = (double)pPos.getZ() + 0.5;
         if (chesttype == ChestType.RIGHT) {
            Direction direction = ChestBlock.getConnectedDirection(pState);
            d0 += (double)direction.getStepX() * 0.5;
            d2 += (double)direction.getStepZ() * 0.5;
         }

         pLevel.playSound((Player)null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, pLevel.random.nextFloat() * 0.1F + 0.9F);
      }

   }

   public boolean triggerEvent(int pId, int pType) {
      if (pId == 1) {
//         this.chestLidController.shouldBeOpen(pType > 0);
         return true;
      } else {
         return super.triggerEvent(pId, pType);
      }
   }

   public void startOpen(Player pPlayer) {
      if (!this.remove && !pPlayer.isSpectator()) {
         this.openersCounter.incrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
      }

   }

   public void stopOpen(Player pPlayer) {
      if (!this.remove && !pPlayer.isSpectator()) {
         this.openersCounter.decrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
      }

   }

   protected NonNullList<ItemStack> getItems() {
      return this.items;
   }

   protected void setItems(NonNullList<ItemStack> pItems) {
      this.items = pItems;
   }

   public static void swapContents( AlphaChestBlockEntity pChest, AlphaChestBlockEntity pOtherChest) {
      NonNullList<ItemStack> nonnulllist = pChest.getItems();
      pChest.setItems(pOtherChest.getItems());
      pOtherChest.setItems(nonnulllist);
   }

   protected AbstractContainerMenu createMenu( int pId, Inventory pPlayer) {
      return ChestMenu.threeRows(pId, pPlayer, this);
   }

   public void setBlockState(BlockState p_155251_) {
      super.setBlockState(p_155251_);
      if (this.chestHandler != null) {
         LazyOptional<?> oldHandler = this.chestHandler;
         this.chestHandler = null;
         oldHandler.invalidate();
      }

   }

   public <T> LazyOptional<T> getCapability( Capability<T> cap, Direction side) {
      if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
         if (this.chestHandler == null) {
            this.chestHandler = LazyOptional.of(this::createHandler);
         }

         return this.chestHandler.cast();
      } else {
         return super.getCapability(cap, side);
      }
   }

   private IItemHandlerModifiable createHandler() {
      BlockState state = this.getBlockState();
      if (!(state.getBlock() instanceof ChestBlock)) {
         return new InvWrapper(this);
      } else {
         Container inv = ChestBlock.getContainer((ChestBlock)state.getBlock(), state, this.getLevel(), this.getBlockPos(), true);
         return new InvWrapper((Container)(inv == null ? this : inv));
      }
   }

   public void invalidateCaps() {
      super.invalidateCaps();
      if (this.chestHandler != null) {
         this.chestHandler.invalidate();
         this.chestHandler = null;
      }

   }

   public void recheckOpen() {
      if (!this.remove) {
         this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
      }

   }

   protected void signalOpenCount(Level pLevel, BlockPos pPos, BlockState pState, int pEventId, int pEventParam) {
      Block block = pState.getBlock();
      pLevel.blockEvent(pPos, block, 1, pEventParam);
   }
}
