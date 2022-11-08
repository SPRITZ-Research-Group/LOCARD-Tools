package androidx.core.widget;

import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build.VERSION;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode.Callback;
import android.widget.TextView;
import defpackage.fx;
import defpackage.fy;
import defpackage.fz;
import defpackage.gs;
import java.lang.reflect.Field;

public final class l {
    private static Field a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    private static java.lang.reflect.Field a(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.core.widget.l.a(java.lang.String):java.lang.reflect.Field. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = android.widget.TextView.class;	 Catch:{ NoSuchFieldException -> 0x000b }
        r0 = r0.getDeclaredField(r4);	 Catch:{ NoSuchFieldException -> 0x000b }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ NoSuchFieldException -> 0x000c }
        goto L_0x0024;
    L_0x000b:
        r0 = 0;
    L_0x000c:
        r1 = "TextViewCompat";
        r2 = new java.lang.StringBuilder;
        r3 = "Could not retrieve ";
        r2.<init>(r3);
        r2.append(r4);
        r4 = " field.";
        r2.append(r4);
        r4 = r2.toString();
        android.util.Log.e(r1, r4);
    L_0x0024:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.l.a(java.lang.String):java.lang.reflect.Field");
    }

    private static int a(java.lang.reflect.Field r1, android.widget.TextView r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.core.widget.l.a(java.lang.reflect.Field, android.widget.TextView):int. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = r1.getInt(r2);	 Catch:{ IllegalAccessException -> 0x0005 }
        return r2;
    L_0x0005:
        r2 = new java.lang.StringBuilder;
        r0 = "Could not retrieve value of ";
        r2.<init>(r0);
        r1 = r1.getName();
        r2.append(r1);
        r1 = " field.";
        r2.append(r1);
        r1 = -1;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.l.a(java.lang.reflect.Field, android.widget.TextView):int");
    }

    public static void a(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (VERSION.SDK_INT >= 18) {
            textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else if (VERSION.SDK_INT >= 17) {
            Object obj = 1;
            if (textView.getLayoutDirection() != 1) {
                obj = null;
            }
            Drawable drawable5 = obj != null ? drawable3 : drawable;
            if (obj == null) {
                drawable = drawable3;
            }
            textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
        } else {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }
    }

    public static int a(TextView textView) {
        if (VERSION.SDK_INT >= 16) {
            return textView.getMaxLines();
        }
        if (!d) {
            c = a("mMaxMode");
            d = true;
        }
        if (c != null && a(c, textView) == 1) {
            if (!b) {
                a = a("mMaximum");
                b = true;
            }
            if (a != null) {
                return a(a, textView);
            }
        }
        return -1;
    }

    public static void a(TextView textView, int i) {
        if (VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    public static Drawable[] b(TextView textView) {
        if (VERSION.SDK_INT >= 18) {
            return textView.getCompoundDrawablesRelative();
        }
        if (VERSION.SDK_INT < 17) {
            return textView.getCompoundDrawables();
        }
        Object obj = 1;
        if (textView.getLayoutDirection() != 1) {
            obj = null;
        }
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        if (obj != null) {
            Drawable drawable = compoundDrawables[2];
            Drawable drawable2 = compoundDrawables[0];
            compoundDrawables[0] = drawable;
            compoundDrawables[2] = drawable2;
        }
        return compoundDrawables;
    }

    public static Callback a(TextView textView, Callback callback) {
        return (VERSION.SDK_INT < 26 || VERSION.SDK_INT > 27 || (callback instanceof m)) ? callback : new m(callback, textView);
    }

    public static void b(TextView textView, int i) {
        gs.a(i);
        if (VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        int i2;
        FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        if (VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) {
            i2 = fontMetricsInt.top;
        } else {
            i2 = fontMetricsInt.ascent;
        }
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i - (-i2), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void c(TextView textView, int i) {
        int i2;
        gs.a(i);
        FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        if (VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) {
            i2 = fontMetricsInt.bottom;
        } else {
            i2 = fontMetricsInt.descent;
        }
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void d(TextView textView, int i) {
        gs.a(i);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i != fontMetricsInt) {
            textView.setLineSpacing((float) (i - fontMetricsInt), 1.0f);
        }
    }

    public static fy c(TextView textView) {
        if (VERSION.SDK_INT >= 28) {
            return new fy(textView.getTextMetricsParams());
        }
        fz fzVar = new fz(new TextPaint(textView.getPaint()));
        if (VERSION.SDK_INT >= 23) {
            fzVar.a(textView.getBreakStrategy());
            fzVar.b(textView.getHyphenationFrequency());
        }
        if (VERSION.SDK_INT >= 18) {
            TextDirectionHeuristic textDirectionHeuristic;
            if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
                textDirectionHeuristic = TextDirectionHeuristics.LTR;
            } else {
                int i = 0;
                if (VERSION.SDK_INT < 28 || (textView.getInputType() & 15) != 3) {
                    if (textView.getLayoutDirection() == 1) {
                        i = 1;
                    }
                    switch (textView.getTextDirection()) {
                        case 2:
                            textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                            break;
                        case 3:
                            textDirectionHeuristic = TextDirectionHeuristics.LTR;
                            break;
                        case 4:
                            textDirectionHeuristic = TextDirectionHeuristics.RTL;
                            break;
                        case 5:
                            textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                            break;
                        case 6:
                            textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                            break;
                        case 7:
                            textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                            break;
                        default:
                            if (i == 0) {
                                textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                                break;
                            }
                            textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                            break;
                    }
                }
                byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
                if (directionality == (byte) 1 || directionality == (byte) 2) {
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                } else {
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                }
            }
            fzVar.a(textDirectionHeuristic);
        }
        return fzVar.a();
    }

    public static void a(TextView textView, fx fxVar) {
        if (c(textView).a(fxVar.a())) {
            textView.setText(fxVar);
            return;
        }
        throw new IllegalArgumentException("Given text can not be applied to TextView.");
    }
}
