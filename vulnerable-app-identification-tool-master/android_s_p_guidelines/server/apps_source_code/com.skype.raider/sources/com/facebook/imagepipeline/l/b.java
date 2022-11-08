package com.facebook.imagepipeline.l;

public class b {
    public static final a a = new b();
    private static volatile c b = null;

    public interface c {
    }

    public interface a {
    }

    private static final class b implements a {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }
    }

    private b() {
    }

    public static boolean a() {
        b();
        return false;
    }

    private static c b() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }
}
