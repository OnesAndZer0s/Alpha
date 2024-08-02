package com.onesandzer0s.alpha.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.onesandzer0s.alpha.common.entity.AlphaPrimedTnt;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class AlphaTntRenderer extends EntityRenderer<AlphaPrimedTnt> {
   private final BlockRenderDispatcher blockRenderer;

   public AlphaTntRenderer(EntityRendererProvider.Context pContext) {
      super(pContext);
      this.shadowRadius = 0.5F;
      this.blockRenderer = pContext.getBlockRenderDispatcher();
   }

   public void render( AlphaPrimedTnt pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
      pPoseStack.pushPose();
      pPoseStack.translate(0.0F, 0.5F, 0.0F);
      int $$6 = pEntity.getFuse();
      if ((float)$$6 - pPartialTicks + 1.0F < 10.0F) {
         float $$7 = 1.0F - ((float)$$6 - pPartialTicks + 1.0F) / 10.0F;
         $$7 = Mth.clamp($$7, 0.0F, 1.0F);
         $$7 *= $$7;
         $$7 *= $$7;
         float $$8 = 1.0F + $$7 * 0.3F;
         pPoseStack.scale($$8, $$8, $$8);
      }

      pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
      pPoseStack.translate(-0.5F, -0.5F, 0.5F);
      pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
      TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, ABlocks.TNT.get().defaultBlockState(), pPoseStack, pBuffer, pPackedLight, $$6 / 5 % 2 == 0);
      pPoseStack.popPose();
      super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
   }

   public ResourceLocation getTextureLocation( AlphaPrimedTnt pEntity) {
      return TextureAtlas.LOCATION_BLOCKS;
   }
}
