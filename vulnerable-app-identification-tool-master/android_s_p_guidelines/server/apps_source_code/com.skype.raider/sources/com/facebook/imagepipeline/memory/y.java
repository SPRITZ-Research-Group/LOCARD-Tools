package com.facebook.imagepipeline.memory;

public final class y implements ae {
    private static y a = null;

    private y() {
    }

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            if (a == null) {
                a = new y();
            }
            yVar = a;
        }
        return yVar;
    }
}
