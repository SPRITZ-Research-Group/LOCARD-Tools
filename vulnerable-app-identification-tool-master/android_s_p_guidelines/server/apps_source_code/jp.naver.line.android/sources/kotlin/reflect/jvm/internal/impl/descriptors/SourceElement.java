package kotlin.reflect.jvm.internal.impl.descriptors;

public interface SourceElement {
    public static final SourceElement NO_SOURCE = new SourceElement() {
        public final String toString() {
            return "NO_SOURCE";
        }

        public final SourceFile getContainingFile() {
            return SourceFile.NO_SOURCE_FILE;
        }
    };

    SourceFile getContainingFile();
}
