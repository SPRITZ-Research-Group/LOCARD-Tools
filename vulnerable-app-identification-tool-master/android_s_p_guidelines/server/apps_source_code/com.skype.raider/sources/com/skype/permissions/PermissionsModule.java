package com.skype.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.ap;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PermissionsModule extends ReactContextBaseJavaModule {
    private static final String TAG = "PermissionsModule";
    @Nullable
    private static ae permissionPromise;
    private Context context;
    private a mPermissionsQueue = a.a("PermissionsQueue", b.DEFAULT);

    /* renamed from: com.skype.permissions.PermissionsModule$3 */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[PermissionAction.values().length];

        static {
            try {
                a[PermissionAction.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[PermissionAction.CHECK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum PermissionAction {
        CHECK,
        REQUEST
    }

    public enum PermissionStatus {
        UNKNOWN,
        GRANTED,
        DENIED
    }

    public enum PermissionType {
        MICROPHONE(0, "android.permission.RECORD_AUDIO"),
        CAMERA(1, "android.permission.CAMERA"),
        CAMERAROLL(2, "android.permission.READ_EXTERNAL_STORAGE"),
        CONTACTS(3, "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"),
        LOCATION(4, "android.permission.ACCESS_FINE_LOCATION"),
        SEND_SMS(5, "android.permission.SEND_SMS"),
        READ_SMS(6, "android.permission.READ_SMS"),
        RECEIVE_SMS(7, "android.permission.RECEIVE_SMS"),
        READ_PHONE_STATE(8, "android.permission.READ_PHONE_STATE"),
        DRAW_OVER(9, "android.permission.SYSTEM_ALERT_WINDOW");
        
        public final int k;
        @Nonnull
        public final String[] l;

        private PermissionType(int value, String... permissions) {
            this.k = value;
            this.l = permissions;
        }
    }

    public PermissionsModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext.getApplicationContext();
    }

    public String getName() {
        return "PermissionsManager";
    }

    public static void onRequestPermissionResult(int requestCode, @Nonnull String[] permissions, @Nonnull int[] grantResults) {
        if (permissionPromise != null) {
            if (grantResults.length == 0) {
                permissionPromise.a(Boolean.valueOf(false));
            } else {
                for (int i : grantResults) {
                    if (i != 0) {
                        permissionPromise.a(Boolean.valueOf(false));
                        permissionPromise = null;
                        return;
                    }
                }
                permissionPromise.a(Boolean.valueOf(true));
            }
            permissionPromise = null;
        }
    }

    @ReactMethod
    public void cameraPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.CAMERA, promise);
        }
    }

    @ReactMethod
    public void cameraRollPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.CAMERAROLL, promise);
        }
    }

    @ReactMethod
    public void microphonePermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.MICROPHONE, promise);
        }
    }

    @ReactMethod
    public void contactsPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.CONTACTS, promise);
        }
    }

    @ReactMethod
    public void locationPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.LOCATION, promise);
        }
    }

    @ReactMethod
    public void locationAlwaysPermissionStatus(ae promise) {
        locationPermissionStatus(promise);
    }

    @ReactMethod
    public void sendSmsPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.SEND_SMS, promise);
        }
    }

    @ReactMethod
    public void readSmsPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.READ_SMS, promise);
        }
    }

    @ReactMethod
    public void receiveSmsPermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.RECEIVE_SMS, promise);
        }
    }

    @ReactMethod
    public void readPhoneStatePermissionStatus(ae promise) {
        if (promise != null) {
            permissionStatus(PermissionType.READ_PHONE_STATE, promise);
        }
    }

    @ReactMethod
    public void drawOverPermissionStatus(ae promise) {
        if (promise != null) {
            promise.a(Boolean.valueOf(drawOverPermissionStatus()));
        }
    }

    @ReactMethod
    public void playServicesPermissionStatus(ae promise) {
        if (promise != null) {
            GoogleApiAvailability apiAvailability = GoogleApiAvailability.a();
            int status = apiAvailability.a(this.context);
            if (status == 0 || !apiAvailability.a(status)) {
                promise.a(Integer.valueOf(PermissionStatus.GRANTED.ordinal()));
            } else {
                promise.a(Integer.valueOf(PermissionStatus.UNKNOWN.ordinal()));
            }
        }
    }

    @ReactMethod
    public void backgroundExecutionStatus(ae promise) {
        if (promise != null) {
            promise.a(Integer.valueOf(PermissionStatus.UNKNOWN.ordinal()));
        }
    }

    @ReactMethod
    public void calendarPermissionStatus(ae promise) {
        if (promise != null) {
            promise.a(Integer.valueOf(PermissionStatus.GRANTED.ordinal()));
        }
    }

    @ReactMethod
    public void requestCameraPermission(ae promise) {
        requestPermission(PermissionType.CAMERA, promise);
    }

    @ReactMethod
    public void requestCameraRollPermission(ae promise) {
        requestPermission(PermissionType.CAMERAROLL, promise);
    }

    @ReactMethod
    public void requestMicrophonePermission(ae promise) {
        requestPermission(PermissionType.MICROPHONE, promise);
    }

    @ReactMethod
    public void requestContactsPermission(ae promise) {
        requestPermission(PermissionType.CONTACTS, promise);
    }

    @ReactMethod
    public void requestLocationPermission(ae promise) {
        requestPermission(PermissionType.LOCATION, promise);
    }

    @ReactMethod
    public void requestLocationAlwaysPermission(ae promise) {
        requestLocationPermission(promise);
    }

    @ReactMethod
    public void requestSendSmsPermission(ae promise) {
        requestPermission(PermissionType.SEND_SMS, promise);
    }

    @ReactMethod
    public void requestReadSmsPermission(ae promise) {
        requestPermission(PermissionType.READ_SMS, promise);
    }

    @ReactMethod
    public void requestReceiveSmsPermission(ae promise) {
        requestPermission(PermissionType.RECEIVE_SMS, promise);
    }

    @ReactMethod
    public void requestReadPhoneStatePermission(ae promise) {
        requestPermission(PermissionType.READ_PHONE_STATE, promise);
    }

    @ReactMethod
    public void requestCalendarPermission(ae promise) {
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void requestDrawOverPermission(ae promise) {
        if (drawOverPermissionStatus()) {
            promise.a(Boolean.valueOf(true));
            return;
        }
        startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.context.getPackageName())));
        promise.a(Boolean.valueOf(false));
    }

    @ReactMethod
    public void requestPlayServicesPermission(ae promise) {
        final GoogleApiAvailability apiAvailability = GoogleApiAvailability.a();
        final int status = apiAvailability.a(this.context);
        if (status == 0 || !apiAvailability.a(status)) {
            promise.a(Boolean.valueOf(false));
            return;
        }
        ap.a(new Runnable(this) {
            final /* synthetic */ PermissionsModule c;

            public final void run() {
                int localStatus = (this.c.isPlayServicesEnabled() || status != 2) ? status : 3;
                apiAvailability.a(this.c.getCurrentActivity(), localStatus);
            }
        });
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void requestBackgroundExecutionPermission(ae promise) {
        promise.a("requestBackgroundExecutionPermission not supported");
    }

    private boolean isPlayServicesEnabled() {
        boolean playServicesEnabled = true;
        try {
            return this.context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).enabled;
        } catch (NameNotFoundException e) {
            FLog.e(TAG, Log.getStackTraceString(e));
            return playServicesEnabled;
        }
    }

    private boolean drawOverPermissionStatus() {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        return Settings.canDrawOverlays(this.context);
    }

    private void startActivity(Intent intent) {
        Context context = getReactApplicationContext();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivity(intent);
        } else {
            context.startActivity(intent);
        }
    }

    private void permissionStatus(PermissionType type, ae promise) {
        permissionAction(getCurrentActivity(), type, promise, PermissionAction.CHECK);
    }

    private void permissionRequest(PermissionType type, ae promise) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            throw new IllegalStateException("Requesting permission without active activity");
        }
        permissionAction(activity, type, promise, PermissionAction.REQUEST);
    }

    private void permissionAction(Activity activity, @Nonnull PermissionType type, ae promise, PermissionAction actionType) {
        final PermissionType permissionType = type;
        final PermissionAction permissionAction = actionType;
        final ae aeVar = promise;
        final Activity activity2 = activity;
        this.mPermissionsQueue.b(new Runnable(this) {
            final /* synthetic */ PermissionsModule e;

            @TargetApi(23)
            public final void run() {
                PermissionStatus combinedStatus = PermissionStatus.GRANTED;
                if (VERSION.SDK_INT < 23) {
                    combinedStatus = PermissionStatus.GRANTED;
                } else {
                    Activity activity = this.e.getCurrentActivity();
                    if (activity == null) {
                        combinedStatus = PermissionStatus.UNKNOWN;
                    } else {
                        for (String permission : permissionType.l) {
                            if (PermissionChecker.a(this.e.context, permission) != 0) {
                                PermissionStatus permissionStatus = activity.shouldShowRequestPermissionRationale(permission) ? PermissionStatus.DENIED : PermissionStatus.UNKNOWN;
                                if (!(combinedStatus == permissionStatus || combinedStatus == PermissionStatus.DENIED)) {
                                    combinedStatus = permissionStatus;
                                }
                            }
                        }
                    }
                }
                switch (AnonymousClass3.a[permissionAction.ordinal()]) {
                    case 1:
                        if (combinedStatus == PermissionStatus.GRANTED) {
                            PermissionsModule.permissionPromise = null;
                            if (aeVar != null) {
                                aeVar.a(Boolean.valueOf(true));
                                return;
                            }
                            return;
                        }
                        PermissionsModule.permissionPromise = aeVar;
                        activity2.requestPermissions(permissionType.l, permissionType.k);
                        return;
                    case 2:
                        aeVar.a(Integer.valueOf(combinedStatus.ordinal()));
                        return;
                    default:
                        an.a("Unknown action type:" + permissionAction);
                        return;
                }
            }
        });
    }

    @TargetApi(23)
    private void requestPermission(@Nonnull PermissionType permissionType, ae promise) {
        permissionRequest(permissionType, promise);
    }
}
