package com.github.torisen13.dirtfall.dirtfall.setup;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class ModSurfaceBuilders extends SurfaceBuilder {

    private static final BlockState DIRT = VanillaBlocks.DIRT.get().defaultBlockState();
    private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    private static final BlockState PODZOL = Blocks.PODZOL.defaultBlockState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    private static final BlockState STONE = Blocks.STONE.defaultBlockState();
    private static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.defaultBlockState();
    private static final BlockState SAND = Blocks.SAND.defaultBlockState();
    private static final BlockState RED_SAND = Blocks.RED_SAND.defaultBlockState();
    private static final BlockState WHITE_TERRACOTTA = Blocks.WHITE_TERRACOTTA.defaultBlockState();
    private static final BlockState MYCELIUM = Blocks.MYCELIUM.defaultBlockState();
    private static final BlockState SOUL_SAND = Blocks.SOUL_SAND.defaultBlockState();
    private static final BlockState NETHERRACK = Blocks.NETHERRACK.defaultBlockState();
    private static final BlockState ENDSTONE = Blocks.END_STONE.defaultBlockState();
    private static final BlockState CRIMSON_NYLIUM = Blocks.CRIMSON_NYLIUM.defaultBlockState();
    private static final BlockState WARPED_NYLIUM = Blocks.WARPED_NYLIUM.defaultBlockState();
    private static final BlockState NETHER_WART_BLOCK = Blocks.NETHER_WART_BLOCK.defaultBlockState();
    private static final BlockState WARPED_WART_BLOCK = Blocks.WARPED_WART_BLOCK.defaultBlockState();
    private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
    private static final BlockState BASALT = Blocks.BASALT.defaultBlockState();
    private static final BlockState MAGMA = Blocks.MAGMA_BLOCK.defaultBlockState();

    public static final SurfaceBuilderConfig CONFIG_PODZOL = new SurfaceBuilderConfig(PODZOL, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_GRAVEL = new SurfaceBuilderConfig(GRAVEL, GRAVEL, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_GRASS = new SurfaceBuilderConfig(GRASS_BLOCK, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_STONE = new SurfaceBuilderConfig(STONE, STONE, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_COARSE_DIRT = new SurfaceBuilderConfig(COARSE_DIRT, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_DESERT = new SurfaceBuilderConfig(SAND, SAND, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_OCEAN_SAND = new SurfaceBuilderConfig(GRASS_BLOCK, DIRT, SAND);
    public static final SurfaceBuilderConfig CONFIG_FULL_SAND = new SurfaceBuilderConfig(SAND, SAND, SAND);
    public static final SurfaceBuilderConfig CONFIG_BADLANDS = new SurfaceBuilderConfig(RED_SAND, WHITE_TERRACOTTA, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_MYCELIUM = new SurfaceBuilderConfig(MYCELIUM, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig CONFIG_HELL = new SurfaceBuilderConfig(NETHERRACK, NETHERRACK, NETHERRACK);
    public static final SurfaceBuilderConfig CONFIG_SOUL_SAND_VALLEY = new SurfaceBuilderConfig(SOUL_SAND, SOUL_SAND, SOUL_SAND);
    public static final SurfaceBuilderConfig CONFIG_THEEND = new SurfaceBuilderConfig(ENDSTONE, ENDSTONE, ENDSTONE);
    public static final SurfaceBuilderConfig CONFIG_CRIMSON_FOREST = new SurfaceBuilderConfig(CRIMSON_NYLIUM, NETHERRACK, NETHER_WART_BLOCK);
    public static final SurfaceBuilderConfig CONFIG_WARPED_FOREST = new SurfaceBuilderConfig(WARPED_NYLIUM, NETHERRACK, WARPED_WART_BLOCK);
    public static final SurfaceBuilderConfig CONFIG_BASALT_DELTAS = new SurfaceBuilderConfig(BLACKSTONE, BASALT, MAGMA);


    public ModSurfaceBuilders(Codec p_i232136_1_) {
        super(p_i232136_1_);
    }

    @Override
    public void apply(Random p_205610_1_, IChunk p_205610_2_, Biome p_205610_3_, int p_205610_4_, int p_205610_5_, int p_205610_6_, double p_205610_7_, BlockState p_205610_9_, BlockState p_205610_10_, int p_205610_11_, long p_205610_12_, ISurfaceBuilderConfig p_205610_14_) {
        return;
    }

    //static void register() {}

}
