package kotlin.reflect.jvm.internal.impl.incremental.components;

public interface LookupTracker {

    public final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        public final boolean getRequiresPosition() {
            return false;
        }

        public final void record(String str, Position position, String str2, ScopeKind scopeKind, String str3) {
        }

        private DO_NOTHING() {
        }
    }

    boolean getRequiresPosition();

    void record(String str, Position position, String str2, ScopeKind scopeKind, String str3);
}
