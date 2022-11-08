package defpackage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/* renamed from: acrq */
final class acrq implements Serializable {
    private static final acrq a = new acrq();

    private acrq() {
    }

    private Object readResolve() throws ObjectStreamException {
        return a;
    }
}
