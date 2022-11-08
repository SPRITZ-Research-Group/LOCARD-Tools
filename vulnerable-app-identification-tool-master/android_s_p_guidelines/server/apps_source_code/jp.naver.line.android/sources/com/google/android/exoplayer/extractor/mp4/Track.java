package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.Util;

public final class Track {
    public static final int TYPE_AUDIO = Util.getIntegerCodeForString("soun");
    public static final int TYPE_SUBTITLE = Util.getIntegerCodeForString("sbtl");
    public static final int TYPE_TEXT = Util.getIntegerCodeForString("text");
    public static final int TYPE_VIDEO = Util.getIntegerCodeForString("vide");
    public final long durationUs;
    public final int id;
    public final MediaFormat mediaFormat;
    public final int nalUnitLengthFieldLength;
    public final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final long timescale;
    public final int type;

    public Track(int i, int i2, long j, long j2, MediaFormat mediaFormat, TrackEncryptionBox[] trackEncryptionBoxArr, int i3) {
        this.id = i;
        this.type = i2;
        this.timescale = j;
        this.durationUs = j2;
        this.mediaFormat = mediaFormat;
        this.sampleDescriptionEncryptionBoxes = trackEncryptionBoxArr;
        this.nalUnitLengthFieldLength = i3;
    }
}
