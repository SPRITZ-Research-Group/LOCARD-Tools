package com.facebook.imagepipeline.f;

import com.facebook.common.internal.h;
import com.facebook.imagepipeline.image.f;
import java.util.Collections;
import java.util.List;

public final class g implements e {
    private final b a;

    public interface b {
        List<Integer> a();
    }

    private static class a implements b {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final List<Integer> a() {
            return Collections.EMPTY_LIST;
        }
    }

    public g() {
        this(new a());
    }

    private g(b dynamicValueConfig) {
        this.a = (b) h.a((Object) dynamicValueConfig);
    }

    public final int a(int scanNumber) {
        List<Integer> scansToDecode = this.a.a();
        if (scansToDecode == null || scansToDecode.isEmpty()) {
            return scanNumber + 1;
        }
        for (int i = 0; i < scansToDecode.size(); i++) {
            if (((Integer) scansToDecode.get(i)).intValue() > scanNumber) {
                return ((Integer) scansToDecode.get(i)).intValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    public final com.facebook.imagepipeline.image.g b(int scanNumber) {
        boolean z;
        if (scanNumber >= 0) {
            z = true;
        } else {
            z = false;
        }
        return f.a(scanNumber, z, false);
    }
}
