package com.google.android.exoplayer.extractor;

public interface SeekMap {
    public static final SeekMap UNSEEKABLE = new SeekMap() {
        public final long getPosition(long j) {
            return 0;
        }

        public final boolean isSeekable() {
            return false;
        }
    };

    long getPosition(long j);

    boolean isSeekable();
}
