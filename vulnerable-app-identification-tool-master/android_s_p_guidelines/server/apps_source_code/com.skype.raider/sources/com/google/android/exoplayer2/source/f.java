package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.d.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;

final class f implements d, a {
    public final d[] a;
    private final IdentityHashMap<g, Integer> b = new IdentityHashMap();
    private a c;
    private int d;
    private k e;
    private d[] f;
    private h g;

    public f(d... periods) {
        this.a = periods;
    }

    public final void a(a callback) {
        this.c = callback;
        this.d = this.a.length;
        for (d a : this.a) {
            a.a((a) this);
        }
    }

    public final void d() throws IOException {
        for (d d : this.a) {
            d.d();
        }
    }

    public final k e() {
        return this.e;
    }

    public final long a(com.google.android.exoplayer2.c.f[] selections, boolean[] mayRetainStreamFlags, g[] streams, boolean[] streamResetFlags, long positionUs) {
        int i;
        int j;
        int[] streamChildIndices = new int[selections.length];
        int[] selectionChildIndices = new int[selections.length];
        for (i = 0; i < selections.length; i++) {
            int i2;
            if (streams[i] == null) {
                i2 = -1;
            } else {
                i2 = ((Integer) this.b.get(streams[i])).intValue();
            }
            streamChildIndices[i] = i2;
            selectionChildIndices[i] = -1;
            if (selections[i] != null) {
                j trackGroup = selections[i].a();
                for (j = 0; j < this.a.length; j++) {
                    if (this.a[j].e().a(trackGroup) != -1) {
                        selectionChildIndices[i] = j;
                        break;
                    }
                }
            }
        }
        this.b.clear();
        g[] newStreams = new g[selections.length];
        g[] childStreams = new g[selections.length];
        com.google.android.exoplayer2.c.f[] childSelections = new com.google.android.exoplayer2.c.f[selections.length];
        ArrayList<d> enabledPeriodsList = new ArrayList(this.a.length);
        i = 0;
        while (i < this.a.length) {
            j = 0;
            while (j < selections.length) {
                childStreams[j] = streamChildIndices[j] == i ? streams[j] : null;
                childSelections[j] = selectionChildIndices[j] == i ? selections[j] : null;
                j++;
            }
            long selectPositionUs = this.a[i].a(childSelections, mayRetainStreamFlags, childStreams, streamResetFlags, positionUs);
            if (i == 0) {
                positionUs = selectPositionUs;
            } else if (selectPositionUs != positionUs) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean periodEnabled = false;
            for (j = 0; j < selections.length; j++) {
                if (selectionChildIndices[j] == i) {
                    com.google.android.exoplayer2.d.a.b(childStreams[j] != null);
                    newStreams[j] = childStreams[j];
                    periodEnabled = true;
                    this.b.put(childStreams[j], Integer.valueOf(i));
                } else if (streamChildIndices[j] == i) {
                    com.google.android.exoplayer2.d.a.b(childStreams[j] == null);
                }
            }
            if (periodEnabled) {
                enabledPeriodsList.add(this.a[i]);
            }
            i++;
        }
        System.arraycopy(newStreams, 0, streams, 0, newStreams.length);
        this.f = new d[enabledPeriodsList.size()];
        enabledPeriodsList.toArray(this.f);
        this.g = new a(this.f);
        return positionUs;
    }

    public final boolean a(long positionUs) {
        return this.g.a(positionUs);
    }

    public final long a() {
        return this.g.a();
    }

    public final long f() {
        long positionUs = this.a[0].f();
        for (int i = 1; i < this.a.length; i++) {
            if (this.a[i].f() != -9223372036854775807L) {
                throw new IllegalStateException("Child reported discontinuity");
            }
        }
        if (positionUs != -9223372036854775807L) {
            d[] dVarArr = this.f;
            int length = dVarArr.length;
            int i2 = 0;
            while (i2 < length) {
                d enabledPeriod = dVarArr[i2];
                if (enabledPeriod == this.a[0] || enabledPeriod.b(positionUs) == positionUs) {
                    i2++;
                } else {
                    throw new IllegalStateException("Children seeked to different positions");
                }
            }
        }
        return positionUs;
    }

    public final long g() {
        long bufferedPositionUs = Long.MAX_VALUE;
        for (d g : this.f) {
            long rendererBufferedPositionUs = g.g();
            if (rendererBufferedPositionUs != Long.MIN_VALUE) {
                bufferedPositionUs = Math.min(bufferedPositionUs, rendererBufferedPositionUs);
            }
        }
        return bufferedPositionUs == Long.MAX_VALUE ? Long.MIN_VALUE : bufferedPositionUs;
    }

    public final long b(long positionUs) {
        positionUs = this.f[0].b(positionUs);
        for (int i = 1; i < this.f.length; i++) {
            if (this.f[i].b(positionUs) != positionUs) {
                throw new IllegalStateException("Children seeked to different positions");
            }
        }
        return positionUs;
    }

    public final void a(d ignored) {
        int i = 0;
        int i2 = this.d - 1;
        this.d = i2;
        if (i2 <= 0) {
            int totalTrackGroupCount = 0;
            for (d period : this.a) {
                totalTrackGroupCount += period.e().b;
            }
            j[] trackGroupArray = new j[totalTrackGroupCount];
            int trackGroupIndex = 0;
            d[] dVarArr = this.a;
            int length = dVarArr.length;
            while (i < length) {
                k periodTrackGroups = dVarArr[i].e();
                int periodTrackGroupCount = periodTrackGroups.b;
                int j = 0;
                int trackGroupIndex2 = trackGroupIndex;
                while (j < periodTrackGroupCount) {
                    trackGroupIndex = trackGroupIndex2 + 1;
                    trackGroupArray[trackGroupIndex2] = periodTrackGroups.a(j);
                    j++;
                    trackGroupIndex2 = trackGroupIndex;
                }
                i++;
                trackGroupIndex = trackGroupIndex2;
            }
            this.e = new k(trackGroupArray);
            this.c.a(this);
        }
    }
}
