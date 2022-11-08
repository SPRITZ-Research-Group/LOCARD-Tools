package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acrc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.v;
import kotlin.y;

public final class NewCapturedTypeKt {
    public static /* synthetic */ SimpleType captureFromArguments$default(SimpleType simpleType, CaptureStatus captureStatus, acrc acrc, int i, Object obj) {
        if ((i & 4) != 0) {
            acrc = FunctionsKt.getDO_NOTHING_2();
        }
        return captureFromArguments(simpleType, captureStatus, acrc);
    }

    public static final SimpleType captureFromArguments(SimpleType simpleType, CaptureStatus captureStatus, acrc<? super Integer, ? super NewCapturedType, y> acrc) {
        if (simpleType.getArguments().size() != simpleType.getConstructor().getParameters().size()) {
            return null;
        }
        TypeProjection typeProjection;
        Object obj;
        List arguments = simpleType.getArguments();
        Iterable<TypeProjection> iterable = arguments;
        Object obj2 = 1;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (TypeProjection typeProjection2 : iterable) {
                if (typeProjection2.getProjectionKind() == Variance.INVARIANT) {
                    obj = 1;
                    continue;
                } else {
                    obj = null;
                    continue;
                }
                if (obj == null) {
                    obj2 = null;
                    break;
                }
            }
        }
        if (obj2 != null) {
            return null;
        }
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (Object obj3 : iterable) {
            if (obj3.getProjectionKind() != Variance.INVARIANT) {
                UnwrappedType unwrap = (obj3.isStarProjection() || obj3.getProjectionKind() != Variance.IN_VARIANCE) ? null : obj3.getType().unwrap();
                obj3 = TypeUtilsKt.asTypeProjection(new NewCapturedType(captureStatus, unwrap, obj3));
            }
            arrayList.add(obj3);
        }
        List list = (List) arrayList;
        TypeSubstitutor buildSubstitutor = TypeConstructorSubstitution.Companion.create(simpleType.getConstructor(), list).buildSubstitutor();
        int size = arguments.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection3 = (TypeProjection) arguments.get(i);
            typeProjection2 = (TypeProjection) list.get(i);
            if (typeProjection3.getProjectionKind() != Variance.INVARIANT) {
                Iterable<KotlinType> upperBounds = ((TypeParameterDescriptor) simpleType.getConstructor().getParameters().get(i)).getUpperBounds();
                Collection arrayList2 = new ArrayList(acns.a((Iterable) upperBounds, 10));
                for (KotlinType safeSubstitute : upperBounds) {
                    arrayList2.add(NewKotlinTypeChecker.INSTANCE.transformToNewType(buildSubstitutor.safeSubstitute(safeSubstitute, Variance.INVARIANT).unwrap()));
                }
                List list2 = (List) arrayList2;
                if (!typeProjection3.isStarProjection() && typeProjection3.getProjectionKind() == Variance.OUT_VARIANCE) {
                    list2 = acnz.a((Collection) list2, (Object) NewKotlinTypeChecker.INSTANCE.transformToNewType(typeProjection3.getType().unwrap()));
                }
                KotlinType type = typeProjection2.getType();
                if (type != null) {
                    NewCapturedType newCapturedType = (NewCapturedType) type;
                    newCapturedType.getConstructor().initializeSupertypes(list2);
                    acrc.invoke(Integer.valueOf(i), newCapturedType);
                } else {
                    throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                }
            }
        }
        return KotlinTypeFactory.simpleType(simpleType.getAnnotations(), simpleType.getConstructor(), list, simpleType.isMarkedNullable());
    }
}
