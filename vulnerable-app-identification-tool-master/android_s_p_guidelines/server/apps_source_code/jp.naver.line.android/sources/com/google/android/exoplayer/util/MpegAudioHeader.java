package com.google.android.exoplayer.util;

import com.linecorp.yuki.sensetime.model.SegmentationData;
import org.apache.http.HttpStatus;
import org.bouncycastle.crypto.tls.CipherSuite;

public final class MpegAudioHeader {
    private static final int[] BITRATE_V1_L1 = new int[]{32, 64, 96, 128, SegmentationData.MAX_SEGMENTATION_WIDTH, 192, 224, 256, 288, 320, 352, 384, HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE, 448};
    private static final int[] BITRATE_V1_L2 = new int[]{32, 48, 56, 64, 80, 96, 112, 128, SegmentationData.MAX_SEGMENTATION_WIDTH, 192, 224, 256, 320, 384};
    private static final int[] BITRATE_V1_L3 = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, SegmentationData.MAX_SEGMENTATION_WIDTH, 192, 224, 256, 320};
    private static final int[] BITRATE_V2 = new int[]{8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, SegmentationData.MAX_SEGMENTATION_WIDTH};
    private static final int[] BITRATE_V2_L1 = new int[]{32, 48, 56, 64, 80, 96, 112, 128, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, SegmentationData.MAX_SEGMENTATION_WIDTH, 176, 192, 224, 256};
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final String[] MIME_TYPE_BY_LAYER = new String[]{MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] SAMPLING_RATE_V1 = new int[]{44100, 48000, 32000};
    public int bitrate;
    public int channels;
    public int frameSize;
    public String mimeType;
    public int sampleRate;
    public int samplesPerFrame;
    public int version;

    public static int getFrameSize(int i) {
        if ((i & -2097152) != -2097152) {
            return -1;
        }
        int i2 = (i >>> 19) & 3;
        if (i2 == 1) {
            return -1;
        }
        int i3 = (i >>> 17) & 3;
        if (i3 == 0) {
            return -1;
        }
        int i4 = (i >>> 12) & 15;
        if (i4 == 0 || i4 == 15) {
            return -1;
        }
        int i5 = (i >>> 10) & 3;
        if (i5 == 3) {
            return -1;
        }
        int i6 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i6 /= 2;
        } else if (i2 == 0) {
            i6 /= 4;
        }
        i = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1]) * 12000) / i6) + i) * 4;
        }
        i4 = i2 == 3 ? i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1] : BITRATE_V2[i4 - 1];
        i5 = 144000;
        if (i2 == 3) {
            return ((i4 * 144000) / i6) + i;
        }
        if (i3 == 1) {
            i5 = 72000;
        }
        return ((i5 * i4) / i6) + i;
    }

    public static boolean populateHeader(int i, MpegAudioHeader mpegAudioHeader) {
        if ((i & -2097152) != -2097152) {
            return false;
        }
        int i2 = (i >>> 19) & 3;
        if (i2 == 1) {
            return false;
        }
        int i3 = (i >>> 17) & 3;
        if (i3 == 0) {
            return false;
        }
        int i4 = (i >>> 12) & 15;
        if (i4 == 0 || i4 == 15) {
            return false;
        }
        int i5 = (i >>> 10) & 3;
        if (i5 == 3) {
            return false;
        }
        int i6;
        int i7;
        int i8;
        int i9 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i9 /= 2;
        } else if (i2 == 0) {
            i9 /= 4;
        }
        int i10 = i9;
        i9 = (i >>> 9) & 1;
        if (i3 == 3) {
            i4 = i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1];
            i6 = (((i4 * 12000) / i10) + i9) * 4;
            i7 = i4;
            i8 = 384;
        } else {
            i6 = 1152;
            if (i2 == 3) {
                i4 = i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1];
            } else {
                i4 = BITRATE_V2[i4 - 1];
                if (i3 == 1) {
                    i6 = 576;
                }
                if (i3 == 1) {
                    i7 = 72000;
                    i8 = i6;
                    i6 = ((i7 * i4) / i10) + i9;
                    i7 = i4;
                }
            }
            i7 = 144000;
            i8 = i6;
            i6 = ((i7 * i4) / i10) + i9;
            i7 = i4;
        }
        mpegAudioHeader.setValues(i2, MIME_TYPE_BY_LAYER[3 - i3], i6, i10, ((i >> 6) & 3) == 3 ? 1 : 2, i7, i8);
        return true;
    }

    private void setValues(int i, String str, int i2, int i3, int i4, int i5, int i6) {
        this.version = i;
        this.mimeType = str;
        this.frameSize = i2;
        this.sampleRate = i3;
        this.channels = i4;
        this.bitrate = i5;
        this.samplesPerFrame = i6;
    }
}
