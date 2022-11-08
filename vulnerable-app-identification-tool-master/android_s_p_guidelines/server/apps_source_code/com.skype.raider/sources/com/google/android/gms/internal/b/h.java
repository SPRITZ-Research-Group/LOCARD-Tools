package com.google.android.gms.internal.b;

import java.io.PrintStream;

public final class h {
    private static final i a;
    private static final int b;

    static final class a extends i {
        a() {
        }

        public final void a(Throwable th, Throwable th2) {
        }
    }

    static {
        Integer a;
        i mVar;
        Throwable th;
        try {
            a = a();
            if (a != null) {
                try {
                    if (a.intValue() >= 19) {
                        mVar = new m();
                        a = mVar;
                        b = a == null ? 1 : a.intValue();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            mVar = (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? 1 : null) != null ? new l() : new a();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = null;
            th = th4;
        }
        a = mVar;
        if (a == null) {
        }
        b = a == null ? 1 : a.intValue();
        PrintStream printStream = System.err;
        String name = a.class.getName();
        printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
        th.printStackTrace(System.err);
        mVar = new a();
        a = mVar;
        if (a == null) {
        }
        b = a == null ? 1 : a.intValue();
    }

    private static Integer a() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void a(Throwable th, Throwable th2) {
        a.a(th, th2);
    }
}
