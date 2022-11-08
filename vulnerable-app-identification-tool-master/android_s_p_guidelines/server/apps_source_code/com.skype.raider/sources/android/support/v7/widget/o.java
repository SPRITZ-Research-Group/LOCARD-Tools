package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.appcompat.a.j;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

final class o {
    private static final RectF a = new RectF();
    private static Hashtable<String, Method> b = new Hashtable();
    private int c = 0;
    private boolean d = false;
    private float e = -1.0f;
    private float f = -1.0f;
    private float g = -1.0f;
    private int[] h = new int[0];
    private boolean i = false;
    private TextPaint j;
    private final TextView k;
    private final Context l;

    o(TextView textView) {
        this.k = textView;
        this.l = this.k.getContext();
    }

    final void a(AttributeSet attrs, int defStyleAttr) {
        float autoSizeMinTextSizeInPx = -1.0f;
        float autoSizeMaxTextSizeInPx = -1.0f;
        float autoSizeStepGranularityInPx = -1.0f;
        TypedArray a = this.l.obtainStyledAttributes(attrs, j.AppCompatTextView, defStyleAttr, 0);
        if (a.hasValue(j.AppCompatTextView_autoSizeTextType)) {
            this.c = a.getInt(j.AppCompatTextView_autoSizeTextType, 0);
        }
        if (a.hasValue(j.AppCompatTextView_autoSizeStepGranularity)) {
            autoSizeStepGranularityInPx = a.getDimension(j.AppCompatTextView_autoSizeStepGranularity, -1.0f);
        }
        if (a.hasValue(j.AppCompatTextView_autoSizeMinTextSize)) {
            autoSizeMinTextSizeInPx = a.getDimension(j.AppCompatTextView_autoSizeMinTextSize, -1.0f);
        }
        if (a.hasValue(j.AppCompatTextView_autoSizeMaxTextSize)) {
            autoSizeMaxTextSizeInPx = a.getDimension(j.AppCompatTextView_autoSizeMaxTextSize, -1.0f);
        }
        if (a.hasValue(j.AppCompatTextView_autoSizePresetSizes)) {
            int autoSizeStepSizeArrayResId = a.getResourceId(j.AppCompatTextView_autoSizePresetSizes, 0);
            if (autoSizeStepSizeArrayResId > 0) {
                TypedArray autoSizePreDefTextSizes = a.getResources().obtainTypedArray(autoSizeStepSizeArrayResId);
                int length = autoSizePreDefTextSizes.length();
                int[] iArr = new int[length];
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        iArr[i] = autoSizePreDefTextSizes.getDimensionPixelSize(i, -1);
                    }
                    this.h = a(iArr);
                    h();
                }
                autoSizePreDefTextSizes.recycle();
            }
        }
        a.recycle();
        if (!j()) {
            this.c = 0;
        } else if (this.c == 1) {
            if (!this.i) {
                DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                if (autoSizeMinTextSizeInPx == -1.0f) {
                    autoSizeMinTextSizeInPx = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (autoSizeMaxTextSizeInPx == -1.0f) {
                    autoSizeMaxTextSizeInPx = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (autoSizeStepGranularityInPx == -1.0f) {
                    autoSizeStepGranularityInPx = 1.0f;
                }
                a(autoSizeMinTextSizeInPx, autoSizeMaxTextSizeInPx, autoSizeStepGranularityInPx);
            }
            i();
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void a(int autoSizeTextType) {
        if (j()) {
            switch (autoSizeTextType) {
                case 0:
                    this.c = 0;
                    this.f = -1.0f;
                    this.g = -1.0f;
                    this.e = -1.0f;
                    this.h = new int[0];
                    this.d = false;
                    return;
                case 1:
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (i()) {
                        f();
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Unknown auto-size text type: " + autoSizeTextType);
            }
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void a(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
        if (j()) {
            DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(unit, (float) autoSizeMinTextSize, displayMetrics), TypedValue.applyDimension(unit, (float) autoSizeMaxTextSize, displayMetrics), TypedValue.applyDimension(unit, (float) autoSizeStepGranularity, displayMetrics));
            if (i()) {
                f();
            }
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void a(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
        if (j()) {
            int presetSizesLength = presetSizes.length;
            if (presetSizesLength > 0) {
                int[] presetSizesInPx = new int[presetSizesLength];
                if (unit == 0) {
                    presetSizesInPx = Arrays.copyOf(presetSizes, presetSizesLength);
                } else {
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    for (int i = 0; i < presetSizesLength; i++) {
                        presetSizesInPx[i] = Math.round(TypedValue.applyDimension(unit, (float) presetSizes[i], displayMetrics));
                    }
                }
                this.h = a(presetSizesInPx);
                if (!h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(presetSizes));
                }
            }
            this.i = false;
            if (i()) {
                f();
            }
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final int a() {
        return this.c;
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final int b() {
        return Math.round(this.e);
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final int c() {
        return Math.round(this.f);
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final int d() {
        return Math.round(this.g);
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final int[] e() {
        return this.h;
    }

    private boolean h() {
        int sizesLength = this.h.length;
        this.i = sizesLength > 0;
        if (this.i) {
            this.c = 1;
            this.f = (float) this.h[0];
            this.g = (float) this.h[sizesLength - 1];
            this.e = -1.0f;
        }
        return this.i;
    }

    private static int[] a(int[] presetValues) {
        if (presetValuesLength == 0) {
            return presetValues;
        }
        int i;
        Arrays.sort(presetValues);
        List<Integer> uniqueValidSizes = new ArrayList();
        for (int currentPresetValue : presetValues) {
            if (currentPresetValue > 0 && Collections.binarySearch(uniqueValidSizes, Integer.valueOf(currentPresetValue)) < 0) {
                uniqueValidSizes.add(Integer.valueOf(currentPresetValue));
            }
        }
        if (presetValuesLength == uniqueValidSizes.size()) {
            return presetValues;
        }
        int uniqueValidSizesLength = uniqueValidSizes.size();
        int[] cleanedUpSizes = new int[uniqueValidSizesLength];
        for (i = 0; i < uniqueValidSizesLength; i++) {
            cleanedUpSizes[i] = ((Integer) uniqueValidSizes.get(i)).intValue();
        }
        return cleanedUpSizes;
    }

    private void a(float autoSizeMinTextSizeInPx, float autoSizeMaxTextSizeInPx, float autoSizeStepGranularityInPx) throws IllegalArgumentException {
        if (autoSizeMinTextSizeInPx <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + autoSizeMinTextSizeInPx + "px) is less or equal to (0px)");
        } else if (autoSizeMaxTextSizeInPx <= autoSizeMinTextSizeInPx) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + autoSizeMaxTextSizeInPx + "px) is less or equal to minimum auto-size text size (" + autoSizeMinTextSizeInPx + "px)");
        } else if (autoSizeStepGranularityInPx <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + autoSizeStepGranularityInPx + "px) is less or equal to (0px)");
        } else {
            this.c = 1;
            this.f = autoSizeMinTextSizeInPx;
            this.g = autoSizeMaxTextSizeInPx;
            this.e = autoSizeStepGranularityInPx;
            this.i = false;
        }
    }

    private boolean i() {
        if (j() && this.c == 1) {
            if (!this.i || this.h.length == 0) {
                int autoSizeValuesLength = 1;
                float currentSize = (float) Math.round(this.f);
                while (Math.round(this.e + currentSize) <= Math.round(this.g)) {
                    autoSizeValuesLength++;
                    currentSize += this.e;
                }
                int[] autoSizeTextSizesInPx = new int[autoSizeValuesLength];
                float sizeToAdd = this.f;
                for (int i = 0; i < autoSizeValuesLength; i++) {
                    autoSizeTextSizesInPx[i] = Math.round(sizeToAdd);
                    sizeToAdd += this.e;
                }
                this.h = a(autoSizeTextSizesInPx);
            }
            this.d = true;
        } else {
            this.d = false;
        }
        return this.d;
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void f() {
        if (g()) {
            if (this.d) {
                if (this.k.getMeasuredHeight() > 0 && this.k.getMeasuredWidth() > 0) {
                    int availableWidth;
                    if (((Boolean) a(this.k, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue()) {
                        availableWidth = 1048576;
                    } else {
                        availableWidth = (this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft()) - this.k.getTotalPaddingRight();
                    }
                    int availableHeight = (this.k.getHeight() - this.k.getCompoundPaddingBottom()) - this.k.getCompoundPaddingTop();
                    if (availableWidth > 0 && availableHeight > 0) {
                        synchronized (a) {
                            a.setEmpty();
                            a.right = (float) availableWidth;
                            a.bottom = (float) availableHeight;
                            RectF rectF = a;
                            int length = this.h.length;
                            if (length == 0) {
                                throw new IllegalStateException("No available text sizes to choose from.");
                            }
                            int i = length - 1;
                            length = 0;
                            int i2 = 1;
                            while (i2 <= i) {
                                StaticLayout build;
                                Object obj;
                                int i3 = (i2 + i) / 2;
                                length = this.h[i3];
                                CharSequence text = this.k.getText();
                                int maxLines = VERSION.SDK_INT >= 16 ? this.k.getMaxLines() : -1;
                                if (this.j == null) {
                                    this.j = new TextPaint();
                                } else {
                                    this.j.reset();
                                }
                                this.j.set(this.k.getPaint());
                                this.j.setTextSize((float) length);
                                Alignment alignment = (Alignment) a(this.k, "getLayoutAlignment", Alignment.ALIGN_NORMAL);
                                if (VERSION.SDK_INT >= 23) {
                                    build = Builder.obtain(text, 0, text.length(), this.j, Math.round(rectF.right)).setAlignment(alignment).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines).setTextDirection((TextDirectionHeuristic) a(this.k, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR)).build();
                                } else {
                                    float lineSpacingMultiplier;
                                    float lineSpacingExtra;
                                    boolean includeFontPadding;
                                    int round = Math.round(rectF.right);
                                    if (VERSION.SDK_INT >= 16) {
                                        lineSpacingMultiplier = this.k.getLineSpacingMultiplier();
                                        lineSpacingExtra = this.k.getLineSpacingExtra();
                                        includeFontPadding = this.k.getIncludeFontPadding();
                                    } else {
                                        lineSpacingMultiplier = ((Float) a(this.k, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
                                        lineSpacingExtra = ((Float) a(this.k, "getLineSpacingExtra", Float.valueOf(0.0f))).floatValue();
                                        includeFontPadding = ((Boolean) a(this.k, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue();
                                    }
                                    build = new StaticLayout(text, this.j, round, alignment, lineSpacingMultiplier, lineSpacingExtra, includeFontPadding);
                                }
                                if (maxLines != -1 && (build.getLineCount() > maxLines || build.getLineEnd(build.getLineCount() - 1) != text.length())) {
                                    obj = null;
                                } else if (((float) build.getHeight()) > rectF.bottom) {
                                    obj = null;
                                } else {
                                    obj = 1;
                                }
                                if (obj != null) {
                                    length = i2;
                                    i2 = i3 + 1;
                                } else {
                                    length = i3 - 1;
                                    i = length;
                                }
                            }
                            float optimalTextSize = (float) this.h[length];
                            if (optimalTextSize != this.k.getTextSize()) {
                                a(0, optimalTextSize);
                            }
                        }
                    } else {
                        return;
                    }
                }
                return;
            }
            this.d = true;
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void a(int unit, float size) {
        Resources res;
        if (this.l == null) {
            res = Resources.getSystem();
        } else {
            res = this.l.getResources();
        }
        float applyDimension = TypedValue.applyDimension(unit, size, res.getDisplayMetrics());
        if (applyDimension != this.k.getPaint().getTextSize()) {
            boolean isInLayout;
            this.k.getPaint().setTextSize(applyDimension);
            if (VERSION.SDK_INT >= 18) {
                isInLayout = this.k.isInLayout();
            } else {
                isInLayout = false;
            }
            if (this.k.getLayout() != null) {
                this.d = false;
                try {
                    Method a = a("nullLayouts");
                    if (a != null) {
                        a.invoke(this.k, new Object[0]);
                    }
                } catch (Exception e) {
                }
                if (isInLayout) {
                    this.k.forceLayout();
                } else {
                    this.k.requestLayout();
                }
                this.k.invalidate();
            }
        }
    }

    private static <T> T a(@android.support.annotation.NonNull java.lang.Object r3, @android.support.annotation.NonNull java.lang.String r4, @android.support.annotation.NonNull T r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:289)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:143)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
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
        r1 = a(r4);	 Catch:{ Exception -> 0x000e }
        r2 = 0;	 Catch:{ Exception -> 0x000e }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x000e }
        r0 = r1.invoke(r3, r2);	 Catch:{ Exception -> 0x000e }
        if (r0 != 0) goto L_0x000d;
    L_0x000d:
        return r0;
    L_0x000e:
        r1 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0021 }
        r2 = "Failed to invoke TextView#";	 Catch:{ all -> 0x0021 }
        r1.<init>(r2);	 Catch:{ all -> 0x0021 }
        r1 = r1.append(r4);	 Catch:{ all -> 0x0021 }
        r2 = "() method";	 Catch:{ all -> 0x0021 }
        r1.append(r2);	 Catch:{ all -> 0x0021 }
        r0 = r5;
        goto L_0x000d;
    L_0x0021:
        r1 = move-exception;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.o.a(java.lang.Object, java.lang.String, java.lang.Object):T");
    }

    @Nullable
    private static Method a(@NonNull String methodName) {
        try {
            Method method = (Method) b.get(methodName);
            if (method != null) {
                return method;
            }
            method = TextView.class.getDeclaredMethod(methodName, new Class[0]);
            if (method == null) {
                return method;
            }
            method.setAccessible(true);
            b.put(methodName, method);
            return method;
        } catch (Exception e) {
            new StringBuilder("Failed to retrieve TextView#").append(methodName).append("() method");
            return null;
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final boolean g() {
        return j() && this.c != 0;
    }

    private boolean j() {
        return !(this.k instanceof AppCompatEditText);
    }
}
