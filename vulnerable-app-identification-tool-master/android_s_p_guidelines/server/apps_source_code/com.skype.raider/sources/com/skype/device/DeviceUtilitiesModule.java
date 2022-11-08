package com.skype.device;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.provider.Telephony.Sms;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.f;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import com.skype4life.c.a.a.c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceUtilitiesModule extends ReactContextBaseJavaModule {
    private static final int DEFAULT_MAX_TEXTURE_SIZE = 2048;
    private static final String DEVICE_ORIENTATION_EVENT = "DeviceOrientationChange";
    private static final String KEY_LOW_POWER_MODE = "lowPowerMode";
    private static final String KEY_SMS_MESSAGE_RECEIVED = "messages";
    private static final String LOW_POWER_MODE_CHANGED_EVENT = "LowPowerModeChangeEvent";
    private static final int MAX_WAKELOCK_TIMEOUT = 10000;
    private static final String RN_CLASS = "DeviceUtilities";
    private static final String SHAKE_GESTURE_EVENT = "ShakeGesture";
    private static final String SMS_MESSAGE_RECEIVED_EVENT = "SMSMessageReceivedEvent";
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static final int SMS_RECEIVER_PRIORITY = 999;
    private static final String T1_LIGHT_NOTIFICATION_SETTING_KEY = "notificationLight";
    private static final String T1_NEW_MESSAGE_NOTIFICATION_SETTING_KEY = "newMessageNotification";
    private static final String T1_NOTIFICATIONS_ENABLED_SETTING_KEY = "notificationsEnabled";
    private static final String T1_NOTIFICATIONS_MIGRATED_KEY = "notification_settings_migrated";
    private static final String T1_SOUND_NOTIFICATION_SETTING_KEY = "notificationSound";
    private static final String T1_SYNC_ADDRESS_BOOK_SETTING_ENABLED = "0";
    private static final String T1_SYNC_ADDRESS_BOOK_SETTING_KEY = "enableAutoBuddy";
    private static final String T1_VIBRATE_NOTIFICATION_SETTING_KEY = "notificationVibrate";
    private static final String T2_UPDATE_SHARED_PREFERENCES = "t2_update";
    private static final long VIBRATION_DELAY_MS = 2000;
    private static final long VIBRATION_DURATION_MS = 750;
    private static final long VIBRATION_SHORT_MS = 10;
    private static final String VOLUME_KEY_PRESSED_EVENT = "VolumeKeyPressedEvent";
    private boolean isOrientationListenerEnabled;
    private AtomicInteger keyguardDisabledCount;
    private AtomicInteger keyguardDisabledCountNew;
    private a mDeviceUtilitiesQueue;
    private boolean mIsLowPowerModeListenerEnabled;
    private Boolean mIsNaturalOrientationLandscape;
    private boolean mIsShakeDetectorStarted;
    private boolean mIsVolumeKeyPressedEventListenerEnabled;
    private final f mShakeDetector;
    private com.skype4life.c.a.a orientationTracker;
    private long[] patternLong;
    private PowerManager powerManager;
    private BroadcastReceiver powerSaverChangeReceiver;
    private Method proximityReleaseMethod;
    private WakeLock proximitySensorLock;
    private WakeLock screenWakeLock;
    private BroadcastReceiver smsReceiver;
    private boolean telelephonySupported;
    private WifiLock wifiLock;

    public DeviceUtilitiesModule(ag reactContext) {
        super(reactContext);
        this.proximitySensorLock = null;
        this.proximityReleaseMethod = null;
        this.screenWakeLock = null;
        this.telelephonySupported = false;
        this.wifiLock = null;
        this.mDeviceUtilitiesQueue = a.a(RN_CLASS, b.DEFAULT);
        this.patternLong = new long[]{0, VIBRATION_DURATION_MS, VIBRATION_DELAY_MS};
        this.keyguardDisabledCount = null;
        this.keyguardDisabledCountNew = null;
        this.powerSaverChangeReceiver = new BroadcastReceiver(this) {
            final /* synthetic */ DeviceUtilitiesModule a;

            {
                this.a = this$0;
            }

            public final void onReceive(Context context, Intent intent) {
                if (VERSION.SDK_INT >= 21) {
                    ar data = new WritableNativeMap();
                    data.putBoolean(DeviceUtilitiesModule.KEY_LOW_POWER_MODE, this.a.powerManager.isPowerSaveMode());
                    ((RCTDeviceEventEmitter) this.a.getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(DeviceUtilitiesModule.LOW_POWER_MODE_CHANGED_EVENT, data);
                }
            }
        };
        this.keyguardDisabledCount = new AtomicInteger(0);
        this.keyguardDisabledCountNew = new AtomicInteger(0);
        this.telelephonySupported = reactContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
        try {
            boolean proximitySupported;
            boolean screenWakeSupported;
            this.powerManager = (PowerManager) reactContext.getSystemService("power");
            Class<?> cls = this.powerManager.getClass();
            int screenOffFieldValue = cls.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
            int screenWakeFieldValue = cls.getField("SCREEN_BRIGHT_WAKE_LOCK").getInt(null);
            if (VERSION.SDK_INT > 16) {
                Method levelSupported = cls.getDeclaredMethod("isWakeLockLevelSupported", new Class[]{Integer.TYPE});
                proximitySupported = ((Boolean) levelSupported.invoke(this.powerManager, new Object[]{Integer.valueOf(screenOffFieldValue)})).booleanValue();
                screenWakeSupported = ((Boolean) levelSupported.invoke(this.powerManager, new Object[]{Integer.valueOf(screenWakeFieldValue)})).booleanValue();
            } else {
                int flags = ((Integer) cls.getDeclaredMethod("getSupportedWakeLockFlags", null).invoke(this.powerManager, null)).intValue();
                proximitySupported = (flags & screenOffFieldValue) != 0;
                screenWakeSupported = (flags & screenWakeFieldValue) != 0;
            }
            if (proximitySupported) {
                this.proximitySensorLock = this.powerManager.newWakeLock(screenOffFieldValue, RN_CLASS);
                this.proximityReleaseMethod = this.proximitySensorLock.getClass().getDeclaredMethod("release", new Class[]{Integer.TYPE});
            }
            if (screenWakeSupported) {
                this.screenWakeLock = this.powerManager.newWakeLock(268435462, RN_CLASS);
            }
        } catch (NoSuchFieldException nsfe) {
            FLog.w(RN_CLASS, "Proximity wake lock is not available [%s API %d] - %s!", Build.MODEL, Integer.valueOf(VERSION.SDK_INT), nsfe);
        } catch (NoSuchMethodException nsme) {
            FLog.w(RN_CLASS, "Proximity wake lock is not available [%s API %d] - %s!", Build.MODEL, Integer.valueOf(VERSION.SDK_INT), nsme);
        } catch (IllegalAccessException iae) {
            FLog.w(RN_CLASS, "Proximity wake lock is not available [%s API %d] - %s!", Build.MODEL, Integer.valueOf(VERSION.SDK_INT), iae);
        } catch (InvocationTargetException ite) {
            FLog.w(RN_CLASS, "Proximity wake lock is not available [%s API %d] - %s!", Build.MODEL, Integer.valueOf(VERSION.SDK_INT), ite);
        }
        this.wifiLock = ((WifiManager) reactContext.getApplicationContext().getSystemService("wifi")).createWifiLock(3, "WifiLock");
        this.wifiLock.setReferenceCounted(true);
        final ag agVar = reactContext;
        this.mShakeDetector = new f(new f.a(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void a() {
                ((RCTDeviceEventEmitter) agVar.a(RCTDeviceEventEmitter.class)).emit(DeviceUtilitiesModule.SHAKE_GESTURE_EVENT, null);
            }
        });
        this.orientationTracker = new com.skype4life.c.a.a(reactContext);
        agVar = reactContext;
        this.orientationTracker.a(new com.skype4life.c.a.a.a(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void a(com.skype4life.c.a.a.b deviceOrientation, c deviceOrientationInSpace) {
                WritableNativeMap params = new WritableNativeMap();
                params.putInt("orientation", deviceOrientation.a());
                if (deviceOrientationInSpace != null) {
                    params.putInt("orientationInSpace", deviceOrientationInSpace.a());
                }
                ((RCTDeviceEventEmitter) agVar.a(RCTDeviceEventEmitter.class)).emit(DeviceUtilitiesModule.DEVICE_ORIENTATION_EVENT, params);
            }
        });
    }

    public String getName() {
        return RN_CLASS;
    }

    @ReactMethod
    public void enableProximitySensor(final ae promise) {
        this.mDeviceUtilitiesQueue.b(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: enableProximitySensor");
                if (this.b.proximitySensorLock == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No proximity lock available"));
                    return;
                }
                if (!this.b.proximitySensorLock.isHeld()) {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: Acquiring proximity lock");
                    this.b.proximitySensorLock.acquire();
                }
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void disableProximitySensor(final ae promise) {
        this.mDeviceUtilitiesQueue.b(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: disableProximitySensor");
                if (this.b.proximitySensorLock == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No proximity lock available"));
                    return;
                }
                if (this.b.proximitySensorLock.isHeld()) {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: Releasing proximity lock");
                    try {
                        this.b.proximityReleaseMethod.invoke(this.b.proximitySensorLock, new Object[]{Integer.valueOf(1)});
                    } catch (Throwable th) {
                        this.b.proximitySensorLock.release();
                    }
                }
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void blockScreenLock(final ae promise) {
        FLog.i(RN_CLASS, "blockScreenLock");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                Activity activity = this.b.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                activity.getWindow().addFlags(128);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void enableLowProfileMode(final ae promise) {
        FLog.i(RN_CLASS, "enableLowProfileMode");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                Activity activity = this.b.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(1);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void disableLowProfileMode(final ae promise) {
        FLog.i(RN_CLASS, "disableLowProfileMode");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                Activity activity = this.b.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                activity.getWindow().getDecorView().setSystemUiVisibility(0);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void allowScreenLock(final ae promise) {
        FLog.i(RN_CLASS, "allowScreenLock");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                Activity activity = this.b.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                activity.getWindow().clearFlags(128);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void reEnableKeyguardIfSet(boolean force, final ae promise) {
        FLog.i(RN_CLASS, "reEnableKeyguardIfSet");
        if (force || (this.keyguardDisabledCount.get() != 0 && this.keyguardDisabledCount.decrementAndGet() <= 0)) {
            final Activity activity = getCurrentActivity();
            if (activity == null) {
                promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
            } else {
                activity.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ DeviceUtilitiesModule c;

                    public final void run() {
                        FLog.i(DeviceUtilitiesModule.RN_CLASS, "Clearing system flags : result : called ended : re-enable keyguard and turn screen OFF");
                        this.c.keyguardDisabledCount.set(0);
                        activity.getWindow().clearFlags(6848640);
                        if (this.c.screenWakeLock != null && this.c.screenWakeLock.isHeld()) {
                            this.c.screenWakeLock.release();
                            FLog.i(DeviceUtilitiesModule.RN_CLASS, "Screen wake lock released");
                        }
                        promise.a(null);
                    }
                });
            }
        }
    }

    public void reEnableKeyguardIfSetNew() {
        FLog.i(RN_CLASS, "reEnableKeyguardIfSetNew");
        final Activity activity = getCurrentActivity();
        if (this.keyguardDisabledCountNew.get() == 0 || this.keyguardDisabledCountNew.decrementAndGet() > 0) {
            FLog.i(RN_CLASS, "Not disabling keyguard because keygaurddisabledcount: " + this.keyguardDisabledCountNew);
        }
        if (activity == null) {
            FLog.w(RN_CLASS, "Unable to re-enable keyguard new: no activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "reEnableKeyguardIfSetNew: clearing system flag disable keyguard.");
                    activity.getWindow().clearFlags(4194304);
                }
            });
        }
    }

    @ReactMethod
    public void disableKeyguard() {
        FLog.i(RN_CLASS, "disableKeyguard");
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "Adding system flags: result : Received incoming call message, dismissing keyguard and turning screen ON");
                    this.b.keyguardDisabledCount.incrementAndGet();
                    if (!(this.b.screenWakeLock == null || this.b.screenWakeLock.isHeld())) {
                        this.b.screenWakeLock.acquire(10000);
                        FLog.i(DeviceUtilitiesModule.RN_CLASS, "Screen wake lock acquired");
                    }
                    activity.getWindow().addFlags(6848640);
                }
            });
        }
    }

    public void disableKeyguardNew() {
        int i = 1;
        FLog.i(RN_CLASS, "disableKeyguardNew");
        KeyguardManager keyguardManager = (KeyguardManager) getReactApplicationContext().getSystemService("keyguard");
        if (VERSION.SDK_INT >= 22) {
            int i2 = keyguardManager != null ? 1 : 0;
            if (keyguardManager.isDeviceLocked()) {
                i = 0;
            }
            if ((i2 & i) != 0) {
                FLog.i(RN_CLASS, "disableKeyguardNew returning because device isn't locked");
                return;
            }
        }
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: disableKeyguardNew No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "disableKeyguardNew Dismissing keyguard");
                    this.b.keyguardDisabledCountNew.incrementAndGet();
                    activity.getWindow().addFlags(4194304);
                }
            });
        }
    }

    public void enableShowWhenLocked() {
        FLog.i(RN_CLASS, "enableShowWhenLocked");
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: enableShowWhenLocked No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "Enabling show when locked.");
                    activity.getWindow().addFlags(524288);
                }
            });
        }
    }

    public void disableShowWhenLocked() {
        FLog.i(RN_CLASS, "disableShowWhenLocked");
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: disableShowWhenLocked No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "Disabling show when locked.");
                    activity.getWindow().clearFlags(524288);
                }
            });
        }
    }

    public void enableTurnScreenOn() {
        FLog.i(RN_CLASS, "enableTurnScreenOn");
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: enableTurnScreenOn No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "Enabling turn screen on");
                    activity.getWindow().addFlags(2097152);
                }
            });
        }
    }

    public void disableTurnScreenOn() {
        FLog.i(RN_CLASS, "disableTurnScreenOn");
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.e(RN_CLASS, "DeviceUtilities: disableTurnScreenOn No activity found");
        } else {
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ DeviceUtilitiesModule b;

                public final void run() {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "Disabling turn screen on");
                    activity.getWindow().clearFlags(2097152);
                }
            });
        }
    }

    @ReactMethod
    public void enableWifiLock(final ae promise) {
        this.mDeviceUtilitiesQueue.b(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: enableWifiLock");
                if (this.b.wifiLock == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No WiFi lock available"));
                    return;
                }
                if (!this.b.wifiLock.isHeld()) {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: Enabling WiFi lock");
                    this.b.wifiLock.acquire();
                }
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void disableWifiLock(final ae promise) {
        this.mDeviceUtilitiesQueue.b(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: disableWifiLock");
                if (this.b.wifiLock == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No WiFi lock available"));
                    return;
                }
                if (this.b.wifiLock.isHeld()) {
                    FLog.i(DeviceUtilitiesModule.RN_CLASS, "DeviceUtilities: Disabling WiFi lock");
                    this.b.wifiLock.release();
                }
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void currentOrientation(final ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: currentOrientation");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                com.skype4life.c.a.a.b orientation = this.b.orientationTracker.a();
                if (orientation == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No current orientation"));
                } else {
                    promise.a(Integer.valueOf(orientation.a()));
                }
            }
        });
    }

    @ReactMethod
    public void currentOrientationInSpace(final ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: currentOrientationInSpace");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                c orientation = this.b.orientationTracker.b();
                if (orientation == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No current orientation in space"));
                } else {
                    promise.a(Integer.valueOf(orientation.a()));
                }
            }
        });
    }

    @ReactMethod
    public void unlockOrientation(final ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: unlockOrientation");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule b;

            public final void run() {
                Activity activity = this.b.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                activity.setRequestedOrientation(-1);
                promise.a(Boolean.valueOf(true));
            }
        });
    }

    @ReactMethod
    public void lockOrientation(final int orientation, final ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: lockOrientation");
        new Handler(getReactApplicationContext().getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ DeviceUtilitiesModule c;

            public final void run() {
                int deviceOrientation = 0;
                Activity activity = this.c.getCurrentActivity();
                if (activity == null) {
                    promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
                    return;
                }
                boolean z;
                if (orientation == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    deviceOrientation = 1;
                } else if (activity.getResources().getConfiguration().orientation != 1) {
                    deviceOrientation = this.c.currentLandscapeScreenOrientation(activity);
                }
                activity.setRequestedOrientation(deviceOrientation);
                promise.a(Boolean.valueOf(true));
            }
        });
    }

    @ReactMethod
    public void enableOrientationListener(ae promise) {
        if (!this.isOrientationListenerEnabled && this.orientationTracker.canDetectOrientation()) {
            this.orientationTracker.enable();
            this.isOrientationListenerEnabled = true;
        }
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void disableOrientationListener(ae promise) {
        if (this.isOrientationListenerEnabled) {
            this.orientationTracker.disable();
            this.isOrientationListenerEnabled = false;
        }
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void enableShakeGestureListener() {
        if (!this.mIsShakeDetectorStarted) {
            this.mShakeDetector.a((SensorManager) getReactApplicationContext().getSystemService("sensor"));
            this.mIsShakeDetectorStarted = true;
        }
    }

    @ReactMethod
    public void disableShakeGestureListener() {
        if (this.mIsShakeDetectorStarted) {
            this.mShakeDetector.a();
            this.mIsShakeDetectorStarted = false;
        }
    }

    @ReactMethod
    public void isInLowPowerMode(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: isInLowPowerMode");
        if (VERSION.SDK_INT < 21 || this.powerManager == null) {
            promise.a(Boolean.valueOf(false));
            return;
        }
        try {
            promise.a(Boolean.valueOf(this.powerManager.isPowerSaveMode()));
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    @ReactMethod
    public void enableLowPowerModeChangeListener(ae promise) {
        if (!this.mIsLowPowerModeListenerEnabled && VERSION.SDK_INT >= 21) {
            getReactApplicationContext().registerReceiver(this.powerSaverChangeReceiver, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"));
            this.mIsLowPowerModeListenerEnabled = true;
        }
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void disableLowPowerModeChangeListener(ae promise) {
        if (this.mIsLowPowerModeListenerEnabled && VERSION.SDK_INT >= 21) {
            getReactApplicationContext().unregisterReceiver(this.powerSaverChangeReceiver);
            this.mIsLowPowerModeListenerEnabled = false;
        }
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void enableVolumeKeyPressedEventListener(ae promise) {
        FLog.i(RN_CLASS, "enableVolumeKeyPressedEventListener");
        this.mIsVolumeKeyPressedEventListenerEnabled = true;
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void disableVolumeKeyPressedEventListener(ae promise) {
        FLog.i(RN_CLASS, "disableVolumeKeyPressedEventListener");
        this.mIsVolumeKeyPressedEventListenerEnabled = false;
        promise.a(Boolean.valueOf(true));
    }

    public boolean onVolumeKeyDown(int keyCode, KeyEvent event) {
        if (!this.mIsVolumeKeyPressedEventListenerEnabled || (keyCode != 25 && keyCode != 24)) {
            return false;
        }
        FLog.i(RN_CLASS, "sending volume key down event to JS");
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(VOLUME_KEY_PRESSED_EVENT, null);
        return true;
    }

    @ReactMethod
    public void uriForAsset(String asset, ae promise) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity found"));
            return;
        }
        String filePath = null;
        Uri uri = Uri.parse(asset);
        if (uri.getScheme().equals("file")) {
            promise.a((Object) asset);
            return;
        }
        if (uri.getScheme().equals("content")) {
            Cursor cursor = activity.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String path = cursor.getString(0);
                        if (!TextUtils.isEmpty(path)) {
                            filePath = path;
                        }
                    }
                    cursor.close();
                } catch (Throwable th) {
                    cursor.close();
                }
            }
        }
        if (filePath != null) {
            promise.a("file:" + filePath);
        } else {
            promise.a(new IllegalStateException("DeviceUtilities: file path not found"));
        }
    }

    @ReactMethod
    public void isAppInstalled(String appId, ae promise) {
        try {
            getReactApplicationContext().getPackageManager().getPackageInfo(appId, 0);
            promise.a(Boolean.valueOf(true));
        } catch (NameNotFoundException e) {
            promise.a(Boolean.valueOf(false));
        } catch (Throwable e2) {
            promise.a(e2);
        }
    }

    @ReactMethod
    public void getRingerMode(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: getRingerMode");
        try {
            int ringerMode = ((AudioManager) getReactApplicationContext().getSystemService("audio")).getRingerMode();
            if (!(ringerMode == 0 || VERSION.SDK_INT < 23 || ((NotificationManager) getReactApplicationContext().getSystemService("notification")).getCurrentInterruptionFilter() == 1)) {
                ringerMode = 0;
            }
            promise.a(Integer.valueOf(ringerMode));
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    @ReactMethod
    public void vibrate(boolean loop, ae promise) {
        int i = 0;
        FLog.i(RN_CLASS, "DeviceUtilities: vibrate");
        try {
            Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
            if (v == null || !v.hasVibrator()) {
                promise.a(Boolean.valueOf(false));
                return;
            }
            long[] jArr = this.patternLong;
            if (!loop) {
                i = -1;
            }
            v.vibrate(jArr, i);
            promise.a(Boolean.valueOf(true));
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    @ReactMethod
    public void cancelVibrate(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: cancelVibrate");
        try {
            Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
            if (v == null || !v.hasVibrator()) {
                promise.a(Boolean.valueOf(false));
                return;
            }
            v.cancel();
            promise.a(Boolean.valueOf(true));
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    @ReactMethod
    public void screenDpi(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: screenDpi");
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity"));
        } else {
            promise.a(Integer.valueOf((int) (activity.getResources().getDisplayMetrics().density * 160.0f)));
        }
    }

    @ReactMethod
    public void statusBarHeight(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: statusBarHeight");
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity"));
            return;
        }
        int heightResId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        promise.a(Float.valueOf((heightResId > 0 ? (float) activity.getResources().getDimensionPixelSize(heightResId) : 0.0f) / ((float) ((int) (activity.getResources().getDisplayMetrics().density * 160.0f)))));
    }

    @ReactMethod
    public void supportsEmergencyCalling(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: supportsEmergencyCalling");
        promise.a(Boolean.valueOf(getEmergencyDialerIntent() != null));
    }

    @ReactMethod
    public void emergencyCall(String number, ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: emergencyCall");
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity"));
            return;
        }
        Intent emergencyDialerIntent = getEmergencyDialerIntent();
        if (emergencyDialerIntent != null) {
            emergencyDialerIntent.setData(Uri.parse("tel:" + number));
            activity.startActivity(emergencyDialerIntent);
            promise.a(Boolean.valueOf(true));
            return;
        }
        promise.a(new IllegalArgumentException("Emergency calling is not supported on this device"));
    }

    private Intent getEmergencyDialerIntent() {
        if (!this.telelephonySupported) {
            return null;
        }
        Activity activity = getCurrentActivity();
        if (activity == null) {
            return null;
        }
        Intent emergencyDialerIntent = new Intent("com.android.phone.EmergencyDialer.DIAL");
        if (emergencyDialerIntent.resolveActivity(activity.getPackageManager()) == null) {
            return null;
        }
        return emergencyDialerIntent;
    }

    @ReactMethod
    public void emergencySMS(String number, ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: emergencySMS");
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity"));
            return;
        }
        try {
            Intent smsIntent = new Intent("android.intent.action.VIEW");
            if (VERSION.SDK_INT >= 19) {
                String defaultSmsPackageName = Sms.getDefaultSmsPackage(getReactApplicationContext());
                if (defaultSmsPackageName != null) {
                    smsIntent.setPackage(defaultSmsPackageName);
                }
            }
            smsIntent.setData(Uri.parse("smsto:" + number));
            smsIntent.addFlags(ErrorDialogData.BINDER_CRASH);
            activity.startActivity(smsIntent);
            promise.a(Boolean.valueOf(true));
        } catch (Throwable e) {
            FLog.e(RN_CLASS, "DeviceUtilities: emergencySMS exception: %s (number %s)", e.getLocalizedMessage(), number);
            promise.a(e);
        }
    }

    @ReactMethod
    public void openAppSettings() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", activity.getPackageName(), null));
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            activity.startActivity(intent);
        }
    }

    @ReactMethod
    public void openAppPermissionOptions(int permission) {
        openAppSettings();
    }

    @ReactMethod
    public void rateApp() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            String packageName = getReactApplicationContext().getPackageName();
            try {
                activity.startActivity(rateIntentForUrl(String.format("market://details?id=%s", new Object[]{packageName})));
            } catch (ActivityNotFoundException e) {
                activity.startActivity(rateIntentForUrl(String.format("https://play.google.com/store/apps/details?id=%s", new Object[]{packageName})));
            }
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        int i = VERSION.SDK_INT;
        intent.addFlags(1208483840);
        return intent;
    }

    @ReactMethod
    public void isSyncAddressBookEnabledInT1(String skypeId, ae promise) {
        promise.a(Boolean.valueOf(getReactApplicationContext().getSharedPreferences(skypeId, 0).getString(T1_SYNC_ADDRESS_BOOK_SETTING_KEY, "").equals(T1_SYNC_ADDRESS_BOOK_SETTING_ENABLED)));
    }

    @ReactMethod
    public void getT1UserSettings(String skypeId, ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: migrating notification settings");
        SharedPreferences sharedPrefs = getReactApplicationContext().getSharedPreferences(skypeId, 0);
        boolean isNotificationsEnabled = false;
        boolean isLightEnabled = false;
        boolean isVibrateEnabled = false;
        boolean isSoundEnabled = false;
        boolean isNewMessageNotificationEnabled = false;
        boolean containsNotificationSetting = sharedPrefs.contains(T1_NOTIFICATIONS_ENABLED_SETTING_KEY);
        boolean containsLightSetting = sharedPrefs.contains(T1_LIGHT_NOTIFICATION_SETTING_KEY);
        boolean containsVibrateSetting = sharedPrefs.contains(T1_VIBRATE_NOTIFICATION_SETTING_KEY);
        boolean containsSoundSetting = sharedPrefs.contains(T1_SOUND_NOTIFICATION_SETTING_KEY);
        boolean containsNewMessageNotificationSetting = sharedPrefs.contains(T1_NEW_MESSAGE_NOTIFICATION_SETTING_KEY);
        if (containsNotificationSetting) {
            isNotificationsEnabled = sharedPrefs.getBoolean(T1_NOTIFICATIONS_ENABLED_SETTING_KEY, true);
        }
        if (containsLightSetting) {
            isLightEnabled = sharedPrefs.getBoolean(T1_LIGHT_NOTIFICATION_SETTING_KEY, true);
        }
        if (containsVibrateSetting) {
            isVibrateEnabled = sharedPrefs.getBoolean(T1_VIBRATE_NOTIFICATION_SETTING_KEY, true);
        }
        if (containsSoundSetting) {
            isSoundEnabled = sharedPrefs.getBoolean(T1_SOUND_NOTIFICATION_SETTING_KEY, true);
        }
        if (containsNewMessageNotificationSetting) {
            isNewMessageNotificationEnabled = sharedPrefs.getBoolean(T1_NEW_MESSAGE_NOTIFICATION_SETTING_KEY, true);
        }
        if (containsNotificationSetting || containsLightSetting || containsVibrateSetting || containsSoundSetting || containsNewMessageNotificationSetting) {
            Object map = new WritableNativeMap();
            map.putBoolean(T1_NOTIFICATIONS_ENABLED_SETTING_KEY, isNotificationsEnabled);
            map.putBoolean(T1_LIGHT_NOTIFICATION_SETTING_KEY, isLightEnabled);
            map.putBoolean(T1_VIBRATE_NOTIFICATION_SETTING_KEY, isVibrateEnabled);
            map.putBoolean(T1_SOUND_NOTIFICATION_SETTING_KEY, isSoundEnabled);
            map.putBoolean(T1_NEW_MESSAGE_NOTIFICATION_SETTING_KEY, isNewMessageNotificationEnabled);
            promise.a(map);
            return;
        }
        promise.a(null);
    }

    @ReactMethod
    public void markUserSettingsAsMigrated() {
        getReactApplicationContext().getSharedPreferences(T2_UPDATE_SHARED_PREFERENCES, 0).edit().putBoolean(T1_NOTIFICATIONS_MIGRATED_KEY, true).apply();
    }

    @ReactMethod
    public void isT1UserSettingsMigrated(ae promise) {
        promise.a(Boolean.valueOf(getReactApplicationContext().getSharedPreferences(T2_UPDATE_SHARED_PREFERENCES, 0).getBoolean(T1_NOTIFICATIONS_MIGRATED_KEY, false)));
    }

    @ReactMethod
    public void getMaxViewSizeForTexture(ae promise) {
        FLog.i(RN_CLASS, "DeviceUtilities: screenDpi");
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(new IllegalStateException("DeviceUtilities: No activity"));
            return;
        }
        float density = activity.getResources().getDisplayMetrics().density;
        int maxTextureSize = getMaxTextureSize();
        if (maxTextureSize == 0) {
            maxTextureSize = DEFAULT_MAX_TEXTURE_SIZE;
        }
        promise.a(Integer.valueOf((int) (((float) maxTextureSize) / density)));
    }

    @ReactMethod
    public void getPhoneNumber(ae promise) {
        Object phoneNumber = ((TelephonyManager) getReactApplicationContext().getSystemService("phone")).getLine1Number();
        if (phoneNumber == null) {
            phoneNumber = "";
        }
        promise.a(phoneNumber);
    }

    @ReactMethod
    public void getMncMccCode(ae promise) {
        TelephonyManager telephonyManager = (TelephonyManager) getReactApplicationContext().getSystemService("phone");
        if (telephonyManager == null) {
            promise.a("Telephony manager is null");
            return;
        }
        String mCCMncCode = telephonyManager.getSimOperator();
        String mccCode = null;
        String mncCode = null;
        if (TextUtils.isEmpty(mCCMncCode)) {
            promise.a("Network operator info is empty");
            return;
        }
        if (mCCMncCode.length() >= 3) {
            mccCode = mCCMncCode.substring(0, 3);
        }
        if (mCCMncCode.length() > 3) {
            mncCode = mCCMncCode.substring(3);
        }
        Object map = new WritableNativeMap();
        map.putString("mncCode", mncCode);
        map.putString("mccCode", mccCode);
        promise.a(map);
    }

    @ReactMethod
    public void registerSMSReader() {
        synchronized (this) {
            if (this.smsReceiver == null) {
                this.smsReceiver = new BroadcastReceiver(this) {
                    final /* synthetic */ DeviceUtilitiesModule a;

                    {
                        this.a = this$0;
                    }

                    public final void onReceive(Context context, Intent intent) {
                        this.a.handleSMSIntent(intent);
                    }
                };
                IntentFilter intentFilter = new IntentFilter(SMS_RECEIVED_ACTION);
                intentFilter.setPriority(SMS_RECEIVER_PRIORITY);
                getReactApplicationContext().registerReceiver(this.smsReceiver, intentFilter);
            }
        }
    }

    @ReactMethod
    public void unregisterSMSReader() {
        synchronized (this) {
            if (this.smsReceiver != null) {
                getReactApplicationContext().unregisterReceiver(this.smsReceiver);
                this.smsReceiver = null;
            }
        }
    }

    @ReactMethod
    public void triggerFeedback(ae promise) {
        try {
            Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
            if (v == null || !v.hasVibrator()) {
                promise.a(Boolean.valueOf(false));
                return;
            }
            v.vibrate(VIBRATION_SHORT_MS);
            promise.a(Boolean.valueOf(true));
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    private void handleSMSIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Object[] objects = (Object[]) bundle.get("pdus");
            String format = (String) bundle.get("format");
            aq messages = new WritableNativeArray();
            for (Object object : objects) {
                SmsMessage smsMessage = createSmsMessage((byte[]) object, format);
                if (smsMessage != null) {
                    messages.pushString(smsMessage.getMessageBody());
                }
            }
            if (messages.size() > 0) {
                ar data = new WritableNativeMap();
                data.putArray(KEY_SMS_MESSAGE_RECEIVED, messages);
                ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(SMS_MESSAGE_RECEIVED_EVENT, data);
            }
        }
    }

    private SmsMessage createSmsMessage(byte[] message, String format) {
        if (VERSION.SDK_INT >= 23) {
            return SmsMessage.createFromPdu(message, format);
        }
        return SmsMessage.createFromPdu(message);
    }

    private int getMaxTextureSize() {
        EGLDisplay display = EGL14.eglGetDisplay(0);
        if (display != null) {
            int maxTextureSize = getMaxTextureSizeInternal(display);
            EGL14.eglTerminate(display);
            return maxTextureSize;
        }
        FLog.w(RN_CLASS, "Unable to get EGL14 display");
        return 0;
    }

    private int getMaxTextureSizeInternal(EGLDisplay display) {
        int[] versions = new int[2];
        if (EGL14.eglInitialize(display, versions, 0, versions, 1)) {
            EGLConfig[] configs = new EGLConfig[1];
            int[] numberOfConfigs = new int[1];
            if (EGL14.eglChooseConfig(display, new int[]{12352, 4, 12339, 1, 12351, 12430, 12329, 0, 12344}, 0, configs, 0, 1, numberOfConfigs, 0) && numberOfConfigs[0] != 0) {
                return getMaxTextureSizeInternal(display, configs[0]);
            }
            FLog.w(RN_CLASS, "Unable to choose EGL14 config");
            return 0;
        }
        FLog.w(RN_CLASS, "Unable to initialize EGL14");
        return 0;
    }

    private int getMaxTextureSizeInternal(EGLDisplay display, EGLConfig config) {
        EGLSurface surface = EGL14.eglCreatePbufferSurface(display, config, new int[]{12375, 64, 12374, 64, 12344}, 0);
        if (surface != null) {
            int maxTextureSize = getMaxTextureSizeInternal(display, config, surface);
            EGL14.eglDestroySurface(display, surface);
            return maxTextureSize;
        }
        FLog.w(RN_CLASS, "Unable to create EGL14 surface");
        return 0;
    }

    private int getMaxTextureSizeInternal(EGLDisplay display, EGLConfig config, EGLSurface surface) {
        int maxTextureSize = 0;
        EGLContext context = EGL14.eglCreateContext(display, config, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        if (context != null) {
            if (EGL14.eglMakeCurrent(display, surface, surface, context)) {
                int[] maxSize = new int[1];
                GLES20.glGetIntegerv(3379, maxSize, 0);
                maxTextureSize = maxSize[0];
                EGL14.eglMakeCurrent(display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            } else {
                FLog.w(RN_CLASS, "Unable to make EGL14 context current");
            }
            EGL14.eglDestroyContext(display, context);
        } else {
            FLog.w(RN_CLASS, "Unable to create EGL14 context");
        }
        return maxTextureSize;
    }

    private int currentLandscapeScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 0 || rotation == 1) {
            return 0;
        }
        return 8;
    }
}
