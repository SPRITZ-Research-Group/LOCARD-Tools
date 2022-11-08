package com.airbnb.lottie.c.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.c;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.a.b.n;
import com.airbnb.lottie.c.a.k;
import com.airbnb.lottie.c.b;
import com.airbnb.lottie.c.d;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.g;
import com.airbnb.lottie.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class h extends a {
    private final char[] e = new char[1];
    private final RectF f = new RectF();
    private final Matrix g = new Matrix();
    private final Paint h = new Paint(this) {
        final /* synthetic */ h a;

        {
            this.a = this$0;
            setStyle(Style.FILL);
        }
    };
    private final Paint i = new Paint(this) {
        final /* synthetic */ h a;

        {
            this.a = this$0;
            setStyle(Style.STROKE);
        }
    };
    private final Map<d, List<c>> j = new HashMap();
    private final n k;
    private final LottieDrawable l;
    private final e m;
    @Nullable
    private a<Integer, Integer> n;
    @Nullable
    private a<Integer, Integer> o;
    @Nullable
    private a<Float, Float> p;
    @Nullable
    private a<Float, Float> q;

    h(LottieDrawable lottieDrawable, d layerModel) {
        super(lottieDrawable, layerModel);
        this.l = lottieDrawable;
        this.m = layerModel.a();
        this.k = layerModel.s().b();
        this.k.a((a.a) this);
        a(this.k);
        k textProperties = layerModel.t();
        if (!(textProperties == null || textProperties.a == null)) {
            this.n = textProperties.a.a();
            this.n.a((a.a) this);
            a(this.n);
        }
        if (!(textProperties == null || textProperties.b == null)) {
            this.o = textProperties.b.a();
            this.o.a((a.a) this);
            a(this.o);
        }
        if (!(textProperties == null || textProperties.c == null)) {
            this.p = textProperties.c.a();
            this.p.a((a.a) this);
            a(this.p);
        }
        if (textProperties != null && textProperties.d != null) {
            this.q = textProperties.d.a();
            this.q.a((a.a) this);
            a(this.q);
        }
    }

    final void b(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        canvas.save();
        if (!this.l.j()) {
            canvas.setMatrix(parentMatrix);
        }
        b documentData = (b) this.k.e();
        com.airbnb.lottie.c.c font = (com.airbnb.lottie.c.c) this.m.i().get(documentData.b);
        if (font == null) {
            canvas.restore();
            return;
        }
        if (this.n != null) {
            this.h.setColor(((Integer) this.n.e()).intValue());
        } else {
            this.h.setColor(documentData.h);
        }
        if (this.o != null) {
            this.i.setColor(((Integer) this.o.e()).intValue());
        } else {
            this.i.setColor(documentData.i);
        }
        int alpha = (((Integer) this.d.a().e()).intValue() * 255) / 100;
        this.h.setAlpha(alpha);
        this.i.setAlpha(alpha);
        if (this.p != null) {
            this.i.setStrokeWidth(((Float) this.p.e()).floatValue());
        } else {
            this.i.setStrokeWidth((((float) documentData.j) * f.a()) * f.a(parentMatrix));
        }
        if (this.l.j()) {
            float f = ((float) documentData.c) / 100.0f;
            float a = f.a(parentMatrix);
            String str = documentData.a;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= str.length()) {
                    break;
                }
                d dVar = (d) this.m.h().a(d.a(str.charAt(i2), font.a(), font.c()));
                if (dVar != null) {
                    List list;
                    int i3;
                    int i4;
                    float floatValue;
                    if (this.j.containsKey(dVar)) {
                        list = (List) this.j.get(dVar);
                    } else {
                        List a2 = dVar.a();
                        int size = a2.size();
                        list = new ArrayList(size);
                        i3 = 0;
                        while (true) {
                            i4 = i3;
                            if (i4 >= size) {
                                break;
                            }
                            list.add(new c(this.l, this, (com.airbnb.lottie.c.b.n) a2.get(i4)));
                            i3 = i4 + 1;
                        }
                        this.j.put(dVar, list);
                    }
                    i3 = 0;
                    while (true) {
                        i4 = i3;
                        if (i4 >= list.size()) {
                            break;
                        }
                        Path e = ((c) list.get(i4)).e();
                        e.computeBounds(this.f, false);
                        this.g.set(parentMatrix);
                        this.g.preTranslate(0.0f, ((float) (-documentData.g)) * f.a());
                        this.g.preScale(f, f);
                        e.transform(this.g);
                        if (documentData.k) {
                            a(e, this.h, canvas);
                            a(e, this.i, canvas);
                        } else {
                            a(e, this.i, canvas);
                            a(e, this.h, canvas);
                        }
                        i3 = i4 + 1;
                    }
                    float b = ((((float) dVar.b()) * f) * f.a()) * a;
                    float f2 = ((float) documentData.e) / 10.0f;
                    if (this.q != null) {
                        floatValue = ((Float) this.q.e()).floatValue() + f2;
                    } else {
                        floatValue = f2;
                    }
                    canvas.translate((floatValue * a) + b, 0.0f);
                }
                i = i2 + 1;
            }
        } else {
            a(documentData, font, parentMatrix, canvas);
        }
        canvas.restore();
    }

    private void a(b documentData, com.airbnb.lottie.c.c font, Matrix parentMatrix, Canvas canvas) {
        float parentScale = f.a(parentMatrix);
        Typeface typeface = this.l.a(font.a(), font.c());
        if (typeface != null) {
            String text = documentData.a;
            l textDelegate = this.l.i();
            if (textDelegate != null) {
                text = textDelegate.a(text);
            }
            this.h.setTypeface(typeface);
            this.h.setTextSize((float) (documentData.c * ((double) f.a())));
            this.i.setTypeface(this.h.getTypeface());
            this.i.setTextSize(this.h.getTextSize());
            for (int i = 0; i < text.length(); i++) {
                char character = text.charAt(i);
                this.e[0] = character;
                if (documentData.k) {
                    a(this.e, this.h, canvas);
                    a(this.e, this.i, canvas);
                } else {
                    a(this.e, this.i, canvas);
                    a(this.e, this.h, canvas);
                }
                this.e[0] = character;
                float charWidth = this.h.measureText(this.e, 0, 1);
                float tracking = ((float) documentData.e) / 10.0f;
                if (this.q != null) {
                    tracking += ((Float) this.q.e()).floatValue();
                }
                canvas.translate(charWidth + (tracking * parentScale), 0.0f);
            }
        }
    }

    private static void a(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawPath(path, paint);
            }
        }
    }

    private static void a(char[] character, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawText(character, 0, 1, 0.0f, 0.0f, paint);
            }
        }
    }

    public final <T> void a(T property, @Nullable com.airbnb.lottie.f.c<T> callback) {
        super.a((Object) property, (com.airbnb.lottie.f.c) callback);
        if (property == g.a && this.n != null) {
            this.n.a((com.airbnb.lottie.f.c) callback);
        } else if (property == g.b && this.o != null) {
            this.o.a((com.airbnb.lottie.f.c) callback);
        } else if (property == g.k && this.p != null) {
            this.p.a((com.airbnb.lottie.f.c) callback);
        } else if (property == g.l && this.q != null) {
            this.q.a((com.airbnb.lottie.f.c) callback);
        }
    }
}
