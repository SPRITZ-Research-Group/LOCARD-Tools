package com.skype.android.jipc.omx.api.l;

import com.skype.android.jipc.Transactor.What;
import com.skype.android.jipc.omx.CallRouter;

public class CallRouterImpl implements CallRouter {
    public final What a() {
        return MediaPlayerTransactionId.GET_OMX;
    }

    public final What b() {
        return OmxTransactionId.ALLOCATE_NODE;
    }

    public final What c() {
        return OmxTransactionId.FREE_NODE;
    }

    public final What d() {
        return OmxTransactionId.GET_PARAMETER;
    }

    public final What e() {
        return OmxTransactionId.SET_PARAMETER;
    }

    public final What f() {
        return OmxTransactionId.SET_CONFIG;
    }

    public final What g() {
        return OmxTransactionId.GET_EXTENSION_INDEX;
    }

    public final What h() {
        return OmxTransactionId.OBSERVER_ON_MSG;
    }
}
