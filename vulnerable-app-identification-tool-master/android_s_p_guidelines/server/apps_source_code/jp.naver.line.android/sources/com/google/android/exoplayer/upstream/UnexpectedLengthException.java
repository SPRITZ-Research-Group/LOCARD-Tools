package com.google.android.exoplayer.upstream;

import java.io.IOException;

@Deprecated
public final class UnexpectedLengthException extends IOException {
    public final long actualLength;
    public final long expectedLength;

    public UnexpectedLengthException(long j, long j2) {
        StringBuilder stringBuilder = new StringBuilder("Expected: ");
        stringBuilder.append(j);
        stringBuilder.append(", got: ");
        stringBuilder.append(j2);
        super(stringBuilder.toString());
        this.expectedLength = j;
        this.actualLength = j2;
    }
}
