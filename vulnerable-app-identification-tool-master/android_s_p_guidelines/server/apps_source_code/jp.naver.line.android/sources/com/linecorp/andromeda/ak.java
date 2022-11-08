package com.linecorp.andromeda;

import addon.greenrobot.eventbus.ThreadMode;
import addon.greenrobot.eventbus.p;
import com.linecorp.andromeda.VideoControl.Group.FirstFrameEvent;
import com.linecorp.andromeda.VideoControl.Group.PauseEvent;
import com.linecorp.andromeda.VideoControl.Group.StreamChangeEvent;
import com.linecorp.andromeda.VideoControl.Group.StreamInfoEvent;
import com.linecorp.andromeda.audio.c;
import com.linecorp.andromeda.core.session.constant.AccessNetwork;
import com.linecorp.andromeda.core.session.constant.MediaType;

public abstract class ak {
    @p(a = ThreadMode.MAIN)
    public void accessNetworkEvent(AccessNetwork accessNetwork) {
    }

    @p(a = ThreadMode.MAIN)
    public void audioRouteEvent(c cVar) {
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
    public void micMuteEvent(k kVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void pauseEvent(PauseEvent pauseEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamInfoEvent(StreamInfoEvent streamInfoEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void streamSourceEvent(StreamChangeEvent streamChangeEvent) {
    }

    @p(a = ThreadMode.MAIN)
    public void userEvent(ao aoVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void userStateEvent(ap apVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void userVideoStateEvent(aq aqVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void videoDisconnectEvent(bm bmVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void videoSessionEvent(br brVar) {
    }

    @p(a = ThreadMode.MAIN)
    public void videoSourceEvent(bt btVar) {
    }
}
