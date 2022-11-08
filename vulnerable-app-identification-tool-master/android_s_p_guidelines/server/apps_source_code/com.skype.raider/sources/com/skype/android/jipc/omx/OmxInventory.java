package com.skype.android.jipc.omx;

import android.os.Build.VERSION;

public class OmxInventory {
    private static final ThreadLocal<OmxFacade> a = new ThreadLocal<OmxFacade>() {
        protected final /* synthetic */ Object initialValue() {
            ServicePath servicePath;
            a aVar = a.i;
            if (aVar.ordinal() < a.f.ordinal()) {
                servicePath = ServicePath.MONOLITHIC;
            } else {
                servicePath = ServicePath.FRIGHTENED;
            }
            return new OmxFacade(servicePath, aVar.a());
        }
    };

    enum a {
        ;
        
        static final a i = null;
        final int g;
        final int h;

        abstract CallRouter a();

        static {
            int i = VERSION.SDK_INT;
            for (a aVar : values()) {
                int i2;
                if (i < aVar.g || i > aVar.h) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                if (i2 != 0) {
                    break;
                }
            }
            a aVar2 = a;
            i = aVar2;
        }

        private a(int since, int until) {
            this.g = since;
            this.h = until;
        }

        private a(int single) {
            this(r1, r2, single, single);
        }
    }

    public static OmxFacade a() {
        return (OmxFacade) a.get();
    }

    public static OmxObserver b() {
        return new OmxObserver(a.i.a());
    }
}
