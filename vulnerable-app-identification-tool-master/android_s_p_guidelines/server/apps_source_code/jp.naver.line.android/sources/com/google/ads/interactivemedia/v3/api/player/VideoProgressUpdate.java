package com.google.ads.interactivemedia.v3.api.player;

import com.google.obf.ks;

public final class VideoProgressUpdate {
    public static final VideoProgressUpdate VIDEO_TIME_NOT_READY = new VideoProgressUpdate(-1, -1);
    private float currentTime;
    private float duration;

    public VideoProgressUpdate(long j, long j2) {
        this.currentTime = ((float) j) / 1000.0f;
        this.duration = ((float) j2) / 1000.0f;
    }

    public final float getCurrentTime() {
        return this.currentTime;
    }

    public final float getDuration() {
        return this.duration;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VideoProgressUpdate videoProgressUpdate = (VideoProgressUpdate) obj;
        return Float.floatToIntBits(this.currentTime) == Float.floatToIntBits(videoProgressUpdate.currentTime) && Float.floatToIntBits(this.duration) == Float.floatToIntBits(videoProgressUpdate.duration);
    }

    public final int hashCode() {
        return ks.a(Float.valueOf(this.currentTime), Float.valueOf(this.duration));
    }

    public final String toString() {
        float f = this.currentTime;
        float f2 = this.duration;
        StringBuilder stringBuilder = new StringBuilder(75);
        stringBuilder.append("VideoProgressUpdate [currentTime=");
        stringBuilder.append(f);
        stringBuilder.append(", duration=");
        stringBuilder.append(f2);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
