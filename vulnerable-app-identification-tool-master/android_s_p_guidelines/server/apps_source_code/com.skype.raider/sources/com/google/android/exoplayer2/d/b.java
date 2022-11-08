package com.google.android.exoplayer2.d;

import android.util.Pair;
import com.skype.Defines;

public final class b {
    private static final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final int[] b = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE, 7350};
    private static final int[] c = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> a(byte[] audioSpecificConfig) {
        j bitArray = new j(audioSpecificConfig);
        int audioObjectType = a(bitArray);
        int sampleRate = b(bitArray);
        int channelConfiguration = bitArray.c(4);
        if (audioObjectType == 5 || audioObjectType == 29) {
            sampleRate = b(bitArray);
            if (a(bitArray) == 22) {
                channelConfiguration = bitArray.c(4);
            }
        }
        int channelCount = c[channelConfiguration];
        a.a(channelCount != -1);
        return Pair.create(Integer.valueOf(sampleRate), Integer.valueOf(channelCount));
    }

    public static byte[] a(int audioObjectType, int sampleRateIndex, int channelConfig) {
        return new byte[]{(byte) (((audioObjectType << 3) & 248) | ((sampleRateIndex >> 1) & 7)), (byte) (((sampleRateIndex << 7) & 128) | ((channelConfig << 3) & 120))};
    }

    public static byte[] a(byte[] data, int offset, int length) {
        byte[] nalUnit = new byte[(a.length + length)];
        System.arraycopy(a, 0, nalUnit, 0, a.length);
        System.arraycopy(data, offset, nalUnit, a.length, length);
        return nalUnit;
    }

    private static int a(j bitArray) {
        int audioObjectType = bitArray.c(5);
        if (audioObjectType == 31) {
            return bitArray.c(6) + 32;
        }
        return audioObjectType;
    }

    private static int b(j bitArray) {
        int frequencyIndex = bitArray.c(4);
        if (frequencyIndex == 15) {
            return bitArray.c(24);
        }
        a.a(frequencyIndex < 13);
        return b[frequencyIndex];
    }
}
