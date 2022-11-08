package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.media.MediaCrypto;

@TargetApi(16)
public final class e implements c {
    private final MediaCrypto a;

    public final MediaCrypto a() {
        return this.a;
    }

    public final boolean a(String mimeType) {
        return this.a.requiresSecureDecoderComponent(mimeType);
    }
}
