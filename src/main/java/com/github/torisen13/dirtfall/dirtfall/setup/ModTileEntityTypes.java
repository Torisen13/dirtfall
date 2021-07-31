package com.github.torisen13.dirtfall.dirtfall.setup;

import com.github.torisen13.dirtfall.dirtfall.block.metalpress.MetalPressTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModTileEntityTypes {
    // Creates a TileEntity named METAL_PRESS
    public static final RegistryObject<TileEntityType<MetalPressTileEntity>> METAL_PRESS = register(
            "metal_press",
            MetalPressTileEntity::new,
            ModBlocks.METAL_PRESS
    );

    static void register() {}

    // Helper method to register TileEntity objects
    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name,
                                                                                     Supplier<T> factory,
                                                                                     RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () -> {
            // Comment to suppress the warning about passing null
            //noinspection ConstantConditions
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}
