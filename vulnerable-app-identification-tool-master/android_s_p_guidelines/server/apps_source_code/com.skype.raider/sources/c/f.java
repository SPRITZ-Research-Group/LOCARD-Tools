package c;

import com.adjust.sdk.Constants;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;

public class f implements Serializable, Comparable<f> {
    static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final f b = a(new byte[0]);
    final byte[] c;
    transient int d;
    transient String e;

    public /* synthetic */ int compareTo(Object obj) {
        f fVar = (f) obj;
        int h = h();
        int h2 = fVar.h();
        int min = Math.min(h, h2);
        int i = 0;
        while (i < min) {
            int a = a(i) & 255;
            int a2 = fVar.a(i) & 255;
            if (a == a2) {
                i++;
            } else if (a < a2) {
                return -1;
            } else {
                return 1;
            }
        }
        if (h == h2) {
            return 0;
        }
        return h >= h2 ? 1 : -1;
    }

    f(byte[] data) {
        this.c = data;
    }

    public static f a(byte... data) {
        if (data != null) {
            return new f((byte[]) data.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static f a(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s == null");
        }
        f byteString = new f(s.getBytes(v.a));
        byteString.e = s;
        return byteString;
    }

    public static f a(String s, Charset charset) {
        if (s == null) {
            throw new IllegalArgumentException("s == null");
        } else if (charset != null) {
            return new f(s.getBytes(charset));
        } else {
            throw new IllegalArgumentException("charset == null");
        }
    }

    public String a() {
        String result = this.e;
        if (result != null) {
            return result;
        }
        result = new String(this.c, v.a);
        this.e = result;
        return result;
    }

    public String b() {
        return b.a(this.c);
    }

    public f c() {
        return d(Constants.MD5);
    }

    public f d() {
        return d(Constants.SHA1);
    }

    public f e() {
        return d(Constants.SHA256);
    }

    private f d(String algorithm) {
        try {
            return a(MessageDigest.getInstance(algorithm).digest(this.c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public static f b(String base64) {
        if (base64 == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] decoded = b.a(base64);
        return decoded != null ? new f(decoded) : null;
    }

    public String f() {
        char[] result = new char[(this.c.length * 2)];
        int c = 0;
        for (byte b : this.c) {
            int i = c + 1;
            result[c] = a[(b >> 4) & 15];
            c = i + 1;
            result[i] = a[b & 15];
        }
        return new String(result);
    }

    public static f c(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + hex);
        }
        byte[] result = new byte[(hex.length() / 2)];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) ((a(hex.charAt(i * 2)) << 4) + a(hex.charAt((i * 2) + 1)));
        }
        return a(result);
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public f g() {
        int i = 0;
        while (i < this.c.length) {
            byte c = this.c[i];
            if (c < (byte) 65 || c > (byte) 90) {
                i++;
            } else {
                byte[] lowercase = (byte[]) this.c.clone();
                int i2 = i + 1;
                lowercase[i] = (byte) (c + 32);
                for (i = i2; i < lowercase.length; i++) {
                    c = lowercase[i];
                    if (c >= (byte) 65 && c <= (byte) 90) {
                        lowercase[i] = (byte) (c + 32);
                    }
                }
                return new f(lowercase);
            }
        }
        return this;
    }

    public f a(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (endIndex > this.c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        } else {
            int subLen = endIndex - beginIndex;
            if (subLen < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (beginIndex == 0 && endIndex == this.c.length) {
                return this;
            } else {
                byte[] copy = new byte[subLen];
                System.arraycopy(this.c, beginIndex, copy, 0, subLen);
                this(copy);
                return this;
            }
        }
    }

    public byte a(int pos) {
        return this.c[pos];
    }

    public int h() {
        return this.c.length;
    }

    public byte[] i() {
        return (byte[]) this.c.clone();
    }

    void a(c buffer) {
        buffer.b(this.c, 0, this.c.length);
    }

    public boolean a(int offset, f other, int otherOffset, int byteCount) {
        return other.a(0, this.c, 0, byteCount);
    }

    public boolean a(int offset, byte[] other, int otherOffset, int byteCount) {
        return offset >= 0 && offset <= this.c.length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && v.a(this.c, offset, other, otherOffset, byteCount);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof f) && ((f) o).h() == this.c.length && ((f) o).a(0, this.c, 0, this.c.length);
    }

    public int hashCode() {
        int result = this.d;
        if (result != 0) {
            return result;
        }
        result = Arrays.hashCode(this.c);
        this.d = result;
        return result;
    }

    public String toString() {
        if (this.c.length == 0) {
            return "[size=0]";
        }
        String text = a();
        int length = text.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            if (i != 64) {
                int codePointAt = text.codePointAt(i2);
                if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                    i2 = -1;
                    break;
                }
                i++;
                i2 += Character.charCount(codePointAt);
            } else {
                break;
            }
        }
        i2 = text.length();
        if (i2 != -1) {
            String safeText = text.substring(0, i2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i2 < text.length()) {
                return "[size=" + this.c.length + " text=" + safeText + "…]";
            }
            return "[text=" + safeText + "]";
        } else if (this.c.length <= 64) {
            return "[hex=" + f() + "]";
        } else {
            return "[size=" + this.c.length + " hex=" + a(0, 64).f() + "…]";
        }
    }
}
