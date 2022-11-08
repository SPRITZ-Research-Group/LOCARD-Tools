package com.microsoft.dl.video.graphics.egl;

import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.graphics.GraphicsException;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;

public class EGLException extends GraphicsException {
    public EGLException(String detailMessage, ErrorCode errorCode) {
        super(detailMessage, errorCode);
    }

    public EGLException(String detailMessage, int eglError, ErrorCode errorCode) {
        super(detailMessage + (eglError != 12288 ? ". " + getErrorStr(eglError) : ""), eglError, errorCode);
    }

    public EGLException(Throwable throwable, ErrorCode errorCode) {
        super(throwable, errorCode);
    }

    public static void checkAfter(String context, ErrorCode errorCode) throws EGLException {
        int error = ((EGL10) EGLContext.getEGL()).eglGetError();
        if (error != 12288) {
            throw new EGLException("Error pending after " + context, error, errorCode);
        }
    }

    public static String getErrorStr(int eglError) {
        return "Error " + getErrorName(eglError) + " (0x" + Integer.toHexString(eglError) + ")";
    }

    public static String getErrorName(int eglError) {
        switch (eglError) {
            case 12288:
                return "EGL_SUCCESS";
            case 12289:
                return "EGL_NOT_INITIALIZED";
            case 12290:
                return "EGL_BAD_ACCESS";
            case 12291:
                return "EGL_BAD_ALLOC";
            case 12292:
                return "EGL_BAD_ATTRIBUTE";
            case 12293:
                return "EGL_BAD_CONFIG";
            case 12294:
                return "EGL_BAD_CONTEXT";
            case 12295:
                return "EGL_BAD_CURRENT_SURFACE";
            case 12296:
                return "EGL_BAD_DISPLAY";
            case 12297:
                return "EGL_BAD_MATCH";
            case 12298:
                return "EGL_BAD_NATIVE_PIXMAP";
            case 12299:
                return "EGL_BAD_NATIVE_WINDOW";
            case 12300:
                return "EGL_BAD_PARAMETER";
            case 12301:
                return "EGL_BAD_SURFACE";
            case 12302:
                return "EGL_CONTEXT_LOST";
            default:
                return "Unknown";
        }
    }
}
