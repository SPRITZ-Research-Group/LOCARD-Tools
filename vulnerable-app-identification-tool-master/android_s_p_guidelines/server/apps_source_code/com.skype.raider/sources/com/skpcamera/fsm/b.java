package com.skpcamera.fsm;

import com.facebook.common.logging.FLog;
import com.skypecam.obscura.e.h;

public final class b implements h {
    public final void a(String TAG, String debug) {
        FLog.d(TAG, debug);
    }

    public final void b(String TAG, String message) {
        FLog.i(TAG, message);
    }

    public final void c(String TAG, String warning) {
        FLog.w(TAG, warning);
    }

    public final void d(String TAG, String error) {
        FLog.e(TAG, error);
    }

    public final void a(String TAG, String error, Throwable throwable) {
        FLog.e(TAG, error, throwable);
    }
}
