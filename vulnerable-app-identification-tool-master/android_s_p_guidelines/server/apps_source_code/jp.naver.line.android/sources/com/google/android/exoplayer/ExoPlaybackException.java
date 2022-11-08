package com.google.android.exoplayer;

public final class ExoPlaybackException extends Exception {
    public final boolean caughtAtTopLevel;

    public ExoPlaybackException(String str) {
        super(str);
        this.caughtAtTopLevel = false;
    }

    public ExoPlaybackException(Throwable th) {
        super(th);
        this.caughtAtTopLevel = false;
    }

    public ExoPlaybackException(String str, Throwable th) {
        super(str, th);
        this.caughtAtTopLevel = false;
    }

    ExoPlaybackException(Throwable th, boolean z) {
        super(th);
        this.caughtAtTopLevel = z;
    }
}
