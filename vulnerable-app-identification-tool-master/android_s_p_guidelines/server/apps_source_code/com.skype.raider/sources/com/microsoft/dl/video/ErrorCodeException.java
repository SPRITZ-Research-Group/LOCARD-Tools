package com.microsoft.dl.video;

public abstract class ErrorCodeException extends Exception {
    private final ErrorCode a;
    private final String b;

    public ErrorCodeException(String detailMessage, ErrorCode errorCode) {
        this(detailMessage, errorCode, null);
    }

    public ErrorCodeException(String detailMessage, ErrorCode errorCode, String errorInfo) {
        super(detailMessage);
        this.a = errorCode;
        this.b = errorInfo;
    }

    public ErrorCodeException(Throwable throwable, ErrorCode errorCode) {
        super(throwable);
        this.a = errorCode;
        this.b = throwable.getMessage();
    }

    public ErrorCodeException(String detailMessage, Throwable throwable, ErrorCode errorCode) {
        super(detailMessage, throwable);
        this.a = errorCode;
        this.b = throwable.getMessage();
    }

    public ErrorCode getErrorCode() {
        return this.a;
    }

    public String getErrorInfo() {
        return this.b;
    }
}
