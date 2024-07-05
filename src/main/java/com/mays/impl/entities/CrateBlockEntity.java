package com.mays.impl.entities;

import com.mays.impl.containers.CrateContainer;
import com.mays.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrateBlockEntity extends MaysBlockEntity {
    public CrateBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesRegistry.CRATE_ENTITY.get(), pos, state, 27);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Crate Inventory");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CrateContainer(id, inventory, this);
    }

}
