package org.b.a.c;

import org.a.a.t;
import org.a.a.w;

public final class n extends o {
    public String a;
    public w b;
    public String c;

    public n(String srcName, String msg, w templateToken, Throwable cause) {
        super(g.LEXER_ERROR, null, cause, null);
        this.a = msg;
        this.b = templateToken;
        this.c = srcName;
    }

    public final String toString() {
        t re = this.i;
        int line = re.i;
        int charPos = re.j;
        if (this.b != null) {
            int templateDelimiterSize = 1;
            if (this.b.a() == 5) {
                templateDelimiterSize = 2;
            }
            line += this.b.c() - 1;
            charPos += this.b.d() + templateDelimiterSize;
        }
        String filepos = line + ":" + charPos;
        if (this.c != null) {
            return this.c + " " + filepos + ": " + String.format(this.e.E, new Object[]{this.a});
        }
        return filepos + ": " + String.format(this.e.E, new Object[]{this.a});
    }
}
