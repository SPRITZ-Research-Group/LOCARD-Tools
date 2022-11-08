package com.skype.android.video.render;

import android.annotation.TargetApi;
import android.opengl.EGL14;

@TargetApi(17)
class EGLException extends RuntimeException {
    EGLException(String message) {
        super(message);
    }

    EGLException(int error, String message) {
        super("EGL error: " + error + " " + message);
    }

    EGLException(int error) {
        super("EGL error: " + error);
    }

    static void check(String message) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new EGLException(error, message);
        }
    }
}
