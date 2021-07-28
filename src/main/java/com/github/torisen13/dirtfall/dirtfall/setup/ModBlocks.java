package com.github.torisen13.dirtfall.dirtfall.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    // Creates a Block named EXAMPLE_ORE
    public static final RegistryObject<Block> EXAMPLE_ORE = registerBlockWithBlockItem(
            "example_ore",
            () -> new Block(AbstractBlock.Properties
                    .create(Material.ROCK)
                    .hardnessAndResistance(3, 10)
                    .harvestLevel(2)
                    .sound(SoundType.STONE)
                    .harvestTool(ToolType.PICKAXE)),
            ItemGroup.BUILDING_BLOCKS);

    // Creates a Block named EXAMPLE_BLOCK
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlockWithBlockItem(
            "example_block",
            () -> new Block(AbstractBlock.Properties
                    .create(Material.WOOD)
                    .hardnessAndResistance(3, 10)
                    .sound(SoundType.WOOD)
                    .harvestTool(ToolType.AXE)),
            ItemGroup.MISC);

    static void register() {}

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> newBlock = Registration.BLOCKS.register(name, block);
        return newBlock;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithBlockItem(String name, Supplier<T> block, ItemGroup itemGroup) {
        RegistryObject<T> newBlock = registerBlock(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(newBlock.get(), new Item.Properties().group(itemGroup)));
        return newBlock;
    }
}
