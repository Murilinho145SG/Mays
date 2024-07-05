package com.mays.impl.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public abstract class BagItemEntity extends ItemEntity {
    public BagItemEntity() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    }
}
