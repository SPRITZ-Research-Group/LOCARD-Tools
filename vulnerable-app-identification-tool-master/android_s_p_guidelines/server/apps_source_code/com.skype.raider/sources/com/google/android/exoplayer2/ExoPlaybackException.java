package com.google.android.exoplayer2;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ExoPlaybackException extends Exception {
    public final int a;
    public final int b;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static ExoPlaybackException a(Exception cause, int rendererIndex) {
        return new ExoPlaybackException(1, cause, rendererIndex);
    }

    public static ExoPlaybackException a(IOException cause) {
        return new ExoPlaybackException(0, cause, -1);
    }

    static ExoPlaybackException a(RuntimeException cause) {
        return new ExoPlaybackException(2, cause, -1);
    }

    private ExoPlaybackException(int type, Throwable cause, int rendererIndex) {
        super(null, cause);
        this.a = type;
        this.b = rendererIndex;
    }
}
