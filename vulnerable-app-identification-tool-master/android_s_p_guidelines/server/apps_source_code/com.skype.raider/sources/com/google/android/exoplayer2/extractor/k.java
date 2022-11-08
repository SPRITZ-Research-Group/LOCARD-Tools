package com.google.android.exoplayer2.extractor;

import com.adjust.sdk.Constants;
import com.skype.Defines;

public final class k {
    private static final String[] h = new String[]{"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
    private static final int[] i = new int[]{44100, 48000, 32000};
    private static final int[] j = new int[]{32, 64, 96, 128, 160, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 288, 320, 352, 384, 416, 448};
    private static final int[] k = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE};
    private static final int[] l = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 320, 384};
    private static final int[] m = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 320};
    private static final int[] n = new int[]{8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160};
    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public static int a(int header) {
        if ((header & -2097152) != -2097152) {
            return -1;
        }
        int version = (header >>> 19) & 3;
        if (version == 1) {
            return -1;
        }
        int layer = (header >>> 17) & 3;
        if (layer == 0) {
            return -1;
        }
        int bitrateIndex = (header >>> 12) & 15;
        if (bitrateIndex == 0 || bitrateIndex == 15) {
            return -1;
        }
        int samplingRateIndex = (header >>> 10) & 3;
        if (samplingRateIndex == 3) {
            return -1;
        }
        int samplingRate = i[samplingRateIndex];
        if (version == 2) {
            samplingRate /= 2;
        } else if (version == 0) {
            samplingRate /= 4;
        }
        int padding = (header >>> 9) & 1;
        if (layer == 3) {
            return ((((version == 3 ? j[bitrateIndex - 1] : k[bitrateIndex - 1]) * 12000) / samplingRate) + padding) * 4;
        }
        int bitrate = version == 3 ? layer == 2 ? l[bitrateIndex - 1] : m[bitrateIndex - 1] : n[bitrateIndex - 1];
        if (version == 3) {
            return ((144000 * bitrate) / samplingRate) + padding;
        }
        return (((layer == 1 ? 72000 : 144000) * bitrate) / samplingRate) + padding;
    }

    public static boolean a(int headerData, k header) {
        if ((-2097152 & headerData) != -2097152) {
            return false;
        }
        int version = (headerData >>> 19) & 3;
        if (version == 1) {
            return false;
        }
        int layer = (headerData >>> 17) & 3;
        if (layer == 0) {
            return false;
        }
        int bitrateIndex = (headerData >>> 12) & 15;
        if (bitrateIndex == 0 || bitrateIndex == 15) {
            return false;
        }
        int samplingRateIndex = (headerData >>> 10) & 3;
        if (samplingRateIndex == 3) {
            return false;
        }
        int bitrate;
        int frameSize;
        int samplesPerFrame;
        int i;
        int sampleRate = i[samplingRateIndex];
        if (version == 2) {
            sampleRate /= 2;
        } else if (version == 0) {
            sampleRate /= 4;
        }
        int padding = (headerData >>> 9) & 1;
        if (layer == 3) {
            bitrate = version == 3 ? j[bitrateIndex - 1] : k[bitrateIndex - 1];
            frameSize = (((bitrate * 12000) / sampleRate) + padding) * 4;
            samplesPerFrame = 384;
        } else {
            if (version == 3) {
                bitrate = layer == 2 ? l[bitrateIndex - 1] : m[bitrateIndex - 1];
                samplesPerFrame = 1152;
            } else {
                bitrate = n[bitrateIndex - 1];
                samplesPerFrame = layer == 1 ? 576 : 1152;
                if (layer == 1) {
                    i = 72000;
                    frameSize = ((i * bitrate) / sampleRate) + padding;
                }
            }
            i = 144000;
            frameSize = ((i * bitrate) / sampleRate) + padding;
        }
        String mimeType = h[3 - layer];
        int channels = ((headerData >> 6) & 3) == 3 ? 1 : 2;
        i = bitrate * Constants.ONE_SECOND;
        header.a = version;
        header.b = mimeType;
        header.c = frameSize;
        header.d = sampleRate;
        header.e = channels;
        header.f = i;
        header.g = samplesPerFrame;
        return true;
    }
}
