package com.mays.impl.item;

import com.mays.impl.containers.bags.BagContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class BagItem extends BagItemEntity {
    public BagItem() {
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(hand);
            IItemHandler inventory = getInventory(stack, 27);
            MenuProvider menuProvider = new SimpleMenuProvider((id, playerInventory, playerEntity) -> new BagContainer(id, playerInventory, inventory, stack), Component.literal("BagItem"));
            player.openMenu(menuProvider);
        }
        return super.use(level, player, hand);
    }
}
