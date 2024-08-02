package com.onesandzer0s.alpha.client.model;


import com.google.common.collect.ImmutableList;
import com.onesandzer0s.alpha.common.entity.AlphaBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlphaBoatModel extends ListModel<AlphaBoat> implements WaterPatchModel {
   private static final String WATER_PATCH = "water_patch";
   private static final String BOTTOM = "bottom";
   private static final String BACK = "back";
   private static final String FRONT = "front";
   private static final String RIGHT = "right";
   private static final String LEFT = "left";
   private final ModelPart waterPatch;
   private final ImmutableList<ModelPart> parts;

   public AlphaBoatModel(ModelPart pRoot) {
      this.waterPatch = pRoot.getChild("water_patch");
      this.parts = this.createPartsBuilder(pRoot).build();
   }

   protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelPart pRoot) {
      ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
      builder.add(pRoot.getChild("bottom"), pRoot.getChild("back"), pRoot.getChild("front"), pRoot.getChild("right"), pRoot.getChild("left"));
      return builder;
   }

   public static void createChildren( PartDefinition pRoot) {
      int i = 24;
      int j = 6;
      int k = 20;
      int l = 4;
      pRoot.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 8)
              .addBox((-i/2f), (-k/2f+2), -3.0F,i, k-4, 4),
              PartPose.offsetAndRotation(0.0F, l, 0.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));

      pRoot.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0)
              .addBox((-i/2f+2), (-j-1), -1.0F, i-4, j, 2),
              PartPose.offsetAndRotation((-i/2f+1), l, 0, 0.0F, ((float)Math.PI * 3F / 2F), 0.0F));

      pRoot.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0)
              .addBox((-i / 2f + 2),  (-j-1), -1.0F, i-4, j, 2),
              PartPose.offsetAndRotation((i/2f-1), l, 0.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));

      pRoot.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 0)
              .addBox((-i / 2f + 2),  (-j-1), -1.0F, i-4, j, 2),
              PartPose.offsetAndRotation(0.0F, l, (-k/2f+1), 0.0F, (float)Math.PI, 0.0F));

      pRoot.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 0)
              .addBox((-i / 2f + 2),  (-j-1), -1.0F, i-4, j, 2),
              PartPose.offset(0.0F, l, (k/2f-1)));



      pRoot.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(0, 0)
              .addBox(-12.0F, -9.0F, -3.0F, 22.0F, 16.0F, 3.0F),
              PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, 1.5707964F, 0.0F, 0.0F));

   }

   public static LayerDefinition createBodyModel() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      createChildren(partdefinition);
      return LayerDefinition.create(meshdefinition, 64, 32);
   }

   public ImmutableList<ModelPart> parts() {
      return this.parts;
   }

   public ModelPart waterPatch() {
      return this.waterPatch;
   }

   @Override
   public void setupAnim( AlphaBoat pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch ) {

   }
}