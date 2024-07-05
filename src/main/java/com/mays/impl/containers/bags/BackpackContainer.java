package com.mays.impl.containers.bags;

import com.mays.impl.entities.BackpackBlockEntity;
import com.mays.registries.ContainerRegistry;
import com.mays.impl.containers.MaysContainers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BackpackContainer extends MaysContainers {
    public final IItemHandler invetory;
    public final ItemStack containerStack;
    public BackpackBlockEntity backpackBlockEntity;

    public BackpackContainer(int id, Inventory playerInventory, IItemHandler invetory, ItemStack stack) {
        super(ContainerRegistry.BACKPACK.get(), id, 54);
        this.invetory = invetory;
        this.containerStack = stack;
        this.backpackBlockEntity = null;

        for(int row = 0; row < 6; ++row) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new BagSlotItem(invetory, column + row * 9, 8 + column * 18, 17 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 138 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 196));
        }
    }

    public BackpackContainer(int id, Inventory playerInventory, IItemHandler invetory, BackpackBlockEntity backpackBlockEntity, boolean i) {
        this(id, playerInventory, invetory, null);
        this.backpackBlockEntity = backpackBlockEntity;
    }

    public BackpackContainer(int id, Inventory playerInventory, IItemHandler invetory) {
        this(id, playerInventory, invetory, null);
    }



    @Override
    public boolean stillValid(Player player) {
        if (backpackBlockEntity != null) {
            return backpackBlockEntity.stillValid(player);
        }
        return true;
    }

    @Override
    public void removed(Player player) {
        if (containerStack == null) return;
        super.removed(player);
        if (invetory instanceof ItemStackHandler handler) {
            CompoundTag nbt = handler.serializeNBT();
            containerStack.getOrCreateTag().put("Items", nbt);
        }
    }
}
