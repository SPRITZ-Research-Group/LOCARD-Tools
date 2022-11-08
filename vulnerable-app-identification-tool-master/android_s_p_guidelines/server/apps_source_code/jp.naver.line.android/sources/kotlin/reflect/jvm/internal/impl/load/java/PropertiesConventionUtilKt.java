package kotlin.reflect.jvm.internal.impl.load.java;

import defpackage.acnr;
import defpackage.acnz;
import defpackage.addc;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

public final class PropertiesConventionUtilKt {
    public static final Name propertyNameByGetMethodName(Name name) {
        Name propertyNameFromAccessorMethodName$default = propertyNameFromAccessorMethodName$default(name, "get", false, null, 12, null);
        if (propertyNameFromAccessorMethodName$default != null) {
            return propertyNameFromAccessorMethodName$default;
        }
        return propertyNameFromAccessorMethodName$default(name, "is", false, null, 8, null);
    }

    public static final Name propertyNameBySetMethodName(Name name, boolean z) {
        return propertyNameFromAccessorMethodName$default(name, "set", false, z ? "is" : null, 4, null);
    }

    public static final List<Name> propertyNamesBySetMethodName(Name name) {
        return acnz.h((Iterable) acnr.b((Object[]) new Name[]{propertyNameBySetMethodName(name, false), propertyNameBySetMethodName(name, true)}));
    }

    static /* synthetic */ Name propertyNameFromAccessorMethodName$default(Name name, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return propertyNameFromAccessorMethodName(name, str, z, str2);
    }

    private static final Name propertyNameFromAccessorMethodName(Name name, String str, boolean z, String str2) {
        if (name.isSpecial()) {
            return null;
        }
        String identifier = name.getIdentifier();
        if (!identifier.startsWith(str) || identifier.length() == str.length()) {
            return null;
        }
        char charAt = identifier.charAt(str.length());
        if ('a' <= charAt && 'z' >= charAt) {
            return null;
        }
        if (str2 != null) {
            if (!aa.a || z) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(addc.a(identifier, (CharSequence) str));
                return Name.identifier(stringBuilder.toString());
            }
            throw new AssertionError("Assertion failed");
        } else if (!z) {
            return name;
        } else {
            String decapitalizeSmart = CapitalizeDecapitalizeKt.decapitalizeSmart(addc.a(identifier, (CharSequence) str), true);
            if (Name.isValidIdentifier(decapitalizeSmart)) {
                return Name.identifier(decapitalizeSmart);
            }
            return null;
        }
    }

    public static final List<Name> getPropertyNamesCandidatesByAccessorName(Name name) {
        String asString = name.asString();
        if (JvmAbi.isGetterName(asString)) {
            return acnr.a((Object) propertyNameByGetMethodName(name));
        }
        if (JvmAbi.isSetterName(asString)) {
            return propertyNamesBySetMethodName(name);
        }
        return BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name);
    }
}
