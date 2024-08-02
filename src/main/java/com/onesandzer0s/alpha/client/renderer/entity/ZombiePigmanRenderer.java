package com.onesandzer0s.alpha.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.client.model.ZombiePigmanModel;
import com.onesandzer0s.alpha.common.entity.AlphaPrimedTnt;
import com.onesandzer0s.alpha.common.entity.ZombiePigman;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombiePigmanRenderer extends HumanoidMobRenderer<ZombiePigman, ZombiePigmanModel> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(AlphaMod.MODID, "textures/entity/pigzombie.png");

   public ZombiePigmanRenderer( EntityRendererProvider.Context pContext) {
//      ZombieRenderer
      super(pContext, new ZombiePigmanModel(pContext.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
      this.addLayer(new HumanoidArmorLayer<>(this, new ZombiePigmanModel(pContext.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombiePigmanModel(pContext.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)), pContext.getModelManager()));

   }

   @Override
   public ResourceLocation getTextureLocation( ZombiePigman pEntity ) {
      return TEXTURE_LOCATION;
   }

}
