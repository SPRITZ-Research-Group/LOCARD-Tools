package com.facebook.common.i;

import com.facebook.infer.annotation.Functional;

public enum e {
    YES,
    NO,
    UNSET;

    @Functional
    public static e a(boolean bool) {
        return bool ? YES : NO;
    }
}
