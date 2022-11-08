package kotlin.reflect.jvm.internal.impl.descriptors;

public final class ModalityKt {
    public static final boolean isFinalClass(ClassDescriptor classDescriptor) {
        return classDescriptor.getModality() == Modality.FINAL && classDescriptor.getKind() != ClassKind.ENUM_CLASS;
    }
}
