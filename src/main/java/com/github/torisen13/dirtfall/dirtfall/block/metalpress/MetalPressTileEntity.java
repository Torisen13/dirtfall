package com.github.torisen13.dirtfall.dirtfall.block.metalpress;

import com.github.torisen13.dirtfall.dirtfall.Dirtfall;
import com.github.torisen13.dirtfall.dirtfall.crafting.GenericCraftingHelper;
import com.github.torisen13.dirtfall.dirtfall.crafting.recipe.PressingRecipe;
import com.github.torisen13.dirtfall.dirtfall.setup.ModRecipes;
import com.github.torisen13.dirtfall.dirtfall.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class MetalPressTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
    // The total time it takes (in ticks) to finish a job
    static final int WORK_TIME = GenericCraftingHelper.secondsToTicks(5);

    // An incrementing counter used to track progress
    private int progress = 0;

    private NonNullList<ItemStack> items;
    private final LazyOptional<? extends IItemHandler>[] handlers;

    // Allows us to send data over the network, server/client communicate through the Container
    private final IIntArray fields = new IIntArray() {
        @Override
        public int get(int i) {
            switch (i) {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int i, int value) {
            switch (i) {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            // Return the number of fields we have
            return 1;
        }
    };

    public MetalPressTileEntity() {
        super(ModTileEntityTypes.METAL_PRESS.get());
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    void encodeExtraData(PacketBuffer buffer) {
        buffer.writeByte(fields.getCount());
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide) {
            return;
        }

        PressingRecipe recipe = getRecipe();
        if (recipe != null) {
            doWork(recipe);
        } else {
            stopWork();
        }
    }

    @Nullable
    public PressingRecipe getRecipe() {
        if (this.level == null || getItem(0).isEmpty()) {
            return null;
        }
        return this.level.getRecipeManager().getRecipeFor(ModRecipes.Types.PRESSING, this, this.level).orElse(null);
    }

    private ItemStack getWorkOutput(@Nullable PressingRecipe recipe) {
        if (recipe != null) {
            return recipe.assemble(this);
        }
        return ItemStack.EMPTY;
    }

    private void doWork(PressingRecipe recipe) {
        assert this.level != null;

        // The Item in the output slot of our TileEntity
        ItemStack current = getItem(1);

        // The Item that our recipe outputs
        ItemStack output = getWorkOutput(recipe);

        // If there is an item in the output slot of our TileEntity
        if (!current.isEmpty()) {
            // The new stack size of the ItemStack in the output slot
            int newCount = current.getCount() + output.getCount();

            // If there is some random Item in our TileEntity or if we cannot add any more Items to the output stack
            if (!ItemStack.matches(current, output) || newCount > output.getMaxStackSize()) {
                stopWork();
                return;
            }
        }

        // Increment the progress counter if we are still waiting to finish
        if (progress < WORK_TIME) {
            ++progress;
        }

        // In theory, progress should never exceed WORK_TIME, but it doesn't hurt to check
        if (progress >= WORK_TIME) {
            finishWork(recipe, current, output);
        }
    }

    private void stopWork() {
        progress = 0;
    }

    private void finishWork(PressingRecipe recipe, ItemStack current, ItemStack output) {
        if (!current.isEmpty()) {
            current.grow(output.getCount());
        } else {
            setItem(1, output);
        }

        // Reset the progress counter
        progress = 0;

        // Decrement the ItemStack in the input slot
        this.removeItem(0, 1);
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        // Determines which slots are available to see via each side of the TileEntity
        // This allows all slots to be shown via every side
        return new int[]{0, 1};
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        // Determines which slots can have Items placed in them via each side of the TileEntity
        // This allows Items to be placed into every slot from every side of the TileEntity
        return this.canPlaceItem(i, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return true;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Dirtfall.MOD_ID + ".metal_press");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MetalPressContainer(id, playerInventory, this, this.fields);
    }

    @Override
    public int getContainerSize() {
        // This is the size of the container
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return getItem(0).isEmpty() && getItem(1).isEmpty();
    }

    @Override
    public ItemStack getItem(int i) {
        return items.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int amount) {
        return ItemStackHelper.removeItem(items, i, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ItemStackHelper.takeItem(items, i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        items.set(i, itemStack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level != null
                && this.level.getBlockEntity(this.worldPosition) == this
                && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ()) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        super.load(state, tags);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tags, this.items);
        this.progress = tags.getInt("Progress");
    }

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        super.save(tags);
        ItemStackHelper.saveAllItems(tags, this.items);
        tags.putInt("Progress", this.progress);
        return tags;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tags = this.getUpdateTag();
        ItemStackHelper.saveAllItems(tags, this.items);
        return new SUpdateTileEntityPacket(this.worldPosition, 1, tags);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tags = super.getUpdateTag();
        tags.putInt("Progress", this.progress);
        return tags;
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP) {
                return this.handlers[0].cast();
            } else if (side == Direction.DOWN) {
                return this.handlers[1].cast();
            } else {
                return this.handlers[2].cast();
            }
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }
    }
}
