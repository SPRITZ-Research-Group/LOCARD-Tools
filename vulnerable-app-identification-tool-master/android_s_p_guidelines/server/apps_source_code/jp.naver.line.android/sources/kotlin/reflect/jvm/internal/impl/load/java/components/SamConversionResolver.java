package kotlin.reflect.jvm.internal.impl.load.java.components;

public interface SamConversionResolver {

    public final class Empty implements SamConversionResolver {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }
    }
}
