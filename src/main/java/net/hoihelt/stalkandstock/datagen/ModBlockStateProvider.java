package net.hoihelt.stalkandstock.datagen;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, StalkAndStock.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeAllBlock(ModBlocks.PACKED_KERNELS);
        cubeAllBlock(ModBlocks.POLISHED_KERNELS);

        slabBlock(((SlabBlock) ModBlocks.POLISHED_KERNELS_SLAB.get()),
                blockTexture(ModBlocks.POLISHED_KERNELS.get()),
                blockTexture(ModBlocks.POLISHED_KERNELS.get()));

        stairsBlock((StairBlock) ModBlocks.POLISHED_KERNEL_STAIRS.get(),
                blockTexture(ModBlocks.POLISHED_KERNELS.get()));

        itemBlock(ModBlocks.POLISHED_KERNELS_SLAB);
        itemBlock(ModBlocks.POLISHED_KERNEL_STAIRS);
        crossBlock(ModBlocks.WILD_CORN.get(),"wild_corn");
        crossBlock(ModBlocks.YELLOW_TASSEL.get(),"yellow_tassel");




    }

    private void itemBlock(RegistryObject<Block> block) {
        simpleBlockItem(block.get(),
                new ModelFile.UncheckedModelFile(StalkAndStock.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private void cubeAllBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(),cubeAll(block.get()));
    }

    private void crossBlock(Block block,String modelName) {
     ModelFile model = models().cross(modelName,blockTexture(block)).renderType("cutout");
       itemModels().singleTexture(modelName,mcLoc("item/generated"),"layer0",blockTexture(block));
     getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
    }


}
