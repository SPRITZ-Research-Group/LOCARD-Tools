package addon.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class m {
    private static final List<m> d = new ArrayList();
    Object a;
    u b;
    m c;

    private m(Object obj, u uVar) {
        this.a = obj;
        this.b = uVar;
    }

    static m a(u uVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                m mVar = (m) d.remove(size - 1);
                mVar.a = obj;
                mVar.b = uVar;
                mVar.c = null;
                return mVar;
            }
            return new m(obj, uVar);
        }
    }

    static void a(m mVar) {
        mVar.a = null;
        mVar.b = null;
        mVar.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(mVar);
            }
        }
    }
}
