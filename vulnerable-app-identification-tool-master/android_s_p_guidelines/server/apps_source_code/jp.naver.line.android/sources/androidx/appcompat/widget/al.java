package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.v;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

final class al {
    private static final RectF a = new RectF();
    private static ConcurrentHashMap<String, Method> b = new ConcurrentHashMap();
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

    al(TextView textView) {
        this.k = textView;
        this.l = this.k.getContext();
    }

    final void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(attributeSet, v.AppCompatTextView, i, 0);
        if (obtainStyledAttributes.hasValue(v.AppCompatTextView_autoSizeTextType)) {
            this.c = obtainStyledAttributes.getInt(v.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(v.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(v.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(v.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(v.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(v.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(v.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(v.AppCompatTextView_autoSizePresetSizes)) {
            int resourceId = obtainStyledAttributes.getResourceId(v.AppCompatTextView_autoSizePresetSizes, 0);
            if (resourceId > 0) {
                TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
                int length = obtainTypedArray.length();
                int[] iArr = new int[length];
                if (length > 0) {
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr[i2] = obtainTypedArray.getDimensionPixelSize(i2, -1);
                    }
                    this.h = a(iArr);
                    h();
                }
                obtainTypedArray.recycle();
            }
        }
        obtainStyledAttributes.recycle();
        if (!j()) {
            this.c = 0;
        } else if (this.c == 1) {
            if (!this.i) {
                DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                a(dimension2, dimension3, dimension);
            }
            i();
        }
    }

    final void a(int i) {
        if (j()) {
            switch (i) {
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
                    break;
                default:
                    throw new IllegalArgumentException("Unknown auto-size text type: ".concat(String.valueOf(i)));
            }
        }
    }

    final void a(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (j()) {
            DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i4, (float) i, displayMetrics), TypedValue.applyDimension(i4, (float) i2, displayMetrics), TypedValue.applyDimension(i4, (float) i3, displayMetrics));
            if (i()) {
                f();
            }
        }
    }

    final void a(int[] iArr, int i) throws IllegalArgumentException {
        if (j()) {
            int length = iArr.length;
            int i2 = 0;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    while (i2 < length) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, (float) iArr[i2], displayMetrics));
                        i2++;
                    }
                }
                this.h = a(iArr2);
                if (!h()) {
                    StringBuilder stringBuilder = new StringBuilder("None of the preset sizes is valid: ");
                    stringBuilder.append(Arrays.toString(iArr));
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            this.i = false;
            if (i()) {
                f();
            }
        }
    }

    final int a() {
        return this.c;
    }

    final int b() {
        return Math.round(this.e);
    }

    final int c() {
        return Math.round(this.f);
    }

    final int d() {
        return Math.round(this.g);
    }

    final int[] e() {
        return this.h;
    }

    private boolean h() {
        int length = this.h.length;
        this.i = length > 0;
        if (this.i) {
            this.c = 1;
            this.f = (float) this.h[0];
            this.g = (float) this.h[length - 1];
            this.e = -1.0f;
        }
        return this.i;
    }

    private static int[] a(int[] iArr) {
        if (r0 == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        List arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (r0 == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    private void a(float f, float f2, float f3) throws IllegalArgumentException {
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            StringBuilder stringBuilder = new StringBuilder("Minimum auto-size text size (");
            stringBuilder.append(f);
            stringBuilder.append("px) is less or equal to (0px)");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (f2 <= f) {
            StringBuilder stringBuilder2 = new StringBuilder("Maximum auto-size text size (");
            stringBuilder2.append(f2);
            stringBuilder2.append("px) is less or equal to minimum auto-size text size (");
            stringBuilder2.append(f);
            stringBuilder2.append("px)");
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else if (f3 > BitmapDescriptorFactory.HUE_RED) {
            this.c = 1;
            this.f = f;
            this.g = f2;
            this.e = f3;
            this.i = false;
        } else {
            StringBuilder stringBuilder3 = new StringBuilder("The auto-size step granularity (");
            stringBuilder3.append(f3);
            stringBuilder3.append("px) is less or equal to (0px)");
            throw new IllegalArgumentException(stringBuilder3.toString());
        }
    }

    private boolean i() {
        int i = 0;
        if (j() && this.c == 1) {
            if (!this.i || this.h.length == 0) {
                float round = (float) Math.round(this.f);
                int i2 = 1;
                while (Math.round(this.e + round) <= Math.round(this.g)) {
                    i2++;
                    round += this.e;
                }
                int[] iArr = new int[i2];
                float f = this.f;
                while (i < i2) {
                    iArr[i] = Math.round(f);
                    f += this.e;
                    i++;
                }
                this.h = a(iArr);
            }
            this.d = true;
        } else {
            this.d = false;
        }
        return this.d;
    }

    final void f() {
        if (g()) {
            if (this.d) {
                if (this.k.getMeasuredHeight() > 0 && this.k.getMeasuredWidth() > 0) {
                    int i;
                    if (((Boolean) a(this.k, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue()) {
                        i = PKIFailureInfo.badCertTemplate;
                    } else {
                        i = (this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft()) - this.k.getTotalPaddingRight();
                    }
                    int height = (this.k.getHeight() - this.k.getCompoundPaddingBottom()) - this.k.getCompoundPaddingTop();
                    if (i > 0 && height > 0) {
                        synchronized (a) {
                            a.setEmpty();
                            a.right = (float) i;
                            a.bottom = (float) height;
                            float a = (float) a(a);
                            if (a != this.k.getTextSize()) {
                                a(0, a);
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

    final void a(int i, float f) {
        Resources system;
        if (this.l == null) {
            system = Resources.getSystem();
        } else {
            system = this.l.getResources();
        }
        a(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
    }

    private void a(float f) {
        if (f != this.k.getPaint().getTextSize()) {
            this.k.getPaint().setTextSize(f);
            boolean isInLayout = VERSION.SDK_INT >= 18 ? this.k.isInLayout() : false;
            if (this.k.getLayout() != null) {
                this.d = false;
                try {
                    Method a = a("nullLayouts");
                    if (a != null) {
                        a.invoke(this.k, new Object[0]);
                    }
                } catch (Throwable e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
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

    private int a(RectF rectF) {
        int length = this.h.length;
        if (length != 0) {
            length--;
            int i = 1;
            int i2 = 0;
            while (i <= length) {
                i2 = (i + length) / 2;
                if (a(this.h[i2], rectF)) {
                    int i3 = i2 + 1;
                    i2 = i;
                    i = i3;
                } else {
                    i2--;
                    length = i2;
                }
            }
            return this.h[i2];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(int i, RectF rectF) {
        StaticLayout a;
        CharSequence text = this.k.getText();
        TransformationMethod transformationMethod = this.k.getTransformationMethod();
        if (transformationMethod != null) {
            CharSequence transformation = transformationMethod.getTransformation(text, this.k);
            if (transformation != null) {
                text = transformation;
            }
        }
        int maxLines = VERSION.SDK_INT >= 16 ? this.k.getMaxLines() : -1;
        if (this.j == null) {
            this.j = new TextPaint();
        } else {
            this.j.reset();
        }
        this.j.set(this.k.getPaint());
        this.j.setTextSize((float) i);
        Alignment alignment = (Alignment) a(this.k, "getLayoutAlignment", Alignment.ALIGN_NORMAL);
        if (VERSION.SDK_INT >= 23) {
            a = a(text, alignment, Math.round(rectF.right), maxLines);
        } else {
            a = a(text, alignment, Math.round(rectF.right));
        }
        return (maxLines == -1 || (a.getLineCount() <= maxLines && a.getLineEnd(a.getLineCount() - 1) == text.length())) && ((float) a.getHeight()) <= rectF.bottom;
    }

    private StaticLayout a(CharSequence charSequence, Alignment alignment, int i, int i2) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) a(this.k, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        Builder hyphenationFrequency = Builder.obtain(charSequence, 0, charSequence.length(), this.j, i).setAlignment(alignment).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency());
        if (i2 == -1) {
            i2 = BaseClientBuilder.API_PRIORITY_OTHER;
        }
        return hyphenationFrequency.setMaxLines(i2).setTextDirection(textDirectionHeuristic).build();
    }

    private StaticLayout a(CharSequence charSequence, Alignment alignment, int i) {
        float lineSpacingMultiplier;
        float lineSpacingExtra;
        boolean includeFontPadding;
        if (VERSION.SDK_INT >= 16) {
            lineSpacingMultiplier = this.k.getLineSpacingMultiplier();
            lineSpacingExtra = this.k.getLineSpacingExtra();
            includeFontPadding = this.k.getIncludeFontPadding();
        } else {
            lineSpacingMultiplier = ((Float) a(this.k, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
            lineSpacingExtra = ((Float) a(this.k, "getLineSpacingExtra", Float.valueOf(BitmapDescriptorFactory.HUE_RED))).floatValue();
            includeFontPadding = ((Boolean) a(this.k, "getIncludeFontPadding", Boolean.TRUE)).booleanValue();
        }
        return new StaticLayout(charSequence, this.j, i, alignment, lineSpacingMultiplier, lineSpacingExtra, includeFontPadding);
    }

    private static <T> T a(Object obj, String str, T t) {
        try {
            return a(str).invoke(obj, new Object[0]);
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder("Failed to invoke TextView#");
            stringBuilder.append(str);
            stringBuilder.append("() method");
            Log.w("ACTVAutoSizeHelper", stringBuilder.toString(), e);
            return t;
        }
    }

    private static Method a(String str) {
        try {
            Method method = (Method) b.get(str);
            if (method == null) {
                method = TextView.class.getDeclaredMethod(str, new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    b.put(str, method);
                }
            }
            return method;
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder("Failed to retrieve TextView#");
            stringBuilder.append(str);
            stringBuilder.append("() method");
            Log.w("ACTVAutoSizeHelper", stringBuilder.toString(), e);
            return null;
        }
    }

    final boolean g() {
        return j() && this.c != 0;
    }

    private boolean j() {
        return !(this.k instanceof AppCompatEditText);
    }
}
