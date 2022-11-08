package com.skype.android.jipc.omx.api.n;

import com.skype.android.jipc.Transactor.What;

public enum MediaCodecTransactionId implements What {
    ;

    private MediaCodecTransactionId(String str) {
    }

    public final int a() {
        return ordinal() + 1;
    }
}
