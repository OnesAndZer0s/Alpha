package com.onesandzer0s.alpha.common.registry;

import com.onesandzer0s.alpha.AlphaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ASounds {
   public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AlphaMod.MODID);

   public static final RegistryObject<SoundEvent> BOW_SHOOT = SOUNDS.register("item.bow.use",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "item.bow.use")));
   public static final RegistryObject<SoundEvent> ARROW = SOUNDS.register("item.arrow.land",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "item.arrow.land")));

   public static final RegistryObject<SoundEvent> MUSIC_DISC_CALM = SOUNDS.register("disc.calm",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "disc.calm")));
   public static final RegistryObject<SoundEvent> MUSIC_DISC_DOG = SOUNDS.register("disc.dog",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "disc.dog")));

   public static final RegistryObject<SoundEvent> DOOR_CLOSE = SOUNDS.register("block.door.close",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.door.close")));

   public static final RegistryObject<SoundEvent> DOOR_OPEN = SOUNDS.register("block.door.open",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.door.open")));

   public static final RegistryObject<SoundEvent> CLICK = SOUNDS.register("block.dispenser.click",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.dispenser.click")));

   public static final RegistryObject<SoundEvent> EMPTY = SOUNDS.register("block.dispenser.empty",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.dispenser.empty")));

   public static final RegistryObject<SoundEvent> EXPLODE = SOUNDS.register("block.tnt.explode",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.tnt.explode")));

   public static final RegistryObject<SoundEvent> LAVA_AMBIENT = SOUNDS.register("block.lava.ambient",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.lava.ambient")));

   public static final RegistryObject<SoundEvent> SPLASH = SOUNDS.register("block.water.splash",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "block.water.splash")));

   public static final RegistryObject<SoundEvent> MOB_HURT = SOUNDS.register("entity.mob.hurt",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "entity.mob.hurt")));

   public static final RegistryObject<SoundEvent> MOB_FALL = SOUNDS.register("entity.mob.fall",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "entity.mob.fall")));
   public static final RegistryObject<SoundEvent> MOB_SMALL_FALL = SOUNDS.register("entity.mob.small_fall",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlphaMod.MODID, "entity.mob.small_fall")));
}