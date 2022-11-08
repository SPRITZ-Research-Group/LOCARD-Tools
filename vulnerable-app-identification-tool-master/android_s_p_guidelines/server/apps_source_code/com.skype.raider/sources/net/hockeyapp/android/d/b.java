package net.hockeyapp.android.d;

import java.io.Serializable;

public class b implements Serializable {
    private int a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;

    public final void a(int id) {
        this.a = id;
    }

    public final void b(int messageId) {
        this.b = messageId;
    }

    public final String a() {
        return this.c;
    }

    public final void a(String filename) {
        this.c = filename;
    }

    public final String b() {
        return this.d;
    }

    public final void b(String url) {
        this.d = url;
    }

    public final void c(String createdAt) {
        this.e = createdAt;
    }

    public final void d(String updatedAt) {
        this.f = updatedAt;
    }

    public final String c() {
        return this.b + this.a;
    }

    public String toString() {
        return "\n" + b.class.getSimpleName() + "\nid         " + this.a + "\nmessage id " + this.b + "\nfilename   " + this.c + "\nurl        " + this.d + "\ncreatedAt  " + this.e + "\nupdatedAt  " + this.f;
    }
}
