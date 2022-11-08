package com.google.android.gms.common.images.internal;

import android.graphics.drawable.Drawable;
import android.support.v4.util.g;
import java.util.Arrays;

public final class c extends g<a, Drawable> {

    public static final class a {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            return aVar.a == this.a && aVar.b == this.b;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
        }
    }
}
