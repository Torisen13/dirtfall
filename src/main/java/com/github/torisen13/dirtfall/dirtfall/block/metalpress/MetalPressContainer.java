package com.github.torisen13.dirtfall.dirtfall.block.metalpress;

import com.github.torisen13.dirtfall.dirtfall.MathCraft;
import com.github.torisen13.dirtfall.dirtfall.setup.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

import javax.annotation.Nullable;

public class MetalPressContainer extends Container {
    private final IInventory inventory;
    private IIntArray fields;

    public MetalPressContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(id, playerInventory, new MetalPressTileEntity(), new IntArray(buffer.readByte()));
    }

    public MetalPressContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
        super(ModContainerTypes.METAL_PRESS.get(), id);
        this.inventory = inventory;
        this.fields = fields;

        this.addSlot(new Slot(this.inventory, 0, 56, 35));
        this.addSlot(new Slot(this.inventory, 1, 117, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Represents the Player's Backpack
        for (int y = 0; y < MathCraft.PLAYER_INVENTORY_SIZE_Y; ++y) {
            for (int x = 0; x < MathCraft.PLAYER_INVENTORY_SIZE_X; ++x) {
                int index = x + y * 9;
                int posX = 8 + x * MathCraft.GUI_SLOT_PIXEL_WIDTH;
                int posY = 84 + y * MathCraft.GUI_SLOT_PIXEL_WIDTH;

                this.addSlot(new Slot(playerInventory, index, posX, posY));
            }
        }

        // Represents the Player's hotbar
        for (int x = 0; x < MathCraft.PLAYER_INVENTORY_SIZE_X; ++x) {
            int posX = 8 + x * MathCraft.GUI_SLOT_PIXEL_WIDTH;
            int posY = 84 + 58;
            this.addSlot(new Slot(playerInventory, x, posX, posY));
        }
    }

    public int getProgressArrowScale() {
        int progress = fields.get(0);
        if (progress > 0) {
            return progress * 24 / MetalPressTileEntity.WORK_TIME;
        }
        return 0;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }

    // Called when the Player shift-clicks to move ItemStacks
    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            final int inventorySize = 2;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 1) {
                if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 0) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
