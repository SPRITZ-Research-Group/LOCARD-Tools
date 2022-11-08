package com.google.android.exoplayer.util;

import java.util.Arrays;

public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final int EXTENDED_SAR = 255;
    public static final byte[] NAL_START_CODE = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    public static int unescapeStream(byte[] bArr, int i) {
        synchronized (scratchEscapePositionsLock) {
            int i2;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                i3 = findNextUnescapeIndex(bArr, i3, i);
                if (i3 < i) {
                    if (scratchEscapePositions.length <= i4) {
                        int[] iArr = scratchEscapePositions;
                        scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                    }
                    i2 = i4 + 1;
                    scratchEscapePositions[i4] = i3;
                    i3 += 3;
                    i4 = i2;
                }
            }
            i -= i4;
            int i5 = 0;
            i2 = 0;
            for (i3 = 0; i3 < i4; i3++) {
                int i6 = scratchEscapePositions[i3] - i2;
                System.arraycopy(bArr, i2, bArr, i5, i6);
                i5 += i6;
                int i7 = i5 + 1;
                bArr[i5] = (byte) 0;
                i5 = i7 + 1;
                bArr[i7] = (byte) 0;
                i2 += i6 + 3;
            }
            System.arraycopy(bArr, i2, bArr, i5, i - i5);
        }
        return i;
    }

    public static byte[] parseChildNalUnit(ParsableByteArray parsableByteArray) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(readUnsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.data, position, readUnsignedShort);
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static int findNalUnit(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        boolean z = false;
        Assertions.checkState(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr != null) {
            if (zArr[0]) {
                clearPrefixFlags(zArr);
                return i - 3;
            } else if (i3 > 1 && zArr[1] && bArr[i] == (byte) 1) {
                clearPrefixFlags(zArr);
                return i - 2;
            } else if (i3 > 2 && zArr[2] && bArr[i] == (byte) 0 && bArr[i + 1] == (byte) 1) {
                clearPrefixFlags(zArr);
                return i - 1;
            }
        }
        int i4 = i2 - 1;
        i += 2;
        while (i < i4) {
            if ((bArr[i] & 254) == 0) {
                int i5 = i - 2;
                if (bArr[i5] == (byte) 0 && bArr[i - 1] == (byte) 0 && bArr[i] == (byte) 1) {
                    if (zArr != null) {
                        clearPrefixFlags(zArr);
                    }
                    return i5;
                }
                i -= 2;
            }
            i += 3;
        }
        if (zArr != null) {
            boolean z2;
            if (i3 > 2) {
                z2 = false;
                zArr[0] = z2;
                z2 = i3 <= 1 ? !(zArr[2] && bArr[i4] == (byte) 0) : !(bArr[i2 - 2] == (byte) 0 && bArr[i4] == (byte) 0);
                zArr[1] = z2;
                if (bArr[i4] == (byte) 0) {
                    z = true;
                }
                zArr[2] = z;
            } else {
                z2 = false;
                zArr[0] = z2;
                if (i3 <= 1) {
                    zArr[1] = z2;
                    if (bArr[i4] == (byte) 0) {
                        z = true;
                    }
                    zArr[2] = z;
                } else {
                    zArr[1] = z2;
                    if (bArr[i4] == (byte) 0) {
                        z = true;
                    }
                    zArr[2] = z;
                }
                zArr[1] = z2;
                if (bArr[i4] == (byte) 0) {
                    z = true;
                }
                zArr[2] = z;
            }
            z2 = true;
            zArr[0] = z2;
            if (i3 <= 1) {
                zArr[1] = z2;
                if (bArr[i4] == (byte) 0) {
                    z = true;
                }
                zArr[2] = z;
            } else {
                zArr[1] = z2;
                if (bArr[i4] == (byte) 0) {
                    z = true;
                }
                zArr[2] = z;
            }
            zArr[1] = z2;
            if (bArr[i4] == (byte) 0) {
                z = true;
            }
            zArr[2] = z;
        }
        return i2;
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == (byte) 0 && bArr[i + 1] == (byte) 0 && bArr[i + 2] == (byte) 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private NalUnitUtil() {
    }
}
