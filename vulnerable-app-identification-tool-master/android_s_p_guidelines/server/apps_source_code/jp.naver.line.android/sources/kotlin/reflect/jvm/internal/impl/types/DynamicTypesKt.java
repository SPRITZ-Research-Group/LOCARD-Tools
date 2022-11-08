package kotlin.reflect.jvm.internal.impl.types;

public final class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType kotlinType) {
        return kotlinType.unwrap() instanceof DynamicType;
    }
}
