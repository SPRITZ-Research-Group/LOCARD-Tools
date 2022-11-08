package com.facebook.cache.disk;

public final class a implements g {
    public final f a() {
        return new f(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                com.facebook.cache.disk.c.a aVar = (com.facebook.cache.disk.c.a) obj2;
                long b = ((com.facebook.cache.disk.c.a) obj).b();
                long b2 = aVar.b();
                if (b < b2) {
                    return -1;
                }
                return b2 == b ? 0 : 1;
            }
        };
    }
}
