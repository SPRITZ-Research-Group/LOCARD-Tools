package com.linecorp.andromeda;

import addon.greenrobot.eventbus.ThreadMode;
import addon.greenrobot.eventbus.p;
import com.linecorp.andromeda.audio.c;

public abstract class bg {
    @p(a = ThreadMode.MAIN)
    public void audioRouteEvent(c cVar) {
    }

    @p(a = ThreadMode.MAIN_ORDERED)
    public void callSessionEvent(f fVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void micMuteEvent(k kVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamUnstableEvent(bh bhVar) {
    }
}
