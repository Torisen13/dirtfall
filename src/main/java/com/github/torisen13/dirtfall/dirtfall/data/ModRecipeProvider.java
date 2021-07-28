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

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        // Creates a shapeless recipe for turning one EXAMPLE_BLOCK into nine EXAMPLE_ITEMs (block to ingots)
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.EXAMPLE_ITEM.get(), 9)
                .addIngredient(ModBlocks.EXAMPLE_BLOCK.get())
                .addCriterion("has_item", hasItem(ModBlocks.EXAMPLE_BLOCK.get()))
                .build(consumer);

        // Creates a shaped recipe to turn nine EXAMPLE_ITEMs into one EXAMPLE_BLOCK (ingots to block)
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.EXAMPLE_BLOCK.get())
                .key('#', ModItems.EXAMPLE_ITEM.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.EXAMPLE_BLOCK.get()))
                .build(consumer);

        // Creates a smelting recipe for turning one EXAMPLE_ORE into one EXAMPLE_BLOCK
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.EXAMPLE_ORE.get()), ModItems.EXAMPLE_ITEM.get(), 0.7F, 200)
                .addCriterion("has_item", hasItem(ModBlocks.EXAMPLE_ORE.get()))
                .build(consumer, recipeHash("example_item_smelting"));

        // Creates a blasting recipe for turning one EXAMPLE_ORE into one EXAMPLE_BLOCK
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ModBlocks.EXAMPLE_ORE.get()), ModItems.EXAMPLE_ITEM.get(), 0.7F, 200)
                .addCriterion("has_item", hasItem(ModBlocks.EXAMPLE_ORE.get()))
                .build(consumer, recipeHash("example_item_blasting"));
    }

    private static ResourceLocation recipeHash(String path) {
        return new ResourceLocation(Dirtfall.MOD_ID, path);
    }
}
