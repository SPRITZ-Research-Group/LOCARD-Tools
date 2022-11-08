package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @VisibleForTesting
    public Object b() {
        return null;
    }

    @VisibleForTesting
    public boolean c() {
        return false;
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        FastJsonResponse fastJsonResponse = (FastJsonResponse) obj;
        for (Field field : a().values()) {
            if (a(field)) {
                if (!fastJsonResponse.a(field)) {
                    return false;
                }
                if (!b(field).equals(fastJsonResponse.b(field))) {
                    return false;
                }
            } else if (fastJsonResponse.a(field)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator it = a().values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            Field field = (Field) it.next();
            if (a(field)) {
                i = b(field).hashCode() + (i2 * 31);
            } else {
                i = i2;
            }
        }
    }
}
