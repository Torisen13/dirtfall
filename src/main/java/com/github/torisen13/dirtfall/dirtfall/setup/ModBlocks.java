package com.github.torisen13.dirtfall.dirtfall.setup;

import com.github.torisen13.dirtfall.dirtfall.block.metalpress.MetalPressBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
                    .of(Material.STONE)
                    .strength(3, 10)
                    .harvestLevel(2)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .harvestTool(ToolType.PICKAXE)),
            ItemGroup.TAB_BUILDING_BLOCKS);

    // Creates a Block named EXAMPLE_BLOCK
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlockWithBlockItem(
            "example_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.WOOD)
                    .strength(3, 10)
                    .sound(SoundType.WOOD)
                    .harvestTool(ToolType.AXE)),
            ItemGroup.TAB_MISC);
    
    // Create a Block named METAL_PRESS
    public static final RegistryObject<MetalPressBlock> METAL_PRESS = registerBlockWithBlockItem(
            "metal_press",
            () -> new MetalPressBlock(AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(4, 20)
                    .sound(SoundType.METAL)),
            ItemGroup.TAB_MISC);

    static void register() {}

    // Helper method to register a Block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> newBlock = Registration.BLOCKS.register(name, block);
        return newBlock;
    }

    // Helper method to register a Block with a corresponding BlockItem (this one gets used more frequently)
    private static <T extends Block> RegistryObject<T> registerBlockWithBlockItem(String name, Supplier<T> block, ItemGroup itemGroup) {
        RegistryObject<T> newBlock = registerBlock(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(newBlock.get(), new Item.Properties().tab(itemGroup)));
        return newBlock;
    }
}
