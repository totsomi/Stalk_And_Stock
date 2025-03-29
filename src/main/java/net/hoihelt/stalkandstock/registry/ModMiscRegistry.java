package net.hoihelt.stalkandstock.registry;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMiscRegistry {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, StalkAndStock.MODID);
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS,StalkAndStock.MODID);

    public static final RegistryObject<BannerPattern> MAIZE = BANNER_PATTERNS.register("maize",()-> new BannerPattern("mz"));

    public static final RegistryObject<PaintingVariant> CORNFIELD = PAINTING_VARIANTS.register("cornfield",()-> new PaintingVariant(32,16));
}
