package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.l;

public interface ac {

    public final /* synthetic */ class -CC {
        public static void $default$onLoadingChanged(ac acVar, boolean z) {
        }

        public static void $default$onPlaybackParametersChanged(ac acVar, aa aaVar) {
        }

        public static void $default$onPlayerError(ac acVar, h hVar) {
        }

        public static void $default$onPlayerStateChanged(ac acVar, boolean z, int i) {
        }

        public static void $default$onPositionDiscontinuity(ac acVar, int i) {
        }

        public static void $default$onSeekProcessed(ac acVar) {
        }

        public static void $default$onTimelineChanged(ac acVar, ao aoVar, Object obj, int i) {
        }

        public static void $default$onTracksChanged(ac acVar, TrackGroupArray trackGroupArray, l lVar) {
        }
    }

    void onLoadingChanged(boolean z);

    void onPlaybackParametersChanged(aa aaVar);

    void onPlayerError(h hVar);

    void onPlayerStateChanged(boolean z, int i);

    void onPositionDiscontinuity(int i);

    void onSeekProcessed();

    void onTimelineChanged(ao aoVar, Object obj, int i);

    void onTracksChanged(TrackGroupArray trackGroupArray, l lVar);
}
