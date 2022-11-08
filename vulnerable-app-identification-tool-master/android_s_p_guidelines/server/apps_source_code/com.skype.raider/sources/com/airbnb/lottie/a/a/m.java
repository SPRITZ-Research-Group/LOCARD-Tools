package com.airbnb.lottie.a.a;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.i;
import com.airbnb.lottie.c.b.q;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.List;

public final class m implements j, l, a {
    private final Path a = new Path();
    private final String b;
    private final LottieDrawable c;
    private final i.a d;
    private final com.airbnb.lottie.a.b.a<?, Float> e;
    private final com.airbnb.lottie.a.b.a<?, PointF> f;
    private final com.airbnb.lottie.a.b.a<?, Float> g;
    @Nullable
    private final com.airbnb.lottie.a.b.a<?, Float> h;
    private final com.airbnb.lottie.a.b.a<?, Float> i;
    @Nullable
    private final com.airbnb.lottie.a.b.a<?, Float> j;
    private final com.airbnb.lottie.a.b.a<?, Float> k;
    @Nullable
    private r l;
    private boolean m;

    public m(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, i polystarShape) {
        this.c = lottieDrawable;
        this.b = polystarShape.a();
        this.d = polystarShape.b();
        this.e = polystarShape.c().a();
        this.f = polystarShape.d().a();
        this.g = polystarShape.e().a();
        this.i = polystarShape.g().a();
        this.k = polystarShape.i().a();
        if (this.d == i.a.Star) {
            this.h = polystarShape.f().a();
            this.j = polystarShape.h().a();
        } else {
            this.h = null;
            this.j = null;
        }
        layer.a(this.e);
        layer.a(this.f);
        layer.a(this.g);
        layer.a(this.i);
        layer.a(this.k);
        if (this.d == i.a.Star) {
            layer.a(this.h);
            layer.a(this.j);
        }
        this.e.a((a) this);
        this.f.a((a) this);
        this.g.a((a) this);
        this.i.a((a) this);
        this.k.a((a) this);
        if (this.d == i.a.Star) {
            this.i.a((a) this);
            this.k.a((a) this);
        }
    }

    public final void a(List<b> contentsBefore, List<b> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            b content = (b) contentsBefore.get(i);
            if ((content instanceof r) && ((r) content).c() == q.a.Simultaneously) {
                this.l = (r) content;
                this.l.a(this);
            }
        }
    }

    public final Path e() {
        if (this.m) {
            return this.a;
        }
        this.a.reset();
        float floatValue;
        double d;
        double d2;
        float floatValue2;
        float f;
        float f2;
        float cos;
        float sin;
        PointF pointF;
        float f3;
        float cos2;
        float f4;
        switch (this.d) {
            case Star:
                float floatValue3;
                float f5;
                floatValue = ((Float) this.e.e()).floatValue();
                if (this.g == null) {
                    d = 0.0d;
                } else {
                    d = (double) ((Float) this.g.e()).floatValue();
                }
                d = Math.toRadians(d - 90.0d);
                float f6 = (float) (6.283185307179586d / ((double) floatValue));
                float f7 = f6 / 2.0f;
                float f8 = floatValue - ((float) ((int) floatValue));
                if (f8 != 0.0f) {
                    d2 = d + ((double) ((1.0f - f8) * f7));
                } else {
                    d2 = d;
                }
                float floatValue4 = ((Float) this.i.e()).floatValue();
                floatValue2 = ((Float) this.h.e()).floatValue();
                if (this.j != null) {
                    floatValue3 = ((Float) this.j.e()).floatValue() / 100.0f;
                } else {
                    floatValue3 = 0.0f;
                }
                f = 0.0f;
                if (this.k != null) {
                    f = ((Float) this.k.e()).floatValue() / 100.0f;
                }
                if (f8 != 0.0f) {
                    f2 = ((floatValue4 - floatValue2) * f8) + floatValue2;
                    cos = (float) (((double) f2) * Math.cos(d2));
                    sin = (float) (((double) f2) * Math.sin(d2));
                    this.a.moveTo(cos, sin);
                    d2 += (double) ((f6 * f8) / 2.0f);
                    f5 = f2;
                    f2 = sin;
                    sin = cos;
                } else {
                    cos = (float) (((double) floatValue4) * Math.cos(d2));
                    sin = (float) (((double) floatValue4) * Math.sin(d2));
                    this.a.moveTo(cos, sin);
                    d2 += (double) f7;
                    f5 = 0.0f;
                    f2 = sin;
                    sin = cos;
                }
                double ceil = Math.ceil((double) floatValue) * 2.0d;
                int i = 0;
                Object obj = null;
                float f9 = f2;
                float f10 = sin;
                while (true) {
                    double d3 = d2;
                    if (((double) i) >= ceil) {
                        pointF = (PointF) this.f.e();
                        this.a.offset(pointF.x, pointF.y);
                        this.a.close();
                        break;
                    }
                    Object obj2;
                    if (obj != null) {
                        f2 = floatValue4;
                    } else {
                        f2 = floatValue2;
                    }
                    if (f5 == 0.0f || ((double) i) != ceil - 2.0d) {
                        f3 = f7;
                    } else {
                        f3 = (f6 * f8) / 2.0f;
                    }
                    if (f5 != 0.0f && ((double) i) == ceil - 1.0d) {
                        f2 = f5;
                    }
                    cos2 = (float) (((double) f2) * Math.cos(d3));
                    floatValue = (float) (((double) f2) * Math.sin(d3));
                    if (floatValue3 == 0.0f && f == 0.0f) {
                        this.a.lineTo(cos2, floatValue);
                    } else {
                        float f11;
                        f2 = (float) (Math.atan2((double) f9, (double) f10) - 1.5707963267948966d);
                        float cos3 = (float) Math.cos((double) f2);
                        float sin2 = (float) Math.sin((double) f2);
                        f2 = (float) (Math.atan2((double) floatValue, (double) cos2) - 1.5707963267948966d);
                        float cos4 = (float) Math.cos((double) f2);
                        float sin3 = (float) Math.sin((double) f2);
                        f4 = obj != null ? floatValue3 : f;
                        if (obj != null) {
                            cos = f;
                        } else {
                            cos = floatValue3;
                        }
                        if (obj != null) {
                            sin = floatValue2;
                        } else {
                            sin = floatValue4;
                        }
                        if (obj != null) {
                            f2 = floatValue4;
                        } else {
                            f2 = floatValue2;
                        }
                        cos3 *= (sin * f4) * 0.47829f;
                        f4 = ((sin * f4) * 0.47829f) * sin2;
                        sin = ((f2 * cos) * 0.47829f) * cos4;
                        f2 = ((f2 * cos) * 0.47829f) * sin3;
                        if (f8 != 0.0f) {
                            if (i == 0) {
                                f4 *= f8;
                                cos = f2;
                                f11 = sin;
                                sin = cos3 * f8;
                                cos3 = f11;
                            } else if (((double) i) == ceil - 1.0d) {
                                cos = f2 * f8;
                                f11 = sin * f8;
                                sin = cos3;
                                cos3 = f11;
                            }
                            this.a.cubicTo(f10 - sin, f9 - f4, cos3 + cos2, cos + floatValue, cos2, floatValue);
                        }
                        cos = f2;
                        f11 = sin;
                        sin = cos3;
                        cos3 = f11;
                        this.a.cubicTo(f10 - sin, f9 - f4, cos3 + cos2, cos + floatValue, cos2, floatValue);
                    }
                    d2 = d3 + ((double) f3);
                    if (obj == null) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    i++;
                    obj = obj2;
                    f9 = floatValue;
                    f10 = cos2;
                }
                break;
            case Polygon:
                int floor = (int) Math.floor((double) ((Float) this.e.e()).floatValue());
                if (this.g == null) {
                    d = 0.0d;
                } else {
                    d = (double) ((Float) this.g.e()).floatValue();
                }
                d2 = Math.toRadians(d - 90.0d);
                floatValue2 = (float) (6.283185307179586d / ((double) floor));
                f = ((Float) this.k.e()).floatValue() / 100.0f;
                f3 = ((Float) this.i.e()).floatValue();
                cos2 = (float) (((double) f3) * Math.cos(d2));
                floatValue = (float) (((double) f3) * Math.sin(d2));
                this.a.moveTo(cos2, floatValue);
                d2 += (double) floatValue2;
                double ceil2 = Math.ceil((double) floor);
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    sin = cos2;
                    double d4 = d2;
                    f4 = floatValue;
                    if (((double) i3) >= ceil2) {
                        pointF = (PointF) this.f.e();
                        this.a.offset(pointF.x, pointF.y);
                        this.a.close();
                        break;
                    }
                    cos2 = (float) (((double) f3) * Math.cos(d4));
                    floatValue = (float) (((double) f3) * Math.sin(d4));
                    if (f != 0.0f) {
                        f2 = (float) (Math.atan2((double) f4, (double) sin) - 1.5707963267948966d);
                        cos = (float) (Math.atan2((double) floatValue, (double) cos2) - 1.5707963267948966d);
                        this.a.cubicTo(sin - (((float) Math.cos((double) f2)) * ((f3 * f) * 0.25f)), f4 - (((f3 * f) * 0.25f) * ((float) Math.sin((double) f2))), cos2 + (((float) Math.cos((double) cos)) * ((f3 * f) * 0.25f)), (((float) Math.sin((double) cos)) * ((f3 * f) * 0.25f)) + floatValue, cos2, floatValue);
                    } else {
                        this.a.lineTo(cos2, floatValue);
                    }
                    d2 = d4 + ((double) floatValue2);
                    i2 = i3 + 1;
                }
        }
        this.a.close();
        f.a(this.a, this.l);
        this.m = true;
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final void a(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        com.airbnb.lottie.e.e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        if (property == g.o) {
            this.e.a((c) callback);
        } else if (property == g.p) {
            this.g.a((c) callback);
        } else if (property == g.h) {
            this.f.a((c) callback);
        } else if (property == g.q && this.h != null) {
            this.h.a((c) callback);
        } else if (property == g.r) {
            this.i.a((c) callback);
        } else if (property == g.s && this.j != null) {
            this.j.a((c) callback);
        } else if (property == g.t) {
            this.k.a((c) callback);
        }
    }

    public final void a() {
        this.m = false;
        this.c.invalidateSelf();
    }
}
