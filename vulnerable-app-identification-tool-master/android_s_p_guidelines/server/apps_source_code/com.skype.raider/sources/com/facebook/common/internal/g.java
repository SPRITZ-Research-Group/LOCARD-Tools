package com.facebook.common.internal;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class g {

    public static final class a {
        private final String a;
        private a b;
        private a c;
        private boolean d;

        private static final class a {
            @Nullable
            String a;
            @Nullable
            Object b;
            a c;

            private a() {
            }

            /* synthetic */ a(byte b) {
                this();
            }
        }

        /* synthetic */ a(String x0, byte b) {
            this(x0);
        }

        private a(String className) {
            this.b = new a();
            this.c = this.b;
            this.d = false;
            this.a = (String) h.a((Object) className);
        }

        public final a a(String name, @Nullable Object value) {
            return b(name, value);
        }

        public final a a(String name, boolean value) {
            return b(name, String.valueOf(value));
        }

        public final a a(String name, int value) {
            return b(name, String.valueOf(value));
        }

        public final String toString() {
            boolean omitNullValuesSnapshot = this.d;
            String nextSeparator = "";
            StringBuilder builder = new StringBuilder(32).append(this.a).append('{');
            a valueHolder = this.b.c;
            while (valueHolder != null) {
                if (!omitNullValuesSnapshot || valueHolder.b != null) {
                    builder.append(nextSeparator);
                    nextSeparator = ", ";
                    if (valueHolder.a != null) {
                        builder.append(valueHolder.a).append('=');
                    }
                    builder.append(valueHolder.b);
                }
                valueHolder = valueHolder.c;
            }
            return builder.append('}').toString();
        }

        private a b(String name, @Nullable Object value) {
            a valueHolder = new a();
            this.c.c = valueHolder;
            this.c = valueHolder;
            valueHolder.b = value;
            valueHolder.a = (String) h.a((Object) name);
            return this;
        }
    }

    @CheckReturnValue
    public static boolean a(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static a a(Object self) {
        String replaceAll = self.getClass().getName().replaceAll("\\$[0-9]+", "\\$");
        int lastIndexOf = replaceAll.lastIndexOf(36);
        if (lastIndexOf == -1) {
            lastIndexOf = replaceAll.lastIndexOf(46);
        }
        return new a(replaceAll.substring(lastIndexOf + 1), (byte) 0);
    }
}
