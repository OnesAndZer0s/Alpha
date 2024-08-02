package com.onesandzer0s.alpha.common.block.entity.menu;

import com.google.common.collect.Lists;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import com.onesandzer0s.alpha.common.registry.AMenuTypes;
import com.onesandzer0s.alpha.common.registry.ARecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlphaStonecutterMenu extends AbstractContainerMenu {
   public static final int INPUT_SLOT = 0;
   public static final int RESULT_SLOT = 1;
   private static final int INV_SLOT_START = 2;
   private static final int INV_SLOT_END = 29;
   private static final int USE_ROW_SLOT_START = 29;
   private static final int USE_ROW_SLOT_END = 38;
   private final ContainerLevelAccess access;
   protected final DataSlot selectedRecipeIndex;
   protected final Level level;
   protected List<SingleItemRecipe> recipes;
   private ItemStack input;
   long lastSoundTime;
   final Slot inputSlot;
   protected final Slot resultSlot;
   Runnable slotUpdateListener;
   public final Container container;
   final ResultContainer resultContainer;

   public AlphaStonecutterMenu( int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pBuffer) {
      this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
   }

   public AlphaStonecutterMenu( int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess ) {
      super(MenuType.STONECUTTER, pContainerId);
      this.selectedRecipeIndex = DataSlot.standalone();
      this.recipes = Lists.newArrayList();
      this.input = ItemStack.EMPTY;
      this.slotUpdateListener = () -> {
      };
      this.container = new SimpleContainer(1) {
         public void setChanged() {
            super.setChanged();
            AlphaStonecutterMenu.this.slotsChanged(this);
            AlphaStonecutterMenu.this.slotUpdateListener.run();
         }
      };
      this.resultContainer = new ResultContainer();
      this.access = pAccess;
      this.level = pPlayerInventory.player.level();
      this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
      this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
         public boolean mayPlace( ItemStack p_40362_ ) {
            return false;
         }

         public void onTake( Player p_150672_, ItemStack p_150673_ ) {
            p_150673_.onCraftedBy(p_150672_.level(), p_150672_, p_150673_.getCount());
            AlphaStonecutterMenu.this.resultContainer.awardUsedRecipes(p_150672_, this.getRelevantItems());
            ItemStack aef = AlphaStonecutterMenu.this.inputSlot.remove(1);
            if ( !aef.isEmpty() ) {
               AlphaStonecutterMenu.this.setupResultSlot();
            }

            pAccess.execute(( p_40364_, p_40365_ ) -> {
               long $$2 = p_40364_.getGameTime();
               if ( AlphaStonecutterMenu.this.lastSoundTime != $$2 ) {
                  p_40364_.playSound((Player) null, p_40365_, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                  AlphaStonecutterMenu.this.lastSoundTime = $$2;
               }

            });
            super.onTake(p_150672_, p_150673_);
         }

         private List<ItemStack> getRelevantItems() {
            return List.of(AlphaStonecutterMenu.this.inputSlot.getItem());
         }
      });

      int $$5;
      for ( $$5 = 0; $$5 < 3; ++$$5 ) {
         for ( int $$4 = 0; $$4 < 9; ++$$4 ) {
            this.addSlot(new Slot(pPlayerInventory, $$4 + $$5 * 9 + 9, 8 + $$4 * 18, 84 + $$5 * 18));
         }
      }

      for ( $$5 = 0; $$5 < 9; ++$$5 ) {
         this.addSlot(new Slot(pPlayerInventory, $$5, 8 + $$5 * 18, 142));
      }

      this.addDataSlot(this.selectedRecipeIndex);
   }

   public int getSelectedRecipeIndex() {
      return this.selectedRecipeIndex.get();
   }

   public List<SingleItemRecipe> getRecipes() {
      return this.recipes;
   }

   public int getNumRecipes() {
      return this.recipes.size();
   }

   public boolean hasInputItem() {
      return this.inputSlot.hasItem() && !this.recipes.isEmpty();
   }

   public boolean stillValid( Player pPlayer ) {
      return stillValid(this.access, pPlayer, ABlocks.STONECUTTER.get());
   }

   public boolean clickMenuButton( Player pPlayer, int pId ) {
      if ( this.isValidRecipeIndex(pId) ) {
         this.selectedRecipeIndex.set(pId);
         this.setupResultSlot();
      }

      return true;
   }

   private boolean isValidRecipeIndex( int pRecipeIndex ) {
      return pRecipeIndex >= 0 && pRecipeIndex < this.recipes.size();
   }

   public void slotsChanged( Container pInventory ) {
      ItemStack $$1 = this.inputSlot.getItem();
      if ( !$$1.is(this.input.getItem()) ) {
         this.input = $$1.copy();
         this.setupRecipeList(pInventory, $$1);
      }

   }

   void setupResultSlot() {
      if ( !this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get()) ) {
         SingleItemRecipe $$0 = this.recipes.get(this.selectedRecipeIndex.get());
         ItemStack $$1 = $$0.assemble(this.container, this.level.registryAccess());
         if ( $$1.isItemEnabled(this.level.enabledFeatures()) ) {
            this.resultContainer.setRecipeUsed($$0);
            this.resultSlot.set($$1);
         }
         else {
            this.resultSlot.set(ItemStack.EMPTY);
         }
      }
      else {
         this.resultSlot.set(ItemStack.EMPTY);
      }

      this.broadcastChanges();
   }

   public MenuType<?> getType() {
      return AMenuTypes.STONECUTTER.get();
   }

   public void registerUpdateListener( Runnable pListener ) {
      this.slotUpdateListener = pListener;
   }

   public boolean canTakeItemForPickAll( ItemStack pStack, Slot pSlot ) {
      return pSlot.container != this.resultContainer && super.canTakeItemForPickAll(pStack, pSlot);
   }

   public ItemStack quickMoveStack( Player pPlayer, int pIndex ) {
      ItemStack $$2 = ItemStack.EMPTY;
      Slot $$3 = (Slot) this.slots.get(pIndex);
      if ( $$3 != null && $$3.hasItem() ) {
         ItemStack $$4 = $$3.getItem();
         Item $$5 = $$4.getItem();
         $$2 = $$4.copy();
         if ( pIndex == 1 ) {
            $$5.onCraftedBy($$4, pPlayer.level(), pPlayer);
            if ( !this.moveItemStackTo($$4, 2, 38, true) ) {
               return ItemStack.EMPTY;
            }

            $$3.onQuickCraft($$4, $$2);
         }
         else if ( pIndex == 0 ) {
            if ( !this.moveItemStackTo($$4, 2, 38, false) ) {
               return ItemStack.EMPTY;
            }
         }
         else if ( this.level.getRecipeManager().getRecipeFor(RecipeType.STONECUTTING, new SimpleContainer(new ItemStack[]{ $$4 }), this.level).isPresent() || this.level.getRecipeManager().getRecipeFor(ARecipeTypes.ALPHA_STONECUTTER.get(), new SimpleContainer(new ItemStack[]{ $$4 }), this.level).isPresent()) {
            if ( !this.moveItemStackTo($$4, 0, 1, false) ) {
               return ItemStack.EMPTY;
            }
         }
         else if ( pIndex >= 2 && pIndex < 29 ) {
            if ( !this.moveItemStackTo($$4, 29, 38, false) ) {
               return ItemStack.EMPTY;
            }
         }
         else if ( pIndex >= 29 && pIndex < 38 && !this.moveItemStackTo($$4, 2, 29, false) ) {
            return ItemStack.EMPTY;
         }

         if ( $$4.isEmpty() ) {
            $$3.setByPlayer(ItemStack.EMPTY);
         }

         $$3.setChanged();
         if ( $$4.getCount() == $$2.getCount() ) {
            return ItemStack.EMPTY;
         }

         $$3.onTake(pPlayer, $$4);
         this.broadcastChanges();
      }

      return $$2;
   }

   public void removed( Player pPlayer ) {
      super.removed(pPlayer);
      this.resultContainer.removeItemNoUpdate(1);
      this.access.execute(( p_40313_, p_40314_ ) -> {
         this.clearContainer(pPlayer, this.container);
      });
   }


   protected void setupRecipeList( Container pContainer, ItemStack pStack ) {
      this.recipes.clear();
      this.selectedRecipeIndex.set(-1);
      this.resultSlot.set(ItemStack.EMPTY);
      if ( !pStack.isEmpty() ) {
         this.recipes =
                 Stream.of(this.level.getRecipeManager().getRecipesFor(RecipeType.STONECUTTING, pContainer, this.level),
                         this.level.getRecipeManager().getRecipesFor(ARecipeTypes.ALPHA_STONECUTTER.get(), pContainer, this.level)
                 ).flatMap(Collection::stream).filter(( a ) -> a.matches(pContainer, this.level)).collect(Collectors.toList());
      }

   }
}
