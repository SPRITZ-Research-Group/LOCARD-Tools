package com.a.a.a.c;

import com.a.a.a.g;

public final class b extends g {
    protected final b c;
    protected String d;
    protected b e = null;

    private b(int i, b bVar) {
        this.a = i;
        this.c = bVar;
        this.b = -1;
    }

    public static b f() {
        return new b(0, null);
    }

    private b a(int i) {
        this.a = i;
        this.b = -1;
        this.d = null;
        return this;
    }

    public final b g() {
        b bVar = this.e;
        if (bVar != null) {
            return bVar.a(1);
        }
        bVar = new b(1, this);
        this.e = bVar;
        return bVar;
    }

    public final b h() {
        b bVar = this.e;
        if (bVar != null) {
            return bVar.a(2);
        }
        bVar = new b(2, this);
        this.e = bVar;
        return bVar;
    }

    public final b i() {
        return this.c;
    }

    public final int a(String str) {
        if (this.a != 2 || this.d != null) {
            return 4;
        }
        this.d = str;
        return this.b < 0 ? 0 : 1;
    }

    public final int j() {
        if (this.a == 2) {
            if (this.d == null) {
                return 5;
            }
            this.d = null;
            this.b++;
            return 2;
        } else if (this.a == 1) {
            int i = this.b;
            this.b++;
            return i < 0 ? 0 : 1;
        } else {
            this.b++;
            return this.b == 0 ? 0 : 3;
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        if (this.a == 2) {
            stringBuilder.append('{');
            if (this.d != null) {
                stringBuilder.append('\"');
                stringBuilder.append(this.d);
                stringBuilder.append('\"');
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append('}');
        } else if (this.a == 1) {
            stringBuilder.append('[');
            stringBuilder.append(e());
            stringBuilder.append(']');
        } else {
            stringBuilder.append("/");
        }
        return stringBuilder.toString();
    }
}
