package androidx.lifecycle;

public enum k {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public final boolean a(k kVar) {
        return compareTo(kVar) >= 0;
    }
}
