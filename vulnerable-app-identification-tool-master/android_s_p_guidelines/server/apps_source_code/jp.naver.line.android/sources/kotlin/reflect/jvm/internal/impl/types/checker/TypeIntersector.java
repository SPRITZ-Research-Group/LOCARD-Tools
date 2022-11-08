package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    enum ResultNullability {
        ;

        final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i, null);
            }

            public final ResultNullability combine(UnwrappedType unwrappedType) {
                return getResultNullability(unwrappedType);
            }
        }

        final class NOT_NULL extends ResultNullability {
            public final NOT_NULL combine(UnwrappedType unwrappedType) {
                return this;
            }

            NOT_NULL(String str, int i) {
                super(str, i, null);
            }
        }

        final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i, null);
            }

            public final ResultNullability combine(UnwrappedType unwrappedType) {
                return getResultNullability(unwrappedType);
            }
        }

        final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i, null);
            }

            public final ResultNullability combine(UnwrappedType unwrappedType) {
                ResultNullability resultNullability = getResultNullability(unwrappedType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        public abstract ResultNullability combine(UnwrappedType unwrappedType);

        protected final ResultNullability getResultNullability(UnwrappedType unwrappedType) {
            if (unwrappedType.isMarkedNullable()) {
                return ACCEPT_NULL;
            }
            if (NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType)) {
                return NOT_NULL;
            }
            return UNKNOWN;
        }
    }

    private TypeIntersector() {
    }

    public final SimpleType intersectTypes$descriptors(List<? extends SimpleType> list) {
        Object obj = list.size() > 1 ? 1 : null;
        if (aa.a && obj == null) {
            StringBuilder stringBuilder = new StringBuilder("Size should be at least 2, but it is ");
            stringBuilder.append(list.size());
            throw new AssertionError(stringBuilder.toString());
        }
        ArrayList arrayList = new ArrayList();
        for (SimpleType simpleType : list) {
            if (simpleType.getConstructor() instanceof IntersectionTypeConstructor) {
                Iterable<KotlinType> supertypes = simpleType.getConstructor().getSupertypes();
                Collection arrayList2 = new ArrayList(acns.a((Iterable) supertypes, 10));
                for (KotlinType upperIfFlexible : supertypes) {
                    Object upperIfFlexible2 = FlexibleTypesKt.upperIfFlexible(upperIfFlexible);
                    if (simpleType.isMarkedNullable()) {
                        upperIfFlexible2 = upperIfFlexible2.makeNullableAsSpecified(true);
                    }
                    arrayList2.add(upperIfFlexible2);
                }
                arrayList.addAll((List) arrayList2);
            } else {
                arrayList.add(simpleType);
            }
        }
        Iterable<UnwrappedType> iterable = arrayList;
        ResultNullability resultNullability = ResultNullability.START;
        for (UnwrappedType combine : iterable) {
            resultNullability = resultNullability.combine(combine);
        }
        Collection linkedHashSet = new LinkedHashSet();
        for (Object obj2 : iterable) {
            Object obj22;
            if (resultNullability == ResultNullability.NOT_NULL) {
                obj22 = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(obj22);
            }
            linkedHashSet.add(obj22);
        }
        return intersectTypesWithoutIntersectionType((LinkedHashSet) linkedHashSet);
    }

    private final SimpleType intersectTypesWithoutIntersectionType(Set<? extends SimpleType> set) {
        if (set.size() == 1) {
            return (SimpleType) acnz.e((Iterable) set);
        }
        Collection collection = set;
        ArrayList arrayList = new ArrayList(collection);
        Iterator it = arrayList.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            SimpleType simpleType = (SimpleType) it.next();
            Iterable<SimpleType> iterable = arrayList;
            if (!((Collection) iterable).isEmpty()) {
                for (SimpleType simpleType2 : iterable) {
                    Object obj;
                    if (simpleType2 != simpleType) {
                        KotlinType kotlinType = simpleType2;
                        KotlinType kotlinType2 = simpleType;
                        if (INSTANCE.isStrictSupertype(kotlinType, kotlinType2) || NewKotlinTypeChecker.INSTANCE.equalTypes(kotlinType, kotlinType2)) {
                            obj = 1;
                            continue;
                            if (obj != null) {
                                z = true;
                                break;
                            }
                        }
                    }
                    obj = null;
                    continue;
                    if (obj != null) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                it.remove();
            }
        }
        int isEmpty = 1 ^ arrayList.isEmpty();
        if (aa.a && isEmpty == 0) {
            StringBuilder stringBuilder = new StringBuilder("This collections cannot be empty! input types: ");
            stringBuilder.append(acnz.a((Iterable) set, null, null, null, 0, null, null, 63));
            throw new AssertionError(stringBuilder.toString());
        } else if (arrayList.size() < 2) {
            return (SimpleType) acnz.i((List) arrayList);
        } else {
            IntersectionTypeConstructor intersectionTypeConstructor = new IntersectionTypeConstructor(collection);
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), intersectionTypeConstructor, acob.a, false, intersectionTypeConstructor.createScopeForKotlinType());
        }
    }

    private final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeChecker newKotlinTypeChecker = NewKotlinTypeChecker.INSTANCE;
        return newKotlinTypeChecker.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeChecker.isSubtypeOf(kotlinType2, kotlinType);
    }
}
