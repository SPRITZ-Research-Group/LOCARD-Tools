package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;

public interface JavaClassesTracker {

    public final class Default implements JavaClassesTracker {
        public static final Default INSTANCE = new Default();

        public final void reportClass(JavaClassDescriptor javaClassDescriptor) {
        }

        private Default() {
        }
    }

    void reportClass(JavaClassDescriptor javaClassDescriptor);
}
