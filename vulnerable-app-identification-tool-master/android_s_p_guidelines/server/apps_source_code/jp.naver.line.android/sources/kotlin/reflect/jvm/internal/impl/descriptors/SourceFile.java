package kotlin.reflect.jvm.internal.impl.descriptors;

public interface SourceFile {
    public static final SourceFile NO_SOURCE_FILE = new SourceFile() {
        public final String getName() {
            return null;
        }
    };

    String getName();
}
