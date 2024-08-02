package com.onesandzer0s.alpha.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.client.model.AModelLayers;
import com.onesandzer0s.alpha.client.model.AlphaBoatModel;
import com.onesandzer0s.alpha.common.entity.AlphaBoat;
import com.onesandzer0s.alpha.common.entity.AlphaPrimedTnt;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class AlphaBoatRenderer extends EntityRenderer<AlphaBoat> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(AlphaMod.MODID, "textures/entity/boat.png");
   private final AlphaBoatModel boatModel;

   public AlphaBoatRenderer( EntityRendererProvider.Context pContext) {
      super(pContext);
      this.shadowRadius = 0.5F;
      this.boatModel = new AlphaBoatModel(pContext.bakeLayer(AModelLayers.BOAT));
   }

   public void render( AlphaBoat pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
      pPoseStack.pushPose();
      pPoseStack.translate(0.0F, 0.375F, 0.0F);
      pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntityYaw));
      float f = (float)pEntity.getHurtTime() - pPartialTicks;
      float f1 = pEntity.getDamage() - pPartialTicks;
      if (f1 < 0.0F) {
         f1 = 0.0F;
      }

      if (f > 0.0F) {
         pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)pEntity.getHurtDir()));
      }

      float f2 = pEntity.getBubbleAngle(pPartialTicks);
      if (!Mth.equal(f2, 0.0F)) {
         pPoseStack.mulPose((new Quaternionf()).setAngleAxis(pEntity.getBubbleAngle(pPartialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
      }

      ResourceLocation resourcelocation = getTextureLocation(pEntity);
      ListModel<AlphaBoat> listmodel = boatModel;
      pPoseStack.scale(-1.0F, -1.0F, 1.0F);
      pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
      listmodel.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
      VertexConsumer vertexconsumer = pBuffer.getBuffer(listmodel.renderType(resourcelocation));
      listmodel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      if (!pEntity.isUnderWater()) {
         VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.waterMask());
         if (listmodel instanceof WaterPatchModel) {
            WaterPatchModel waterpatchmodel = (WaterPatchModel)listmodel;
            waterpatchmodel.waterPatch().render(pPoseStack, vertexconsumer1, pPackedLight, OverlayTexture.NO_OVERLAY);
         }
      }

      pPoseStack.popPose();
      super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
   }

   public ResourceLocation getTextureLocation( AlphaBoat pEntity) {
      return TEXTURE_LOCATION;
   }
}
