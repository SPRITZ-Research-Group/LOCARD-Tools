package com.a.a.a;

import java.io.Closeable;

public abstract class e implements Closeable {

    public enum a {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false);
        
        private final boolean i;

        public static int a() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.i) {
                    i |= 1 << aVar.ordinal();
                }
            }
            return i;
        }

        private a(boolean z) {
            this.i = z;
        }
    }
}
