package com.facebook.imagepipeline.f;

import com.facebook.imagepipeline.image.e;

public final class a extends RuntimeException {
    private final e a;

    public a(String message, e encodedImage) {
        super(message);
        this.a = encodedImage;
    }

    public final e a() {
        return this.a;
    }
}
