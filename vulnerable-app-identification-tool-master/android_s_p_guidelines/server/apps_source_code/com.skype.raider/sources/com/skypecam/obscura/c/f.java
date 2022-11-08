package com.skypecam.obscura.c;

import com.skypecam.obscura.d.b;

public final class f extends RuntimeException {
    private final b a;

    f(String message, b failure) {
        super(message);
        this.a = failure;
    }

    public final b a() {
        return this.a;
    }
}
