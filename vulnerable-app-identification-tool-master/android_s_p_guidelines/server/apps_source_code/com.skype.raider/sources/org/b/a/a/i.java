package org.b.a.a;

import java.util.ArrayList;
import java.util.List;
import org.a.a.e;
import org.a.a.g;
import org.a.a.n;
import org.a.a.q;
import org.a.a.t;
import org.a.a.w;
import org.a.a.x;
import org.b.a.c.f;
import org.b.a.c.j;

public final class i implements x {
    public static final w a = new a(-1, "<skip>");
    char b = '<';
    char c = '>';
    boolean d = false;
    public int e = 0;
    f f;
    w g;
    e h;
    char i;
    int j;
    int k;
    int l;
    List<w> m = new ArrayList();

    public static class a extends g {
        public a(e input, int type, int start, int stop) {
            super(input, type, start, stop);
        }

        public a(int type, String text) {
            super(type, text);
        }

        public final String toString() {
            String tokenName;
            String channelStr = "";
            if (this.d > 0) {
                channelStr = ",channel=" + this.d;
            }
            String txt = b();
            if (txt != null) {
                txt = j.d(txt);
            } else {
                txt = "<no text>";
            }
            if (this.a == -1) {
                tokenName = "EOF";
            } else {
                tokenName = j.c[this.a];
            }
            return "[@" + h() + "," + this.h + ":" + this.i + "='" + txt + "',<" + tokenName + ">" + channelStr + "," + this.b + ":" + d() + "]";
        }
    }

    public i(f errMgr, e input, char delimiterStartChar, char delimiterStopChar) {
        this.f = errMgr;
        this.h = input;
        this.i = (char) input.a(1);
        this.g = null;
        this.b = delimiterStartChar;
        this.c = delimiterStopChar;
    }

    public final w a() {
        if (this.m.size() > 0) {
            return (w) this.m.remove(0);
        }
        w wVar;
        do {
            this.j = this.h.b();
            this.k = this.h.e();
            this.l = this.h.f();
            if (this.i == 65535) {
                return a(-1);
            }
            if (this.d) {
                while (true) {
                    switch (this.i) {
                        case 9:
                        case 10:
                        case 13:
                        case ' ':
                            c();
                            wVar = a;
                            break;
                        case '!':
                            c();
                            wVar = a(10);
                            break;
                        case '\"':
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(this.i);
                            c();
                            boolean z = false;
                            while (this.i != '\"') {
                                if (this.i == '\\') {
                                    z = true;
                                    c();
                                    switch (this.i) {
                                        case 'n':
                                            stringBuilder.append(10);
                                            break;
                                        case 'r':
                                            stringBuilder.append(13);
                                            break;
                                        case 't':
                                            stringBuilder.append(9);
                                            break;
                                        default:
                                            stringBuilder.append(this.i);
                                            break;
                                    }
                                    c();
                                } else {
                                    stringBuilder.append(this.i);
                                    c();
                                    if (this.i == 65535) {
                                        t nVar = new n(34, this.h);
                                        nVar.i = this.h.e();
                                        nVar.j = this.h.f();
                                        this.f.a(this.h.g(), "EOF in string", this.g, nVar);
                                        stringBuilder.append(this.i);
                                        c();
                                        if (!z) {
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            stringBuilder.append(this.i);
                            c();
                            wVar = !z ? a(26) : a(26, stringBuilder.toString());
                        case '&':
                            c();
                            a('&');
                            wVar = a(30);
                            break;
                        case '(':
                            c();
                            wVar = a(14);
                            break;
                        case ')':
                            c();
                            wVar = a(15);
                            break;
                        case ',':
                            c();
                            wVar = a(18);
                            break;
                        case '.':
                            c();
                            if (this.h.a(1) != 46 || this.h.a(2) != 46) {
                                wVar = a(19);
                                break;
                            }
                            c();
                            a('.');
                            wVar = a(11);
                            break;
                        case ':':
                            c();
                            wVar = a(13);
                            break;
                        case ';':
                            c();
                            wVar = a(9);
                            break;
                        case '=':
                            c();
                            wVar = a(12);
                            break;
                        case '@':
                            c();
                            if (this.i != 'e' || this.h.a(2) != 110 || this.h.a(3) != 100) {
                                wVar = a(33);
                                break;
                            }
                            c();
                            c();
                            c();
                            wVar = a(34);
                            break;
                        case '[':
                            c();
                            wVar = a(16);
                            break;
                        case ']':
                            c();
                            wVar = a(17);
                            break;
                        case '{':
                            wVar = e();
                            break;
                        case '|':
                            c();
                            a('|');
                            wVar = a(29);
                            break;
                        default:
                            if (this.i == this.c) {
                                c();
                                this.d = false;
                                wVar = a(24);
                                break;
                            } else if (b(this.i)) {
                                wVar = g();
                                String b = wVar.b();
                                if (!b.equals("if")) {
                                    if (!b.equals("endif")) {
                                        if (!b.equals("else")) {
                                            if (!b.equals("elseif")) {
                                                if (!b.equals("super")) {
                                                    if (!b.equals("true")) {
                                                        if (b.equals("false")) {
                                                            wVar = a(36);
                                                            break;
                                                        }
                                                    }
                                                    wVar = a(35);
                                                    break;
                                                }
                                                wVar = a(8);
                                                break;
                                            }
                                            wVar = a(6);
                                            break;
                                        }
                                        wVar = a(5);
                                        break;
                                    }
                                    wVar = a(7);
                                    break;
                                }
                                wVar = a(4);
                                break;
                            } else {
                                t qVar = new q("", 0, 0, this.h);
                                qVar.i = this.k;
                                qVar.j = this.l;
                                this.f.a(this.h.g(), "invalid character '" + c(this.i) + "'", this.g, qVar);
                                if (this.i == 65535) {
                                    wVar = a(-1);
                                    break;
                                }
                                c();
                            }
                            break;
                    }
                }
            }
            wVar = d();
        } while (wVar == a);
        return wVar;
    }

    private void a(char x) {
        if (this.i != x) {
            this.f.a(this.h.g(), "expecting '" + x + "', found '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
        }
        c();
    }

    private void c() {
        this.h.a();
        this.i = (char) this.h.a(1);
    }

    private w d() {
        if (this.h.f() == 0 && (this.i == ' ' || this.i == 9)) {
            while (true) {
                if (this.i != ' ' && this.i != 9) {
                    break;
                }
                c();
            }
            if (this.i != 65535) {
                return a(31);
            }
            return a(22);
        } else if (this.i == this.b) {
            c();
            if (this.i == '!') {
                a('!');
                while (true) {
                    if (this.i == '!' && this.h.a(2) == this.c) {
                        break;
                    } else if (this.i == 65535) {
                        t nVar = new n(33, this.h);
                        nVar.i = this.h.e();
                        nVar.j = this.h.f();
                        this.f.a(this.h.g(), "Nonterminated comment starting at " + this.k + ":" + this.l + ": '!" + this.c + "' missing", this.g, nVar);
                        break;
                    } else {
                        c();
                    }
                }
                c();
                c();
                return a(37);
            } else if (this.i == '\\') {
                this.j = this.h.b();
                this.l = this.h.f();
                c();
                w a;
                if (this.i == 'u') {
                    c();
                    char[] cArr = new char[4];
                    if (!c(this.i)) {
                        this.f.a(this.h.g(), "invalid unicode char: '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
                    }
                    cArr[0] = this.i;
                    c();
                    if (!c(this.i)) {
                        this.f.a(this.h.g(), "invalid unicode char: '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
                    }
                    cArr[1] = this.i;
                    c();
                    if (!c(this.i)) {
                        this.f.a(this.h.g(), "invalid unicode char: '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
                    }
                    cArr[2] = this.i;
                    c();
                    if (!c(this.i)) {
                        this.f.a(this.h.g(), "invalid unicode char: '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
                    }
                    cArr[3] = this.i;
                    a = a(String.valueOf((char) Integer.parseInt(new String(cArr), 16)), this.h.f() - 6);
                    c();
                    a(this.c);
                    return a;
                }
                String str;
                switch (this.i) {
                    case ' ':
                        str = " ";
                        break;
                    case '\\':
                        i();
                        return a;
                    case 'n':
                        str = "\n";
                        break;
                    case 't':
                        str = "\t";
                        break;
                    default:
                        this.f.a(this.h.g(), "invalid escaped char: '" + c(this.i) + "'", this.g, new q("", 0, 0, this.h));
                        c();
                        a(this.c);
                        return a;
                }
                c();
                a = a(str, this.h.f() - 2);
                a(this.c);
                return a;
            } else {
                this.d = true;
                return a(23);
            }
        } else if (this.i == 13) {
            c();
            c();
            return a(32);
        } else if (this.i == 10) {
            c();
            return a(32);
        } else if (this.i != '}' || this.e <= 0) {
            return f();
        } else {
            this.d = true;
            this.e--;
            c();
            return b(21);
        }
    }

    private w e() {
        this.e++;
        int m = this.h.d();
        int curlyStartChar = this.j;
        int curlyLine = this.k;
        int curlyPos = this.l;
        List<w> argTokens = new ArrayList();
        c();
        w curly = b(20);
        h();
        argTokens.add(g());
        h();
        while (this.i == ',') {
            c();
            argTokens.add(b(18));
            h();
            argTokens.add(g());
            h();
        }
        h();
        if (this.i == '|') {
            boolean z;
            c();
            argTokens.add(b(28));
            char c = this.i;
            if (c == ' ' || c == 9 || c == 10 || c == 13) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                c();
            }
            for (w t : argTokens) {
                this.m.add(t);
            }
            this.h.c(m);
            this.d = false;
            this.j = curlyStartChar;
            this.k = curlyLine;
            this.l = curlyPos;
        } else {
            this.h.b(m);
            this.j = curlyStartChar;
            this.k = curlyLine;
            this.l = curlyPos;
            c();
            this.d = false;
        }
        return curly;
    }

    private w f() {
        boolean modifiedText = false;
        StringBuilder buf = new StringBuilder();
        while (this.i != 65535 && this.i != this.b && this.i != 13 && this.i != 10 && (this.i != '}' || this.e <= 0)) {
            if (this.i != '\\') {
                buf.append(this.i);
                c();
            } else if (this.h.a(2) == 92) {
                c();
                c();
                buf.append('\\');
                modifiedText = true;
            } else if (this.h.a(2) == this.b || this.h.a(2) == 125) {
                modifiedText = true;
                c();
                buf.append(this.i);
                c();
            } else {
                buf.append(this.i);
                c();
            }
        }
        if (modifiedText) {
            return a(22, buf.toString());
        }
        return a(22);
    }

    private w g() {
        this.j = this.h.b();
        this.k = this.h.e();
        this.l = this.h.f();
        c();
        while (b(this.i)) {
            c();
        }
        return a(25);
    }

    private void h() {
        while (true) {
            if (this.i == ' ' || this.i == 9 || this.i == 10 || this.i == 13) {
                c();
            } else {
                return;
            }
        }
    }

    private void i() {
        a('\\');
        a(this.c);
        while (true) {
            if (this.i != ' ' && this.i != 9) {
                break;
            }
            c();
        }
        if (this.i == 65535) {
            t re = new t(this.h);
            re.i = this.h.e();
            re.j = this.h.f();
            this.f.a(this.h.g(), "Missing newline after newline escape <\\\\>", this.g, re);
            return;
        }
        if (this.i == 13) {
            c();
        }
        a(10);
        while (true) {
            if (this.i == ' ' || this.i == 9) {
                c();
            } else {
                return;
            }
        }
    }

    private static boolean b(char c) {
        return (c >= 'a' && c <= 'z') || ((c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '_' || c == '/'));
    }

    private static boolean c(char c) {
        return (c >= 'a' && c <= 'f') || ((c >= 'A' && c <= 'F') || (c >= '0' && c <= '9'));
    }

    private w a(int ttype) {
        a t = new a(this.h, ttype, this.j, this.h.b() - 1);
        t.a(this.k);
        t.b(this.l);
        return t;
    }

    private w b(int ttype) {
        a t = new a(this.h, ttype, this.h.b() - 1, this.h.b() - 1);
        t.a(this.h.e());
        t.b(this.h.f() - 1);
        return t;
    }

    private w a(String text, int pos) {
        a t = new a(22, text);
        t.d(this.j);
        t.e(this.h.b() - 1);
        t.a(this.h.e());
        t.b(pos);
        return t;
    }

    private w a(int ttype, String text) {
        a t = new a(ttype, text);
        t.d(this.j);
        t.e(this.h.b() - 1);
        t.a(this.k);
        t.b(this.l);
        return t;
    }

    public final String b() {
        return "no idea";
    }

    private static String c(int c) {
        if (c == 65535) {
            return "<EOF>";
        }
        return String.valueOf((char) c);
    }
}
