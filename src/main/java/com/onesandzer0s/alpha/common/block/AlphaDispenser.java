package com.onesandzer0s.alpha.common.block;

import com.onesandzer0s.alpha.common.registry.ASounds;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;

import java.lang.reflect.InvocationTargetException;

public class AlphaDispenser extends DispenserBlock {
   public AlphaDispenser( Properties pProperties ) {
      super(pProperties);
   }

   @Override
   protected void dispenseFrom( ServerLevel pLevel, BlockPos pPos) {
      BlockSourceImpl blocksourceimpl = new BlockSourceImpl(pLevel, pPos);
      DispenserBlockEntity dispenserblockentity = blocksourceimpl.getEntity();
      int i = dispenserblockentity.getRandomSlot(pLevel.random);
      if (i < 0) {
         pLevel.playSound(null, pPos, ASounds.EMPTY.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
         pLevel.gameEvent(GameEvent.BLOCK_ACTIVATE, pPos, GameEvent.Context.of(dispenserblockentity.getBlockState()));
      } else {
         ItemStack itemstack = dispenserblockentity.getItem(i);
         DispenseItemBehavior dispenseitembehavior = this.getDispenseMethod(itemstack);
//         if (dispenseitembehavior instanceof AbstractProjectileDispenseBehavior asd) {
//            dispenseitembehavior = new AbstractProjectileDispenseBehavior() {
//               @Override
//               public Projectile getProjectile( Level level, Position position, ItemStack itemStack ) {
//                  Projectile e = asd.getProjectile(level, position, itemStack);
//                  if (e instanceof Arrow arrow) {
//                     arrow.setSoundEvent(ASounds.ARROW.get());
//                     return arrow;
//                  }
//                  return e;
//               }
//
//               @Override
//               public float getPower() {return asd.getPower();}
//
//               @Override
//               public float getUncertainty() {return asd.getUncertainty();}
//
//               @Override
//               public void playSound( BlockSource pSource ) {
//                  pSource.getLevel().playSound(null, pSource.getPos(), ASounds.BOW_SHOOT.get(), SoundSource.BLOCKS, 1.0F, 1.0F / (pSource.getLevel().getRandom().nextFloat() * 0.4F + 1.2F)+0.5f);
////                  super.playSound(pSource);
//               }
//            };
//         }
//         else if (dispenseitembehavior instanceof DefaultDispenseItemBehavior ddib) {
//            dispenseitembehavior = new DefaultDispenseItemBehavior() {
//               @Override
//               protected void playSound( BlockSource pSource ) {
//                    pSource.getLevel().playSound(null, pSource.getPos(), ASounds.CLICK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
//               }
//
//               @Override
//               public ItemStack execute( BlockSource pSource, ItemStack pStack ) {
//                 return ddib.execute(pSource, pStack);
//               }
//            };
//         }
         if (dispenseitembehavior != DispenseItemBehavior.NOOP) {
            dispenserblockentity.setItem(i, dispenseitembehavior.dispense(blocksourceimpl, itemstack));
         }

      }
   }
}
