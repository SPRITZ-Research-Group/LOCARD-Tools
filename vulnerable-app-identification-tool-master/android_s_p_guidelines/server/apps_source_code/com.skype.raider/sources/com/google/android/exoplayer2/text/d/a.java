package com.google.android.exoplayer2.text.d;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import java.nio.charset.Charset;
import java.util.List;

public final class a extends com.google.android.exoplayer2.text.a {
    private static final int a = s.e("styl");
    private static final int b = s.e("tbox");
    private final k c = new k();
    private boolean d;
    private int e;
    private int f;
    private String g;
    private float h;
    private int i;

    public a(List<byte[]> initializationData) {
        super("Tx3gDecoder");
        if (initializationData != null && initializationData.size() == 1 && (((byte[]) initializationData.get(0)).length == 48 || ((byte[]) initializationData.get(0)).length == 53)) {
            boolean z;
            byte[] bArr = (byte[]) initializationData.get(0);
            this.e = bArr[24];
            this.f = ((((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16)) | ((bArr[28] & 255) << 8)) | (bArr[29] & 255);
            this.g = "Serif".equals(new String(bArr, 43, bArr.length + -43)) ? "serif" : "sans-serif";
            this.i = bArr[25] * 20;
            if ((bArr[0] & 32) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.d = z;
            if (this.d) {
                this.h = ((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) this.i);
                this.h = s.a(this.h, 0.0f, 0.95f);
                return;
            }
            this.h = 0.85f;
            return;
        }
        this.e = 0;
        this.f = -1;
        this.g = "sans-serif";
        this.d = false;
        this.h = 0.85f;
    }

    protected final c a(byte[] bytes, int length, boolean reset) throws e {
        String cueTextString;
        this.c.a(bytes, length);
        k kVar = this.c;
        a(kVar.b() >= 2);
        int h = kVar.h();
        if (h == 0) {
            cueTextString = "";
        } else {
            if (kVar.b() >= 2) {
                char f = kVar.f();
                if (f == 65279 || f == 65534) {
                    cueTextString = kVar.a(h, Charset.forName("UTF-16"));
                }
            }
            cueTextString = kVar.a(h, Charset.forName(Constants.ENCODING));
        }
        if (cueTextString.isEmpty()) {
            return b.a;
        }
        SpannableStringBuilder cueText = new SpannableStringBuilder(cueTextString);
        a(cueText, this.e, 0, 0, cueText.length(), 16711680);
        b(cueText, this.f, -1, 0, cueText.length(), 16711680);
        String str = this.g;
        String str2 = "sans-serif";
        int length2 = cueText.length();
        if (str != str2) {
            cueText.setSpan(new TypefaceSpan(str), 0, length2, 16711713);
        }
        float verticalPlacement = this.h;
        while (this.c.b() >= 8) {
            int position = this.c.d();
            int atomSize = this.c.n();
            int atomType = this.c.n();
            if (atomType == a) {
                a(this.c.b() >= 2);
                int styleRecordCount = this.c.h();
                for (int i = 0; i < styleRecordCount; i++) {
                    kVar = this.c;
                    a(kVar.b() >= 12);
                    length2 = kVar.h();
                    int h2 = kVar.h();
                    kVar.d(2);
                    h = kVar.g();
                    kVar.d(1);
                    int n = kVar.n();
                    a(cueText, h, this.e, length2, h2, 0);
                    b(cueText, n, this.f, length2, h2, 0);
                }
            } else if (atomType == b && this.d) {
                a(this.c.b() >= 2);
                verticalPlacement = s.a(((float) this.c.h()) / ((float) this.i), 0.0f, 0.95f);
            }
            this.c.c(position + atomSize);
        }
        return new b(new Cue(cueText, null, verticalPlacement, 0, 0, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE));
    }

    private static void a(SpannableStringBuilder cueText, int fontFace, int defaultFontFace, int start, int end, int spanPriority) {
        boolean isUnderlined = true;
        if (fontFace != defaultFontFace) {
            boolean isBold;
            boolean isItalic;
            int flags = spanPriority | 33;
            if ((fontFace & 1) != 0) {
                isBold = true;
            } else {
                isBold = false;
            }
            if ((fontFace & 2) != 0) {
                isItalic = true;
            } else {
                isItalic = false;
            }
            if (isBold) {
                if (isItalic) {
                    cueText.setSpan(new StyleSpan(3), start, end, flags);
                } else {
                    cueText.setSpan(new StyleSpan(1), start, end, flags);
                }
            } else if (isItalic) {
                cueText.setSpan(new StyleSpan(2), start, end, flags);
            }
            if ((fontFace & 4) == 0) {
                isUnderlined = false;
            }
            if (isUnderlined) {
                cueText.setSpan(new UnderlineSpan(), start, end, flags);
            }
            if (!isUnderlined && !isBold && !isItalic) {
                cueText.setSpan(new StyleSpan(0), start, end, flags);
            }
        }
    }

    private static void b(SpannableStringBuilder cueText, int colorRgba, int defaultColorRgba, int start, int end, int spanPriority) {
        if (colorRgba != defaultColorRgba) {
            cueText.setSpan(new ForegroundColorSpan(((colorRgba & 255) << 24) | (colorRgba >>> 8)), start, end, spanPriority | 33);
        }
    }

    private static void a(boolean checkValue) throws e {
        if (!checkValue) {
            throw new e("Unexpected subtitle format.");
        }
    }
}
