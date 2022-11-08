package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.util.m;
import android.util.JsonReader;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.a;
import java.io.IOException;
import java.lang.ref.WeakReference;

class q {
    private static final Interpolator a = new LinearInterpolator();
    private static m<WeakReference<Interpolator>> b;

    q() {
    }

    @Nullable
    private static WeakReference<Interpolator> a(int hash) {
        WeakReference<Interpolator> weakReference;
        synchronized (q.class) {
            if (b == null) {
                b = new m();
            }
            weakReference = (WeakReference) b.a(hash);
        }
        return weakReference;
    }

    static <T> a<T> a(JsonReader reader, e composition, float scale, ah<T> valueParser, boolean animated) throws IOException {
        if (animated) {
            return a(composition, reader, scale, valueParser);
        }
        return new a(valueParser.a(reader, scale));
    }

    private static <T> a<T> a(e composition, JsonReader reader, float scale, ah<T> valueParser) throws IOException {
        Object obj;
        PointF cp1 = null;
        PointF cp2 = null;
        float startFrame = 0.0f;
        boolean hold = false;
        Interpolator interpolator = null;
        PointF pathCp1 = null;
        PointF pathCp2 = null;
        reader.beginObject();
        T endValue = null;
        Object endValue2 = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj2 = -1;
            switch (nextName.hashCode()) {
                case 101:
                    if (nextName.equals("e")) {
                        obj2 = 2;
                        break;
                    }
                    break;
                case 104:
                    if (nextName.equals("h")) {
                        obj2 = 5;
                        break;
                    }
                    break;
                case 105:
                    if (nextName.equals("i")) {
                        obj2 = 4;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        obj2 = 3;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        obj2 = 1;
                        break;
                    }
                    break;
                case 116:
                    if (nextName.equals("t")) {
                        obj2 = null;
                        break;
                    }
                    break;
                case 3701:
                    if (nextName.equals("ti")) {
                        obj2 = 7;
                        break;
                    }
                    break;
                case 3707:
                    if (nextName.equals("to")) {
                        obj2 = 6;
                        break;
                    }
                    break;
            }
            switch (obj2) {
                case null:
                    startFrame = (float) reader.nextDouble();
                    break;
                case 1:
                    endValue2 = valueParser.a(reader, scale);
                    break;
                case 2:
                    endValue = valueParser.a(reader, scale);
                    break;
                case 3:
                    cp1 = p.b(reader, scale);
                    break;
                case 4:
                    cp2 = p.b(reader, scale);
                    break;
                case 5:
                    hold = reader.nextInt() == 1;
                    break;
                case 6:
                    pathCp1 = p.b(reader, scale);
                    break;
                case 7:
                    pathCp2 = p.b(reader, scale);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        T obj3;
        if (hold) {
            interpolator = a;
            obj3 = endValue2;
        } else if (cp1 == null || cp2 == null) {
            interpolator = a;
            obj3 = endValue;
        } else {
            cp1.x = com.airbnb.lottie.e.e.a(cp1.x, -scale, scale);
            cp1.y = com.airbnb.lottie.e.e.a(cp1.y, -100.0f, 100.0f);
            cp2.x = com.airbnb.lottie.e.e.a(cp2.x, -scale, scale);
            cp2.y = com.airbnb.lottie.e.e.a(cp2.y, -100.0f, 100.0f);
            int hash = f.a(cp1.x, cp1.y, cp2.x, cp2.y);
            WeakReference<Interpolator> interpolatorRef = a(hash);
            if (interpolatorRef != null) {
                interpolator = (Interpolator) interpolatorRef.get();
            }
            if (interpolatorRef == null || interpolator == null) {
                interpolator = android.support.v4.view.animation.f.a(cp1.x / scale, cp1.y / scale, cp2.x / scale, cp2.y / scale);
                try {
                    WeakReference weakReference = new WeakReference(interpolator);
                    synchronized (q.class) {
                        b.a(hash, weakReference);
                    }
                    obj3 = endValue;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            obj3 = endValue;
        }
        a<T> keyframe = new a(composition, endValue2, obj3, interpolator, startFrame, null);
        keyframe.f = pathCp1;
        keyframe.g = pathCp2;
        return keyframe;
    }
}
