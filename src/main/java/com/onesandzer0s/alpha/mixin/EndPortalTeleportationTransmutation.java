package com.onesandzer0s.alpha.mixin;


import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.AItems;
import com.onesandzer0s.alpha.common.registry.ARecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(EndPortalBlock.class)
public class EndPortalTeleportationTransmutation {





   @Inject(method="Lnet/minecraft/world/level/block/EndPortalBlock;entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)V", at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;changeDimension(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/entity/Entity;"), cancellable=true)
    protected void entityInside( BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, CallbackInfo ci ) {
       if (pEntity instanceof ItemEntity itemE) {
            ItemStack stack = itemE.getItem();
            Item other = pLevel.getRecipeManager().getAllRecipesFor(ARecipeTypes.ALPHA_STONECUTTER.get()).stream().filter(r -> r.getIngredients().get(0).test(stack)).map(r -> r.getResultItem(( RegistryAccess ) null).getItem()).findFirst().orElse(null);
            if (other != null) {
               itemE.setItem(new ItemStack(other, stack.getCount()));
               itemE.setPos(itemE.getX(), itemE.getY()+2, itemE.getZ());
               itemE.setNoGravity(true);
               itemE.setDeltaMovement(0, 0, 0);
                pLevel.playSound(null, itemE.getOnPos(), SoundEvents.ENDER_EYE_DEATH, SoundSource.BLOCKS, 1, pLevel.random.nextFloat() * 0.1F + 0.9F);
                ci.cancel();
            }
       } else if (pEntity instanceof LivingEntity livE) {
          Class other = AlphaMod.MOB_CONVERSION.get(livE.getClass());
          if (other != null) {
             if (pLevel instanceof ServerLevel )
             try {
                Mob newEntity = (Mob) other.getConstructor(Level.class).newInstance(pLevel);
                newEntity.copyPosition(livE);
                newEntity.setPos(newEntity.getX(), newEntity.getY()+1, newEntity.getZ());
                newEntity.finalizeSpawn((ServerLevel) pLevel, pLevel.getCurrentDifficultyAt(newEntity.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                pLevel.addFreshEntity(newEntity);
                newEntity.setDeltaMovement(pLevel.random.nextFloat()-0.5f, 0.5, pLevel.random.nextFloat()-0.5f);
                livE.remove(Entity.RemovalReason.CHANGED_DIMENSION);
                pLevel.playSound(null, pPos, SoundEvents.ENDER_EYE_DEATH, SoundSource.BLOCKS, 1, pLevel.random.nextFloat() * 0.1F + 0.9F);
                ci.cancel();
             } catch (Exception e) {
                e.printStackTrace();
             }
          }

       }
    }
}
