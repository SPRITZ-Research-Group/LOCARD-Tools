package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acns;
import defpackage.acnz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class IntersectionTypeKt {
    public static final UnwrappedType intersectTypes(List<? extends UnwrappedType> list) {
        switch (list.size()) {
            case 0:
                throw new IllegalStateException("Expected some types".toString());
            case 1:
                return (UnwrappedType) acnz.i((List) list);
            default:
                Iterable<UnwrappedType> iterable = list;
                Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
                Object obj = null;
                Object obj2 = null;
                for (UnwrappedType unwrappedType : iterable) {
                    Object obj3;
                    obj = (obj != null || KotlinTypeKt.isError(unwrappedType)) ? 1 : null;
                    if (unwrappedType instanceof SimpleType) {
                        obj3 = (SimpleType) unwrappedType;
                    } else if (!(unwrappedType instanceof FlexibleType)) {
                        throw new k();
                    } else if (DynamicTypesKt.isDynamic(unwrappedType)) {
                        return unwrappedType;
                    } else {
                        obj3 = ((FlexibleType) unwrappedType).getLowerBound();
                        obj2 = 1;
                    }
                    arrayList.add(obj3);
                }
                List list2 = (List) arrayList;
                if (obj != null) {
                    return ErrorUtils.createErrorType("Intersection of error types: ".concat(String.valueOf(list)));
                }
                if (obj2 == null) {
                    return TypeIntersector.INSTANCE.intersectTypes$descriptors(list2);
                }
                Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable, 10));
                for (UnwrappedType upperIfFlexible : iterable) {
                    arrayList2.add(FlexibleTypesKt.upperIfFlexible(upperIfFlexible));
                }
                return KotlinTypeFactory.flexibleType(TypeIntersector.INSTANCE.intersectTypes$descriptors(list2), TypeIntersector.INSTANCE.intersectTypes$descriptors((List) arrayList2));
        }
    }
}
