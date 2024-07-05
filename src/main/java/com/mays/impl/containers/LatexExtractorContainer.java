package com.mays.impl.containers;

import com.mays.registries.BlockRegistry;
import com.mays.registries.ContainerRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LatexExtractorContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess containerLevelAccess;

    public LatexExtractorContainer(int id, Inventory playerInventory, ContainerLevelAccess containerLevelAccess) {
        super(ContainerRegistry.CONTAINER.get(), id);
        this.containerLevelAccess = containerLevelAccess;

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
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.containerLevelAccess, player, BlockRegistry.LATEXEXTRACTORBLOCK.get());
    }
}
