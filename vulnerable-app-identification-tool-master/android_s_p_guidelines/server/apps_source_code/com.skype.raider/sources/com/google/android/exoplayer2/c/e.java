package com.google.android.exoplayer2.c;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.n;
import com.google.android.exoplayer2.o;
import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.source.k;
import java.util.Arrays;
import java.util.Map;

public abstract class e extends h {
    private final SparseArray<Map<k, b>> a = new SparseArray();
    private final SparseBooleanArray b = new SparseBooleanArray();
    private int c = 0;
    private a d;

    public static final class a {
        public final int a;
        private final int[] b;
        private final k[] c;
        private final int[] d;
        private final int[][][] e;
        private final k f;

        a(int[] rendererTrackTypes, k[] trackGroups, int[] mixedMimeTypeAdaptiveSupport, int[][][] formatSupport, k unassociatedTrackGroups) {
            this.b = rendererTrackTypes;
            this.c = trackGroups;
            this.e = formatSupport;
            this.d = mixedMimeTypeAdaptiveSupport;
            this.f = unassociatedTrackGroups;
            this.a = trackGroups.length;
        }
    }

    public static final class b {
        public final com.google.android.exoplayer2.c.f.a a;
        public final int b;
        public final int[] c;
    }

    protected abstract f[] a(n[] nVarArr, k[] kVarArr, int[][][] iArr) throws ExoPlaybackException;

    public final i a(n[] rendererCapabilities, k trackGroups) throws ExoPlaybackException {
        int i;
        int i2;
        int length;
        int i3;
        int i4;
        int rendererTrackGroupCount;
        int[] rendererTrackGroupCounts = new int[(rendererCapabilities.length + 1)];
        j[][] rendererTrackGroups = new j[(rendererCapabilities.length + 1)][];
        int[][][] rendererFormatSupports = new int[(rendererCapabilities.length + 1)][][];
        for (i = 0; i < rendererTrackGroups.length; i++) {
            rendererTrackGroups[i] = new j[trackGroups.b];
            rendererFormatSupports[i] = new int[trackGroups.b][];
        }
        int[] mixedMimeTypeAdaptationSupport = new int[rendererCapabilities.length];
        for (i2 = 0; i2 < mixedMimeTypeAdaptationSupport.length; i2++) {
            mixedMimeTypeAdaptationSupport[i2] = rendererCapabilities[i2].m();
        }
        for (int groupIndex = 0; groupIndex < trackGroups.b; groupIndex++) {
            int rendererIndex;
            int[] rendererFormatSupport;
            j group = trackGroups.a(groupIndex);
            length = rendererCapabilities.length;
            i3 = 0;
            for (i2 = 0; i2 < rendererCapabilities.length; i2++) {
                n nVar = rendererCapabilities[i2];
                i4 = 0;
                while (i4 < group.a) {
                    int a = nVar.a(group.a(i4)) & 3;
                    if (a > i3) {
                        if (a == 3) {
                            rendererIndex = i2;
                            break;
                        }
                        length = a;
                        a = i2;
                    } else {
                        a = length;
                        length = i3;
                    }
                    i4++;
                    i3 = length;
                    length = a;
                }
            }
            rendererIndex = length;
            if (rendererIndex == rendererCapabilities.length) {
                rendererFormatSupport = new int[group.a];
            } else {
                n nVar2 = rendererCapabilities[rendererIndex];
                rendererFormatSupport = new int[group.a];
                for (i2 = 0; i2 < group.a; i2++) {
                    rendererFormatSupport[i2] = nVar2.a(group.a(i2));
                }
            }
            rendererTrackGroupCount = rendererTrackGroupCounts[rendererIndex];
            rendererTrackGroups[rendererIndex][rendererTrackGroupCount] = group;
            rendererFormatSupports[rendererIndex][rendererTrackGroupCount] = rendererFormatSupport;
            rendererTrackGroupCounts[rendererIndex] = rendererTrackGroupCounts[rendererIndex] + 1;
        }
        k[] rendererTrackGroupArrays = new k[rendererCapabilities.length];
        int[] rendererTrackTypes = new int[rendererCapabilities.length];
        for (i = 0; i < rendererCapabilities.length; i++) {
            rendererTrackGroupCount = rendererTrackGroupCounts[i];
            rendererTrackGroupArrays[i] = new k((j[]) Arrays.copyOf(rendererTrackGroups[i], rendererTrackGroupCount));
            rendererFormatSupports[i] = (int[][]) Arrays.copyOf(rendererFormatSupports[i], rendererTrackGroupCount);
            rendererTrackTypes[i] = rendererCapabilities[i].a();
        }
        k unassociatedTrackGroupArray = new k((j[]) Arrays.copyOf(rendererTrackGroups[rendererCapabilities.length], rendererTrackGroupCounts[rendererCapabilities.length]));
        f[] trackSelections = a(rendererCapabilities, rendererTrackGroupArrays, rendererFormatSupports);
        for (i = 0; i < rendererCapabilities.length; i++) {
            if (this.b.get(i)) {
                trackSelections[i] = null;
            } else {
                k rendererTrackGroup = rendererTrackGroupArrays[i];
                Map<k, b> overrides = (Map) this.a.get(i);
                b override = overrides == null ? null : (b) overrides.get(rendererTrackGroup);
                if (override != null) {
                    trackSelections[i] = override.a.a(rendererTrackGroup.a(override.b), override.c);
                }
            }
        }
        a mappedTrackInfo = new a(rendererTrackTypes, rendererTrackGroupArrays, mixedMimeTypeAdaptationSupport, rendererFormatSupports, unassociatedTrackGroupArray);
        o[] rendererConfigurations = new o[rendererCapabilities.length];
        for (i = 0; i < rendererCapabilities.length; i++) {
            rendererConfigurations[i] = trackSelections[i] != null ? o.a : null;
        }
        int i5 = this.c;
        if (i5 != 0) {
            length = -1;
            i3 = -1;
            for (i2 = 0; i2 < rendererCapabilities.length; i2++) {
                int a2 = rendererCapabilities[i2].a();
                f fVar = trackSelections[i2];
                if ((a2 == 1 || a2 == 2) && fVar != null) {
                    Object obj;
                    int[][] iArr = rendererFormatSupports[i2];
                    k kVar = rendererTrackGroupArrays[i2];
                    if (fVar == null) {
                        obj = null;
                    } else {
                        int a3 = kVar.a(fVar.a());
                        for (i4 = 0; i4 < fVar.b(); i4++) {
                            if ((iArr[a3][fVar.b(i4)] & 16) != 16) {
                                obj = null;
                                break;
                            }
                        }
                        obj = 1;
                    }
                    if (obj == null) {
                        continue;
                    } else if (a2 == 1) {
                        if (length != -1) {
                            i4 = 0;
                            break;
                        }
                        length = i2;
                    } else if (i3 != -1) {
                        i4 = 0;
                        break;
                    } else {
                        i3 = i2;
                    }
                }
            }
            i4 = 1;
            i2 = (length == -1 || i3 == -1) ? 0 : 1;
            if ((i2 & i4) != 0) {
                o oVar = new o(i5);
                rendererConfigurations[length] = oVar;
                rendererConfigurations[i3] = oVar;
            }
        }
        return new i(trackGroups, new g(trackSelections), mappedTrackInfo, rendererConfigurations);
    }

    public final void a(Object info) {
        this.d = (a) info;
    }
}
