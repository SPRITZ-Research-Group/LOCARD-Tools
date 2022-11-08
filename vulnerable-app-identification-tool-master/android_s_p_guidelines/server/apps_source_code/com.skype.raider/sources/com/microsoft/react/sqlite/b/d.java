package com.microsoft.react.sqlite.b;

import com.microsoft.b.a.c;

public final class d {
    public static String a(Throwable th) {
        switch (c.b(th)) {
            case SQLITE_ERROR:
                return "5";
            case SQLITE_FULL:
                return "4";
            case SQLITE_CONSTRAINT:
                return "6";
            default:
                return "0";
        }
    }
}
