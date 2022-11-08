package com.skype.android.jipc.omx.api.k;

import com.skype.android.jipc.Transactor.What;

public enum OmxTransactionId implements What {
    CONNECT,
    LIVES_LOCALLY,
    LIST_NODES,
    ALLOCATE_NODE,
    FREE_NODE,
    SEND_COMMAND,
    GET_PARAMETER,
    SET_PARAMETER,
    GET_CONFIG,
    SET_CONFIG,
    GET_STATE,
    ENABLE_GRAPHIC_BUFFERS,
    USE_BUFFER,
    USE_GRAPHIC_BUFFER,
    CREATE_INPUT_SURFACE,
    SIGNAL_END_OF_INPUT_STREAM,
    STORE_META_DATA_IN_BUFFERS,
    PREPARE_FOR_ADAPTIVE_PLAYBACK,
    ALLOC_BUFFER,
    ALLOC_BUFFER_WITH_BACKUP,
    FREE_BUFFER,
    FILL_BUFFER,
    EMPTY_BUFFER,
    GET_EXTENSION_INDEX,
    OBSERVER_ON_MSG,
    GET_GRAPHIC_BUFFER_USAGE,
    SET_INTERNAL_OPTION,
    UPDATE_GRAPHIC_BUFFER_IN_META;

    public final int a() {
        return ordinal() + 1;
    }
}
