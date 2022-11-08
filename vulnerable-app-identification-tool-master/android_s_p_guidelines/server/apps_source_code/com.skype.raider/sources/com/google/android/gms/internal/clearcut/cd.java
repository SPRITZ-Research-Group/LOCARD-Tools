package com.google.android.gms.internal.clearcut;

import java.util.Map.Entry;

final class cd extends cc<FieldDescriptorType, Object> {
    cd(int i) {
        super(i, (byte) 0);
    }

    public final void a() {
        if (!b()) {
            for (int i = 0; i < c(); i++) {
                b(i).getKey();
            }
            for (Entry key : d()) {
                key.getKey();
            }
        }
        super.a();
    }
}
