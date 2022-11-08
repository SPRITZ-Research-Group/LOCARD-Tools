package com.skype.credentials;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;

public class SkypeCredentialsFetcherModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNSkypeCredentialsFetcher";
    private static final String TAG = "SkypeCredentialsFetcher";

    public SkypeCredentialsFetcherModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void fetchUserInfo(boolean forceFetch, ae promise) {
        Context context = getReactApplicationContext();
        String passwordHash = null;
        String msaRefreshToken = null;
        if (!CredentialsFetcher.c(context) || forceFetch) {
            String defaultUser = CredentialsFetcher.a(context);
            if (defaultUser != null) {
                passwordHash = CredentialsFetcher.a(context, defaultUser);
                msaRefreshToken = CredentialsFetcher.b(context, defaultUser);
            }
            Object map = new WritableNativeMap();
            map.putString("username", defaultUser);
            map.putString("passwordHash", passwordHash);
            map.putString("msaRefreshToken", msaRefreshToken);
            promise.a(map);
            return;
        }
        FLog.i(TAG, "Already migrated account. Ignore.");
        promise.a("credentials_migrated", "credentials have been set to migrated already, use forceFetch=true to retrieve them again");
    }

    @ReactMethod
    public void markAsMigrated() {
        CredentialsFetcher.b(getReactApplicationContext());
    }
}
