package com.adjust.sdk;

import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;

public enum ActivityKind {
    UNKNOWN,
    SESSION,
    EVENT,
    CLICK,
    ATTRIBUTION,
    REVENUE,
    REATTRIBUTION,
    INFO;

    public static ActivityKind fromString(String string) {
        if ("session".equals(string)) {
            return SESSION;
        }
        if (EventsEntry.COLUMN_NAME_EVENT.equals(string)) {
            return EVENT;
        }
        if ("click".equals(string)) {
            return CLICK;
        }
        if ("attribution".equals(string)) {
            return ATTRIBUTION;
        }
        if ("info".equals(string)) {
            return INFO;
        }
        return UNKNOWN;
    }

    public final String toString() {
        switch (this) {
            case SESSION:
                return "session";
            case EVENT:
                return EventsEntry.COLUMN_NAME_EVENT;
            case CLICK:
                return "click";
            case ATTRIBUTION:
                return "attribution";
            case INFO:
                return "info";
            default:
                return "unknown";
        }
    }
}
