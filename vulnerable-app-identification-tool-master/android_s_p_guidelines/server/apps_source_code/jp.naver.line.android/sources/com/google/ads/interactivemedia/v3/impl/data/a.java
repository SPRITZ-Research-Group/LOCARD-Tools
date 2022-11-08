package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.obf.km;

@km(a = d.class)
public abstract class a {

    @km(a = e.class)
    public abstract class a {
        public abstract int height();

        public abstract int left();

        public abstract int top();

        public abstract int width();

        public static a create(int i, int i2, int i3, int i4) {
            return new e(i, i2, i3, i4);
        }

        public static a createFromLocationOnScreen(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return create(iArr[0], iArr[1], view.getHeight(), view.getWidth());
        }
    }

    public interface b {
        b appState(String str);

        a build();

        b eventId(String str);

        b nativeTime(long j);

        b nativeViewAttached(boolean z);

        b nativeViewBounds(a aVar);

        b nativeViewHidden(boolean z);

        b nativeViewVisibleBounds(a aVar);

        b nativeVolume(double d);

        b queryId(String str);

        b vastEvent(String str);
    }

    public abstract String appState();

    public abstract String eventId();

    public abstract long nativeTime();

    public abstract boolean nativeViewAttached();

    public abstract a nativeViewBounds();

    public abstract boolean nativeViewHidden();

    public abstract a nativeViewVisibleBounds();

    public abstract double nativeVolume();

    public abstract String queryId();

    public abstract String vastEvent();

    public static b builder() {
        return new a();
    }
}
