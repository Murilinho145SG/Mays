package com.mays.impl.containers.bags;

import com.mays.registries.ContainerRegistry;
import com.mays.impl.item.BagItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class PocketContainer extends AbstractContainerMenu {
    public final IItemHandler invetory;
    public final ItemStack containerStack;

    public PocketContainer(int id, Inventory playerInventory, IItemHandler invetory, ItemStack stack) {
        super(ContainerRegistry.POCKET.get(), id);
        this.invetory = invetory;
        this.containerStack = stack;

        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 3; ++column) {
                this.addSlot(new BagSlotItem(invetory, column + row * 3, 62 + column * 18, 17 + row * 18));
            }
        }


        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int id) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(id);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if (id < 9) {
                if (!this.moveItemStackTo(itemStack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack);
        }

        return stack;
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (invetory instanceof ItemStackHandler) {
            CompoundTag nbt = ((ItemStackHandler) invetory).serializeNBT();
            containerStack.getOrCreateTag().put("Items", nbt);
            System.out.println("serializeNBT BagContainer Class: " + nbt);
        }
    }

    private static class BagSlot extends SlotItemHandler {
        private final ItemStack itemStack;

        public BagSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, ItemStack itemStack) {
            super(itemHandler, index, xPosition, yPosition);
            this.itemStack = itemStack;
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            if (stack.getItem() instanceof BagItem) {
                return false;
            }
            return super.mayPlace(stack);
        }
    }
}
