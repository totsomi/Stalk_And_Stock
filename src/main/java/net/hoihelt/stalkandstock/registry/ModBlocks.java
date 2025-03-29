package net.hoihelt.stalkandstock.registry;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.block.CornCropBlock;
import net.hoihelt.stalkandstock.block.WildCornBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StalkAndStock.MODID);

    public static final RegistryObject<Block> PACKED_KERNELS = registerBlockItem("packed_kernels", ()->
            new Block(BlockBehaviour.Properties.of()
                    .strength(1.0f,3.0f)
                    .sound(SoundType.NYLIUM)
                    .mapColor(DyeColor.YELLOW)));

    public static final RegistryObject<Block> POLISHED_KERNELS = registerBlockItem("polished_kernels", ()->
            new Block(BlockBehaviour.Properties.copy(PACKED_KERNELS.get()).sound(SoundType.POINTED_DRIPSTONE).strength(1.5f,3.5f)));

    public static final RegistryObject<Block> POLISHED_KERNELS_SLAB = registerBlockItem("polished_kernels_slab", ()->
            new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_KERNELS.get())));

    public static final RegistryObject<Block> POLISHED_KERNEL_STAIRS = registerBlockItem("polished_kernel_stairs", ()->
            new StairBlock(()-> POLISHED_KERNELS.get().defaultBlockState(),BlockBehaviour.Properties.copy(POLISHED_KERNELS.get())));

    public static final RegistryObject<Block> CORN = BLOCKS.register("corn",()-> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> WILD_CORN = BLOCKS.register("wild_corn",()-> new WildCornBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));

    public static final RegistryObject<Block> YELLOW_TASSEL = BLOCKS.register("yellow_tassel",()-> new FlowerBlock(()->MobEffects.DAMAGE_BOOST,3,BlockBehaviour.Properties.copy(Blocks.POPPY)));
    public static final RegistryObject<Block> POTTED_YELLOW_TASSEL = BLOCKS.register("potted_yellow_tassel",()-> flowerPot(YELLOW_TASSEL.get()));


    private static FlowerPotBlock flowerPot(Block pContent, FeatureFlag... pRequiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        if (pRequiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(pRequiredFeatures);
        }

        return new FlowerPotBlock(()-> (FlowerPotBlock)Blocks.FLOWER_POT,()-> pContent,blockbehaviour$properties);
    }

    private static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        ModItems.ITEMS.register(name,()-> new BlockItem(toReturn.get(),new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
    }
