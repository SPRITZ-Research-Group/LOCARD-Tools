package com.google.android.exoplayer.util;

import android.annotation.SuppressLint;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public final class CodecSpecificDataUtil {
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = new int[]{0, 1, 2, 3, 4, 5, 6, 8};
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final byte[] NAL_START_CODE = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final int SPS_NAL_UNIT_TYPE = 7;

    private CodecSpecificDataUtil() {
    }

    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(byte[] bArr) {
        boolean z = false;
        int i = (bArr[0] >> 3) & 31;
        i = (i == 5 || i == 29) ? 1 : 0;
        i++;
        int i2 = ((bArr[i] & 7) << 1) | ((bArr[i] >> 7) & 1);
        if (i2 < 13) {
            z = true;
        }
        Assertions.checkState(z);
        return Pair.create(Integer.valueOf(AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[i2]), Integer.valueOf((bArr[i] >> 3) & 15));
    }

    public static byte[] buildAacAudioSpecificConfig(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    public static byte[] buildAacAudioSpecificConfig(int i, int i2) {
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE.length; i5++) {
            if (i == AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[i5]) {
                i4 = i5;
            }
        }
        for (i = 0; i < AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE.length; i++) {
            if (i2 == AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[i]) {
                i3 = i;
            }
        }
        byte[] bArr = new byte[2];
        bArr[0] = (byte) ((i4 >> 1) | 16);
        bArr[1] = (byte) ((i3 << 3) | ((i4 & 1) << 7));
        return bArr;
    }

    public static byte[] buildNalUnit(byte[] bArr, int i, int i2) {
        Object obj = new byte[(NAL_START_CODE.length + i2)];
        System.arraycopy(NAL_START_CODE, 0, obj, 0, NAL_START_CODE.length);
        System.arraycopy(bArr, i, obj, NAL_START_CODE.length, i2);
        return obj;
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        List arrayList = new ArrayList();
        int i = 0;
        do {
            arrayList.add(Integer.valueOf(i));
            i = findNalStartCode(bArr, i + NAL_START_CODE.length);
        } while (i != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int intValue = ((Integer) arrayList.get(i2)).intValue();
            Object obj = new byte[((i2 < arrayList.size() + -1 ? ((Integer) arrayList.get(i2 + 1)).intValue() : bArr.length) - intValue)];
            System.arraycopy(bArr, intValue, obj, 0, obj.length);
            bArr2[i2] = obj;
            i2++;
        }
        return bArr2;
    }

    private static int findNalStartCode(byte[] bArr, int i) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i <= length) {
            if (isNalStartCode(bArr, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean isNalStartCode(byte[] bArr, int i) {
        if (bArr.length - i <= NAL_START_CODE.length) {
            return false;
        }
        for (int i2 = 0; i2 < NAL_START_CODE.length; i2++) {
            if (bArr[i + i2] != NAL_START_CODE[i2]) {
                return false;
            }
        }
        return true;
    }

    public static Pair<Integer, Integer> parseSpsNalUnit(byte[] bArr) {
        return (isNalStartCode(bArr, 0) && bArr.length == 8 && (bArr[5] & 31) == 7) ? Pair.create(Integer.valueOf(parseAvcProfile(bArr)), Integer.valueOf(parseAvcLevel(bArr))) : null;
    }

    @SuppressLint({"InlinedApi"})
    private static int parseAvcProfile(byte[] bArr) {
        int i = bArr[6] & 255;
        if (i == 66) {
            return 1;
        }
        if (i == 77) {
            return 2;
        }
        if (i == 88) {
            return 4;
        }
        if (i == 100) {
            return 8;
        }
        if (i == 110) {
            return 16;
        }
        if (i != 122) {
            return i != 244 ? 0 : 64;
        } else {
            return 32;
        }
    }

    @SuppressLint({"InlinedApi"})
    private static int parseAvcLevel(byte[] bArr) {
        int i = bArr[8] & 255;
        switch (i) {
            case 9:
                return 2;
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            default:
                switch (i) {
                    case 20:
                        return 32;
                    case 21:
                        return 64;
                    case 22:
                        return 128;
                    default:
                        switch (i) {
                            case 30:
                                return 256;
                            case 31:
                                return 512;
                            case 32:
                                return 1024;
                            default:
                                switch (i) {
                                    case 40:
                                        return 2048;
                                    case 41:
                                        return 4096;
                                    case 42:
                                        return 8192;
                                    default:
                                        switch (i) {
                                            case 50:
                                                return 16384;
                                            case 51:
                                                return 32768;
                                            default:
                                                return 0;
                                        }
                                }
                        }
                }
        }
    }
}
