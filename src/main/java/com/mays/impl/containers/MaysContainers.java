package com.mays.impl.containers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class MaysContainers extends AbstractContainerMenu {
    private final int containerSlot;
    protected MaysContainers(@Nullable MenuType<?> menuType, int index, int containerSlot) {
        super(menuType, index);
        this.containerSlot = containerSlot;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int id) {
        return quickMoveStack(player, id, containerSlot);
    }

    public ItemStack quickMoveStack(Player player, int id, int containerSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(id);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if (id < 27) {
                if (!this.moveItemStackTo(itemStack, containerSlot, containerSlot + 36, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack, 0, containerSlot, false)) {
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

}
