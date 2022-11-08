package com.skype.android.video.hw.codec.encoder.camera.gl;

public class GLException extends Exception {
    private static final long serialVersionUID = 1;

    public GLException(String detailMessage, int eglError) {
        super(detailMessage + " Error: 0x" + Integer.toHexString(eglError));
    }

    GLException(String detailMessage) {
        super(detailMessage);
    }

    public GLException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public GLException(Throwable throwable) {
        super(throwable);
    }
}
