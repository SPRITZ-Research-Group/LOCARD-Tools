package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.s;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

final class p {
    final b a;
    final a b = new a();
    final List<View> c = new ArrayList();

    interface b {
        int a();

        int a(View view);

        void a(int i);

        void a(View view, int i);

        void a(View view, int i, LayoutParams layoutParams);

        s b(View view);

        View b(int i);

        void b();

        void c(int i);

        void c(View view);

        void d(View view);
    }

    static class a {
        long a = 0;
        a b;

        a() {
        }

        final void a(int index) {
            while (index >= 64) {
                a();
                this = this.b;
                index -= 64;
            }
            this.a |= 1 << index;
        }

        private void a() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        final void b(int index) {
            while (index >= 64) {
                if (this.b != null) {
                    this = this.b;
                    index -= 64;
                } else {
                    return;
                }
            }
            this.a &= (1 << index) ^ -1;
        }

        final boolean c(int index) {
            while (index >= 64) {
                a();
                this = this.b;
                index -= 64;
            }
            return (this.a & (1 << index)) != 0;
        }

        final void a(int index, boolean value) {
            while (true) {
                if (index >= 64) {
                    a();
                    this = this.b;
                    index -= 64;
                } else {
                    boolean lastBit = (this.a & Long.MIN_VALUE) != 0;
                    long mask = (1 << index) - 1;
                    this.a = (this.a & mask) | ((this.a & (-1 ^ mask)) << 1);
                    if (value) {
                        a(index);
                    } else {
                        b(index);
                    }
                    if (lastBit || this.b != null) {
                        a();
                        this = this.b;
                        index = 0;
                        value = lastBit;
                    } else {
                        return;
                    }
                }
            }
        }

        final boolean d(int index) {
            while (index >= 64) {
                a();
                this = this.b;
                index -= 64;
            }
            long mask = 1 << index;
            boolean value = (this.a & mask) != 0;
            this.a &= -1 ^ mask;
            mask--;
            this.a = (this.a & mask) | Long.rotateRight(this.a & (-1 ^ mask), 1);
            if (this.b != null) {
                if (this.b.c(0)) {
                    a(63);
                }
                this.b.d(0);
            }
            return value;
        }

        final int e(int index) {
            if (this.b == null) {
                if (index >= 64) {
                    return Long.bitCount(this.a);
                }
                return Long.bitCount(this.a & ((1 << index) - 1));
            } else if (index < 64) {
                return Long.bitCount(this.a & ((1 << index) - 1));
            } else {
                return this.b.e(index - 64) + Long.bitCount(this.a);
            }
        }

        public final String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.a);
        }
    }

    p(b callback) {
        this.a = callback;
    }

    private void h(View child) {
        this.c.add(child);
        this.a.c(child);
    }

    private boolean i(View child) {
        if (!this.c.remove(child)) {
            return false;
        }
        this.a.d(child);
        return true;
    }

    final void a(View child) {
        a(child, -1, true);
    }

    final void a(View child, int index, boolean hidden) {
        int offset;
        if (index < 0) {
            offset = this.a.a();
        } else {
            offset = e(index);
        }
        this.b.a(offset, hidden);
        if (hidden) {
            h(child);
        }
        this.a.a(child, offset);
    }

    private int e(int index) {
        if (index < 0) {
            return -1;
        }
        int limit = this.a.a();
        int offset = index;
        while (offset < limit) {
            int diff = index - (offset - this.b.e(offset));
            if (diff == 0) {
                while (this.b.c(offset)) {
                    offset++;
                }
                return offset;
            }
            offset += diff;
        }
        return -1;
    }

    final void b(View view) {
        int index = this.a.a(view);
        if (index >= 0) {
            if (this.b.d(index)) {
                i(view);
            }
            this.a.a(index);
        }
    }

    final void a(int index) {
        int offset = e(index);
        View view = this.a.b(offset);
        if (view != null) {
            if (this.b.d(offset)) {
                i(view);
            }
            this.a.a(offset);
        }
    }

    final View b(int index) {
        return this.a.b(e(index));
    }

    final void a(View child, int index, LayoutParams layoutParams, boolean hidden) {
        int offset;
        if (index < 0) {
            offset = this.a.a();
        } else {
            offset = e(index);
        }
        this.b.a(offset, hidden);
        if (hidden) {
            h(child);
        }
        this.a.a(child, offset, layoutParams);
    }

    final int a() {
        return this.a.a() - this.c.size();
    }

    final int b() {
        return this.a.a();
    }

    final View c(int index) {
        return this.a.b(index);
    }

    final void d(int index) {
        int offset = e(index);
        this.b.d(offset);
        this.a.c(offset);
    }

    final int c(View child) {
        int index = this.a.a(child);
        if (index == -1 || this.b.c(index)) {
            return -1;
        }
        return index - this.b.e(index);
    }

    final boolean d(View view) {
        return this.c.contains(view);
    }

    final void e(View view) {
        int offset = this.a.a(view);
        if (offset < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.b.a(offset);
        h(view);
    }

    final void f(View view) {
        int offset = this.a.a(view);
        if (offset < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.b.c(offset)) {
            this.b.b(offset);
            i(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }

    final boolean g(View view) {
        int index = this.a.a(view);
        if (index == -1) {
            i(view);
            return true;
        } else if (!this.b.c(index)) {
            return false;
        } else {
            this.b.d(index);
            i(view);
            this.a.a(index);
            return true;
        }
    }
}
