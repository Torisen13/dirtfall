package com.github.torisen13.dirtfall.dirtfall.setup;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {
    // Creates a DeferredRegistry for Blocks and Items
    public static final DeferredRegister<Block> BLOCKS = createDeferredRegister(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Item> ITEMS = createDeferredRegister(ForgeRegistries.ITEMS);

    // Create generic DeferredRegistry Types, wildcarded type
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = createDeferredRegister(ForgeRegistries.CONTAINERS);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = createDeferredRegister(ForgeRegistries.TILE_ENTITIES);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = createDeferredRegister(ForgeRegistries.RECIPE_SERIALIZERS);

    // Helper method to create DeferredRegistries when provided a ForgeRegistries.<TYPE>
    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> createDeferredRegister(IForgeRegistry<T> registryType) {
        return DeferredRegister.create(registryType, Dirtfall.MOD_ID);
    }

    // Attempt to override vanilla blocks
    public static final DeferredRegister<Item> OVERRIDE_ITEMS = createOverrideDeferredRegister(ForgeRegistries.ITEMS);
    public static final DeferredRegister<Block> OVERRIDE_BLOCKS = createOverrideDeferredRegister(ForgeRegistries.BLOCKS);

    // Helper method to create DeferredRegistries that override vanilla registries when provided a ForgeRegistries.<TYPE>
    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> createOverrideDeferredRegister(IForgeRegistry<T> registryType) {
        return DeferredRegister.create(registryType, "minecraft");
    }

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register our DeferredRegistries to add new stuff
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);

        // Register our OverrideDeferredRegistries to modify vanilla stuff
        OVERRIDE_ITEMS.register(modEventBus);
        OVERRIDE_BLOCKS.register(modEventBus);

        // Register our modded stuff
        ModItems.register();
        ModBlocks.register();
        ModContainerTypes.register();
        ModTileEntityTypes.register();
        ModRecipes.register();

        // Register and vanilla overrides
        VanillaBlocks.register();
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Dirtfall.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Client {
        private Client() {}

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModContainerTypes.registerScreens(event);
        }
    }
}
