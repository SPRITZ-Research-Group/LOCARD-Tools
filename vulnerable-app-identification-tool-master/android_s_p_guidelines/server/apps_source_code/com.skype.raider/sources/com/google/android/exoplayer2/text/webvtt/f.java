package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import java.util.ArrayList;
import java.util.List;

public final class f extends a {
    private final e a = new e();
    private final k b = new k();
    private final d.a c = new d.a();
    private final a d = new a();
    private final List<WebvttCssStyle> e = new ArrayList();

    public f() {
        super("WebvttDecoder");
    }

    private static int a(k parsableWebvttData) {
        int foundEvent = -1;
        int currentInputPosition = 0;
        while (foundEvent == -1) {
            currentInputPosition = parsableWebvttData.d();
            String line = parsableWebvttData.x();
            if (line == null) {
                foundEvent = 0;
            } else if ("STYLE".equals(line)) {
                foundEvent = 2;
            } else if ("NOTE".startsWith(line)) {
                foundEvent = 1;
            } else {
                foundEvent = 3;
            }
        }
        parsableWebvttData.c(currentInputPosition);
        return foundEvent;
    }

    private static void b(k parsableWebvttData) {
        do {
        } while (!TextUtils.isEmpty(parsableWebvttData.x()));
    }

    protected final /* synthetic */ c a(byte[] bArr, int i, boolean z) throws e {
        this.b.a(bArr, i);
        this.c.a();
        this.e.clear();
        g.a(this.b);
        do {
        } while (!TextUtils.isEmpty(this.b.x()));
        List arrayList = new ArrayList();
        while (true) {
            int a = a(this.b);
            if (a == 0) {
                return new h(arrayList);
            }
            if (a == 1) {
                b(this.b);
            } else if (a == 2) {
                if (arrayList.isEmpty()) {
                    this.b.x();
                    WebvttCssStyle a2 = this.d.a(this.b);
                    if (a2 != null) {
                        this.e.add(a2);
                    }
                } else {
                    throw new e("A style block was found after the first cue.");
                }
            } else if (a == 3 && this.a.a(this.b, this.c, this.e)) {
                arrayList.add(this.c.b());
                this.c.a();
            }
        }
    }
}
