package com.mays.impl.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class UniversalWrench extends Item {

    public UniversalWrench(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        if (!level.isClientSide() && player != null) {
            BlockState blockState = level.getBlockState(pos);
            for (Property<?> property : blockState.getProperties()) {
                if (property instanceof DirectionProperty && property.getName().equals("facing")) {
                    Direction currentDirection = blockState.getValue((DirectionProperty) property);
                    player.sendSystemMessage(Component.literal("Current facing direction: " + currentDirection));

                    Direction nextDirection = currentDirection.getClockWise();
                    level.setBlock(pos, blockState.setValue((DirectionProperty) property, nextDirection), 3);
                    player.sendSystemMessage(Component.literal("Changed facing direction to: " + nextDirection));

                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    private void rotateBlock() {

    }
}
