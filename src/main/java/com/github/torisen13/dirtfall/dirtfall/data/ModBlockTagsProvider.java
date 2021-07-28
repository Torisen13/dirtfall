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
    protected void registerTags() {
        // Attach each Block tag to the corresponding Block
        getOrCreateBuilder(ModTags.Blocks.ORES_EXAMPLE).add(ModBlocks.EXAMPLE_ORE.get());
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_EXAMPLE).add(ModBlocks.EXAMPLE_BLOCK.get());

        // Add existing generic "ORES" tags to our ores
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_EXAMPLE);
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.STORAGE_BLOCKS_EXAMPLE);
    }
}
