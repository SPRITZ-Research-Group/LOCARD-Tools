package com.linecorp.rxeventbus;

public enum SubscriberType {
    MAIN(false, false),
    BACKGROUND(true, false),
    MAIN_STICKY(false, true),
    BACKGROUND_STICKY(true, true);
    
    static final SubscriberType[] STICKY_TYPES = null;
    private final boolean background;
    private final boolean sticky;

    static {
        STICKY_TYPES = new SubscriberType[]{MAIN_STICKY, BACKGROUND_STICKY};
    }

    private SubscriberType(boolean z, boolean z2) {
        this.background = z;
        this.sticky = z2;
    }

    final boolean a() {
        return this.background;
    }

    final boolean b() {
        return this.sticky;
    }
}
