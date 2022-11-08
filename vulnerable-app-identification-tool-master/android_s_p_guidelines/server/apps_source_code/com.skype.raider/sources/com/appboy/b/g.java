package com.appboy.b;

import com.appboy.e.e;

public enum g implements e<String> {
    OPTED_IN,
    SUBSCRIBED,
    UNSUBSCRIBED;

    public final String a() {
        switch (this) {
            case UNSUBSCRIBED:
                return "unsubscribed";
            case SUBSCRIBED:
                return "subscribed";
            case OPTED_IN:
                return "opted_in";
            default:
                return null;
        }
    }
}
