package kotlin.reflect.jvm.internal.impl.protobuf;

final class Utf8 {
    private static int incompleteStateFor(int i) {
        return i > -12 ? -1 : i;
    }

    private static int incompleteStateFor(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : i ^ (i2 << 8);
    }

    private static int incompleteStateFor(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return partialIsValidUtf8(bArr, i, i2) == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        if (i != 0) {
            if (i2 >= i3) {
                return i;
            }
            int i4 = (byte) i;
            if (i4 < (byte) -32) {
                if (i4 >= (byte) -62) {
                    i = i2 + 1;
                }
                return -1;
            } else if (i4 < (byte) -16) {
                byte b = (byte) ((i >> 8) ^ -1);
                if (b == (byte) 0) {
                    i = i2 + 1;
                    byte b2 = bArr[i2];
                    if (i >= i3) {
                        return incompleteStateFor(i4, b2);
                    }
                    byte b3 = b2;
                    i2 = i;
                    b = b3;
                }
                if (b <= (byte) -65 && ((i4 != (byte) -32 || b >= (byte) -96) && (i4 != (byte) -19 || b < (byte) -96))) {
                    i = i2 + 1;
                }
                return -1;
            } else {
                int i5 = (byte) ((i >> 8) ^ -1);
                byte b4 = (byte) 0;
                if (i5 == 0) {
                    i = i2 + 1;
                    i5 = bArr[i2];
                    if (i >= i3) {
                        return incompleteStateFor(i4, i5);
                    }
                }
                b4 = (byte) (i >> 16);
                i = i2;
                if (b4 == (byte) 0) {
                    i2 = i + 1;
                    b4 = bArr[i];
                    if (i2 >= i3) {
                        return incompleteStateFor(i4, i5, (int) b4);
                    }
                    i = i2;
                }
                if (i5 <= -65 && (((i4 << 28) + (i5 + 112)) >> 30) == 0 && r4 <= (byte) -65) {
                    i2 = i + 1;
                }
                return -1;
            }
            return partialIsValidUtf8(bArr, i, i3);
        }
        i = i2;
        return partialIsValidUtf8(bArr, i, i3);
    }

    public static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] >= (byte) 0) {
            i++;
        }
        if (i >= i2) {
            return 0;
        }
        return partialIsValidUtf8NonAscii(bArr, i, i2);
    }

    private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b < (byte) 0) {
                byte b2;
                if (b < (byte) -32) {
                    if (i3 >= i2) {
                        return b;
                    }
                    if (b >= (byte) -62) {
                        i = i3 + 1;
                        if (bArr[i3] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (b < (byte) -16) {
                    if (i3 >= i2 - 1) {
                        return incompleteStateFor(bArr, i3, i2);
                    }
                    int i4 = i3 + 1;
                    b2 = bArr[i3];
                    if (b2 <= (byte) -65 && ((b != (byte) -32 || b2 >= (byte) -96) && (b != (byte) -19 || b2 < (byte) -96))) {
                        i = i4 + 1;
                        if (bArr[i4] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (i3 >= i2 - 2) {
                    return incompleteStateFor(bArr, i3, i2);
                } else {
                    int i5 = i3 + 1;
                    b2 = bArr[i3];
                    if (b2 <= (byte) -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                        i = i5 + 1;
                        if (bArr[i5] <= (byte) -65) {
                            i3 = i + 1;
                            if (bArr[i] > (byte) -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            i = i3;
        }
        return 0;
    }

    private static int incompleteStateFor(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return incompleteStateFor(i3);
            case 1:
                return incompleteStateFor(i3, bArr[i]);
            case 2:
                return incompleteStateFor(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }
}
