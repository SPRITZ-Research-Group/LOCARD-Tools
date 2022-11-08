package kotlin.reflect.jvm.internal.impl.load.java.components;

import defpackage.acns;
import defpackage.acnw;
import defpackage.acod;
import defpackage.acom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinRetention;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinTarget;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.u;

public final class JavaAnnotationTargetMapper {
    public static final JavaAnnotationTargetMapper INSTANCE = new JavaAnnotationTargetMapper();
    private static final Map<String, KotlinRetention> retentionNameList = acom.a(u.a("RUNTIME", KotlinRetention.RUNTIME), u.a("CLASS", KotlinRetention.BINARY), u.a("SOURCE", KotlinRetention.SOURCE));
    private static final Map<String, EnumSet<KotlinTarget>> targetNameLists = acom.a(u.a("PACKAGE", EnumSet.noneOf(KotlinTarget.class)), u.a("TYPE", EnumSet.of(KotlinTarget.CLASS, KotlinTarget.FILE)), u.a("ANNOTATION_TYPE", EnumSet.of(KotlinTarget.ANNOTATION_CLASS)), u.a("TYPE_PARAMETER", EnumSet.of(KotlinTarget.TYPE_PARAMETER)), u.a("FIELD", EnumSet.of(KotlinTarget.FIELD)), u.a("LOCAL_VARIABLE", EnumSet.of(KotlinTarget.LOCAL_VARIABLE)), u.a("PARAMETER", EnumSet.of(KotlinTarget.VALUE_PARAMETER)), u.a("CONSTRUCTOR", EnumSet.of(KotlinTarget.CONSTRUCTOR)), u.a("METHOD", EnumSet.of(KotlinTarget.FUNCTION, KotlinTarget.PROPERTY_GETTER, KotlinTarget.PROPERTY_SETTER)), u.a("TYPE_USE", EnumSet.of(KotlinTarget.TYPE)));

    private JavaAnnotationTargetMapper() {
    }

    public final Set<KotlinTarget> mapJavaTargetArgumentByName(String str) {
        EnumSet enumSet = (EnumSet) targetNameLists.get(str);
        return enumSet != null ? enumSet : acod.a;
    }

    public final ConstantValue<?> mapJavaTargetArguments$descriptors_jvm(List<? extends JavaAnnotationArgument> list) {
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof JavaEnumValueAnnotationArgument) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument : (List) arrayList) {
            JavaAnnotationTargetMapper javaAnnotationTargetMapper = INSTANCE;
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            acnw.a(arrayList2, (Iterable) javaAnnotationTargetMapper.mapJavaTargetArgumentByName(entryName != null ? entryName.asString() : null));
        }
        Iterable<KotlinTarget> iterable = (List) arrayList2;
        arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (KotlinTarget name : iterable) {
            arrayList.add(new EnumValue(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.annotationTarget), Name.identifier(name.name())));
        }
        return new ArrayValue((List) arrayList, JavaAnnotationTargetMapper$mapJavaTargetArguments$1.INSTANCE);
    }

    public final ConstantValue<?> mapJavaRetentionArgument$descriptors_jvm(JavaAnnotationArgument javaAnnotationArgument) {
        EnumValue enumValue = null;
        if (!(javaAnnotationArgument instanceof JavaEnumValueAnnotationArgument)) {
            javaAnnotationArgument = null;
        }
        JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = (JavaEnumValueAnnotationArgument) javaAnnotationArgument;
        if (javaEnumValueAnnotationArgument != null) {
            Map map = retentionNameList;
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            KotlinRetention kotlinRetention = (KotlinRetention) map.get(entryName != null ? entryName.asString() : null);
            if (kotlinRetention != null) {
                enumValue = new EnumValue(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.annotationRetention), Name.identifier(kotlinRetention.name()));
            }
        }
        return enumValue;
    }
}
