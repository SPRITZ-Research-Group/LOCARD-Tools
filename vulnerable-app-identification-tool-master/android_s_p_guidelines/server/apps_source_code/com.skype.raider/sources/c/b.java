package c;

import java.io.UnsupportedEncodingException;

final class b {
    private static final byte[] a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] b = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};

    public static byte[] a(String in) {
        char c;
        int i;
        int limit = in.length();
        while (limit > 0) {
            c = in.charAt(limit - 1);
            if (c != '=' && c != 10 && c != 13 && c != ' ' && c != 9) {
                break;
            }
            limit--;
        }
        byte[] out = new byte[((int) ((((long) limit) * 6) / 8))];
        int inCount = 0;
        int word = 0;
        int pos = 0;
        int outCount = 0;
        while (pos < limit) {
            int bits;
            c = in.charAt(pos);
            if (c >= 'A' && c <= 'Z') {
                bits = c - 65;
            } else if (c >= 'a' && c <= 'z') {
                bits = c - 71;
            } else if (c >= '0' && c <= '9') {
                bits = c + 4;
            } else if (c == '+' || c == '-') {
                bits = 62;
            } else if (c == '/' || c == '_') {
                bits = 63;
            } else {
                if (!(c == 10 || c == 13 || c == ' ' || c == 9)) {
                    i = outCount;
                    return null;
                }
                i = outCount;
                pos++;
                outCount = i;
            }
            word = (word << 6) | ((byte) bits);
            inCount++;
            if (inCount % 4 == 0) {
                i = outCount + 1;
                out[outCount] = (byte) (word >> 16);
                outCount = i + 1;
                out[i] = (byte) (word >> 8);
                i = outCount + 1;
                out[outCount] = (byte) word;
                pos++;
                outCount = i;
            }
            i = outCount;
            pos++;
            outCount = i;
        }
        int lastWordChars = inCount % 4;
        if (lastWordChars == 1) {
            i = outCount;
            return null;
        }
        if (lastWordChars == 2) {
            i = outCount + 1;
            out[outCount] = (byte) ((word << 12) >> 16);
        } else {
            if (lastWordChars == 3) {
                word <<= 6;
                i = outCount + 1;
                out[outCount] = (byte) (word >> 16);
                outCount = i + 1;
                out[i] = (byte) (word >> 8);
            }
            i = outCount;
        }
        if (i == out.length) {
            return out;
        }
        byte[] prefix = new byte[i];
        System.arraycopy(out, 0, prefix, 0, i);
        return prefix;
    }

    public static String a(byte[] in) {
        return a(in, a);
    }

    private static String a(byte[] in, byte[] map) {
        int i;
        byte[] out = new byte[(((in.length + 2) / 3) * 4)];
        int end = in.length - (in.length % 3);
        int index = 0;
        for (int i2 = 0; i2 < end; i2 += 3) {
            i = index + 1;
            out[index] = map[(in[i2] & 255) >> 2];
            index = i + 1;
            out[i] = map[((in[i2] & 3) << 4) | ((in[i2 + 1] & 255) >> 4)];
            i = index + 1;
            out[index] = map[((in[i2 + 1] & 15) << 2) | ((in[i2 + 2] & 255) >> 6)];
            index = i + 1;
            out[i] = map[in[i2 + 2] & 63];
        }
        switch (in.length % 3) {
            case 1:
                i = index + 1;
                out[index] = map[(in[end] & 255) >> 2];
                index = i + 1;
                out[i] = map[(in[end] & 3) << 4];
                i = index + 1;
                out[index] = (byte) 61;
                out[i] = (byte) 61;
                break;
            case 2:
                i = index + 1;
                out[index] = map[(in[end] & 255) >> 2];
                index = i + 1;
                out[i] = map[((in[end] & 3) << 4) | ((in[end + 1] & 255) >> 4)];
                i = index + 1;
                out[index] = map[(in[end + 1] & 15) << 2];
                out[i] = (byte) 61;
                break;
            default:
                i = index;
                break;
        }
        try {
            return new String(out, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
