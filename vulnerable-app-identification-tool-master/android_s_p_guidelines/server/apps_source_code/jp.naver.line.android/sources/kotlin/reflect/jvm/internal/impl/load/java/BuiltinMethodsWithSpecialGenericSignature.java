package kotlin.reflect.jvm.internal.impl.load.java;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import defpackage.acns;
import defpackage.acnz;
import defpackage.acom;
import defpackage.acot;
import defpackage.acou;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.u;

public final class BuiltinMethodsWithSpecialGenericSignature {
    private static final List<String> ERASED_COLLECTION_PARAMETER_NAMES;
    private static final List<NameAndSignature> ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
    private static final List<String> ERASED_COLLECTION_PARAMETER_SIGNATURES;
    private static final Set<Name> ERASED_VALUE_PARAMETERS_SHORT_NAMES;
    private static final Set<String> ERASED_VALUE_PARAMETERS_SIGNATURES;
    private static final Map<NameAndSignature, TypeSafeBarrierDescription> GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
    public static final BuiltinMethodsWithSpecialGenericSignature INSTANCE = new BuiltinMethodsWithSpecialGenericSignature();
    private static final Map<String, TypeSafeBarrierDescription> SIGNATURE_TO_DEFAULT_VALUES_MAP;

    public enum SpecialSignatureInfo {
        ;
        
        private final boolean isObjectReplacedWithTypeParameter;
        private final String valueParametersSignature;

        private SpecialSignatureInfo(String str, boolean z) {
            this.valueParametersSignature = str;
            this.isObjectReplacedWithTypeParameter = z;
        }
    }

    public enum TypeSafeBarrierDescription {
        ;
        
        private final Object defaultValue;

        final class MAP_GET_OR_DEFAULT extends TypeSafeBarrierDescription {
            MAP_GET_OR_DEFAULT(String str, int i) {
                super(str, i, null, null);
            }
        }

        private TypeSafeBarrierDescription(Object obj) {
            this.defaultValue = obj;
        }
    }

    static {
        Iterable<String> a = acot.a((Object[]) new String[]{"containsAll", "removeAll", "retainAll"});
        Collection arrayList = new ArrayList(acns.a((Iterable) a, 10));
        for (String access$method : a) {
            arrayList.add(SpecialBuiltinMembers.method("java/util/Collection", access$method, "Ljava/util/Collection;", JvmPrimitiveType.BOOLEAN.getDesc()));
        }
        List list = (List) arrayList;
        ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES = list;
        Iterable<NameAndSignature> iterable = list;
        Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable, 10));
        for (NameAndSignature signature : iterable) {
            arrayList2.add(signature.getSignature());
        }
        ERASED_COLLECTION_PARAMETER_SIGNATURES = (List) arrayList2;
        Iterable<NameAndSignature> iterable2 = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        arrayList = new ArrayList(acns.a((Iterable) iterable2, 10));
        for (NameAndSignature signature2 : iterable2) {
            arrayList.add(signature2.getName().asString());
        }
        ERASED_COLLECTION_PARAMETER_NAMES = (List) arrayList;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        Map a2 = acom.a(u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Collection"), "contains", "Ljava/lang/Object;", JvmPrimitiveType.BOOLEAN.getDesc()), TypeSafeBarrierDescription.FALSE), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Collection"), ProductAction.ACTION_REMOVE, "Ljava/lang/Object;", JvmPrimitiveType.BOOLEAN.getDesc()), TypeSafeBarrierDescription.FALSE), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), "containsKey", "Ljava/lang/Object;", JvmPrimitiveType.BOOLEAN.getDesc()), TypeSafeBarrierDescription.FALSE), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), "containsValue", "Ljava/lang/Object;", JvmPrimitiveType.BOOLEAN.getDesc()), TypeSafeBarrierDescription.FALSE), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), ProductAction.ACTION_REMOVE, "Ljava/lang/Object;Ljava/lang/Object;", JvmPrimitiveType.BOOLEAN.getDesc()), TypeSafeBarrierDescription.FALSE), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.MAP_GET_OR_DEFAULT), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), "get", "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), ProductAction.ACTION_REMOVE, "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("List"), "indexOf", "Ljava/lang/Object;", JvmPrimitiveType.INT.getDesc()), TypeSafeBarrierDescription.INDEX), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("List"), "lastIndexOf", "Ljava/lang/Object;", JvmPrimitiveType.INT.getDesc()), TypeSafeBarrierDescription.INDEX));
        GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP = a2;
        Map linkedHashMap = new LinkedHashMap(acom.a(a2.size()));
        for (Entry entry : a2.entrySet()) {
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_DEFAULT_VALUES_MAP = linkedHashMap;
        iterable2 = acou.b(GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP.keySet(), (Iterable) ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES);
        arrayList = new ArrayList(acns.a((Iterable) iterable2, 10));
        for (NameAndSignature name : iterable2) {
            arrayList.add(name.getName());
        }
        ERASED_VALUE_PARAMETERS_SHORT_NAMES = acnz.m((List) arrayList);
        arrayList = new ArrayList(acns.a((Iterable) iterable2, 10));
        for (NameAndSignature signature3 : iterable2) {
            arrayList.add(signature3.getSignature());
        }
        ERASED_VALUE_PARAMETERS_SIGNATURES = acnz.m((List) arrayList);
    }

    private BuiltinMethodsWithSpecialGenericSignature() {
    }

    private final boolean getHasErasedValueParametersInJava(CallableMemberDescriptor callableMemberDescriptor) {
        return acnz.a((Iterable) ERASED_VALUE_PARAMETERS_SIGNATURES, (Object) MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor));
    }

    public static final FunctionDescriptor getOverriddenBuiltinFunctionWithErasedValueParametersInJava(FunctionDescriptor functionDescriptor) {
        if (INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(functionDescriptor.getName())) {
            return (FunctionDescriptor) DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, BuiltinMethodsWithSpecialGenericSignature$getOverriddenBuiltinFunctionWithErasedValueParametersInJava$1.INSTANCE, 1, null);
        }
        return null;
    }

    public final boolean getSameAsBuiltinMethodWithErasedValueParameters(Name name) {
        return ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(name);
    }

    public static final SpecialSignatureInfo getSpecialSignatureInfo(CallableMemberDescriptor callableMemberDescriptor) {
        if (!ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return null;
        }
        callableMemberDescriptor = DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor, false, BuiltinMethodsWithSpecialGenericSignature$getSpecialSignatureInfo$builtinSignature$1.INSTANCE, 1, null);
        if (callableMemberDescriptor != null) {
            String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor);
            if (computeJvmSignature != null) {
                if (ERASED_COLLECTION_PARAMETER_SIGNATURES.contains(computeJvmSignature)) {
                    return SpecialSignatureInfo.ONE_COLLECTION_PARAMETER;
                }
                Object obj = SIGNATURE_TO_DEFAULT_VALUES_MAP.get(computeJvmSignature);
                if (obj == null) {
                    acry.a();
                }
                if (((TypeSafeBarrierDescription) obj) == TypeSafeBarrierDescription.NULL) {
                    return SpecialSignatureInfo.OBJECT_PARAMETER_GENERIC;
                }
                return SpecialSignatureInfo.OBJECT_PARAMETER_NON_GENERIC;
            }
        }
        return null;
    }
}
