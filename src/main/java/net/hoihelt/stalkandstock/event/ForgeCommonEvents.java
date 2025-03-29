package net.hoihelt.stalkandstock.event;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = StalkAndStock.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //TODO make a config for values, whatever farmarers can sell corn in any way.
        //TODO make them collect and grow corn
        //TODO make Wondering Traders Sell them ass well and make it a config
        //TODO spawn corn  farm in hot/temperate villages
        if (event.getType() == VillagerProfession.FARMER) {
            List<VillagerTrades.ItemListing> noviceTrades = event.getTrades().get(2);

            noviceTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD,1),
                    new ItemStack(ModItems.CORNCOB.get(),pRandom.nextInt(11) + 5),12,5,1.5f)));

            noviceTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(ModItems.CORNCOB.get(),pRandom.nextInt(33) + 15),
                    new ItemStack(Items.EMERALD,1),12,5,1.3f)));
        }
    }
}
