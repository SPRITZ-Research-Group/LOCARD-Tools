package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class a {
    private static e a = f.c;
    private static final String b = Character.toString(8206);
    private static final String c = Character.toString(8207);
    private static final a d = new a(false, 2, a);
    private static final a e = new a(true, 2, a);
    private final boolean f;
    private final int g;
    private final e h;

    public static final class a {
        private boolean a = a.a(Locale.getDefault());
        private int b = 2;
        private e c = a.a;

        public final a a() {
            if (this.b == 2 && this.c == a.a) {
                return this.a ? a.e : a.d;
            } else {
                return new a(this.a, this.b, this.c, (byte) 0);
            }
        }
    }

    private static class b {
        private static final byte[] a = new byte[1792];
        private final CharSequence b;
        private final boolean c = false;
        private final int d;
        private int e;
        private char f;

        static {
            for (int i = 0; i < 1792; i++) {
                a[i] = Character.getDirectionality(i);
            }
        }

        b(CharSequence text) {
            this.b = text;
            this.d = text.length();
        }

        final int a() {
            this.e = 0;
            int embeddingLevel = 0;
            int embeddingLevelDir = 0;
            int firstNonEmptyEmbeddingLevel = 0;
            while (this.e < this.d && firstNonEmptyEmbeddingLevel == 0) {
                byte directionality;
                this.f = this.b.charAt(this.e);
                int codePointAt;
                if (Character.isHighSurrogate(this.f)) {
                    codePointAt = Character.codePointAt(this.b, this.e);
                    this.e += Character.charCount(codePointAt);
                    directionality = Character.getDirectionality(codePointAt);
                } else {
                    this.e++;
                    directionality = a(this.f);
                    if (this.c) {
                        if (this.f == '<') {
                            codePointAt = this.e;
                            while (this.e < this.d) {
                                CharSequence charSequence = this.b;
                                int i = this.e;
                                this.e = i + 1;
                                this.f = charSequence.charAt(i);
                                if (this.f == '>') {
                                    directionality = (byte) 12;
                                } else if (this.f == '\"' || this.f == '\'') {
                                    char c = this.f;
                                    while (this.e < this.d) {
                                        CharSequence charSequence2 = this.b;
                                        int i2 = this.e;
                                        this.e = i2 + 1;
                                        char charAt = charSequence2.charAt(i2);
                                        this.f = charAt;
                                        if (charAt == c) {
                                        }
                                    }
                                }
                            }
                            this.e = codePointAt;
                            this.f = '<';
                            directionality = (byte) 13;
                        } else if (this.f == '&') {
                            while (this.e < this.d) {
                                CharSequence charSequence3 = this.b;
                                int i3 = this.e;
                                this.e = i3 + 1;
                                char charAt2 = charSequence3.charAt(i3);
                                this.f = charAt2;
                                if (charAt2 == ';') {
                                    directionality = (byte) 12;
                                }
                            }
                            directionality = (byte) 12;
                        }
                    }
                }
                switch (directionality) {
                    case (byte) 0:
                        if (embeddingLevel != 0) {
                            firstNonEmptyEmbeddingLevel = embeddingLevel;
                            break;
                        }
                        return -1;
                    case (byte) 1:
                    case (byte) 2:
                        if (embeddingLevel != 0) {
                            firstNonEmptyEmbeddingLevel = embeddingLevel;
                            break;
                        }
                        return 1;
                    case (byte) 9:
                        break;
                    case (byte) 14:
                    case (byte) 15:
                        embeddingLevel++;
                        embeddingLevelDir = -1;
                        break;
                    case (byte) 16:
                    case (byte) 17:
                        embeddingLevel++;
                        embeddingLevelDir = 1;
                        break;
                    case (byte) 18:
                        embeddingLevel--;
                        embeddingLevelDir = 0;
                        break;
                    default:
                        firstNonEmptyEmbeddingLevel = embeddingLevel;
                        break;
                }
            }
            if (firstNonEmptyEmbeddingLevel == 0) {
                return 0;
            }
            if (embeddingLevelDir != 0) {
                return embeddingLevelDir;
            }
            while (this.e > 0) {
                switch (c()) {
                    case (byte) 14:
                    case (byte) 15:
                        if (firstNonEmptyEmbeddingLevel != embeddingLevel) {
                            embeddingLevel--;
                            break;
                        }
                        return -1;
                    case (byte) 16:
                    case (byte) 17:
                        if (firstNonEmptyEmbeddingLevel != embeddingLevel) {
                            embeddingLevel--;
                            break;
                        }
                        return 1;
                    case (byte) 18:
                        embeddingLevel++;
                        break;
                    default:
                        break;
                }
            }
            return 0;
        }

        final int b() {
            this.e = this.d;
            int embeddingLevel = 0;
            int lastNonEmptyEmbeddingLevel = 0;
            while (this.e > 0) {
                switch (c()) {
                    case (byte) 0:
                        if (embeddingLevel != 0) {
                            if (lastNonEmptyEmbeddingLevel != 0) {
                                break;
                            }
                            lastNonEmptyEmbeddingLevel = embeddingLevel;
                            break;
                        }
                        return -1;
                    case (byte) 1:
                    case (byte) 2:
                        if (embeddingLevel != 0) {
                            if (lastNonEmptyEmbeddingLevel != 0) {
                                break;
                            }
                            lastNonEmptyEmbeddingLevel = embeddingLevel;
                            break;
                        }
                        return 1;
                    case (byte) 9:
                        break;
                    case (byte) 14:
                    case (byte) 15:
                        if (lastNonEmptyEmbeddingLevel != embeddingLevel) {
                            embeddingLevel--;
                            break;
                        }
                        return -1;
                    case (byte) 16:
                    case (byte) 17:
                        if (lastNonEmptyEmbeddingLevel != embeddingLevel) {
                            embeddingLevel--;
                            break;
                        }
                        return 1;
                    case (byte) 18:
                        embeddingLevel++;
                        break;
                    default:
                        if (lastNonEmptyEmbeddingLevel != 0) {
                            break;
                        }
                        lastNonEmptyEmbeddingLevel = embeddingLevel;
                        break;
                }
            }
            return 0;
        }

        private static byte a(char c) {
            return c < 1792 ? a[c] : Character.getDirectionality(c);
        }

        private byte c() {
            this.f = this.b.charAt(this.e - 1);
            if (Character.isLowSurrogate(this.f)) {
                int codePoint = Character.codePointBefore(this.b, this.e);
                this.e -= Character.charCount(codePoint);
                return Character.getDirectionality(codePoint);
            }
            this.e--;
            byte dirType = a(this.f);
            if (!this.c) {
                return dirType;
            }
            int i;
            CharSequence charSequence;
            int i2;
            if (this.f == '>') {
                i = this.e;
                while (this.e > 0) {
                    charSequence = this.b;
                    i2 = this.e - 1;
                    this.e = i2;
                    this.f = charSequence.charAt(i2);
                    if (this.f != '<') {
                        if (this.f == '>') {
                            break;
                        } else if (this.f == '\"' || this.f == '\'') {
                            char c = this.f;
                            while (this.e > 0) {
                                CharSequence charSequence2 = this.b;
                                int i3 = this.e - 1;
                                this.e = i3;
                                char charAt = charSequence2.charAt(i3);
                                this.f = charAt;
                                if (charAt == c) {
                                    break;
                                }
                            }
                        }
                    } else {
                        return (byte) 12;
                    }
                }
                this.e = i;
                this.f = '>';
                return (byte) 13;
            } else if (this.f != ';') {
                return dirType;
            } else {
                i = this.e;
                while (this.e > 0) {
                    charSequence = this.b;
                    i2 = this.e - 1;
                    this.e = i2;
                    this.f = charSequence.charAt(i2);
                    if (this.f != '&') {
                        if (this.f == ';') {
                            break;
                        }
                    }
                    return (byte) 12;
                }
                this.e = i;
                this.f = ';';
                return (byte) 13;
            }
        }
    }

    /* synthetic */ a(boolean x0, int x1, e x2, byte b) {
        this(x0, x1, x2);
    }

    static /* synthetic */ boolean a(Locale x0) {
        return g.a(x0) == 1;
    }

    public static a a() {
        return new a().a();
    }

    private a(boolean isRtlContext, int flags, e heuristic) {
        this.f = isRtlContext;
        this.g = flags;
        this.h = heuristic;
    }

    public final CharSequence a(CharSequence str) {
        e eVar = this.h;
        if (str == null) {
            return null;
        }
        Object obj;
        boolean a;
        CharSequence charSequence;
        boolean a2 = eVar.a(str, str.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if ((this.g & 2) != 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            if (a2) {
                eVar = f.b;
            } else {
                eVar = f.a;
            }
            a = eVar.a(str, str.length());
            if (!this.f && (a || c(str) == 1)) {
                charSequence = b;
            } else if (!this.f || (a && c(str) != -1)) {
                charSequence = "";
            } else {
                charSequence = c;
            }
            spannableStringBuilder.append(charSequence);
        }
        if (a2 != this.f) {
            spannableStringBuilder.append(a2 ? 8235 : 8234);
            spannableStringBuilder.append(str);
            spannableStringBuilder.append(8236);
        } else {
            spannableStringBuilder.append(str);
        }
        if (a2) {
            eVar = f.b;
        } else {
            eVar = f.a;
        }
        a = eVar.a(str, str.length());
        if (!this.f && (a || b(str) == 1)) {
            charSequence = b;
        } else if (!this.f || (a && b(str) != -1)) {
            charSequence = "";
        } else {
            charSequence = c;
        }
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private static int b(CharSequence str) {
        return new b(str).b();
    }

    private static int c(CharSequence str) {
        return new b(str).a();
    }
}
