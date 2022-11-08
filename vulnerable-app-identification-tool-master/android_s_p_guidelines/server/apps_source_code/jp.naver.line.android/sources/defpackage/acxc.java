package defpackage;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker.Default;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator.DoNothing;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver.Empty;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\bH\u0000\" \u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"moduleByClassLoader", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/reflect/jvm/internal/WeakClassLoaderBox;", "Ljava/lang/ref/WeakReference;", "Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "clearModuleByClassLoaderCache", "", "getOrCreateModule", "Ljava/lang/Class;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acxc */
public final class acxc {
    private static final ConcurrentMap<acxo, WeakReference<aczm>> a = new ConcurrentHashMap();

    public static final aczm a(Class<?> cls) {
        acxo acxo;
        Throwable th;
        ClassLoader a = adab.a((Class) cls);
        acxo acxo2 = new acxo(a);
        WeakReference weakReference = (WeakReference) a.get(acxo2);
        if (weakReference != null) {
            aczm aczm = (aczm) weakReference.get();
            if (aczm != null) {
                return aczm;
            }
            a.remove(acxo2, weakReference);
        }
        aczn aczn = aczm.a;
        StorageManager lockBasedStorageManager = new LockBasedStorageManager();
        StorageManager storageManager = lockBasedStorageManager;
        JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(lockBasedStorageManager, false, 2, null);
        StringBuilder stringBuilder = new StringBuilder("<runtime module for ");
        stringBuilder.append(a);
        stringBuilder.append('>');
        acxo acxo3 = acxo2;
        ModuleDescriptorImpl moduleDescriptorImpl = r4;
        ModuleDescriptorImpl moduleDescriptorImpl2 = new ModuleDescriptorImpl(Name.special(stringBuilder.toString()), lockBasedStorageManager, jvmBuiltIns, null, null, null, 56, null);
        aczj aczj = new aczj(a);
        DeserializedDescriptorResolver deserializedDescriptorResolver = r12;
        DeserializedDescriptorResolver deserializedDescriptorResolver2 = new DeserializedDescriptorResolver();
        SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
        aczo aczo = new aczo(a);
        JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
        JavaResolverCache javaResolverCache2 = javaResolverCache;
        ModuleDescriptor moduleDescriptor = moduleDescriptorImpl;
        ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
        NotFoundClasses notFoundClasses = new NotFoundClasses(lockBasedStorageManager, moduleDescriptor);
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = r7;
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver2 = new AnnotationTypeQualifierResolver(lockBasedStorageManager, Jsr305State.DISABLED);
        ModuleDescriptorImpl moduleDescriptorImpl3 = moduleDescriptorImpl;
        JavaClassFinder aczf = new aczf(a);
        KotlinClassFinder kotlinClassFinder = aczj;
        KotlinClassFinder kotlinClassFinder2 = kotlinClassFinder;
        SignaturePropagator signaturePropagator = SignaturePropagator.DO_NOTHING;
        ErrorReporter errorReporter = aczl.a;
        JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator = DoNothing.INSTANCE;
        SamConversionResolver samConversionResolver = Empty.INSTANCE;
        JavaSourceElementFactory javaSourceElementFactory = aczq.a;
        ModuleClassResolver moduleClassResolver = singleModuleClassResolver;
        PackagePartProvider packagePartProvider = aczo;
        SupertypeLoopChecker supertypeLoopChecker = EMPTY.INSTANCE;
        LookupTracker lookupTracker = DO_NOTHING.INSTANCE;
        ReflectionTypes reflectionTypes = r1;
        ReflectionTypes reflectionTypes2 = new ReflectionTypes(moduleDescriptor, notFoundClasses);
        SignatureEnhancement signatureEnhancement = r1;
        SignatureEnhancement signatureEnhancement2 = new SignatureEnhancement(annotationTypeQualifierResolver2, Jsr305State.DISABLED);
        LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = new LazyJavaPackageFragmentProvider(new JavaResolverComponents(storageManager, aczf, kotlinClassFinder2, deserializedDescriptorResolver, signaturePropagator, errorReporter, javaResolverCache2, javaPropertyInitializerEvaluator, samConversionResolver, javaSourceElementFactory, moduleClassResolver, packagePartProvider, supertypeLoopChecker, lookupTracker, moduleDescriptor2, reflectionTypes, annotationTypeQualifierResolver, signatureEnhancement, Default.INSTANCE, JavaResolverSettings.Default.INSTANCE));
        jvmBuiltIns.initialize(moduleDescriptor, true);
        JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(lazyJavaPackageFragmentProvider, javaResolverCache);
        JavaClassDataFinder javaClassDataFinder = new JavaClassDataFinder(kotlinClassFinder, deserializedDescriptorResolver2);
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = new BinaryClassAnnotationAndConstantLoaderImpl(moduleDescriptor, notFoundClasses, lockBasedStorageManager, kotlinClassFinder);
        StorageManager storageManager2 = lockBasedStorageManager;
        NotFoundClasses notFoundClasses2 = notFoundClasses;
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl2 = binaryClassAnnotationAndConstantLoaderImpl;
        aczo aczo2 = aczo;
        LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider2 = lazyJavaPackageFragmentProvider;
        SingleModuleClassResolver singleModuleClassResolver2 = singleModuleClassResolver;
        NotFoundClasses notFoundClasses3 = notFoundClasses2;
        DeserializedDescriptorResolver deserializedDescriptorResolver3 = deserializedDescriptorResolver2;
        aczo aczo3 = aczo2;
        DeserializationComponentsForJava deserializationComponentsForJava = r4;
        DeserializationComponentsForJava deserializationComponentsForJava2 = new DeserializationComponentsForJava(storageManager2, moduleDescriptor, DeserializationConfiguration.Default.INSTANCE, javaClassDataFinder, binaryClassAnnotationAndConstantLoaderImpl2, lazyJavaPackageFragmentProvider2, notFoundClasses3, aczl.a, DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT());
        singleModuleClassResolver2.setResolver(javaDescriptorResolver);
        deserializedDescriptorResolver3.setComponents(deserializationComponentsForJava);
        ModuleDescriptorImpl[] moduleDescriptorImplArr = new ModuleDescriptorImpl[]{moduleDescriptorImpl3, jvmBuiltIns.getBuiltInsModule()};
        ModuleDescriptorImpl moduleDescriptorImpl4 = moduleDescriptorImpl3;
        moduleDescriptorImpl4.setDependencies(moduleDescriptorImplArr);
        moduleDescriptorImpl4.initialize(javaDescriptorResolver.getPackageFragmentProvider());
        aczm aczm2 = new aczm(deserializationComponentsForJava.getComponents(), aczo3, (byte) 0);
        while (true) {
            try {
                acxo = acxo3;
                WeakReference weakReference2 = (WeakReference) a.putIfAbsent(acxo, new WeakReference(aczm2));
                if (weakReference2 == null) {
                    acxo.a();
                    return aczm2;
                }
                aczm aczm3 = (aczm) weakReference2.get();
                if (aczm3 != null) {
                    acxo.a();
                    return aczm3;
                }
                try {
                    a.remove(acxo, weakReference2);
                    acxo3 = acxo;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                acxo = acxo3;
            }
        }
        acxo.a();
        throw th;
    }
}
