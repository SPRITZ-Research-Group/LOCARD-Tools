package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.ErrorCodeException;

public class CaptureException extends ErrorCodeException {
    public CaptureException(String detailMessage, ErrorCode errorCode) {
        super(detailMessage, errorCode);
    }

    public CaptureException(String detailMessage, ErrorCode errorCode, String errorInfo) {
        super(detailMessage, errorCode, errorInfo);
    }

    public CaptureException(Throwable throwable, ErrorCode errorCode) {
        super(throwable, errorCode);
    }

    public CaptureException(String detailMessage, Throwable throwable, ErrorCode errorCode) {
        super(detailMessage, throwable, errorCode);
    }
}
