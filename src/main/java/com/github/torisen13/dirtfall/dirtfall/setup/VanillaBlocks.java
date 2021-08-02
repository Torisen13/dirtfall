package com.github.torisen13.dirtfall.dirtfall.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class VanillaBlocks {
    // Override dirt block
    public static final RegistryObject<Block> DIRT = registerOverrideBlockWithBlockItem("dirt",
            () -> new FallingBlock(AbstractBlock.Properties
                    .of(Material.DIRT, MaterialColor.DIRT)
                    .strength(0.5F)
                    .sound(SoundType.GLASS)),
            ItemGroup.TAB_BUILDING_BLOCKS);

    static void register() {}

    // Helper method to register a Block override
    private static <T extends Block> RegistryObject<T> registerOverrideBlock(String name, Supplier<T> block) {
        RegistryObject<T> newBlock = Registration.OVERRIDE_BLOCKS.register(name, block);
        return newBlock;
    }

    // Helper method to register an override Block with a corresponding BlockItem
    private static <T extends Block> RegistryObject<T> registerOverrideBlockWithBlockItem(String name, Supplier<T> block, ItemGroup itemGroup) {
        RegistryObject<T> newBlock = registerOverrideBlock(name, block);
        Registration.OVERRIDE_ITEMS.register(name, () -> new BlockItem(newBlock.get(), new Item.Properties().tab(itemGroup)));
        return newBlock;
    }
}
