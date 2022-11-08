package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class z {

    public static final class a {
        private final List<String> a;
        private final Object b;

        private a(Object obj) {
            this.b = ab.a(obj);
            this.a = new ArrayList();
        }

        /* synthetic */ a(Object obj, byte b) {
            this(obj);
        }

        public final a a(String str, @Nullable Object obj) {
            List list = this.a;
            String str2 = (String) ab.a((Object) str);
            String valueOf = String.valueOf(obj);
            list.add(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(valueOf).length()).append(str2).append("=").append(valueOf).toString());
            return this;
        }

        public final String toString() {
            StringBuilder append = new StringBuilder(100).append(this.b.getClass().getSimpleName()).append('{');
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.a.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static a a(Object obj) {
        return new a(obj, (byte) 0);
    }

    public static boolean a(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
