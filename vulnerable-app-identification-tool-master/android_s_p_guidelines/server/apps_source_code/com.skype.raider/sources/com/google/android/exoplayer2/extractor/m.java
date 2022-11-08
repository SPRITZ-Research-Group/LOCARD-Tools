package com.google.android.exoplayer2.extractor;

public interface m {

    public static final class a implements m {
        private final long a;

        public a(long durationUs) {
            this.a = durationUs;
        }

        public final boolean i_() {
            return false;
        }

        public final long b() {
            return this.a;
        }

        public final long a(long timeUs) {
            return 0;
        }
    }

    long a(long j);

    long b();

    boolean i_();
}
