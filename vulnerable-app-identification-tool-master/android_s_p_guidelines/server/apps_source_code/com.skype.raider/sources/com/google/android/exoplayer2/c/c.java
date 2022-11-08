package com.google.android.exoplayer2.c;

import android.graphics.Point;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.n;
import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.source.k;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class c extends e {
    private static final int[] a = new int[0];
    private final com.google.android.exoplayer2.c.f.a b;
    private final AtomicReference<b> c;

    private static final class a {
        public final int a;
        public final int b;
        public final String c;

        public a(int channelCount, int sampleRate, String mimeType) {
            this.a = channelCount;
            this.b = sampleRate;
            this.c = mimeType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a other = (a) obj;
            if (this.a == other.a && this.b == other.b && TextUtils.equals(this.c, other.c)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.c != null ? this.c.hashCode() : 0) + (((this.a * 31) + this.b) * 31);
        }
    }

    public static final class b {
        public final String a;
        public final String b;
        public final boolean c;
        public final boolean d;
        public final int e;
        public final int f;
        public final int g;
        public final boolean h;
        public final boolean i;
        public final int j;
        public final int k;
        public final boolean l;

        public b() {
            this((byte) 0);
        }

        private b(byte b) {
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = true;
            this.e = Integer.MAX_VALUE;
            this.f = Integer.MAX_VALUE;
            this.g = Integer.MAX_VALUE;
            this.h = true;
            this.i = true;
            this.j = Integer.MAX_VALUE;
            this.k = Integer.MAX_VALUE;
            this.l = true;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b other = (b) obj;
            if (this.c == other.c && this.d == other.d && this.e == other.e && this.f == other.f && this.h == other.h && this.i == other.i && this.l == other.l && this.j == other.j && this.k == other.k && this.g == other.g && TextUtils.equals(this.a, other.a) && TextUtils.equals(this.b, other.b)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int i2 = 1;
            int hashCode = ((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + (this.c ? 1 : 0)) * 31;
            if (this.d) {
                i = 1;
            } else {
                i = 0;
            }
            hashCode = (((((((hashCode + i) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31;
            if (this.h) {
                i = 1;
            } else {
                i = 0;
            }
            hashCode = (hashCode + i) * 31;
            if (this.i) {
                i = 1;
            } else {
                i = 0;
            }
            i = (hashCode + i) * 31;
            if (!this.l) {
                i2 = 0;
            }
            return ((((i + i2) * 31) + this.j) * 31) + this.k;
        }
    }

    public c() {
        this(null);
    }

    public c(com.google.android.exoplayer2.c.f.a adaptiveTrackSelectionFactory) {
        this.b = adaptiveTrackSelectionFactory;
        this.c = new AtomicReference(new b());
    }

    protected final f[] a(n[] rendererCapabilities, k[] rendererTrackGroupArrays, int[][][] rendererFormatSupports) throws ExoPlaybackException {
        int i;
        int rendererCount = rendererCapabilities.length;
        f[] rendererTrackSelections = new f[rendererCount];
        b params = (b) this.c.get();
        boolean seenVideoRendererWithMappedTracks = false;
        boolean selectedVideoTracks = false;
        for (i = 0; i < rendererCount; i++) {
            if (2 == rendererCapabilities[i].a()) {
                int i2;
                if (!selectedVideoTracks) {
                    n nVar = rendererCapabilities[i];
                    k kVar = rendererTrackGroupArrays[i];
                    int[][] iArr = rendererFormatSupports[i];
                    int i3 = params.e;
                    int i4 = params.f;
                    int i5 = params.g;
                    boolean z = params.d;
                    boolean z2 = params.c;
                    int i6 = params.j;
                    int i7 = params.k;
                    boolean z3 = params.l;
                    com.google.android.exoplayer2.c.f.a aVar = this.b;
                    boolean z4 = params.h;
                    boolean z5 = params.i;
                    f fVar = null;
                    if (aVar != null) {
                        Object obj;
                        int i8 = z ? 12 : 8;
                        if (!z2 || (nVar.m() & i8) == 0) {
                            obj = null;
                        } else {
                            obj = 1;
                        }
                        i2 = 0;
                        while (true) {
                            int i9 = i2;
                            if (i9 >= kVar.b) {
                                fVar = null;
                                break;
                            }
                            j a = kVar.a(i9);
                            int[] iArr2 = iArr[i9];
                            if (a.a < 2) {
                                iArr2 = a;
                            } else {
                                List a2 = a(a, i6, i7, z3);
                                if (a2.size() < 2) {
                                    iArr2 = a;
                                } else {
                                    String str;
                                    String str2 = null;
                                    if (obj == null) {
                                        HashSet hashSet = new HashSet();
                                        int i10 = 0;
                                        int i11 = 0;
                                        while (true) {
                                            int i12 = i11;
                                            if (i12 >= a2.size()) {
                                                break;
                                            }
                                            String str3;
                                            str = a.a(((Integer) a2.get(i12)).intValue()).f;
                                            if (hashSet.add(str)) {
                                                int a3 = a(a, iArr2, i8, str, i3, i4, i5, a2);
                                                if (a3 > i10) {
                                                    int i13 = a3;
                                                    str3 = str;
                                                    i11 = i13;
                                                    i10 = i12 + 1;
                                                    i12 = i10;
                                                    str2 = str3;
                                                }
                                            }
                                            i11 = i10;
                                            str3 = str2;
                                            i10 = i12 + 1;
                                            i12 = i10;
                                            str2 = str3;
                                        }
                                        str = str2;
                                    } else {
                                        str = null;
                                    }
                                    b(a, iArr2, i8, str, i3, i4, i5, a2);
                                    iArr2 = a2.size() < 2 ? a : s.a(a2);
                                }
                            }
                            if (iArr2.length > 0) {
                                fVar = aVar.a(a, iArr2);
                                break;
                            }
                            i2 = i9 + 1;
                        }
                    }
                    if (fVar == null) {
                        fVar = a(kVar, iArr, i3, i4, i5, i6, i7, z3, z4, z5);
                    }
                    rendererTrackSelections[i] = fVar;
                    selectedVideoTracks = rendererTrackSelections[i] != null;
                }
                if (rendererTrackGroupArrays[i].b > 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                seenVideoRendererWithMappedTracks |= i2;
            }
        }
        boolean selectedAudioTracks = false;
        boolean selectedTextTracks = false;
        for (i = 0; i < rendererCount; i++) {
            switch (rendererCapabilities[i].a()) {
                case 1:
                    if (!selectedAudioTracks) {
                        rendererTrackSelections[i] = a(rendererTrackGroupArrays[i], rendererFormatSupports[i], params.a, params.i, params.c, seenVideoRendererWithMappedTracks ? null : this.b);
                        if (rendererTrackSelections[i] == null) {
                            selectedAudioTracks = false;
                            break;
                        }
                        selectedAudioTracks = true;
                        break;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    if (!selectedTextTracks) {
                        rendererTrackSelections[i] = a(rendererTrackGroupArrays[i], rendererFormatSupports[i], params.b, params.a, params.i);
                        if (rendererTrackSelections[i] == null) {
                            selectedTextTracks = false;
                            break;
                        }
                        selectedTextTracks = true;
                        break;
                    }
                    break;
                default:
                    rendererTrackSelections[i] = a(rendererTrackGroupArrays[i], rendererFormatSupports[i], params.i);
                    break;
            }
        }
        return rendererTrackSelections;
    }

    private static int a(j group, int[] formatSupport, int requiredAdaptiveSupport, String mimeType, int maxVideoWidth, int maxVideoHeight, int maxVideoBitrate, List<Integer> selectedTrackIndices) {
        int adaptiveTrackCount = 0;
        for (int i = 0; i < selectedTrackIndices.size(); i++) {
            int trackIndex = ((Integer) selectedTrackIndices.get(i)).intValue();
            if (a(group.a(trackIndex), mimeType, formatSupport[trackIndex], requiredAdaptiveSupport, maxVideoWidth, maxVideoHeight, maxVideoBitrate)) {
                adaptiveTrackCount++;
            }
        }
        return adaptiveTrackCount;
    }

    private static void b(j group, int[] formatSupport, int requiredAdaptiveSupport, String mimeType, int maxVideoWidth, int maxVideoHeight, int maxVideoBitrate, List<Integer> selectedTrackIndices) {
        for (int i = selectedTrackIndices.size() - 1; i >= 0; i--) {
            int trackIndex = ((Integer) selectedTrackIndices.get(i)).intValue();
            if (!a(group.a(trackIndex), mimeType, formatSupport[trackIndex], requiredAdaptiveSupport, maxVideoWidth, maxVideoHeight, maxVideoBitrate)) {
                selectedTrackIndices.remove(i);
            }
        }
    }

    private static boolean a(Format format, String mimeType, int formatSupport, int requiredAdaptiveSupport, int maxVideoWidth, int maxVideoHeight, int maxVideoBitrate) {
        if (!a(formatSupport, false) || (formatSupport & requiredAdaptiveSupport) == 0) {
            return false;
        }
        if (mimeType != null && !s.a(format.f, (Object) mimeType)) {
            return false;
        }
        if (format.j != -1 && format.j > maxVideoWidth) {
            return false;
        }
        if (format.k != -1 && format.k > maxVideoHeight) {
            return false;
        }
        if (format.b == -1 || format.b <= maxVideoBitrate) {
            return true;
        }
        return false;
    }

    private static f a(k groups, int[][] formatSupport, int maxVideoWidth, int maxVideoHeight, int maxVideoBitrate, int viewportWidth, int viewportHeight, boolean orientationMayChange, boolean exceedConstraintsIfNecessary, boolean exceedRendererCapabilitiesIfNecessary) {
        j selectedGroup = null;
        int selectedTrackIndex = 0;
        int selectedTrackScore = 0;
        int selectedBitrate = -1;
        int selectedPixelCount = -1;
        for (int groupIndex = 0; groupIndex < groups.b; groupIndex++) {
            j trackGroup = groups.a(groupIndex);
            List<Integer> selectedTrackIndices = a(trackGroup, viewportWidth, viewportHeight, orientationMayChange);
            int[] trackFormatSupport = formatSupport[groupIndex];
            for (int trackIndex = 0; trackIndex < trackGroup.a; trackIndex++) {
                if (a(trackFormatSupport[trackIndex], exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup.a(trackIndex);
                    boolean isWithinConstraints = selectedTrackIndices.contains(Integer.valueOf(trackIndex)) && ((format.j == -1 || format.j <= maxVideoWidth) && ((format.k == -1 || format.k <= maxVideoHeight) && (format.b == -1 || format.b <= maxVideoBitrate)));
                    if (isWithinConstraints || exceedConstraintsIfNecessary) {
                        int trackScore = isWithinConstraints ? 2 : 1;
                        boolean isWithinCapabilities = a(trackFormatSupport[trackIndex], false);
                        if (isWithinCapabilities) {
                            trackScore += Constants.ONE_SECOND;
                        }
                        boolean selectTrack = trackScore > selectedTrackScore;
                        if (trackScore == selectedTrackScore) {
                            int comparisonResult;
                            if (format.a() != selectedPixelCount) {
                                comparisonResult = a(format.a(), selectedPixelCount);
                            } else {
                                comparisonResult = a(format.b, selectedBitrate);
                            }
                            selectTrack = (isWithinCapabilities && isWithinConstraints) ? comparisonResult > 0 : comparisonResult < 0;
                        }
                        if (selectTrack) {
                            selectedGroup = trackGroup;
                            selectedTrackIndex = trackIndex;
                            selectedTrackScore = trackScore;
                            selectedBitrate = format.b;
                            selectedPixelCount = format.a();
                        }
                    }
                }
            }
        }
        return selectedGroup == null ? null : new d(selectedGroup, selectedTrackIndex);
    }

    private static int a(int first, int second) {
        return first == -1 ? second == -1 ? 0 : -1 : second == -1 ? 1 : first - second;
    }

    private static f a(k groups, int[][] formatSupport, String preferredAudioLanguage, boolean exceedRendererCapabilitiesIfNecessary, boolean allowMixedMimeAdaptiveness, com.google.android.exoplayer2.c.f.a adaptiveTrackSelectionFactory) {
        int i;
        int i2;
        int selectedGroupIndex = -1;
        int selectedTrackIndex = -1;
        int selectedTrackScore = 0;
        for (int groupIndex = 0; groupIndex < groups.b; groupIndex++) {
            j trackGroup = groups.a(groupIndex);
            int[] trackFormatSupport = formatSupport[groupIndex];
            for (int trackIndex = 0; trackIndex < trackGroup.a; trackIndex++) {
                if (a(trackFormatSupport[trackIndex], exceedRendererCapabilitiesIfNecessary)) {
                    int trackScore;
                    Format format = trackGroup.a(trackIndex);
                    i = trackFormatSupport[trackIndex];
                    Object obj = (format.x & 1) != 0 ? 1 : null;
                    if (a(format, preferredAudioLanguage)) {
                        if (obj != null) {
                            i2 = 4;
                        } else {
                            i2 = 3;
                        }
                    } else if (obj != null) {
                        i2 = 2;
                    } else {
                        i2 = 1;
                    }
                    if (a(i, false)) {
                        trackScore = i2 + Constants.ONE_SECOND;
                    } else {
                        trackScore = i2;
                    }
                    if (trackScore > selectedTrackScore) {
                        selectedGroupIndex = groupIndex;
                        selectedTrackIndex = trackIndex;
                        selectedTrackScore = trackScore;
                    }
                }
            }
        }
        if (selectedGroupIndex == -1) {
            return null;
        }
        j selectedGroup = groups.a(selectedGroupIndex);
        if (adaptiveTrackSelectionFactory != null) {
            int a;
            int[] adaptiveTracks;
            int[] iArr = formatSupport[selectedGroupIndex];
            int i3 = 0;
            a aVar = null;
            HashSet hashSet = new HashSet();
            int i4 = 0;
            while (i4 < selectedGroup.a) {
                Format a2 = selectedGroup.a(i4);
                a aVar2 = new a(a2.r, a2.s, allowMixedMimeAdaptiveness ? null : a2.f);
                if (hashSet.add(aVar2)) {
                    a = a(selectedGroup, iArr, aVar2);
                    if (a > i3) {
                        i = a;
                        i4++;
                        i3 = i;
                        aVar = aVar2;
                    }
                }
                aVar2 = aVar;
                i = i3;
                i4++;
                i3 = i;
                aVar = aVar2;
            }
            if (i3 > 1) {
                adaptiveTracks = new int[i3];
                a = 0;
                for (i2 = 0; i2 < selectedGroup.a; i2++) {
                    if (a(selectedGroup.a(i2), iArr[i2], aVar)) {
                        i3 = a + 1;
                        adaptiveTracks[a] = i2;
                        a = i3;
                    }
                }
            } else {
                adaptiveTracks = a;
            }
            if (adaptiveTracks.length > 0) {
                return adaptiveTrackSelectionFactory.a(selectedGroup, adaptiveTracks);
            }
        }
        return new d(selectedGroup, selectedTrackIndex);
    }

    private static int a(j group, int[] formatSupport, a configuration) {
        int count = 0;
        for (int i = 0; i < group.a; i++) {
            if (a(group.a(i), formatSupport[i], configuration)) {
                count++;
            }
        }
        return count;
    }

    private static boolean a(Format format, int formatSupport, a configuration) {
        if (!a(formatSupport, false) || format.r != configuration.a || format.s != configuration.b) {
            return false;
        }
        if (configuration.c == null || TextUtils.equals(configuration.c, format.f)) {
            return true;
        }
        return false;
    }

    private static f a(k groups, int[][] formatSupport, String preferredTextLanguage, String preferredAudioLanguage, boolean exceedRendererCapabilitiesIfNecessary) {
        j selectedGroup = null;
        int selectedTrackIndex = 0;
        int selectedTrackScore = 0;
        for (int groupIndex = 0; groupIndex < groups.b; groupIndex++) {
            j trackGroup = groups.a(groupIndex);
            int[] trackFormatSupport = formatSupport[groupIndex];
            for (int trackIndex = 0; trackIndex < trackGroup.a; trackIndex++) {
                if (a(trackFormatSupport[trackIndex], exceedRendererCapabilitiesIfNecessary)) {
                    int trackScore;
                    int selectedTrackScore2;
                    Format format = trackGroup.a(trackIndex);
                    boolean isDefault = (format.x & 1) != 0;
                    boolean isForced = (format.x & 2) != 0;
                    if (a(format, preferredTextLanguage)) {
                        if (isDefault) {
                            trackScore = 6;
                        } else if (isForced) {
                            trackScore = 4;
                        } else {
                            trackScore = 5;
                        }
                    } else if (isDefault) {
                        trackScore = 3;
                    } else if (isForced) {
                        if (a(format, preferredAudioLanguage)) {
                            trackScore = 2;
                        } else {
                            trackScore = 1;
                        }
                    }
                    if (a(trackFormatSupport[trackIndex], false)) {
                        selectedTrackScore2 = trackScore + Constants.ONE_SECOND;
                    } else {
                        selectedTrackScore2 = trackScore;
                    }
                    if (selectedTrackScore2 > selectedTrackScore) {
                        selectedGroup = trackGroup;
                        selectedTrackIndex = trackIndex;
                        selectedTrackScore = selectedTrackScore2;
                    }
                }
            }
        }
        return selectedGroup == null ? null : new d(selectedGroup, selectedTrackIndex);
    }

    private static f a(k groups, int[][] formatSupport, boolean exceedRendererCapabilitiesIfNecessary) {
        j selectedGroup = null;
        int selectedTrackIndex = 0;
        int selectedTrackScore = 0;
        for (int groupIndex = 0; groupIndex < groups.b; groupIndex++) {
            j trackGroup = groups.a(groupIndex);
            int[] trackFormatSupport = formatSupport[groupIndex];
            for (int trackIndex = 0; trackIndex < trackGroup.a; trackIndex++) {
                if (a(trackFormatSupport[trackIndex], exceedRendererCapabilitiesIfNecessary)) {
                    boolean z;
                    int trackScore;
                    if ((trackGroup.a(trackIndex).x & 1) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        trackScore = 2;
                    } else {
                        trackScore = 1;
                    }
                    if (a(trackFormatSupport[trackIndex], false)) {
                        trackScore += Constants.ONE_SECOND;
                    }
                    if (trackScore > selectedTrackScore) {
                        selectedGroup = trackGroup;
                        selectedTrackIndex = trackIndex;
                        selectedTrackScore = trackScore;
                    }
                }
            }
        }
        return selectedGroup == null ? null : new d(selectedGroup, selectedTrackIndex);
    }

    private static boolean a(int formatSupport, boolean allowExceedsCapabilities) {
        int maskedSupport = formatSupport & 3;
        return maskedSupport == 3 || (allowExceedsCapabilities && maskedSupport == 2);
    }

    private static boolean a(Format format, String language) {
        return language != null && TextUtils.equals(language, s.b(format.y));
    }

    private static List<Integer> a(j group, int viewportWidth, int viewportHeight, boolean orientationMayChange) {
        int i;
        ArrayList<Integer> selectedTrackIndices = new ArrayList(group.a);
        for (i = 0; i < group.a; i++) {
            selectedTrackIndices.add(Integer.valueOf(i));
        }
        if (!(viewportWidth == Integer.MAX_VALUE || viewportHeight == Integer.MAX_VALUE)) {
            int maxVideoPixelsToRetain = Integer.MAX_VALUE;
            for (i = 0; i < group.a; i++) {
                Format format = group.a(i);
                if (format.j > 0 && format.k > 0) {
                    int i2;
                    int i3;
                    Point maxVideoSizeInViewport;
                    int videoPixels;
                    int i4 = format.j;
                    int i5 = format.k;
                    if (orientationMayChange) {
                        Object obj;
                        Object obj2;
                        if (i4 > i5) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (viewportWidth > viewportHeight) {
                            obj2 = 1;
                        } else {
                            obj2 = null;
                        }
                        if (obj != obj2) {
                            i2 = viewportWidth;
                            i3 = viewportHeight;
                            if (i4 * i2 < i5 * i3) {
                                maxVideoSizeInViewport = new Point(i3, s.a(i3 * i5, i4));
                            } else {
                                maxVideoSizeInViewport = new Point(s.a(i2 * i4, i5), i2);
                            }
                            videoPixels = format.j * format.k;
                            if (format.j >= ((int) (((float) maxVideoSizeInViewport.x) * 0.98f)) && format.k >= ((int) (((float) maxVideoSizeInViewport.y) * 0.98f)) && videoPixels < maxVideoPixelsToRetain) {
                                maxVideoPixelsToRetain = videoPixels;
                            }
                        }
                    }
                    i2 = viewportHeight;
                    i3 = viewportWidth;
                    if (i4 * i2 < i5 * i3) {
                        maxVideoSizeInViewport = new Point(s.a(i2 * i4, i5), i2);
                    } else {
                        maxVideoSizeInViewport = new Point(i3, s.a(i3 * i5, i4));
                    }
                    videoPixels = format.j * format.k;
                    maxVideoPixelsToRetain = videoPixels;
                }
            }
            if (maxVideoPixelsToRetain != Integer.MAX_VALUE) {
                for (i = selectedTrackIndices.size() - 1; i >= 0; i--) {
                    int pixelCount = group.a(((Integer) selectedTrackIndices.get(i)).intValue()).a();
                    if (pixelCount == -1 || pixelCount > maxVideoPixelsToRetain) {
                        selectedTrackIndices.remove(i);
                    }
                }
            }
        }
        return selectedTrackIndices;
    }
}
