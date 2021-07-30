package com.github.torisen13.dirtfall.dirtfall.data;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.setup.ModBlocks;
import com.github.torisen13.dirtfall.dirtfall.setup.ModItems;
import com.github.torisen13.dirtfall.dirtfall.setup.ModTags;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    // Official Mojang method is buildShapelessRecipes for registering all recipes
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        // Creates a shapeless recipe for turning one EXAMPLE_BLOCK into nine EXAMPLE_ITEMs (block to ingots)
        ShapelessRecipeBuilder.shapeless(ModItems.EXAMPLE_ITEM.get(), 9)
                .requires(ModBlocks.EXAMPLE_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.EXAMPLE_BLOCK.get()))
                .save(consumer);

        // Creates a shaped recipe to turn nine EXAMPLE_ITEMs into one EXAMPLE_BLOCK (ingots to block)
        ShapedRecipeBuilder.shaped(ModBlocks.EXAMPLE_BLOCK.get())
                .define('#', ModItems.EXAMPLE_ITEM.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModBlocks.EXAMPLE_BLOCK.get()))
                .save(consumer);

        // Creates a smelting recipe for turning one EXAMPLE_ORE into one EXAMPLE_BLOCK
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.EXAMPLE_ORE.get()), ModItems.EXAMPLE_ITEM.get(), 0.7F, 200)
                .unlockedBy("has_item", has(ModBlocks.EXAMPLE_ORE.get()))
                .save(consumer, recipeHash("example_item_smelting"));

        // Creates a blasting recipe for turning one EXAMPLE_ORE into one EXAMPLE_BLOCK
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.EXAMPLE_ORE.get()), ModItems.EXAMPLE_ITEM.get(), 0.7F, 200)
                .unlockedBy("has_item", has(ModBlocks.EXAMPLE_ORE.get()))
                .save(consumer, recipeHash("example_item_blasting"));
    }

    private static ResourceLocation recipeHash(String path) {
        // Creates a unique recipe ID using our MOD_ID combined with a unique name of the recipe
        // Otherwise, recipes will override themselves or other recipes
        return new ResourceLocation(Dirtfall.MOD_ID, path);
    }
}
