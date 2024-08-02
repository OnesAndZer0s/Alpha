package com.onesandzer0s.alpha.client.renderer;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.registry.ABlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;

import java.util.function.BiConsumer;

public class BlockRenderLayers {
   public static void init( BiConsumer<Block, RenderType> consumer) {
      consumer.accept(ABlocks.SAPLING.get(), RenderType.cutout());
      consumer.accept(ABlocks.DEAD_BUSH.get(), RenderType.cutout());

      consumer.accept(ABlocks.LEAVES.get(), RenderType.cutout());
      consumer.accept(ABlocks.CACTUS.get(), RenderType.cutout());
      consumer.accept(ABlocks.SUGARCANE.get(), RenderType.cutout());
      consumer.accept(ABlocks.GEAR.get(), RenderType.cutout());
      consumer.accept(ABlocks.CAKE.get(), RenderType.solid());


      consumer.accept(ABlocks.GLASS.get(), RenderType.translucent());
      consumer.accept(ABlocks.GLASS_PANE.get(), RenderType.translucent());
      consumer.accept(ABlocks.ICE.get(), RenderType.translucent());
      consumer.accept(ABlocks.ALPHA_WATER.get(), RenderType.translucent());





//      consumer.accept(ABlocks.OLD_ALPHA_LAVA.get(), RenderHelper.ALPHA_LAVA);


      BuiltInRegistries.BLOCK.stream().filter(b -> BuiltInRegistries.BLOCK.getKey(b).getNamespace().equals(AlphaMod.MODID))
              .forEach(b -> {
                 if (b instanceof FlowerBlock || b instanceof FlowerPotBlock ) {
                    consumer.accept(b, RenderType.cutout());
                 }
              });


   }
   }
