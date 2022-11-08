package com.google.android.exoplayer;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import com.google.android.exoplayer.util.TraceUtil;
import defpackage.kru;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

final class ExoPlayerImplInternal implements Callback {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_CUSTOM = 9;
    private static final int MSG_DO_SOME_WORK = 7;
    public static final int MSG_ERROR = 4;
    private static final int MSG_INCREMENTAL_PREPARE = 2;
    private static final int MSG_PREPARE = 1;
    public static final int MSG_PREPARED = 1;
    private static final int MSG_RELEASE = 5;
    private static final int MSG_SEEK_TO = 6;
    private static final int MSG_SET_PLAY_WHEN_READY = 3;
    public static final int MSG_SET_PLAY_WHEN_READY_ACK = 3;
    private static final int MSG_SET_RENDERER_ENABLED = 8;
    public static final int MSG_STATE_CHANGED = 2;
    private static final int MSG_STOP = 4;
    private static final int PREPARE_INTERVAL_MS = 10;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private volatile long bufferedPositionUs;
    private int customMessagesProcessed = 0;
    private int customMessagesSent = 0;
    private volatile long durationUs;
    private long elapsedRealtimeUs;
    private final List<TrackRenderer> enabledRenderers;
    private final Handler eventHandler;
    private final Handler handler;
    private final HandlerThread internalPlaybackThread;
    private long minBufferUs;
    private final long minRebufferUs;
    private boolean playWhenReady;
    private volatile long positionUs;
    private boolean rebuffering;
    private boolean released;
    private final boolean[] rendererEnabledFlags;
    private MediaClock rendererMediaClock;
    private TrackRenderer rendererMediaClockSource;
    private TrackRenderer[] renderers;
    private final StandaloneMediaClock standaloneMediaClock;
    private int state;

    public ExoPlayerImplInternal(Handler handler, boolean z, boolean[] zArr, int i, int i2) {
        int i3 = 0;
        this.eventHandler = handler;
        this.playWhenReady = z;
        this.rendererEnabledFlags = new boolean[zArr.length];
        this.minBufferUs = ((long) i) * 1000;
        this.minRebufferUs = ((long) i2) * 1000;
        while (i3 < zArr.length) {
            this.rendererEnabledFlags[i3] = zArr[i3];
            i3++;
        }
        this.state = 1;
        this.durationUs = -1;
        this.bufferedPositionUs = -1;
        this.standaloneMediaClock = new StandaloneMediaClock();
        this.enabledRenderers = new ArrayList(zArr.length);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(":Handler");
        this.internalPlaybackThread = new PriorityHandlerThread(stringBuilder.toString(), -16);
        this.internalPlaybackThread.start();
        this.handler = new Handler(this.internalPlaybackThread.getLooper(), this);
    }

    public final Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public final long getCurrentPosition() {
        return this.positionUs / 1000;
    }

    public final long getBufferedPosition() {
        return this.bufferedPositionUs == -1 ? -1 : this.bufferedPositionUs / 1000;
    }

    public final long getDuration() {
        return this.durationUs == -1 ? -1 : this.durationUs / 1000;
    }

    public final void prepare(TrackRenderer... trackRendererArr) {
        this.handler.obtainMessage(1, trackRendererArr).sendToTarget();
    }

    public final void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(3, z, 0).sendToTarget();
    }

    public final void seekTo(long j) {
        this.handler.obtainMessage(6, Long.valueOf(j)).sendToTarget();
    }

    public final void stop() {
        this.handler.sendEmptyMessage(4);
    }

    public final void setRendererEnabled(int i, boolean z) {
        this.handler.obtainMessage(8, i, z).sendToTarget();
    }

    public final void sendMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
        this.customMessagesSent++;
        this.handler.obtainMessage(9, i, 0, Pair.create(exoPlayerComponent, obj)).sendToTarget();
    }

    public final synchronized void blockingSendMessage(com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent r5, int r6, java.lang.Object r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.ExoPlayerImplInternal.blockingSendMessage(com.google.android.exoplayer.ExoPlayer$ExoPlayerComponent, int, java.lang.Object):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.released;	 Catch:{ all -> 0x0047 }
        if (r0 == 0) goto L_0x001f;	 Catch:{ all -> 0x0047 }
    L_0x0005:
        r5 = "ExoPlayerImplInternal";	 Catch:{ all -> 0x0047 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0047 }
        r0 = "Sent message(";	 Catch:{ all -> 0x0047 }
        r7.<init>(r0);	 Catch:{ all -> 0x0047 }
        r7.append(r6);	 Catch:{ all -> 0x0047 }
        r6 = ") after release. Message ignored.";	 Catch:{ all -> 0x0047 }
        r7.append(r6);	 Catch:{ all -> 0x0047 }
        r6 = r7.toString();	 Catch:{ all -> 0x0047 }
        android.util.Log.w(r5, r6);	 Catch:{ all -> 0x0047 }
        monitor-exit(r4);
        return;
    L_0x001f:
        r0 = r4.customMessagesSent;	 Catch:{ all -> 0x0047 }
        r1 = r0 + 1;	 Catch:{ all -> 0x0047 }
        r4.customMessagesSent = r1;	 Catch:{ all -> 0x0047 }
        r1 = r4.handler;	 Catch:{ all -> 0x0047 }
        r2 = 9;	 Catch:{ all -> 0x0047 }
        r3 = 0;	 Catch:{ all -> 0x0047 }
        r5 = android.util.Pair.create(r5, r7);	 Catch:{ all -> 0x0047 }
        r5 = r1.obtainMessage(r2, r6, r3, r5);	 Catch:{ all -> 0x0047 }
        r5.sendToTarget();	 Catch:{ all -> 0x0047 }
    L_0x0035:
        r5 = r4.customMessagesProcessed;	 Catch:{ all -> 0x0047 }
        if (r5 > r0) goto L_0x0045;
    L_0x0039:
        r4.wait();	 Catch:{ InterruptedException -> 0x003d }
        goto L_0x0035;
    L_0x003d:
        r5 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0047 }
        r5.interrupt();	 Catch:{ all -> 0x0047 }
        goto L_0x0035;
    L_0x0045:
        monitor-exit(r4);
        return;
    L_0x0047:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.ExoPlayerImplInternal.blockingSendMessage(com.google.android.exoplayer.ExoPlayer$ExoPlayerComponent, int, java.lang.Object):void");
    }

    public final synchronized void release() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.ExoPlayerImplInternal.release():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.released;	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);
        return;
    L_0x0007:
        r0 = r2.handler;	 Catch:{ all -> 0x0024 }
        r1 = 5;	 Catch:{ all -> 0x0024 }
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0024 }
    L_0x000d:
        r0 = r2.released;	 Catch:{ all -> 0x0024 }
        if (r0 != 0) goto L_0x001d;
    L_0x0011:
        r2.wait();	 Catch:{ InterruptedException -> 0x0015 }
        goto L_0x000d;
    L_0x0015:
        r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0024 }
        r0.interrupt();	 Catch:{ all -> 0x0024 }
        goto L_0x000d;	 Catch:{ all -> 0x0024 }
    L_0x001d:
        r0 = r2.internalPlaybackThread;	 Catch:{ all -> 0x0024 }
        r0.quit();	 Catch:{ all -> 0x0024 }
        monitor-exit(r2);
        return;
    L_0x0024:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.ExoPlayerImplInternal.release():void");
    }

    public final boolean handleMessage(Message message) {
        try {
            boolean z = false;
            switch (message.what) {
                case 1:
                    prepareInternal((TrackRenderer[]) message.obj);
                    return true;
                case 2:
                    incrementalPrepareInternal();
                    return true;
                case 3:
                    if (message.arg1 != 0) {
                        z = true;
                    }
                    setPlayWhenReadyInternal(z);
                    return true;
                case 4:
                    stopInternal();
                    return true;
                case 5:
                    releaseInternal();
                    return true;
                case 6:
                    seekToInternal(((Long) message.obj).longValue());
                    return true;
                case 7:
                    doSomeWork();
                    return true;
                case 8:
                    int i = message.arg1;
                    if (message.arg2 != 0) {
                        z = true;
                    }
                    setRendererEnabledInternal(i, z);
                    return true;
                case 9:
                    sendMessageInternal(message.arg1, message.obj);
                    return true;
                default:
                    return false;
            }
        } catch (Throwable e) {
            Log.e(TAG, "Internal track renderer error.", e);
            this.eventHandler.obtainMessage(4, e).sendToTarget();
            stopInternal();
            return true;
        } catch (Throwable e2) {
            Log.e(TAG, "Internal runtime error.", e2);
            this.eventHandler.obtainMessage(4, new ExoPlaybackException(e2, true)).sendToTarget();
            stopInternal();
            return true;
        }
    }

    private void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.eventHandler.obtainMessage(2, i, 0).sendToTarget();
        }
    }

    private void prepareInternal(TrackRenderer[] trackRendererArr) throws ExoPlaybackException {
        resetInternal();
        this.renderers = trackRendererArr;
        for (int i = 0; i < trackRendererArr.length; i++) {
            MediaClock mediaClock = trackRendererArr[i].getMediaClock();
            if (mediaClock != null) {
                Assertions.checkState(this.rendererMediaClock == null);
                this.rendererMediaClock = mediaClock;
                this.rendererMediaClockSource = trackRendererArr[i];
            }
        }
        setState(2);
        incrementalPrepareInternal();
    }

    private void incrementalPrepareInternal() throws ExoPlaybackException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = 1;
        for (TrackRenderer trackRenderer : this.renderers) {
            if (trackRenderer.getState() == 0 && trackRenderer.prepare(this.positionUs) == 0) {
                trackRenderer.maybeThrowError();
                obj = null;
            }
        }
        if (obj == null) {
            scheduleNextOperation(2, elapsedRealtime, 10);
            return;
        }
        Object obj2 = new boolean[this.renderers.length];
        long j = 0;
        Object obj3 = 1;
        Object obj4 = 1;
        for (int i = 0; i < this.renderers.length; i++) {
            TrackRenderer trackRenderer2 = this.renderers[i];
            obj2[i] = trackRenderer2.getState() == 1;
            if (obj2[i]) {
                if (j != -1) {
                    long durationUs = trackRenderer2.getDurationUs();
                    if (durationUs == -1) {
                        j = -1;
                    } else if (durationUs != -2) {
                        j = Math.max(j, durationUs);
                    }
                }
                if (this.rendererEnabledFlags[i]) {
                    trackRenderer2.enable(this.positionUs, false);
                    this.enabledRenderers.add(trackRenderer2);
                    obj3 = (obj3 == null || !trackRenderer2.isEnded()) ? null : 1;
                    obj4 = (obj4 == null || !rendererReadyOrEnded(trackRenderer2)) ? null : 1;
                }
            }
        }
        this.durationUs = j;
        int i2 = (obj3 == null || (j != -1 && j > this.positionUs)) ? obj4 != null ? 4 : 3 : 5;
        this.state = i2;
        this.eventHandler.obtainMessage(1, this.state, 0, obj2).sendToTarget();
        if (this.playWhenReady && this.state == 4) {
            startRenderers();
        }
        this.handler.sendEmptyMessage(7);
    }

    private boolean rendererReadyOrEnded(TrackRenderer trackRenderer) {
        if (trackRenderer.isEnded()) {
            return true;
        }
        if (!trackRenderer.isReady()) {
            return false;
        }
        if (this.state == 4) {
            return true;
        }
        long durationUs = trackRenderer.getDurationUs();
        long bufferedPositionUs = trackRenderer.getBufferedPositionUs();
        long toMicros = TimeUnit.MILLISECONDS.toMicros((long) kru.a);
        if (toMicros == this.minBufferUs) {
            toMicros = this.minBufferUs;
        }
        this.minBufferUs = toMicros;
        toMicros = this.rebuffering ? this.minRebufferUs : this.minBufferUs;
        if (toMicros <= 0 || bufferedPositionUs == -1 || bufferedPositionUs == -3 || bufferedPositionUs >= this.positionUs + toMicros || (durationUs != -1 && durationUs != -2 && bufferedPositionUs >= durationUs)) {
            return true;
        }
        return false;
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        try {
            this.rebuffering = false;
            this.playWhenReady = z;
            if (!z) {
                stopRenderers();
                updatePositionUs();
            } else if (this.state == 4) {
                startRenderers();
                this.handler.sendEmptyMessage(7);
            } else if (this.state == 3) {
                this.handler.sendEmptyMessage(7);
            }
            this.eventHandler.obtainMessage(3).sendToTarget();
        } catch (Throwable th) {
            this.eventHandler.obtainMessage(3).sendToTarget();
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        int i = 0;
        this.rebuffering = false;
        this.standaloneMediaClock.start();
        while (i < this.enabledRenderers.size()) {
            ((TrackRenderer) this.enabledRenderers.get(i)).start();
            i++;
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.standaloneMediaClock.stop();
        for (int i = 0; i < this.enabledRenderers.size(); i++) {
            ensureStopped((TrackRenderer) this.enabledRenderers.get(i));
        }
    }

    private void updatePositionUs() {
        if (this.rendererMediaClock == null || !this.enabledRenderers.contains(this.rendererMediaClockSource) || this.rendererMediaClockSource.isEnded()) {
            this.positionUs = this.standaloneMediaClock.getPositionUs();
        } else {
            this.positionUs = this.rendererMediaClock.getPositionUs();
            this.standaloneMediaClock.setPositionUs(this.positionUs);
        }
        this.elapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    private void doSomeWork() throws ExoPlaybackException {
        TraceUtil.beginSection("doSomeWork");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.durationUs != -1 ? r6.durationUs : Long.MAX_VALUE;
        updatePositionUs();
        long j2 = j;
        Object obj = 1;
        Object obj2 = 1;
        for (int i = 0; i < r6.enabledRenderers.size(); i++) {
            TrackRenderer trackRenderer = (TrackRenderer) r6.enabledRenderers.get(i);
            trackRenderer.doSomeWork(r6.positionUs, r6.elapsedRealtimeUs);
            obj = (obj == null || !trackRenderer.isEnded()) ? null : 1;
            boolean rendererReadyOrEnded = rendererReadyOrEnded(trackRenderer);
            if (!rendererReadyOrEnded) {
                trackRenderer.maybeThrowError();
            }
            obj2 = (obj2 == null || !rendererReadyOrEnded) ? null : 1;
            if (j2 != -1) {
                long durationUs = trackRenderer.getDurationUs();
                long bufferedPositionUs = trackRenderer.getBufferedPositionUs();
                if (bufferedPositionUs == -1) {
                    j2 = -1;
                } else if (bufferedPositionUs != -3 && (durationUs == -1 || durationUs == -2 || bufferedPositionUs < durationUs)) {
                    j2 = Math.min(j2, bufferedPositionUs);
                }
            }
        }
        r6.bufferedPositionUs = j2;
        if (obj != null && (r6.durationUs == -1 || r6.durationUs <= r6.positionUs)) {
            setState(5);
            stopRenderers();
        } else if (r6.state == 3 && obj2 != null) {
            setState(4);
            if (r6.playWhenReady) {
                startRenderers();
            }
        } else if (r6.state == 4 && obj2 == null) {
            r6.rebuffering = r6.playWhenReady;
            setState(3);
            stopRenderers();
        }
        r6.handler.removeMessages(7);
        if ((r6.playWhenReady && r6.state == 4) || r6.state == 3) {
            scheduleNextOperation(7, elapsedRealtime, 10);
        } else if (!r6.enabledRenderers.isEmpty()) {
            scheduleNextOperation(7, elapsedRealtime, 1000);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextOperation(int i, long j, long j2) {
        j = (j + j2) - SystemClock.elapsedRealtime();
        if (j <= 0) {
            this.handler.sendEmptyMessage(i);
        } else {
            this.handler.sendEmptyMessageDelayed(i, j);
        }
    }

    private void seekToInternal(long j) throws ExoPlaybackException {
        int i = 0;
        this.rebuffering = false;
        this.positionUs = j * 1000;
        this.standaloneMediaClock.stop();
        this.standaloneMediaClock.setPositionUs(this.positionUs);
        if (this.state != 1 && this.state != 2) {
            while (i < this.enabledRenderers.size()) {
                TrackRenderer trackRenderer = (TrackRenderer) this.enabledRenderers.get(i);
                ensureStopped(trackRenderer);
                trackRenderer.seekTo(this.positionUs);
                i++;
            }
            setState(3);
            this.handler.sendEmptyMessage(7);
        }
    }

    private void stopInternal() {
        resetInternal();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal();
        setState(1);
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal() {
        this.handler.removeMessages(7);
        this.handler.removeMessages(2);
        int i = 0;
        this.rebuffering = false;
        this.standaloneMediaClock.stop();
        if (this.renderers != null) {
            while (i < this.renderers.length) {
                TrackRenderer trackRenderer = this.renderers[i];
                stopAndDisable(trackRenderer);
                release(trackRenderer);
                i++;
            }
            this.renderers = null;
            this.rendererMediaClock = null;
            this.rendererMediaClockSource = null;
            this.enabledRenderers.clear();
        }
    }

    private void stopAndDisable(TrackRenderer trackRenderer) {
        try {
            ensureStopped(trackRenderer);
            if (trackRenderer.getState() == 2) {
                trackRenderer.disable();
            }
        } catch (Throwable e) {
            Log.e(TAG, "Stop failed.", e);
        } catch (Throwable e2) {
            Log.e(TAG, "Stop failed.", e2);
        }
    }

    private void release(TrackRenderer trackRenderer) {
        try {
            trackRenderer.release();
        } catch (Throwable e) {
            Log.e(TAG, "Release failed.", e);
        } catch (Throwable e2) {
            Log.e(TAG, "Release failed.", e2);
        }
    }

    private <T> void sendMessageInternal(int i, Object obj) throws ExoPlaybackException {
        try {
            Pair pair = (Pair) obj;
            ((ExoPlayerComponent) pair.first).handleMessage(i, pair.second);
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
            }
            if (this.state != 1 && this.state != 2) {
                this.handler.sendEmptyMessage(7);
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
            }
        }
    }

    private void setRendererEnabledInternal(int i, boolean z) throws ExoPlaybackException {
        if (this.rendererEnabledFlags[i] != z) {
            this.rendererEnabledFlags[i] = z;
            boolean z2 = true;
            if (this.state != 1 && this.state != 2) {
                TrackRenderer trackRenderer = this.renderers[i];
                int state = trackRenderer.getState();
                if (state != 1 && state != 2 && state != 3) {
                    return;
                }
                if (z) {
                    if (!(this.playWhenReady && this.state == 4)) {
                        z2 = false;
                    }
                    trackRenderer.enable(this.positionUs, z2);
                    this.enabledRenderers.add(trackRenderer);
                    if (z2) {
                        trackRenderer.start();
                    }
                    this.handler.sendEmptyMessage(7);
                    return;
                }
                if (trackRenderer == this.rendererMediaClockSource) {
                    this.standaloneMediaClock.setPositionUs(this.rendererMediaClock.getPositionUs());
                }
                ensureStopped(trackRenderer);
                this.enabledRenderers.remove(trackRenderer);
                trackRenderer.disable();
            }
        }
    }

    private void ensureStopped(TrackRenderer trackRenderer) throws ExoPlaybackException {
        if (trackRenderer.getState() == 3) {
            trackRenderer.stop();
        }
    }
}
