package com.github.torisen13.dirtfall.dirtfall.data.client;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Dirtfall.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // For basic blocks, use the simpleBlock method and pass in the block
        simpleBlock(ModBlocks.EXAMPLE_BLOCK.get());
        simpleBlock(ModBlocks.EXAMPLE_ORE.get());
    }
}
