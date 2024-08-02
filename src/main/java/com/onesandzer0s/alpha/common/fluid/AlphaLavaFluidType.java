package com.onesandzer0s.alpha.common.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.onesandzer0s.alpha.AlphaMod;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class AlphaLavaFluidType extends FluidType {

   public static final ResourceLocation FLUID_STILL_TEXTURE = new  ResourceLocation(AlphaMod.MODID, "block/lava_still");
   public static final ResourceLocation FLUID_FLOWING_TEXTURE = new ResourceLocation(AlphaMod.MODID,"block/lava_flow");

   public AlphaLavaFluidType() {
      super(Properties.create()
              .descriptionId("block.minecraft.lava")
              .canSwim(false)
              .canDrown(false)
              .pathType(BlockPathTypes.LAVA)
              .adjacentPathType(null)
              .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
              .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
              .lightLevel(15)
              .density(3000)
              .viscosity(6000)
              .temperature(1300)
      );
   }

   @Override
   public double motionScale( Entity entity)
   {
      return entity.level().dimensionType().ultraWarm() ? 0.007D : 0.0023333333333333335D;
   }

   @Override
   public void setItemMovement( ItemEntity entity)
   {
      Vec3 vec3 = entity.getDeltaMovement();
      entity.setDeltaMovement(vec3.x * (double)0.95F, vec3.y + (double)(vec3.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3.z * (double)0.95F);
   }



   @Override
   public void initializeClient( Consumer<IClientFluidTypeExtensions> consumer) {
      consumer.accept(new IClientFluidTypeExtensions() {

         @Override
         public ResourceLocation getStillTexture()
         {
            return FLUID_STILL_TEXTURE;
         }

         @Override
         public ResourceLocation getFlowingTexture()
         {
            return FLUID_FLOWING_TEXTURE;
         }

         @Override
         public Vector3f modifyFogColor( Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
         {
            return new Vector3f(0.407F, 0.121F, 0.137F);
         }

         @Override
         public void modifyFogRender( Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
         {
            RenderSystem.setShaderFogStart(0.125F);
            RenderSystem.setShaderFogEnd(5.0F);
         }

      });
   }
}