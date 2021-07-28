package com.github.torisen13.dirtfall.dirtfall.data;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.data.client.ModBlockStateProvider;
import com.github.torisen13.dirtfall.dirtfall.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Dirtfall.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent) {
        DataGenerator gen = gatherDataEvent.getGenerator();
        ExistingFileHelper exFileHelper = gatherDataEvent.getExistingFileHelper();

        // Generate JSON files for the assets referenced in these classes
        gen.addProvider(new ModBlockStateProvider(gen, exFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, exFileHelper));

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, exFileHelper);

        // Tag modded objects
        gen.addProvider(blockTags);
        gen.addProvider(new ModItemTagsProvider(gen, blockTags, exFileHelper));

        // Generates the loot table for the mod
        gen.addProvider(new ModLootTableProvider(gen));

        // Generates the list of crafting recipes for the mod
        gen.addProvider(new ModRecipeProvider(gen));
    }
}
