package net.hoihelt.stalkandstock.util;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BannerPatternTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

public class ModTags {

    public static class Blocks{
        private static TagKey<Block> createTag(String namespace, String blockPath) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(namespace,blockPath));
        }

        public static final TagKey<Block> FARMLANDS = createTag("forge","farmlands");
    }

    public static class Items{
        private static TagKey<Item> createTag(String itemPath) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge",itemPath));
        }

        public static final TagKey<Item> CROPS_CORN = createTag("crops/corn");
        public static final TagKey<Item> SEEDS_CORN = createTag("seeds/corn");

    }

    public static class BannerPatterns {
        private static TagKey<BannerPattern> createTag(String bannerPath) {
            return TagKey.create(Registries.BANNER_PATTERN,ResourceLocation.fromNamespaceAndPath(StalkAndStock.MODID,bannerPath));
        }
        public static final TagKey<BannerPattern> PATTERN_ITEM_MAIZE = createTag("pattern_item/maize");
    }
}
