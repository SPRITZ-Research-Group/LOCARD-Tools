package com.linecorp.andromeda;

import addon.greenrobot.eventbus.ThreadMode;
import addon.greenrobot.eventbus.p;
import com.linecorp.andromeda.VideoControl.Personal.FirstFrameEvent;
import com.linecorp.andromeda.VideoControl.Personal.PauseEvent;
import com.linecorp.andromeda.VideoControl.Personal.StreamChangeEvent;
import com.linecorp.andromeda.VideoControl.Personal.StreamInfoEvent;
import com.linecorp.andromeda.audio.c;
import com.linecorp.andromeda.audio.f;
import com.linecorp.andromeda.core.session.constant.MediaType;

public abstract class ay {
    @p(a = ThreadMode.MAIN)
    public void audioRouteEvent(c cVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void bandwidthEvent(f fVar) {
    }

    @p(a = ThreadMode.MAIN_ORDERED)
    public void callSessionEvent(f fVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void firstFrameEvent(FirstFrameEvent firstFrameEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void mediaTypeEvent(MediaType mediaType) {
    }

    @p(a = ThreadMode.MAIN)
    public void messageEvent(az azVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void micMuteEvent(k kVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void pauseEvent(PauseEvent pauseEvent) {
    }

    @p(a = ThreadMode.BACKGROUND)
    public void pcmEvent(l lVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamInfoEvent(StreamInfoEvent streamInfoEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamSourceEvent(StreamChangeEvent streamChangeEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamUnstableEvent(bb bbVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void videoSessionEvent(br brVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void videoSourceEvent(bt btVar) {
    }
}
