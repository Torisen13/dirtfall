package com.github.torisen13.dirtfall.dirtfall.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class ExampleItem {
    public static final RegistryObject<Item> EXAMPLE_ITEM = Registration.ITEMS.register("example_item", () ->
        new Item(new Item.Properties().group(ItemGroup.MISC)));

    static void register() {}
}
