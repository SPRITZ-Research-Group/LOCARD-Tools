package com.facebook.imagepipeline.common;

import javax.annotation.Nullable;

public enum d {
    LOW,
    MEDIUM,
    HIGH;

    public static d a(@Nullable d priority1, @Nullable d priority2) {
        if (priority1 == null) {
            return priority2;
        }
        if (priority2 == null) {
            return priority1;
        }
        if (priority1.ordinal() > priority2.ordinal()) {
            return priority1;
        }
        return priority2;
    }
}
