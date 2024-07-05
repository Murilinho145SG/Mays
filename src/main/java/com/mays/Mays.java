package com.mays;

import com.mays.registries.BlockRegistry;
import com.mays.registries.ContainerRegistry;
import com.mays.registries.BlockEntitiesRegistry;
import com.mays.registries.ItemRegistry;
import com.mays.impl.screens.CrateScreen;
import com.mays.impl.screens.LatexExtractorScreen;
import com.mays.impl.screens.bags.BackpackScreen;
import com.mays.impl.screens.bags.BagScreen;
import com.mays.impl.command.CommandRegistryManager;
import com.mays.impl.screens.bags.PocketScreen;
import com.mays.impl.sounds.SoundsRegistry;
import com.mays.utils.ResourceDataLocation;
import com.mays.utils.ResourceFormating;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

@Mod(Mays.MODID)
public class Mays {
    public static final String MODID = "mays";
    public static final Logger LOGGER = LogUtils.getLogger();
    public Mays() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.REGISTER.register(modEventBus);
        ItemRegistry.REGISTER.register(modEventBus);
        ItemRegistry.TABREGISTRY.register(modEventBus);
        ContainerRegistry.CONTAINERS.register(modEventBus);
        BlockEntitiesRegistry.REGISTRY.register(modEventBus);
        SoundsRegistry.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ContainerRegistry.CONTAINER.get(), LatexExtractorScreen::new);
        MenuScreens.register(ContainerRegistry.BAG.get(), BagScreen::new);
        MenuScreens.register(ContainerRegistry.BACKPACK.get(), BackpackScreen::new);
        MenuScreens.register(ContainerRegistry.POCKET.get(), PocketScreen::new);
        MenuScreens.register(ContainerRegistry.CRATE.get(), CrateScreen::new);
    }

    @SubscribeEvent
    public void onRegistryCommand(RegisterCommandsEvent event) {
        CommandRegistryManager.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        String itemId = ResourceFormating.getResource(item.builtInRegistryHolder().key().location());

        List<TagKey<Item>> tags = itemStack.getTags().toList();
        ResourceDataLocation dataLocation = new ResourceDataLocation(MODID, "tooltip_ore/tooltips.json");
        Map<String, Map<String, String>> json = dataLocation.read().getGson();

        if (json.get("item").containsKey(itemId)) {
            event.getToolTip().add(Component.literal(json.get("item").get(itemId)).withStyle(ChatFormatting.AQUA));
        }

        for (TagKey<Item> tag : tags) {
            if (json.get("tags").containsKey(ResourceFormating.getResource(tag.location()))) {
                event.getToolTip().add(Component.literal(json.get("tags").get(tag.location().toString())).withStyle(ChatFormatting.AQUA));
                break;
            }
        }

        ResourceDataLocation simpleLocation = new ResourceDataLocation(MODID, "tooltips/tooltips.json");
        Map<String, Map<String, String>> jsonTooltip = simpleLocation.read().getGson();

        if (jsonTooltip.containsKey(itemId)) {
            event.getToolTip().add(Component.literal(jsonTooltip.get(itemId).get("title")));
        }
    }

}
