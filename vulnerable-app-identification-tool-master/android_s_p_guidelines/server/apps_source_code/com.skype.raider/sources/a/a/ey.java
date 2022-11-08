package a.a;

public enum ey {
    STRICT(false, true),
    LENIENT(true, false),
    NON_EXTENSIBLE(false, false),
    STRICT_ORDER(true, true);
    
    private final boolean e;
    private final boolean f;

    private ey(boolean z, boolean z2) {
        this.e = z;
        this.f = z2;
    }

    public final boolean a() {
        return this.e;
    }

    public final boolean b() {
        return this.f;
    }
}
