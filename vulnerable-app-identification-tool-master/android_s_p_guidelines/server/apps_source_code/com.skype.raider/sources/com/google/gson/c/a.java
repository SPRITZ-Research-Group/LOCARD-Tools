package com.google.gson.c;

import com.google.gson.a.e;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        e.a = new e() {
            public final void a(a reader) throws IOException {
                if (reader instanceof com.google.gson.a.a.e) {
                    ((com.google.gson.a.a.e) reader).o();
                    return;
                }
                int p = reader.a;
                if (p == 0) {
                    p = reader.r();
                }
                if (p == 13) {
                    reader.a = 9;
                } else if (p == 12) {
                    reader.a = 8;
                } else if (p == 14) {
                    reader.a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + reader.f() + reader.w());
                }
            }
        };
    }

    public a(Reader in) {
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        this.c = in;
    }

    public final void a(boolean lenient) {
        this.d = lenient;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + w());
    }

    public void b() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 4) {
            this.n--;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + w());
    }

    public void c() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 1) {
            a(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + w());
    }

    public void d() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + w());
    }

    public boolean e() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        return (p == 2 || p == 4) ? false : true;
    }

    public b f() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        switch (p) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    final int r() throws IOException {
        int peekStack = this.m[this.n - 1];
        if (peekStack == 1) {
            this.m[this.n - 1] = 2;
        } else if (peekStack == 2) {
            switch (b(true)) {
                case 44:
                    break;
                case 59:
                    u();
                    break;
                case 93:
                    this.a = 4;
                    return 4;
                default:
                    throw a("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            this.m[this.n - 1] = 4;
            if (peekStack == 5) {
                switch (b(true)) {
                    case 44:
                        break;
                    case 59:
                        u();
                        break;
                    case 125:
                        this.a = 2;
                        return 2;
                    default:
                        throw a("Unterminated object");
                }
            }
            int c = b(true);
            switch (c) {
                case 34:
                    this.a = 13;
                    return 13;
                case 39:
                    u();
                    this.a = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.a = 2;
                        return 2;
                    }
                    throw a("Expected name");
                default:
                    u();
                    this.f--;
                    if (a((char) c)) {
                        this.a = 14;
                        return 14;
                    }
                    throw a("Expected name");
            }
        } else if (peekStack == 4) {
            this.m[this.n - 1] = 5;
            switch (b(true)) {
                case 58:
                    break;
                case 61:
                    u();
                    if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                        this.f++;
                        break;
                    }
                default:
                    throw a("Expected ':'");
            }
        } else if (peekStack == 6) {
            if (this.d) {
                b(true);
                this.f--;
                if (this.f + b.length <= this.g || b(b.length)) {
                    for (int i = 0; i < b.length; i++) {
                        if (this.e[this.f + i] != b[i]) {
                            break;
                        }
                    }
                    this.f += b.length;
                }
            }
            this.m[this.n - 1] = 7;
        } else if (peekStack == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            u();
            this.f--;
        } else if (peekStack == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (b(true)) {
            case 34:
                this.a = 9;
                return 9;
            case 39:
                u();
                this.a = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.a = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.a = 4;
                    return 4;
                }
                break;
            case 123:
                this.a = 1;
                return 1;
            default:
                this.f--;
                int result = o();
                if (result != 0) {
                    return result;
                }
                result = s();
                if (result != 0) {
                    return result;
                }
                if (a(this.e[this.f])) {
                    u();
                    this.a = 10;
                    return 10;
                }
                throw a("Expected value");
        }
        if (peekStack == 1 || peekStack == 2) {
            u();
            this.f--;
            this.a = 7;
            return 7;
        }
        throw a("Unexpected value");
    }

    private int o() throws IOException {
        String keyword;
        int peeking;
        char c = this.e[this.f];
        String keywordUpper;
        if (c == 't' || c == 'T') {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 'f' || c == 'F') {
            keyword = "false";
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        int i = 1;
        while (i < length) {
            if (this.f + i >= this.g && !b(i + 1)) {
                return 0;
            }
            c = this.e[this.f + i];
            if (c != keyword.charAt(i) && c != keywordUpper.charAt(i)) {
                return 0;
            }
            i++;
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = peeking;
        return peeking;
    }

    private int s() throws IOException {
        char c;
        char[] buffer = this.e;
        int p = this.f;
        int l = this.g;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            if (p + i == l) {
                if (i == buffer.length) {
                    return 0;
                }
                if (b(i + 1)) {
                    p = this.f;
                    l = this.g;
                }
            }
            c = buffer[p + i];
            switch (c) {
                case '+':
                    if (last != 5) {
                        return 0;
                    }
                    last = 6;
                    continue;
                case '-':
                    if (last == 0) {
                        negative = true;
                        last = 1;
                        continue;
                    } else if (last == 5) {
                        last = 6;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (last != 2) {
                        return 0;
                    }
                    last = 3;
                    continue;
                case 'E':
                case 'e':
                    if (last != 2 && last != 4) {
                        return 0;
                    }
                    last = 5;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (last != 1 && last != 0) {
                            if (last != 2) {
                                if (last != 3) {
                                    if (last != 5 && last != 6) {
                                        break;
                                    }
                                    last = 7;
                                    break;
                                }
                                last = 4;
                                break;
                            } else if (value != 0) {
                                long newValue = (10 * value) - ((long) (c - 48));
                                int i2 = (value > -922337203685477580L || (value == -922337203685477580L && newValue < value)) ? 1 : 0;
                                fitsInLong &= i2;
                                value = newValue;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        value = (long) (-(c - 48));
                        last = 2;
                        continue;
                    } else {
                        break;
                    }
                    break;
            }
            i++;
        }
        if (a(c)) {
            return 0;
        }
        if (last == 2 && fitsInLong && (value != Long.MIN_VALUE || negative)) {
            if (!negative) {
                value = -value;
            }
            this.j = value;
            this.f += i;
            this.a = 15;
            return 15;
        } else if (last != 2 && last != 4 && last != 7) {
            return 0;
        } else {
            this.k = i;
            this.a = 16;
            return 16;
        }
    }

    private boolean a(char c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                u();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String result;
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 14) {
            result = t();
        } else if (p == 12) {
            result = b('\'');
        } else if (p == 13) {
            result = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + w());
        }
        this.a = 0;
        this.o[this.n - 1] = result;
        return result;
    }

    public String h() throws IOException {
        String result;
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 10) {
            result = t();
        } else if (p == 8) {
            result = b('\'');
        } else if (p == 9) {
            result = b('\"');
        } else if (p == 11) {
            result = this.l;
            this.l = null;
        } else if (p == 15) {
            result = Long.toString(this.j);
        } else if (p == 16) {
            result = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + w());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean i() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 5) {
            this.a = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + f() + w());
        }
    }

    public void j() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        if (p == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + w());
    }

    public double k() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        int[] iArr;
        int i;
        if (p == 15) {
            this.a = 0;
            iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return (double) this.j;
        }
        if (p == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (p == 8 || p == 9) {
            this.l = b(p == 8 ? '\'' : '\"');
        } else if (p == 10) {
            this.l = t();
        } else if (p != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + w());
        }
        this.a = 11;
        double result = Double.parseDouble(this.l);
        if (this.d || !(Double.isNaN(result) || Double.isInfinite(result))) {
            this.l = null;
            this.a = 0;
            iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return result;
        }
        throw new d("JSON forbids NaN and infinities: " + result + w());
    }

    public long l() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        int[] iArr;
        int i;
        if (p == 15) {
            this.a = 0;
            iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        long parseLong;
        if (p == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.l = t();
            } else {
                this.l = b(p == 8 ? '\'' : '\"');
            }
            try {
                parseLong = Long.parseLong(this.l);
                this.a = 0;
                iArr = this.p;
                i = this.n - 1;
                iArr[i] = iArr[i] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + w());
        }
        this.a = 11;
        double asDouble = Double.parseDouble(this.l);
        parseLong = (long) asDouble;
        if (((double) parseLong) != asDouble) {
            throw new NumberFormatException("Expected a long but was " + this.l + w());
        }
        this.l = null;
        this.a = 0;
        iArr = this.p;
        i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return parseLong;
    }

    private String b(char quote) throws IOException {
        char[] buffer = this.e;
        StringBuilder builder = new StringBuilder();
        while (true) {
            int p = this.f;
            int l = this.g;
            int start = p;
            int p2 = p;
            while (p2 < l) {
                p = p2 + 1;
                char c = buffer[p2];
                if (c == quote) {
                    this.f = p;
                    builder.append(buffer, start, (p - start) - 1);
                    return builder.toString();
                } else if (c == '\\') {
                    this.f = p;
                    builder.append(buffer, start, (p - start) - 1);
                    builder.append(x());
                    break;
                } else {
                    if (c == 10) {
                        this.h++;
                        this.i = p;
                    }
                    p2 = p;
                }
            }
            builder.append(buffer, start, p2 - start);
            this.f = p2;
            if (!b(1)) {
                throw a("Unterminated string");
            }
        }
    }

    private String t() throws IOException {
        String result;
        StringBuilder builder = null;
        int i = 0;
        while (true) {
            if (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        u();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.e.length) {
                if (builder == null) {
                    builder = new StringBuilder();
                }
                builder.append(this.e, this.f, i);
                this.f += i;
                i = 0;
                if (b(1)) {
                }
            } else if (b(i + 1)) {
            }
        }
        if (builder == null) {
            result = new String(this.e, this.f, i);
        } else {
            builder.append(this.e, this.f, i);
            result = builder.toString();
        }
        this.f += i;
        return result;
    }

    private void c(char quote) throws IOException {
        char[] buffer = this.e;
        while (true) {
            int p = this.f;
            int l = this.g;
            int p2 = p;
            while (p2 < l) {
                p = p2 + 1;
                char c = buffer[p2];
                if (c == quote) {
                    this.f = p;
                    return;
                } else if (c == '\\') {
                    this.f = p;
                    x();
                    break;
                } else {
                    if (c == 10) {
                        this.h++;
                        this.i = p;
                    }
                    p2 = p;
                }
            }
            this.f = p2;
            if (!b(1)) {
                throw a("Unterminated string");
            }
        }
    }

    public int m() throws IOException {
        int p = this.a;
        if (p == 0) {
            p = r();
        }
        int result;
        int[] iArr;
        int i;
        if (p == 15) {
            result = (int) this.j;
            if (this.j != ((long) result)) {
                throw new NumberFormatException("Expected an int but was " + this.j + w());
            }
            this.a = 0;
            iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return result;
        }
        if (p == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.l = t();
            } else {
                this.l = b(p == 8 ? '\'' : '\"');
            }
            try {
                result = Integer.parseInt(this.l);
                this.a = 0;
                iArr = this.p;
                i = this.n - 1;
                iArr[i] = iArr[i] + 1;
                return result;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + f() + w());
        }
        this.a = 11;
        double asDouble = Double.parseDouble(this.l);
        result = (int) asDouble;
        if (((double) result) != asDouble) {
            throw new NumberFormatException("Expected an int but was " + this.l + w());
        }
        this.l = null;
        this.a = 0;
        iArr = this.p;
        i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() throws IOException {
        int count = 0;
        do {
            int p = this.a;
            if (p == 0) {
                p = r();
            }
            if (p == 3) {
                a(1);
                count++;
            } else if (p == 1) {
                a(3);
                count++;
            } else if (p == 4) {
                this.n--;
                count--;
            } else if (p == 2) {
                this.n--;
                count--;
            } else if (p == 14 || p == 10) {
                do {
                    int i = 0;
                    while (this.f + i < this.g) {
                        switch (this.e[this.f + i]) {
                            case 9:
                            case 10:
                            case 12:
                            case 13:
                            case ' ':
                            case ',':
                            case ':':
                            case '[':
                            case ']':
                            case '{':
                            case '}':
                                break;
                            case '#':
                            case '/':
                            case ';':
                            case '=':
                            case '\\':
                                u();
                                break;
                            default:
                                i++;
                        }
                        this.f = i + this.f;
                    }
                    this.f = i + this.f;
                } while (b(1));
            } else if (p == 8 || p == 12) {
                c('\'');
            } else if (p == 9 || p == 13) {
                c('\"');
            } else if (p == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (count != 0);
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int newTop) {
        if (this.n == this.m.length) {
            int[] newStack = new int[(this.n * 2)];
            int[] newPathIndices = new int[(this.n * 2)];
            String[] newPathNames = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, newStack, 0, this.n);
            System.arraycopy(this.p, 0, newPathIndices, 0, this.n);
            System.arraycopy(this.o, 0, newPathNames, 0, this.n);
            this.m = newStack;
            this.p = newPathIndices;
            this.o = newPathNames;
        }
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = newTop;
    }

    private boolean b(int minimum) throws IOException {
        char[] buffer = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(buffer, this.f, buffer, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int total = this.c.read(buffer, this.g, buffer.length - this.g);
            if (total == -1) {
                return false;
            }
            this.g += total;
            if (this.h == 0 && this.i == 0 && this.g > 0 && buffer[0] == 65279) {
                this.f++;
                this.i++;
                minimum++;
            }
        } while (this.g < minimum);
        return true;
    }

    private int b(boolean throwOnEof) throws IOException {
        char[] buffer = this.e;
        int p = this.f;
        int l = this.g;
        while (true) {
            if (p == l) {
                this.f = p;
                if (b(1)) {
                    p = this.f;
                    l = this.g;
                } else if (!throwOnEof) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + w());
                }
            }
            int p2 = p + 1;
            int c = buffer[p];
            if (c == 10) {
                this.h++;
                this.i = p2;
                p = p2;
            } else if (c == 32 || c == 13 || c == 9) {
                p = p2;
            } else if (c == 47) {
                this.f = p2;
                if (p2 == l) {
                    this.f--;
                    boolean charsLoaded = b(2);
                    this.f++;
                    if (!charsLoaded) {
                        p = p2;
                        return c;
                    }
                }
                u();
                switch (buffer[this.f]) {
                    case '*':
                        int i;
                        this.f++;
                        String str = "*/";
                        while (true) {
                            if (this.f + str.length() <= this.g || b(str.length())) {
                                if (this.e[this.f] == 10) {
                                    this.h++;
                                    this.i = this.f + 1;
                                } else {
                                    i = 0;
                                    while (i < str.length()) {
                                        if (this.e[this.f + i] == str.charAt(i)) {
                                            i++;
                                        }
                                    }
                                    i = 1;
                                }
                                this.f++;
                            } else {
                                i = 0;
                            }
                        }
                        if (i != 0) {
                            p = this.f + 2;
                            l = this.g;
                            break;
                        }
                        throw a("Unterminated comment");
                    case '/':
                        this.f++;
                        v();
                        p = this.f;
                        l = this.g;
                        break;
                    default:
                        p = p2;
                        return c;
                }
            } else if (c == 35) {
                this.f = p2;
                u();
                v();
                p = this.f;
                l = this.g;
            } else {
                this.f = p2;
                p = p2;
                return c;
            }
        }
    }

    private void u() throws IOException {
        if (!this.d) {
            throw a("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void v() throws IOException {
        char c;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    public String toString() {
        return getClass().getSimpleName() + w();
    }

    private String w() {
        return " at line " + (this.h + 1) + " column " + ((this.f - this.i) + 1) + " path " + p();
    }

    public String p() {
        StringBuilder result = new StringBuilder("$");
        int size = this.n;
        for (int i = 0; i < size; i++) {
            switch (this.m[i]) {
                case 1:
                case 2:
                    result.append('[').append(this.p[i]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    result.append('.');
                    if (this.o[i] == null) {
                        break;
                    }
                    result.append(this.o[i]);
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

    private char x() throws IOException {
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char escaped = cArr[i];
            switch (escaped) {
                case 10:
                    this.h++;
                    this.i = this.f;
                    break;
                case '\"':
                case '\'':
                case '/':
                case '\\':
                    break;
                case 'b':
                    return 8;
                case 'f':
                    return 12;
                case 'n':
                    return 10;
                case 'r':
                    return 13;
                case 't':
                    return 9;
                case 'u':
                    if (this.f + 4 <= this.g || b(4)) {
                        char result = 0;
                        int i2 = this.f;
                        int end = i2 + 4;
                        while (i2 < end) {
                            int i3;
                            char c = this.e[i2];
                            result = (char) (result << 4);
                            if (c >= '0' && c <= '9') {
                                i3 = c - 48;
                            } else if (c >= 'a' && c <= 'f') {
                                i3 = (c - 97) + 10;
                            } else if (c < 'A' || c > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                            } else {
                                i3 = (c - 65) + 10;
                            }
                            result = (char) (i3 + result);
                            i2++;
                        }
                        this.f += 4;
                        return result;
                    }
                    throw a("Unterminated escape sequence");
                default:
                    throw a("Invalid escape sequence");
            }
            return escaped;
        }
        throw a("Unterminated escape sequence");
    }

    private IOException a(String message) throws IOException {
        throw new d(message + w());
    }
}
