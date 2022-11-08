package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.d;

public interface e {

    public static final class a {
        private final Handler a;
        private final e b;

        public a(Handler handler, e listener) {
            this.a = listener != null ? (Handler) com.google.android.exoplayer2.d.a.a((Object) handler) : null;
            this.b = listener;
        }

        public final void a(final d decoderCounters) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.b.a(decoderCounters);
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
                        this.b.b.a(format);
                    }
                });
            }
        }

        public final void a(final int droppedFrameCount, final long elapsedMs) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a c;

                    public final void run() {
                    }
                });
            }
        }

        public final void a(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            if (this.b != null) {
                final int i = width;
                final int i2 = height;
                final int i3 = unappliedRotationDegrees;
                final float f = pixelWidthHeightRatio;
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a e;

                    public final void run() {
                        this.e.b.a(i, i2, i3, f);
                    }
                });
            }
        }

        public final void a(final Surface surface) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                    }
                });
            }
        }

        public final void b(final d counters) {
            if (this.b != null) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        counters.a();
                        this.b.b.b(counters);
                    }
                });
            }
        }
    }

    void a(int i, int i2, int i3, float f);

    void a(Format format);

    void a(d dVar);

    void b(d dVar);
}
