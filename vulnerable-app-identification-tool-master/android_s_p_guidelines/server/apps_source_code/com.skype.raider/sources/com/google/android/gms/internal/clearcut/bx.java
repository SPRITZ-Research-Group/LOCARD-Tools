package com.google.android.gms.internal.clearcut;

import com.skype.Defines;
import java.lang.reflect.Field;
import java.util.Arrays;

final class bx {
    private int A;
    private int B;
    private Field C;
    private Object D;
    private Object E;
    private Object F;
    private final by a;
    private final Object[] b;
    private Class<?> c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private final int[] n;
    private int o;
    private int p;
    private int q = Integer.MAX_VALUE;
    private int r = Integer.MIN_VALUE;
    private int s = 0;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private int w = 0;
    private int x;
    private int y;
    private int z;

    bx(Class<?> cls, String str, Object[] objArr) {
        int[] iArr = null;
        this.c = cls;
        this.a = new by(str);
        this.b = objArr;
        this.d = this.a.b();
        this.e = this.a.b();
        if (this.e == 0) {
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.l = 0;
            this.k = 0;
            this.m = 0;
            this.n = null;
            return;
        }
        this.f = this.a.b();
        this.g = this.a.b();
        this.h = this.a.b();
        this.i = this.a.b();
        this.l = this.a.b();
        this.k = this.a.b();
        this.j = this.a.b();
        this.m = this.a.b();
        int b = this.a.b();
        if (b != 0) {
            iArr = new int[b];
        }
        this.n = iArr;
        this.o = (this.f << 1) + this.g;
    }

    private static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(((String.valueOf(str).length() + 40) + String.valueOf(name).length()) + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    private final Object p() {
        Object[] objArr = this.b;
        int i = this.o;
        this.o = i + 1;
        return objArr[i];
    }

    private final boolean q() {
        return (this.d & 1) == 1;
    }

    final boolean a() {
        Object obj = null;
        if (!this.a.a()) {
            return false;
        }
        this.x = this.a.b();
        this.y = this.a.b();
        this.z = this.y & 255;
        if (this.x < this.q) {
            this.q = this.x;
        }
        if (this.x > this.r) {
            this.r = this.x;
        }
        if (this.z == ad.MAP.a()) {
            this.s++;
        } else if (this.z >= ad.DOUBLE_LIST.a() && this.z <= ad.GROUP_LIST.a()) {
            this.t++;
        }
        this.w++;
        if (cb.a(this.q, this.x, this.w)) {
            this.v = this.x + 1;
            this.u = this.v - this.q;
        } else {
            this.u++;
        }
        if (((this.y & 1024) != 0 ? 1 : null) != null) {
            int[] iArr = this.n;
            int i = this.p;
            this.p = i + 1;
            iArr[i] = this.x;
        }
        this.D = null;
        this.E = null;
        this.F = null;
        if (d()) {
            this.A = this.a.b();
            if (this.z == ad.MESSAGE.a() + 51 || this.z == ad.GROUP.a() + 51) {
                this.D = p();
            } else if (this.z == ad.ENUM.a() + 51 && q()) {
                this.E = p();
            }
        } else {
            this.C = a(this.c, (String) p());
            if (h()) {
                this.B = this.a.b();
            }
            if (this.z == ad.MESSAGE.a() || this.z == ad.GROUP.a()) {
                this.D = this.C.getType();
            } else if (this.z == ad.MESSAGE_LIST.a() || this.z == ad.GROUP_LIST.a()) {
                this.D = p();
            } else if (this.z == ad.ENUM.a() || this.z == ad.ENUM_LIST.a() || this.z == ad.ENUM_LIST_PACKED.a()) {
                if (q()) {
                    this.E = p();
                }
            } else if (this.z == ad.MAP.a()) {
                this.F = p();
                if ((this.y & 2048) != 0) {
                    obj = 1;
                }
                if (obj != null) {
                    this.E = p();
                }
            }
        }
        return true;
    }

    final int b() {
        return this.x;
    }

    final int c() {
        return this.z;
    }

    final boolean d() {
        return this.z > ad.MAP.a();
    }

    final Field e() {
        int i = this.A << 1;
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a = a(this.c, (String) obj);
        this.b[i] = a;
        return a;
    }

    final Field f() {
        int i = (this.A << 1) + 1;
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a = a(this.c, (String) obj);
        this.b[i] = a;
        return a;
    }

    final Field g() {
        return this.C;
    }

    final boolean h() {
        return q() && this.z <= ad.GROUP.a();
    }

    final Field i() {
        int i = (this.B / 32) + (this.f << 1);
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field a = a(this.c, (String) obj);
        this.b[i] = a;
        return a;
    }

    final int j() {
        return this.B % 32;
    }

    final boolean k() {
        return (this.y & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0;
    }

    final boolean l() {
        return (this.y & 512) != 0;
    }

    final Object m() {
        return this.D;
    }

    final Object n() {
        return this.E;
    }

    final Object o() {
        return this.F;
    }
}
