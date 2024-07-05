package com.mays.impl.entities;

import com.mays.impl.containers.bags.BackpackContainer;
import com.mays.impl.item.BackpackItem;
import com.mays.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class BackpackBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(56);
    public BackpackBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesRegistry.BACKPACK_ENTITY.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Backpack");
    }

    public void loadFromItem(ItemStack stack) {
        if (stack.getItem() instanceof BackpackItem) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.contains("Items")) {
                itemHandler.deserializeNBT(tag.getCompound("Items"));
            }
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains("Items")) {
            itemHandler.deserializeNBT(nbt.getCompound("Items"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("Items", itemHandler.serializeNBT());
    }

    public void saveToItem(ItemStack stack) {
        if (stack.getItem() instanceof BackpackItem) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.put("Items", itemHandler.serializeNBT());
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BackpackContainer(i, inventory, itemHandler, this, false);
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }
}
