package com.skype.android.jipc.omx.api.j;

import com.skype.android.jipc.Transactor.What;

public enum MediaPlayerTransactionId implements What {
    CREATE,
    DECODE_URL,
    DECODE_FD,
    CREATE_MEDIA_RECORDER,
    CREATE_METADATA_RETRIEVER,
    GET_OMX,
    MAKE_CRYPTO,
    MAKE_DRM,
    MAKE_HDCP,
    ADD_BATTERY_DATA,
    PULL_BATTERY_DATA,
    LISTEN_FOR_REMOTE_DISPLAY,
    UPDATE_PROXY_CONFIG;

    public final int a() {
        return ordinal() + 1;
    }
}
