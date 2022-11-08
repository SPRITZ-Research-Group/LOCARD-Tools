package androidx.lifecycle;

public final class al {
    private final an a;
    private final aq b;

    public al(ar arVar, an anVar) {
        this(arVar.getViewModelStore(), anVar);
    }

    public al(aq aqVar, an anVar) {
        this.a = anVar;
        this.b = aqVar;
    }

    public final <T extends ak> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return a("androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(String.valueOf(canonicalName)), cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public final <T extends ak> T a(String str, Class<T> cls) {
        T a = this.b.a(str);
        if (cls.isInstance(a)) {
            return a;
        }
        T a2 = this.a.a(cls);
        this.b.a(str, a2);
        return a2;
    }
}
