package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat.b;
import android.support.v4.content.res.FontResourcesParserCompat.c;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(14)
@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
class g implements a {

    private interface a<T> {
        boolean a(T t);

        int b(T t);
    }

    g() {
    }

    private static <T> T a(T[] fonts, int style, a<T> extractor) {
        boolean isTargetItalic;
        int targetWeight = (style & 1) == 0 ? 400 : 700;
        if ((style & 2) != 0) {
            isTargetItalic = true;
        } else {
            isTargetItalic = false;
        }
        T best = null;
        int bestScore = Integer.MAX_VALUE;
        for (T font : fonts) {
            int i;
            int abs = Math.abs(extractor.b(font) - targetWeight) * 2;
            if (extractor.a(font) == isTargetItalic) {
                i = 0;
            } else {
                i = 1;
            }
            int score = abs + i;
            if (best == null || bestScore > score) {
                best = font;
                bestScore = score;
            }
        }
        return best;
    }

    protected final android.support.v4.provider.FontsContractCompat.a a(android.support.v4.provider.FontsContractCompat.a[] fonts, int style) {
        return (android.support.v4.provider.FontsContractCompat.a) a((Object[]) fonts, style, new a<android.support.v4.provider.FontsContractCompat.a>(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ boolean a(Object obj) {
                return ((android.support.v4.provider.FontsContractCompat.a) obj).d();
            }

            public final /* synthetic */ int b(Object obj) {
                return ((android.support.v4.provider.FontsContractCompat.a) obj).c();
            }
        });
    }

    protected static Typeface a(Context context, InputStream is) {
        Typeface typeface = null;
        File tmpFile = h.a(context);
        if (tmpFile != null) {
            try {
                if (h.a(tmpFile, is)) {
                    typeface = Typeface.createFromFile(tmpFile.getPath());
                    tmpFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tmpFile.delete();
            }
        }
        return typeface;
    }

    public Typeface a(Context context, @NonNull android.support.v4.provider.FontsContractCompat.a[] fonts, int style) {
        Typeface typeface = null;
        if (fonts.length > 0) {
            Closeable is = null;
            try {
                is = context.getContentResolver().openInputStream(a(fonts, style).a());
                typeface = a(context, (InputStream) is);
            } catch (IOException e) {
            } finally {
                h.a(is);
            }
        }
        return typeface;
    }

    @Nullable
    public Typeface a(Context context, Resources resources, int id, String path, int style) {
        Typeface typeface = null;
        File tmpFile = h.a(context);
        if (tmpFile != null) {
            try {
                if (h.a(tmpFile, resources, id)) {
                    typeface = Typeface.createFromFile(tmpFile.getPath());
                    tmpFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tmpFile.delete();
            }
        }
        return typeface;
    }

    @Nullable
    public Typeface a(Context context, b entry, Resources resources, int style) {
        c best = (c) a(entry.a(), style, new a<c>(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ boolean a(Object obj) {
                return ((c) obj).c();
            }

            public final /* bridge */ /* synthetic */ int b(Object obj) {
                return ((c) obj).b();
            }
        });
        if (best == null) {
            return null;
        }
        return c.a(context, resources, best.d(), best.a(), style);
    }
}
