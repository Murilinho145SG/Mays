package com.mays.registries;

import com.mays.Mays;
import com.mays.impl.item.BackpackItem;
import com.mays.impl.item.BagItem;
import com.mays.impl.item.PocketItem;
import com.mays.impl.sounds.SoundsRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Mays.MODID);
    public static final RegistryObject<Item> LATEXEXTRACTOR = REGISTER.register("latex_extractor", () -> new BlockItem(BlockRegistry.LATEXEXTRACTORBLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> GLASS_MI = REGISTER.register("glass_mi", () -> new Item(new Item.Properties()));
    public static final RegistryObject<PocketItem> POCKET = REGISTER.register("pocket", PocketItem::new);
    public static final RegistryObject<BagItem> BAG = REGISTER.register("bag", BagItem::new);
    public static final RegistryObject<BackpackItem> BACKPACK = REGISTER.register("backpack", BackpackItem::new);
    public static final RegistryObject<RecordItem> TRY_TRIP = REGISTER.register("music_disc_try_trip", () -> new RecordItem(15, SoundsRegistry.TRY_TRIP, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 259 * 20));
    public static final RegistryObject<Item> HAMMER = REGISTER.register("hammer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCREWDRIVER = REGISTER.register("screwdriver", () -> new Item(new Item.Properties()));

    public static Collection<RegistryObject<Item>> getEntry() {
        return REGISTER.getEntries();
    }

    public static final DeferredRegister<CreativeModeTab> TABREGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mays.MODID);
    public static final RegistryObject<CreativeModeTab> MAYSTAB = TABREGISTRY.register("maystab", ItemRegistry::tab);

    public static CreativeModeTab tab() {
        return CreativeModeTab.builder()
                .icon(HAMMER.get()::getDefaultInstance)
                .title(Component.literal("Mays"))
                .displayItems((i, o) -> {
                    for (RegistryObject<Item> itemRegistryObject : getEntry()) {
                        o.accept(itemRegistryObject.get());
                    }
                })
                .build();
    }
}
