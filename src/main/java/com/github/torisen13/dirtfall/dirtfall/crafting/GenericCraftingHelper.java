package com.github.torisen13.dirtfall.dirtfall.crafting;

// Contains generic methods to assist writing crafting stuff
public class GenericCraftingHelper {
    public GenericCraftingHelper() {}

    // Convert seconds into ticks for setting times for recipes to take
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }
}
