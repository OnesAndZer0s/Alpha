package com.onesandzer0s.alpha.client.model;

import com.onesandzer0s.alpha.common.entity.ZombiePigman;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;

public class ZombiePigmanModel extends AbstractZombieModel<ZombiePigman> {
   public ZombiePigmanModel( ModelPart pRoot ) {super(pRoot);}

   @Override
   public boolean isAggressive( ZombiePigman pEntity ) {
      return pEntity.isAggressive();
   }

}
