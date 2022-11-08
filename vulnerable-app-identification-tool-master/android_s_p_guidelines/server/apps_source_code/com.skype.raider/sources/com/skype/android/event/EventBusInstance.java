package com.skype.android.event;

public class EventBusInstance {
    private static final EventBus a = new EventBus();

    private EventBusInstance() {
    }

    public static EventBus a() {
        return a;
    }
}
