package com.skype.react.activationExperiment.models;

import com.skype.react.activationExperiment.Utils;
import com.skype.react.activationExperiment.models.OEMEcsConfig.UpgradeNotification;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ExperimentPayload {
    private final List<ExperimentMeta> experimentList = new ArrayList();
    private final String schemaVersion = "v1";
    private int totalRetargetAttempts = -1;
    private int totalWakeAttempts = 0;
    private final List<WakeUpMeta> wakeUpList = new ArrayList();

    public final WakeUpMeta a() {
        if (this.wakeUpList.size() <= 0) {
            return null;
        }
        WakeUpMeta lastWakeUp = (WakeUpMeta) this.wakeUpList.get(this.wakeUpList.size() - 1);
        if (lastWakeUp.a() != WakeResultStatus.WakeUpPending) {
            return null;
        }
        return lastWakeUp;
    }

    public final void a(int totalRetargetAttempts) {
        this.totalRetargetAttempts = totalRetargetAttempts;
    }

    public final int b() {
        if (this.totalRetargetAttempts == -1) {
            int totalRetargetAttempts = 0;
            if (!this.experimentList.isEmpty()) {
                for (ExperimentMeta e : this.experimentList) {
                    a lastStatus = e.e();
                    if (lastStatus != null && lastStatus.b() == ExperimentState.SkippedForRetargeting) {
                        totalRetargetAttempts++;
                    }
                }
            }
            this.totalRetargetAttempts = totalRetargetAttempts;
        }
        return this.totalRetargetAttempts;
    }

    public final WakeUpMeta a(long time) {
        for (WakeUpMeta meta : this.wakeUpList) {
            if (meta != null && meta.time == time) {
                return meta;
            }
        }
        return null;
    }

    public final void a(WakeUpMeta wakeUpMeta) {
        this.wakeUpList.add(wakeUpMeta);
    }

    public final void a(ExperimentMeta experimentMeta) {
        this.experimentList.add(experimentMeta);
    }

    public final void c() {
        int upgradeCount = 0;
        List<WakeUpMeta> trimmedList = new ArrayList();
        for (int kk = this.wakeUpList.size() - 1; kk >= 0; kk--) {
            WakeUpMeta meta = (WakeUpMeta) this.wakeUpList.get(kk);
            trimmedList.add(meta);
            if (meta.b() == WakeUpReason.Upgrade) {
                upgradeCount++;
            }
            if (upgradeCount >= 2) {
                break;
            }
        }
        Collections.reverse(trimmedList);
        this.wakeUpList.clear();
        this.wakeUpList.addAll(trimmedList);
    }

    public final int d() {
        int count = 0;
        for (WakeUpMeta b : this.wakeUpList) {
            if (b.b() == WakeUpReason.Upgrade) {
                count = 0;
            } else {
                count++;
            }
        }
        return count;
    }

    public final boolean e() {
        if (this.wakeUpList.size() > 1) {
            return false;
        }
        if (this.wakeUpList.size() == 1 && ((WakeUpMeta) this.wakeUpList.get(0)).a() == WakeResultStatus.WakeUpPending) {
            return true;
        }
        return false;
    }

    public final ExperimentMeta f() {
        if (this.experimentList.isEmpty()) {
            return null;
        }
        return (ExperimentMeta) this.experimentList.get(this.experimentList.size() - 1);
    }

    public final ExperimentMeta b(long notificationId) {
        if (this.experimentList.size() <= 0) {
            return null;
        }
        ExperimentMeta experimentMeta = null;
        for (ExperimentMeta meta : this.experimentList) {
            if (meta.f() == notificationId) {
                experimentMeta = meta;
            }
        }
        return experimentMeta;
    }

    public String toString() {
        return Utils.a((Object) this, (Type) ExperimentPayload.class);
    }

    public final void b(int totalWakeAttempts) {
        this.totalWakeAttempts = totalWakeAttempts;
    }

    public final int g() {
        return this.totalWakeAttempts;
    }

    public final ExperimentPayload h() {
        ExperimentPayload summary = new ExperimentPayload();
        summary.experimentList.add(f());
        summary.wakeUpList.addAll(this.wakeUpList);
        summary.totalWakeAttempts = this.totalWakeAttempts;
        summary.totalRetargetAttempts = b();
        return summary;
    }

    public final boolean a(OEMEcsConfig ecsConfig) {
        Date retargetingNotAllowedAfter = null;
        ExperimentMeta latestExperimentMeta = f();
        if (latestExperimentMeta == null || ecsConfig == null) {
            return false;
        }
        a retargetInfo;
        UpgradeNotification a = ecsConfig.a();
        if (a == null) {
            retargetInfo = null;
        } else {
            retargetInfo = a.e();
        }
        if (retargetInfo == null || ((long) this.experimentList.size()) > retargetInfo.maxRetargetAllowed) {
            return false;
        }
        if (retargetInfo.lastExperimentDisplayDate != null) {
            retargetingNotAllowedAfter = Utils.a(retargetInfo.lastExperimentDisplayDate, "dd-MM-yyyy");
        }
        if (retargetingNotAllowedAfter == null) {
            return false;
        }
        long minDurationRequiredForRetargeting = System.currentTimeMillis() - retargetingNotAllowedAfter.getTime();
        if (minDurationRequiredForRetargeting >= 0) {
            return latestExperimentMeta.a(minDurationRequiredForRetargeting);
        }
        return false;
    }
}
