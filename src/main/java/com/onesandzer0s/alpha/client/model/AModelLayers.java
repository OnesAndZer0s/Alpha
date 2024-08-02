package com.onesandzer0s.alpha.client.model;

import com.onesandzer0s.alpha.AlphaMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;


@OnlyIn(Dist.CLIENT)
public class AModelLayers {
   public static final ModelLayerLocation BOAT = createLocation("boat", "main");


   public static void register( EntityRenderersEvent.RegisterLayerDefinitions event) {
      event.registerLayerDefinition(BOAT, AlphaBoatModel::createBodyModel);
   }
   private static ModelLayerLocation createLocation(String model, String layer) {
      return new ModelLayerLocation(new ResourceLocation(AlphaMod.MODID, model), layer);
   }
}
