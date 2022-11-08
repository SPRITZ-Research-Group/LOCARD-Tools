package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public final class as {
    private static final c a;

    private interface c {
        void a(@NonNull View view, @Nullable CharSequence charSequence);
    }

    @TargetApi(26)
    private static class a implements c {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void a(@NonNull View view, @Nullable CharSequence tooltipText) {
            view.setTooltipText(tooltipText);
        }
    }

    private static class b implements c {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(@NonNull View view, @Nullable CharSequence tooltipText) {
            at.a(view, tooltipText);
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static void a(@NonNull View view, @Nullable CharSequence tooltipText) {
        a.a(view, tooltipText);
    }
}
