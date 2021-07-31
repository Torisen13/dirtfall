package com.github.torisen13.dirtfall.dirtfall.crafting.recipe;

import com.github.torisen13.dirtfall.dirtfall.setup.ModRecipes;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class PressingRecipe extends SingleItemRecipe {
    public PressingRecipe(ResourceLocation recipeID,
                          Ingredient ingredient,
                          ItemStack result) {
        super(ModRecipes.Types.PRESSING, ModRecipes.Serializers.PRESSING.get(), recipeID, "", ingredient, result);
    }

    // Every recipe is going to need a 'matches' recipe, try and fail fast to avoid looking through thousands of recipes
    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PressingRecipe> {
        @Override
        public PressingRecipe fromJson(ResourceLocation recipeID, JsonObject json) {
            // Get the ingredient of the recipe from the JSON object
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));

            // Get the resulting item of the recipe from the JSON object
            ResourceLocation itemID = new ResourceLocation(JSONUtils.getAsString(json, "result"));

            // Get the number of resulting items of the recipe from the JSON object, default to 1
            int count = JSONUtils.getAsInt(json, "count", 1);

            // Grab a stack of 'count' items of type 'itemID'
            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemID), count);
            return new PressingRecipe(recipeID, ingredient, result);
        }

        @Nullable
        @Override
        public PressingRecipe fromNetwork(ResourceLocation recipeID, PacketBuffer buffer) {
            // Client side, read the recipe from the buffer
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new PressingRecipe(recipeID, ingredient, result);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, PressingRecipe recipe) {
            // Server side, write the recipe to a buffer and send it to the client
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);

            /** For recipes with more than one ingredient
             * buffer.writeByte(numberOfIngredients);
             * for (int i = 0; i < numberOfIngredients; ++i) {
             *     buffer.writeItem(ingredientCollection[i]);
             * }
             *
             * For reading, it would be the same, but with read operations
             */
        }
    }
}
