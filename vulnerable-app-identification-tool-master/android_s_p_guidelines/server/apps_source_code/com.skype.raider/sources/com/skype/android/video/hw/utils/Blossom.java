package com.skype.android.video.hw.utils;

public class Blossom {
    private final int bits;
    private long bloom;

    public Blossom(int bits) {
        this.bits = bits;
    }

    private static int knuth(int value) {
        return ((value + 3) * value) % 64;
    }

    private long getFamily(int value) {
        int enough = value + this.bits;
        long family = 0;
        for (int value2 = value; value2 < enough; value2++) {
            family |= (long) (1 << knuth(value2));
        }
        return family;
    }

    private static int fold(long value) {
        return (int) ((value >>> 32) ^ value);
    }

    public void put(int value) {
        this.bloom |= getFamily(value);
    }

    public void put(long value) {
        put(fold(value));
    }

    public void put(Object obj) {
        put(obj.hashCode());
    }

    public boolean mayHave(int value) {
        long family = getFamily(value);
        return (this.bloom & family) == family;
    }

    public boolean mayHave(long value) {
        return mayHave(fold(value));
    }

    public boolean mayHave(Object obj) {
        return mayHave(obj.hashCode());
    }

    public void clear() {
        this.bloom = 0;
    }
}
