package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.EGL14;
import android.opengl.EGLObjectHandle;

public final class EGLUtils {
    public static void validate(String msg) throws GLException {
        int result = EGL14.eglGetError();
        if (result != 12288) {
            throw new GLException(msg, result);
        }
    }

    public static String idStringOf(EGLObjectHandle eglObjectHandle) {
        if (eglObjectHandle == null) {
            return "null";
        }
        StringBuilder str = new StringBuilder();
        str.append(eglObjectHandle.getClass().getSimpleName());
        str.append("@0x");
        str.append(Integer.toHexString(eglObjectHandle.hashCode()));
        return str.toString();
    }
}
