package com.microsoft.dl.video.graphics.gles;

import android.opengl.GLES20;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.graphics.GraphicsException;

public class GLException extends GraphicsException {
    public GLException(String detailMessage, int glError) {
        super(detailMessage + (glError != 0 ? ". " + getErrorStr(glError) : ""), glError, ErrorCode.ANDROID_GL_FAILURE);
    }

    public static void checkAfter(String context) throws GLException {
        int error = GLES20.glGetError();
        if (error != 0) {
            throw new GLException("Error pending after " + context, error);
        }
    }

    public static String getErrorStr(int glesError) {
        return "Erorr " + getErrorName(glesError) + " (0x" + Integer.toHexString(glesError) + ")";
    }

    public static String getErrorName(int eglError) {
        switch (eglError) {
            case 0:
                return "GL_NO_ERROR";
            case 1280:
                return "GL_INVALID_ENUM";
            case 1281:
                return "GL_INVALID_VALUE";
            case 1282:
                return "GL_INVALID_OPERATION";
            case 1285:
                return "GL_OUT_OF_MEMORY";
            case 1286:
                return "GL_INVALID_FRAMEBUFFER_OPERATION";
            default:
                return "Unknown";
        }
    }
}
