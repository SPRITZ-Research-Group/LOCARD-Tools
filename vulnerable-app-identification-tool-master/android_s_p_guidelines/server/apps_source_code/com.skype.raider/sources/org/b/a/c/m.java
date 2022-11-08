package org.b.a.c;

import org.a.a.w;

public final class m extends o {
    public w a;
    public w b;
    public String c;

    public m(g error, String srcName, w templateToken, w t, Object arg) {
        this(error, srcName, templateToken, t, arg, null);
    }

    public m(g error, String srcName, w templateToken, w t, Object arg, Object arg2) {
        super(error, null, null, arg, arg2);
        this.a = templateToken;
        this.b = t;
        this.c = srcName;
    }

    public final String toString() {
        int line = 0;
        int charPos = -1;
        if (this.b != null) {
            line = this.b.c();
            charPos = this.b.d();
            if (!(this.a == null || this.a.i().equals(this.b.i()))) {
                int templateDelimiterSize = 1;
                if (this.a.a() == 5 || this.a.a() == 6) {
                    templateDelimiterSize = 2;
                }
                line += this.a.c() - 1;
                charPos += this.a.d() + templateDelimiterSize;
            }
        }
        String filepos = line + ":" + charPos;
        if (this.c != null) {
            return this.c + " " + filepos + ": " + String.format(this.e.E, new Object[]{this.f, this.g});
        }
        return filepos + ": " + String.format(this.e.E, new Object[]{this.f, this.g});
    }
}
