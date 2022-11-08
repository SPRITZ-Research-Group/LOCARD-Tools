package kotlin.reflect.jvm.internal.impl.load.java;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.aa;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.u;

public final class BuiltinSpecialProperties {
    private static final Map<Name, List<Name>> GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;
    public static final BuiltinSpecialProperties INSTANCE = new BuiltinSpecialProperties();
    private static final Map<FqName, Name> PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    private static final Set<FqName> SPECIAL_FQ_NAMES;
    private static final Set<Name> SPECIAL_SHORT_NAMES;

    static {
        Map a = acom.a(u.a(SpecialBuiltinMembers.childSafe(KotlinBuiltIns.FQ_NAMES._enum, "name"), Name.identifier("name")), u.a(SpecialBuiltinMembers.childSafe(KotlinBuiltIns.FQ_NAMES._enum, "ordinal"), Name.identifier("ordinal")), u.a(SpecialBuiltinMembers.child(KotlinBuiltIns.FQ_NAMES.collection, "size"), Name.identifier("size")), u.a(SpecialBuiltinMembers.child(KotlinBuiltIns.FQ_NAMES.map, "size"), Name.identifier("size")), u.a(SpecialBuiltinMembers.childSafe(KotlinBuiltIns.FQ_NAMES.charSequence, "length"), Name.identifier("length")), u.a(SpecialBuiltinMembers.child(KotlinBuiltIns.FQ_NAMES.map, "keys"), Name.identifier("keySet")), u.a(SpecialBuiltinMembers.child(KotlinBuiltIns.FQ_NAMES.map, "values"), Name.identifier("values")), u.a(SpecialBuiltinMembers.child(KotlinBuiltIns.FQ_NAMES.map, "entries"), Name.identifier("entrySet")));
        PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = a;
        Iterable<Entry> entrySet = a.entrySet();
        Collection arrayList = new ArrayList(acns.a((Iterable) entrySet, 10));
        for (Entry entry : entrySet) {
            arrayList.add(new m(((FqName) entry.getKey()).shortName(), entry.getValue()));
        }
        a = new LinkedHashMap();
        for (m mVar : (List) arrayList) {
            Name name = (Name) mVar.b();
            ArrayList arrayList2 = a.get(name);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                a.put(name, arrayList2);
            }
            arrayList2.add((Name) mVar.a());
        }
        GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = a;
        Set keySet = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
        SPECIAL_FQ_NAMES = keySet;
        Iterable<FqName> iterable = keySet;
        arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (FqName shortName : iterable) {
            arrayList.add(shortName.shortName());
        }
        SPECIAL_SHORT_NAMES = acnz.m((List) arrayList);
    }

    private BuiltinSpecialProperties() {
    }

    public final Set<Name> getSPECIAL_SHORT_NAMES$descriptors_jvm() {
        return SPECIAL_SHORT_NAMES;
    }

    public final boolean hasBuiltinSpecialPropertyFqName(CallableMemberDescriptor callableMemberDescriptor) {
        if (SPECIAL_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
        }
        return false;
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(CallableMemberDescriptor callableMemberDescriptor) {
        DeclarationDescriptor declarationDescriptor = callableMemberDescriptor;
        if (acnz.a((Iterable) SPECIAL_FQ_NAMES, (Object) DescriptorUtilsKt.fqNameOrNull(declarationDescriptor)) && callableMemberDescriptor.getValueParameters().isEmpty()) {
            return true;
        }
        if (!KotlinBuiltIns.isBuiltIn(declarationDescriptor)) {
            return false;
        }
        Iterable<CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        if (!((overriddenDescriptors instanceof Collection) && ((Collection) overriddenDescriptors).isEmpty())) {
            for (CallableMemberDescriptor hasBuiltinSpecialPropertyFqName : overriddenDescriptors) {
                if (INSTANCE.hasBuiltinSpecialPropertyFqName(hasBuiltinSpecialPropertyFqName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final List<Name> getPropertyNameCandidatesBySpecialGetterName(Name name) {
        List<Name> list = (List) GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name);
        return list == null ? acob.a : list;
    }

    public final String getBuiltinSpecialPropertyGetterName(CallableMemberDescriptor callableMemberDescriptor) {
        boolean isBuiltIn = KotlinBuiltIns.isBuiltIn(callableMemberDescriptor);
        if (!aa.a || isBuiltIn) {
            callableMemberDescriptor = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor), false, BuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1.INSTANCE, 1, null);
            if (callableMemberDescriptor == null) {
                return null;
            }
            Name name = (Name) PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.get(DescriptorUtilsKt.getFqNameSafe(callableMemberDescriptor));
            if (name != null) {
                return name.asString();
            }
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("This method is defined only for builtin members, but ");
        stringBuilder.append(callableMemberDescriptor);
        stringBuilder.append(" found");
        throw new AssertionError(stringBuilder.toString());
    }
}
