package com.onesandzer0s.alpha.common.block.entity;

import com.onesandzer0s.alpha.common.registry.ABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GearBlockEntity extends BlockEntity {
   private BlockPos from;
   public GearBlockEntity( BlockPos pPos, BlockState pBlockState ) {
      super(ABlockEntityTypes.GEAR.get(), pPos, pBlockState);
   }


   protected void saveAdditional( CompoundTag pTag) {
      super.saveAdditional(pTag);
      if (this.from != null) {
         pTag.put("from", NbtUtils.writeBlockPos(this.from));
      }
   }

   @Override
   public void load(CompoundTag pTag) {
      super.load(pTag);
        if (pTag.contains("from")) {
             this.from = NbtUtils.readBlockPos(pTag.getCompound("from"));
        }
   }


}
