package com.mays.impl.command;

import com.mays.utils.ResourceFormating;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CommandRegistryManager {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("hand")
                .requires(source -> source.hasPermission(2))
                .executes(context -> execute(context.getSource())));
    }

    private static int execute(CommandSourceStack source) {
        if (source.getEntity() instanceof ServerPlayer player) {
            ItemStack item = player.getMainHandItem();
            String itemName = item.getDisplayName().getString();
            player.sendSystemMessage(
                    Component
                            .literal(itemName + ":").withStyle(ChatFormatting.GREEN));
            if (item.getTags().toList().isEmpty()) {
                player.sendSystemMessage(Component.literal("No tags found.").withStyle(ChatFormatting.RED));
                return 1;
            }
            for (TagKey<Item> itemTagKey : item.getTags().toList()) {
                player.sendSystemMessage(
                        ComponentUtils.copyOnClickText(ResourceFormating.getResource(itemTagKey.location()))
                                .withStyle(ChatFormatting.GREEN));
            }
        }
        return 1;
    }
}
