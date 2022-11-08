package com.facebook.ads.internal.q.a;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public class s {
    private static final String a = s.class.getSimpleName();
    private boolean b;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private long g = -1;
    private int h = -1;
    private long i = -1;
    private long j = -1;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private float o = -1.0f;
    private float p = -1.0f;
    private float q = -1.0f;
    @Nullable
    private View r;
    @Nullable
    private View s;

    public final void a() {
        this.g = System.currentTimeMillis();
    }

    public final void a(MotionEvent motionEvent, View view, View view2) {
        if (!this.b) {
            this.b = true;
            InputDevice device = motionEvent.getDevice();
            if (device != null) {
                MotionRange motionRange = device.getMotionRange(0);
                MotionRange motionRange2 = device.getMotionRange(1);
                if (!(motionRange == null || motionRange2 == null)) {
                    this.q = Math.min(motionRange.getRange(), motionRange2.getRange());
                }
            }
            if (this.q <= 0.0f) {
                this.q = (float) Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        switch (motionEvent.getAction()) {
            case 0:
                this.c = (int) (((float) iArr[0]) / u.b);
                this.d = (int) (((float) iArr[1]) / u.b);
                this.e = (int) (((float) view.getWidth()) / u.b);
                this.f = (int) (((float) view.getHeight()) / u.b);
                this.h = 1;
                this.i = System.currentTimeMillis();
                this.k = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / u.b);
                this.l = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / u.b);
                this.o = motionEvent.getPressure();
                this.p = motionEvent.getSize();
                this.r = view2;
                return;
            case 1:
            case 3:
                this.j = System.currentTimeMillis();
                this.m = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / u.b);
                this.n = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / u.b);
                this.s = view2;
                return;
            case 2:
                this.o -= this.o / ((float) this.h);
                this.o += motionEvent.getPressure() / ((float) this.h);
                this.p -= this.p / ((float) this.h);
                this.p += motionEvent.getSize() / ((float) this.h);
                this.h++;
                return;
            default:
                return;
        }
    }

    public final boolean b() {
        return this.g != -1;
    }

    public final long c() {
        return b() ? System.currentTimeMillis() - this.g : -1;
    }

    public final boolean d() {
        return this.b;
    }

    public final Map<String, String> e() {
        if (!this.b) {
            return null;
        }
        j jVar;
        String valueOf = String.valueOf((this.p * this.q) / 2.0f);
        long j = (this.g <= 0 || this.j <= this.g) ? -1 : this.j - this.g;
        Map<String, String> hashMap = new HashMap();
        hashMap.put("adPositionX", String.valueOf(this.c));
        hashMap.put("adPositionY", String.valueOf(this.d));
        hashMap.put("width", String.valueOf(this.e));
        hashMap.put("height", String.valueOf(this.f));
        hashMap.put("clickDelayTime", String.valueOf(j));
        hashMap.put("startTime", String.valueOf(this.i));
        hashMap.put("endTime", String.valueOf(this.j));
        hashMap.put("startX", String.valueOf(this.k));
        hashMap.put("startY", String.valueOf(this.l));
        hashMap.put("clickX", String.valueOf(this.m));
        hashMap.put("clickY", String.valueOf(this.n));
        hashMap.put("endX", String.valueOf(this.m));
        hashMap.put("endY", String.valueOf(this.n));
        hashMap.put("force", String.valueOf(this.o));
        hashMap.put("radiusX", valueOf);
        hashMap.put("radiusY", valueOf);
        String str = "clickedViewTag";
        if (this.r == null || this.s == null) {
            jVar = j.INTERNAL_NULL_VIEW;
        } else if (this.r != this.s) {
            jVar = j.INTERNAL_NO_CLICK;
        } else if (VERSION.SDK_INT < 4) {
            jVar = j.INTERNAL_API_TOO_LOW;
        } else {
            Object tag = this.r.getTag(j.o);
            jVar = tag == null ? j.INTERNAL_NO_TAG : !(tag instanceof j) ? j.INTERNAL_WRONG_TAG_CLASS : (j) tag;
        }
        hashMap.put(str, String.valueOf(jVar.a()));
        return hashMap;
    }
}
