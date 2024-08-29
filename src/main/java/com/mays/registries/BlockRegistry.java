package com.mays.registries;

import com.mays.Mays;
import com.mays.impl.block.BackpackBlock;
import com.mays.impl.block.CrateBlock;
import com.mays.impl.block.EnderChest;
import com.mays.impl.block.LatexExtractorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Mays.MODID);
    public static final RegistryObject<Block> LATEXEXTRACTORBLOCK = REGISTER.register("latex_extractor", () -> new LatexExtractorBlock(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> CRATE = REGISTER.register("crate", CrateBlock::new);
    public static final RegistryObject<BackpackBlock> BACKPACK = REGISTER.register("backpack", BackpackBlock::new);
    public static final RegistryObject<EnderChest> ENDER_CHEST = REGISTER.register("ender_chest", EnderChest::new);
}
