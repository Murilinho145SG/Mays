package com.mays.impl.item;

import com.mays.impl.containers.bags.BackpackContainer;
import com.mays.impl.entities.BackpackBlockEntity;
import com.mays.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class BackpackItem extends BagItemEntity {
    public BackpackItem() {
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        ItemStack itemStack = context.getItemInHand();

        if (player != null && player.isCrouching() && !level.isClientSide()) {
            BlockPos placePos = pos.relative(direction);

            if (level.getBlockState(placePos).isAir()) {
                level.setBlock(placePos, BlockRegistry.BACKPACK.get().defaultBlockState(), 3);

                level.playSound(null, placePos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                BlockEntity blockEntity = level.getBlockEntity(placePos);
                if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
                    backpackBlockEntity.loadFromItem(itemStack);
                }

                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            if (player != null && !player.isCrouching()) {
                ItemStack stack = player.getItemInHand(hand);
                IItemHandler inventory = getInventory(stack, 56);
                MenuProvider menuProvider = new SimpleMenuProvider((id, playerInventory, playerEntity) -> new BackpackContainer(id, playerInventory, inventory, stack), Component.literal("Backpack"));
                player.openMenu(menuProvider);
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
