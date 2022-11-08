package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.google.obf.ly;
import defpackage.acnz;
import defpackage.acqr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.v;

public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    public final String javaLang(String str) {
        return "java/lang/".concat(String.valueOf(str));
    }

    public final String javaUtil(String str) {
        return "java/util/".concat(String.valueOf(str));
    }

    public final String javaFunction(String str) {
        return "java/util/function/".concat(String.valueOf(str));
    }

    public final LinkedHashSet<String> inJavaLang(String str, String... strArr) {
        return inClass(javaLang(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final LinkedHashSet<String> inJavaUtil(String str, String... strArr) {
        return inClass(javaUtil(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final LinkedHashSet<String> inClass(String str, String... strArr) {
        Collection linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(ly.a);
            stringBuilder.append(str2);
            linkedHashSet.add(stringBuilder.toString());
        }
        return (LinkedHashSet) linkedHashSet;
    }

    public final String signature(ClassDescriptor classDescriptor, String str) {
        return signature(MethodSignatureMappingKt.getInternalName(classDescriptor), str);
    }

    public final String signature(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(ly.a);
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public final String jvmDescriptor(String str, List<String> list, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append('(');
        stringBuilder.append(acnz.a((Iterable) list, (CharSequence) "", null, null, 0, null, (acqr) SignatureBuildingComponents$jvmDescriptor$1.INSTANCE, 30));
        stringBuilder.append(')');
        stringBuilder.append(escapeClassName(str2));
        return stringBuilder.toString();
    }

    private final String escapeClassName(String str) {
        if (str.length() <= 1) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder("L");
        stringBuilder.append(str);
        stringBuilder.append(';');
        return stringBuilder.toString();
    }

    public final String[] constructors(String... strArr) {
        Collection arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            StringBuilder stringBuilder = new StringBuilder("<init>(");
            stringBuilder.append(str);
            stringBuilder.append(")V");
            arrayList.add(stringBuilder.toString());
        }
        Object[] toArray = ((List) arrayList).toArray(new String[0]);
        if (toArray != null) {
            return (String[]) toArray;
        }
        throw new v("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
