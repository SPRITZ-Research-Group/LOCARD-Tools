package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import com.google.obf.ly;
import defpackage.acnr;
import defpackage.acod;
import defpackage.acry;
import defpackage.adda;
import defpackage.addc;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

public final class JavaToKotlinClassMap {
    private static final ClassId FUNCTION_N_CLASS_ID;
    private static final FqName FUNCTION_N_FQ_NAME;
    public static final JavaToKotlinClassMap INSTANCE;
    private static final ClassId K_FUNCTION_CLASS_ID = ClassId.topLevel(new FqName("kotlin.reflect.KFunction"));
    private static final String NUMBERED_FUNCTION_PREFIX;
    private static final String NUMBERED_K_FUNCTION_PREFIX;
    private static final String NUMBERED_K_SUSPEND_FUNCTION_PREFIX;
    private static final String NUMBERED_SUSPEND_FUNCTION_PREFIX;
    private static final HashMap<FqNameUnsafe, ClassId> javaToKotlin = new HashMap();
    private static final HashMap<FqNameUnsafe, ClassId> kotlinToJava = new HashMap();
    private static final List<PlatformMutabilityMapping> mutabilityMappings;
    private static final HashMap<FqNameUnsafe, FqName> mutableToReadOnly = new HashMap();
    private static final HashMap<FqNameUnsafe, FqName> readOnlyToMutable = new HashMap();

    public final class PlatformMutabilityMapping {
        private final ClassId javaClass;
        private final ClassId kotlinMutable;
        private final ClassId kotlinReadOnly;

        public final ClassId component1() {
            return this.javaClass;
        }

        public final ClassId component2() {
            return this.kotlinReadOnly;
        }

        public final ClassId component3() {
            return this.kotlinMutable;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof PlatformMutabilityMapping) {
                    PlatformMutabilityMapping platformMutabilityMapping = (PlatformMutabilityMapping) obj;
                    if (acry.a(this.javaClass, platformMutabilityMapping.javaClass)) {
                        if (acry.a(this.kotlinReadOnly, platformMutabilityMapping.kotlinReadOnly)) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            ClassId classId = this.javaClass;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            ClassId classId2 = this.kotlinReadOnly;
            hashCode = (hashCode + (classId2 != null ? classId2.hashCode() : 0)) * 31;
            classId2 = this.kotlinMutable;
            if (classId2 != null) {
                i = classId2.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("PlatformMutabilityMapping(javaClass=");
            stringBuilder.append(this.javaClass);
            stringBuilder.append(", kotlinReadOnly=");
            stringBuilder.append(this.kotlinReadOnly);
            stringBuilder.append(", kotlinMutable=");
            stringBuilder.append(this.kotlinMutable);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public PlatformMutabilityMapping(ClassId classId, ClassId classId2, ClassId classId3) {
            this.javaClass = classId;
            this.kotlinReadOnly = classId2;
            this.kotlinMutable = classId3;
        }

        public final ClassId getJavaClass() {
            return this.javaClass;
        }
    }

    static {
        StringBuilder stringBuilder;
        JavaToKotlinClassMap javaToKotlinClassMap = new JavaToKotlinClassMap();
        INSTANCE = javaToKotlinClassMap;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Kind.Function.getPackageFqName().toString());
        stringBuilder2.append(ly.a);
        stringBuilder2.append(Kind.Function.getClassNamePrefix());
        NUMBERED_FUNCTION_PREFIX = stringBuilder2.toString();
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Kind.KFunction.getPackageFqName().toString());
        stringBuilder2.append(ly.a);
        stringBuilder2.append(Kind.KFunction.getClassNamePrefix());
        NUMBERED_K_FUNCTION_PREFIX = stringBuilder2.toString();
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Kind.SuspendFunction.getPackageFqName().toString());
        stringBuilder2.append(ly.a);
        stringBuilder2.append(Kind.SuspendFunction.getClassNamePrefix());
        NUMBERED_SUSPEND_FUNCTION_PREFIX = stringBuilder2.toString();
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Kind.KSuspendFunction.getPackageFqName().toString());
        stringBuilder2.append(ly.a);
        stringBuilder2.append(Kind.KSuspendFunction.getClassNamePrefix());
        NUMBERED_K_SUSPEND_FUNCTION_PREFIX = stringBuilder2.toString();
        ClassId topLevel = ClassId.topLevel(new FqName("kotlin.jvm.functions.FunctionN"));
        FUNCTION_N_CLASS_ID = topLevel;
        FUNCTION_N_FQ_NAME = topLevel.asSingleFqName();
        PlatformMutabilityMapping[] platformMutabilityMappingArr = new PlatformMutabilityMapping[8];
        ClassId topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.iterable);
        int i = 0;
        platformMutabilityMappingArr[0] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Iterable.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableIterable, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.iterator);
        platformMutabilityMappingArr[1] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Iterator.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableIterator, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.collection);
        platformMutabilityMappingArr[2] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Collection.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableCollection, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.list);
        platformMutabilityMappingArr[3] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(List.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableList, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.set);
        platformMutabilityMappingArr[4] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Set.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableSet, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.listIterator);
        platformMutabilityMappingArr[5] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(ListIterator.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableListIterator, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.map);
        platformMutabilityMappingArr[6] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Map.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableMap, topLevel2.getPackageFqName()), false));
        topLevel2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.map).createNestedClassId(KotlinBuiltIns.FQ_NAMES.mapEntry.shortName());
        platformMutabilityMappingArr[7] = new PlatformMutabilityMapping(javaToKotlinClassMap.classId(Entry.class), topLevel2, new ClassId(topLevel2.getPackageFqName(), FqNamesUtilKt.tail(KotlinBuiltIns.FQ_NAMES.mutableMapEntry, topLevel2.getPackageFqName()), false));
        mutabilityMappings = acnr.b((Object[]) platformMutabilityMappingArr);
        javaToKotlinClassMap.addTopLevel(Object.class, KotlinBuiltIns.FQ_NAMES.any);
        javaToKotlinClassMap.addTopLevel(String.class, KotlinBuiltIns.FQ_NAMES.string);
        javaToKotlinClassMap.addTopLevel(CharSequence.class, KotlinBuiltIns.FQ_NAMES.charSequence);
        javaToKotlinClassMap.addTopLevel(Throwable.class, KotlinBuiltIns.FQ_NAMES.throwable);
        javaToKotlinClassMap.addTopLevel(Cloneable.class, KotlinBuiltIns.FQ_NAMES.cloneable);
        javaToKotlinClassMap.addTopLevel(Number.class, KotlinBuiltIns.FQ_NAMES.number);
        javaToKotlinClassMap.addTopLevel(Comparable.class, KotlinBuiltIns.FQ_NAMES.comparable);
        javaToKotlinClassMap.addTopLevel(Enum.class, KotlinBuiltIns.FQ_NAMES._enum);
        javaToKotlinClassMap.addTopLevel(Annotation.class, KotlinBuiltIns.FQ_NAMES.annotation);
        for (PlatformMutabilityMapping addMapping : mutabilityMappings) {
            javaToKotlinClassMap.addMapping(addMapping);
        }
        for (JvmPrimitiveType jvmPrimitiveType : JvmPrimitiveType.values()) {
            javaToKotlinClassMap.add(ClassId.topLevel(jvmPrimitiveType.getWrapperFqName()), ClassId.topLevel(KotlinBuiltIns.getPrimitiveFqName(jvmPrimitiveType.getPrimitiveType())));
        }
        for (ClassId topLevel22 : CompanionObjectMapping.INSTANCE.allClassesWithIntrinsicCompanions()) {
            StringBuilder stringBuilder3 = new StringBuilder("kotlin.jvm.internal.");
            stringBuilder3.append(topLevel22.getShortClassName().asString());
            stringBuilder3.append("CompanionObject");
            javaToKotlinClassMap.add(ClassId.topLevel(new FqName(stringBuilder3.toString())), topLevel22.createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT));
        }
        for (int i2 = 0; i2 < 23; i2++) {
            javaToKotlinClassMap.add(ClassId.topLevel(new FqName("kotlin.jvm.functions.Function".concat(String.valueOf(i2)))), KotlinBuiltIns.getFunctionClassId(i2));
            stringBuilder = new StringBuilder();
            stringBuilder.append(NUMBERED_K_FUNCTION_PREFIX);
            stringBuilder.append(i2);
            javaToKotlinClassMap.addKotlinToJava(new FqName(stringBuilder.toString()), K_FUNCTION_CLASS_ID);
        }
        while (i < 22) {
            Kind kind = Kind.KSuspendFunction;
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(kind.getPackageFqName().toString());
            stringBuilder4.append(ly.a);
            stringBuilder4.append(kind.getClassNamePrefix());
            String stringBuilder5 = stringBuilder4.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder5);
            stringBuilder.append(i);
            javaToKotlinClassMap.addKotlinToJava(new FqName(stringBuilder.toString()), K_FUNCTION_CLASS_ID);
            i++;
        }
        javaToKotlinClassMap.addKotlinToJava(KotlinBuiltIns.FQ_NAMES.nothing.toSafe(), javaToKotlinClassMap.classId(Void.class));
    }

    private JavaToKotlinClassMap() {
    }

    public final FqName getFUNCTION_N_FQ_NAME() {
        return FUNCTION_N_FQ_NAME;
    }

    public final List<PlatformMutabilityMapping> getMutabilityMappings() {
        return mutabilityMappings;
    }

    public final ClassId mapJavaToKotlin(FqName fqName) {
        return (ClassId) javaToKotlin.get(fqName.toUnsafe());
    }

    public static /* synthetic */ ClassDescriptor mapJavaToKotlin$default(JavaToKotlinClassMap javaToKotlinClassMap, FqName fqName, KotlinBuiltIns kotlinBuiltIns, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return javaToKotlinClassMap.mapJavaToKotlin(fqName, kotlinBuiltIns, num);
    }

    public final ClassDescriptor mapJavaToKotlin(FqName fqName, KotlinBuiltIns kotlinBuiltIns, Integer num) {
        ClassId mapJavaToKotlin;
        if (num == null || !acry.a((Object) fqName, FUNCTION_N_FQ_NAME)) {
            mapJavaToKotlin = mapJavaToKotlin(fqName);
        } else {
            mapJavaToKotlin = KotlinBuiltIns.getFunctionClassId(num.intValue());
        }
        return mapJavaToKotlin != null ? kotlinBuiltIns.getBuiltInClassByFqName(mapJavaToKotlin.asSingleFqName()) : null;
    }

    public final ClassId mapKotlinToJava(FqNameUnsafe fqNameUnsafe) {
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_FUNCTION_PREFIX)) {
            return FUNCTION_N_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_SUSPEND_FUNCTION_PREFIX)) {
            return FUNCTION_N_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_K_FUNCTION_PREFIX)) {
            return K_FUNCTION_CLASS_ID;
        }
        if (isKotlinFunctionWithBigArity(fqNameUnsafe, NUMBERED_K_SUSPEND_FUNCTION_PREFIX)) {
            return K_FUNCTION_CLASS_ID;
        }
        return (ClassId) kotlinToJava.get(fqNameUnsafe);
    }

    private final boolean isKotlinFunctionWithBigArity(FqNameUnsafe fqNameUnsafe, String str) {
        String b = addc.b(fqNameUnsafe.asString(), str, "");
        CharSequence charSequence = b;
        if ((charSequence.length() > 0 ? 1 : null) == null || addc.e(charSequence)) {
            return false;
        }
        Integer f = adda.f(b);
        return f != null && f.intValue() >= 23;
    }

    private final void addMapping(PlatformMutabilityMapping platformMutabilityMapping) {
        ClassId component1 = platformMutabilityMapping.component1();
        ClassId component2 = platformMutabilityMapping.component2();
        ClassId component3 = platformMutabilityMapping.component3();
        add(component1, component2);
        addKotlinToJava(component3.asSingleFqName(), component1);
        FqName asSingleFqName = component2.asSingleFqName();
        FqName asSingleFqName2 = component3.asSingleFqName();
        mutableToReadOnly.put(component3.asSingleFqName().toUnsafe(), asSingleFqName);
        readOnlyToMutable.put(asSingleFqName.toUnsafe(), asSingleFqName2);
    }

    private final void add(ClassId classId, ClassId classId2) {
        addJavaToKotlin(classId, classId2);
        addKotlinToJava(classId2.asSingleFqName(), classId);
    }

    private final void addTopLevel(Class<?> cls, FqNameUnsafe fqNameUnsafe) {
        addTopLevel((Class) cls, fqNameUnsafe.toSafe());
    }

    private final void addTopLevel(Class<?> cls, FqName fqName) {
        add(classId(cls), ClassId.topLevel(fqName));
    }

    private final void addJavaToKotlin(ClassId classId, ClassId classId2) {
        javaToKotlin.put(classId.asSingleFqName().toUnsafe(), classId2);
    }

    private final void addKotlinToJava(FqName fqName, ClassId classId) {
        kotlinToJava.put(fqName.toUnsafe(), classId);
    }

    public final Collection<ClassDescriptor> mapPlatformClass(FqName fqName, KotlinBuiltIns kotlinBuiltIns) {
        ClassDescriptor mapJavaToKotlin$default = mapJavaToKotlin$default(this, fqName, kotlinBuiltIns, null, 4, null);
        if (mapJavaToKotlin$default == null) {
            return acod.a;
        }
        if (((FqName) readOnlyToMutable.get(DescriptorUtilsKt.getFqNameUnsafe(mapJavaToKotlin$default))) == null) {
            return Collections.singleton(mapJavaToKotlin$default);
        }
        return Arrays.asList(new ClassDescriptor[]{mapJavaToKotlin$default, kotlinBuiltIns.getBuiltInClassByFqName((FqName) readOnlyToMutable.get(DescriptorUtilsKt.getFqNameUnsafe(mapJavaToKotlin$default)))});
    }

    public final boolean isMutable(ClassDescriptor classDescriptor) {
        return mutableToReadOnly.containsKey(DescriptorUtils.getFqName(classDescriptor));
    }

    public final boolean isMutable(KotlinType kotlinType) {
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(kotlinType);
        return classDescriptor != null && isMutable(classDescriptor);
    }

    public final boolean isReadOnly(ClassDescriptor classDescriptor) {
        return readOnlyToMutable.containsKey(DescriptorUtils.getFqName(classDescriptor));
    }

    public final boolean isReadOnly(KotlinType kotlinType) {
        ClassDescriptor classDescriptor = TypeUtils.getClassDescriptor(kotlinType);
        return classDescriptor != null && isReadOnly(classDescriptor);
    }

    public final ClassDescriptor convertMutableToReadOnly(ClassDescriptor classDescriptor) {
        return convertToOppositeMutability(classDescriptor, mutableToReadOnly, "mutable");
    }

    public final ClassDescriptor convertReadOnlyToMutable(ClassDescriptor classDescriptor) {
        return convertToOppositeMutability(classDescriptor, readOnlyToMutable, "read-only");
    }

    private final ClassId classId(Class<?> cls) {
        Object obj = (cls.isPrimitive() || cls.isArray()) ? null : 1;
        if (aa.a && obj == null) {
            throw new AssertionError("Invalid class: ".concat(String.valueOf(cls)));
        }
        Class declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            return ClassId.topLevel(new FqName(cls.getCanonicalName()));
        }
        return classId(declaringClass).createNestedClassId(Name.identifier(cls.getSimpleName()));
    }

    private final ClassDescriptor convertToOppositeMutability(ClassDescriptor classDescriptor, Map<FqNameUnsafe, FqName> map, String str) {
        DeclarationDescriptor declarationDescriptor = classDescriptor;
        FqName fqName = (FqName) map.get(DescriptorUtils.getFqName(declarationDescriptor));
        if (fqName != null) {
            return DescriptorUtilsKt.getBuiltIns(declarationDescriptor).getBuiltInClassByFqName(fqName);
        }
        StringBuilder stringBuilder = new StringBuilder("Given class ");
        stringBuilder.append(classDescriptor);
        stringBuilder.append(" is not a ");
        stringBuilder.append(str);
        stringBuilder.append(" collection");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
