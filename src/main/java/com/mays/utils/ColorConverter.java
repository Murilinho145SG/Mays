package com.mays.utils;

public class ColorConverter {
    private final int R;
    private final int G;
    private final int B;

    public ColorConverter(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public int getDecimal() {
        return R * 65536 + G * 256 + B;
    }
}
