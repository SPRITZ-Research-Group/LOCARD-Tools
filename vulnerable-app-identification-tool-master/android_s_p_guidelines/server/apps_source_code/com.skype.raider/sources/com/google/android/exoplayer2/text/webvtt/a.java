package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.d.c;
import com.google.android.exoplayer2.d.k;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class a {
    private static final Pattern a = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final k b = new k();
    private final StringBuilder c = new StringBuilder();

    public final WebvttCssStyle a(k input) {
        String selector;
        int d;
        String a;
        String str;
        int c;
        Object obj;
        int i;
        this.c.setLength(0);
        int initialInputPosition = input.d();
        do {
        } while (!TextUtils.isEmpty(input.x()));
        this.b.a(input.a, input.d());
        this.b.c(initialInputPosition);
        k kVar = this.b;
        StringBuilder stringBuilder = this.c;
        b(kVar);
        if (kVar.b() < 5) {
            selector = null;
        } else {
            if ("::cue".equals(kVar.e(5))) {
                d = kVar.d();
                a = a(kVar, stringBuilder);
                if (a == null) {
                    selector = null;
                } else if ("{".equals(a)) {
                    kVar.c(d);
                    selector = "";
                } else {
                    str = null;
                    if ("(".equals(a)) {
                        int d2 = kVar.d();
                        c = kVar.c();
                        obj = null;
                        while (d2 < c && obj == null) {
                            i = d2 + 1;
                            obj = ((char) kVar.a[d2]) == ')' ? 1 : null;
                            d2 = i;
                        }
                        str = kVar.e((d2 - 1) - kVar.d()).trim();
                    }
                    a = a(kVar, stringBuilder);
                    selector = (!")".equals(a) || a == null) ? null : str;
                }
            } else {
                selector = null;
            }
        }
        if (selector == null || !"{".equals(a(this.b, this.c))) {
            return null;
        }
        WebvttCssStyle style = new WebvttCssStyle();
        if (!"".equals(selector)) {
            d = selector.indexOf(91);
            if (d != -1) {
                Matcher matcher = a.matcher(selector.substring(d));
                if (matcher.matches()) {
                    style.c(matcher.group(1));
                }
                selector = selector.substring(0, d);
            }
            String[] split = selector.split("\\.");
            a = split[0];
            i = a.indexOf(35);
            if (i != -1) {
                style.b(a.substring(0, i));
                style.a(a.substring(i + 1));
            } else {
                style.b(a);
            }
            if (split.length > 1) {
                style.a((String[]) Arrays.copyOfRange(split, 1, split.length));
            }
        }
        String token = null;
        boolean blockEndFound = false;
        while (!blockEndFound) {
            int position = this.b.d();
            token = a(this.b, this.c);
            blockEndFound = token == null || "}".equals(token);
            if (!blockEndFound) {
                this.b.c(position);
                k kVar2 = this.b;
                StringBuilder stringBuilder2 = this.c;
                b(kVar2);
                String b = b(kVar2, stringBuilder2);
                if (!"".equals(b) && ":".equals(a(kVar2, stringBuilder2))) {
                    b(kVar2);
                    stringBuilder = new StringBuilder();
                    obj = null;
                    while (obj == null) {
                        c = kVar2.d();
                        String a2 = a(kVar2, stringBuilder2);
                        if (a2 == null) {
                            str = null;
                            break;
                        } else if ("}".equals(a2) || ";".equals(a2)) {
                            kVar2.c(c);
                            obj = 1;
                        } else {
                            stringBuilder.append(a2);
                        }
                    }
                    str = stringBuilder.toString();
                    if (!(str == null || "".equals(str))) {
                        int d3 = kVar2.d();
                        String a3 = a(kVar2, stringBuilder2);
                        if (!";".equals(a3)) {
                            if ("}".equals(a3)) {
                                kVar2.c(d3);
                            }
                        }
                        if ("color".equals(b)) {
                            style.a(c.b(str));
                        } else if ("background-color".equals(b)) {
                            style.b(c.b(str));
                        } else if ("text-decoration".equals(b)) {
                            if ("underline".equals(str)) {
                                style.d();
                            }
                        } else if ("font-family".equals(b)) {
                            style.d(str);
                        } else if ("font-weight".equals(b)) {
                            if ("bold".equals(str)) {
                                style.e();
                            }
                        } else if ("font-style".equals(b) && "italic".equals(str)) {
                            style.f();
                        }
                    }
                }
            }
        }
        return !"}".equals(token) ? null : style;
    }

    private static void b(k input) {
        boolean skipping = true;
        while (input.b() > 0 && skipping) {
            int i;
            switch ((char) input.a[input.d()]) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    input.d(1);
                    i = 1;
                    break;
                default:
                    i = 0;
                    break;
            }
            if (i == 0) {
                int d = input.d();
                i = input.c();
                byte[] bArr = input.a;
                if (d + 2 <= i) {
                    int i2 = d + 1;
                    if (bArr[d] == (byte) 47) {
                        d = i2 + 1;
                        if (bArr[i2] == (byte) 42) {
                            while (true) {
                                i2 = d;
                                if (i2 + 1 < i) {
                                    d = i2 + 1;
                                    if (((char) bArr[i2]) == '*' && ((char) bArr[d]) == '/') {
                                        i = d + 1;
                                        d = i;
                                    }
                                } else {
                                    input.d(i - input.d());
                                    i = 1;
                                    if (i == 0) {
                                        skipping = false;
                                    }
                                }
                            }
                        }
                    }
                }
                i = 0;
                if (i == 0) {
                    skipping = false;
                }
            }
            skipping = true;
        }
    }

    private static String a(k input, StringBuilder stringBuilder) {
        b(input);
        if (input.b() == 0) {
            return null;
        }
        String identifier = b(input, stringBuilder);
        return "".equals(identifier) ? ((char) input.g()) : identifier;
    }

    private static String b(k input, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        int position = input.d();
        int limit = input.c();
        boolean identifierEndFound = false;
        while (position < limit && !identifierEndFound) {
            char c = (char) input.a[position];
            if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !((c >= '0' && c <= '9') || c == '#' || c == '-' || c == '.' || c == '_'))) {
                identifierEndFound = true;
            } else {
                position++;
                stringBuilder.append(c);
            }
        }
        input.d(position - input.d());
        return stringBuilder.toString();
    }
}
