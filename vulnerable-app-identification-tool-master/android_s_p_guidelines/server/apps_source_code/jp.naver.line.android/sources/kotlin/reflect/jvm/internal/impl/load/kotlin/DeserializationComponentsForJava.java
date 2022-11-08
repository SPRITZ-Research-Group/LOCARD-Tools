package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acob;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.NoPlatformDependent;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings.Default;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class DeserializationComponentsForJava {
    private final DeserializationComponents components;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeserializationComponentsForJava(StorageManager storageManager, ModuleDescriptor moduleDescriptor, DeserializationConfiguration deserializationConfiguration, JavaClassDataFinder javaClassDataFinder, BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl, LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider, NotFoundClasses notFoundClasses, ErrorReporter errorReporter, LookupTracker lookupTracker, ContractDeserializer contractDeserializer) {
        JvmBuiltInsSettings settings;
        JvmBuiltInsSettings settings2;
        KotlinBuiltIns builtIns = moduleDescriptor.getBuiltIns();
        if (!(builtIns instanceof JvmBuiltIns)) {
            builtIns = null;
        }
        JvmBuiltIns jvmBuiltIns = (JvmBuiltIns) builtIns;
        ClassDataFinder classDataFinder = javaClassDataFinder;
        AnnotationAndConstantLoader annotationAndConstantLoader = binaryClassAnnotationAndConstantLoaderImpl;
        PackageFragmentProvider packageFragmentProvider = lazyJavaPackageFragmentProvider;
        LocalClassifierTypeSettings localClassifierTypeSettings = Default.INSTANCE;
        FlexibleTypeDeserializer flexibleTypeDeserializer = JavaFlexibleTypeDeserializer.INSTANCE;
        Iterable iterable = acob.a;
        if (jvmBuiltIns != null) {
            settings = jvmBuiltIns.getSettings();
        }
        settings = None.INSTANCE;
        AdditionalClassPartsProvider additionalClassPartsProvider = settings;
        if (jvmBuiltIns != null) {
            settings2 = jvmBuiltIns.getSettings();
        }
        settings2 = NoPlatformDependent.INSTANCE;
        DeserializationComponents deserializationComponents = r1;
        DeserializationComponents deserializationComponents2 = new DeserializationComponents(storageManager, moduleDescriptor, deserializationConfiguration, classDataFinder, annotationAndConstantLoader, packageFragmentProvider, localClassifierTypeSettings, errorReporter, lookupTracker, flexibleTypeDeserializer, iterable, notFoundClasses, contractDeserializer, additionalClassPartsProvider, settings2, JvmProtoBufUtil.INSTANCE.getEXTENSION_REGISTRY());
        this.components = deserializationComponents;
    }

    public final DeserializationComponents getComponents() {
        return this.components;
    }
}
