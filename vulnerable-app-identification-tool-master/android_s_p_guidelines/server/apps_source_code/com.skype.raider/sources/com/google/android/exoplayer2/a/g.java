package com.google.android.exoplayer2.a;

import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.j;
import com.skype.Defines;
import java.nio.ByteBuffer;

public final class g {
    private static final int[] a = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] b = new int[]{-1, Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] c = new int[]{64, 112, 128, 192, 224, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static Format a(byte[] frame, String trackId, String language) {
        j frameBits = new j(frame);
        frameBits.b(60);
        int channelCount = a[frameBits.c(6)];
        int sampleRate = b[frameBits.c(4)];
        int rate = frameBits.c(5);
        int bitrate = rate >= c.length ? -1 : (c[rate] * Constants.ONE_SECOND) / 2;
        frameBits.b(10);
        return Format.a(trackId, "audio/vnd.dts", bitrate, -1, channelCount + (frameBits.c(2) > 0 ? 1 : 0), sampleRate, null, null, language);
    }

    public static int a(byte[] data) {
        return ((((data[4] & 1) << 6) | ((data[5] & 252) >> 2)) + 1) * 32;
    }

    public static int a(ByteBuffer buffer) {
        int position = buffer.position();
        return ((((buffer.get(position + 4) & 1) << 6) | ((buffer.get(position + 5) & 252) >> 2)) + 1) * 32;
    }

    public static int b(byte[] data) {
        return ((((data[5] & 2) << 12) | ((data[6] & 255) << 4)) | ((data[7] & 240) >> 4)) + 1;
    }
}
