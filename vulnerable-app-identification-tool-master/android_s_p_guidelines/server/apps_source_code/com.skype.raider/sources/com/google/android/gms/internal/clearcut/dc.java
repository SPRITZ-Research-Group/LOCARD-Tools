package com.google.android.gms.internal.clearcut;

final class dc extends IllegalArgumentException {
    dc(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
