package com.microsoft.dl.video.graphics;

import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.ErrorCodeException;

public abstract class GraphicsException extends ErrorCodeException {
    public GraphicsException(String detailMessage, ErrorCode dlErrorCode) {
        super(detailMessage, dlErrorCode);
    }

    public GraphicsException(String detailMessage, int graphicsErrorCode, ErrorCode dlErrorCode) {
        super(detailMessage, dlErrorCode, String.valueOf(graphicsErrorCode));
    }

    public GraphicsException(Throwable throwable, ErrorCode dlErrorCode) {
        super(throwable, dlErrorCode);
    }
}
