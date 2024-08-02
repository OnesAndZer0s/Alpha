package com.onesandzer0s.alpha.client.events;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.client.renderer.BlockRenderLayers;
import com.onesandzer0s.alpha.client.renderer.entity.*;
import com.onesandzer0s.alpha.common.registry.AEntities;
import com.onesandzer0s.alpha.common.registry.AFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;
import java.io.UncheckedIOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = AlphaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientHandler {

   @SubscribeEvent
   public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
      event.registerEntityRenderer(AEntities.ALPHA_TNT.get(), AlphaTntRenderer::new);
      event.registerEntityRenderer(AEntities.ZOMBIE_PIGMAN.get(), ZombiePigmanRenderer::new);
      event.registerEntityRenderer(AEntities.PIGMAN.get(), PigmanRenderer::new);
      event.registerEntityRenderer(AEntities.BOAT.get(), AlphaBoatRenderer::new);
      event.registerEntityRenderer(AEntities.MOB.get(), AlphaMobRenderer::new);

   }

   @SubscribeEvent
   public static void init(final FMLClientSetupEvent evt) {
      BlockRenderLayers.init(ItemBlockRenderTypes::setRenderLayer);
      ItemBlockRenderTypes.setRenderLayer(AFluids.ALPHA_WATER.get(), RenderType.translucent());
      ItemBlockRenderTypes.setRenderLayer(AFluids.FLOWING_ALPHA_WATER.get(), RenderType.translucent());

//
//      evt.enqueueWork(() -> {
//         ItemProperties.register(ItemsRegistry.ENCHANTERS_SHIELD.get(), new ResourceLocation(ArsNouveau.MODID, "blocking"), (item, resourceLocation, livingEntity, arg4) -> {
//            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == item ? 1.0F : 0.0F;
//         });
//         ItemProperties.register(ItemsRegistry.DOWSING_ROD.get(), new ResourceLocation(ArsNouveau.MODID, "uses"), new ClampedItemPropertyFunction() {
//            @Override
//            public float unclampedCall(ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
//               return switch (pStack.getDamageValue()) {
//                  case 1 -> 0.75f;
//                  case 2 -> 0.50f;
//                  case 3 -> 0.25f;
//                  default -> 1.0f;
//               };
//            }
//         });
//         ItemProperties.register(BlockRegistry.POTION_JAR.asItem(), new ResourceLocation(ArsNouveau.MODID, "amount"), (stack, level, entity, seed) -> {
//            CompoundTag tag = stack.getTag();
//            return tag != null ? (tag.getCompound("BlockEntityTag").getInt("currentFill") / 10000.0F) : 0.0F;
//         });
//         ItemProperties.register(BlockRegistry.SOURCE_JAR.asItem(), new ResourceLocation(ArsNouveau.MODID, "source"), (stack, level, entity, seed) -> {
//            CompoundTag tag = stack.getTag();
//            return tag != null ? (tag.getCompound("BlockEntityTag").getInt("source") / 10000.0F) : 0.0F;
//         });
//      });
   }

}
