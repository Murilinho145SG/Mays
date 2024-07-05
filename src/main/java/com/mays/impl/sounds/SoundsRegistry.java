package com.mays.impl.sounds;

import com.mays.Mays;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsRegistry {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Mays.MODID);
    public static final RegistryObject<SoundEvent> TRY_TRIP = REGISTRY.register("try_trip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Mays.MODID, "try_trip")));
}
