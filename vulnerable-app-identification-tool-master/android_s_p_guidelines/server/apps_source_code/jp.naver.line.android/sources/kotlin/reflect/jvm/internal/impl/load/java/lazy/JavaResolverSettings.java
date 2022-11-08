package kotlin.reflect.jvm.internal.impl.load.java.lazy;

public interface JavaResolverSettings {
    public static final Companion Companion = Companion.$$INSTANCE;

    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    public final class Default implements JavaResolverSettings {
        public static final Default INSTANCE = new Default();

        public final boolean isReleaseCoroutines() {
            return false;
        }

        private Default() {
        }
    }

    boolean isReleaseCoroutines();
}
