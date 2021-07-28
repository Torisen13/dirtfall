package com.github.torisen13.dirtfall.dirtfall.data.client;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

// Generates JSON files to provide textures and names to items included in the mod.
public class ModItemModelProvider extends ItemModelProvider {
    // Constructor with "hardcoded" mod ID
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Dirtfall.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Blocks follow this same pattern
        withExistingParent("example_block", modLoc("block/example_block"));
        withExistingParent("example_ore", modLoc("block/example_ore"));

        // Grab the existing parent for registered item assets
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        // Create the JSON files for items
        buildItem("example_item", itemGenerated);
    }

    // Helper method to create JSON files for modded items
    // TODO: ABC
    private ItemModelBuilder buildItem(String name, ModelFile itemGenerated) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
