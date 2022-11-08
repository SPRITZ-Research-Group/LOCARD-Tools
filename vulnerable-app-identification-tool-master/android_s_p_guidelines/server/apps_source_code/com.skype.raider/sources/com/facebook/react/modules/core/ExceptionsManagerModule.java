package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.al;
import com.facebook.react.common.b;
import com.facebook.react.devsupport.a.a;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "ExceptionsManager")
public class ExceptionsManagerModule extends BaseJavaModule {
    protected static final String NAME = "ExceptionsManager";
    private final a mDevSupportManager;

    public ExceptionsManagerModule(a devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void reportFatalException(String title, al details, int exceptionId) {
        showOrThrowError(title, details, exceptionId);
    }

    @ReactMethod
    public void reportSoftException(String title, al details, int exceptionId) {
        FLog.e("React", com.facebook.react.e.a.a(title, details));
    }

    private void showOrThrowError(String title, al details, int exceptionId) {
        throw new b(com.facebook.react.e.a.a(title, details));
    }

    @ReactMethod
    public void updateExceptionMessage(String title, al details, int exceptionId) {
    }

    @ReactMethod
    public void dismissRedbox() {
    }
}
