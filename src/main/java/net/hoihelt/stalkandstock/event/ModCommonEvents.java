package net.hoihelt.stalkandstock.event;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = StalkAndStock.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(()-> {
            ComposterBlock.COMPOSTABLES.put(ModItems.CORNCOB.get(),0.55f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CORN_KERNELS.get(),0.35f);
            ComposterBlock.COMPOSTABLES.put(ModItems.COOKED_CORNCOB.get(),0.55f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CORNBREAD.get(),0.65f);
            ComposterBlock.COMPOSTABLES.put(ModItems.YELLOW_TASSEL.get(),0.65f);
            ComposterBlock.COMPOSTABLES.put(ModItems.WILD_CORN.get(),0.40f);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.YELLOW_TASSEL.getId(),ModBlocks.POTTED_YELLOW_TASSEL);
        });
    }
}
