package com.mays.utils;

import net.minecraft.resources.ResourceLocation;

public class ResourceFormating {
    public static String getResource(ResourceLocation location) {
        return location.getNamespace()+":"+location.getPath();
    }
}
