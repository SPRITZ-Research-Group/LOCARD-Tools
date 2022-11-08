package com.skype.rt;

import android.util.SparseArray;

public enum LogLevel {
    TRACE(10),
    DEBUG6(16),
    DEBUG5(18),
    DEBUG4(20),
    DEBUG3(30),
    DEBUG2(40),
    DEBUG1(50),
    WARN(60),
    ERROR(70),
    FATAL(80),
    META(90);
    
    private static final SparseArray<LogLevel> levelMap = null;
    private final int level;

    static {
        levelMap = new SparseArray();
        LogLevel[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            LogLevel logLevel = values[i];
            levelMap.put(logLevel.value(), logLevel);
            i++;
        }
    }

    public final int value() {
        return this.level;
    }

    public static LogLevel fromInt(int level) {
        return (LogLevel) levelMap.get(level);
    }

    private LogLevel(int level) {
        this.level = level;
    }
}
