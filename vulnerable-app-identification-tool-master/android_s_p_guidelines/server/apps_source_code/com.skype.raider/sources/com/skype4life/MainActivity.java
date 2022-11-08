package com.skype4life;

import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationManagerCompat;
import android.view.KeyEvent;
import com.customkeyboard.CustomKeyboard;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.i;
import com.facebook.react.l.b;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.react.incomingcallinteractionmanager.IncomingCallInteractionManagerModule;
import com.microsoft.react.incomingcallinteractionmanager.a;
import com.microsoft.react.push.d;
import com.skype.callintent.CallIntentModule;
import com.skype.device.DeviceUtilitiesModule;
import com.skype.permissions.PermissionsModule;
import com.skype.react.upgrade.AppUpgradeModule;
import com.skype.react.upgrade.UpgradeConstants;
import com.skype.sharetoapp.ShareToAppModule;
import com.skype4life.syncadapter.c;

public class MainActivity extends ReactActivity implements b, d, UpgradeConstants {
    private static CustomKeyboard g;
    private static DeviceUtilitiesModule i;
    private static ShareToAppModule j;
    private static CallIntentModule k;
    private static IncomingCallInteractionManagerModule l;
    private boolean b;
    private boolean c;
    private boolean d;
    private c e;
    private ai f;
    private c h;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FLog.i("MainActivity", "onCreate");
        if (VERSION.SDK_INT == 23) {
            int identifier = Resources.getSystem().getIdentifier("config_webViewPackageName", "string", "android");
            String str = "com.google.android.webview";
            if (identifier != 0) {
                str = Resources.getSystem().getString(identifier);
            }
            try {
                getPackageManager().getPackageInfo(str, 128);
            } catch (NameNotFoundException e) {
                FLog.w("MainActivity", "Device have no WebView. Redirecting to the playstore to install it");
                startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + str)));
                finish();
            }
        }
        this.d = true;
        boolean z = savedInstanceState == null && (getIntent().getFlags() & 1048576) == 0;
        this.c = z;
        this.e = new c(this);
        ((SkypeApplication) getApplicationContext()).a((b) this);
    }

    protected void onDestroy() {
        super.onDestroy();
        FLog.i("MainActivity", "onDestroy");
    }

    protected final String a() {
        return "RXApp";
    }

    public final void a(ai context) {
        if (context != null) {
            FLog.i("MainActivity", "onReactContextInitialized");
            this.f = context;
            g = (CustomKeyboard) context.b(CustomKeyboard.class);
            i = (DeviceUtilitiesModule) context.b(DeviceUtilitiesModule.class);
            j = (ShareToAppModule) context.b(ShareToAppModule.class);
            k = (CallIntentModule) context.b(CallIntentModule.class);
            l = (IncomingCallInteractionManagerModule) context.b(IncomingCallInteractionManagerModule.class);
            l.setDeviceUtilitiesProvider(new a() {
                public final void a() {
                    MainActivity.i.disableKeyguardNew();
                }

                public final void b() {
                    MainActivity.i.reEnableKeyguardIfSetNew();
                }

                public final void c() {
                    MainActivity.i.enableTurnScreenOn();
                }

                public final void d() {
                    MainActivity.i.disableTurnScreenOn();
                }

                public final void e() {
                    MainActivity.i.enableShowWhenLocked();
                }

                public final void f() {
                    MainActivity.i.disableShowWhenLocked();
                }
            });
            if (this.b) {
                f();
            }
            this.h.a(this.f);
        }
    }

    public final void d() {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.h.a(new ColorDrawable(-1));
                this.a.getWindow().setBackgroundDrawable(new ColorDrawable(-1));
            }
        });
    }

    protected final i b() {
        this.h = new c(this, "RXApp");
        return this.h;
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FLog.i("MainActivity", "onNewIntent: " + intent.getAction());
        if (b(intent.getExtras())) {
            a(intent);
        }
        setIntent(intent);
        this.c = true;
        this.d = false;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (g != null) {
            g.onConfigurationChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        FLog.i("MainActivity", "onResume");
        this.b = true;
        f();
    }

    protected void onPause() {
        super.onPause();
        FLog.i("MainActivity", "onPause");
        this.b = false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled = false;
        if (keyCode == 25 || keyCode == 24) {
            if (i != null) {
                handled = i.onVolumeKeyDown(keyCode, event);
            }
        } else if (keyCode == 4) {
            onBackPressed();
            handled = true;
        }
        if (handled) {
            return handled;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void f() {
        if (this.f == null || !this.f.b()) {
            FLog.i("MainActivity", "Failed to process launch action as reactContext not ready");
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        FLog.i("MainActivity", "Processing launch action: " + action + " isNew: " + this.c);
        Bundle extras;
        if (this.c && "LocalNotificationActionReceived".equals(action)) {
            extras = intent.getExtras();
            if (extras != null && b(extras)) {
                a(intent);
                intent.removeExtra("enableFullScreenIncomingCall");
                intent.removeExtra("category");
            }
            a(extras);
            intent.removeExtra("isKeyguardOn");
        } else if (this.c && ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action))) {
            j.processIntent(intent);
        } else if (this.c && "WakeEventReceiver.ACTION_NOTIFICATION_CLICKED".equals(action)) {
            AppUpgradeModule.setAppLaunchAttribution(getApplicationContext(), true);
            AppUpgradeModule.raiseNotificationClickEvent(this.f.getApplicationContext(), intent);
        } else if (this.c && IncomingCallInteractionManagerModule.ACTION_INCOMING_RING_RECEIVED.equals(action)) {
            l.processLaunchIntent(intent);
        } else if (this.c && ("android.intent.action.CALL".equals(action) || "android.intent.action.DIAL".equals(action))) {
            k.processIntent(intent);
        } else if (this.c && "android.intent.action.VIEW".equals(action) && this.e.a(intent)) {
            k.processIntent(this.e.b(intent));
        } else {
            extras = new Bundle();
            extras.putString("category", "ChatCategoryIdentifier");
            a(extras);
        }
        this.c = false;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsModule.onRequestPermissionResult(requestCode, permissions, grantResults);
    }

    public void onBackPressed() {
        if (g != null && g.isDisplayingCustomKeyboard() && g.canHideKeyboardOnAndroidBackButton()) {
            g.dismissCustomKeyboards();
        } else {
            super.onBackPressed();
        }
    }

    private synchronized void a(Bundle extras) {
        ar localEvent;
        RCTDeviceEventEmitter eventEmitter = (RCTDeviceEventEmitter) this.f.a(RCTDeviceEventEmitter.class);
        if (extras == null) {
            localEvent = new WritableNativeMap();
        } else {
            localEvent = com.facebook.react.bridge.b.a(extras);
        }
        eventEmitter.emit("LocalNotificationActionReceived", localEvent);
        try {
            NotificationManagerCompat.from(this.f.getApplicationContext()).cancelAll();
        } catch (Exception ignored) {
            FLog.w("MainActivity", "Cannot cancel notifications. " + ignored);
        }
    }

    private static boolean b(Bundle bundle) {
        return bundle != null && bundle.getBoolean("enableFullScreenIncomingCall") && "CallCategoryIdentifier".equals(bundle.getString("category"));
    }

    private void a(Intent intent) {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        Bundle extras = intent.getExtras();
        if (!extras.containsKey("isKeyguardOn")) {
            extras.putBoolean("isKeyguardOn", keyguardManager.inKeyguardRestrictedInputMode());
            intent.putExtras(extras);
            if (this.d) {
                this.d = false;
                if (i != null) {
                    i.disableKeyguard();
                } else {
                    FLog.e("MainActivity", "Failed to disable keyguard");
                }
            }
        }
    }
}
