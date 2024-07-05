package com.mays.impl.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ItemEntity extends Item {
    public ItemEntity(Properties properties) {
        super(properties);
    }

    public IItemHandler getInventory(ItemStack stack, int size) {
        ItemStackHandler itemStackHandler = new ItemStackHandler(size) {
            @Override
            public void deserializeNBT(CompoundTag nbt) {
                super.deserializeNBT(stack.getOrCreateTag().getCompound("Items"));
            }
        };
        itemStackHandler.deserializeNBT(stack.getOrCreateTag());
        return itemStackHandler;
    }
}
