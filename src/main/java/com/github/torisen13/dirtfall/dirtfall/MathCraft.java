package com.github.torisen13.dirtfall.dirtfall;

/**
 * Contains generic methods to assist with the constants and values used frequently in development
 */
public class MathCraft {
    public static final int PLAYER_INVENTORY_SIZE_X = 9;
    public static final int PLAYER_INVENTORY_SIZE_Y = 3;
    public static final int GUI_SLOT_PIXEL_WIDTH = 18;

    public MathCraft() {}

    /**
     * Convert seconds into ticks
     */
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }


}
