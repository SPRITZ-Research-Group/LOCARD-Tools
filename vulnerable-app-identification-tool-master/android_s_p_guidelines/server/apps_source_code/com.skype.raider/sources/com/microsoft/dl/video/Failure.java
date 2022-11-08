package com.microsoft.dl.video;

public class Failure {
    private final long a;

    private static native long initialize(int i, String str);

    public Failure(ErrorCode errorCode) {
        this.a = initialize(errorCode.getCode(), null);
    }

    public Failure(ErrorCodeException exception) {
        this.a = initialize(exception.getErrorCode().getCode(), exception.getErrorInfo());
    }

    public Failure(RuntimeException exception) {
        this.a = initialize(ErrorCode.ANDROID_UNCHECKED_EXCEPTION.getCode(), null);
    }

    public long getNativeContext() {
        return this.a;
    }
}
