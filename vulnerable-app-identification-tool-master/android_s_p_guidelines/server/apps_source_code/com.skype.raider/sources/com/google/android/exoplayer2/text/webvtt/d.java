package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.Cue;

final class d extends Cue {
    public final long m;
    public final long n;

    /* renamed from: com.google.android.exoplayer2.text.webvtt.d$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Alignment.values().length];

        static {
            try {
                a[Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Alignment.ALIGN_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static final class a {
        private long a;
        private long b;
        private SpannableStringBuilder c;
        private Alignment d;
        private float e;
        private int f;
        private int g;
        private float h;
        private int i;
        private float j;

        public a() {
            a();
        }

        public final void a() {
            this.a = 0;
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = Float.MIN_VALUE;
            this.f = Integer.MIN_VALUE;
            this.g = Integer.MIN_VALUE;
            this.h = Float.MIN_VALUE;
            this.i = Integer.MIN_VALUE;
            this.j = Float.MIN_VALUE;
        }

        public final d b() {
            if (this.h != Float.MIN_VALUE && this.i == Integer.MIN_VALUE) {
                if (this.d != null) {
                    switch (AnonymousClass1.a[this.d.ordinal()]) {
                        case 1:
                            this.i = 0;
                            break;
                        case 2:
                            this.i = 1;
                            break;
                        case 3:
                            this.i = 2;
                            break;
                        default:
                            new StringBuilder("Unrecognized alignment: ").append(this.d);
                            this.i = 0;
                            break;
                    }
                }
                this.i = Integer.MIN_VALUE;
            }
            return new d(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }

        public final a a(long time) {
            this.a = time;
            return this;
        }

        public final a b(long time) {
            this.b = time;
            return this;
        }

        public final a a(SpannableStringBuilder aText) {
            this.c = aText;
            return this;
        }

        public final a a(Alignment textAlignment) {
            this.d = textAlignment;
            return this;
        }

        public final a a(float line) {
            this.e = line;
            return this;
        }

        public final a a(int lineType) {
            this.f = lineType;
            return this;
        }

        public final a b(int lineAnchor) {
            this.g = lineAnchor;
            return this;
        }

        public final a b(float position) {
            this.h = position;
            return this;
        }

        public final a c(int positionAnchor) {
            this.i = positionAnchor;
            return this;
        }

        public final a c(float width) {
            this.j = width;
            return this;
        }
    }

    public d(CharSequence text) {
        this(text, (byte) 0);
    }

    private d(CharSequence text, byte b) {
        this(0, 0, text, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public d(long startTime, long endTime, CharSequence text, Alignment textAlignment, float line, int lineType, int lineAnchor, float position, int positionAnchor, float width) {
        super(text, textAlignment, line, lineType, lineAnchor, position, positionAnchor, width);
        this.m = startTime;
        this.n = endTime;
    }
}
