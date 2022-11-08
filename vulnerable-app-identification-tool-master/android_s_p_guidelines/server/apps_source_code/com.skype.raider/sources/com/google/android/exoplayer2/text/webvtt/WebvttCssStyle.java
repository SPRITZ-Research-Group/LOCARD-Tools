package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout.Alignment;
import com.google.android.exoplayer2.d.s;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class WebvttCssStyle {
    private String a = "";
    private String b = "";
    private List<String> c = Collections.emptyList();
    private String d = "";
    private String e = null;
    private int f;
    private boolean g = false;
    private int h;
    private boolean i = false;
    private int j = -1;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private float o;
    private Alignment p = null;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FontSizeUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StyleFlags {
    }

    public final void a(String targetId) {
        this.a = targetId;
    }

    public final void b(String targetTag) {
        this.b = targetTag;
    }

    public final void a(String[] targetClasses) {
        this.c = Arrays.asList(targetClasses);
    }

    public final void c(String targetVoice) {
        this.d = targetVoice;
    }

    public final int a(String id, String tag, String[] classes, String voice) {
        if (!this.a.isEmpty() || !this.b.isEmpty() || !this.c.isEmpty() || !this.d.isEmpty()) {
            int score = a(a(a(0, this.a, id, (int) ErrorDialogData.SUPPRESSED), this.b, tag, 2), this.d, voice, 4);
            if (score == -1 || !Arrays.asList(classes).containsAll(this.c)) {
                return 0;
            }
            return (this.c.size() * 4) + score;
        } else if (tag.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    public final int a() {
        int i = 0;
        if (this.l == -1 && this.m == -1) {
            return -1;
        }
        int i2 = this.l == 1 ? 1 : 0;
        if (this.m == 1) {
            i = 2;
        }
        return i2 | i;
    }

    public final boolean b() {
        return this.j == 1;
    }

    public final boolean c() {
        return this.k == 1;
    }

    public final WebvttCssStyle d() {
        this.k = 1;
        return this;
    }

    public final WebvttCssStyle e() {
        this.l = 1;
        return this;
    }

    public final WebvttCssStyle f() {
        this.m = 1;
        return this;
    }

    public final String g() {
        return this.e;
    }

    public final WebvttCssStyle d(String fontFamily) {
        this.e = s.d(fontFamily);
        return this;
    }

    public final int h() {
        if (this.g) {
            return this.f;
        }
        throw new IllegalStateException("Font color not defined");
    }

    public final WebvttCssStyle a(int color) {
        this.f = color;
        this.g = true;
        return this;
    }

    public final boolean i() {
        return this.g;
    }

    public final int j() {
        if (this.i) {
            return this.h;
        }
        throw new IllegalStateException("Background color not defined.");
    }

    public final WebvttCssStyle b(int backgroundColor) {
        this.h = backgroundColor;
        this.i = true;
        return this;
    }

    public final boolean k() {
        return this.i;
    }

    public final Alignment l() {
        return this.p;
    }

    public final int m() {
        return this.n;
    }

    public final float n() {
        return this.o;
    }

    private static int a(int currentScore, String target, String actual, int score) {
        if (target.isEmpty() || currentScore == -1) {
            return currentScore;
        }
        return target.equals(actual) ? currentScore + score : -1;
    }
}
