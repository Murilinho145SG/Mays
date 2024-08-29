package com.mays.impl.containers;

import com.mays.impl.containers.bags.BagSlotItem;
import com.mays.impl.entities.EnderChestEntity;
import com.mays.registries.ContainerRegistry;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnderChestContainer extends MaysContainers {
    private final Container container;
    public EnderChestContainer(int id, Inventory inventory, EnderChestEntity container) {
        super(ContainerRegistry.ENDER_CHEST.get(), id, 54);
        this.container = (Container) container;

        for(int row = 0; row < 6; ++row) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new Slot((Container) container, column + row * 9, 8 + column * 18, 17 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 138 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 196));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }
}
