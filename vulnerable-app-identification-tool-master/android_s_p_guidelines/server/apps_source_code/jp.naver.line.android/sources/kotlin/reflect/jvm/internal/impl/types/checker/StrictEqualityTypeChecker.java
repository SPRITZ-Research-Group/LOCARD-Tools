package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class StrictEqualityTypeChecker {
    public static final StrictEqualityTypeChecker INSTANCE = new StrictEqualityTypeChecker();

    private StrictEqualityTypeChecker() {
    }

    public final boolean strictEqualTypes(UnwrappedType unwrappedType, UnwrappedType unwrappedType2) {
        if (unwrappedType == unwrappedType2) {
            return true;
        }
        if ((unwrappedType instanceof SimpleType) && (unwrappedType2 instanceof SimpleType)) {
            return strictEqualTypes((SimpleType) unwrappedType, (SimpleType) unwrappedType2);
        }
        if (!(unwrappedType instanceof FlexibleType) || !(unwrappedType2 instanceof FlexibleType)) {
            return false;
        }
        FlexibleType flexibleType = (FlexibleType) unwrappedType;
        FlexibleType flexibleType2 = (FlexibleType) unwrappedType2;
        if (strictEqualTypes(flexibleType.getLowerBound(), flexibleType2.getLowerBound()) && strictEqualTypes(flexibleType.getUpperBound(), flexibleType2.getUpperBound())) {
            return true;
        }
        return false;
    }

    public final boolean strictEqualTypes(SimpleType simpleType, SimpleType simpleType2) {
        if (simpleType.isMarkedNullable() != simpleType2.isMarkedNullable() || SpecialTypesKt.isDefinitelyNotNullType(simpleType) != SpecialTypesKt.isDefinitelyNotNullType(simpleType2) || (acry.a(simpleType.getConstructor(), simpleType2.getConstructor()) ^ 1) != 0 || simpleType.getArguments().size() != simpleType2.getArguments().size()) {
            return false;
        }
        if (simpleType.getArguments() == simpleType2.getArguments()) {
            return true;
        }
        int size = simpleType.getArguments().size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = (TypeProjection) simpleType.getArguments().get(i);
            TypeProjection typeProjection2 = (TypeProjection) simpleType2.getArguments().get(i);
            if (typeProjection.isStarProjection() != typeProjection2.isStarProjection()) {
                return false;
            }
            if (!typeProjection.isStarProjection() && (typeProjection.getProjectionKind() != typeProjection2.getProjectionKind() || !strictEqualTypes(typeProjection.getType().unwrap(), typeProjection2.getType().unwrap()))) {
                return false;
            }
        }
        return true;
    }
}
