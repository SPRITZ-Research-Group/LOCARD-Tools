package com.skype.slimcore.logging;

import java.util.logging.Logger;

public class MethodTrace {
    public static final Logger a = Logger.getLogger("SkypePerf");
    private Logger b = a;
    private String c;
    private String d;
    private long e;

    public MethodTrace(String source, String methodName) {
        this.d = source;
        this.c = methodName;
        this.b.entering(this.d, this.c);
        this.e = System.currentTimeMillis();
    }

    public final long a() {
        this.b.exiting(this.d, this.c);
        long currentTimeMillis = System.currentTimeMillis() - this.e;
        this.b.info(this.d + "." + this.c + " " + currentTimeMillis + "ms");
        return currentTimeMillis;
    }
}
