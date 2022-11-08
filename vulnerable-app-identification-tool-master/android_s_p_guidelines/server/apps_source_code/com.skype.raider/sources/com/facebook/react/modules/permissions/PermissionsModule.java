package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.SparseArray;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.c;
import com.facebook.react.modules.core.d;
import java.util.ArrayList;

@ReactModule(name = "PermissionsAndroid")
public class PermissionsModule extends ReactContextBaseJavaModule implements d {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private final String DENIED = "denied";
    private final String GRANTED = "granted";
    private final String NEVER_ASK_AGAIN = "never_ask_again";
    private final SparseArray<com.facebook.react.bridge.d> mCallbacks = new SparseArray();
    private int mRequestCode = 0;

    public PermissionsModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "PermissionsAndroid";
    }

    @ReactMethod
    public void checkPermission(String permission, ae promise) {
        boolean z = true;
        Context context = getReactApplicationContext().getBaseContext();
        if (VERSION.SDK_INT < 23) {
            if (context.checkPermission(permission, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.a(Boolean.valueOf(z));
            return;
        }
        if (context.checkSelfPermission(permission) != 0) {
            z = false;
        }
        promise.a(Boolean.valueOf(z));
    }

    @ReactMethod
    public void shouldShowRequestPermissionRationale(String permission, ae promise) {
        if (VERSION.SDK_INT < 23) {
            promise.a(Boolean.valueOf(false));
            return;
        }
        try {
            promise.a(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(permission)));
        } catch (Throwable e) {
            promise.a(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @ReactMethod
    public void requestPermission(final String permission, final ae promise) {
        boolean z = true;
        Context context = getReactApplicationContext().getBaseContext();
        if (VERSION.SDK_INT < 23) {
            if (context.checkPermission(permission, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.a(Boolean.valueOf(z));
        } else if (context.checkSelfPermission(permission) == 0) {
            promise.a((Object) "granted");
        } else {
            try {
                c activity = getPermissionAwareActivity();
                this.mCallbacks.put(this.mRequestCode, new com.facebook.react.bridge.d(this) {
                    final /* synthetic */ PermissionsModule c;

                    public final void invoke(Object... args) {
                        if (((int[]) args[0])[0] == 0) {
                            promise.a((Object) "granted");
                        } else if (((c) args[1]).shouldShowRequestPermissionRationale(permission)) {
                            promise.a((Object) "denied");
                        } else {
                            promise.a((Object) "never_ask_again");
                        }
                    }
                });
                activity.a(new String[]{permission}, this.mRequestCode, this);
                this.mRequestCode++;
            } catch (Throwable e) {
                promise.a(ERROR_INVALID_ACTIVITY, e);
            }
        }
    }

    @ReactMethod
    public void requestMultiplePermissions(al permissions, final ae promise) {
        final Object grantedPermissions = new WritableNativeMap();
        final ArrayList<String> permissionsToCheck = new ArrayList();
        int checkedPermissionsCount = 0;
        Context context = getReactApplicationContext().getBaseContext();
        for (int i = 0; i < permissions.size(); i++) {
            String perm = permissions.getString(i);
            if (VERSION.SDK_INT < 23) {
                grantedPermissions.putString(perm, context.checkPermission(perm, Process.myPid(), Process.myUid()) == 0 ? "granted" : "denied");
                checkedPermissionsCount++;
            } else if (context.checkSelfPermission(perm) == 0) {
                grantedPermissions.putString(perm, "granted");
                checkedPermissionsCount++;
            } else {
                permissionsToCheck.add(perm);
            }
        }
        if (permissions.size() == checkedPermissionsCount) {
            promise.a(grantedPermissions);
            return;
        }
        try {
            c activity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new com.facebook.react.bridge.d(this) {
                final /* synthetic */ PermissionsModule d;

                public final void invoke(Object... args) {
                    int[] results = args[0];
                    c activity = args[1];
                    for (int j = 0; j < permissionsToCheck.size(); j++) {
                        String permission = (String) permissionsToCheck.get(j);
                        if (results[j] == 0) {
                            grantedPermissions.putString(permission, "granted");
                        } else if (activity.shouldShowRequestPermissionRationale(permission)) {
                            grantedPermissions.putString(permission, "denied");
                        } else {
                            grantedPermissions.putString(permission, "never_ask_again");
                        }
                    }
                    promise.a(grantedPermissions);
                }
            });
            activity.a((String[]) permissionsToCheck.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (Throwable e) {
            promise.a(ERROR_INVALID_ACTIVITY, e);
        }
    }

    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ((com.facebook.react.bridge.d) this.mCallbacks.get(requestCode)).invoke(grantResults, getPermissionAwareActivity());
        this.mCallbacks.remove(requestCode);
        return this.mCallbacks.size() == 0;
    }

    private c getPermissionAwareActivity() {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (activity instanceof c) {
            return (c) activity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }
}
