package com.google.a.f.b;

import com.google.a.f.a.a;
import com.google.a.f.a.b;
import com.google.a.h;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class c {
    private static final int[] a = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    public static f a(String content, a ecLevel, Map<com.google.a.c, ?> hints) throws h {
        b mode;
        int i;
        int i2;
        int charAt;
        com.google.a.f.a.c version;
        String encoding = "ISO-8859-1";
        boolean hasEncodingHint = hints != null && hints.containsKey(com.google.a.c.CHARACTER_SET);
        if (hasEncodingHint) {
            encoding = hints.get(com.google.a.c.CHARACTER_SET).toString();
        }
        if ("Shift_JIS".equals(encoding) && a(content)) {
            mode = b.KANJI;
        } else {
            Object obj = null;
            i = 0;
            for (i2 = 0; i2 < content.length(); i2++) {
                charAt = content.charAt(i2);
                if (charAt < '0' || charAt > '9') {
                    i = a(charAt);
                    if (i == -1) {
                        mode = b.BYTE;
                        break;
                    }
                }
                obj = 1;
            }
            if (i != 0) {
                mode = b.ALPHANUMERIC;
            } else if (obj != null) {
                mode = b.NUMERIC;
            } else {
                mode = b.BYTE;
            }
        }
        com.google.a.b.a headerBits = new com.google.a.b.a();
        if (mode == b.BYTE && (hasEncodingHint || !"ISO-8859-1".equals(encoding))) {
            com.google.a.b.c eci = com.google.a.b.c.a(encoding);
            if (eci != null) {
                headerBits.a(b.ECI.a(), 4);
                headerBits.a(eci.a(), 8);
            }
        }
        Object obj2 = (hints == null || !hints.containsKey(com.google.a.c.GS1_FORMAT)) ? null : 1;
        if (obj2 != null && Boolean.valueOf(hints.get(com.google.a.c.GS1_FORMAT).toString()).booleanValue()) {
            a(b.FNC1_FIRST_POSITION, headerBits);
        }
        a(mode, headerBits);
        com.google.a.b.a dataBits = new com.google.a.b.a();
        int charAt2;
        switch (mode) {
            case NUMERIC:
                i = content.length();
                i2 = 0;
                while (i2 < i) {
                    charAt2 = content.charAt(i2) - 48;
                    if (i2 + 2 < i) {
                        dataBits.a(((charAt2 * 100) + ((content.charAt(i2 + 1) - 48) * 10)) + (content.charAt(i2 + 2) - 48), 10);
                        i2 += 3;
                    } else if (i2 + 1 < i) {
                        dataBits.a((charAt2 * 10) + (content.charAt(i2 + 1) - 48), 7);
                        i2 += 2;
                    } else {
                        dataBits.a(charAt2, 4);
                        i2++;
                    }
                }
                break;
            case ALPHANUMERIC:
                i = content.length();
                i2 = 0;
                while (i2 < i) {
                    charAt2 = a(content.charAt(i2));
                    if (charAt2 == -1) {
                        throw new h();
                    } else if (i2 + 1 < i) {
                        charAt = a(content.charAt(i2 + 1));
                        if (charAt == -1) {
                            throw new h();
                        }
                        dataBits.a((charAt2 * 45) + charAt, 11);
                        i2 += 2;
                    } else {
                        dataBits.a(charAt2, 6);
                        i2++;
                    }
                }
                break;
            case BYTE:
                try {
                    for (byte a : content.getBytes(encoding)) {
                        dataBits.a(a, 8);
                    }
                    break;
                } catch (Throwable e) {
                    throw new h(e);
                }
            case KANJI:
                try {
                    byte[] bytes = content.getBytes("Shift_JIS");
                    charAt = bytes.length;
                    i2 = 0;
                    while (true) {
                        i = i2;
                        if (i >= charAt) {
                            break;
                        }
                        int i3 = (bytes[i + 1] & 255) | ((bytes[i] & 255) << 8);
                        i2 = -1;
                        if (i3 >= 33088 && i3 <= 40956) {
                            i2 = i3 - 33088;
                        } else if (i3 >= 57408 && i3 <= 60351) {
                            i2 = i3 - 49472;
                        }
                        if (i2 == -1) {
                            throw new h("Invalid byte sequence");
                        }
                        dataBits.a((i2 & 255) + ((i2 >> 8) * 192), 13);
                        i2 = i + 2;
                    }
                } catch (Throwable e2) {
                    throw new h(e2);
                }
                break;
            default:
                throw new h("Invalid mode: " + mode);
        }
        if (hints == null || !hints.containsKey(com.google.a.c.QR_VERSION)) {
            version = a(a(mode, headerBits, dataBits, a(a(mode, headerBits, dataBits, com.google.a.f.a.c.a(1)), ecLevel)), ecLevel);
        } else {
            version = com.google.a.f.a.c.a(Integer.parseInt(hints.get(com.google.a.c.QR_VERSION).toString()));
            if (!a(a(mode, headerBits, dataBits, version), version, ecLevel)) {
                throw new h("Data too big for requested version");
            }
        }
        com.google.a.b.a headerAndDataBits = new com.google.a.b.a();
        headerAndDataBits.a(headerBits);
        if (mode == b.BYTE) {
            i2 = dataBits.b();
        } else {
            i2 = content.length();
        }
        i = mode.a(version);
        if (i2 >= (1 << i)) {
            throw new h(i2 + " is bigger than " + ((1 << i) - 1));
        }
        headerAndDataBits.a(i2, i);
        headerAndDataBits.a(dataBits);
        com.google.a.f.a.c.b ecBlocks = version.a(ecLevel);
        int numDataBytes = version.b() - ecBlocks.c();
        a(numDataBytes, headerAndDataBits);
        com.google.a.b.a finalBits = a(headerAndDataBits, version.b(), numDataBytes, ecBlocks.b());
        f qrCode = new f();
        qrCode.a(ecLevel);
        qrCode.a(mode);
        qrCode.a(version);
        int dimension = version.c();
        b matrix = new b(dimension, dimension);
        int maskPattern = a(finalBits, ecLevel, version, matrix);
        qrCode.a(maskPattern);
        e.a(finalBits, ecLevel, version, maskPattern, matrix);
        qrCode.a(matrix);
        return qrCode;
    }

    private static int a(b mode, com.google.a.b.a headerBits, com.google.a.b.a dataBits, com.google.a.f.a.c version) {
        return (headerBits.a() + mode.a(version)) + dataBits.a();
    }

    private static int a(int code) {
        if (code < a.length) {
            return a[code];
        }
        return -1;
    }

    private static boolean a(String content) {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int byte1 = bytes[i] & 255;
                if ((byte1 < 129 || byte1 > 159) && (byte1 < 224 || byte1 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int a(com.google.a.b.a bits, a ecLevel, com.google.a.f.a.c version, b matrix) throws h {
        int minPenalty = Integer.MAX_VALUE;
        int bestMaskPattern = -1;
        for (int maskPattern = 0; maskPattern < 8; maskPattern++) {
            int i;
            byte[] bArr;
            e.a(bits, ecLevel, version, maskPattern, matrix);
            int a = d.a(matrix);
            int i2 = 0;
            byte[][] c = matrix.c();
            int b = matrix.b();
            int a2 = matrix.a();
            int i3 = 0;
            while (true) {
                i = i3;
                if (i >= a2 - 1) {
                    break;
                }
                bArr = c[i];
                i3 = i2;
                i2 = 0;
                while (i2 < b - 1) {
                    byte b2 = bArr[i2];
                    if (b2 == bArr[i2 + 1] && b2 == c[i + 1][i2] && b2 == c[i + 1][i2 + 1]) {
                        i3++;
                    }
                    i2++;
                }
                i2 = i + 1;
                i = i2;
            }
            a = ((i2 * 3) + a) + d.b(matrix);
            i2 = 0;
            c = matrix.c();
            b = matrix.b();
            a2 = matrix.a();
            i = 0;
            while (i < a2) {
                bArr = c[i];
                i3 = i2;
                for (i2 = 0; i2 < b; i2++) {
                    if (bArr[i2] == (byte) 1) {
                        i3++;
                    }
                }
                i++;
                i2 = i3;
            }
            i3 = matrix.a() * matrix.b();
            int penalty = a + (((Math.abs((i2 << 1) - i3) * 10) / i3) * 10);
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestMaskPattern = maskPattern;
            }
        }
        return bestMaskPattern;
    }

    private static com.google.a.f.a.c a(int numInputBits, a ecLevel) throws h {
        for (int versionNum = 1; versionNum <= 40; versionNum++) {
            com.google.a.f.a.c version = com.google.a.f.a.c.a(versionNum);
            if (a(numInputBits, version, ecLevel)) {
                return version;
            }
        }
        throw new h("Data too big");
    }

    private static boolean a(int numInputBits, com.google.a.f.a.c version, a ecLevel) {
        return version.b() - version.a(ecLevel).c() >= (numInputBits + 7) / 8;
    }

    private static void a(int numDataBytes, com.google.a.b.a bits) throws h {
        int capacity = numDataBytes << 3;
        if (bits.a() > capacity) {
            throw new h("data bits cannot fit in the QR Code" + bits.a() + " > " + capacity);
        }
        int i;
        for (i = 0; i < 4 && bits.a() < capacity; i++) {
            bits.a(false);
        }
        int numBitsInLastByte = bits.a() & 7;
        if (numBitsInLastByte > 0) {
            for (i = numBitsInLastByte; i < 8; i++) {
                bits.a(false);
            }
        }
        int numPaddingBytes = numDataBytes - bits.b();
        for (i = 0; i < numPaddingBytes; i++) {
            bits.a((i & 1) == 0 ? 236 : 17, 8);
        }
        if (bits.a() != capacity) {
            throw new h("Bits size does not equal capacity");
        }
    }

    private static com.google.a.b.a a(com.google.a.b.a bits, int numTotalBytes, int numDataBytes, int numRSBlocks) throws h {
        if (bits.b() != numDataBytes) {
            throw new h("Number of bits and data bytes does not match");
        }
        byte[] dataBytes;
        byte[] ecBytes;
        int dataBytesOffset = 0;
        int maxNumDataBytes = 0;
        int maxNumEcBytes = 0;
        Collection<a> blocks = new ArrayList(numRSBlocks);
        int i = 0;
        while (i < numRSBlocks) {
            int[] numDataBytesInBlock = new int[1];
            int[] numEcBytesInBlock = new int[1];
            if (i >= numRSBlocks) {
                throw new h("Block ID too large");
            }
            int i2 = numTotalBytes % numRSBlocks;
            int i3 = numRSBlocks - i2;
            int i4 = numTotalBytes / numRSBlocks;
            int i5 = i4 + 1;
            int i6 = numDataBytes / numRSBlocks;
            int i7 = i6 + 1;
            i4 -= i6;
            i5 -= i7;
            if (i4 != i5) {
                throw new h("EC bytes mismatch");
            } else if (numRSBlocks != i3 + i2) {
                throw new h("RS blocks mismatch");
            } else {
                if (numTotalBytes != (i2 * (i7 + i5)) + ((i6 + i4) * i3)) {
                    throw new h("Total bytes mismatch");
                }
                if (i < i3) {
                    numDataBytesInBlock[0] = i6;
                    numEcBytesInBlock[0] = i4;
                } else {
                    numDataBytesInBlock[0] = i7;
                    numEcBytesInBlock[0] = i5;
                }
                int size = numDataBytesInBlock[0];
                dataBytes = new byte[size];
                bits.a(dataBytesOffset << 3, dataBytes, size);
                ecBytes = a(dataBytes, numEcBytesInBlock[0]);
                blocks.add(new a(dataBytes, ecBytes));
                maxNumDataBytes = Math.max(maxNumDataBytes, size);
                maxNumEcBytes = Math.max(maxNumEcBytes, ecBytes.length);
                dataBytesOffset += numDataBytesInBlock[0];
                i++;
            }
        }
        if (numDataBytes != dataBytesOffset) {
            throw new h("Data bytes does not match offset");
        }
        com.google.a.b.a result = new com.google.a.b.a();
        for (i = 0; i < maxNumDataBytes; i++) {
            for (a a : blocks) {
                dataBytes = a.a();
                if (i < dataBytes.length) {
                    result.a(dataBytes[i], 8);
                }
            }
        }
        for (i = 0; i < maxNumEcBytes; i++) {
            for (a a2 : blocks) {
                ecBytes = a2.b();
                if (i < ecBytes.length) {
                    result.a(ecBytes[i], 8);
                }
            }
        }
        if (numTotalBytes == result.b()) {
            return result;
        }
        throw new h("Interleaving error: " + numTotalBytes + " and " + result.b() + " differ.");
    }

    private static byte[] a(byte[] dataBytes, int numEcBytesInBlock) {
        int i;
        int numDataBytes = dataBytes.length;
        int[] toEncode = new int[(numDataBytes + numEcBytesInBlock)];
        for (i = 0; i < numDataBytes; i++) {
            toEncode[i] = dataBytes[i] & 255;
        }
        new com.google.a.b.a.c(com.google.a.b.a.a.e).a(toEncode, numEcBytesInBlock);
        byte[] ecBytes = new byte[numEcBytesInBlock];
        for (i = 0; i < numEcBytesInBlock; i++) {
            ecBytes[i] = (byte) toEncode[numDataBytes + i];
        }
        return ecBytes;
    }

    private static void a(b mode, com.google.a.b.a bits) {
        bits.a(mode.a(), 4);
    }
}
