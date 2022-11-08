package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class ViewBoundsCheck {
    final b a;
    a b = new a();

    interface b {
        int a();

        int a(View view);

        View a(int i);

        int b();

        int b(View view);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    static class a {
        int a = 0;
        int b;
        int c;
        int d;
        int e;

        a() {
        }

        final void a(int rvStart, int rvEnd, int childStart, int childEnd) {
            this.b = rvStart;
            this.c = rvEnd;
            this.d = childStart;
            this.e = childEnd;
        }

        final void a(int flags) {
            this.a |= flags;
        }

        private static int a(int x, int y) {
            if (x > y) {
                return 1;
            }
            if (x == y) {
                return 2;
            }
            return 4;
        }

        final boolean a() {
            if ((this.a & 7) != 0 && (this.a & (a(this.d, this.b) << 0)) == 0) {
                return false;
            }
            if ((this.a & 112) != 0 && (this.a & (a(this.d, this.c) << 4)) == 0) {
                return false;
            }
            if ((this.a & 1792) != 0 && (this.a & (a(this.e, this.b) << 8)) == 0) {
                return false;
            }
            if ((this.a & 28672) == 0 || (this.a & (a(this.e, this.c) << 12)) != 0) {
                return true;
            }
            return false;
        }
    }

    ViewBoundsCheck(b callback) {
        this.a = callback;
    }

    final View a(int fromIndex, int toIndex, int preferredBoundFlags, int acceptableBoundFlags) {
        int start = this.a.a();
        int end = this.a.b();
        int next = toIndex > fromIndex ? 1 : -1;
        View acceptableMatch = null;
        for (int i = fromIndex; i != toIndex; i += next) {
            View child = this.a.a(i);
            this.b.a(start, end, this.a.a(child), this.a.b(child));
            if (preferredBoundFlags != 0) {
                this.b.a = 0;
                this.b.a(preferredBoundFlags);
                if (this.b.a()) {
                    return child;
                }
            }
            if (acceptableBoundFlags != 0) {
                this.b.a = 0;
                this.b.a(acceptableBoundFlags);
                if (this.b.a()) {
                    acceptableMatch = child;
                }
            }
        }
        return acceptableMatch;
    }

    final boolean a(View child) {
        this.b.a(this.a.a(), this.a.b(), this.a.a(child), this.a.b(child));
        this.b.a = 0;
        this.b.a(24579);
        return this.b.a();
    }
}
