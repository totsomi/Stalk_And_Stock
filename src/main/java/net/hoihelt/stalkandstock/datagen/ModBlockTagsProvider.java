package net.hoihelt.stalkandstock.datagen;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.hoihelt.stalkandstock.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StalkAndStock.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.PACKED_KERNELS.get());
        
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.POLISHED_KERNELS.get(),
                        ModBlocks.POLISHED_KERNEL_STAIRS.get(),
                        ModBlocks.POLISHED_KERNELS_SLAB.get());

        this.tag(ModTags.Blocks.FARMLANDS)
                .add(Blocks.FARMLAND);

        this.tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_YELLOW_TASSEL.get());
        this.tag(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.YELLOW_TASSEL.get());


    }
}
