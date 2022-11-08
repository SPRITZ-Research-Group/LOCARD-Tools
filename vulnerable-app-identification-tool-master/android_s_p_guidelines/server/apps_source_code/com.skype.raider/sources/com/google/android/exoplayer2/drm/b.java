package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.os.Looper;

@TargetApi(16)
public interface b<T extends c> {
    a<T> a(Looper looper, DrmInitData drmInitData);

    void a();
}
