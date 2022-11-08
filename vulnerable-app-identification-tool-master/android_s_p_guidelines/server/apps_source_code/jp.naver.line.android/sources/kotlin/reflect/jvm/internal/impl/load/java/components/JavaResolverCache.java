package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaResolverCache {
    public static final JavaResolverCache EMPTY = new JavaResolverCache() {
        public final ClassDescriptor getClassResolvedFromSource(FqName fqName) {
            return null;
        }

        public final void recordClass(JavaClass javaClass, ClassDescriptor classDescriptor) {
        }

        public final void recordConstructor(JavaElement javaElement, ConstructorDescriptor constructorDescriptor) {
        }

        public final void recordField(JavaField javaField, PropertyDescriptor propertyDescriptor) {
        }

        public final void recordMethod(JavaMethod javaMethod, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        }
    };

    ClassDescriptor getClassResolvedFromSource(FqName fqName);

    void recordClass(JavaClass javaClass, ClassDescriptor classDescriptor);

    void recordConstructor(JavaElement javaElement, ConstructorDescriptor constructorDescriptor);

    void recordField(JavaField javaField, PropertyDescriptor propertyDescriptor);

    void recordMethod(JavaMethod javaMethod, SimpleFunctionDescriptor simpleFunctionDescriptor);
}
