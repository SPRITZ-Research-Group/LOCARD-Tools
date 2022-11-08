package com.facebook.react.views.scroll;

import android.view.ViewGroup;
import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.p;
import javax.annotation.Nullable;

public final class c {

    public interface b<T extends ViewGroup> {
        void scrollBy(T t, a aVar);

        void scrollTo(T t, c cVar);

        void scrollToEnd(T t, d dVar);
    }

    public static class a {
        public final int a;
        public final int b;
        public final boolean c;

        a(int deltaX, int deltaY, boolean animated) {
            this.a = deltaX;
            this.b = deltaY;
            this.c = animated;
        }
    }

    public static class c {
        public final int a;
        public final int b;
        public final boolean c;

        c(int destX, int destY, boolean animated) {
            this.a = destX;
            this.b = destY;
            this.c = animated;
        }
    }

    public static class d {
        public final boolean a;

        d(boolean animated) {
            this.a = animated;
        }
    }

    public static <T extends ViewGroup> void a(b<T> viewManager, T scrollView, int commandType, @Nullable al args) {
        com.facebook.infer.annotation.a.a((Object) viewManager);
        com.facebook.infer.annotation.a.a((Object) scrollView);
        com.facebook.infer.annotation.a.a((Object) args);
        boolean animated;
        int id;
        switch (commandType) {
            case 1:
                int destX = Math.round(p.a((float) args.getDouble(0)));
                int destY = Math.round(p.a((float) args.getDouble(1)));
                animated = args.getBoolean(2);
                id = args.getInt(3);
                viewManager.scrollTo(scrollView, new c(destX, destY, animated));
                d.a((ViewGroup) scrollView, id);
                return;
            case 2:
                viewManager.scrollToEnd(scrollView, new d(args.getBoolean(0)));
                return;
            case 3:
                int deltaX = Math.round(p.a((float) args.getDouble(0)));
                int deltaY = Math.round(p.a((float) args.getDouble(1)));
                animated = args.getBoolean(2);
                id = args.getInt(3);
                viewManager.scrollBy(scrollView, new a(deltaX, deltaY, animated));
                d.a((ViewGroup) scrollView, id);
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), viewManager.getClass().getSimpleName()}));
        }
    }
}
