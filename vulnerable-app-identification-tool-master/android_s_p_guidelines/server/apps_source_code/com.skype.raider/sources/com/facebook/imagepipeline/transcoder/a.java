package com.facebook.imagepipeline.transcoder;

public final class a {
    private final int a;

    public a(int transcodeStatus) {
        this.a = transcodeStatus;
    }

    public final int a() {
        return this.a;
    }

    public final String toString() {
        return String.format(null, "Status: %d", new Object[]{Integer.valueOf(this.a)});
    }
}
