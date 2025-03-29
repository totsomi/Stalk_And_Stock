package net.hoihelt.stalkandstock;

import com.mojang.logging.LogUtils;
import net.hoihelt.stalkandstock.event.ForgeCommonEvents;
import net.hoihelt.stalkandstock.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(StalkAndStock.MODID)
public class StalkAndStock {
    public static final String MODID = "stalkandstock";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StalkAndStock(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ForgeCommonEvents.class);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModCreativeTab.register(modEventBus);
        ModSounds.register(modEventBus);

        ModMiscRegistry.PAINTING_VARIANTS.register(modEventBus);
        ModMiscRegistry.BANNER_PATTERNS.register(modEventBus);

    }
}
