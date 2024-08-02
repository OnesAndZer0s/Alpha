package com.onesandzer0s.alpha.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.client.model.MobModel;
import com.onesandzer0s.alpha.client.model.PigmanModel;
import com.onesandzer0s.alpha.common.entity.AlphaMob;
import com.onesandzer0s.alpha.common.entity.AlphaPrimedTnt;
import com.onesandzer0s.alpha.common.entity.Pigman;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlphaMobRenderer extends HumanoidMobRenderer<AlphaMob, HumanoidModel<AlphaMob>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(AlphaMod.MODID, "textures/entity/mob.png");

   public AlphaMobRenderer( EntityRendererProvider.Context pContext) {
      super(pContext, new MobModel(pContext.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
   }

   public void render( AlphaMob pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
      super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
   }



   public ResourceLocation getTextureLocation( AlphaMob pEntity) {
      return TEXTURE_LOCATION;
   }
}
