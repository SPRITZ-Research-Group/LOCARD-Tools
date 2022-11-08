package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acnz;
import defpackage.acry;
import java.util.ArrayDeque;
import java.util.Set;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexible;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.None;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.UpperIfFlexible;

public final class NullabilityChecker {
    public static final NullabilityChecker INSTANCE = new NullabilityChecker();

    private NullabilityChecker() {
    }

    public final boolean isPossibleSubtype(TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        return runIsPossibleSubtype(typeCheckerContext, simpleType, simpleType2);
    }

    public final boolean isSubtypeOfAny(UnwrappedType unwrappedType) {
        return hasNotNullSupertype(new TypeCheckerContext(false, false, 2, null), FlexibleTypesKt.lowerIfFlexible(unwrappedType), LowerIfFlexible.INSTANCE);
    }

    private final boolean runIsPossibleSubtype(TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        Object obj = (NewKotlinTypeCheckerKt.isIntersectionType(simpleType) || NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType) || typeCheckerContext.isAllowedTypeVariable(simpleType)) ? 1 : null;
        if (aa.a && obj == null) {
            throw new AssertionError("Not singleClassifierType superType: ".concat(String.valueOf(simpleType2)));
        }
        obj = (NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType2) || typeCheckerContext.isAllowedTypeVariable(simpleType2)) ? 1 : null;
        if (aa.a && obj == null) {
            throw new AssertionError("Not singleClassifierType superType: ".concat(String.valueOf(simpleType2)));
        } else if (simpleType2.isMarkedNullable() || SpecialTypesKt.isDefinitelyNotNullType(simpleType) || hasNotNullSupertype(typeCheckerContext, simpleType, LowerIfFlexible.INSTANCE)) {
            return true;
        } else {
            if (SpecialTypesKt.isDefinitelyNotNullType(simpleType2) || hasNotNullSupertype(typeCheckerContext, simpleType2, UpperIfFlexible.INSTANCE) || NewKotlinTypeCheckerKt.isClassType(simpleType)) {
                return false;
            }
            return hasPathByNotMarkedNullableNodes(typeCheckerContext, simpleType, simpleType2.getConstructor());
        }
    }

    private final boolean hasNotNullSupertype(TypeCheckerContext typeCheckerContext, SimpleType simpleType, SupertypesPolicy supertypesPolicy) {
        Object obj = ((!NewKotlinTypeCheckerKt.isClassType(simpleType) || simpleType.isMarkedNullable()) && !SpecialTypesKt.isDefinitelyNotNullType(simpleType)) ? null : 1;
        if (obj != null) {
            return true;
        }
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            acry.a();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            acry.a();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while ((access$getSupertypesDeque$p.isEmpty() ^ 1) != 0) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    SupertypesPolicy supertypesPolicy2 = simpleType2.isMarkedNullable() ? None.INSTANCE : supertypesPolicy;
                    if ((acry.a((Object) supertypesPolicy2, None.INSTANCE) ^ 1) == 0) {
                        supertypesPolicy2 = null;
                    }
                    if (supertypesPolicy2 != null) {
                        for (KotlinType transformType : simpleType2.getConstructor().getSupertypes()) {
                            SimpleType transformType2 = supertypesPolicy2.transformType(transformType);
                            Object obj2 = ((!NewKotlinTypeCheckerKt.isClassType(transformType2) || transformType2.isMarkedNullable()) && !SpecialTypesKt.isDefinitelyNotNullType(transformType2)) ? null : 1;
                            if (obj2 != null) {
                                typeCheckerContext.clear();
                                return true;
                            }
                            access$getSupertypesDeque$p.add(transformType2);
                        }
                        continue;
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder("Too many supertypes for type: ");
                stringBuilder.append(simpleType);
                stringBuilder.append(". Supertypes = ");
                stringBuilder.append(acnz.a((Iterable) access$getSupertypesSet$p, null, null, null, 0, null, null, 63));
                throw new IllegalStateException(stringBuilder.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }

    private final boolean hasPathByNotMarkedNullableNodes(TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        Object obj = (simpleType.isMarkedNullable() || !acry.a(simpleType.getConstructor(), (Object) typeConstructor)) ? null : 1;
        if (obj != null) {
            return true;
        }
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            acry.a();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            acry.a();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while ((access$getSupertypesDeque$p.isEmpty() ^ 1) != 0) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    SupertypesPolicy supertypesPolicy = simpleType2.isMarkedNullable() ? None.INSTANCE : LowerIfFlexible.INSTANCE;
                    if ((acry.a((Object) supertypesPolicy, None.INSTANCE) ^ 1) == 0) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType transformType : simpleType2.getConstructor().getSupertypes()) {
                            SimpleType transformType2 = supertypesPolicy.transformType(transformType);
                            Object obj2 = (transformType2.isMarkedNullable() || !acry.a(transformType2.getConstructor(), (Object) typeConstructor)) ? null : 1;
                            if (obj2 != null) {
                                typeCheckerContext.clear();
                                return true;
                            }
                            access$getSupertypesDeque$p.add(transformType2);
                        }
                        continue;
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder("Too many supertypes for type: ");
                stringBuilder.append(simpleType);
                stringBuilder.append(". Supertypes = ");
                stringBuilder.append(acnz.a((Iterable) access$getSupertypesSet$p, null, null, null, 0, null, null, 63));
                throw new IllegalStateException(stringBuilder.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }
}
