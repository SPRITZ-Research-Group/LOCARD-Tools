package com.skype.android.event;

import com.skype.PROPKEY;

public class ListenerError extends Error {
    public ListenerError(Throwable throwable) {
        super(throwable);
    }

    public ListenerError(PROPKEY propkey, Throwable throwable) {
        super(propkey.name(), throwable);
    }
}
