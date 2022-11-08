package com.projectseptember.RNGL;

import android.annotation.TargetApi;
import android.opengl.EGL14;

@TargetApi(17)
final class c extends RuntimeException {
    c(String message) {
        super(message);
    }

    private c(int error, String message) {
        super("EGL error: " + error + " " + message);
    }

    static void a(String message) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new c(error, message);
        }
    }
}
