package com.onesandzer0s.alpha.common.registry;

import ca.weblite.objc.Proxy;
import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.block.AlphaChest;
import com.onesandzer0s.alpha.common.block.entity.AlphaChestBlockEntity;
import com.onesandzer0s.alpha.common.block.entity.AlphaFurnaceBlockEntity;
//import com.onesandzer0s.alpha.common.block.entity.AlphaJukeboxBlockEntity;
import com.onesandzer0s.alpha.common.block.entity.AlphaNetherReactorBlockEntity;
import com.onesandzer0s.alpha.common.block.entity.GearBlockEntity;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ABlockEntityTypes {
   public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AlphaMod.MODID);
   public static final RegistryObject<BlockEntityType<AlphaFurnaceBlockEntity>> FURNACE = BLOCK_ENTITY_TYPES.register("furnace",()->BlockEntityType.Builder.of(AlphaFurnaceBlockEntity::new, ABlocks.FURNACE.get()).build(null));
   public static final RegistryObject<BlockEntityType<AlphaChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest",()->BlockEntityType.Builder.of(AlphaChestBlockEntity::new, ABlocks.CHEST.get()).build(null));
   public static final RegistryObject<BlockEntityType<AlphaNetherReactorBlockEntity>> NETHER_REACTOR = BLOCK_ENTITY_TYPES.register("nether_reactor",()->BlockEntityType.Builder.of(AlphaNetherReactorBlockEntity::new, ABlocks.NETHER_REACTOR.get(),ABlocks.NEW_NETHER_REACTOR.get()).build(null));
   public static final RegistryObject<BlockEntityType<GearBlockEntity>> GEAR = BLOCK_ENTITY_TYPES.register("gear",()->BlockEntityType.Builder.of(GearBlockEntity::new, ABlocks.GEAR.get()).build(null));

//   public static final RegistryObject<BlockEntityType<AlphaSpawnerBlockEntity>> MOB_SPAWNER = BLOCK_ENTITY_TYPES.register("mob_spawner", ()->BlockEntityType.Builder.of(AlphaSpawnerBlockEntity::new, ABlocks.SPAWNER.get()).build(null));
//   public static final RegistryObject<BlockEntityType<AlphaJukeboxBlockEntity>> JUKEBOX = BLOCK_ENTITY_TYPES.register("jukebox", ()->BlockEntityType.Builder.of(AlphaJukeboxBlockEntity::new, ABlocks.JUKEBOX.get()).build(null));

}
