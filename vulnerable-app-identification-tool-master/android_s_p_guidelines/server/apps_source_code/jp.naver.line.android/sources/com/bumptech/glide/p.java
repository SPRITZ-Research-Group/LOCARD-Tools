package com.bumptech.glide;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.l;
import com.bumptech.glide.load.m;
import defpackage.aat;
import defpackage.aav;
import defpackage.aeg;
import defpackage.aei;
import defpackage.aej;
import defpackage.aix;
import defpackage.aiy;
import defpackage.ajh;
import defpackage.ajj;
import defpackage.ajk;
import defpackage.ajl;
import defpackage.ajm;
import defpackage.ajo;
import defpackage.alv;
import defpackage.gp;
import defpackage.ym;
import defpackage.yn;
import defpackage.yo;
import defpackage.zz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class p {
    private final aej a = new aej(this.j);
    private final ajh b = new ajh();
    private final ajm c = new ajm();
    private final ajo d = new ajo();
    private final yo e = new yo();
    private final aiy f = new aiy();
    private final ajj g = new ajj();
    private final ajl h = new ajl();
    private final ajk i = new ajk();
    private final gp<List<Throwable>> j = alv.a();

    public p() {
        List arrayList = new ArrayList(Arrays.asList(new String[]{"Gif", "Bitmap", "BitmapDrawable"}));
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.c.a(arrayList);
    }

    public final <Data> p a(Class<Data> cls, d<Data> dVar) {
        this.b.a(cls, dVar);
        return this;
    }

    public final <Data> p b(Class<Data> cls, d<Data> dVar) {
        this.b.b(cls, dVar);
        return this;
    }

    public final <Data, TResource> p a(Class<Data> cls, Class<TResource> cls2, l<Data, TResource> lVar) {
        a("legacy_append", cls, cls2, lVar);
        return this;
    }

    public final <Data, TResource> p a(String str, Class<Data> cls, Class<TResource> cls2, l<Data, TResource> lVar) {
        this.c.a(str, lVar, cls, cls2);
        return this;
    }

    public final <TResource> p a(Class<TResource> cls, m<TResource> mVar) {
        this.d.a(cls, mVar);
        return this;
    }

    public final p a(yn<?> ynVar) {
        this.e.a((yn) ynVar);
        return this;
    }

    public final <TResource, Transcode> p a(Class<TResource> cls, Class<Transcode> cls2, aix<TResource, Transcode> aix) {
        this.f.a(cls, cls2, aix);
        return this;
    }

    public final p a(ImageHeaderParser imageHeaderParser) {
        this.g.a(imageHeaderParser);
        return this;
    }

    public final <Model, Data> p a(Class<Model> cls, Class<Data> cls2, aei<Model, Data> aei) {
        this.a.a(cls, cls2, aei);
        return this;
    }

    public final <Model, Data> p b(Class<Model> cls, Class<Data> cls2, aei<Model, Data> aei) {
        this.a.b(cls, cls2, aei);
        return this;
    }

    public final <Model, Data> p c(Class<Model> cls, Class<Data> cls2, aei<? extends Model, ? extends Data> aei) {
        this.a.c(cls, cls2, aei);
        return this;
    }

    public final <Data, TResource, Transcode> aat<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        Class<Data> cls4 = cls;
        Class<TResource> cls5 = cls2;
        Class<Transcode> cls6 = cls3;
        aat<Data, TResource, Transcode> a = this.i.a(cls4, cls5, cls6);
        if (ajk.a(a)) {
            return null;
        }
        if (a == null) {
            List arrayList = new ArrayList();
            for (Class cls7 : r0.c.b(cls4, cls5)) {
                for (Class cls8 : r0.f.b(cls7, cls6)) {
                    zz zzVar = r1;
                    zz zzVar2 = new zz(cls, cls7, cls8, r0.c.a(cls4, cls7), r0.f.a(cls7, cls8), r0.j);
                    arrayList.add(zzVar);
                }
            }
            if (arrayList.isEmpty()) {
                a = null;
            } else {
                a = new aat(cls, cls2, cls3, arrayList, r0.j);
            }
            r0.i.a(cls4, cls5, cls6, a);
        }
        return a;
    }

    public final <Model, TResource, Transcode> List<Class<?>> b(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> a = this.h.a(cls, cls2);
        if (a == null) {
            a = new ArrayList();
            for (Class b : this.a.a((Class) cls)) {
                for (Class cls4 : this.c.b(b, cls2)) {
                    if (!(this.f.b(cls4, cls3).isEmpty() || a.contains(cls4))) {
                        a.add(cls4);
                    }
                }
            }
            this.h.a(cls, cls2, Collections.unmodifiableList(a));
        }
        return a;
    }

    public final boolean a(aav<?> aav) {
        return this.d.a(aav.c()) != null;
    }

    public final <X> m<X> b(aav<X> aav) throws t {
        m<X> a = this.d.a(aav.c());
        if (a != null) {
            return a;
        }
        throw new t(aav.c());
    }

    public final <X> d<X> a(X x) throws u {
        d<X> a = this.b.a(x.getClass());
        if (a != null) {
            return a;
        }
        throw new u(x.getClass());
    }

    public final <X> ym<X> b(X x) {
        return this.e.a((Object) x);
    }

    public final <Model> List<aeg<Model, ?>> c(Model model) {
        List<aeg<Model, ?>> a = this.a.a((Object) model);
        if (!a.isEmpty()) {
            return a;
        }
        throw new s(model);
    }

    public final List<ImageHeaderParser> a() {
        List<ImageHeaderParser> a = this.g.a();
        if (!a.isEmpty()) {
            return a;
        }
        throw new r();
    }

    public final <Data, TResource> p b(Class<Data> cls, Class<TResource> cls2, l<Data, TResource> lVar) {
        this.c.b("legacy_prepend_all", lVar, cls, cls2);
        return this;
    }
}
