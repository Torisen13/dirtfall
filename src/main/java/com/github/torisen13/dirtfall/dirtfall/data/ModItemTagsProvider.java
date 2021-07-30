package com.github.torisen13.dirtfall.dirtfall.data;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.setup.ModItems;
import com.github.torisen13.dirtfall.dirtfall.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

// Custom class to attach tags to modded Items
public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Dirtfall.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Copy the Block tags onto the BlockItem
        copy(ModTags.Blocks.ORES_EXAMPLE, ModTags.Items.ORES_EXAMPLE);
        copy(ModTags.Blocks.STORAGE_BLOCKS_EXAMPLE, ModTags.Items.STORAGE_BLOCKS_EXAMPLE);

        // Copy any generic tags onto the BlockItems
        copy(Tags.Blocks.ORES, ModTags.Items.ORES_EXAMPLE);
        copy(Tags.Blocks.ORES, ModTags.Items.STORAGE_BLOCKS_EXAMPLE);

        // Attach each Item tag to the corresponding Item
        tag(ModTags.Items.ITEMS_EXAMPLE).add(ModItems.EXAMPLE_ITEM.get());

        // Add any generic tags onto the Items
        tag(Tags.Items.CROPS_WHEAT).addTag(ModTags.Items.ITEMS_EXAMPLE);
    }
}
