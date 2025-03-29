package net.hoihelt.stalkandstock.datagen;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.hoihelt.stalkandstock.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, providerCompletableFuture, lookupCompletableFuture, StalkAndStock.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.CROPS_CORN).add(ModItems.CORNCOB.get());
        this.tag(ModTags.Items.SEEDS_CORN).add(ModItems.CORN_KERNELS.get());

        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(ModItems.CORN_KERNELS.get());
        this.tag(ItemTags.SMALL_FLOWERS).add(ModItems.YELLOW_TASSEL.get());
    }
}
