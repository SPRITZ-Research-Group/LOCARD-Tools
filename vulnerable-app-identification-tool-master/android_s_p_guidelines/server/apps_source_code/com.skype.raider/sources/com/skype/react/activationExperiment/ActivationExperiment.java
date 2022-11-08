package com.skype.react.activationExperiment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.react.activationExperiment.ImageFetcher.ImageFetcherCallback;
import com.skype.react.activationExperiment.models.ActivationOptions;
import com.skype.react.activationExperiment.models.AppLaunchState;
import com.skype.react.activationExperiment.models.DeviceExperimentConfig;
import com.skype.react.activationExperiment.models.ExperimentEligibilityStatus;
import com.skype.react.activationExperiment.models.ExperimentMeta;
import com.skype.react.activationExperiment.models.ExperimentPayload;
import com.skype.react.activationExperiment.models.ExperimentState;
import com.skype.react.activationExperiment.models.NotificationError;
import com.skype.react.activationExperiment.models.NotificationOptions;
import com.skype.react.activationExperiment.models.NotificationState;
import com.skype.react.activationExperiment.models.OEMEcsConfig;
import com.skype.react.activationExperiment.models.OEMEcsConfig.AppParameters;
import com.skype.react.activationExperiment.models.OEMEcsConfig.UpgradeNotification;
import com.skype.react.activationExperiment.models.WakeResultStatus;
import com.skype.react.activationExperiment.models.WakeUpMeta;
import com.skype.react.activationExperiment.models.WakeUpReason;
import com.skype.react.common.NotificationStatusCallback;
import com.skype.react.common.OnWakeCompleteCallback;
import com.skype.react.upgrade.WakeEventReceiver;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ActivationExperiment {
    private static ActivationExperiment a = new ActivationExperiment();
    private ExperimentPayload b;

    public static void a(Context context, am data, OnWakeCompleteCallback callback) {
        try {
            a(context, WakeUpReason.Upgrade, data, callback);
        } catch (Throwable ex) {
            FLog.e("ActivationExperiment", "onUpgradeWakeUp", ex);
            callback.a(b(WakeResultStatus.WakeUpError, ExperimentEligibilityStatus.Unknown));
        }
    }

    public static void b(Context context, am data, OnWakeCompleteCallback callback) {
        try {
            a(context, WakeUpReason.RtcWakeUp, data, callback);
        } catch (Throwable ex) {
            FLog.e("ActivationExperiment", "onRtcWakeUp", ex);
            callback.a(b(WakeResultStatus.WakeUpError, ExperimentEligibilityStatus.Unknown));
        }
    }

    public static void c(Context context, am data, OnWakeCompleteCallback callback) {
        NotificationOptions options = new NotificationOptions(data);
        long notificationId = options.notificationId;
        AppLaunchState launchState = options.launchState;
        FLog.i("ActivationExperiment", "onNotificationClicked id(%d)", Long.valueOf(notificationId));
        if (notificationId == -1) {
            callback.a(b(WakeResultStatus.DidNothing, ExperimentEligibilityStatus.Unknown));
            return;
        }
        ExperimentMeta experimentMeta;
        a.c(context);
        ActivationExperiment activationExperiment = a;
        if (activationExperiment.b != null) {
            experimentMeta = activationExperiment.b.b(notificationId);
        } else {
            experimentMeta = null;
        }
        if (experimentMeta == null || !experimentMeta.a()) {
            e(context);
            callback.a(b(WakeResultStatus.NotificationClickExperimentMissing, ExperimentEligibilityStatus.Unknown));
        } else if (AppLaunchState.SignedIn == launchState) {
            e(context);
            callback.a(b(WakeResultStatus.NotificationClickUserAlreadySignedIn, ExperimentEligibilityStatus.Unknown));
        } else if (experimentMeta.c() || experimentMeta.d()) {
            e(context);
            callback.a(b(WakeResultStatus.NotificationClickUserAlreadySignedIn, ExperimentEligibilityStatus.Unknown));
        } else if (experimentMeta.a()) {
            final long wakeUpTime = System.currentTimeMillis();
            experimentMeta.a(ExperimentState.NotificationClicked);
            experimentMeta.a(true);
            a.d(context);
            final Context context2 = context;
            final OnWakeCompleteCallback onWakeCompleteCallback = callback;
            a.a(context, new ImageFetcherCallback() {
                public final void a(Bitmap bitmap) {
                    experimentMeta.a(false);
                    ActivationExperiment.a.a(wakeUpTime, WakeUpReason.NotificationClick, WakeResultStatus.NotificationClickedNetworkSuccess);
                    ActivationExperiment.e(context2);
                    ActivationExperiment.a.d(context2);
                    onWakeCompleteCallback.a(ActivationExperiment.b(WakeResultStatus.NotificationClickedNetworkSuccess, ExperimentEligibilityStatus.Eligible));
                }

                public final void a(a fetchError) {
                    ActivationExperiment.a.a(wakeUpTime, WakeUpReason.NotificationClick, WakeResultStatus.NotificationClickedNetworkFailure);
                    ActivationExperiment.a.d(context2);
                    onWakeCompleteCallback.a(ActivationExperiment.b(WakeResultStatus.NotificationClickedNetworkFailure, ExperimentEligibilityStatus.Eligible));
                }
            });
        } else {
            callback.a(b(WakeResultStatus.DidNothing, ExperimentEligibilityStatus.Unknown));
        }
    }

    public static void a(Context context, OnWakeCompleteCallback callback) {
        a.c(context);
        ExperimentMeta experimentMeta = a.e();
        if (experimentMeta == null) {
            callback.a(b(WakeResultStatus.DidNothing, ExperimentEligibilityStatus.Unknown));
        } else if (experimentMeta.c() || experimentMeta.d()) {
            callback.a(b(WakeResultStatus.ExperimentAlreadyFinished, ExperimentEligibilityStatus.Unknown));
        } else if (!experimentMeta.a()) {
            experimentMeta.a(ExperimentState.CancelledUserSignedIn);
            a.a(System.currentTimeMillis(), WakeUpReason.UserSignIn, WakeResultStatus.ExperimentCancelledUserSignedIn);
            e(context);
            a.d(context);
            callback.a(b(WakeResultStatus.ExperimentCancelledUserSignedIn, ExperimentEligibilityStatus.CancelledUserSignedIn));
        } else if (!experimentMeta.a() || experimentMeta.d()) {
            callback.a(b(WakeResultStatus.DidNothing, ExperimentEligibilityStatus.Unknown));
        } else {
            experimentMeta.a(ExperimentState.UserSignedIn);
            a.a(System.currentTimeMillis(), WakeUpReason.UserSignIn, WakeResultStatus.UserSignedInSuccess);
            e(context);
            a.d(context);
            callback.a(b(WakeResultStatus.UserSignedInSuccess, ExperimentEligibilityStatus.Eligible));
        }
    }

    public static void a(Context context) {
        FLog.d("ActivationExperiment", "rescheduleRtcWake:received");
        a.c(context);
        WakeUpMeta pendingWakeUp = a.c();
        if (pendingWakeUp == null) {
            FLog.d("ActivationExperiment", "rescheduleRtcWake:skipped, no pending wake");
            return;
        }
        long currTimeInMillis = Calendar.getInstance().getTimeInMillis();
        if (pendingWakeUp.time - currTimeInMillis > 0) {
            FLog.d("ActivationExperiment", "rescheduleRtcWake: reschedule experiment, wait hasn't expired during app shutdown");
            a(context, pendingWakeUp.time);
            return;
        }
        long scheduledWakeUpTime = currTimeInMillis + 600000;
        FLog.d("ActivationExperiment", "rescheduleRtcWake: reschedule experiment, wait has expired during app shutdown, wake after %d seconds", Long.valueOf(600));
        a.b(pendingWakeUp.time, WakeUpReason.DeviceBoot, WakeResultStatus.RescheduledOnBoot);
        a.a(scheduledWakeUpTime, WakeUpReason.RtcWakeUp, WakeResultStatus.WakeUpPending);
        a.d(context);
        a(context, scheduledWakeUpTime);
    }

    private static void a(Context context, WakeUpReason wakeUpReason, am optionsMap, OnWakeCompleteCallback callback) {
        final ActivationOptions activationOptions = new ActivationOptions(optionsMap);
        FLog.i("ActivationExperiment", "ActivationOptions%s", activationOptions.toString());
        FLog.i("ActivationExperiment", "onAppWakeUp, woke up at: %s with AppLaunchState %s, wakeUpReason(%s)", Utils.a(), activationOptions.launchState.toString(), wakeUpReason.toString());
        if (activationOptions.notificationState == NotificationState.Displayed) {
            FLog.i("ActivationExperiment", "onAppWakeUp: OlderNotification(%s) already displayed");
            callback.a(b(WakeResultStatus.ExperimentAlreadyFinishedBefore817, ExperimentEligibilityStatus.Eligible));
            return;
        }
        ExperimentEligibilityStatus eligibleToRunStatus;
        if (activationOptions.ecsConfig == null) {
            FLog.i("ActivationExperiment", "isEligibleToStartExperiment: ecs not found");
            eligibleToRunStatus = ExperimentEligibilityStatus.EcsNotLoaded;
        } else if (a(activationOptions.partnerPreInstallId)) {
            FLog.i("ActivationExperiment", "isEligibleToStartExperiment, SAMSUNG_PREINSTALL");
            eligibleToRunStatus = ExperimentEligibilityStatus.Eligible;
        } else if (activationOptions.isInsidersBuild) {
            FLog.i("ActivationExperiment", "isEligibleToStartExperiment, INSIDERS_BUILD");
            eligibleToRunStatus = ExperimentEligibilityStatus.Eligible;
        } else if (activationOptions.ecsConfig.d()) {
            FLog.i("ActivationExperiment", "isEligibleToStartExperiment, upgradeNotification.enabled");
            eligibleToRunStatus = ExperimentEligibilityStatus.Eligible;
        } else {
            DeviceExperimentConfig e = activationOptions.ecsConfig.e();
            if (e != null) {
                FLog.i("ActivationExperiment", "Found valid experiment config in ECS %s", e.experimentId);
                eligibleToRunStatus = ExperimentEligibilityStatus.Eligible;
            } else {
                eligibleToRunStatus = ExperimentEligibilityStatus.ActivationNotEnabled;
            }
        }
        if (eligibleToRunStatus != ExperimentEligibilityStatus.Eligible) {
            FLog.i("ActivationExperiment", "onAppWakeUp: isEligibleToStartExperiment(%s)", String.valueOf(eligibleToRunStatus));
            callback.a(b(WakeResultStatus.NotEligibleToRun, eligibleToRunStatus));
            return;
        }
        boolean a;
        ExperimentMeta experimentMeta;
        Object obj;
        a.c(context);
        ActivationExperiment activationExperiment = a;
        OEMEcsConfig oEMEcsConfig = activationOptions.ecsConfig;
        if (activationExperiment.b != null) {
            a = activationExperiment.b.a(oEMEcsConfig);
        } else {
            a = false;
        }
        if (a) {
            FLog.i("ActivationExperiment", "onAppWakeUp:Retargeting");
            experimentMeta = a.e();
            experimentMeta.a(ExperimentState.SkippedForRetargeting);
            experimentMeta.b(ExperimentState.SkippedForRetargeting);
            a.b();
            activationExperiment = a;
            if (activationExperiment.b != null) {
                activationExperiment.b.a(activationExperiment.b.b() + 1);
            }
        }
        ExperimentMeta e2 = a.e();
        if (e2 == null || !e2.c()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            experimentMeta = a.e();
            if (experimentMeta != null) {
                FLog.i("ActivationExperiment", "onAppWakeUp:ExperimentAlreadyFinished %s", experimentMeta.toString());
            }
            callback.a(b(WakeResultStatus.ExperimentAlreadyFinished, ExperimentEligibilityStatus.Unknown));
            return;
        }
        ExperimentMeta latestExperimentMeta = a.e();
        if (latestExperimentMeta == null || !latestExperimentMeta.a() || latestExperimentMeta.b()) {
            long upgradeWakeUpTime = 0;
            if (wakeUpReason == WakeUpReason.Upgrade) {
                upgradeWakeUpTime = System.currentTimeMillis();
                a.a(upgradeWakeUpTime, WakeUpReason.Upgrade, WakeResultStatus.NextWakeUpScheduled);
            }
            final long wakeUpScheduleTime = wakeUpReason == WakeUpReason.Upgrade ? upgradeWakeUpTime : activationOptions.scheduleWakeUpTime;
            final Context context2;
            if (latestExperimentMeta == null || !latestExperimentMeta.b()) {
                e2 = a.e();
                if (e2 == null || e2.a() || !e2.c() || activationOptions.launchState != AppLaunchState.SignedIn) {
                    obj = null;
                } else {
                    e2.b(ExperimentState.CancelledUserSignedIn);
                    obj = 1;
                }
                if (obj != null) {
                    a.b(wakeUpScheduleTime, null, WakeResultStatus.ExperimentCancelledUserSignedIn);
                    a.d(context);
                    callback.a(b(WakeResultStatus.ExperimentCancelledUserSignedIn, ExperimentEligibilityStatus.CancelledUserSignedIn));
                    return;
                }
                ExperimentEligibilityStatus eligibilityStatus;
                if (activationOptions.ecsConfig == null) {
                    FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: ecs not found");
                    eligibilityStatus = ExperimentEligibilityStatus.EcsNotLoaded;
                } else if (activationOptions.launchState == AppLaunchState.SignedIn) {
                    FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: not supported Launch State %s", activationOptions.launchState);
                    eligibilityStatus = ExperimentEligibilityStatus.CancelledUserSignedIn;
                } else {
                    OEMEcsConfig oEMEcsConfig2 = activationOptions.ecsConfig;
                    obj = (oEMEcsConfig2.a() == null || !oEMEcsConfig2.a().a()) ? null : 1;
                    DeviceExperimentConfig e3 = a(activationOptions.partnerPreInstallId) ? oEMEcsConfig2.e() : null;
                    if (obj == null && e3 == null) {
                        FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: upgradeNotification not enabled");
                        eligibilityStatus = ExperimentEligibilityStatus.EcsNotificationNotEnabled;
                    } else {
                        UpgradeNotification a2 = oEMEcsConfig2.a();
                        if (a2.c() == null) {
                            FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: appParameters not found");
                            eligibilityStatus = ExperimentEligibilityStatus.EcsAppParametersNotSet;
                        } else {
                            AppParameters c = a2.c();
                            if (c.a() == null || c.a().indexOf(activationOptions.launchState.toString()) == -1) {
                                FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: app state doesn't match, required one of [%s], found(%s)", c.a() != null ? TextUtils.join(",", c.a()) : "", String.valueOf(activationOptions.launchState));
                                eligibilityStatus = ExperimentEligibilityStatus.EcsAppLaunchStateNoMatch;
                            } else {
                                long currentTimeMillis = (System.currentTimeMillis() - activationOptions.lastLaunchTime) / 1000;
                                long b = (long) c.b();
                                b = b < 2400 ? b * 3600 : b / 1000;
                                if (currentTimeMillis < b) {
                                    FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: time diff seconds(%d) is below min threshold(%d) seconds", Long.valueOf(currentTimeMillis), Long.valueOf(b));
                                    eligibilityStatus = ExperimentEligibilityStatus.EcsTimeDiffBelowThreshold;
                                } else {
                                    if (AppLaunchState.NeverLaunched.equals(activationOptions.launchState)) {
                                        activationExperiment = a;
                                        if (activationExperiment.b == null || activationExperiment.b.e()) {
                                            obj = 1;
                                        } else {
                                            obj = null;
                                        }
                                        if (obj != null) {
                                            FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: isBarredTimeWindowForNotification, %s", a.b());
                                            eligibilityStatus = ExperimentEligibilityStatus.FirstTimeNeverLaunched;
                                        }
                                    }
                                    if (a.a(oEMEcsConfig2.a().b().b())) {
                                        FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: isBeforeDate");
                                        eligibilityStatus = ExperimentEligibilityStatus.NotificationTimeWindowNotStarted;
                                    } else if (a.b(oEMEcsConfig2.a().b().c())) {
                                        FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: isAfterDate");
                                        eligibilityStatus = ExperimentEligibilityStatus.NotificationTimeWindowExpired;
                                    } else if (a.a()) {
                                        FLog.i("ActivationExperiment", "isEligibleToExecuteExperiment: isBarredTimeWindowForNotification, %s", a.b());
                                        eligibilityStatus = ExperimentEligibilityStatus.NotificationBarredTimeWindow;
                                    } else {
                                        eligibilityStatus = ExperimentEligibilityStatus.Eligible;
                                    }
                                }
                            }
                        }
                    }
                }
                if (eligibilityStatus != ExperimentEligibilityStatus.Eligible) {
                    AppLaunchState appLaunchState = activationOptions.launchState;
                    if (appLaunchState == AppLaunchState.Launched || appLaunchState == AppLaunchState.PossiblyLaunched || appLaunchState == AppLaunchState.NeverLaunched || appLaunchState == AppLaunchState.SignedOut) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        if (wakeUpReason == WakeUpReason.RtcWakeUp) {
                            if (a.d() < activationOptions.a()) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (obj == null) {
                                a.b(wakeUpScheduleTime, null, WakeResultStatus.WakeUpRetriesExhausted);
                                if (latestExperimentMeta != null) {
                                    latestExperimentMeta.b(ExperimentState.RetriesExhausted);
                                }
                                a.d(context);
                                FLog.i("ActivationExperiment", "scheduleWakeUpForActivationExperiment: RetriesExhausted, canScheduleWake(false), WakeUpCount: %d", Integer.valueOf(a.d()));
                                callback.a(b(WakeResultStatus.WakeUpRetriesExhausted, eligibilityStatus));
                                return;
                            }
                        }
                        a.b(wakeUpScheduleTime, null, WakeResultStatus.NextWakeUpScheduled);
                        b(context, wakeUpReason, eligibilityStatus, activationOptions, callback);
                        return;
                    }
                }
                if (eligibilityStatus != ExperimentEligibilityStatus.Eligible || activationOptions.launchState == AppLaunchState.SignedIn) {
                    FLog.i("ActivationExperiment", "onAppWakeUp:Not eligible %s", String.valueOf(eligibilityStatus));
                    callback.a(b(WakeResultStatus.DidNothing, eligibilityStatus));
                    return;
                }
                ExperimentMeta experimentMeta2;
                FLog.i("ActivationExperiment", "onAppWakeUp:executeExperiment");
                ActivationExperiment activationExperiment2 = a;
                context2 = context;
                final OnWakeCompleteCallback onWakeCompleteCallback = callback;
                AnonymousClass2 anonymousClass2 = new OnWakeCompleteCallback() {
                    public final void a(ar wakeUpData) {
                        WakeResultStatus wakeResultStatus = WakeResultStatus.a(wakeUpData.getString("wakeResultStatus"));
                        ActivationExperiment.a.b(wakeUpScheduleTime, null, wakeResultStatus);
                        ActivationExperiment.a.d(context2);
                        onWakeCompleteCallback.a(ActivationExperiment.b(wakeResultStatus, eligibilityStatus));
                    }
                };
                if (activationExperiment2.b == null) {
                    experimentMeta2 = null;
                } else {
                    experimentMeta2 = activationExperiment2.b.f();
                    if (experimentMeta2 == null) {
                        experimentMeta2 = activationExperiment2.b();
                    }
                }
                final AnonymousClass2 anonymousClass22 = anonymousClass2;
                final Context context3 = context;
                final WakeUpReason wakeUpReason2 = wakeUpReason;
                a.a(context, activationOptions.notificationMeta, new NotificationStatusCallback(activationExperiment2) {
                    final /* synthetic */ ActivationExperiment f;

                    public final void a(int notificationId) {
                        String notificationStyle = activationOptions.notificationStyle;
                        experimentMeta2.a(ExperimentState.DisplayedToUser);
                        experimentMeta2.b((long) notificationId);
                        experimentMeta2.c(activationOptions.lastLaunchTime > 0 ? System.currentTimeMillis() - activationOptions.lastLaunchTime : -1);
                        experimentMeta2.a(notificationStyle);
                        anonymousClass22.a(ActivationExperiment.b(WakeResultStatus.DisplaySuccess, ExperimentEligibilityStatus.Eligible));
                        FLog.i("ActivationExperiment", "executeExperiment: %s", ExperimentState.DisplayedToUser);
                    }

                    public final void a(NotificationError error) {
                        experimentMeta2.a(ExperimentState.a(error));
                        ActivationExperiment.b(context3, wakeUpReason2, ExperimentEligibilityStatus.Eligible, activationOptions, new OnWakeCompleteCallback(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = this$1;
                            }

                            public final void a(ar wakeUpResult) {
                                experimentMeta2.a(activationOptions.notificationStyle);
                                experimentMeta2.c(activationOptions.lastLaunchTime > 0 ? System.currentTimeMillis() - activationOptions.lastLaunchTime : -1);
                                anonymousClass22.a(ActivationExperiment.b(WakeResultStatus.DisplayFailure, ExperimentEligibilityStatus.Eligible));
                            }
                        });
                        FLog.i("ActivationExperiment", "executeExperiment: %s", ExperimentState.DisplaySkippedNetworkError);
                    }
                });
                return;
            }
            FLog.i("ActivationExperiment", "onAppWakeUp:ExperimentHasPendingSend %s", latestExperimentMeta.toString());
            final ActivationExperiment activationExperiment3 = a;
            final ExperimentMeta e4 = activationExperiment3.e();
            if (e4 == null) {
                return;
            }
            if (wakeUpReason == WakeUpReason.Upgrade) {
                a.b(wakeUpScheduleTime, null, WakeResultStatus.PendingSendTrySuccess);
                activationExperiment3.d(context);
                e4.a(false);
                callback.a(b(WakeResultStatus.PendingSendTrySuccess, ExperimentEligibilityStatus.Eligible));
                return;
            }
            e4.a(true);
            activationExperiment3.d(context);
            context2 = context;
            final OnWakeCompleteCallback onWakeCompleteCallback2 = callback;
            a.a(context, new ImageFetcherCallback() {
                public final void a(Bitmap bitmap) {
                    e4.a(false);
                    ActivationExperiment.a.b(wakeUpScheduleTime, null, WakeResultStatus.PendingSendTrySuccess);
                    ActivationExperiment.e(context2);
                    activationExperiment3.d(context2);
                    onWakeCompleteCallback2.a(ActivationExperiment.b(WakeResultStatus.PendingSendTrySuccess, ExperimentEligibilityStatus.Eligible));
                }

                public final void a(a fetchError) {
                    ActivationExperiment.a.b(wakeUpScheduleTime, null, WakeResultStatus.PendingSendTryFailed);
                    activationExperiment3.d(context2);
                    ActivationExperiment.e(context2);
                    onWakeCompleteCallback2.a(ActivationExperiment.b(WakeResultStatus.PendingSendTryFailed, ExperimentEligibilityStatus.Eligible));
                }
            });
            return;
        }
        FLog.i("ActivationExperiment", "onAppWakeUp:ExperimentAlreadyDisplayed %s", latestExperimentMeta.toString());
        callback.a(b(WakeResultStatus.DidNothing, ExperimentEligibilityStatus.Unknown));
    }

    private static void b(@Nonnull Context context, @Nonnull WakeUpReason wakeUpReason, @Nonnull ExperimentEligibilityStatus experimentEligibilityStatus, @Nonnull ActivationOptions options, @Nonnull OnWakeCompleteCallback callback) {
        long scheduledWakeUpTime;
        long afterSeconds;
        WakeUpMeta pendingWakeUp;
        ActivationExperiment activationExperiment;
        long startTime = System.currentTimeMillis();
        a.c(context);
        OEMEcsConfig oEMEcsConfig = options.ecsConfig;
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int a = oEMEcsConfig.a().b().a();
        Date b = oEMEcsConfig.a().b().b();
        if (b != null && experimentEligibilityStatus == ExperimentEligibilityStatus.NotificationTimeWindowNotStarted) {
            instance.setTime(b);
            instance.set(11, a);
        } else if (wakeUpReason == WakeUpReason.Upgrade && experimentEligibilityStatus == ExperimentEligibilityStatus.NotificationBarredTimeWindow) {
            if (i < a) {
                instance.set(11, a);
            } else {
                instance.add(5, 1);
                instance.set(11, a);
            }
            scheduledWakeUpTime = instance.getTimeInMillis();
            afterSeconds = (scheduledWakeUpTime - startTime) / 1000;
            pendingWakeUp = a.c();
            if (!(pendingWakeUp == null || wakeUpReason == WakeUpReason.Upgrade)) {
                FLog.i("ActivationExperiment", "Overriding previous wake up that was schedule for %s", Utils.a(pendingWakeUp.time));
            }
            FLog.i("ActivationExperiment", "scheduleWakeUpForActivationExperiment: schedule next wake up after: %d secs, app will wake up on %s", Long.valueOf(afterSeconds), Utils.a(scheduledWakeUpTime));
            a(context, scheduledWakeUpTime);
            if (wakeUpReason == WakeUpReason.Upgrade) {
                activationExperiment = a;
                if (activationExperiment.b != null) {
                    activationExperiment.b.c();
                }
            }
            a.a(scheduledWakeUpTime, WakeUpReason.RtcWakeUp, WakeResultStatus.WakeUpPending);
            a.d(context);
            FLog.i("ActivationExperiment", "scheduleWakeUpForActivationExperiment: scheduled attempt(%d), TimeTaken(%d ms)", Integer.valueOf(a.d()), Long.valueOf(System.currentTimeMillis() - startTime));
            callback.a(b(WakeResultStatus.NextWakeUpScheduled, experimentEligibilityStatus));
        } else {
            int b2 = oEMEcsConfig.b();
            if (b2 < 86400) {
                instance.add(13, b2);
            } else {
                instance.add(5, b2 / 86400);
                instance.set(11, a);
            }
        }
        scheduledWakeUpTime = instance.getTimeInMillis();
        afterSeconds = (scheduledWakeUpTime - startTime) / 1000;
        pendingWakeUp = a.c();
        FLog.i("ActivationExperiment", "Overriding previous wake up that was schedule for %s", Utils.a(pendingWakeUp.time));
        FLog.i("ActivationExperiment", "scheduleWakeUpForActivationExperiment: schedule next wake up after: %d secs, app will wake up on %s", Long.valueOf(afterSeconds), Utils.a(scheduledWakeUpTime));
        a(context, scheduledWakeUpTime);
        if (wakeUpReason == WakeUpReason.Upgrade) {
            activationExperiment = a;
            if (activationExperiment.b != null) {
                activationExperiment.b.c();
            }
        }
        a.a(scheduledWakeUpTime, WakeUpReason.RtcWakeUp, WakeResultStatus.WakeUpPending);
        a.d(context);
        FLog.i("ActivationExperiment", "scheduleWakeUpForActivationExperiment: scheduled attempt(%d), TimeTaken(%d ms)", Integer.valueOf(a.d()), Long.valueOf(System.currentTimeMillis() - startTime));
        callback.a(b(WakeResultStatus.NextWakeUpScheduled, experimentEligibilityStatus));
    }

    private void c(Context context) {
        if (this.b == null) {
            SharedPreferences sharedPref = context.getSharedPreferences("ExperimentPayload.File", 0);
            long startTime = System.currentTimeMillis();
            String payloadStr = sharedPref.getString("ExperimentPayload.PrefKey.v1", null);
            FLog.i("ActivationExperiment", "loadFromDisk:%s, timeTaken(%d ms)", (Object) payloadStr, Long.valueOf(System.currentTimeMillis() - startTime));
            this.b = new ExperimentPayload();
            this.b = (ExperimentPayload) Utils.a(payloadStr, ExperimentPayload.class);
            if (this.b == null) {
                this.b = new ExperimentPayload();
            }
        }
    }

    private void d(Context context) {
        if (this.b == null) {
            FLog.i("ActivationExperiment", "persistToDisk: NOT_SAVING, payload(null)");
            return;
        }
        Object payloadStr = Utils.a(this.b, (Type) ExperimentPayload.class);
        Editor editor = context.getSharedPreferences("ExperimentPayload.File", 0).edit();
        editor.putString("ExperimentPayload.PrefKey.v1", payloadStr);
        editor.apply();
        FLog.i("ActivationExperiment", "persistToDisk: SAVING, payload(%s)", payloadStr);
    }

    private ExperimentMeta b() {
        if (this.b == null) {
            return null;
        }
        ExperimentMeta meta = new ExperimentMeta("activation_experiment");
        meta.a(ExperimentState.Started);
        this.b.a(meta);
        return meta;
    }

    private static boolean a(String partnerId) {
        return partnerId != null && "534".equals(partnerId);
    }

    private WakeUpMeta c() {
        return this.b != null ? this.b.a() : null;
    }

    private static void a(Context context, long scheduledWakeUpTime) {
        e(context);
        Intent intent = new Intent(context, WakeEventReceiver.class);
        intent.setAction("WakeEventReceiver.ACTION_OEM_RTC_WAKE");
        intent.putExtra("scheduledWakeUpTime", scheduledWakeUpTime);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 9978234, intent, ErrorDialogData.BINDER_CRASH);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.set(0, scheduledWakeUpTime, pendingIntent);
        }
    }

    private static void e(Context context) {
        FLog.i("ActivationExperiment", "cancelPendingRtcWake");
        Intent intent = new Intent(context, WakeEventReceiver.class);
        intent.setAction("WakeEventReceiver.ACTION_OEM_RTC_WAKE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 9978234, intent, ErrorDialogData.DYNAMITE_CRASH);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void a(long time, WakeUpReason reason, WakeResultStatus wakeUpState) {
        if (this.b != null) {
            this.b.a(new WakeUpMeta(wakeUpState, time, reason));
        }
    }

    private void b(long time, @Nullable WakeUpReason reason, @Nonnull WakeResultStatus status) {
        if (this.b != null) {
            WakeUpMeta meta = this.b.a(time);
            if (meta != null) {
                if (reason != null) {
                    meta.a(reason);
                }
                meta.a(status);
                if (meta.a() != WakeResultStatus.WakeUpPending) {
                    this.b.b(this.b.g() + 1);
                }
            }
        }
    }

    private int d() {
        return this.b != null ? this.b.d() : 0;
    }

    private ExperimentMeta e() {
        return this.b != null ? this.b.f() : null;
    }

    private static ar b(WakeResultStatus wakeResultStatus, ExperimentEligibilityStatus experimentEligibilityStatus) {
        ExperimentPayload h;
        ar writableMap = new WritableNativeMap();
        writableMap.putString("wakeResultStatus", wakeResultStatus.toString());
        writableMap.putString("experimentEligibilityStatus", experimentEligibilityStatus.toString());
        String str = "payloadSummary";
        ActivationExperiment activationExperiment = a;
        if (activationExperiment.b != null) {
            h = activationExperiment.b.h();
        } else {
            h = new ExperimentPayload();
        }
        writableMap.putString(str, h.toString());
        ExperimentMeta meta = a.e();
        if (meta != null) {
            writableMap.putString("notificationStyle", meta.g());
        }
        return writableMap;
    }
}
