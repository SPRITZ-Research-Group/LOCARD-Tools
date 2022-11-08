package a.a;

import java.util.List;

public final class ds extends dt implements di {
    public ds(List<di> list) {
        super(list);
    }

    public final boolean a(ed edVar) {
        boolean z = false;
        for (di a : this.a) {
            if (!a.a(edVar)) {
                return false;
            }
            z = true;
        }
        return z;
    }
}
