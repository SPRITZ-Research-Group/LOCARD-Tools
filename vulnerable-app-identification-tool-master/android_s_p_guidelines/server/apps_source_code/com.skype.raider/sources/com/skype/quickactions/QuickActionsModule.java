package com.skype.quickactions;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ShortcutManager;
import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import java.util.HashMap;
import java.util.Map;

@TargetApi(25)
public class QuickActionsModule extends ReactContextBaseJavaModule {
    private static final String ERROR_UNSUPPORTED_PLATFORM = "Unsupported Platform";
    static final String IS_SYSTEM_SUPPORTED = "isSystemSupported";
    private static final String MODULE_NAME = "QuickActions";
    private static final String TAG = "QuickActions";
    private Context context;
    private boolean isShortcutSupported = _isSystemSupported();
    private a quickActionsHelper;

    QuickActionsModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext.getApplicationContext();
    }

    public String getName() {
        return "QuickActions";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        FLog.i("QuickActions", "isSystemSupported: " + this.isShortcutSupported);
        constants.put(IS_SYSTEM_SUPPORTED, Boolean.valueOf(this.isShortcutSupported));
        return constants;
    }

    @ReactMethod
    public void removeAll(ae promise) {
        if (this.isShortcutSupported) {
            boolean result = this.quickActionsHelper.a();
            FLog.i("QuickActions", result ? "Update Successful" : "Update Failed");
            promise.a(Boolean.valueOf(result));
            return;
        }
        promise.a(ERROR_UNSUPPORTED_PLATFORM, "Fail to remove all.");
    }

    @ReactMethod
    public void updateAll(al shortcutsArray, ae promise) {
        if (this.isShortcutSupported) {
            boolean result = this.quickActionsHelper.a(shortcutsArray);
            FLog.i("QuickActions", result ? "Update Successful" : "Update Failed");
            promise.a(Boolean.valueOf(result));
            return;
        }
        promise.a(ERROR_UNSUPPORTED_PLATFORM, "Fail to update.");
    }

    @ReactMethod
    public void getAllPinnedShortcuts(ae promise) {
        try {
            promise.a(this.quickActionsHelper.b());
        } catch (NullPointerException e) {
            promise.a(ERROR_UNSUPPORTED_PLATFORM, "Pinned Shortcut is NUll.");
        } catch (UnsupportedOperationException e2) {
            promise.a(ERROR_UNSUPPORTED_PLATFORM, "Pinned Shortcut not supported.");
        }
    }

    private boolean _isSystemSupported() {
        boolean z;
        if (VERSION.SDK_INT >= 25) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        try {
            ShortcutManager shortcutManager = (ShortcutManager) this.context.getSystemService(ShortcutManager.class);
            this.quickActionsHelper = new a(this.context);
            this.quickActionsHelper.a(shortcutManager);
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (NoClassDefFoundError e2) {
            return false;
        }
    }
}
