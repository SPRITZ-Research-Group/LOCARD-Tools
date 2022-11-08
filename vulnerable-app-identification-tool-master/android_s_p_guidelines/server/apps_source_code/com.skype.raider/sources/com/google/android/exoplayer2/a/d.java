package com.google.android.exoplayer2.a;

import android.os.Handler;
import com.google.android.exoplayer2.Format;

public interface d {

    public static final class a {
        private final Handler a;
        private final d b;

        public a(Handler handler, d listener) {
            this.a = listener != null ? (Handler) com.google.android.exoplayer2.d.a.a((Object) handler) : null;
            this.b = listener;
        }

        public final void a(final com.google.android.exoplayer2.decoder.d decoderCounters) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.b.c(decoderCounters);
                    }
                });
            }
        }

        public final void a(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
            if (this.b != null) {
                final String str = decoderName;
                final long j = initializedTimestampMs;
                final long j2 = initializationDurationMs;
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a d;

                    public final void run() {
                    }
                });
            }
        }

        public final void a(final Format format) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.b.b(format);
                    }
                });
            }
        }

        public final void a(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {
            if (this.b != null) {
                final int i = bufferSize;
                final long j = bufferSizeMs;
                final long j2 = elapsedSinceLastFeedMs;
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a d;

                    public final void run() {
                    }
                });
            }
        }

        public final void b(final com.google.android.exoplayer2.decoder.d counters) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        counters.a();
                        this.b.b.d(counters);
                    }
                });
            }
        }

        public final void a(final int audioSessionId) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.b.a(audioSessionId);
                    }
                });
            }
        }
    }

    void a(int i);

    void b(Format format);

    void c(com.google.android.exoplayer2.decoder.d dVar);

    void d(com.google.android.exoplayer2.decoder.d dVar);
}
