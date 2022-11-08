package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.ah.e;

final class ba implements ca {
    private static final bj b = new bb();
    private final bj a;

    public ba() {
        this(new bc(ag.a(), a()));
    }

    private ba(bj bjVar) {
        this.a = (bj) aj.a((Object) bjVar, "messageInfoFactory");
    }

    private static bj a() {
        try {
            return (bj) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return b;
        }
    }

    private static boolean a(bi biVar) {
        return biVar.a() == e.h;
    }

    public final <T> bz<T> a(Class<T> cls) {
        cb.a((Class) cls);
        bi b = this.a.b(cls);
        return b.b() ? ah.class.isAssignableFrom(cls) ? bp.a(cb.c(), z.a(), b.c()) : bp.a(cb.a(), z.b(), b.c()) : ah.class.isAssignableFrom(cls) ? a(b) ? bo.a(b, bt.b(), aw.b(), cb.c(), z.a(), bh.b()) : bo.a(b, bt.b(), aw.b(), cb.c(), null, bh.b()) : a(b) ? bo.a(b, bt.a(), aw.a(), cb.a(), z.b(), bh.a()) : bo.a(b, bt.a(), aw.a(), cb.b(), null, bh.a());
    }
}
