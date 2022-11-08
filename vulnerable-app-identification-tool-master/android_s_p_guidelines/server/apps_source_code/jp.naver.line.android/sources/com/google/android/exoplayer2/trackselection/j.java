package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import defpackage.axg;
import defpackage.axh;
import java.util.List;

public interface j {

    public final /* synthetic */ class -CC {
        @Deprecated
        public static void $default$a(j -this) {
            throw new UnsupportedOperationException();
        }

        public static void $default$updateSelectedTrack(j -this, long j, long j2, long j3, List list, axh[] axhArr) {
            -this.a();
        }
    }

    @Deprecated
    void a();

    boolean blacklist(int i, long j);

    void disable();

    void enable();

    Format getFormat(int i);

    int getIndexInTrackGroup(int i);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    TrackGroup getTrackGroup();

    int indexOf(int i);

    int length();

    void onPlaybackSpeed(float f);

    void updateSelectedTrack(long j, long j2, long j3, List<? extends axg> list, axh[] axhArr);
}
