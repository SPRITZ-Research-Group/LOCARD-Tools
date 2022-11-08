package com.skype.android.video.hw.codec.encoder.camera.capture;

public class CapturerException extends Exception {
    private static final long serialVersionUID = 1;

    CapturerException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    CapturerException(String msg) {
        super(msg);
    }

    public CapturerException(Throwable throwable) {
        super(throwable);
    }
}
