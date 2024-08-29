package com.mays.impl.entities;

import com.mays.impl.containers.EnderChestContainer;
import com.mays.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnderChestEntity extends MaysBlockEntity {
    public EnderChestEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesRegistry.ENDER_CHEST_ENTITY.get(), pos, state, 27);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Ender Chest");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new EnderChestContainer(id, inventory, this);
    }

}
