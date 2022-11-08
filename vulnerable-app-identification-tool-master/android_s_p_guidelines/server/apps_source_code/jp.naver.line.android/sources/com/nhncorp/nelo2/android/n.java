package com.nhncorp.nelo2.android;

public enum n {
    TRACE(10),
    DEBUG(50),
    INFO(100),
    WARN(200),
    ERROR(500),
    FATAL(1000);
    
    private int severity;

    private n(int i) {
        this.severity = i;
    }

    public final int a() {
        return this.severity;
    }
}
