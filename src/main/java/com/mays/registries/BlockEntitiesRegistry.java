package com.mays.registries;

import com.mays.Mays;
import com.mays.impl.entities.BackpackBlockEntity;
import com.mays.impl.entities.CrateBlockEntity;
import com.mays.impl.entities.EnderChestEntity;
import com.mays.registries.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesRegistry {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Mays.MODID);
    public static final RegistryObject<BlockEntityType<CrateBlockEntity>> CRATE_ENTITY = REGISTRY.register("crate_entity",
            () -> BlockEntityType.Builder.of(CrateBlockEntity::new, BlockRegistry.CRATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BackpackBlockEntity>> BACKPACK_ENTITY = REGISTRY.register("backpack_entity",
            () -> BlockEntityType.Builder.of(BackpackBlockEntity::new, BlockRegistry.BACKPACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnderChestEntity>> ENDER_CHEST_ENTITY = REGISTRY.register("ender_chest_entity",
            () -> BlockEntityType.Builder.of(EnderChestEntity::new, BlockRegistry.ENDER_CHEST.get()).build(null));
}
