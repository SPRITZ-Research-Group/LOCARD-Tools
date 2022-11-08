package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Format implements Parcelable {
    public static final Creator<Format> CREATOR = new Creator<Format>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new Format[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new Format(parcel);
        }
    };
    private int A;
    public final String a;
    public final int b;
    public final String c;
    public final Metadata d;
    public final String e;
    public final String f;
    public final int g;
    public final List<byte[]> h;
    public final DrmInitData i;
    public final int j;
    public final int k;
    public final float l;
    public final int m;
    public final float n;
    public final int o;
    public final byte[] p;
    public final ColorInfo q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final long w;
    public final int x;
    public final String y;
    public final int z;

    public static Format a(String id, String sampleMimeType, int width, int height, List<byte[]> initializationData, float pixelWidthHeightRatio) {
        return a(id, sampleMimeType, -1, width, height, initializationData, -1, pixelWidthHeightRatio, null, -1, null, null);
    }

    public static Format a(String id, String sampleMimeType, int maxInputSize, int width, int height, List<byte[]> initializationData, int rotationDegrees, float pixelWidthHeightRatio, byte[] projectionData, int stereoMode, ColorInfo colorInfo, DrmInitData drmInitData) {
        return new Format(id, null, sampleMimeType, null, -1, maxInputSize, width, height, -1.0f, rotationDegrees, pixelWidthHeightRatio, projectionData, stereoMode, colorInfo, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, initializationData, drmInitData, null);
    }

    public static Format a(String id, String sampleMimeType, int bitrate, int maxInputSize, int channelCount, int sampleRate, List<byte[]> initializationData, DrmInitData drmInitData, String language) {
        return a(id, sampleMimeType, bitrate, maxInputSize, channelCount, sampleRate, -1, initializationData, drmInitData, 0, language);
    }

    public static Format a(String id, String sampleMimeType, int bitrate, int maxInputSize, int channelCount, int sampleRate, int pcmEncoding, List<byte[]> initializationData, DrmInitData drmInitData, int selectionFlags, String language) {
        return a(id, sampleMimeType, bitrate, maxInputSize, channelCount, sampleRate, pcmEncoding, -1, -1, initializationData, drmInitData, selectionFlags, language, null);
    }

    public static Format a(String id, String sampleMimeType, int bitrate, int maxInputSize, int channelCount, int sampleRate, int pcmEncoding, int encoderDelay, int encoderPadding, List<byte[]> initializationData, DrmInitData drmInitData, int selectionFlags, String language, Metadata metadata) {
        return new Format(id, null, sampleMimeType, null, bitrate, maxInputSize, -1, -1, -1.0f, -1, -1.0f, null, -1, null, channelCount, sampleRate, pcmEncoding, encoderDelay, encoderPadding, selectionFlags, language, -1, Long.MAX_VALUE, initializationData, drmInitData, metadata);
    }

    public static Format a(String id, String sampleMimeType, int selectionFlags, String language, DrmInitData drmInitData) {
        return a(id, sampleMimeType, selectionFlags, language, -1, drmInitData, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format a(String id, String sampleMimeType, int selectionFlags, String language, int accessibilityChannel) {
        return a(id, sampleMimeType, selectionFlags, language, accessibilityChannel, null, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format a(String id, String sampleMimeType, int selectionFlags, String language, int accessibilityChannel, DrmInitData drmInitData, long subsampleOffsetUs, List<byte[]> initializationData) {
        return new Format(id, null, sampleMimeType, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, selectionFlags, language, accessibilityChannel, subsampleOffsetUs, initializationData, drmInitData, null);
    }

    public static Format a(String id, String sampleMimeType, List<byte[]> initializationData, String language, DrmInitData drmInitData) {
        return new Format(id, null, sampleMimeType, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, language, -1, Long.MAX_VALUE, initializationData, drmInitData, null);
    }

    public static Format a(String sampleMimeType, long subsampleOffsetUs) {
        return new Format(null, null, sampleMimeType, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, subsampleOffsetUs, null, null, null);
    }

    public static Format a(String id, String sampleMimeType, DrmInitData drmInitData) {
        return new Format(id, null, sampleMimeType, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, drmInitData, null);
    }

    private Format(String id, String containerMimeType, String sampleMimeType, String codecs, int bitrate, int maxInputSize, int width, int height, float frameRate, int rotationDegrees, float pixelWidthHeightRatio, byte[] projectionData, int stereoMode, ColorInfo colorInfo, int channelCount, int sampleRate, int pcmEncoding, int encoderDelay, int encoderPadding, int selectionFlags, String language, int accessibilityChannel, long subsampleOffsetUs, List<byte[]> initializationData, DrmInitData drmInitData, Metadata metadata) {
        this.a = id;
        this.e = containerMimeType;
        this.f = sampleMimeType;
        this.c = codecs;
        this.b = bitrate;
        this.g = maxInputSize;
        this.j = width;
        this.k = height;
        this.l = frameRate;
        this.m = rotationDegrees;
        this.n = pixelWidthHeightRatio;
        this.p = projectionData;
        this.o = stereoMode;
        this.q = colorInfo;
        this.r = channelCount;
        this.s = sampleRate;
        this.t = pcmEncoding;
        this.u = encoderDelay;
        this.v = encoderPadding;
        this.x = selectionFlags;
        this.y = language;
        this.z = accessibilityChannel;
        this.w = subsampleOffsetUs;
        if (initializationData == null) {
            initializationData = Collections.emptyList();
        }
        this.h = initializationData;
        this.i = drmInitData;
        this.d = metadata;
    }

    Format(Parcel in) {
        this.a = in.readString();
        this.e = in.readString();
        this.f = in.readString();
        this.c = in.readString();
        this.b = in.readInt();
        this.g = in.readInt();
        this.j = in.readInt();
        this.k = in.readInt();
        this.l = in.readFloat();
        this.m = in.readInt();
        this.n = in.readFloat();
        this.p = in.readInt() != 0 ? in.createByteArray() : null;
        this.o = in.readInt();
        this.q = (ColorInfo) in.readParcelable(ColorInfo.class.getClassLoader());
        this.r = in.readInt();
        this.s = in.readInt();
        this.t = in.readInt();
        this.u = in.readInt();
        this.v = in.readInt();
        this.x = in.readInt();
        this.y = in.readString();
        this.z = in.readInt();
        this.w = in.readLong();
        int initializationDataSize = in.readInt();
        this.h = new ArrayList(initializationDataSize);
        for (int i = 0; i < initializationDataSize; i++) {
            this.h.add(in.createByteArray());
        }
        this.i = (DrmInitData) in.readParcelable(DrmInitData.class.getClassLoader());
        this.d = (Metadata) in.readParcelable(Metadata.class.getClassLoader());
    }

    public final Format a(int maxInputSize) {
        return new Format(this.a, this.e, this.f, this.c, this.b, maxInputSize, this.j, this.k, this.l, this.m, this.n, this.p, this.o, this.q, this.r, this.s, this.t, this.u, this.v, this.x, this.y, this.z, this.w, this.h, this.i, this.d);
    }

    public final Format a(long subsampleOffsetUs) {
        return new Format(this.a, this.e, this.f, this.c, this.b, this.g, this.j, this.k, this.l, this.m, this.n, this.p, this.o, this.q, this.r, this.s, this.t, this.u, this.v, this.x, this.y, this.z, subsampleOffsetUs, this.h, this.i, this.d);
    }

    public final Format a(int encoderDelay, int encoderPadding) {
        return new Format(this.a, this.e, this.f, this.c, this.b, this.g, this.j, this.k, this.l, this.m, this.n, this.p, this.o, this.q, this.r, this.s, this.t, encoderDelay, encoderPadding, this.x, this.y, this.z, this.w, this.h, this.i, this.d);
    }

    public final Format a(DrmInitData drmInitData) {
        return new Format(this.a, this.e, this.f, this.c, this.b, this.g, this.j, this.k, this.l, this.m, this.n, this.p, this.o, this.q, this.r, this.s, this.t, this.u, this.v, this.x, this.y, this.z, this.w, this.h, drmInitData, this.d);
    }

    public final Format a(Metadata metadata) {
        return new Format(this.a, this.e, this.f, this.c, this.b, this.g, this.j, this.k, this.l, this.m, this.n, this.p, this.o, this.q, this.r, this.s, this.t, this.u, this.v, this.x, this.y, this.z, this.w, this.h, this.i, metadata);
    }

    public final int a() {
        return (this.j == -1 || this.k == -1) ? -1 : this.j * this.k;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat b() {
        MediaFormat format = new MediaFormat();
        format.setString("mime", this.f);
        String str = "language";
        String str2 = this.y;
        if (str2 != null) {
            format.setString(str, str2);
        }
        a(format, "max-input-size", this.g);
        a(format, "width", this.j);
        a(format, "height", this.k);
        str = "frame-rate";
        float f = this.l;
        if (f != -1.0f) {
            format.setFloat(str, f);
        }
        a(format, "rotation-degrees", this.m);
        a(format, "channel-count", this.r);
        a(format, "sample-rate", this.s);
        a(format, "encoder-delay", this.u);
        a(format, "encoder-padding", this.v);
        for (int i = 0; i < this.h.size(); i++) {
            format.setByteBuffer("csd-" + i, ByteBuffer.wrap((byte[]) this.h.get(i)));
        }
        ColorInfo colorInfo = this.q;
        if (colorInfo != null) {
            a(format, "color-transfer", colorInfo.c);
            a(format, "color-standard", colorInfo.a);
            a(format, "color-range", colorInfo.b);
            str2 = "hdr-static-info";
            byte[] bArr = colorInfo.d;
            if (bArr != null) {
                format.setByteBuffer(str2, ByteBuffer.wrap(bArr));
            }
        }
        return format;
    }

    public final String toString() {
        return "Format(" + this.a + ", " + this.e + ", " + this.f + ", " + this.b + ", " + this.y + ", [" + this.j + ", " + this.k + ", " + this.l + "], [" + this.r + ", " + this.s + "])";
    }

    public final int hashCode() {
        int i = 0;
        if (this.A == 0) {
            int hashCode = ((((((((((((((((((((((((this.a == null ? 0 : this.a.hashCode()) + 527) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + this.b) * 31) + this.j) * 31) + this.k) * 31) + this.r) * 31) + this.s) * 31) + (this.y == null ? 0 : this.y.hashCode())) * 31) + this.z) * 31) + (this.i == null ? 0 : this.i.hashCode())) * 31;
            if (this.d != null) {
                i = this.d.hashCode();
            }
            this.A = hashCode + i;
        }
        return this.A;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Format other = (Format) obj;
        if (this.b != other.b || this.g != other.g || this.j != other.j || this.k != other.k || this.l != other.l || this.m != other.m || this.n != other.n || this.o != other.o || this.r != other.r || this.s != other.s || this.t != other.t || this.u != other.u || this.v != other.v || this.w != other.w || this.x != other.x || !s.a(this.a, other.a) || !s.a(this.y, other.y) || this.z != other.z || !s.a(this.e, other.e) || !s.a(this.f, other.f) || !s.a(this.c, other.c) || !s.a(this.i, other.i) || !s.a(this.d, other.d) || !s.a(this.q, other.q) || !Arrays.equals(this.p, other.p) || this.h.size() != other.h.size()) {
            return false;
        }
        for (int i = 0; i < this.h.size(); i++) {
            if (!Arrays.equals((byte[]) this.h.get(i), (byte[]) other.h.get(i))) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(16)
    private static void a(MediaFormat format, String key, int value) {
        if (value != -1) {
            format.setInteger(key, value);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeString(this.a);
        dest.writeString(this.e);
        dest.writeString(this.f);
        dest.writeString(this.c);
        dest.writeInt(this.b);
        dest.writeInt(this.g);
        dest.writeInt(this.j);
        dest.writeInt(this.k);
        dest.writeFloat(this.l);
        dest.writeInt(this.m);
        dest.writeFloat(this.n);
        if (this.p != null) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (this.p != null) {
            dest.writeByteArray(this.p);
        }
        dest.writeInt(this.o);
        dest.writeParcelable(this.q, flags);
        dest.writeInt(this.r);
        dest.writeInt(this.s);
        dest.writeInt(this.t);
        dest.writeInt(this.u);
        dest.writeInt(this.v);
        dest.writeInt(this.x);
        dest.writeString(this.y);
        dest.writeInt(this.z);
        dest.writeLong(this.w);
        int initializationDataSize = this.h.size();
        dest.writeInt(initializationDataSize);
        for (int i2 = 0; i2 < initializationDataSize; i2++) {
            dest.writeByteArray((byte[]) this.h.get(i2));
        }
        dest.writeParcelable(this.i, 0);
        dest.writeParcelable(this.d, 0);
    }
}
