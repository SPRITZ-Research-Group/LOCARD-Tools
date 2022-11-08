package com.facebook.react.bridge;

import javax.annotation.Nullable;

public class m extends RuntimeException {
    public m(String detailMessage) {
        super(detailMessage);
    }

    public m(@Nullable String detailMessage, @Nullable Throwable throwable) {
        super(detailMessage, throwable);
    }
}
