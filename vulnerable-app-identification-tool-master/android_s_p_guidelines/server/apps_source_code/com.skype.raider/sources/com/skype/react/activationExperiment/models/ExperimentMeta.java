package com.skype.react.activationExperiment.models;

import com.skype.react.activationExperiment.Utils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExperimentMeta {
    private ExperimentState experimentState;
    public final String name;
    private long notificationId;
    private String notificationStyle;
    private boolean pendingSend = false;
    private final List<a> statusTimeline;
    private long timeSinceLastLaunch = -1;

    public ExperimentMeta(String name) {
        this.name = name;
        this.statusTimeline = new ArrayList();
    }

    public String toString() {
        return Utils.a((Object) this, (Type) ExperimentMeta.class);
    }

    public final void a(ExperimentState status) {
        a statusMeta = new a();
        statusMeta.a(status);
        statusMeta.a(System.currentTimeMillis());
        this.statusTimeline.add(statusMeta);
    }

    public final void b(ExperimentState status) {
        this.experimentState = status;
    }

    public final void a(boolean value) {
        this.pendingSend = value;
    }

    public final boolean a() {
        return c(ExperimentState.DisplayedToUser);
    }

    public final boolean b() {
        return this.pendingSend;
    }

    public final boolean c() {
        return c(ExperimentState.UserSignedIn) || c(ExperimentState.CancelledUserSignedIn);
    }

    public final boolean d() {
        return c(ExperimentState.UserSignedIn);
    }

    public final boolean a(long minDurationRequiredForRetargeting) {
        if (c() || this.statusTimeline.isEmpty() || !c(ExperimentState.DisplayedToUser)) {
            return false;
        }
        a displayMeta = null;
        for (a meta : this.statusTimeline) {
            if (meta.b() == ExperimentState.DisplayedToUser) {
                displayMeta = meta;
                break;
            }
        }
        if (displayMeta == null || System.currentTimeMillis() - displayMeta.a() <= minDurationRequiredForRetargeting) {
            return false;
        }
        return true;
    }

    final a e() {
        if (this.statusTimeline.size() <= 0) {
            return null;
        }
        return (a) this.statusTimeline.get(this.statusTimeline.size() - 1);
    }

    private boolean c(ExperimentState status) {
        for (a meta : this.statusTimeline) {
            if (meta != null && meta.b() == status) {
                return true;
            }
        }
        return false;
    }

    final long f() {
        return this.notificationId;
    }

    public final void b(long notificationId) {
        this.notificationId = notificationId;
    }

    public final String g() {
        return this.notificationStyle;
    }

    public final void a(String notificationStyle) {
        this.notificationStyle = notificationStyle;
    }

    public final void c(long timeSinceLastLaunch) {
        this.timeSinceLastLaunch = timeSinceLastLaunch;
    }
}
