package com.google.a.e.a;

import com.google.a.b.c;
import com.google.a.h;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class g {
    private static final byte[] a = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 38, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 35, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 43, (byte) 37, (byte) 42, (byte) 61, (byte) 94, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] b = new byte[]{(byte) 59, (byte) 60, (byte) 62, (byte) 64, (byte) 91, (byte) 92, (byte) 93, (byte) 95, (byte) 96, (byte) 126, (byte) 33, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 10, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 34, (byte) 124, (byte) 42, (byte) 40, (byte) 41, (byte) 63, (byte) 123, (byte) 125, (byte) 39, (byte) 0};
    private static final byte[] c = new byte[128];
    private static final byte[] d = new byte[128];
    private static final Charset e = StandardCharsets.ISO_8859_1;

    static {
        int i;
        byte b;
        Arrays.fill(c, (byte) -1);
        for (i = 0; i < a.length; i++) {
            b = a[i];
            if (b > (byte) 0) {
                c[b] = (byte) i;
            }
        }
        Arrays.fill(d, (byte) -1);
        for (i = 0; i < b.length; i++) {
            b = b[i];
            if (b > (byte) 0) {
                d[b] = (byte) i;
            }
        }
    }

    static String a(String msg, c compaction, Charset encoding) throws h {
        StringBuilder sb = new StringBuilder(msg.length());
        if (encoding == null) {
            encoding = e;
        } else if (!e.equals(encoding)) {
            c eci = c.a(encoding.name());
            if (eci != null) {
                int a = eci.a();
                if (a >= 0 && a < 900) {
                    sb.append(927);
                    sb.append((char) a);
                } else if (a < 810900) {
                    sb.append(926);
                    sb.append((char) ((a / 900) - 1));
                    sb.append((char) (a % 900));
                } else if (a < 811800) {
                    sb.append(925);
                    sb.append((char) (810900 - a));
                } else {
                    throw new h("ECI number not in valid range from 0..811799, but was " + a);
                }
            }
        }
        int len = msg.length();
        int p = 0;
        int textSubMode = 0;
        switch (compaction) {
            case TEXT:
                a(msg, 0, len, sb, 0);
                break;
            case BYTE:
                byte[] bytes = msg.getBytes(encoding);
                a(bytes, bytes.length, 1, sb);
                break;
            case NUMERIC:
                sb.append(902);
                a(msg, 0, len, sb);
                break;
            default:
                int encodingMode = 0;
                while (p < len) {
                    int n = a(msg, p);
                    if (n >= 13) {
                        sb.append(902);
                        encodingMode = 2;
                        textSubMode = 0;
                        a(msg, p, n, sb);
                        p += n;
                    } else {
                        int t = b(msg, p);
                        if (t >= 5 || n == len) {
                            if (encodingMode != 0) {
                                sb.append(900);
                                encodingMode = 0;
                                textSubMode = 0;
                            }
                            textSubMode = a(msg, p, t, sb, textSubMode);
                            p += t;
                        } else {
                            int b = a(msg, p, encoding);
                            if (b == 0) {
                                b = 1;
                            }
                            byte[] bytes2 = msg.substring(p, p + b).getBytes(encoding);
                            if (bytes2.length == 1 && encodingMode == 0) {
                                a(bytes2, 1, 0, sb);
                            } else {
                                a(bytes2, bytes2.length, encodingMode, sb);
                                encodingMode = 1;
                                textSubMode = 0;
                            }
                            p += b;
                        }
                    }
                }
                break;
        }
        return sb.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(CharSequence msg, int startpos, int count, StringBuilder sb, int initialSubmode) {
        StringBuilder tmp = new StringBuilder(count);
        int submode = initialSubmode;
        int idx = 0;
        while (true) {
            char ch = msg.charAt(startpos + idx);
            switch (submode) {
                case 0:
                    if (b(ch)) {
                        if (ch == ' ') {
                            tmp.append(26);
                        } else {
                            tmp.append((char) (ch - 65));
                        }
                    } else if (c(ch)) {
                        submode = 1;
                        tmp.append(27);
                        break;
                    } else if (d(ch)) {
                        submode = 2;
                        tmp.append(28);
                        break;
                    } else {
                        tmp.append(29);
                        tmp.append((char) d[ch]);
                    }
                case 1:
                    if (c(ch)) {
                        if (ch == ' ') {
                            tmp.append(26);
                        } else {
                            tmp.append((char) (ch - 97));
                        }
                    } else if (b(ch)) {
                        tmp.append(27);
                        tmp.append((char) (ch - 65));
                    } else if (d(ch)) {
                        submode = 2;
                        tmp.append(28);
                        break;
                    } else {
                        tmp.append(29);
                        tmp.append((char) d[ch]);
                    }
                case 2:
                    if (!d(ch)) {
                        if (!b(ch)) {
                            if (!c(ch)) {
                                if ((startpos + idx) + 1 < count && e(msg.charAt((startpos + idx) + 1))) {
                                    submode = 3;
                                    tmp.append(25);
                                    break;
                                }
                                tmp.append(29);
                                tmp.append((char) d[ch]);
                            } else {
                                submode = 1;
                                tmp.append(27);
                                break;
                            }
                        }
                        submode = 0;
                        tmp.append(28);
                        break;
                    }
                    tmp.append((char) c[ch]);
                    break;
                default:
                    if (!e(ch)) {
                        submode = 0;
                        tmp.append(29);
                        break;
                    }
                    tmp.append((char) d[ch]);
                    idx++;
                    if (idx < count) {
                        break;
                    }
                    char h = 0;
                    int len = tmp.length();
                    for (int i = 0; i < len; i++) {
                        if ((i % 2 != 0 ? 1 : null) != null) {
                            h = (char) ((h * 30) + tmp.charAt(i));
                            sb.append(h);
                        } else {
                            h = tmp.charAt(i);
                        }
                    }
                    if (len % 2 != 0) {
                        sb.append((char) ((h * 30) + 29));
                    }
                    return submode;
            }
        }
    }

    private static void a(byte[] bytes, int count, int startmode, StringBuilder sb) {
        int i;
        if (count == 1 && startmode == 0) {
            sb.append(913);
        } else if (count % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        int idx = 0;
        if (count >= 6) {
            char[] chars = new char[5];
            while ((count + 0) - idx >= 6) {
                long t = 0;
                for (i = 0; i < 6; i++) {
                    t = (t << 8) + ((long) (bytes[idx + i] & 255));
                }
                for (i = 0; i < 5; i++) {
                    chars[i] = (char) ((int) (t % 900));
                    t /= 900;
                }
                for (i = 4; i >= 0; i--) {
                    sb.append(chars[i]);
                }
                idx += 6;
            }
        }
        for (i = idx; i < count + 0; i++) {
            sb.append((char) (bytes[i] & 255));
        }
    }

    private static void a(String msg, int startpos, int count, StringBuilder sb) {
        int idx = 0;
        StringBuilder tmp = new StringBuilder((count / 3) + 1);
        BigInteger num900 = BigInteger.valueOf(900);
        BigInteger num0 = BigInteger.valueOf(0);
        while (idx < count) {
            tmp.setLength(0);
            int len = Math.min(44, count - idx);
            BigInteger bigint = new BigInteger("1" + msg.substring(startpos + idx, (startpos + idx) + len));
            do {
                tmp.append((char) bigint.mod(num900).intValue());
                bigint = bigint.divide(num900);
            } while (!bigint.equals(num0));
            for (int i = tmp.length() - 1; i >= 0; i--) {
                sb.append(tmp.charAt(i));
            }
            idx += len;
        }
    }

    private static boolean a(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean b(char ch) {
        return ch == ' ' || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean c(char ch) {
        return ch == ' ' || (ch >= 'a' && ch <= 'z');
    }

    private static boolean d(char ch) {
        return c[ch] != (byte) -1;
    }

    private static boolean e(char ch) {
        return d[ch] != (byte) -1;
    }

    private static int a(CharSequence msg, int startpos) {
        int count = 0;
        int len = msg.length();
        int idx = startpos;
        if (startpos < len) {
            char ch = msg.charAt(startpos);
            while (a(ch) && idx < len) {
                count++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
        }
        return count;
    }

    private static int b(CharSequence msg, int startpos) {
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && a(ch) && idx < len) {
                numericCount++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
            if (numericCount >= 13) {
                return (idx - startpos) - numericCount;
            }
            if (numericCount <= 0) {
                Object obj;
                char charAt = msg.charAt(idx);
                if (charAt == 9 || charAt == 10 || charAt == 13 || (charAt >= ' ' && charAt <= '~')) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    break;
                }
                idx++;
            }
        }
        return idx - startpos;
    }

    private static int a(String msg, int startpos, Charset encoding) throws h {
        CharsetEncoder encoder = encoding.newEncoder();
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && a(ch)) {
                numericCount++;
                int i = idx + numericCount;
                if (i >= len) {
                    break;
                }
                ch = msg.charAt(i);
            }
            if (numericCount >= 13) {
                return idx - startpos;
            }
            ch = msg.charAt(idx);
            if (encoder.canEncode(ch)) {
                idx++;
            } else {
                throw new h("Non-encodable character detected: " + ch + " (Unicode: " + ch + ')');
            }
        }
        return idx - startpos;
    }
}
