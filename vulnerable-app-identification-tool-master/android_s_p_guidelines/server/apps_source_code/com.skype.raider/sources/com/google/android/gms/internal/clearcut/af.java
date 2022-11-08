package com.google.android.gms.internal.clearcut;

enum af {
    SCALAR(false),
    VECTOR(true),
    PACKED_VECTOR(true),
    MAP(false);
    
    private final boolean e;

    private af(boolean z) {
        this.e = z;
    }
}
