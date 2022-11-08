package com.google.android.gms.internal.clearcut;

final class dd extends da {
    dd() {
    }

    private static int a(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return cz.b(i);
            case 1:
                return cz.b(i, cx.a(bArr, j));
            case 2:
                return cz.b(i, cx.a(bArr, j), cx.a(bArr, 1 + j));
            default:
                throw new AssertionError();
        }
    }

    final int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j = (long) i;
        long j2 = j + ((long) i2);
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
        }
        char charAt;
        long j3;
        int i3 = 0;
        while (i3 < length) {
            charAt = charSequence.charAt(i3);
            if (charAt >= 128) {
                break;
            }
            j3 = 1 + j;
            cx.a(bArr, j, (byte) charAt);
            i3++;
            j = j3;
        }
        if (i3 == length) {
            return (int) j;
        }
        j3 = j;
        while (i3 < length) {
            charAt = charSequence.charAt(i3);
            long j4;
            if (charAt < 128 && j3 < j2) {
                j = 1 + j3;
                cx.a(bArr, j3, (byte) charAt);
            } else if (charAt < 2048 && j3 <= j2 - 2) {
                j4 = j3 + 1;
                cx.a(bArr, j3, (byte) ((charAt >>> 6) | 960));
                j = 1 + j4;
                cx.a(bArr, j4, (byte) ((charAt & 63) | 128));
            } else if ((charAt < 55296 || 57343 < charAt) && j3 <= j2 - 3) {
                j = 1 + j3;
                cx.a(bArr, j3, (byte) ((charAt >>> 12) | 480));
                j3 = 1 + j;
                cx.a(bArr, j, (byte) (((charAt >>> 6) & 63) | 128));
                j = 1 + j3;
                cx.a(bArr, j3, (byte) ((charAt & 63) | 128));
            } else if (j3 <= j2 - 4) {
                if (i3 + 1 != length) {
                    i3++;
                    char charAt2 = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        j = 1 + j3;
                        cx.a(bArr, j3, (byte) ((toCodePoint >>> 18) | 240));
                        j3 = 1 + j;
                        cx.a(bArr, j, (byte) (((toCodePoint >>> 12) & 63) | 128));
                        j4 = j3 + 1;
                        cx.a(bArr, j3, (byte) (((toCodePoint >>> 6) & 63) | 128));
                        j = 1 + j4;
                        cx.a(bArr, j4, (byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new dc(i3 - 1, length);
            } else if (55296 > charAt || charAt > 57343 || (i3 + 1 != length && Character.isSurrogatePair(charAt, charSequence.charAt(i3 + 1)))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt + " at index " + j3);
            } else {
                throw new dc(i3, length);
            }
            i3++;
            j3 = j;
        }
        return (int) j3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final int b(byte[] bArr, int i, int i2) {
        if (((i | i2) | (bArr.length - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        }
        int i3;
        long j;
        long j2;
        long j3 = (long) i;
        int i4 = (int) (((long) i2) - j3);
        if (i4 < 16) {
            i3 = 0;
        } else {
            i3 = 0;
            j = j3;
            while (i3 < i4) {
                j2 = 1 + j;
                if (cx.a(bArr, j) < (byte) 0) {
                    break;
                }
                i3++;
                j = j2;
            }
            i3 = i4;
        }
        j = ((long) i3) + j3;
        i3 = i4 - i3;
        while (true) {
            i4 = (byte) 0;
            j3 = j;
            while (i3 > 0) {
                j = 1 + j3;
                i4 = cx.a(bArr, j3);
                if (i4 < 0) {
                    j3 = j;
                    break;
                }
                i3--;
                j3 = j;
            }
            if (i3 != 0) {
                i3--;
                if (i4 >= -32) {
                    if (i4 >= -16) {
                        if (i3 >= 3) {
                            i3 -= 3;
                            j = 1 + j3;
                            byte a = cx.a(bArr, j3);
                            if (a > (byte) -65 || (((i4 << 28) + (a + 112)) >> 30) != 0) {
                                break;
                            }
                            j3 = 1 + j;
                            if (cx.a(bArr, j) > (byte) -65) {
                                break;
                            }
                            j = 1 + j3;
                            if (cx.a(bArr, j3) > (byte) -65) {
                                break;
                            }
                        } else {
                            return a(bArr, i4, j3, i3);
                        }
                    } else if (i3 >= 2) {
                        i3 -= 2;
                        j2 = j3 + 1;
                        byte a2 = cx.a(bArr, j3);
                        if (a2 > (byte) -65 || ((i4 == -32 && a2 < (byte) -96) || (i4 == -19 && a2 >= (byte) -96))) {
                            break;
                        }
                        j = 1 + j2;
                        if (cx.a(bArr, j2) > (byte) -65) {
                            break;
                        }
                    } else {
                        return a(bArr, i4, j3, i3);
                    }
                } else if (i3 != 0) {
                    i3--;
                    if (i4 < -62) {
                        break;
                    }
                    j = 1 + j3;
                    if (cx.a(bArr, j3) > (byte) -65) {
                        break;
                    }
                } else {
                    return i4;
                }
            }
            return 0;
        }
        return -1;
    }
}
