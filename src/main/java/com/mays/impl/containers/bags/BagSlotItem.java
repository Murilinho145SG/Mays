package com.mays.impl.containers.bags;

import com.mays.impl.item.BagItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class BagSlotItem extends SlotItemHandler {

    public BagSlotItem(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof BagItemEntity) {
            return false;
        }
        return super.mayPlace(stack);
    }

}
