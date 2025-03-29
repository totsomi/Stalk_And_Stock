package net.hoihelt.stalkandstock.registry;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StalkAndStock.MODID);

    public static final RegistryObject<SoundEvent> CORN_EAT = registerSoundEvents("entity.corn.eat");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name,()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(StalkAndStock.MODID,name)));
    }


    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
