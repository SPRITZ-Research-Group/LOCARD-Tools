package androidx.work;

import java.util.List;

public abstract class k {
    private static final String a = p.a("InputMerger");

    public abstract h a(List<h> list);

    public static k a(String str) {
        try {
            return (k) Class.forName(str).newInstance();
        } catch (Exception e) {
            p.a().b(a, "Trouble instantiating + ".concat(String.valueOf(str)), e);
            return null;
        }
    }
}
