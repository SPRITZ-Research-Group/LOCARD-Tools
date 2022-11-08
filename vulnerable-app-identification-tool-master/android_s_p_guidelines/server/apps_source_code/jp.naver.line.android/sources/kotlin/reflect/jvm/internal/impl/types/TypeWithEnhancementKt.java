package kotlin.reflect.jvm.internal.impl.types;

import kotlin.k;

public final class TypeWithEnhancementKt {
    public static final KotlinType getEnhancement(KotlinType kotlinType) {
        return kotlinType instanceof TypeWithEnhancement ? ((TypeWithEnhancement) kotlinType).getEnhancement() : null;
    }

    public static final KotlinType unwrapEnhancement(KotlinType kotlinType) {
        KotlinType enhancement = getEnhancement(kotlinType);
        return enhancement == null ? kotlinType : enhancement;
    }

    public static final UnwrappedType inheritEnhancement(UnwrappedType unwrappedType, KotlinType kotlinType) {
        return wrapEnhancement(unwrappedType, getEnhancement(kotlinType));
    }

    public static final UnwrappedType wrapEnhancement(UnwrappedType unwrappedType, KotlinType kotlinType) {
        if (kotlinType == null) {
            return unwrappedType;
        }
        if (unwrappedType instanceof SimpleType) {
            return new SimpleTypeWithEnhancement((SimpleType) unwrappedType, kotlinType);
        }
        if (unwrappedType instanceof FlexibleType) {
            return new FlexibleTypeWithEnhancement((FlexibleType) unwrappedType, kotlinType);
        }
        throw new k();
    }
}
