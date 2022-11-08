package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acns;
import defpackage.acob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        return (AbbreviatedType) unwrap;
    }

    public static final SimpleType getAbbreviation(KotlinType kotlinType) {
        AbbreviatedType abbreviatedType = getAbbreviatedType(kotlinType);
        return abbreviatedType != null ? abbreviatedType.getAbbreviation() : null;
    }

    public static final SimpleType withAbbreviation(SimpleType simpleType, SimpleType simpleType2) {
        if (KotlinTypeKt.isError(simpleType)) {
            return simpleType;
        }
        return new AbbreviatedType(simpleType, simpleType2);
    }

    public static final boolean isDefinitelyNotNullType(KotlinType kotlinType) {
        return kotlinType.unwrap() instanceof DefinitelyNotNullType;
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType simpleType) {
        SimpleType simpleType2;
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(simpleType);
        if (makeDefinitelyNotNull$descriptors != null) {
            simpleType2 = makeDefinitelyNotNull$descriptors;
        } else {
            simpleType2 = makeIntersectionTypeDefinitelyNotNullOrNotNull(simpleType);
        }
        return simpleType2 == null ? simpleType.makeNullableAsSpecified(false) : simpleType2;
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType unwrappedType) {
        UnwrappedType unwrappedType2;
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(unwrappedType);
        if (makeDefinitelyNotNull$descriptors != null) {
            unwrappedType2 = makeDefinitelyNotNull$descriptors;
        } else {
            unwrappedType2 = makeIntersectionTypeDefinitelyNotNullOrNotNull(unwrappedType);
        }
        return unwrappedType2 == null ? unwrappedType.makeNullableAsSpecified(false) : unwrappedType2;
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType kotlinType) {
        TypeConstructor constructor = kotlinType.getConstructor();
        if (!(constructor instanceof IntersectionTypeConstructor)) {
            constructor = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        if (intersectionTypeConstructor == null) {
            return null;
        }
        intersectionTypeConstructor = makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor);
        if (intersectionTypeConstructor == null) {
            return null;
        }
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(kotlinType.getAnnotations(), intersectionTypeConstructor, acob.a, false, intersectionTypeConstructor.createScopeForKotlinType());
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor intersectionTypeConstructor) {
        Iterable<Object> supertypes = intersectionTypeConstructor.getSupertypes();
        Collection arrayList = new ArrayList(acns.a((Iterable) supertypes, 10));
        Object obj = null;
        for (Object obj2 : supertypes) {
            Object obj22;
            if (TypeUtils.isNullableType(obj22)) {
                obj = 1;
                obj22 = makeDefinitelyNotNullOrNotNull(obj22.unwrap());
            }
            arrayList.add(obj22);
        }
        List list = (List) arrayList;
        if (obj == null) {
            return null;
        }
        return new IntersectionTypeConstructor(list);
    }
}
