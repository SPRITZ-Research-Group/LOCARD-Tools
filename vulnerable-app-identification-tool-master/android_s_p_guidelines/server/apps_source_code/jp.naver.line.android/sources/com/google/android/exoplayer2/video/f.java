package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.os.Handler;

@TargetApi(23)
final class f implements OnFrameRenderedListener {
    final /* synthetic */ d a;

    /* synthetic */ f(d dVar, MediaCodec mediaCodec, byte b) {
        this(dVar, mediaCodec);
    }

    private f(d dVar, MediaCodec mediaCodec) {
        this.a = dVar;
        mediaCodec.setOnFrameRenderedListener(this, new Handler());
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        if (this == this.a.b) {
            this.a.e(j);
        }
    }
}
