package com.facebook.react.uimanager;

import java.util.Comparator;

final class am {
    public static Comparator<am> a = new Comparator<am>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((am) obj).c - ((am) obj2).c;
        }
    };
    public final int b;
    public final int c;

    public am(int tag, int index) {
        this.b = tag;
        this.c = index;
    }
}
