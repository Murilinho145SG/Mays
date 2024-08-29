package com.mays.registries;

import com.mays.Mays;
import com.mays.impl.containers.CrateContainer;
import com.mays.impl.containers.EnderChestContainer;
import com.mays.impl.containers.LatexExtractorContainer;
import com.mays.impl.containers.bags.BackpackContainer;
import com.mays.impl.containers.bags.BagContainer;
import com.mays.impl.containers.bags.PocketContainer;
import com.mays.impl.entities.CrateBlockEntity;
import com.mays.impl.entities.EnderChestEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Mays.MODID);
    public static final RegistryObject<MenuType<LatexExtractorContainer>> CONTAINER = CONTAINERS.register("container", () -> IForgeMenuType.create((id, inv, data) -> new LatexExtractorContainer(id, inv, ContainerLevelAccess.NULL)));
    public static final RegistryObject<MenuType<PocketContainer>> POCKET = CONTAINERS.register("pocket", () -> IForgeMenuType.create((id, inv, data) -> new PocketContainer(id, inv, new ItemStackHandler(9), ItemStack.EMPTY)));
    public static final RegistryObject<MenuType<BagContainer>> BAG = CONTAINERS.register("bag", () -> IForgeMenuType.create((id, inv, data) -> new BagContainer(id, inv, new ItemStackHandler(27), ItemStack.EMPTY)));
    public static final RegistryObject<MenuType<BackpackContainer>> BACKPACK = CONTAINERS.register("backpack", () -> IForgeMenuType.create((id, inv, data) -> new BackpackContainer(id, inv, new ItemStackHandler(56))));
    public static final RegistryObject<MenuType<CrateContainer>> CRATE = CONTAINERS.register("crate", () -> IForgeMenuType.create((id, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level level = inv.player.level();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CrateBlockEntity) {
            return new CrateContainer(id, inv, (CrateBlockEntity) blockEntity);
        }
        return null;
    }));
    public static final RegistryObject<MenuType<EnderChestContainer>> ENDER_CHEST = CONTAINERS.register("ender_chest", () -> IForgeMenuType.create((id, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level level = inv.player.level();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof EnderChestEntity) {
            return new EnderChestContainer(id, inv, (EnderChestEntity) blockEntity);
        }
        return null;
    }));
}
