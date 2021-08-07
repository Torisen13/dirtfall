package com.github.torisen13.dirtfall.dirtfall.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags {
    public static final class Blocks {
        // These are the tags created for the Blocks in the mod
        public static final ITag.INamedTag<Block> ORES_EXAMPLE = makeBlockWrapperTag("forge", "ores/example");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_EXAMPLE = makeBlockWrapperTag("forge", "storage_blocks/example");

        // Creates a new WrapperTag when given a namespace and path
        private static ITag.INamedTag<Block> makeBlockWrapperTag(String namespace, String path) {
            return BlockTags.bind(new ResourceLocation(namespace, path).toString());
        }
    }

    public static final class VanillaBlocks {
        // These are the tags created for the Blocks in the mod
        public static final ITag.INamedTag<Block> OVERWRITE_DIRT = makeBlockWrapperTag("minecraft", "dirt");
        public static final ITag.INamedTag<Block> FORGE_DIRT = makeBlockWrapperTag("forge", "dirt");

        // Creates a new WrapperTag when given a namespace and path
        private static ITag.INamedTag<Block> makeBlockWrapperTag(String namespace, String path) {
            return BlockTags.bind(new ResourceLocation(namespace, path).toString());
        }
    }


    public static final class Items {
        // These are the tags created for the BlockItems in the mod
        public static final ITag.INamedTag<Item> ORES_EXAMPLE = makeItemWrapperTag("forge", "ores/example");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_EXAMPLE = makeItemWrapperTag("forge", "storage_blocks/example");

        // These are the tags created for the Items in the mod
        public static final ITag.INamedTag<Item> ITEMS_EXAMPLE = makeItemWrapperTag("forge", "items/example");

        // Creates a new WrapperTag when given a namespace and path
        private static ITag.INamedTag<Item> makeItemWrapperTag(String namespace, String path) {
            return ItemTags.bind(new ResourceLocation(namespace, path).toString());
        }
    }
}
