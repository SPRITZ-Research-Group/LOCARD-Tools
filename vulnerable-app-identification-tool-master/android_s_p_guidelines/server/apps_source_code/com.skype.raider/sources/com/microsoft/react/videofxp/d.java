package com.microsoft.react.videofxp;

import android.annotation.TargetApi;
import android.opengl.EGL14;

@TargetApi(17)
final class d extends RuntimeException {
    d(String message) {
        super(message);
    }

    private d(int error, String message) {
        super("EGL error: " + error + " " + message);
    }

    static void a(String message) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new d(error, message);
        }
    }
}
