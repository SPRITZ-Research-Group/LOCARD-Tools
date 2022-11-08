package com.skype.android.jipc;

public class LoopholeClosedException extends SecurityException {
    public LoopholeClosedException(Throwable cause) {
        super(cause);
    }

    public LoopholeClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
