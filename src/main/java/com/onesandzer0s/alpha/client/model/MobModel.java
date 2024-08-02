package com.onesandzer0s.alpha.client.model;

import com.onesandzer0s.alpha.common.entity.AlphaMob;
import com.onesandzer0s.alpha.common.entity.Pigman;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class MobModel extends HumanoidModel<AlphaMob> {
   public MobModel( ModelPart pRoot ) {super(pRoot);}


   @Override
   public void setupAnim( AlphaMob pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch ) {
      super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
      float time = (float) ((double)System.nanoTime() / 1.0E9D * 10.0D)+pEntity.timeOffs;
      this.head.yRot = (float) Math.sin((double) time * 0.83D);
      this.head.xRot = (float)Math.sin(time) * 0.8F;
      this.rightArm.xRot = (float)Math.sin((double)time * 0.6662D + 3.141592653589793D) * 2.0F;
      this.rightArm.zRot = (float) ( Math.sin((double) time * 0.2312D) + 1.0D );
      this.leftArm.xRot = (float)Math.sin((double)time * 0.6662D) * 2.0F;
      this.leftArm.zRot = (float) ( Math.sin((double) time * 0.2812D) - 1.0D );
      this.rightLeg.xRot = (float)Math.sin((double)time * 0.6662D) * 1.4F;
      this.leftLeg.xRot = (float)Math.sin((double)time * 0.6662D + 3.141592653589793D) * 1.4F;
   }
}
