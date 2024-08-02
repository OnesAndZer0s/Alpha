package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import com.onesandzer0s.alpha.common.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlphaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AEntities {

   public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AlphaMod.MODID);
   static <T extends Entity> RegistryObject<EntityType<T>> registerEntity( String name, EntityType.Builder<T> builder) {
      return ENTITIES.register(name, () -> builder.build(AlphaMod.MODID + ":" + name));
   }


   public static final RegistryObject<EntityType<AlphaPrimedTnt>> ALPHA_TNT = registerEntity("alpha_tnt",
           EntityType.Builder.<AlphaPrimedTnt>of(AlphaPrimedTnt::new, MobCategory.MISC)
                   .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10));

   public static final RegistryObject<EntityType<ZombiePigman>> ZOMBIE_PIGMAN = registerEntity("zombie_pigman",
           EntityType.Builder.<ZombiePigman>of(ZombiePigman::new, MobCategory.MONSTER)
                   .fireImmune().sized(0.6F, 1.95F).clientTrackingRange(8));

   public static final RegistryObject<EntityType<AlphaBoat>> BOAT = registerEntity("boat",
           EntityType.Builder.<AlphaBoat>of(AlphaBoat::new, MobCategory.MISC)
                   .sized(1.5F, 0.6F).clientTrackingRange(10));


   public static final RegistryObject<EntityType<Pigman>> PIGMAN = registerEntity("pigman",
           EntityType.Builder.<Pigman>of(Pigman::new, MobCategory.MISC)
                   .sized(0.6F, 1.95F).clientTrackingRange(8));


   public static final RegistryObject<EntityType<AlphaMob>> MOB = registerEntity("mob",
           EntityType.Builder.<AlphaMob>of(AlphaMob::new, MobCategory.CREATURE)
                   .sized(0.6F, 1.95F).clientTrackingRange(8));


   @SubscribeEvent
   public static void initializeAttributes( EntityAttributeCreationEvent event) {
      event.put(PIGMAN.get(), Pigman.createAttributes().build());
      event.put(ZOMBIE_PIGMAN.get(), ZombiePigman.createAttributes().build());
      event.put(MOB.get(), AlphaMob.createAttributes().build());
   }
}
