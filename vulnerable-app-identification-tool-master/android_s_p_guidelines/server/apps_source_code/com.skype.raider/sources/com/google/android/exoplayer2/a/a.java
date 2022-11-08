package com.google.android.exoplayer2.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.skype.Defines;
import java.nio.ByteBuffer;

public final class a {
    private static final int[] a = new int[]{1, 2, 3, 6};
    private static final int[] b = new int[]{48000, 44100, 32000};
    private static final int[] c = new int[]{24000, 22050, 16000};
    private static final int[] d = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] e = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 320, 384, 448, 512, 576, 640};
    private static final int[] f = new int[]{69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static final class a {
        public final String a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;

        /* synthetic */ a(String x0, int x1, int x2, int x3, int x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        private a(String mimeType, int channelCount, int sampleRate, int frameSize, int sampleCount) {
            this.a = mimeType;
            this.c = channelCount;
            this.b = sampleRate;
            this.d = frameSize;
            this.e = sampleCount;
        }
    }

    public static Format a(k data, String trackId, String language, DrmInitData drmInitData) {
        int sampleRate = b[(data.g() & 192) >> 6];
        int nextByte = data.g();
        int channelCount = d[(nextByte & 56) >> 3];
        if ((nextByte & 4) != 0) {
            channelCount++;
        }
        return Format.a(trackId, "audio/ac3", -1, -1, channelCount, sampleRate, null, drmInitData, language);
    }

    public static Format b(k data, String trackId, String language, DrmInitData drmInitData) {
        data.d(2);
        int sampleRate = b[(data.g() & 192) >> 6];
        int nextByte = data.g();
        int channelCount = d[(nextByte & 14) >> 1];
        if ((nextByte & 1) != 0) {
            channelCount++;
        }
        return Format.a(trackId, "audio/eac3", -1, -1, channelCount, sampleRate, null, drmInitData, language);
    }

    public static a a(j data) {
        String mimeType;
        int frameSize;
        int sampleRate;
        int sampleCount;
        int acmod;
        int initialPosition = data.b();
        data.b(40);
        boolean isEac3 = data.c(5) == 16;
        data.a(initialPosition);
        int fscod;
        if (isEac3) {
            int audioBlocks;
            mimeType = "audio/eac3";
            data.b(21);
            frameSize = (data.c(11) + 1) * 2;
            fscod = data.c(2);
            if (fscod == 3) {
                sampleRate = c[data.c(2)];
                audioBlocks = 6;
            } else {
                audioBlocks = a[data.c(2)];
                sampleRate = b[fscod];
            }
            sampleCount = audioBlocks * Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
            acmod = data.c(3);
        } else {
            mimeType = "audio/ac3";
            data.b(32);
            fscod = data.c(2);
            frameSize = a(fscod, data.c(6));
            data.b(8);
            acmod = data.c(3);
            if (!((acmod & 1) == 0 || acmod == 1)) {
                data.b(2);
            }
            if ((acmod & 4) != 0) {
                data.b(2);
            }
            if (acmod == 2) {
                data.b(2);
            }
            sampleRate = b[fscod];
            sampleCount = 1536;
        }
        return new a(mimeType, d[acmod] + (data.d() ? 1 : 0), sampleRate, frameSize, sampleCount, (byte) 0);
    }

    public static int a(byte[] data) {
        if (data.length < 5) {
            return -1;
        }
        return a((data[4] & 192) >> 6, data[4] & 63);
    }

    public static int a() {
        return 1536;
    }

    public static int a(ByteBuffer buffer) {
        int i;
        if (((buffer.get(buffer.position() + 4) & 192) >> 6) == 3) {
            i = 6;
        } else {
            i = a[(buffer.get(buffer.position() + 4) & 48) >> 4];
        }
        return i * Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
    }

    private static int a(int fscod, int frmsizecod) {
        int halfFrmsizecod = frmsizecod / 2;
        if (fscod < 0 || fscod >= b.length || frmsizecod < 0 || halfFrmsizecod >= f.length) {
            return -1;
        }
        int sampleRate = b[fscod];
        if (sampleRate == 44100) {
            return (f[halfFrmsizecod] + (frmsizecod % 2)) * 2;
        }
        int bitrate = e[halfFrmsizecod];
        if (sampleRate == 32000) {
            return bitrate * 6;
        }
        return bitrate * 4;
    }
}
