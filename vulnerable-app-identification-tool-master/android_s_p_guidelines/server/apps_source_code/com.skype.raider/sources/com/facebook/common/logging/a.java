package com.facebook.common.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class a implements b {
    public static final a a = new a();
    private String b = "unknown";
    private int c = 5;

    public static a a() {
        return a;
    }

    private a() {
    }

    public final void a(int level) {
        this.c = level;
    }

    public final int b() {
        return this.c;
    }

    public final boolean b(int level) {
        return this.c <= level;
    }

    public final void a(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    public final void b(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    public final void c(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    public final void d(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    public final void e(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    public final void f(String tag, String msg, Throwable tr) {
        g(tag, msg, tr);
    }

    private void g(String tag, String msg, Throwable tr) {
        String str;
        a(tag);
        StringBuilder append = new StringBuilder().append(msg).append(10);
        if (tr == null) {
            str = "";
        } else {
            Writer stringWriter = new StringWriter();
            tr.printStackTrace(new PrintWriter(stringWriter));
            str = stringWriter.toString();
        }
        append.append(str);
    }

    private String a(String tag) {
        if (this.b != null) {
            return this.b + ":" + tag;
        }
        return tag;
    }

    public final void a(String tag, String msg) {
        a(tag);
    }

    public final void b(String tag, String msg) {
        a(tag);
    }

    public final void c(String tag, String msg) {
        a(tag);
    }

    public final void d(String tag, String msg) {
        a(tag);
    }

    public final void e(String tag, String msg) {
        a(tag);
    }

    public final void f(String tag, String msg) {
        a(tag);
    }
}
