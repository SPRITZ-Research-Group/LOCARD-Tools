package com.skype.react.activationExperiment.models;

public class WakeUpMeta {
    private WakeUpReason reason;
    private WakeResultStatus status;
    public final long time;

    public WakeUpMeta(WakeResultStatus status, long time, WakeUpReason reason) {
        this.status = status;
        this.time = time;
        this.reason = reason;
    }

    public final WakeResultStatus a() {
        return this.status;
    }

    public final void a(WakeResultStatus status) {
        this.status = status;
    }

    public final void a(WakeUpReason reason) {
        this.reason = reason;
    }

    public final WakeUpReason b() {
        return this.reason;
    }
}
