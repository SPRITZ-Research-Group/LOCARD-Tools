package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acnz;
import defpackage.acqr;
import defpackage.acrc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeCheckerImpl;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import kotlin.y;

public class OverridingUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final OverridingUtil DEFAULT = new OverridingUtil(new TypeConstructorEquality() {
        public final boolean equals(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
            return typeConstructor.equals(typeConstructor2);
        }
    });
    private static final List<ExternalOverridabilityCondition> EXTERNAL_CONDITIONS = acnz.k(ServiceLoader.load(ExternalOverridabilityCondition.class, ExternalOverridabilityCondition.class.getClassLoader()));
    private final TypeConstructorEquality equalityAxioms;

    public class OverrideCompatibilityInfo {
        private static final OverrideCompatibilityInfo SUCCESS = new OverrideCompatibilityInfo(Result.OVERRIDABLE, "SUCCESS");
        private final String debugMessage;
        private final Result overridable;

        public enum Result {
            OVERRIDABLE,
            INCOMPATIBLE,
            CONFLICT
        }

        public static OverrideCompatibilityInfo success() {
            return SUCCESS;
        }

        public static OverrideCompatibilityInfo incompatible(String str) {
            return new OverrideCompatibilityInfo(Result.INCOMPATIBLE, str);
        }

        public static OverrideCompatibilityInfo conflict(String str) {
            return new OverrideCompatibilityInfo(Result.CONFLICT, str);
        }

        public OverrideCompatibilityInfo(Result result, String str) {
            this.overridable = result;
            this.debugMessage = str;
        }

        public Result getResult() {
            return this.overridable;
        }
    }

    public static OverridingUtil createWithEqualityAxioms(TypeConstructorEquality typeConstructorEquality) {
        return new OverridingUtil(typeConstructorEquality);
    }

    private OverridingUtil(TypeConstructorEquality typeConstructorEquality) {
        this.equalityAxioms = typeConstructorEquality;
    }

    public static <D extends CallableDescriptor> Set<D> filterOutOverridden(Set<D> set) {
        return filterOverrides(set, new acrc<D, D, m<CallableDescriptor, CallableDescriptor>>() {
            public final m<CallableDescriptor, CallableDescriptor> invoke(D d, D d2) {
                return new m(d, d2);
            }
        });
    }

    public static <D> Set<D> filterOverrides(Set<D> set, acrc<? super D, ? super D, m<CallableDescriptor, CallableDescriptor>> acrc) {
        if (set.size() <= 1) {
            return set;
        }
        Set<D> linkedHashSet = new LinkedHashSet();
        for (Object next : set) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                m mVar = (m) acrc.invoke(next, it.next());
                CallableDescriptor callableDescriptor = (CallableDescriptor) mVar.c();
                CallableDescriptor callableDescriptor2 = (CallableDescriptor) mVar.d();
                if (!overrides(callableDescriptor, callableDescriptor2)) {
                    if (overrides(callableDescriptor2, callableDescriptor)) {
                        break;
                    }
                } else {
                    it.remove();
                }
            }
            linkedHashSet.add(next);
        }
        return linkedHashSet;
    }

    public static <D extends CallableDescriptor> boolean overrides(D d, D d2) {
        if (!d.equals(d2) && DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(d.getOriginal(), d2.getOriginal())) {
            return true;
        }
        DeclarationDescriptor original = d2.getOriginal();
        for (CallableDescriptor areEquivalent : DescriptorUtils.getAllOverriddenDescriptors(d)) {
            if (DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(original, areEquivalent)) {
                return true;
            }
        }
        return false;
    }

    public static Set<CallableMemberDescriptor> getOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor) {
        Set<CallableMemberDescriptor> linkedHashSet = new LinkedHashSet();
        collectOverriddenDeclarations(callableMemberDescriptor, linkedHashSet);
        return linkedHashSet;
    }

    private static void collectOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor, Set<CallableMemberDescriptor> set) {
        if (callableMemberDescriptor.getKind().isReal()) {
            set.add(callableMemberDescriptor);
        } else if (callableMemberDescriptor.getOverriddenDescriptors().isEmpty()) {
            throw new IllegalStateException("No overridden descriptors found for (fake override) ".concat(String.valueOf(callableMemberDescriptor)));
        } else {
            for (CallableMemberDescriptor collectOverriddenDeclarations : callableMemberDescriptor.getOverriddenDescriptors()) {
                collectOverriddenDeclarations(collectOverriddenDeclarations, set);
            }
        }
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor) {
        return isOverridableBy(callableDescriptor, callableDescriptor2, classDescriptor, false);
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor, boolean z) {
        OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = isOverridableByWithoutExternalConditions(callableDescriptor, callableDescriptor2, z);
        Object obj = isOverridableByWithoutExternalConditions.getResult() == Result.OVERRIDABLE ? 1 : null;
        for (ExternalOverridabilityCondition externalOverridabilityCondition : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition.getContract() != Contract.CONFLICTS_ONLY && (obj == null || externalOverridabilityCondition.getContract() != Contract.SUCCESS_ONLY)) {
                switch (externalOverridabilityCondition.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor)) {
                    case OVERRIDABLE:
                        obj = 1;
                        break;
                    case CONFLICT:
                        return OverrideCompatibilityInfo.conflict("External condition failed");
                    case INCOMPATIBLE:
                        return OverrideCompatibilityInfo.incompatible("External condition");
                    default:
                        break;
                }
            }
        }
        if (obj == null) {
            return isOverridableByWithoutExternalConditions;
        }
        for (ExternalOverridabilityCondition externalOverridabilityCondition2 : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition2.getContract() == Contract.CONFLICTS_ONLY) {
                switch (externalOverridabilityCondition2.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor)) {
                    case OVERRIDABLE:
                        StringBuilder stringBuilder = new StringBuilder("Contract violation in ");
                        stringBuilder.append(externalOverridabilityCondition2.getClass().getName());
                        stringBuilder.append(" condition. It's not supposed to end with success");
                        throw new IllegalStateException(stringBuilder.toString());
                    case CONFLICT:
                        return OverrideCompatibilityInfo.conflict("External condition failed");
                    case INCOMPATIBLE:
                        return OverrideCompatibilityInfo.incompatible("External condition");
                    default:
                        break;
                }
            }
        }
        return OverrideCompatibilityInfo.success();
    }

    public OverrideCompatibilityInfo isOverridableByWithoutExternalConditions(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z) {
        OverrideCompatibilityInfo basicOverridabilityProblem = getBasicOverridabilityProblem(callableDescriptor, callableDescriptor2);
        if (basicOverridabilityProblem != null) {
            return basicOverridabilityProblem;
        }
        List compiledValueParameters = compiledValueParameters(callableDescriptor);
        List compiledValueParameters2 = compiledValueParameters(callableDescriptor2);
        List typeParameters = callableDescriptor.getTypeParameters();
        List typeParameters2 = callableDescriptor2.getTypeParameters();
        int i = 0;
        if (typeParameters.size() != typeParameters2.size()) {
            while (i < compiledValueParameters.size()) {
                if (!KotlinTypeChecker.DEFAULT.equalTypes((KotlinType) compiledValueParameters.get(i), (KotlinType) compiledValueParameters2.get(i))) {
                    return OverrideCompatibilityInfo.incompatible("Type parameter number mismatch");
                }
                i++;
            }
            return OverrideCompatibilityInfo.conflict("Type parameter number mismatch");
        }
        KotlinTypeChecker createTypeChecker = createTypeChecker(typeParameters, typeParameters2);
        for (int i2 = 0; i2 < typeParameters.size(); i2++) {
            if (!areTypeParametersEquivalent((TypeParameterDescriptor) typeParameters.get(i2), (TypeParameterDescriptor) typeParameters2.get(i2), createTypeChecker)) {
                return OverrideCompatibilityInfo.incompatible("Type parameter bounds mismatch");
            }
        }
        for (int i3 = 0; i3 < compiledValueParameters.size(); i3++) {
            if (!areTypesEquivalent((KotlinType) compiledValueParameters.get(i3), (KotlinType) compiledValueParameters2.get(i3), createTypeChecker)) {
                return OverrideCompatibilityInfo.incompatible("Value parameter type mismatch");
            }
        }
        if ((callableDescriptor instanceof FunctionDescriptor) && (callableDescriptor2 instanceof FunctionDescriptor) && ((FunctionDescriptor) callableDescriptor).isSuspend() != ((FunctionDescriptor) callableDescriptor2).isSuspend()) {
            return OverrideCompatibilityInfo.conflict("Incompatible suspendability");
        }
        if (z) {
            KotlinType returnType = callableDescriptor.getReturnType();
            KotlinType returnType2 = callableDescriptor2.getReturnType();
            if (!(returnType == null || returnType2 == null)) {
                if (KotlinTypeKt.isError(returnType2) && KotlinTypeKt.isError(returnType)) {
                    i = 1;
                }
                if (i == 0 && !createTypeChecker.isSubtypeOf(returnType2, returnType)) {
                    return OverrideCompatibilityInfo.conflict("Return type mismatch");
                }
            }
        }
        return OverrideCompatibilityInfo.success();
    }

    public static OverrideCompatibilityInfo getBasicOverridabilityProblem(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        boolean z = callableDescriptor instanceof FunctionDescriptor;
        if (!z || (callableDescriptor2 instanceof FunctionDescriptor)) {
            boolean z2 = callableDescriptor instanceof PropertyDescriptor;
            if (!z2 || (callableDescriptor2 instanceof PropertyDescriptor)) {
                if (!z && !z2) {
                    throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: ".concat(String.valueOf(callableDescriptor)));
                } else if (!callableDescriptor.getName().equals(callableDescriptor2.getName())) {
                    return OverrideCompatibilityInfo.incompatible("Name mismatch");
                } else {
                    OverrideCompatibilityInfo checkReceiverAndParameterCount = checkReceiverAndParameterCount(callableDescriptor, callableDescriptor2);
                    return checkReceiverAndParameterCount != null ? checkReceiverAndParameterCount : null;
                }
            }
        }
        return OverrideCompatibilityInfo.incompatible("Member kind mismatch");
    }

    private KotlinTypeChecker createTypeChecker(List<TypeParameterDescriptor> list, List<TypeParameterDescriptor> list2) {
        if (list.isEmpty()) {
            return KotlinTypeCheckerImpl.withAxioms(this.equalityAxioms);
        }
        final Map hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(((TypeParameterDescriptor) list.get(i)).getTypeConstructor(), ((TypeParameterDescriptor) list2.get(i)).getTypeConstructor());
        }
        return KotlinTypeCheckerImpl.withAxioms(new TypeConstructorEquality() {
            public boolean equals(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
                if (OverridingUtil.this.equalityAxioms.equals(typeConstructor, typeConstructor2)) {
                    return true;
                }
                TypeConstructor typeConstructor3 = (TypeConstructor) hashMap.get(typeConstructor);
                TypeConstructor typeConstructor4 = (TypeConstructor) hashMap.get(typeConstructor2);
                if ((typeConstructor3 == null || !typeConstructor3.equals(typeConstructor2)) && (typeConstructor4 == null || !typeConstructor4.equals(typeConstructor))) {
                    return false;
                }
                return true;
            }
        });
    }

    private static OverrideCompatibilityInfo checkReceiverAndParameterCount(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        Object obj = null;
        Object obj2 = callableDescriptor.getExtensionReceiverParameter() == null ? 1 : null;
        if (callableDescriptor2.getExtensionReceiverParameter() == null) {
            obj = 1;
        }
        if (obj2 != obj) {
            return OverrideCompatibilityInfo.incompatible("Receiver presence mismatch");
        }
        return callableDescriptor.getValueParameters().size() != callableDescriptor2.getValueParameters().size() ? OverrideCompatibilityInfo.incompatible("Value parameter number mismatch") : null;
    }

    private static boolean areTypesEquivalent(KotlinType kotlinType, KotlinType kotlinType2, KotlinTypeChecker kotlinTypeChecker) {
        Object obj = (KotlinTypeKt.isError(kotlinType) && KotlinTypeKt.isError(kotlinType2)) ? 1 : null;
        return obj != null || kotlinTypeChecker.equalTypes(kotlinType, kotlinType2);
    }

    private static boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, KotlinTypeChecker kotlinTypeChecker) {
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        List arrayList = new ArrayList(typeParameterDescriptor2.getUpperBounds());
        if (upperBounds.size() != arrayList.size()) {
            return false;
        }
        for (KotlinType kotlinType : upperBounds) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (areTypesEquivalent(kotlinType, (KotlinType) listIterator.next(), kotlinTypeChecker)) {
                    listIterator.remove();
                }
            }
            return false;
        }
        return true;
    }

    private static List<KotlinType> compiledValueParameters(CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        List<KotlinType> arrayList = new ArrayList();
        if (extensionReceiverParameter != null) {
            arrayList.add(extensionReceiverParameter.getType());
        }
        for (ValueParameterDescriptor type : callableDescriptor.getValueParameters()) {
            arrayList.add(type.getType());
        }
        return arrayList;
    }

    public static void generateOverridesInFunctionGroup(Name name, Collection<? extends CallableMemberDescriptor> collection, Collection<? extends CallableMemberDescriptor> collection2, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        Collection linkedHashSet = new LinkedHashSet(collection);
        for (CallableMemberDescriptor extractAndBindOverridesForMember : collection2) {
            linkedHashSet.removeAll(extractAndBindOverridesForMember(extractAndBindOverridesForMember, collection, classDescriptor, overridingStrategy));
        }
        createAndBindFakeOverrides(classDescriptor, linkedHashSet, overridingStrategy);
    }

    public static boolean isVisibleForOverride(MemberDescriptor memberDescriptor, MemberDescriptor memberDescriptor2) {
        return !Visibilities.isPrivate(memberDescriptor2.getVisibility()) && Visibilities.isVisibleIgnoringReceiver(memberDescriptor2, memberDescriptor);
    }

    private static Collection<CallableMemberDescriptor> extractAndBindOverridesForMember(CallableMemberDescriptor callableMemberDescriptor, Collection<? extends CallableMemberDescriptor> collection, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        Collection<CallableMemberDescriptor> arrayList = new ArrayList(collection.size());
        Collection create = SmartSet.create();
        for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
            Result result = DEFAULT.isOverridableBy(callableMemberDescriptor2, callableMemberDescriptor, classDescriptor).getResult();
            boolean isVisibleForOverride = isVisibleForOverride(callableMemberDescriptor, callableMemberDescriptor2);
            switch (result) {
                case OVERRIDABLE:
                    if (isVisibleForOverride) {
                        create.add(callableMemberDescriptor2);
                    }
                    arrayList.add(callableMemberDescriptor2);
                    break;
                case CONFLICT:
                    if (isVisibleForOverride) {
                        overridingStrategy.overrideConflict(callableMemberDescriptor2, callableMemberDescriptor);
                    }
                    arrayList.add(callableMemberDescriptor2);
                    break;
                default:
                    break;
            }
        }
        overridingStrategy.setOverriddenDescriptors(callableMemberDescriptor, create);
        return arrayList;
    }

    private static boolean allHasSameContainingDeclaration(Collection<CallableMemberDescriptor> collection) {
        if (collection.size() < 2) {
            return true;
        }
        final DeclarationDescriptor containingDeclaration = ((CallableMemberDescriptor) collection.iterator().next()).getContainingDeclaration();
        acqr anonymousClass4 = new acqr<CallableMemberDescriptor, Boolean>() {
            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(callableMemberDescriptor.getContainingDeclaration() == containingDeclaration);
            }
        };
        if (!((collection instanceof Collection) && collection.isEmpty())) {
            for (Object invoke : collection) {
                if (!((Boolean) anonymousClass4.invoke(invoke)).booleanValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void createAndBindFakeOverrides(ClassDescriptor classDescriptor, Collection<CallableMemberDescriptor> collection, OverridingStrategy overridingStrategy) {
        if (allHasSameContainingDeclaration(collection)) {
            for (CallableMemberDescriptor singleton : collection) {
                createAndBindFakeOverride(Collections.singleton(singleton), classDescriptor, overridingStrategy);
            }
            return;
        }
        Object linkedList = new LinkedList(collection);
        while (!linkedList.isEmpty()) {
            createAndBindFakeOverride(extractMembersOverridableInBothWays(VisibilityUtilKt.findMemberWithMaxVisibility(linkedList), linkedList, overridingStrategy), classDescriptor, overridingStrategy);
        }
    }

    public static boolean isMoreSpecific(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        KotlinType returnType = callableDescriptor.getReturnType();
        KotlinType returnType2 = callableDescriptor2.getReturnType();
        if (!isVisibilityMoreSpecific(callableDescriptor, callableDescriptor2)) {
            return false;
        }
        if (callableDescriptor instanceof FunctionDescriptor) {
            return isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2);
        }
        if (callableDescriptor instanceof PropertyDescriptor) {
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) callableDescriptor;
            PropertyDescriptor propertyDescriptor2 = (PropertyDescriptor) callableDescriptor2;
            if (!isAccessorMoreSpecific(propertyDescriptor.getSetter(), propertyDescriptor2.getSetter())) {
                return false;
            }
            if (propertyDescriptor.isVar() && propertyDescriptor2.isVar()) {
                return DEFAULT.createTypeChecker(callableDescriptor.getTypeParameters(), callableDescriptor2.getTypeParameters()).equalTypes(returnType, returnType2);
            }
            if ((propertyDescriptor.isVar() || !propertyDescriptor2.isVar()) && isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2)) {
                return true;
            }
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder("Unexpected callable: ");
        stringBuilder.append(callableDescriptor.getClass());
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private static boolean isVisibilityMoreSpecific(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2) {
        Integer compare = Visibilities.compare(declarationDescriptorWithVisibility.getVisibility(), declarationDescriptorWithVisibility2.getVisibility());
        return compare == null || compare.intValue() >= 0;
    }

    private static boolean isAccessorMoreSpecific(PropertyAccessorDescriptor propertyAccessorDescriptor, PropertyAccessorDescriptor propertyAccessorDescriptor2) {
        return (propertyAccessorDescriptor == null || propertyAccessorDescriptor2 == null) ? true : isVisibilityMoreSpecific(propertyAccessorDescriptor, propertyAccessorDescriptor2);
    }

    private static boolean isMoreSpecificThenAllOf(CallableDescriptor callableDescriptor, Collection<CallableDescriptor> collection) {
        for (CallableDescriptor isMoreSpecific : collection) {
            if (!isMoreSpecific(callableDescriptor, isMoreSpecific)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReturnTypeMoreSpecific(CallableDescriptor callableDescriptor, KotlinType kotlinType, CallableDescriptor callableDescriptor2, KotlinType kotlinType2) {
        return DEFAULT.createTypeChecker(callableDescriptor.getTypeParameters(), callableDescriptor2.getTypeParameters()).isSubtypeOf(kotlinType, kotlinType2);
    }

    public static <H> H selectMostSpecificMember(Collection<H> collection, acqr<H, CallableDescriptor> acqr) {
        if (collection.size() == 1) {
            return acnz.b((Iterable) collection);
        }
        Iterable arrayList = new ArrayList(2);
        Collection c = acnz.c((Iterable) collection, (acqr) acqr);
        H b = acnz.b((Iterable) collection);
        CallableDescriptor callableDescriptor = (CallableDescriptor) acqr.invoke(b);
        for (H next : collection) {
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) acqr.invoke(next);
            if (isMoreSpecificThenAllOf(callableDescriptor2, c)) {
                arrayList.add(next);
            }
            if (isMoreSpecific(callableDescriptor2, callableDescriptor) && !isMoreSpecific(callableDescriptor, callableDescriptor2)) {
                b = next;
            }
        }
        if (arrayList.isEmpty()) {
            return b;
        }
        if (arrayList.size() == 1) {
            return acnz.b(arrayList);
        }
        H h = null;
        for (H next2 : arrayList) {
            if (!FlexibleTypesKt.isFlexible(((CallableDescriptor) acqr.invoke(next2)).getReturnType())) {
                h = next2;
                break;
            }
        }
        if (h != null) {
            return h;
        }
        return acnz.b(arrayList);
    }

    private static void createAndBindFakeOverride(Collection<CallableMemberDescriptor> collection, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        Collection collection2;
        Collection filterVisibleFakeOverrides = filterVisibleFakeOverrides(classDescriptor, collection2);
        boolean isEmpty = filterVisibleFakeOverrides.isEmpty();
        if (!isEmpty) {
            collection2 = filterVisibleFakeOverrides;
        }
        CallableMemberDescriptor copy = ((CallableMemberDescriptor) selectMostSpecificMember(collection2, new acqr<CallableMemberDescriptor, CallableDescriptor>() {
            public final CallableMemberDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return callableMemberDescriptor;
            }
        })).copy(classDescriptor, determineModalityForFakeOverride(collection2, classDescriptor), isEmpty ? Visibilities.INVISIBLE_FAKE : Visibilities.INHERITED, Kind.FAKE_OVERRIDE, false);
        overridingStrategy.setOverriddenDescriptors(copy, collection2);
        overridingStrategy.addFakeOverride(copy);
    }

    private static Modality determineModalityForFakeOverride(Collection<CallableMemberDescriptor> collection, ClassDescriptor classDescriptor) {
        boolean z = true;
        Object obj = null;
        Object obj2 = null;
        for (CallableMemberDescriptor callableMemberDescriptor : collection) {
            switch (callableMemberDescriptor.getModality()) {
                case FINAL:
                    return Modality.FINAL;
                case SEALED:
                    throw new IllegalStateException("Member cannot have SEALED modality: ".concat(String.valueOf(callableMemberDescriptor)));
                case OPEN:
                    obj = 1;
                    break;
                case ABSTRACT:
                    obj2 = 1;
                    break;
                default:
                    break;
            }
        }
        if (!classDescriptor.isExpect() || classDescriptor.getModality() == Modality.ABSTRACT || classDescriptor.getModality() == Modality.SEALED) {
            z = false;
        }
        if (obj != null && obj2 == null) {
            return Modality.OPEN;
        }
        if (obj == null && obj2 != null) {
            return z ? classDescriptor.getModality() : Modality.ABSTRACT;
        } else {
            Set hashSet = new HashSet();
            for (CallableMemberDescriptor overriddenDeclarations : collection) {
                hashSet.addAll(getOverriddenDeclarations(overriddenDeclarations));
            }
            return getMinimalModality(filterOutOverridden(hashSet), z, classDescriptor.getModality());
        }
    }

    private static Modality getMinimalModality(Collection<CallableMemberDescriptor> collection, boolean z, Modality modality) {
        Enum enumR = Modality.ABSTRACT;
        for (CallableMemberDescriptor callableMemberDescriptor : collection) {
            Enum modality2 = (z && callableMemberDescriptor.getModality() == Modality.ABSTRACT) ? modality : callableMemberDescriptor.getModality();
            if (modality2.compareTo(enumR) < 0) {
                enumR = modality2;
            }
        }
        return enumR;
    }

    private static Collection<CallableMemberDescriptor> filterVisibleFakeOverrides(final ClassDescriptor classDescriptor, Collection<CallableMemberDescriptor> collection) {
        return acnz.b((Iterable) collection, new acqr<CallableMemberDescriptor, Boolean>() {
            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                boolean z = !Visibilities.isPrivate(callableMemberDescriptor.getVisibility()) && Visibilities.isVisibleIgnoringReceiver(callableMemberDescriptor, classDescriptor);
                return Boolean.valueOf(z);
            }
        });
    }

    public static <H> Collection<H> extractMembersOverridableInBothWays(H h, Collection<H> collection, acqr<H, CallableDescriptor> acqr, acqr<H, y> acqr2) {
        Collection<H> arrayList = new ArrayList();
        arrayList.add(h);
        CallableDescriptor callableDescriptor = (CallableDescriptor) acqr.invoke(h);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            H next = it.next();
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) acqr.invoke(next);
            if (h == next) {
                it.remove();
            } else {
                Result bothWaysOverridability = getBothWaysOverridability(callableDescriptor, callableDescriptor2);
                if (bothWaysOverridability == Result.OVERRIDABLE) {
                    arrayList.add(next);
                    it.remove();
                } else if (bothWaysOverridability == Result.CONFLICT) {
                    acqr2.invoke(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public static Result getBothWaysOverridability(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        Result result = DEFAULT.isOverridableBy(callableDescriptor2, callableDescriptor, null).getResult();
        Result result2 = DEFAULT.isOverridableBy(callableDescriptor, callableDescriptor2, null).getResult();
        if (result == Result.OVERRIDABLE && result2 == Result.OVERRIDABLE) {
            return Result.OVERRIDABLE;
        }
        return (result == Result.CONFLICT || result2 == Result.CONFLICT) ? Result.CONFLICT : Result.INCOMPATIBLE;
    }

    private static Collection<CallableMemberDescriptor> extractMembersOverridableInBothWays(final CallableMemberDescriptor callableMemberDescriptor, Queue<CallableMemberDescriptor> queue, final OverridingStrategy overridingStrategy) {
        return extractMembersOverridableInBothWays(callableMemberDescriptor, queue, new acqr<CallableMemberDescriptor, CallableDescriptor>() {
            public final CallableDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return callableMemberDescriptor;
            }
        }, new acqr<CallableMemberDescriptor, y>() {
            public final y invoke(CallableMemberDescriptor callableMemberDescriptor) {
                overridingStrategy.inheritanceConflict(callableMemberDescriptor, callableMemberDescriptor);
                return y.a;
            }
        });
    }

    public static void resolveUnknownVisibilityForMember(CallableMemberDescriptor callableMemberDescriptor, acqr<CallableMemberDescriptor, y> acqr) {
        for (CallableMemberDescriptor callableMemberDescriptor2 : callableMemberDescriptor.getOverriddenDescriptors()) {
            if (callableMemberDescriptor2.getVisibility() == Visibilities.INHERITED) {
                resolveUnknownVisibilityForMember(callableMemberDescriptor2, acqr);
            }
        }
        if (callableMemberDescriptor.getVisibility() == Visibilities.INHERITED) {
            Visibility visibility;
            Visibility computeVisibilityToInherit = computeVisibilityToInherit(callableMemberDescriptor);
            if (computeVisibilityToInherit == null) {
                if (acqr != null) {
                    acqr.invoke(callableMemberDescriptor);
                }
                visibility = Visibilities.PUBLIC;
            } else {
                visibility = computeVisibilityToInherit;
            }
            if (callableMemberDescriptor instanceof PropertyDescriptorImpl) {
                ((PropertyDescriptorImpl) callableMemberDescriptor).setVisibility(visibility);
                for (PropertyAccessorDescriptor resolveUnknownVisibilityForMember : ((PropertyDescriptor) callableMemberDescriptor).getAccessors()) {
                    resolveUnknownVisibilityForMember(resolveUnknownVisibilityForMember, computeVisibilityToInherit == null ? null : acqr);
                }
            } else if (callableMemberDescriptor instanceof FunctionDescriptorImpl) {
                ((FunctionDescriptorImpl) callableMemberDescriptor).setVisibility(visibility);
            } else {
                PropertyAccessorDescriptorImpl propertyAccessorDescriptorImpl = (PropertyAccessorDescriptorImpl) callableMemberDescriptor;
                propertyAccessorDescriptorImpl.setVisibility(visibility);
                if (visibility != propertyAccessorDescriptorImpl.getCorrespondingProperty().getVisibility()) {
                    propertyAccessorDescriptorImpl.setDefault(false);
                }
            }
        }
    }

    private static Visibility computeVisibilityToInherit(CallableMemberDescriptor callableMemberDescriptor) {
        Collection<CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Visibility findMaxVisibility = findMaxVisibility(overriddenDescriptors);
        if (findMaxVisibility == null) {
            return null;
        }
        if (callableMemberDescriptor.getKind() != Kind.FAKE_OVERRIDE) {
            return findMaxVisibility.normalize();
        }
        for (CallableMemberDescriptor callableMemberDescriptor2 : overriddenDescriptors) {
            if (callableMemberDescriptor2.getModality() != Modality.ABSTRACT && !callableMemberDescriptor2.getVisibility().equals(findMaxVisibility)) {
                return null;
            }
        }
        return findMaxVisibility;
    }

    public static Visibility findMaxVisibility(Collection<? extends CallableMemberDescriptor> collection) {
        if (collection.isEmpty()) {
            return Visibilities.DEFAULT_VISIBILITY;
        }
        Visibility visibility;
        loop0:
        while (true) {
            visibility = null;
            for (CallableMemberDescriptor visibility2 : collection) {
                Visibility visibility3 = visibility2.getVisibility();
                if (visibility != null) {
                    Integer compare = Visibilities.compare(visibility3, visibility);
                    if (compare != null) {
                        if (compare.intValue() <= 0) {
                        }
                    }
                }
                visibility = visibility3;
            }
            break loop0;
        }
        if (visibility == null) {
            return null;
        }
        for (CallableMemberDescriptor visibility4 : collection) {
            Integer compare2 = Visibilities.compare(visibility, visibility4.getVisibility());
            if (compare2 != null) {
                if (compare2.intValue() < 0) {
                }
            }
            return null;
        }
        return visibility;
    }
}
