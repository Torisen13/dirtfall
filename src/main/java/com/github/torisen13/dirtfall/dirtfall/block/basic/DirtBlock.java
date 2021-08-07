package com.github.torisen13.dirtfall.dirtfall.block.basic;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DirtBlock extends FallingBlock {
    private final int dustColor;

    public DirtBlock(int dustColor, AbstractBlock.Properties properties) {
        super(properties);
        this.dustColor = dustColor;
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos) {
        return this.dustColor;
    }
}
