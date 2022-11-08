package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbh;

@VisibleForTesting
public interface c {

    @VisibleForTesting
    public static final class a {
        private String a = null;
        private int b = 0;
        private long c = Long.MIN_VALUE;
        private short d = (short) -1;
        private double e;
        private double f;
        private float g;
        private int h = 0;
        private int i = -1;

        public final a a() {
            if (-1 < 0) {
                this.c = -1;
            } else {
                this.c = SystemClock.elapsedRealtime() - 1;
            }
            return this;
        }

        public final a a(double d, double d2, float f) {
            this.d = (short) 1;
            this.e = d;
            this.f = d2;
            this.g = f;
            return this;
        }

        public final a a(int i) {
            this.b = i;
            return this;
        }

        public final a a(String str) {
            this.a = str;
            return this;
        }

        public final a b(int i) {
            this.h = i;
            return this;
        }

        public final c b() {
            if (this.a == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.b == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.b & 4) != 0 && this.i < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.c == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.d == (short) -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.h >= 0) {
                return new zzbh(this.a, this.b, (short) 1, this.e, this.f, this.g, this.c, this.h, this.i);
            } else {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
        }
    }

    String a();
}
