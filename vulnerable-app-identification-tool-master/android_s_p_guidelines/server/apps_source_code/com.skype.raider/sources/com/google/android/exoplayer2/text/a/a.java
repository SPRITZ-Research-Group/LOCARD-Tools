package com.google.android.exoplayer2.text.a;

import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class a extends d {
    private static final int[] a = new int[]{11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] b = new int[]{0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] c = new int[]{-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
    private static final int[] d = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] e = new int[]{174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] f = new int[]{193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] g = new int[]{195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private final k h = new k();
    private final int i;
    private final int j;
    private final LinkedList<a> k = new LinkedList();
    private a l = new a(0, 4);
    private List<Cue> m;
    private List<Cue> n;
    private int o;
    private int p;
    private boolean q;
    private byte r;
    private byte s;

    private static class a {
        private final List<CharacterStyle> a = new ArrayList();
        private final List<a> b = new ArrayList();
        private final List<SpannableString> c = new LinkedList();
        private final SpannableStringBuilder d = new SpannableStringBuilder();
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;

        private static class a {
            public final CharacterStyle a;
            public final int b;
            public final int c;

            public a(CharacterStyle style, int start, int nextStyleIncrement) {
                this.a = style;
                this.b = start;
                this.c = nextStyleIncrement;
            }
        }

        public a(int captionMode, int captionRowCount) {
            a(captionMode, captionRowCount);
        }

        public final void a(int captionMode, int captionRowCount) {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.clear();
            this.e = 15;
            this.f = 0;
            this.g = 0;
            this.h = captionMode;
            this.i = captionRowCount;
            this.j = -1;
        }

        public final boolean a() {
            return this.a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.length() == 0;
        }

        public final void b() {
            int length = this.d.length();
            if (length > 0) {
                this.d.delete(length - 1, length);
            }
        }

        public final int c() {
            return this.e;
        }

        public final void a(int row) {
            this.e = row;
        }

        public final void d() {
            this.c.add(f());
            this.d.clear();
            this.a.clear();
            this.b.clear();
            this.j = -1;
            int numRows = Math.min(this.i, this.e);
            while (this.c.size() >= numRows) {
                this.c.remove(0);
            }
        }

        public final void b(int indent) {
            this.f = indent;
        }

        public final void c(int tabs) {
            this.g = tabs;
        }

        public final void a(CharacterStyle style) {
            this.a.add(style);
        }

        public final void a(CharacterStyle style, int nextStyleIncrement) {
            this.b.add(new a(style, this.d.length(), nextStyleIncrement));
        }

        public final void a(boolean enabled) {
            if (enabled) {
                this.j = this.d.length();
            } else if (this.j != -1) {
                this.d.setSpan(new UnderlineSpan(), this.j, this.d.length(), 33);
                this.j = -1;
            }
        }

        public final void a(char text) {
            this.d.append(text);
        }

        private SpannableString f() {
            int i;
            int length = this.d.length();
            for (i = 0; i < this.a.size(); i++) {
                this.d.setSpan(this.a.get(i), 0, length, 33);
            }
            for (i = 0; i < this.b.size(); i++) {
                int end;
                a cueStyle = (a) this.b.get(i);
                if (i < this.b.size() - cueStyle.c) {
                    end = ((a) this.b.get(cueStyle.c + i)).b;
                } else {
                    end = length;
                }
                this.d.setSpan(cueStyle.a, cueStyle.b, end, 33);
            }
            if (this.j != -1) {
                this.d.setSpan(new UnderlineSpan(), this.j, length, 33);
            }
            return new SpannableString(this.d);
        }

        public final Cue e() {
            SpannableStringBuilder cueString = new SpannableStringBuilder();
            for (int i = 0; i < this.c.size(); i++) {
                cueString.append((CharSequence) this.c.get(i));
                cueString.append(10);
            }
            cueString.append(f());
            if (cueString.length() == 0) {
                return null;
            }
            float position;
            int positionAnchor;
            int lineAnchor;
            int line;
            int startPadding = this.f + this.g;
            int endPadding = (32 - startPadding) - cueString.length();
            int startEndPaddingDelta = startPadding - endPadding;
            if (this.h == 2 && Math.abs(startEndPaddingDelta) < 3) {
                position = 0.5f;
                positionAnchor = 1;
            } else if (this.h != 2 || startEndPaddingDelta <= 0) {
                position = ((((float) startPadding) / 32.0f) * 0.8f) + 0.1f;
                positionAnchor = 0;
            } else {
                position = ((((float) (32 - endPadding)) / 32.0f) * 0.8f) + 0.1f;
                positionAnchor = 2;
            }
            if (this.h == 1 || this.e > 7) {
                lineAnchor = 2;
                line = (this.e - 15) - 2;
            } else {
                lineAnchor = 0;
                line = this.e;
            }
            return new Cue(cueString, Alignment.ALIGN_NORMAL, (float) line, 1, lineAnchor, position, positionAnchor, Float.MIN_VALUE);
        }

        public final String toString() {
            return this.d.toString();
        }
    }

    public final /* bridge */ /* synthetic */ void b(g gVar) throws e {
        super.b(gVar);
    }

    public final /* bridge */ /* synthetic */ h g() throws e {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ g h() throws e {
        return super.h();
    }

    public a(String mimeType, int accessibilityChannel) {
        this.i = "application/x-mp4-cea-608".equals(mimeType) ? 2 : 3;
        switch (accessibilityChannel) {
            case 3:
            case 4:
                this.j = 2;
                break;
            default:
                this.j = 1;
                break;
        }
        a(0);
        j();
    }

    public final void c() {
        super.c();
        this.m = null;
        this.n = null;
        a(0);
        j();
        this.p = 4;
        this.q = false;
        this.r = (byte) 0;
        this.s = (byte) 0;
    }

    public final void d() {
    }

    protected final boolean e() {
        return this.m != this.n;
    }

    protected final c f() {
        this.n = this.m;
        return new f(this.m);
    }

    protected final void a(g inputBuffer) {
        this.h.a(inputBuffer.b.array(), inputBuffer.b.limit());
        boolean captionDataProcessed = false;
        boolean isRepeatableControl = false;
        while (this.h.b() >= this.i) {
            byte ccDataHeader;
            if (this.i == 2) {
                ccDataHeader = (byte) -4;
            } else {
                ccDataHeader = (byte) this.h.g();
            }
            byte ccData1 = (byte) (this.h.g() & 127);
            byte ccData2 = (byte) (this.h.g() & 127);
            if ((ccDataHeader & 6) == 4 && ((this.j != 1 || (ccDataHeader & 1) == 0) && ((this.j != 2 || (ccDataHeader & 1) == 1) && !(ccData1 == (byte) 0 && ccData2 == (byte) 0)))) {
                captionDataProcessed = true;
                if ((ccData1 & 247) == 17 && (ccData2 & 240) == 48) {
                    this.l.a((char) e[ccData2 & 15]);
                } else if ((ccData1 & 246) == 18 && (ccData2 & 224) == 32) {
                    this.l.b();
                    if ((ccData1 & 1) == 0) {
                        this.l.a((char) f[ccData2 & 31]);
                    } else {
                        this.l.a((char) g[ccData2 & 31]);
                    }
                } else if ((ccData1 & 224) == 0) {
                    boolean z;
                    if ((ccData1 & 240) == 16) {
                        isRepeatableControl = true;
                    } else {
                        isRepeatableControl = false;
                    }
                    if (isRepeatableControl) {
                        if (this.q && this.r == ccData1 && this.s == ccData2) {
                            this.q = false;
                            isRepeatableControl = true;
                        } else {
                            this.q = true;
                            this.r = ccData1;
                            this.s = ccData2;
                        }
                    }
                    if ((ccData1 & 247) == 17 && (ccData2 & 240) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    int i;
                    if (!z) {
                        if ((ccData1 & 240) == 16 && (ccData2 & 192) == 64) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            if ((ccData1 & 247) != 23 || ccData2 < (byte) 33 || ccData2 > (byte) 35) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (!z) {
                                if ((ccData1 & 247) == 20 && (ccData2 & 240) == 32) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    switch (ccData2) {
                                        case (byte) 32:
                                            a(2);
                                            break;
                                        case (byte) 37:
                                            this.p = 2;
                                            a(1);
                                            break;
                                        case (byte) 38:
                                            this.p = 3;
                                            a(1);
                                            break;
                                        case (byte) 39:
                                            this.p = 4;
                                            a(1);
                                            break;
                                        case (byte) 41:
                                            a(3);
                                            break;
                                        default:
                                            if (this.o != 0) {
                                                switch (ccData2) {
                                                    case (byte) 33:
                                                        this.l.b();
                                                        break;
                                                    case (byte) 44:
                                                        this.m = null;
                                                        if (this.o == 1 || this.o == 3) {
                                                            j();
                                                            break;
                                                        }
                                                    case (byte) 45:
                                                        if (this.o == 1 && !this.l.a()) {
                                                            this.l.d();
                                                            break;
                                                        }
                                                    case (byte) 46:
                                                        j();
                                                        break;
                                                    case (byte) 47:
                                                        this.m = i();
                                                        j();
                                                        break;
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                            this.l.c(ccData2 - 32);
                        } else {
                            i = a[ccData1 & 7];
                            if ((ccData2 & 32) != 0) {
                                i++;
                            }
                            if (i != this.l.c()) {
                                if (!(this.o == 1 || this.l.a())) {
                                    this.l = new a(this.o, this.p);
                                    this.k.add(this.l);
                                }
                                this.l.a(i);
                            }
                            if ((ccData2 & 1) == 1) {
                                this.l.a(new UnderlineSpan());
                            }
                            i = (ccData2 >> 1) & 15;
                            if (i > 7) {
                                this.l.b(b[i & 7]);
                            } else if (i == 7) {
                                this.l.a(new StyleSpan(2));
                                this.l.a(new ForegroundColorSpan(-1));
                            } else {
                                this.l.a(new ForegroundColorSpan(c[i]));
                            }
                        }
                    } else {
                        this.l.a((ccData2 & 1) == 1);
                        i = (ccData2 >> 1) & 15;
                        if (i == 7) {
                            this.l.a(new StyleSpan(2), 2);
                            this.l.a(new ForegroundColorSpan(-1), 1);
                        } else {
                            this.l.a(new ForegroundColorSpan(c[i]), 1);
                        }
                    }
                } else {
                    this.l.a(a(ccData1));
                    if ((ccData2 & 224) != 0) {
                        this.l.a(a(ccData2));
                    }
                }
            }
        }
        if (captionDataProcessed) {
            if (!isRepeatableControl) {
                this.q = false;
            }
            if (this.o == 1 || this.o == 3) {
                this.m = i();
            }
        }
    }

    private List<Cue> i() {
        List<Cue> displayCues = new ArrayList();
        for (int i = 0; i < this.k.size(); i++) {
            Cue cue = ((a) this.k.get(i)).e();
            if (cue != null) {
                displayCues.add(cue);
            }
        }
        return displayCues;
    }

    private void a(int captionMode) {
        if (this.o != captionMode) {
            int oldCaptionMode = this.o;
            this.o = captionMode;
            j();
            if (oldCaptionMode == 3 || captionMode == 1 || captionMode == 0) {
                this.m = null;
            }
        }
    }

    private void j() {
        this.l.a(this.o, this.p);
        this.k.clear();
        this.k.add(this.l);
    }

    private static char a(byte ccData) {
        return (char) d[(ccData & 127) - 32];
    }
}
