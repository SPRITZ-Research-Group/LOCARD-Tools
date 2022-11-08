package kotlin.reflect.jvm.internal.impl.types;

import kotlin.k;
import kotlin.v;

public final class FlexibleTypesKt {
    public static final boolean isFlexible(KotlinType kotlinType) {
        return kotlinType.unwrap() instanceof FlexibleType;
    }

    public static final FlexibleType asFlexibleType(KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap != null) {
            return (FlexibleType) unwrap;
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    public static final SimpleType lowerIfFlexible(KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getLowerBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new k();
    }

    public static final SimpleType upperIfFlexible(KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getUpperBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new k();
    }
}
