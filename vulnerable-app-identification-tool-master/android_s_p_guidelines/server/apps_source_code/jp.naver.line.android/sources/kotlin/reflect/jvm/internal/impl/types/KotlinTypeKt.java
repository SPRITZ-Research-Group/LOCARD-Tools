package kotlin.reflect.jvm.internal.impl.types;

public final class KotlinTypeKt {
    public static final boolean isNullable(KotlinType kotlinType) {
        return TypeUtils.isNullableType(kotlinType);
    }

    public static final boolean isError(KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        return (unwrap instanceof ErrorType) || ((unwrap instanceof FlexibleType) && (((FlexibleType) unwrap).getDelegate() instanceof ErrorType));
    }
}
