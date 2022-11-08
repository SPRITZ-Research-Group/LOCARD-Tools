package com.google.android.exoplayer2.text.a;

import android.graphics.Color;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class c extends d {
    private final k a = new k();
    private final j b = new j();
    private final int c;
    private final a[] d;
    private a e;
    private List<Cue> f;
    private List<Cue> g;
    private b h;
    private int i;

    private static final class a {
        public static final int a = a(2, 2, 2, 0);
        public static final int b = a(0, 0, 0, 0);
        public static final int c = a(0, 0, 0, 3);
        private static final int[] d = new int[]{0, 0, 0, 0, 0, 2, 0};
        private static final int[] e = new int[]{0, 0, 0, 0, 0, 0, 2};
        private static final int[] f = new int[]{3, 3, 3, 3, 3, 3, 1};
        private static final boolean[] g = new boolean[]{false, false, false, true, true, true, false};
        private static final int[] h = new int[]{b, c, b, b, c, b, b};
        private static final int[] i = new int[]{0, 1, 2, 3, 4, 3, 4};
        private static final int[] j = new int[]{0, 0, 0, 0, 0, 3, 3};
        private static final int[] k = new int[]{b, b, b, b, b, c, c};
        private int A;
        private int B;
        private int C;
        private int D;
        private int E;
        private int F;
        private int G;
        private final List<SpannableString> l = new LinkedList();
        private final SpannableStringBuilder m = new SpannableStringBuilder();
        private boolean n;
        private boolean o;
        private int p;
        private boolean q;
        private int r;
        private int s;
        private int t;
        private int u;
        private boolean v;
        private int w;
        private int x;
        private int y;
        private int z;

        public a() {
            b();
        }

        public final void b() {
            c();
            this.n = false;
            this.o = false;
            this.p = 4;
            this.q = false;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = 15;
            this.v = true;
            this.w = 0;
            this.x = 0;
            this.y = 0;
            this.z = b;
            this.D = a;
            this.F = b;
        }

        public final void c() {
            this.l.clear();
            this.m.clear();
            this.A = -1;
            this.B = -1;
            this.C = -1;
            this.E = -1;
            this.G = 0;
        }

        public final boolean d() {
            return this.n;
        }

        public final void a(boolean visible) {
            this.o = visible;
        }

        public final boolean e() {
            return this.o;
        }

        public final void a(boolean visible, boolean rowLock, int priority, boolean relativePositioning, int verticalAnchor, int horizontalAnchor, int rowCount, int anchorId, int windowStyleId, int penStyleId) {
            this.n = true;
            this.o = visible;
            this.v = rowLock;
            this.p = priority;
            this.q = relativePositioning;
            this.r = verticalAnchor;
            this.s = horizontalAnchor;
            this.t = anchorId;
            if (this.u != rowCount + 1) {
                this.u = rowCount + 1;
                while (true) {
                    if ((!rowLock || this.l.size() < this.u) && this.l.size() < 15) {
                        break;
                    }
                    this.l.remove(0);
                }
            }
            if (!(windowStyleId == 0 || this.x == windowStyleId)) {
                this.x = windowStyleId;
                int windowStyleIdIndex = windowStyleId - 1;
                a(h[windowStyleIdIndex], d[windowStyleIdIndex]);
            }
            if (penStyleId != 0 && this.y != penStyleId) {
                this.y = penStyleId;
                int penStyleIdIndex = penStyleId - 1;
                a(false, false);
                b(a, k[penStyleIdIndex]);
            }
        }

        public final void a(int fillColor, int justification) {
            this.z = fillColor;
            this.w = justification;
        }

        public final void a(boolean italicsToggle, boolean underlineToggle) {
            if (this.A != -1) {
                if (!italicsToggle) {
                    this.m.setSpan(new StyleSpan(2), this.A, this.m.length(), 33);
                    this.A = -1;
                }
            } else if (italicsToggle) {
                this.A = this.m.length();
            }
            if (this.B != -1) {
                if (!underlineToggle) {
                    this.m.setSpan(new UnderlineSpan(), this.B, this.m.length(), 33);
                    this.B = -1;
                }
            } else if (underlineToggle) {
                this.B = this.m.length();
            }
        }

        public final void b(int foregroundColor, int backgroundColor) {
            if (!(this.C == -1 || this.D == foregroundColor)) {
                this.m.setSpan(new ForegroundColorSpan(this.D), this.C, this.m.length(), 33);
            }
            if (foregroundColor != a) {
                this.C = this.m.length();
                this.D = foregroundColor;
            }
            if (!(this.E == -1 || this.F == backgroundColor)) {
                this.m.setSpan(new BackgroundColorSpan(this.F), this.E, this.m.length(), 33);
            }
            if (backgroundColor != b) {
                this.E = this.m.length();
                this.F = backgroundColor;
            }
        }

        public final void a(int row) {
            if (this.G != row) {
                a(10);
            }
            this.G = row;
        }

        public final void f() {
            int length = this.m.length();
            if (length > 0) {
                this.m.delete(length - 1, length);
            }
        }

        public final void a(char text) {
            if (text == 10) {
                this.l.add(h());
                this.m.clear();
                if (this.A != -1) {
                    this.A = 0;
                }
                if (this.B != -1) {
                    this.B = 0;
                }
                if (this.C != -1) {
                    this.C = 0;
                }
                if (this.E != -1) {
                    this.E = 0;
                }
                while (true) {
                    if ((this.v && this.l.size() >= this.u) || this.l.size() >= 15) {
                        this.l.remove(0);
                    } else {
                        return;
                    }
                }
            }
            this.m.append(text);
        }

        private SpannableString h() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.m);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.A != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.A, length, 33);
                }
                if (this.B != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.B, length, 33);
                }
                if (this.C != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.D), this.C, length, 33);
                }
                if (this.E != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.F), this.E, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public final b g() {
            boolean windowColorSet = true;
            if (a()) {
                return null;
            }
            Alignment alignment;
            float position;
            float line;
            int verticalAnchorType;
            int horizontalAnchorType;
            SpannableStringBuilder cueString = new SpannableStringBuilder();
            for (int i = 0; i < this.l.size(); i++) {
                cueString.append((CharSequence) this.l.get(i));
                cueString.append(10);
            }
            cueString.append(h());
            switch (this.w) {
                case 0:
                case 3:
                    alignment = Alignment.ALIGN_NORMAL;
                    break;
                case 1:
                    alignment = Alignment.ALIGN_OPPOSITE;
                    break;
                case 2:
                    alignment = Alignment.ALIGN_CENTER;
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected justification value: " + this.w);
            }
            if (this.q) {
                position = ((float) this.s) / 99.0f;
                line = ((float) this.r) / 99.0f;
            } else {
                position = ((float) this.s) / 209.0f;
                line = ((float) this.r) / 74.0f;
            }
            position = (position * 0.9f) + 0.05f;
            line = (line * 0.9f) + 0.05f;
            if (this.t % 3 == 0) {
                verticalAnchorType = 0;
            } else if (this.t % 3 == 1) {
                verticalAnchorType = 1;
            } else {
                verticalAnchorType = 2;
            }
            if (this.t / 3 == 0) {
                horizontalAnchorType = 0;
            } else if (this.t / 3 == 1) {
                horizontalAnchorType = 1;
            } else {
                horizontalAnchorType = 2;
            }
            if (this.z == b) {
                windowColorSet = false;
            }
            return new b(cueString, alignment, line, verticalAnchorType, position, horizontalAnchorType, windowColorSet, this.z, this.p);
        }

        public static int a(int red, int green, int blue) {
            return a(red, green, blue, 0);
        }

        public static int a(int red, int green, int blue, int opacity) {
            int alpha;
            int i;
            int i2;
            int i3 = 255;
            com.google.android.exoplayer2.d.a.a(red, 4);
            com.google.android.exoplayer2.d.a.a(green, 4);
            com.google.android.exoplayer2.d.a.a(blue, 4);
            com.google.android.exoplayer2.d.a.a(opacity, 4);
            switch (opacity) {
                case 0:
                case 1:
                    alpha = 255;
                    break;
                case 2:
                    alpha = 127;
                    break;
                case 3:
                    alpha = 0;
                    break;
                default:
                    alpha = 255;
                    break;
            }
            if (red > 1) {
                i = 255;
            } else {
                i = 0;
            }
            if (green > 1) {
                i2 = 255;
            } else {
                i2 = 0;
            }
            if (blue <= 1) {
                i3 = 0;
            }
            return Color.argb(alpha, i, i2, i3);
        }

        public final boolean a() {
            return !this.n || (this.l.isEmpty() && this.m.length() == 0);
        }
    }

    private static final class b {
        public final int a;
        public final int b;
        public final byte[] c;
        int d = 0;

        public b(int sequenceNumber, int packetSize) {
            this.a = sequenceNumber;
            this.b = packetSize;
            this.c = new byte[((packetSize * 2) - 1)];
        }
    }

    public final /* bridge */ /* synthetic */ void b(g gVar) throws e {
        super.b(gVar);
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ h g() throws e {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ g h() throws e {
        return super.h();
    }

    public c(int accessibilityChannel) {
        if (accessibilityChannel == -1) {
            accessibilityChannel = 1;
        }
        this.c = accessibilityChannel;
        this.d = new a[8];
        for (int i = 0; i < 8; i++) {
            this.d[i] = new a();
        }
        this.e = this.d[0];
        k();
    }

    public final void c() {
        super.c();
        this.f = null;
        this.g = null;
        this.i = 0;
        this.e = this.d[this.i];
        k();
        this.h = null;
    }

    protected final boolean e() {
        return this.f != this.g;
    }

    protected final com.google.android.exoplayer2.text.c f() {
        this.g = this.f;
        return new f(this.f);
    }

    protected final void a(g inputBuffer) {
        this.a.a(inputBuffer.b.array(), inputBuffer.b.limit());
        while (this.a.b() >= 3) {
            int ccTypeAndValid = this.a.g() & 7;
            int ccType = ccTypeAndValid & 3;
            boolean ccValid = (ccTypeAndValid & 4) == 4;
            byte ccData1 = (byte) this.a.g();
            byte ccData2 = (byte) this.a.g();
            if ((ccType == 2 || ccType == 3) && ccValid) {
                byte[] bArr;
                b bVar;
                int i;
                if (ccType == 3) {
                    i();
                    int sequenceNumber = (ccData1 & 192) >> 6;
                    int packetSize = ccData1 & 63;
                    if (packetSize == 0) {
                        packetSize = 64;
                    }
                    this.h = new b(sequenceNumber, packetSize);
                    bArr = this.h.c;
                    bVar = this.h;
                    i = bVar.d;
                    bVar.d = i + 1;
                    bArr[i] = ccData2;
                } else {
                    com.google.android.exoplayer2.d.a.a(ccType == 2);
                    if (this.h != null) {
                        bArr = this.h.c;
                        bVar = this.h;
                        i = bVar.d;
                        bVar.d = i + 1;
                        bArr[i] = ccData1;
                        bArr = this.h.c;
                        bVar = this.h;
                        i = bVar.d;
                        bVar.d = i + 1;
                        bArr[i] = ccData2;
                    }
                }
                if (this.h.d == (this.h.b * 2) - 1) {
                    i();
                }
            }
        }
    }

    private void i() {
        if (this.h != null) {
            if (this.h.d != (this.h.b * 2) - 1) {
                new StringBuilder("DtvCcPacket ended prematurely; size is ").append((this.h.b * 2) - 1).append(", but current index is ").append(this.h.d).append(" (sequence number ").append(this.h.a).append("); ignoring packet");
            } else {
                this.b.a(this.h.c, this.h.d);
                int c = this.b.c(3);
                int c2 = this.b.c(5);
                if (c == 7) {
                    this.b.b(2);
                    c += this.b.c(6);
                }
                if (c2 == 0) {
                    if (c != 0) {
                        new StringBuilder("serviceNumber is non-zero (").append(c).append(") when blockSize is 0");
                    }
                } else if (c == this.c) {
                    Object obj = null;
                    while (this.b.a() > 0) {
                        c2 = this.b.c(8);
                        if (c2 == 16) {
                            c2 = this.b.c(8);
                            if (c2 <= 31) {
                                if (c2 > 7) {
                                    if (c2 <= 15) {
                                        this.b.b(8);
                                    } else if (c2 <= 23) {
                                        this.b.b(16);
                                    } else if (c2 <= 31) {
                                        this.b.b(24);
                                    }
                                }
                            } else if (c2 <= 127) {
                                switch (c2) {
                                    case 32:
                                        this.e.a(' ');
                                        break;
                                    case 33:
                                        this.e.a(160);
                                        break;
                                    case 37:
                                        this.e.a(8230);
                                        break;
                                    case 42:
                                        this.e.a(352);
                                        break;
                                    case 44:
                                        this.e.a(338);
                                        break;
                                    case 48:
                                        this.e.a(9608);
                                        break;
                                    case 49:
                                        this.e.a(8216);
                                        break;
                                    case 50:
                                        this.e.a(8217);
                                        break;
                                    case 51:
                                        this.e.a(8220);
                                        break;
                                    case 52:
                                        this.e.a(8221);
                                        break;
                                    case 53:
                                        this.e.a(8226);
                                        break;
                                    case 57:
                                        this.e.a(8482);
                                        break;
                                    case 58:
                                        this.e.a(353);
                                        break;
                                    case 60:
                                        this.e.a(339);
                                        break;
                                    case 61:
                                        this.e.a(8480);
                                        break;
                                    case 63:
                                        this.e.a(376);
                                        break;
                                    case 118:
                                        this.e.a(8539);
                                        break;
                                    case 119:
                                        this.e.a(8540);
                                        break;
                                    case 120:
                                        this.e.a(8541);
                                        break;
                                    case 121:
                                        this.e.a(8542);
                                        break;
                                    case 122:
                                        this.e.a(9474);
                                        break;
                                    case 123:
                                        this.e.a(9488);
                                        break;
                                    case 124:
                                        this.e.a(9492);
                                        break;
                                    case 125:
                                        this.e.a(9472);
                                        break;
                                    case 126:
                                        this.e.a(9496);
                                        break;
                                    case 127:
                                        this.e.a(9484);
                                        break;
                                }
                                obj = 1;
                            } else if (c2 <= 159) {
                                if (c2 <= 135) {
                                    this.b.b(32);
                                } else if (c2 <= 143) {
                                    this.b.b(40);
                                } else if (c2 <= 159) {
                                    this.b.b(2);
                                    this.b.b(this.b.c(6) * 8);
                                }
                            } else if (c2 <= 255) {
                                if (c2 == 160) {
                                    this.e.a(13252);
                                } else {
                                    this.e.a('_');
                                }
                                obj = 1;
                            }
                        } else if (c2 <= 31) {
                            switch (c2) {
                                case 0:
                                case 14:
                                    break;
                                case 3:
                                    this.f = j();
                                    break;
                                case 8:
                                    this.e.f();
                                    break;
                                case 12:
                                    k();
                                    break;
                                case 13:
                                    this.e.a(10);
                                    break;
                                default:
                                    if (c2 < 17 || c2 > 23) {
                                        if (c2 >= 24 && c2 <= 31) {
                                            this.b.b(16);
                                            break;
                                        }
                                    }
                                    this.b.b(8);
                                    break;
                                    break;
                            }
                        } else if (c2 <= 127) {
                            if (c2 == 127) {
                                this.e.a(9835);
                            } else {
                                this.e.a((char) (c2 & 255));
                            }
                            obj = 1;
                        } else if (c2 <= 159) {
                            a(c2);
                            obj = 1;
                        } else if (c2 <= 255) {
                            this.e.a((char) (c2 & 255));
                            obj = 1;
                        }
                    }
                    if (obj != null) {
                        this.f = j();
                    }
                }
            }
            this.h = null;
        }
    }

    private void a(int command) {
        int window;
        int i;
        boolean d;
        int a;
        int a2;
        switch (command) {
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                window = command - 128;
                if (this.i != window) {
                    this.i = window;
                    this.e = this.d[window];
                    return;
                }
                return;
            case 136:
                for (i = 1; i <= 8; i++) {
                    if (this.b.d()) {
                        this.d[8 - i].c();
                    }
                }
                return;
            case 137:
                for (i = 1; i <= 8; i++) {
                    if (this.b.d()) {
                        this.d[8 - i].a(true);
                    }
                }
                return;
            case 138:
                for (i = 1; i <= 8; i++) {
                    if (this.b.d()) {
                        this.d[8 - i].a(false);
                    }
                }
                return;
            case 139:
                for (i = 1; i <= 8; i++) {
                    if (this.b.d()) {
                        a aVar = this.d[8 - i];
                        aVar.a(!aVar.e());
                    }
                }
                return;
            case 140:
                for (i = 1; i <= 8; i++) {
                    if (this.b.d()) {
                        this.d[8 - i].b();
                    }
                }
                return;
            case 141:
                this.b.b(8);
                return;
            case 143:
                k();
                return;
            case 144:
                if (this.e.d()) {
                    this.b.c(4);
                    this.b.c(2);
                    this.b.c(2);
                    boolean d2 = this.b.d();
                    d = this.b.d();
                    this.b.c(3);
                    this.b.c(3);
                    this.e.a(d2, d);
                    return;
                }
                this.b.b(16);
                return;
            case 145:
                if (this.e.d()) {
                    a = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
                    a2 = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
                    this.b.b(2);
                    a.a(this.b.c(2), this.b.c(2), this.b.c(2));
                    this.e.b(a, a2);
                    return;
                }
                this.b.b(24);
                return;
            case 146:
                if (this.e.d()) {
                    this.b.b(4);
                    a = this.b.c(4);
                    this.b.b(2);
                    this.b.c(6);
                    this.e.a(a);
                    return;
                }
                this.b.b(16);
                return;
            case 151:
                if (this.e.d()) {
                    a = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
                    this.b.c(2);
                    a.a(this.b.c(2), this.b.c(2), this.b.c(2));
                    this.b.d();
                    this.b.d();
                    this.b.c(2);
                    this.b.c(2);
                    a2 = this.b.c(2);
                    this.b.b(8);
                    this.e.a(a, a2);
                    return;
                }
                this.b.b(32);
                return;
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                window = command - 152;
                a aVar2 = this.d[window];
                this.b.b(2);
                d = this.b.d();
                boolean d3 = this.b.d();
                this.b.d();
                int c = this.b.c(3);
                boolean d4 = this.b.d();
                int c2 = this.b.c(7);
                int c3 = this.b.c(8);
                int c4 = this.b.c(4);
                int c5 = this.b.c(4);
                this.b.b(2);
                this.b.c(6);
                this.b.b(2);
                aVar2.a(d, d3, c, d4, c2, c3, c5, c4, this.b.c(3), this.b.c(3));
                if (this.i != window) {
                    this.i = window;
                    this.e = this.d[window];
                    return;
                }
                return;
            default:
                return;
        }
    }

    private List<Cue> j() {
        List<b> displayCues = new ArrayList();
        int i = 0;
        while (i < 8) {
            if (!this.d[i].a() && this.d[i].e()) {
                displayCues.add(this.d[i].g());
            }
            i++;
        }
        Collections.sort(displayCues);
        return Collections.unmodifiableList(displayCues);
    }

    private void k() {
        for (int i = 0; i < 8; i++) {
            this.d[i].b();
        }
    }
}
