package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.util.ArrayList;

public final class bq {
    SparseArray<br> a = new SparseArray();
    private int b = 0;

    public final void a(int i, int i2) {
        br b = b(i);
        b.b = i2;
        ArrayList arrayList = b.a;
        while (arrayList.size() > i2) {
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public final cb a(int i) {
        br brVar = (br) this.a.get(i);
        if (brVar == null || brVar.a.isEmpty()) {
            return null;
        }
        ArrayList arrayList = brVar.a;
        return (cb) arrayList.remove(arrayList.size() - 1);
    }

    public final void a(cb cbVar) {
        int itemViewType = cbVar.getItemViewType();
        ArrayList arrayList = b(itemViewType).a;
        if (((br) this.a.get(itemViewType)).b > arrayList.size()) {
            cbVar.resetInternal();
            arrayList.add(cbVar);
        }
    }

    private static long a(long j, long j2) {
        return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
    }

    final void a(int i, long j) {
        br b = b(i);
        b.c = a(b.c, j);
    }

    final void b(int i, long j) {
        br b = b(i);
        b.d = a(b.d, j);
    }

    final boolean a(int i, long j, long j2) {
        long j3 = b(i).c;
        return j3 == 0 || j + j3 < j2;
    }

    final boolean b(int i, long j, long j2) {
        long j3 = b(i).d;
        return j3 == 0 || j + j3 < j2;
    }

    final void a() {
        this.b++;
    }

    final void b() {
        this.b--;
    }

    final void a(az azVar, az azVar2, boolean z) {
        if (azVar != null) {
            b();
        }
        if (!z && this.b == 0) {
            for (int i = 0; i < this.a.size(); i++) {
                ((br) this.a.valueAt(i)).a.clear();
            }
        }
        if (azVar2 != null) {
            a();
        }
    }

    private br b(int i) {
        br brVar = (br) this.a.get(i);
        if (brVar != null) {
            return brVar;
        }
        brVar = new br();
        this.a.put(i, brVar);
        return brVar;
    }
}
