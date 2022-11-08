package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;

public final class g {
    private final SparseIntArray a = new SparseIntArray();

    public final void a(long downTime) {
        this.a.put((int) downTime, 0);
    }

    public final void b(long downTime) {
        int currentValue = this.a.get((int) downTime, -1);
        if (currentValue == -1) {
            throw new RuntimeException("Tried to increment non-existent cookie");
        }
        this.a.put((int) downTime, currentValue + 1);
    }

    public final short c(long downTime) {
        int currentValue = this.a.get((int) downTime, -1);
        if (currentValue != -1) {
            return (short) (65535 & currentValue);
        }
        throw new RuntimeException("Tried to get non-existent cookie");
    }

    public final void d(long downTime) {
        this.a.delete((int) downTime);
    }

    public final boolean e(long downTime) {
        if (this.a.get((int) downTime, -1) == -1) {
            return false;
        }
        return true;
    }
}
