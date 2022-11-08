package kotlin.reflect.jvm.internal.impl.load.java;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import defpackage.acns;
import defpackage.acob;
import defpackage.acom;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.u;

public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE = SpecialBuiltinMembers.method("java/util/List", "removeAt", JvmPrimitiveType.INT.getDesc(), "Ljava/lang/Object;");
    private static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        Map a = acom.a(u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toByte", "", JvmPrimitiveType.BYTE.getDesc()), Name.identifier("byteValue")), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toShort", "", JvmPrimitiveType.SHORT.getDesc()), Name.identifier("shortValue")), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toInt", "", JvmPrimitiveType.INT.getDesc()), Name.identifier("intValue")), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toLong", "", JvmPrimitiveType.LONG.getDesc()), Name.identifier("longValue")), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toFloat", "", JvmPrimitiveType.FLOAT.getDesc()), Name.identifier("floatValue")), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("Number"), "toDouble", "", JvmPrimitiveType.DOUBLE.getDesc()), Name.identifier("doubleValue")), u.a(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier(ProductAction.ACTION_REMOVE)), u.a(SpecialBuiltinMembers.method(signatureBuildingComponents.javaLang("CharSequence"), "get", JvmPrimitiveType.INT.getDesc(), JvmPrimitiveType.CHAR.getDesc()), Name.identifier("charAt")));
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = a;
        Map linkedHashMap = new LinkedHashMap(acom.a(a.size()));
        for (Entry entry : a.entrySet()) {
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Iterable<NameAndSignature> keySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        Collection arrayList = new ArrayList(acns.a((Iterable) keySet, 10));
        for (NameAndSignature name : keySet) {
            arrayList.add(name.getName());
        }
        ORIGINAL_SHORT_NAMES = (List) arrayList;
        Iterable<Entry> entrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        arrayList = new ArrayList(acns.a((Iterable) entrySet, 10));
        for (Entry entry2 : entrySet) {
            arrayList.add(new m(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        a = new LinkedHashMap();
        for (m mVar : (List) arrayList) {
            Name name2 = (Name) mVar.b();
            ArrayList arrayList2 = a.get(name2);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                a.put(name2, arrayList2);
            }
            arrayList2.add((Name) mVar.a());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = a;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(Name name) {
        return ORIGINAL_SHORT_NAMES.contains(name);
    }

    public final Name getJvmName(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Map map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor);
        return computeJvmSignature == null ? null : (Name) map.get(computeJvmSignature);
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        return KotlinBuiltIns.isBuiltIn((DeclarationDescriptor) simpleFunctionDescriptor) && DescriptorUtilsKt.firstOverridden$default(simpleFunctionDescriptor, false, new BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1(simpleFunctionDescriptor), 1, null) != null;
    }

    public final List<Name> getBuiltinFunctionNamesByJvmName(Name name) {
        List<Name> list = (List) JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        return list == null ? acob.a : list;
    }

    public final boolean isRemoveAtByIndex(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        return acry.a(simpleFunctionDescriptor.getName().asString(), (Object) "removeAt") && acry.a(MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor), REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
