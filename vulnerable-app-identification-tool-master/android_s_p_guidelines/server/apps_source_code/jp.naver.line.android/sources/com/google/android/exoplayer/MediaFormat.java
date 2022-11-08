package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MediaFormat {
    private static final String KEY_PIXEL_WIDTH_HEIGHT_RATIO = "com.google.android.videos.pixelWidthHeightRatio";
    public static final int NO_VALUE = -1;
    public final int channelCount;
    public final long durationUs;
    private android.media.MediaFormat frameworkMediaFormat;
    private int hashCode;
    public final int height;
    public final List<byte[]> initializationData;
    private int maxHeight;
    public final int maxInputSize;
    private int maxWidth;
    public final String mimeType;
    public final float pixelWidthHeightRatio;
    public final int sampleRate;
    public final int width;

    @TargetApi(16)
    public static MediaFormat createFromFrameworkMediaFormatV16(android.media.MediaFormat mediaFormat) {
        return new MediaFormat(mediaFormat);
    }

    public static MediaFormat createVideoFormat(String str, int i, int i2, int i3, List<byte[]> list) {
        return createVideoFormat(str, i, -1, i2, i3, list);
    }

    public static MediaFormat createVideoFormat(String str, int i, long j, int i2, int i3, List<byte[]> list) {
        return createVideoFormat(str, i, j, i2, i3, 1.0f, list);
    }

    public static MediaFormat createVideoFormat(String str, int i, long j, int i2, int i3, float f, List<byte[]> list) {
        return new MediaFormat(str, i, j, i2, i3, f, -1, -1, list);
    }

    public static MediaFormat createAudioFormat(String str, int i, int i2, int i3, List<byte[]> list) {
        return createAudioFormat(str, i, -1, i2, i3, list);
    }

    public static MediaFormat createAudioFormat(String str, int i, long j, int i2, int i3, List<byte[]> list) {
        return new MediaFormat(str, i, j, -1, -1, -1.0f, i2, i3, list);
    }

    public static MediaFormat createTextFormat(String str) {
        return createTextFormat(str, -1);
    }

    public static MediaFormat createTextFormat(String str, long j) {
        return createFormatForMimeType(str, j);
    }

    public static MediaFormat createFormatForMimeType(String str) {
        return createFormatForMimeType(str, -1);
    }

    public static MediaFormat createFormatForMimeType(String str, long j) {
        return new MediaFormat(str, -1, j, -1, -1, -1.0f, -1, -1, null);
    }

    @TargetApi(16)
    private MediaFormat(android.media.MediaFormat mediaFormat) {
        this.frameworkMediaFormat = mediaFormat;
        this.mimeType = mediaFormat.getString("mime");
        this.maxInputSize = getOptionalIntegerV16(mediaFormat, "max-input-size");
        this.width = getOptionalIntegerV16(mediaFormat, "width");
        this.height = getOptionalIntegerV16(mediaFormat, "height");
        this.channelCount = getOptionalIntegerV16(mediaFormat, "channel-count");
        this.sampleRate = getOptionalIntegerV16(mediaFormat, "sample-rate");
        this.pixelWidthHeightRatio = getOptionalFloatV16(mediaFormat, KEY_PIXEL_WIDTH_HEIGHT_RATIO);
        this.initializationData = new ArrayList();
        for (int i = 0; mediaFormat.containsKey("csd-".concat(String.valueOf(i))); i++) {
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-".concat(String.valueOf(i)));
            Object obj = new byte[byteBuffer.limit()];
            byteBuffer.get(obj);
            this.initializationData.add(obj);
            byteBuffer.flip();
        }
        this.durationUs = mediaFormat.containsKey("durationUs") ? mediaFormat.getLong("durationUs") : -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
    }

    private MediaFormat(String str, int i, long j, int i2, int i3, float f, int i4, int i5, List<byte[]> list) {
        List list2;
        this.mimeType = str;
        this.maxInputSize = i;
        this.durationUs = j;
        this.width = i2;
        this.height = i3;
        this.pixelWidthHeightRatio = f;
        this.channelCount = i4;
        this.sampleRate = i5;
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        this.initializationData = list2;
        this.maxWidth = -1;
        this.maxHeight = -1;
    }

    public final void setMaxVideoDimensions(int i, int i2) {
        this.maxWidth = i;
        this.maxHeight = i2;
        if (this.frameworkMediaFormat != null) {
            maybeSetMaxDimensionsV16(this.frameworkMediaFormat);
        }
    }

    public final int getMaxVideoWidth() {
        return this.maxWidth;
    }

    public final int getMaxVideoHeight() {
        return this.maxHeight;
    }

    public final int hashCode() {
        if (this.hashCode == 0) {
            int hashCode = (((((((((((((((((((this.mimeType == null ? 0 : this.mimeType.hashCode()) + 527) * 31) + this.maxInputSize) * 31) + this.width) * 31) + this.height) * 31) + Float.floatToRawIntBits(this.pixelWidthHeightRatio)) * 31) + ((int) this.durationUs)) * 31) + this.maxWidth) * 31) + this.maxHeight) * 31) + this.channelCount) * 31) + this.sampleRate;
            for (int i = 0; i < this.initializationData.size(); i++) {
                hashCode = (hashCode * 31) + Arrays.hashCode((byte[]) this.initializationData.get(i));
            }
            this.hashCode = hashCode;
        }
        return this.hashCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equalsInternal((MediaFormat) obj, false);
    }

    public final boolean equals(MediaFormat mediaFormat, boolean z) {
        if (this == mediaFormat) {
            return true;
        }
        return mediaFormat == null ? false : equalsInternal(mediaFormat, z);
    }

    private boolean equalsInternal(MediaFormat mediaFormat, boolean z) {
        if (this.maxInputSize != mediaFormat.maxInputSize || this.width != mediaFormat.width || this.height != mediaFormat.height || this.pixelWidthHeightRatio != mediaFormat.pixelWidthHeightRatio || ((!z && (this.maxWidth != mediaFormat.maxWidth || this.maxHeight != mediaFormat.maxHeight)) || this.channelCount != mediaFormat.channelCount || this.sampleRate != mediaFormat.sampleRate || !Util.areEqual(this.mimeType, mediaFormat.mimeType) || this.initializationData.size() != mediaFormat.initializationData.size())) {
            return false;
        }
        for (int i = 0; i < this.initializationData.size(); i++) {
            if (!Arrays.equals((byte[]) this.initializationData.get(i), (byte[]) mediaFormat.initializationData.get(i))) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("MediaFormat(");
        stringBuilder.append(this.mimeType);
        stringBuilder.append(", ");
        stringBuilder.append(this.maxInputSize);
        stringBuilder.append(", ");
        stringBuilder.append(this.width);
        stringBuilder.append(", ");
        stringBuilder.append(this.height);
        stringBuilder.append(", ");
        stringBuilder.append(this.pixelWidthHeightRatio);
        stringBuilder.append(", ");
        stringBuilder.append(this.channelCount);
        stringBuilder.append(", ");
        stringBuilder.append(this.sampleRate);
        stringBuilder.append(", ");
        stringBuilder.append(this.durationUs);
        stringBuilder.append(", ");
        stringBuilder.append(this.maxWidth);
        stringBuilder.append(", ");
        stringBuilder.append(this.maxHeight);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @TargetApi(16)
    public final android.media.MediaFormat getFrameworkMediaFormatV16() {
        if (this.frameworkMediaFormat == null) {
            android.media.MediaFormat mediaFormat = new android.media.MediaFormat();
            mediaFormat.setString("mime", this.mimeType);
            maybeSetIntegerV16(mediaFormat, "max-input-size", this.maxInputSize);
            maybeSetIntegerV16(mediaFormat, "width", this.width);
            maybeSetIntegerV16(mediaFormat, "height", this.height);
            maybeSetIntegerV16(mediaFormat, "channel-count", this.channelCount);
            maybeSetIntegerV16(mediaFormat, "sample-rate", this.sampleRate);
            maybeSetFloatV16(mediaFormat, KEY_PIXEL_WIDTH_HEIGHT_RATIO, this.pixelWidthHeightRatio);
            for (int i = 0; i < this.initializationData.size(); i++) {
                mediaFormat.setByteBuffer("csd-".concat(String.valueOf(i)), ByteBuffer.wrap((byte[]) this.initializationData.get(i)));
            }
            if (this.durationUs != -1) {
                mediaFormat.setLong("durationUs", this.durationUs);
            }
            maybeSetMaxDimensionsV16(mediaFormat);
            this.frameworkMediaFormat = mediaFormat;
        }
        return this.frameworkMediaFormat;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    private final void maybeSetMaxDimensionsV16(android.media.MediaFormat mediaFormat) {
        maybeSetIntegerV16(mediaFormat, "max-width", this.maxWidth);
        maybeSetIntegerV16(mediaFormat, "max-height", this.maxHeight);
    }

    @TargetApi(16)
    private static final void maybeSetIntegerV16(android.media.MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @TargetApi(16)
    private static final void maybeSetFloatV16(android.media.MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    @TargetApi(16)
    private static final int getOptionalIntegerV16(android.media.MediaFormat mediaFormat, String str) {
        return mediaFormat.containsKey(str) ? mediaFormat.getInteger(str) : -1;
    }

    @TargetApi(16)
    private static final float getOptionalFloatV16(android.media.MediaFormat mediaFormat, String str) {
        return mediaFormat.containsKey(str) ? mediaFormat.getFloat(str) : -1.0f;
    }
}
