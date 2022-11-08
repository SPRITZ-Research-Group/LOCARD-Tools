package org.b.a;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class b implements i {
    public List<String> a;
    public int[] b;
    public int c;
    public String d;
    public Writer e;
    public boolean f;
    public int g;
    public int h;
    public int i;

    private b(Writer out, String newline) {
        this.a = new ArrayList();
        this.b = new int[10];
        this.c = -1;
        this.e = null;
        this.f = true;
        this.g = 0;
        this.h = 0;
        this.i = -1;
        this.e = out;
        this.a.add(null);
        this.d = newline;
    }

    public b(Writer out) {
        this(out, System.getProperty("line.separator"));
    }

    public final void a() {
        this.i = -1;
    }

    public final void a(String indent) {
        this.a.add(indent);
    }

    public final String b() {
        return (String) this.a.remove(this.a.size() - 1);
    }

    public final void c() {
        if (this.c + 1 >= this.b.length) {
            int[] a = new int[(this.b.length * 2)];
            System.arraycopy(this.b, 0, a, 0, this.b.length - 1);
            this.b = a;
        }
        this.c++;
        this.b[this.c] = this.g;
    }

    public final void d() {
        this.c--;
    }

    public final int e() {
        return this.h;
    }

    public final int b(String str) throws IOException {
        int n = 0;
        int nll = this.d.length();
        int sl = str.length();
        for (int i = 0; i < sl; i++) {
            char c = str.charAt(i);
            if (c != 13) {
                if (c == 10) {
                    this.f = true;
                    this.g = -nll;
                    this.e.write(this.d);
                    n += nll;
                    this.h += nll;
                    this.g += n;
                } else {
                    if (this.f) {
                        n += f();
                        this.f = false;
                    }
                    n++;
                    this.e.write(c);
                    this.g++;
                    this.h++;
                }
            }
        }
        return n;
    }

    public final int c(String str) throws IOException {
        return b(str);
    }

    public final int a(String str, String wrap) throws IOException {
        return d(wrap) + b(str);
    }

    public final int d(String wrap) throws IOException {
        int n = 0;
        if (!(this.i == -1 || wrap == null || this.f || this.g < this.i)) {
            for (int i = 0; i < wrap.length(); i++) {
                char c = wrap.charAt(i);
                if (c != 13) {
                    if (c == 10) {
                        this.e.write(this.d);
                        n += this.d.length();
                        this.g = 0;
                        this.h += this.d.length();
                        n += f();
                    } else {
                        n++;
                        this.e.write(c);
                        this.g++;
                        this.h++;
                    }
                }
            }
        }
        return n;
    }

    private int f() throws IOException {
        int n = 0;
        for (String ind : this.a) {
            if (ind != null) {
                n += ind.length();
                this.e.write(ind);
            }
        }
        int indentWidth = n;
        if (this.c >= 0 && this.b[this.c] > indentWidth) {
            int remainder = this.b[this.c] - indentWidth;
            for (int i = 1; i <= remainder; i++) {
                this.e.write(32);
            }
            n += remainder;
        }
        this.g += n;
        this.h += n;
        return n;
    }
}
