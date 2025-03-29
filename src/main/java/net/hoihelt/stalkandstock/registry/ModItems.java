package net.hoihelt.stalkandstock.registry;


import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.item.CornItem;
import net.hoihelt.stalkandstock.util.ModTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StalkAndStock.MODID);

   public static final RegistryObject<Item> CORNCOB = ITEMS.register("corncob",()->
           new CornItem(new Item.Properties().food(ModFoods.CORNCOB)));

   public static final RegistryObject<Item> COOKED_CORNCOB = ITEMS.register("cooked_corncob",()->
           new CornItem(new Item.Properties().food(ModFoods.COOKED_CORNCOB)));

   public static final RegistryObject<Item> CORNBREAD = ITEMS.register("cornbread",()->
           new Item(new Item.Properties().food(ModFoods.CORNBREAD)));


   public static final RegistryObject<Item> CORN_KERNELS = ITEMS.register("corn_kernels",()->
           new ItemNameBlockItem(ModBlocks.CORN.get(),new Item.Properties()));

   public static final RegistryObject<Item> WILD_CORN = ITEMS.register("wild_corn",()-> new ItemNameBlockItem(ModBlocks.WILD_CORN.get(),new Item.Properties()));
   public static final RegistryObject<Item> YELLOW_TASSEL = ITEMS.register("yellow_tassel",()-> new ItemNameBlockItem(ModBlocks.YELLOW_TASSEL.get(),new Item.Properties()));


   public static final RegistryObject<Item> MAIZE_BANNER_PATTERN = ITEMS.register("maize_banner_pattern",()->
           new BannerPatternItem(ModTags.BannerPatterns.PATTERN_ITEM_MAIZE,new Item.Properties().stacksTo(1).rarity(Rarity.COMMON)));





   public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
   }



}
