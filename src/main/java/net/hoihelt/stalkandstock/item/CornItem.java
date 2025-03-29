package net.hoihelt.stalkandstock.item;

import net.hoihelt.stalkandstock.registry.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class CornItem extends Item {
    public CornItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public SoundEvent getEatingSound() {
        return ModSounds.CORN_EAT.get();
    }
}
