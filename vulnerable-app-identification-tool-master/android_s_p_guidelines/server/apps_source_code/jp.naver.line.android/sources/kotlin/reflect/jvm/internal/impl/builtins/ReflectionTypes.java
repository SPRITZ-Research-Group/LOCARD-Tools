package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acnz;
import defpackage.acru;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import defpackage.addb;
import java.util.Collections;
import kotlin.e;
import kotlin.j;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;

public final class ReflectionTypes {
    static final /* synthetic */ acuo[] $$delegatedProperties = new acuo[]{acso.a(new acsi(acso.a(ReflectionTypes.class), "kotlinReflectScope", "getKotlinReflectScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kClass", "getKClass()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kProperty0", "getKProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kProperty1", "getKProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kProperty2", "getKProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kMutableProperty0", "getKMutableProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kMutableProperty1", "getKMutableProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), acso.a(new acsi(acso.a(ReflectionTypes.class), "kMutableProperty2", "getKMutableProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;"))};
    public static final Companion Companion = new Companion();
    private final ClassLookup kClass$delegate = new ClassLookup(1);
    private final ClassLookup kMutableProperty0$delegate = new ClassLookup(1);
    private final ClassLookup kMutableProperty1$delegate = new ClassLookup(2);
    private final ClassLookup kMutableProperty2$delegate = new ClassLookup(3);
    private final ClassLookup kProperty0$delegate = new ClassLookup(1);
    private final ClassLookup kProperty1$delegate = new ClassLookup(2);
    private final ClassLookup kProperty2$delegate = new ClassLookup(3);
    private final e kotlinReflectScope$delegate;
    private final NotFoundClasses notFoundClasses;

    final class ClassLookup {
        private final int numberOfTypeParameters;

        public ClassLookup(int i) {
            this.numberOfTypeParameters = i;
        }

        public final ClassDescriptor getValue(ReflectionTypes reflectionTypes, acuo<?> acuo) {
            return reflectionTypes.find(addb.h(acuo.getName()), this.numberOfTypeParameters);
        }
    }

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final KotlinType createKPropertyStarType(ModuleDescriptor moduleDescriptor) {
            ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, KotlinBuiltIns.FQ_NAMES.kProperty);
            if (findClassAcrossModuleDependencies == null) {
                return null;
            }
            return KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), findClassAcrossModuleDependencies, Collections.singletonList(new StarProjectionImpl((TypeParameterDescriptor) acnz.i(findClassAcrossModuleDependencies.getTypeConstructor().getParameters()))));
        }
    }

    private final MemberScope getKotlinReflectScope() {
        return (MemberScope) this.kotlinReflectScope$delegate.d();
    }

    public final ClassDescriptor getKClass() {
        return this.kClass$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public ReflectionTypes(ModuleDescriptor moduleDescriptor, NotFoundClasses notFoundClasses) {
        this.notFoundClasses = notFoundClasses;
        this.kotlinReflectScope$delegate = h.a(j.PUBLICATION, new ReflectionTypes$kotlinReflectScope$2(moduleDescriptor));
    }

    private final ClassDescriptor find(String str, int i) {
        Name identifier = Name.identifier(str);
        ClassifierDescriptor contributedClassifier = getKotlinReflectScope().getContributedClassifier(identifier, NoLookupLocation.FROM_REFLECTION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        return classDescriptor == null ? this.notFoundClasses.getClass(new ClassId(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), identifier), Collections.singletonList(Integer.valueOf(i))) : classDescriptor;
    }
}
