package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class AdaptationSet {
    public static final int TYPE_AUDIO = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_VIDEO = 0;
    public final List<ContentProtection> contentProtections;
    public final int id;
    public final List<Representation> representations;
    public final int type;

    public AdaptationSet(int i, int i2, List<Representation> list, List<ContentProtection> list2) {
        this.id = i;
        this.type = i2;
        this.representations = Collections.unmodifiableList(list);
        if (list2 == null) {
            this.contentProtections = Collections.emptyList();
        } else {
            this.contentProtections = Collections.unmodifiableList(list2);
        }
    }

    public AdaptationSet(int i, int i2, List<Representation> list) {
        this(i, i2, list, null);
    }

    public boolean hasContentProtection() {
        return !this.contentProtections.isEmpty();
    }
}
