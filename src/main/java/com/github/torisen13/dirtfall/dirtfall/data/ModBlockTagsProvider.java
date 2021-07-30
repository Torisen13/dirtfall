package com.github.torisen13.dirtfall.dirtfall.data;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.setup.ModBlocks;
import com.github.torisen13.dirtfall.dirtfall.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

// Custom class to attach tags to modded Blocks
public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, Dirtfall.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Attach each Block tag to the corresponding Block
        tag(ModTags.Blocks.ORES_EXAMPLE).add(ModBlocks.EXAMPLE_ORE.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_EXAMPLE).add(ModBlocks.EXAMPLE_BLOCK.get());

        // Add existing generic "ORES" tags to our ores
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_EXAMPLE);
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.STORAGE_BLOCKS_EXAMPLE);
    }
}
