package android.support.b.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.VectorDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class i extends h {
    static final Mode a = Mode.SRC_IN;
    private f c;
    private PorterDuffColorFilter d;
    private ColorFilter e;
    private boolean f;
    private boolean g;
    private ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    private static class d {
        protected android.support.v4.graphics.b.b[] m = null;
        String n;
        int o;

        public d(d copy) {
            this.n = copy.n;
            this.o = copy.o;
            this.m = android.support.v4.graphics.b.a(copy.m);
        }

        public final void a(Path path) {
            path.reset();
            if (this.m != null) {
                android.support.v4.graphics.b.b.a(this.m, path);
            }
        }

        public String getPathName() {
            return this.n;
        }

        public boolean a() {
            return false;
        }

        public android.support.v4.graphics.b.b[] getPathData() {
            return this.m;
        }

        public void setPathData(android.support.v4.graphics.b.b[] nodes) {
            if (android.support.v4.graphics.b.a(this.m, nodes)) {
                android.support.v4.graphics.b.b[] bVarArr = this.m;
                for (int i = 0; i < nodes.length; i++) {
                    bVarArr[i].a = nodes[i].a;
                    for (int i2 = 0; i2 < nodes[i].b.length; i2++) {
                        bVarArr[i].b[i2] = nodes[i].b[i2];
                    }
                }
                return;
            }
            this.m = android.support.v4.graphics.b.a(nodes);
        }
    }

    private static class a extends d {
        public a(a copy) {
            super(copy);
        }

        public final void a(Resources r, AttributeSet attrs, Theme theme, XmlPullParser parser) {
            if (android.support.v4.content.res.b.a(parser, "pathData")) {
                TypedArray a = android.support.v4.content.res.b.a(r, theme, attrs, a.d);
                String string = a.getString(0);
                if (string != null) {
                    this.n = string;
                }
                string = a.getString(1);
                if (string != null) {
                    this.m = android.support.v4.graphics.b.b(string);
                }
                a.recycle();
            }
        }

        public final boolean a() {
            return true;
        }
    }

    private static class b extends d {
        int a = 0;
        float b = 0.0f;
        int c = 0;
        float d = 1.0f;
        int e = 0;
        float f = 1.0f;
        float g = 0.0f;
        float h = 1.0f;
        float i = 0.0f;
        Cap j = Cap.BUTT;
        Join k = Join.MITER;
        float l = 4.0f;
        private int[] p;

        public b(b copy) {
            super(copy);
            this.p = copy.p;
            this.a = copy.a;
            this.b = copy.b;
            this.d = copy.d;
            this.c = copy.c;
            this.e = copy.e;
            this.f = copy.f;
            this.g = copy.g;
            this.h = copy.h;
            this.i = copy.i;
            this.j = copy.j;
            this.k = copy.k;
            this.l = copy.l;
        }

        public final void a(Resources r, AttributeSet attrs, Theme theme, XmlPullParser parser) {
            TypedArray a = android.support.v4.content.res.b.a(r, theme, attrs, a.c);
            this.p = null;
            if (android.support.v4.content.res.b.a(parser, "pathData")) {
                String string = a.getString(0);
                if (string != null) {
                    this.n = string;
                }
                string = a.getString(2);
                if (string != null) {
                    this.m = android.support.v4.graphics.b.b(string);
                }
                this.c = android.support.v4.content.res.b.b(a, parser, "fillColor", 1, this.c);
                this.f = android.support.v4.content.res.b.a(a, parser, "fillAlpha", 12, this.f);
                int a2 = android.support.v4.content.res.b.a(a, parser, "strokeLineCap", 8, -1);
                Cap cap = this.j;
                switch (a2) {
                    case 0:
                        cap = Cap.BUTT;
                        break;
                    case 1:
                        cap = Cap.ROUND;
                        break;
                    case 2:
                        cap = Cap.SQUARE;
                        break;
                }
                this.j = cap;
                a2 = android.support.v4.content.res.b.a(a, parser, "strokeLineJoin", 9, -1);
                Join join = this.k;
                switch (a2) {
                    case 0:
                        join = Join.MITER;
                        break;
                    case 1:
                        join = Join.ROUND;
                        break;
                    case 2:
                        join = Join.BEVEL;
                        break;
                }
                this.k = join;
                this.l = android.support.v4.content.res.b.a(a, parser, "strokeMiterLimit", 10, this.l);
                this.a = android.support.v4.content.res.b.b(a, parser, "strokeColor", 3, this.a);
                this.d = android.support.v4.content.res.b.a(a, parser, "strokeAlpha", 11, this.d);
                this.b = android.support.v4.content.res.b.a(a, parser, "strokeWidth", 4, this.b);
                this.h = android.support.v4.content.res.b.a(a, parser, "trimPathEnd", 6, this.h);
                this.i = android.support.v4.content.res.b.a(a, parser, "trimPathOffset", 7, this.i);
                this.g = android.support.v4.content.res.b.a(a, parser, "trimPathStart", 5, this.g);
                this.e = android.support.v4.content.res.b.a(a, parser, "fillType", 13, this.e);
            }
            a.recycle();
        }

        final int getStrokeColor() {
            return this.a;
        }

        final void setStrokeColor(int strokeColor) {
            this.a = strokeColor;
        }

        final float getStrokeWidth() {
            return this.b;
        }

        final void setStrokeWidth(float strokeWidth) {
            this.b = strokeWidth;
        }

        final float getStrokeAlpha() {
            return this.d;
        }

        final void setStrokeAlpha(float strokeAlpha) {
            this.d = strokeAlpha;
        }

        final int getFillColor() {
            return this.c;
        }

        final void setFillColor(int fillColor) {
            this.c = fillColor;
        }

        final float getFillAlpha() {
            return this.f;
        }

        final void setFillAlpha(float fillAlpha) {
            this.f = fillAlpha;
        }

        final float getTrimPathStart() {
            return this.g;
        }

        final void setTrimPathStart(float trimPathStart) {
            this.g = trimPathStart;
        }

        final float getTrimPathEnd() {
            return this.h;
        }

        final void setTrimPathEnd(float trimPathEnd) {
            this.h = trimPathEnd;
        }

        final float getTrimPathOffset() {
            return this.i;
        }

        final void setTrimPathOffset(float trimPathOffset) {
            this.i = trimPathOffset;
        }
    }

    private static class c {
        final ArrayList<Object> a = new ArrayList();
        float b = 0.0f;
        int c;
        private final Matrix d = new Matrix();
        private float e = 0.0f;
        private float f = 0.0f;
        private float g = 1.0f;
        private float h = 1.0f;
        private float i = 0.0f;
        private float j = 0.0f;
        private final Matrix k = new Matrix();
        private int[] l;
        private String m = null;

        public c(c copy, android.support.v4.util.a<String, Object> targetsMap) {
            this.b = copy.b;
            this.e = copy.e;
            this.f = copy.f;
            this.g = copy.g;
            this.h = copy.h;
            this.i = copy.i;
            this.j = copy.j;
            this.l = copy.l;
            this.m = copy.m;
            this.c = copy.c;
            if (this.m != null) {
                targetsMap.put(this.m, this);
            }
            this.k.set(copy.k);
            ArrayList<Object> children = copy.a;
            for (int i = 0; i < children.size(); i++) {
                c copyChild = children.get(i);
                if (copyChild instanceof c) {
                    this.a.add(new c(copyChild, targetsMap));
                } else {
                    d newPath;
                    if (copyChild instanceof b) {
                        newPath = new b((b) copyChild);
                    } else if (copyChild instanceof a) {
                        newPath = new a((a) copyChild);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.a.add(newPath);
                    if (newPath.n != null) {
                        targetsMap.put(newPath.n, newPath);
                    }
                }
            }
        }

        public final String getGroupName() {
            return this.m;
        }

        public final Matrix getLocalMatrix() {
            return this.k;
        }

        public final void a(Resources res, AttributeSet attrs, Theme theme, XmlPullParser parser) {
            TypedArray a = android.support.v4.content.res.b.a(res, theme, attrs, a.b);
            this.l = null;
            this.b = android.support.v4.content.res.b.a(a, parser, "rotation", 5, this.b);
            this.e = a.getFloat(1, this.e);
            this.f = a.getFloat(2, this.f);
            this.g = android.support.v4.content.res.b.a(a, parser, "scaleX", 3, this.g);
            this.h = android.support.v4.content.res.b.a(a, parser, "scaleY", 4, this.h);
            this.i = android.support.v4.content.res.b.a(a, parser, "translateX", 6, this.i);
            this.j = android.support.v4.content.res.b.a(a, parser, "translateY", 7, this.j);
            String string = a.getString(0);
            if (string != null) {
                this.m = string;
            }
            a();
            a.recycle();
        }

        private void a() {
            this.k.reset();
            this.k.postTranslate(-this.e, -this.f);
            this.k.postScale(this.g, this.h);
            this.k.postRotate(this.b, 0.0f, 0.0f);
            this.k.postTranslate(this.i + this.e, this.j + this.f);
        }

        public final float getRotation() {
            return this.b;
        }

        public final void setRotation(float rotation) {
            if (rotation != this.b) {
                this.b = rotation;
                a();
            }
        }

        public final float getPivotX() {
            return this.e;
        }

        public final void setPivotX(float pivotX) {
            if (pivotX != this.e) {
                this.e = pivotX;
                a();
            }
        }

        public final float getPivotY() {
            return this.f;
        }

        public final void setPivotY(float pivotY) {
            if (pivotY != this.f) {
                this.f = pivotY;
                a();
            }
        }

        public final float getScaleX() {
            return this.g;
        }

        public final void setScaleX(float scaleX) {
            if (scaleX != this.g) {
                this.g = scaleX;
                a();
            }
        }

        public final float getScaleY() {
            return this.h;
        }

        public final void setScaleY(float scaleY) {
            if (scaleY != this.h) {
                this.h = scaleY;
                a();
            }
        }

        public final float getTranslateX() {
            return this.i;
        }

        public final void setTranslateX(float translateX) {
            if (translateX != this.i) {
                this.i = translateX;
                a();
            }
        }

        public final float getTranslateY() {
            return this.j;
        }

        public final void setTranslateY(float translateY) {
            if (translateY != this.j) {
                this.j = translateY;
                a();
            }
        }
    }

    private static class e {
        private static final Matrix k = new Matrix();
        final c a;
        float b;
        float c;
        float d;
        float e;
        int f;
        String g;
        final android.support.v4.util.a<String, Object> h;
        private final Path i;
        private final Path j;
        private final Matrix l;
        private Paint m;
        private Paint n;
        private PathMeasure o;
        private int p;

        public e() {
            this.l = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 255;
            this.g = null;
            this.h = new android.support.v4.util.a();
            this.a = new c();
            this.i = new Path();
            this.j = new Path();
        }

        public final void setRootAlpha(int alpha) {
            this.f = alpha;
        }

        public final int getRootAlpha() {
            return this.f;
        }

        public final void setAlpha(float alpha) {
            setRootAlpha((int) (255.0f * alpha));
        }

        public final float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public e(e copy) {
            this.l = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 255;
            this.g = null;
            this.h = new android.support.v4.util.a();
            this.a = new c(copy.a, this.h);
            this.i = new Path(copy.i);
            this.j = new Path(copy.j);
            this.b = copy.b;
            this.c = copy.c;
            this.d = copy.d;
            this.e = copy.e;
            this.p = copy.p;
            this.f = copy.f;
            this.g = copy.g;
            if (copy.g != null) {
                this.h.put(copy.g, this);
            }
        }

        private void a(c currentGroup, Matrix currentMatrix, Canvas canvas, int w, int h, ColorFilter filter) {
            currentGroup.d.set(currentMatrix);
            currentGroup.d.preConcat(currentGroup.k);
            canvas.save();
            for (int i = 0; i < currentGroup.a.size(); i++) {
                c child = currentGroup.a.get(i);
                if (child instanceof c) {
                    a(child, currentGroup.d, canvas, w, h, filter);
                } else if (child instanceof d) {
                    d childPath = (d) child;
                    float f = ((float) w) / this.d;
                    float f2 = ((float) h) / this.e;
                    float min = Math.min(f, f2);
                    Matrix a = currentGroup.d;
                    this.l.set(a);
                    this.l.postScale(f, f2);
                    float[] fArr = new float[]{0.0f, 1.0f, 1.0f, 0.0f};
                    a.mapVectors(fArr);
                    float f3 = (fArr[3] * fArr[0]) - (fArr[1] * fArr[2]);
                    f2 = Math.max((float) Math.hypot((double) fArr[0], (double) fArr[1]), (float) Math.hypot((double) fArr[2], (double) fArr[3]));
                    if (f2 > 0.0f) {
                        f2 = Math.abs(f3) / f2;
                    } else {
                        f2 = 0.0f;
                    }
                    if (f2 != 0.0f) {
                        childPath.a(this.i);
                        Path path = this.i;
                        this.j.reset();
                        if (childPath.a()) {
                            this.j.addPath(path, this.l);
                            canvas.clipPath(this.j);
                        } else {
                            b bVar = (b) childPath;
                            if (!(bVar.g == 0.0f && bVar.h == 1.0f)) {
                                float f4 = (bVar.g + bVar.i) % 1.0f;
                                f3 = (bVar.h + bVar.i) % 1.0f;
                                if (this.o == null) {
                                    this.o = new PathMeasure();
                                }
                                this.o.setPath(this.i, false);
                                float length = this.o.getLength();
                                f4 *= length;
                                f3 *= length;
                                path.reset();
                                if (f4 > f3) {
                                    this.o.getSegment(f4, length, path, true);
                                    this.o.getSegment(0.0f, f3, path, true);
                                } else {
                                    this.o.getSegment(f4, f3, path, true);
                                }
                                path.rLineTo(0.0f, 0.0f);
                            }
                            this.j.addPath(path, this.l);
                            if (bVar.c != 0) {
                                if (this.n == null) {
                                    this.n = new Paint();
                                    this.n.setStyle(Style.FILL);
                                    this.n.setAntiAlias(true);
                                }
                                Paint paint = this.n;
                                paint.setColor(i.a(bVar.c, bVar.f));
                                paint.setColorFilter(filter);
                                this.j.setFillType(bVar.e == 0 ? FillType.WINDING : FillType.EVEN_ODD);
                                canvas.drawPath(this.j, paint);
                            }
                            if (bVar.a != 0) {
                                if (this.m == null) {
                                    this.m = new Paint();
                                    this.m.setStyle(Style.STROKE);
                                    this.m.setAntiAlias(true);
                                }
                                Paint paint2 = this.m;
                                if (bVar.k != null) {
                                    paint2.setStrokeJoin(bVar.k);
                                }
                                if (bVar.j != null) {
                                    paint2.setStrokeCap(bVar.j);
                                }
                                paint2.setStrokeMiter(bVar.l);
                                paint2.setColor(i.a(bVar.a, bVar.d));
                                paint2.setColorFilter(filter);
                                paint2.setStrokeWidth((f2 * min) * bVar.b);
                                canvas.drawPath(this.j, paint2);
                            }
                        }
                    }
                }
            }
            canvas.restore();
        }

        public final void a(Canvas canvas, int w, int h) {
            a(this.a, k, canvas, w, h, null);
        }
    }

    private static class f extends ConstantState {
        int a;
        e b;
        ColorStateList c;
        Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public f(f copy) {
            this.c = null;
            this.d = i.a;
            if (copy != null) {
                this.a = copy.a;
                this.b = new e(copy.b);
                if (copy.b.n != null) {
                    this.b.n = new Paint(copy.b.n);
                }
                if (copy.b.m != null) {
                    this.b.m = new Paint(copy.b.m);
                }
                this.c = copy.c;
                this.d = copy.d;
                this.e = copy.e;
            }
        }

        public final void a(int width, int height) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), width, height);
        }

        public f() {
            this.c = null;
            this.d = i.a;
            this.b = new e();
        }

        public final Drawable newDrawable() {
            return new i(this);
        }

        public final Drawable newDrawable(Resources res) {
            return new i(this);
        }

        public final int getChangingConfigurations() {
            return this.a;
        }
    }

    @RequiresApi(24)
    private static class g extends ConstantState {
        private final ConstantState a;

        public g(ConstantState state) {
            this.a = state;
        }

        public final Drawable newDrawable() {
            i drawableCompat = new i();
            drawableCompat.b = (VectorDrawable) this.a.newDrawable();
            return drawableCompat;
        }

        public final Drawable newDrawable(Resources res) {
            i drawableCompat = new i();
            drawableCompat.b = (VectorDrawable) this.a.newDrawable(res);
            return drawableCompat;
        }

        public final Drawable newDrawable(Resources res, Theme theme) {
            i drawableCompat = new i();
            drawableCompat.b = (VectorDrawable) this.a.newDrawable(res, theme);
            return drawableCompat;
        }

        public final boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public final int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
    }

    public final /* bridge */ /* synthetic */ void applyTheme(Theme theme) {
        super.applyTheme(theme);
    }

    public final /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public final /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public final /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public final /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public final /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public final /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public final /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public final /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public final /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public final /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public final /* bridge */ /* synthetic */ void setColorFilter(int i, Mode mode) {
        super.setColorFilter(i, mode);
    }

    public final /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public final /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public final /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public final /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    i() {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = new f();
    }

    i(@NonNull f state) {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = state;
        this.d = a(state.c, state.d);
    }

    public final Drawable mutate() {
        if (this.b != null) {
            this.b.mutate();
        } else if (!this.f && super.mutate() == this) {
            this.c = new f(this.c);
            this.f = true;
        }
        return this;
    }

    final Object a(String name) {
        return this.c.b.h.get(name);
    }

    public final ConstantState getConstantState() {
        if (this.b != null && VERSION.SDK_INT >= 24) {
            return new g(this.b.getConstantState());
        }
        this.c.a = getChangingConfigurations();
        return this.c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void draw(Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
            return;
        }
        copyBounds(this.k);
        if (this.k.width() > 0 && this.k.height() > 0) {
            ColorFilter colorFilter = this.e == null ? this.d : this.e;
            canvas.getMatrix(this.j);
            this.j.getValues(this.i);
            float canvasScaleX = Math.abs(this.i[0]);
            float canvasScaleY = Math.abs(this.i[4]);
            float canvasSkewX = Math.abs(this.i[1]);
            float canvasSkewY = Math.abs(this.i[3]);
            if (!(canvasSkewX == 0.0f && canvasSkewY == 0.0f)) {
                canvasScaleX = 1.0f;
                canvasScaleY = 1.0f;
            }
            int scaledHeight = (int) (((float) this.k.height()) * canvasScaleY);
            int scaledWidth = Math.min(2048, (int) (((float) this.k.width()) * canvasScaleX));
            scaledHeight = Math.min(2048, scaledHeight);
            if (scaledWidth > 0 && scaledHeight > 0) {
                Object obj;
                Paint paint;
                int saveCount = canvas.save();
                canvas.translate((float) this.k.left, (float) this.k.top);
                if (VERSION.SDK_INT < 17) {
                    obj = null;
                } else if (isAutoMirrored() && android.support.v4.graphics.drawable.a.h(this) == 1) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    canvas.translate((float) this.k.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.k.offsetTo(0, 0);
                f fVar = this.c;
                if (fVar.f != null) {
                    if (scaledWidth == fVar.f.getWidth() && scaledHeight == fVar.f.getHeight()) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                }
                fVar.f = Bitmap.createBitmap(scaledWidth, scaledHeight, Config.ARGB_8888);
                fVar.k = true;
                if (this.g) {
                    f fVar2 = this.c;
                    if (!fVar2.k && fVar2.g == fVar2.c && fVar2.h == fVar2.d && fVar2.j == fVar2.e && fVar2.i == fVar2.b.getRootAlpha()) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        this.c.a(scaledWidth, scaledHeight);
                        fVar2 = this.c;
                        fVar2.g = fVar2.c;
                        fVar2.h = fVar2.d;
                        fVar2.i = fVar2.b.getRootAlpha();
                        fVar2.j = fVar2.e;
                        fVar2.k = false;
                    }
                } else {
                    this.c.a(scaledWidth, scaledHeight);
                }
                fVar = this.c;
                Rect rect = this.k;
                if (fVar.b.getRootAlpha() < 255) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null && colorFilter == null) {
                    paint = null;
                } else {
                    if (fVar.l == null) {
                        fVar.l = new Paint();
                        fVar.l.setFilterBitmap(true);
                    }
                    fVar.l.setAlpha(fVar.b.getRootAlpha());
                    fVar.l.setColorFilter(colorFilter);
                    paint = fVar.l;
                }
                canvas.drawBitmap(fVar.f, null, rect, paint);
                canvas.restoreToCount(saveCount);
            }
        }
    }

    public final int getAlpha() {
        if (this.b != null) {
            return android.support.v4.graphics.drawable.a.c(this.b);
        }
        return this.c.b.getRootAlpha();
    }

    public final void setAlpha(int alpha) {
        if (this.b != null) {
            this.b.setAlpha(alpha);
        } else if (this.c.b.getRootAlpha() != alpha) {
            this.c.b.setRootAlpha(alpha);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
            return;
        }
        this.e = colorFilter;
        invalidateSelf();
    }

    private PorterDuffColorFilter a(ColorStateList tint, Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return new PorterDuffColorFilter(tint.getColorForState(getState(), 0), tintMode);
    }

    public final void setTint(int tint) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tint);
        } else {
            setTintList(ColorStateList.valueOf(tint));
        }
    }

    public final void setTintList(ColorStateList tint) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tint);
            return;
        }
        f state = this.c;
        if (state.c != tint) {
            state.c = tint;
            this.d = a(tint, state.d);
            invalidateSelf();
        }
    }

    public final void setTintMode(Mode tintMode) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, tintMode);
            return;
        }
        f state = this.c;
        if (state.d != tintMode) {
            state.d = tintMode;
            this.d = a(state.c, tintMode);
            invalidateSelf();
        }
    }

    public final boolean isStateful() {
        if (this.b != null) {
            return this.b.isStateful();
        }
        return super.isStateful() || !(this.c == null || this.c.c == null || !this.c.c.isStateful());
    }

    protected final boolean onStateChange(int[] stateSet) {
        if (this.b != null) {
            return this.b.setState(stateSet);
        }
        f state = this.c;
        if (state.c == null || state.d == null) {
            return false;
        }
        this.d = a(state.c, state.d);
        invalidateSelf();
        return true;
    }

    public final int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return -3;
    }

    public final int getIntrinsicWidth() {
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return (int) this.c.b.b;
    }

    public final int getIntrinsicHeight() {
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return (int) this.c.b.c;
    }

    public final boolean canApplyTheme() {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.d(this.b);
        }
        return false;
    }

    public final boolean isAutoMirrored() {
        if (this.b != null) {
            return android.support.v4.graphics.drawable.a.b(this.b);
        }
        return this.c.e;
    }

    public final void setAutoMirrored(boolean mirrored) {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, mirrored);
        } else {
            this.c.e = mirrored;
        }
    }

    @Nullable
    public static i a(@NonNull Resources res, @DrawableRes int resId, @Nullable Theme theme) {
        if (VERSION.SDK_INT >= 24) {
            Drawable drawable;
            i drawable2 = new i();
            if (VERSION.SDK_INT >= 21) {
                drawable = res.getDrawable(resId, theme);
            } else {
                drawable = res.getDrawable(resId);
            }
            drawable2.b = drawable;
            drawable2.h = new g(drawable2.b.getConstantState());
            return drawable2;
        }
        try {
            int type;
            XmlPullParser parser = res.getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            do {
                type = parser.next();
                if (type == 2) {
                    break;
                }
            } while (type != 1);
            if (type == 2) {
                return a(res, parser, attrs, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (XmlPullParserException e) {
            return null;
        } catch (IOException e2) {
            return null;
        }
    }

    public static i a(Resources r, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
        i drawable = new i();
        drawable.inflate(r, parser, attrs, theme);
        return drawable;
    }

    static int a(int color, float alpha) {
        return (16777215 & color) | (((int) (((float) Color.alpha(color)) * alpha)) << 24);
    }

    public final void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        if (this.b != null) {
            this.b.inflate(res, parser, attrs);
        } else {
            inflate(res, parser, attrs, null);
        }
    }

    public final void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
        if (this.b != null) {
            android.support.v4.graphics.drawable.a.a(this.b, res, parser, attrs, theme);
            return;
        }
        f state = this.c;
        state.b = new e();
        TypedArray a = android.support.v4.content.res.b.a(res, theme, attrs, a.a);
        f fVar = this.c;
        e eVar = fVar.b;
        int a2 = android.support.v4.content.res.b.a(a, parser, "tintMode", 6, -1);
        Mode mode = Mode.SRC_IN;
        switch (a2) {
            case 3:
                mode = Mode.SRC_OVER;
                break;
            case 5:
                mode = Mode.SRC_IN;
                break;
            case 9:
                mode = Mode.SRC_ATOP;
                break;
            case 14:
                mode = Mode.MULTIPLY;
                break;
            case 15:
                mode = Mode.SCREEN;
                break;
            case 16:
                if (VERSION.SDK_INT >= 11) {
                    mode = Mode.ADD;
                    break;
                }
                break;
        }
        fVar.d = mode;
        ColorStateList colorStateList = a.getColorStateList(1);
        if (colorStateList != null) {
            fVar.c = colorStateList;
        }
        boolean z = fVar.e;
        if (android.support.v4.content.res.b.a(parser, "autoMirrored")) {
            z = a.getBoolean(5, z);
        }
        fVar.e = z;
        eVar.d = android.support.v4.content.res.b.a(a, parser, "viewportWidth", 7, eVar.d);
        eVar.e = android.support.v4.content.res.b.a(a, parser, "viewportHeight", 8, eVar.e);
        if (eVar.d <= 0.0f) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (eVar.e <= 0.0f) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        } else {
            eVar.b = a.getDimension(3, eVar.b);
            eVar.c = a.getDimension(2, eVar.c);
            if (eVar.b <= 0.0f) {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (eVar.c <= 0.0f) {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires height > 0");
            } else {
                eVar.setAlpha(android.support.v4.content.res.b.a(a, parser, "alpha", 4, eVar.getAlpha()));
                String string = a.getString(0);
                if (string != null) {
                    eVar.g = string;
                    eVar.h.put(string, eVar);
                }
                a.recycle();
                state.a = getChangingConfigurations();
                state.k = true;
                b(res, parser, attrs, theme);
                this.d = a(state.c, state.d);
            }
        }
    }

    private void b(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
        f state = this.c;
        e pathRenderer = state.b;
        boolean noPathTag = true;
        Stack<c> groupStack = new Stack();
        groupStack.push(pathRenderer.a);
        int eventType = parser.getEventType();
        int innerDepth = parser.getDepth() + 1;
        while (eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3)) {
            if (eventType == 2) {
                String tagName = parser.getName();
                c currentGroup = (c) groupStack.peek();
                if ("path".equals(tagName)) {
                    b path = new b();
                    path.a(res, attrs, theme, parser);
                    currentGroup.a.add(path);
                    if (path.getPathName() != null) {
                        pathRenderer.h.put(path.getPathName(), path);
                    }
                    noPathTag = false;
                    state.a |= path.o;
                } else if ("clip-path".equals(tagName)) {
                    a path2 = new a();
                    path2.a(res, attrs, theme, parser);
                    currentGroup.a.add(path2);
                    if (path2.getPathName() != null) {
                        pathRenderer.h.put(path2.getPathName(), path2);
                    }
                    state.a |= path2.o;
                } else if ("group".equals(tagName)) {
                    c newChildGroup = new c();
                    newChildGroup.a(res, attrs, theme, parser);
                    currentGroup.a.add(newChildGroup);
                    groupStack.push(newChildGroup);
                    if (newChildGroup.getGroupName() != null) {
                        pathRenderer.h.put(newChildGroup.getGroupName(), newChildGroup);
                    }
                    state.a |= newChildGroup.c;
                }
            } else if (eventType == 3) {
                if ("group".equals(parser.getName())) {
                    groupStack.pop();
                }
            }
            eventType = parser.next();
        }
        if (noPathTag) {
            StringBuffer tag = new StringBuffer();
            if (tag.length() > 0) {
                tag.append(" or ");
            }
            tag.append("path");
            throw new XmlPullParserException("no " + tag + " defined");
        }
    }

    final void a() {
        this.g = false;
    }

    protected final void onBoundsChange(Rect bounds) {
        if (this.b != null) {
            this.b.setBounds(bounds);
        }
    }

    public final int getChangingConfigurations() {
        if (this.b != null) {
            return this.b.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.c.getChangingConfigurations();
    }

    public final void invalidateSelf() {
        if (this.b != null) {
            this.b.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public final void scheduleSelf(Runnable what, long when) {
        if (this.b != null) {
            this.b.scheduleSelf(what, when);
        } else {
            super.scheduleSelf(what, when);
        }
    }

    public final boolean setVisible(boolean visible, boolean restart) {
        if (this.b != null) {
            return this.b.setVisible(visible, restart);
        }
        return super.setVisible(visible, restart);
    }

    public final void unscheduleSelf(Runnable what) {
        if (this.b != null) {
            this.b.unscheduleSelf(what);
        } else {
            super.unscheduleSelf(what);
        }
    }
}
