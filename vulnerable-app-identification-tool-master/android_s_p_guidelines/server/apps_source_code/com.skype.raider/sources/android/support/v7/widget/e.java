package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.s;
import java.util.ArrayList;
import java.util.List;

final class e implements a {
    final ArrayList<b> a;
    final ArrayList<b> b;
    final a c;
    Runnable d;
    final boolean e;
    final ac f;
    private android.support.v4.util.j.a<b> g;
    private int h;

    interface a {
        s a(int i);

        void a(int i, int i2);

        void a(int i, int i2, Object obj);

        void a(b bVar);

        void b(int i, int i2);

        void b(b bVar);

        void c(int i, int i2);

        void d(int i, int i2);
    }

    static class b {
        int a;
        int b;
        Object c;
        int d;

        b(int cmd, int positionStart, int itemCount, Object payload) {
            this.a = cmd;
            this.b = positionStart;
            this.d = itemCount;
            this.c = payload;
        }

        public final String toString() {
            String str;
            StringBuilder append = new StringBuilder().append(Integer.toHexString(System.identityHashCode(this))).append("[");
            switch (this.a) {
                case 1:
                    str = "add";
                    break;
                case 2:
                    str = "rm";
                    break;
                case 4:
                    str = "up";
                    break;
                case 8:
                    str = "mv";
                    break;
                default:
                    str = "??";
                    break;
            }
            return append.append(str).append(",s:").append(this.b).append("c:").append(this.d).append(",p:").append(this.c).append("]").toString();
        }

        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            b op = (b) o;
            if (this.a != op.a) {
                return false;
            }
            if (this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == op.b && this.b == op.d) {
                return true;
            }
            if (this.d != op.d) {
                return false;
            }
            if (this.b != op.b) {
                return false;
            }
            if (this.c != null) {
                if (this.c.equals(op.c)) {
                    return true;
                }
                return false;
            } else if (op.c != null) {
                return false;
            } else {
                return true;
            }
        }

        public final int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.d;
        }
    }

    e(a callback) {
        this(callback, (byte) 0);
    }

    private e(a callback, byte b) {
        this.g = new android.support.v4.util.j.b(30);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.h = 0;
        this.c = callback;
        this.e = false;
        this.f = new ac(this);
    }

    final void a() {
        a(this.a);
        a(this.b);
        this.h = 0;
    }

    final void b() {
        ac acVar = this.f;
        List list = this.a;
        while (true) {
            Object obj;
            int i;
            int i2;
            b bVar;
            b bVar2;
            b bVar3;
            Object obj2;
            Object obj3;
            int i3;
            int count;
            int i4;
            b op;
            int i5;
            int i6;
            int i7;
            int i8;
            Object obj4 = null;
            int size = list.size() - 1;
            while (size >= 0) {
                if (((b) list.get(size)).a != 8) {
                    obj = 1;
                } else if (obj4 != null) {
                    i = size;
                    if (i != -1) {
                        i2 = i + 1;
                        bVar = (b) list.get(i);
                        bVar2 = (b) list.get(i2);
                        switch (bVar2.a) {
                            case 1:
                                size = 0;
                                if (bVar.d < bVar2.b) {
                                    size = -1;
                                }
                                if (bVar.b < bVar2.b) {
                                    size++;
                                }
                                if (bVar2.b <= bVar.b) {
                                    bVar.b += bVar2.d;
                                }
                                if (bVar2.b <= bVar.d) {
                                    bVar.d += bVar2.d;
                                }
                                bVar2.b = size + bVar2.b;
                                list.set(i, bVar2);
                                list.set(i2, bVar);
                                break;
                            case 2:
                                bVar3 = null;
                                obj2 = null;
                                if (bVar.b < bVar.d) {
                                    obj3 = null;
                                    if (bVar2.b == bVar.b && bVar2.d == bVar.d - bVar.b) {
                                        obj2 = 1;
                                    }
                                } else {
                                    obj3 = 1;
                                    if (bVar2.b == bVar.d + 1 && bVar2.d == bVar.b - bVar.d) {
                                        obj2 = 1;
                                    }
                                }
                                if (bVar.d < bVar2.b) {
                                    if (bVar.d < bVar2.b + bVar2.d) {
                                        bVar2.d--;
                                        bVar.a = 2;
                                        bVar.d = 1;
                                        if (bVar2.d != 0) {
                                            break;
                                        }
                                        list.remove(i2);
                                        acVar.a.a(bVar2);
                                        break;
                                    }
                                }
                                bVar2.b--;
                                if (bVar.b <= bVar2.b) {
                                    bVar2.b++;
                                } else if (bVar.b < bVar2.b + bVar2.d) {
                                    bVar3 = acVar.a.a(2, bVar.b + 1, (bVar2.b + bVar2.d) - bVar.b, null);
                                    bVar2.d = bVar.b - bVar2.b;
                                }
                                if (obj2 == null) {
                                    if (obj3 == null) {
                                        if (bVar3 != null) {
                                            if (bVar.b > bVar3.b) {
                                                bVar.b -= bVar3.d;
                                            }
                                            if (bVar.d > bVar3.b) {
                                                bVar.d -= bVar3.d;
                                            }
                                        }
                                        if (bVar.b > bVar2.b) {
                                            bVar.b -= bVar2.d;
                                        }
                                        if (bVar.d > bVar2.b) {
                                            bVar.d -= bVar2.d;
                                        }
                                    } else {
                                        if (bVar3 != null) {
                                            if (bVar.b >= bVar3.b) {
                                                bVar.b -= bVar3.d;
                                            }
                                            if (bVar.d >= bVar3.b) {
                                                bVar.d -= bVar3.d;
                                            }
                                        }
                                        if (bVar.b >= bVar2.b) {
                                            bVar.b -= bVar2.d;
                                        }
                                        if (bVar.d >= bVar2.b) {
                                            bVar.d -= bVar2.d;
                                        }
                                    }
                                    list.set(i, bVar2);
                                    if (bVar.b == bVar.d) {
                                        list.set(i2, bVar);
                                    } else {
                                        list.remove(i2);
                                    }
                                    if (bVar3 == null) {
                                        break;
                                    }
                                    list.add(i, bVar3);
                                    break;
                                }
                                list.set(i, bVar2);
                                list.remove(i2);
                                acVar.a.a(bVar);
                                break;
                            case 4:
                                obj3 = null;
                                obj2 = null;
                                if (bVar.d < bVar2.b) {
                                    bVar2.b--;
                                } else if (bVar.d < bVar2.b + bVar2.d) {
                                    bVar2.d--;
                                    obj3 = acVar.a.a(4, bVar.b, 1, bVar2.c);
                                }
                                if (bVar.b <= bVar2.b) {
                                    bVar2.b++;
                                } else if (bVar.b < bVar2.b + bVar2.d) {
                                    i3 = (bVar2.b + bVar2.d) - bVar.b;
                                    obj2 = acVar.a.a(4, bVar.b + 1, i3, bVar2.c);
                                    bVar2.d -= i3;
                                }
                                list.set(i2, bVar);
                                if (bVar2.d <= 0) {
                                    list.set(i, bVar2);
                                } else {
                                    list.remove(i);
                                    acVar.a.a(bVar2);
                                }
                                if (obj3 != null) {
                                    list.add(i, obj3);
                                }
                                if (obj2 == null) {
                                    break;
                                }
                                list.add(i, obj2);
                                break;
                            default:
                                break;
                        }
                    }
                    count = this.a.size();
                    for (i4 = 0; i4 < count; i4++) {
                        op = (b) this.a.get(i4);
                        switch (op.a) {
                            case 1:
                                c(op);
                                break;
                            case 2:
                                i = op.b;
                                size = op.d + op.b;
                                i5 = -1;
                                i6 = op.b;
                                i3 = 0;
                                while (i6 < size) {
                                    obj = null;
                                    if (this.c.a(i6) == null || c(i6)) {
                                        if (i5 == 0) {
                                            b(a(2, i, i3, null));
                                            obj = 1;
                                        }
                                        i5 = 1;
                                    } else {
                                        if (i5 == 1) {
                                            c(a(2, i, i3, null));
                                            obj = 1;
                                        }
                                        i5 = 0;
                                    }
                                    if (obj == null) {
                                        i7 = i6 - i3;
                                        i6 = size - i3;
                                        size = 1;
                                    } else {
                                        i8 = i6;
                                        i6 = size;
                                        size = i3 + 1;
                                        i7 = i8;
                                    }
                                    i3 = size;
                                    size = i6;
                                    i6 = i7 + 1;
                                }
                                if (i3 != op.d) {
                                    a(op);
                                    op = a(2, i, i3, null);
                                }
                                if (i5 != 0) {
                                    c(op);
                                    break;
                                } else {
                                    b(op);
                                    break;
                                }
                            case 4:
                                i5 = op.b;
                                i3 = op.b + op.d;
                                size = op.b;
                                i7 = 0;
                                i6 = i5;
                                i5 = -1;
                                while (size < i3) {
                                    if (this.c.a(size) == null || c(size)) {
                                        if (i5 == 0) {
                                            b(a(4, i6, i7, op.c));
                                            i7 = 0;
                                            i6 = size;
                                        }
                                        i5 = 1;
                                    } else {
                                        if (i5 == 1) {
                                            c(a(4, i6, i7, op.c));
                                            i7 = 0;
                                            i6 = size;
                                        }
                                        i5 = 0;
                                    }
                                    i8 = i5;
                                    i5 = i6;
                                    size++;
                                    i7++;
                                    i6 = i5;
                                    i5 = i8;
                                }
                                if (i7 != op.d) {
                                    obj3 = op.c;
                                    a(op);
                                    op = a(4, i6, i7, obj3);
                                }
                                if (i5 != 0) {
                                    c(op);
                                    break;
                                } else {
                                    b(op);
                                    break;
                                }
                            case 8:
                                c(op);
                                break;
                        }
                        if (this.d != null) {
                            this.d.run();
                        }
                    }
                    this.a.clear();
                    return;
                } else {
                    obj = obj4;
                }
                size--;
                obj4 = obj;
            }
            i = -1;
            if (i != -1) {
                i2 = i + 1;
                bVar = (b) list.get(i);
                bVar2 = (b) list.get(i2);
                switch (bVar2.a) {
                    case 1:
                        size = 0;
                        if (bVar.d < bVar2.b) {
                            size = -1;
                        }
                        if (bVar.b < bVar2.b) {
                            size++;
                        }
                        if (bVar2.b <= bVar.b) {
                            bVar.b += bVar2.d;
                        }
                        if (bVar2.b <= bVar.d) {
                            bVar.d += bVar2.d;
                        }
                        bVar2.b = size + bVar2.b;
                        list.set(i, bVar2);
                        list.set(i2, bVar);
                        break;
                    case 2:
                        bVar3 = null;
                        obj2 = null;
                        if (bVar.b < bVar.d) {
                            obj3 = null;
                            obj2 = 1;
                            break;
                        }
                        obj3 = 1;
                        obj2 = 1;
                        break;
                        if (bVar.d < bVar2.b) {
                            if (bVar.d < bVar2.b + bVar2.d) {
                                bVar2.d--;
                                bVar.a = 2;
                                bVar.d = 1;
                                if (bVar2.d != 0) {
                                    list.remove(i2);
                                    acVar.a.a(bVar2);
                                    break;
                                }
                                break;
                            }
                        }
                        bVar2.b--;
                        if (bVar.b <= bVar2.b) {
                            bVar2.b++;
                        } else if (bVar.b < bVar2.b + bVar2.d) {
                            bVar3 = acVar.a.a(2, bVar.b + 1, (bVar2.b + bVar2.d) - bVar.b, null);
                            bVar2.d = bVar.b - bVar2.b;
                        }
                        if (obj2 == null) {
                            list.set(i, bVar2);
                            list.remove(i2);
                            acVar.a.a(bVar);
                            break;
                        }
                        if (obj3 == null) {
                            if (bVar3 != null) {
                                if (bVar.b >= bVar3.b) {
                                    bVar.b -= bVar3.d;
                                }
                                if (bVar.d >= bVar3.b) {
                                    bVar.d -= bVar3.d;
                                }
                            }
                            if (bVar.b >= bVar2.b) {
                                bVar.b -= bVar2.d;
                            }
                            if (bVar.d >= bVar2.b) {
                                bVar.d -= bVar2.d;
                            }
                        } else {
                            if (bVar3 != null) {
                                if (bVar.b > bVar3.b) {
                                    bVar.b -= bVar3.d;
                                }
                                if (bVar.d > bVar3.b) {
                                    bVar.d -= bVar3.d;
                                }
                            }
                            if (bVar.b > bVar2.b) {
                                bVar.b -= bVar2.d;
                            }
                            if (bVar.d > bVar2.b) {
                                bVar.d -= bVar2.d;
                            }
                        }
                        list.set(i, bVar2);
                        if (bVar.b == bVar.d) {
                            list.remove(i2);
                        } else {
                            list.set(i2, bVar);
                        }
                        if (bVar3 == null) {
                            list.add(i, bVar3);
                            break;
                        }
                        break;
                    case 4:
                        obj3 = null;
                        obj2 = null;
                        if (bVar.d < bVar2.b) {
                            bVar2.b--;
                        } else if (bVar.d < bVar2.b + bVar2.d) {
                            bVar2.d--;
                            obj3 = acVar.a.a(4, bVar.b, 1, bVar2.c);
                        }
                        if (bVar.b <= bVar2.b) {
                            bVar2.b++;
                        } else if (bVar.b < bVar2.b + bVar2.d) {
                            i3 = (bVar2.b + bVar2.d) - bVar.b;
                            obj2 = acVar.a.a(4, bVar.b + 1, i3, bVar2.c);
                            bVar2.d -= i3;
                        }
                        list.set(i2, bVar);
                        if (bVar2.d <= 0) {
                            list.remove(i);
                            acVar.a.a(bVar2);
                        } else {
                            list.set(i, bVar2);
                        }
                        if (obj3 != null) {
                            list.add(i, obj3);
                        }
                        if (obj2 == null) {
                            list.add(i, obj2);
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            count = this.a.size();
            for (i4 = 0; i4 < count; i4++) {
                op = (b) this.a.get(i4);
                switch (op.a) {
                    case 1:
                        c(op);
                        break;
                    case 2:
                        i = op.b;
                        size = op.d + op.b;
                        i5 = -1;
                        i6 = op.b;
                        i3 = 0;
                        while (i6 < size) {
                            obj = null;
                            if (this.c.a(i6) == null) {
                                break;
                            }
                            if (i5 == 0) {
                                b(a(2, i, i3, null));
                                obj = 1;
                            }
                            i5 = 1;
                            if (obj == null) {
                                i8 = i6;
                                i6 = size;
                                size = i3 + 1;
                                i7 = i8;
                            } else {
                                i7 = i6 - i3;
                                i6 = size - i3;
                                size = 1;
                            }
                            i3 = size;
                            size = i6;
                            i6 = i7 + 1;
                        }
                        if (i3 != op.d) {
                            a(op);
                            op = a(2, i, i3, null);
                        }
                        if (i5 != 0) {
                            b(op);
                            break;
                        } else {
                            c(op);
                            break;
                        }
                    case 4:
                        i5 = op.b;
                        i3 = op.b + op.d;
                        size = op.b;
                        i7 = 0;
                        i6 = i5;
                        i5 = -1;
                        while (size < i3) {
                            if (this.c.a(size) == null) {
                                break;
                            }
                            if (i5 == 0) {
                                b(a(4, i6, i7, op.c));
                                i7 = 0;
                                i6 = size;
                            }
                            i5 = 1;
                            i8 = i5;
                            i5 = i6;
                            size++;
                            i7++;
                            i6 = i5;
                            i5 = i8;
                        }
                        if (i7 != op.d) {
                            obj3 = op.c;
                            a(op);
                            op = a(4, i6, i7, obj3);
                        }
                        if (i5 != 0) {
                            b(op);
                            break;
                        } else {
                            c(op);
                            break;
                        }
                    case 8:
                        c(op);
                        break;
                }
                if (this.d != null) {
                    this.d.run();
                }
            }
            this.a.clear();
            return;
        }
    }

    final void c() {
        int count = this.b.size();
        for (int i = 0; i < count; i++) {
            this.c.b((b) this.b.get(i));
        }
        a(this.b);
        this.h = 0;
    }

    private void b(b op) {
        if (op.a == 1 || op.a == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int positionMultiplier;
        b tmp;
        int tmpStart = b(op.b, op.a);
        int tmpCnt = 1;
        int offsetPositionForPartial = op.b;
        switch (op.a) {
            case 2:
                positionMultiplier = 0;
                break;
            case 4:
                positionMultiplier = 1;
                break;
            default:
                throw new IllegalArgumentException("op should be remove or update." + op);
        }
        for (int p = 1; p < op.d; p++) {
            int updatedPos = b(op.b + (positionMultiplier * p), op.a);
            boolean continuous = false;
            switch (op.a) {
                case 2:
                    if (updatedPos != tmpStart) {
                        continuous = false;
                        break;
                    } else {
                        continuous = true;
                        break;
                    }
                case 4:
                    if (updatedPos != tmpStart + 1) {
                        continuous = false;
                        break;
                    } else {
                        continuous = true;
                        break;
                    }
            }
            if (continuous) {
                tmpCnt++;
            } else {
                tmp = a(op.a, tmpStart, tmpCnt, op.c);
                a(tmp, offsetPositionForPartial);
                a(tmp);
                if (op.a == 4) {
                    offsetPositionForPartial += tmpCnt;
                }
                tmpStart = updatedPos;
                tmpCnt = 1;
            }
        }
        Object payload = op.c;
        a(op);
        if (tmpCnt > 0) {
            tmp = a(op.a, tmpStart, tmpCnt, payload);
            a(tmp, offsetPositionForPartial);
            a(tmp);
        }
    }

    private void a(b op, int offsetStart) {
        this.c.a(op);
        switch (op.a) {
            case 2:
                this.c.a(offsetStart, op.d);
                return;
            case 4:
                this.c.a(offsetStart, op.d, op.c);
                return;
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int b(int pos, int cmd) {
        int i;
        for (i = this.b.size() - 1; i >= 0; i--) {
            b postponed = (b) this.b.get(i);
            if (postponed.a == 8) {
                int start;
                int end;
                if (postponed.b < postponed.d) {
                    start = postponed.b;
                    end = postponed.d;
                } else {
                    start = postponed.d;
                    end = postponed.b;
                }
                if (pos < start || pos > end) {
                    if (pos < postponed.b) {
                        if (cmd == 1) {
                            postponed.b++;
                            postponed.d++;
                        } else if (cmd == 2) {
                            postponed.b--;
                            postponed.d--;
                        }
                    }
                } else if (start == postponed.b) {
                    if (cmd == 1) {
                        postponed.d++;
                    } else if (cmd == 2) {
                        postponed.d--;
                    }
                    pos++;
                } else {
                    if (cmd == 1) {
                        postponed.b++;
                    } else if (cmd == 2) {
                        postponed.b--;
                    }
                    pos--;
                }
            } else if (postponed.b <= pos) {
                if (postponed.a == 1) {
                    pos -= postponed.d;
                } else if (postponed.a == 2) {
                    pos += postponed.d;
                }
            } else if (cmd == 1) {
                postponed.b++;
            } else if (cmd == 2) {
                postponed.b--;
            }
        }
        for (i = this.b.size() - 1; i >= 0; i--) {
            b op = (b) this.b.get(i);
            if (op.a == 8) {
                if (op.d == op.b || op.d < 0) {
                    this.b.remove(i);
                    a(op);
                }
            } else if (op.d <= 0) {
                this.b.remove(i);
                a(op);
            }
        }
        return pos;
    }

    private boolean c(int position) {
        int count = this.b.size();
        for (int i = 0; i < count; i++) {
            b op = (b) this.b.get(i);
            if (op.a == 8) {
                if (a(op.d, i + 1) == position) {
                    return true;
                }
            } else if (op.a == 1) {
                int end = op.b + op.d;
                for (int pos = op.b; pos < end; pos++) {
                    if (a(pos, i + 1) == position) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void c(b op) {
        this.b.add(op);
        switch (op.a) {
            case 1:
                this.c.c(op.b, op.d);
                return;
            case 2:
                this.c.b(op.b, op.d);
                return;
            case 4:
                this.c.a(op.b, op.d, op.c);
                return;
            case 8:
                this.c.d(op.b, op.d);
                return;
            default:
                throw new IllegalArgumentException("Unknown update op type for " + op);
        }
    }

    final boolean d() {
        return this.a.size() > 0;
    }

    final boolean a(int updateTypes) {
        return (this.h & updateTypes) != 0;
    }

    final int b(int position) {
        return a(position, 0);
    }

    final int a(int position, int firstPostponedItem) {
        int count = this.b.size();
        for (int i = firstPostponedItem; i < count; i++) {
            b op = (b) this.b.get(i);
            if (op.a == 8) {
                if (op.b == position) {
                    position = op.d;
                } else {
                    if (op.b < position) {
                        position--;
                    }
                    if (op.d <= position) {
                        position++;
                    }
                }
            } else if (op.b > position) {
                continue;
            } else if (op.a == 2) {
                if (position < op.b + op.d) {
                    return -1;
                }
                position -= op.d;
            } else if (op.a == 1) {
                position += op.d;
            }
        }
        return position;
    }

    final void e() {
        c();
        int count = this.a.size();
        for (int i = 0; i < count; i++) {
            b op = (b) this.a.get(i);
            switch (op.a) {
                case 1:
                    this.c.b(op);
                    this.c.c(op.b, op.d);
                    break;
                case 2:
                    this.c.b(op);
                    this.c.a(op.b, op.d);
                    break;
                case 4:
                    this.c.b(op);
                    this.c.a(op.b, op.d, op.c);
                    break;
                case 8:
                    this.c.b(op);
                    this.c.d(op.b, op.d);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        a(this.a);
        this.h = 0;
    }

    public final b a(int cmd, int positionStart, int itemCount, Object payload) {
        b op = (b) this.g.a();
        if (op == null) {
            return new b(cmd, positionStart, itemCount, payload);
        }
        op.a = cmd;
        op.b = positionStart;
        op.d = itemCount;
        op.c = payload;
        return op;
    }

    public final void a(b op) {
        if (!this.e) {
            op.c = null;
            this.g.a(op);
        }
    }

    private void a(List<b> ops) {
        int count = ops.size();
        for (int i = 0; i < count; i++) {
            a((b) ops.get(i));
        }
        ops.clear();
    }
}
