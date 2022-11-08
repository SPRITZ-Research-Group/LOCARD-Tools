package com.facebook.internal;

import com.facebook.s;

public enum i {
    Login(0),
    Share(1),
    Message(2),
    Like(3),
    GameRequest(4),
    AppGroupCreate(5),
    AppGroupJoin(6),
    AppInvite(7),
    DeviceShare(8),
    InAppPurchase(9);
    
    private final int offset;

    private i(int i) {
        this.offset = i;
    }

    public final int a() {
        return s.o() + this.offset;
    }
}
