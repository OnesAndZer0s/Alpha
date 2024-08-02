package com.onesandzer0s.alpha.common.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.onesandzer0s.alpha.AlphaMod;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class AlphaWaterFluidType extends FluidType {

   public static final ResourceLocation FLUID_STILL_TEXTURE = new  ResourceLocation(AlphaMod.MODID, "block/water_still");
   public static final ResourceLocation FLUID_FLOWING_TEXTURE = new ResourceLocation(AlphaMod.MODID,"block/water_flow");
   public static final ResourceLocation FLUID_UNDERWATER_TEXTURE = new ResourceLocation(AlphaMod.MODID,"textures/block/water_overlay.png");

   public AlphaWaterFluidType() {
      super(Properties.create()
              .descriptionId("block.minecraft.water")
              .fallDistanceModifier(0F)
              .canExtinguish(true)
              .canConvertToSource(true)
              .supportsBoating(true)
              .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
              .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
              .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
              .canHydrate(true)
      );
   }

   @Override
   public @Nullable BlockPathTypes getBlockPathType( FluidState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, boolean canFluidLog)
   {
      return canFluidLog ? super.getBlockPathType(state, level, pos, mob, true) : null;
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
         public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
            return FLUID_UNDERWATER_TEXTURE;
         }

//         @Override
//         public Vector3f modifyFogColor( Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
//         {
//            return new Vector3f(0.407F, 0.121F, 0.137F);
//         }

         @Override
         public int getTintColor() {
            return 0xddffffff;
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