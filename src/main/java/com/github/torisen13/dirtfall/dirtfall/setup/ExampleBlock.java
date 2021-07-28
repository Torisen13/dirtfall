package com.github.torisen13.dirtfall.dirtfall.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ExampleBlock {
    public static final RegistryObject<Block> EXAMPLE_ORE = registerWithItem("example_ore", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).sound(SoundType.STONE)));

    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerWithItem("example_block", () ->
            new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3, 10).sound(SoundType.METAL)));

    static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    // TODO: Fix this, it keeps getting null as ret, and then trying to .get() the null object
    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        if (ret.isPresent()) {
            Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(ItemGroup.MISC)));
        } else {
            System.err.format("Could not register ItemBlock for block '%s': RegistryObject is not Present\n", name);
        }
        return ret;
    }
}
