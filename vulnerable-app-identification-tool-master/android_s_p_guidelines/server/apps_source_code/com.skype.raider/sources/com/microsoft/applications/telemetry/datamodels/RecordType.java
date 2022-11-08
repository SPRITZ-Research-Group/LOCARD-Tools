package com.microsoft.applications.telemetry.datamodels;

public enum RecordType {
    NotSet(0),
    Event(1),
    PerformanceCounter(2),
    Anomaly(3),
    Prediction(4),
    TraceLog(5),
    EventSourceLog(6),
    HttpLog(7),
    PerformanceCounterAzure(8),
    PerformanceCounterGfs(9),
    __INVALID_ENUM_VALUE(10);
    
    private final int value;

    private RecordType(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    public static RecordType fromValue(int value) {
        switch (value) {
            case 0:
                return NotSet;
            case 1:
                return Event;
            case 2:
                return PerformanceCounter;
            case 3:
                return Anomaly;
            case 4:
                return Prediction;
            case 5:
                return TraceLog;
            case 6:
                return EventSourceLog;
            case 7:
                return HttpLog;
            case 8:
                return PerformanceCounterAzure;
            case 9:
                return PerformanceCounterGfs;
            default:
                return __INVALID_ENUM_VALUE;
        }
    }
}
