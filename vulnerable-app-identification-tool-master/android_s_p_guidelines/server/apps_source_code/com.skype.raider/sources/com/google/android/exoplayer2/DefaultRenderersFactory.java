package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer2.a.d;
import com.google.android.exoplayer2.a.h;
import com.google.android.exoplayer2.drm.b;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.text.i;
import com.google.android.exoplayer2.text.i.a;
import com.google.android.exoplayer2.video.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class DefaultRenderersFactory implements p {
    private final Context a;
    private final b<e> b;
    private final int c;
    private final long d;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    public DefaultRenderersFactory(Context context) {
        this(context, (byte) 0);
    }

    private DefaultRenderersFactory(Context context, byte b) {
        this(context, 0);
    }

    private DefaultRenderersFactory(Context context, char c) {
        this(context, (short) 0);
    }

    private DefaultRenderersFactory(Context context, short s) {
        this.a = context;
        this.b = null;
        this.c = 0;
        this.d = 5000;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final m[] a(Handler eventHandler, com.google.android.exoplayer2.video.e videoRendererEventListener, d audioRendererEventListener, a textRendererOutput, com.google.android.exoplayer2.metadata.e.a metadataRendererOutput) {
        int size;
        int i;
        ArrayList<m> renderersList = new ArrayList();
        Context context = this.a;
        b bVar = this.b;
        long j = this.d;
        int i2 = this.c;
        renderersList.add(new c(context, com.google.android.exoplayer2.b.c.a, j, bVar, eventHandler, videoRendererEventListener));
        if (i2 != 0) {
            size = renderersList.size();
            if (i2 == 2) {
                i = size - 1;
            } else {
                i = size;
            }
            try {
                renderersList.add(i, (m) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{Boolean.TYPE, Long.TYPE, Handler.class, com.google.android.exoplayer2.video.e.class, Integer.TYPE}).newInstance(new Object[]{Boolean.valueOf(true), Long.valueOf(j), eventHandler, videoRendererEventListener, Integer.valueOf(50)}));
            } catch (ClassNotFoundException e) {
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
        Context context2 = this.a;
        b bVar2 = this.b;
        com.google.android.exoplayer2.a.c[] cVarArr = new com.google.android.exoplayer2.a.c[0];
        int i3 = this.c;
        renderersList.add(new h(com.google.android.exoplayer2.b.c.a, bVar2, eventHandler, audioRendererEventListener, com.google.android.exoplayer2.a.b.a(context2), cVarArr));
        if (i3 != 0) {
            int size2 = renderersList.size();
            if (i3 == 2) {
                size2--;
            }
            try {
                i = size2 + 1;
                renderersList.add(size2, (m) Class.forName("com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
                size2 = i;
            } catch (ClassNotFoundException e3) {
                size = size2;
                size2 = size;
                i = size2 + 1;
                renderersList.add(size2, (m) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
                renderersList.add(i, (m) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
                renderersList.add(new i(textRendererOutput, eventHandler.getLooper()));
                renderersList.add(new com.google.android.exoplayer2.metadata.e(metadataRendererOutput, eventHandler.getLooper()));
                return (m[]) renderersList.toArray(new m[renderersList.size()]);
            } catch (Throwable e22) {
                throw new RuntimeException(e22);
            }
            try {
                i = size2 + 1;
                renderersList.add(size2, (m) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
            } catch (ClassNotFoundException e4) {
                size = size2;
                i = size;
                renderersList.add(i, (m) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
                renderersList.add(new i(textRendererOutput, eventHandler.getLooper()));
                renderersList.add(new com.google.android.exoplayer2.metadata.e(metadataRendererOutput, eventHandler.getLooper()));
                return (m[]) renderersList.toArray(new m[renderersList.size()]);
            } catch (Throwable e222) {
                throw new RuntimeException(e222);
            }
            try {
                renderersList.add(i, (m) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(new Class[]{Handler.class, d.class, com.google.android.exoplayer2.a.c[].class}).newInstance(new Object[]{eventHandler, audioRendererEventListener, cVarArr}));
            } catch (ClassNotFoundException e5) {
            } catch (Throwable e2222) {
                throw new RuntimeException(e2222);
            }
        }
        renderersList.add(new i(textRendererOutput, eventHandler.getLooper()));
        renderersList.add(new com.google.android.exoplayer2.metadata.e(metadataRendererOutput, eventHandler.getLooper()));
        return (m[]) renderersList.toArray(new m[renderersList.size()]);
    }
}
