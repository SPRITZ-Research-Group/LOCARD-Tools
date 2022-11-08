package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acnz;
import defpackage.acot;
import defpackage.acqq;
import defpackage.acqr;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.NoPlatformDependent;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public abstract class KotlinBuiltIns {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final FqName ANNOTATION_PACKAGE_FQ_NAME;
    public static final Name BUILTINS_MODULE_NAME = Name.special("<built-ins module>");
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES = acot.a((Object[]) new FqName[]{BUILT_INS_PACKAGE_FQ_NAME, COLLECTIONS_PACKAGE_FQ_NAME, RANGES_PACKAGE_FQ_NAME, ANNOTATION_PACKAGE_FQ_NAME, ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("internal")), DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE});
    public static final Name BUILT_INS_PACKAGE_NAME;
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("collections"));
    public static final FqNames FQ_NAMES = new FqNames();
    public static final FqName RANGES_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("ranges"));
    public static final FqName TEXT_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("text"));
    private final MemoizedFunctionToNotNull<Name, ClassDescriptor> builtInClassesByName;
    private ModuleDescriptorImpl builtInsModule;
    private final NotNullLazyValue<PackageFragments> packageFragments;
    private final NotNullLazyValue<Primitives> primitives;
    private final StorageManager storageManager;

    public class FqNames {
        public final FqNameUnsafe _boolean = fqNameUnsafe("Boolean");
        public final FqNameUnsafe _byte = fqNameUnsafe("Byte");
        public final FqNameUnsafe _char = fqNameUnsafe("Char");
        public final FqNameUnsafe _double = fqNameUnsafe("Double");
        public final FqNameUnsafe _enum = fqNameUnsafe("Enum");
        public final FqNameUnsafe _float = fqNameUnsafe("Float");
        public final FqNameUnsafe _int = fqNameUnsafe("Int");
        public final FqNameUnsafe _long = fqNameUnsafe("Long");
        public final FqNameUnsafe _short = fqNameUnsafe("Short");
        public final FqName annotation = fqName("Annotation");
        public final FqName annotationRetention = annotationName("AnnotationRetention");
        public final FqName annotationTarget = annotationName("AnnotationTarget");
        public final FqNameUnsafe any = fqNameUnsafe("Any");
        public final FqNameUnsafe array = fqNameUnsafe("Array");
        public final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
        public final FqNameUnsafe charRange = rangesFqName("CharRange");
        public final FqNameUnsafe charSequence = fqNameUnsafe("CharSequence");
        public final FqNameUnsafe cloneable = fqNameUnsafe("Cloneable");
        public final FqName collection = collectionsFqName("Collection");
        public final FqName comparable = fqName("Comparable");
        public final FqName deprecated = fqName("Deprecated");
        public final FqName deprecationLevel = fqName("DeprecationLevel");
        public final FqName extensionFunctionType = fqName("ExtensionFunctionType");
        public final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
        public final FqNameUnsafe functionSupertype = fqNameUnsafe("Function");
        public final FqNameUnsafe intRange = rangesFqName("IntRange");
        public final FqName iterable = collectionsFqName("Iterable");
        public final FqName iterator = collectionsFqName("Iterator");
        public final FqNameUnsafe kCallable = reflect("KCallable");
        public final FqNameUnsafe kClass = reflect("KClass");
        public final FqNameUnsafe kMutableProperty0 = reflect("KMutableProperty0");
        public final FqNameUnsafe kMutableProperty1 = reflect("KMutableProperty1");
        public final FqNameUnsafe kMutableProperty2 = reflect("KMutableProperty2");
        public final ClassId kProperty = ClassId.topLevel(reflect("KProperty").toSafe());
        public final FqNameUnsafe kProperty0 = reflect("KProperty0");
        public final FqNameUnsafe kProperty1 = reflect("KProperty1");
        public final FqNameUnsafe kProperty2 = reflect("KProperty2");
        public final FqName list = collectionsFqName("List");
        public final FqName listIterator = collectionsFqName("ListIterator");
        public final FqNameUnsafe longRange = rangesFqName("LongRange");
        public final FqName map = collectionsFqName("Map");
        public final FqName mapEntry = this.map.child(Name.identifier("Entry"));
        public final FqName mustBeDocumented = annotationName("MustBeDocumented");
        public final FqName mutableCollection = collectionsFqName("MutableCollection");
        public final FqName mutableIterable = collectionsFqName("MutableIterable");
        public final FqName mutableIterator = collectionsFqName("MutableIterator");
        public final FqName mutableList = collectionsFqName("MutableList");
        public final FqName mutableListIterator = collectionsFqName("MutableListIterator");
        public final FqName mutableMap = collectionsFqName("MutableMap");
        public final FqName mutableMapEntry = this.mutableMap.child(Name.identifier("MutableEntry"));
        public final FqName mutableSet = collectionsFqName("MutableSet");
        public final FqNameUnsafe nothing = fqNameUnsafe("Nothing");
        public final FqNameUnsafe number = fqNameUnsafe("Number");
        public final FqName parameterName = fqName("ParameterName");
        public final Set<Name> primitiveArrayTypeShortNames = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
        public final Set<Name> primitiveTypeShortNames = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
        public final FqName publishedApi = fqName("PublishedApi");
        public final FqName repeatable = annotationName("Repeatable");
        public final FqName replaceWith = fqName("ReplaceWith");
        public final FqName retention = annotationName("Retention");
        public final FqName set = collectionsFqName("Set");
        public final FqNameUnsafe string = fqNameUnsafe("String");
        public final FqName suppress = fqName("Suppress");
        public final FqName target = annotationName("Target");
        public final FqName throwable = fqName("Throwable");
        public final ClassId uByte = ClassId.topLevel(this.uByteFqName);
        public final FqName uByteFqName = fqName("UByte");
        public final ClassId uInt = ClassId.topLevel(this.uIntFqName);
        public final FqName uIntFqName = fqName("UInt");
        public final ClassId uLong = ClassId.topLevel(this.uLongFqName);
        public final FqName uLongFqName = fqName("ULong");
        public final ClassId uShort = ClassId.topLevel(this.uShortFqName);
        public final FqName uShortFqName = fqName("UShort");
        public final FqNameUnsafe unit = fqNameUnsafe("Unit");
        public final FqName unsafeVariance = fqName("UnsafeVariance");

        public FqNames() {
            for (PrimitiveType primitiveType : PrimitiveType.values()) {
                this.primitiveTypeShortNames.add(primitiveType.getTypeName());
                this.primitiveArrayTypeShortNames.add(primitiveType.getArrayTypeName());
                this.fqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getTypeName().asString()), primitiveType);
                this.arrayClassFqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getArrayTypeName().asString()), primitiveType);
            }
        }

        private static FqNameUnsafe fqNameUnsafe(String str) {
            return fqName(str).toUnsafe();
        }

        private static FqName fqName(String str) {
            return KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        private static FqName collectionsFqName(String str) {
            return KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        private static FqNameUnsafe rangesFqName(String str) {
            return KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(str)).toUnsafe();
        }

        private static FqNameUnsafe reflect(String str) {
            return ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME().child(Name.identifier(str)).toUnsafe();
        }

        private static FqName annotationName(String str) {
            return KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }
    }

    class PackageFragments {
        public final Set<PackageFragmentDescriptor> allImportedByDefaultBuiltInsPackageFragments;
        public final PackageFragmentDescriptor annotationPackageFragment;
        public final PackageFragmentDescriptor builtInsPackageFragment;
        public final PackageFragmentDescriptor collectionsPackageFragment;

        /* synthetic */ PackageFragments(PackageFragmentDescriptor packageFragmentDescriptor, PackageFragmentDescriptor packageFragmentDescriptor2, PackageFragmentDescriptor packageFragmentDescriptor3, Set set, AnonymousClass1 anonymousClass1) {
            this(packageFragmentDescriptor, packageFragmentDescriptor2, packageFragmentDescriptor3, set);
        }

        private PackageFragments(PackageFragmentDescriptor packageFragmentDescriptor, PackageFragmentDescriptor packageFragmentDescriptor2, PackageFragmentDescriptor packageFragmentDescriptor3, Set<PackageFragmentDescriptor> set) {
            this.builtInsPackageFragment = packageFragmentDescriptor;
            this.collectionsPackageFragment = packageFragmentDescriptor2;
            this.annotationPackageFragment = packageFragmentDescriptor3;
            this.allImportedByDefaultBuiltInsPackageFragments = set;
        }
    }

    class Primitives {
        public final Map<SimpleType, SimpleType> kotlinArrayTypeToPrimitiveKotlinType;
        public final Map<KotlinType, SimpleType> primitiveKotlinTypeToKotlinArrayType;
        public final Map<PrimitiveType, SimpleType> primitiveTypeToArrayKotlinType;

        /* synthetic */ Primitives(Map map, Map map2, Map map3, AnonymousClass1 anonymousClass1) {
            this(map, map2, map3);
        }

        private Primitives(Map<PrimitiveType, SimpleType> map, Map<KotlinType, SimpleType> map2, Map<SimpleType, SimpleType> map3) {
            this.primitiveTypeToArrayKotlinType = map;
            this.primitiveKotlinTypeToKotlinArrayType = map2;
            this.kotlinArrayTypeToPrimitiveKotlinType = map3;
        }
    }

    /* renamed from: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns$5 */
    class AnonymousClass5 extends PackageFragmentDescriptorImpl {
        final /* synthetic */ FqName val$packageFqName;
        final /* synthetic */ List val$packageFragments;

        AnonymousClass5(ModuleDescriptor moduleDescriptor, FqName fqName, FqName fqName2, List list) {
            this.val$packageFqName = fqName2;
            this.val$packageFragments = list;
            super(moduleDescriptor, fqName);
        }

        public MemberScope getMemberScope() {
            StringBuilder stringBuilder = new StringBuilder("built-in package ");
            stringBuilder.append(this.val$packageFqName);
            return new ChainedMemberScope(stringBuilder.toString(), acnz.c(this.val$packageFragments, new acqr<PackageFragmentDescriptor, MemberScope>() {
                public MemberScope invoke(PackageFragmentDescriptor packageFragmentDescriptor) {
                    return packageFragmentDescriptor.getMemberScope();
                }
            }));
        }
    }

    static {
        Name identifier = Name.identifier("kotlin");
        BUILT_INS_PACKAGE_NAME = identifier;
        FqName topLevel = FqName.topLevel(identifier);
        BUILT_INS_PACKAGE_FQ_NAME = topLevel;
        ANNOTATION_PACKAGE_FQ_NAME = topLevel.child(Name.identifier("annotation"));
    }

    protected KotlinBuiltIns(StorageManager storageManager) {
        this.storageManager = storageManager;
        this.packageFragments = storageManager.createLazyValue(new acqq<PackageFragments>() {
            public PackageFragments invoke() {
                PackageFragmentProvider packageFragmentProvider = KotlinBuiltIns.this.builtInsModule.getPackageFragmentProvider();
                Map linkedHashMap = new LinkedHashMap();
                PackageFragmentDescriptor access$100 = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME);
                KotlinBuiltIns.this.createPackage(packageFragmentProvider, null, DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE);
                PackageFragmentDescriptor access$1002 = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME);
                KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME);
                return new PackageFragments(access$100, access$1002, KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME), new LinkedHashSet(linkedHashMap.values()), null);
            }
        });
        this.primitives = storageManager.createLazyValue(new acqq<Primitives>() {
            public Primitives invoke() {
                Map enumMap = new EnumMap(PrimitiveType.class);
                Map hashMap = new HashMap();
                Map hashMap2 = new HashMap();
                for (PrimitiveType primitiveType : PrimitiveType.values()) {
                    SimpleType access$400 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getTypeName().asString());
                    SimpleType access$4002 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getArrayTypeName().asString());
                    enumMap.put(primitiveType, access$4002);
                    hashMap.put(access$400, access$4002);
                    hashMap2.put(access$4002, access$400);
                }
                return new Primitives(enumMap, hashMap, hashMap2, null);
            }
        });
        this.builtInClassesByName = storageManager.createMemoizedFunction(new acqr<Name, ClassDescriptor>() {
            public ClassDescriptor invoke(Name name) {
                return KotlinBuiltIns.getBuiltInClassByName(name, KotlinBuiltIns.this.getBuiltInsPackageFragment());
            }
        });
    }

    protected void createBuiltInsModule() {
        this.builtInsModule = new ModuleDescriptorImpl(BUILTINS_MODULE_NAME, this.storageManager, this, null);
        this.builtInsModule.initialize(BuiltInsLoader.Companion.getInstance().createPackageFragmentProvider(this.storageManager, this.builtInsModule, getClassDescriptorFactories(), getPlatformDependentDeclarationFilter(), getAdditionalClassPartsProvider()));
        this.builtInsModule.setDependencies(this.builtInsModule);
    }

    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return None.INSTANCE;
    }

    protected PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return NoPlatformDependent.INSTANCE;
    }

    protected Iterable<ClassDescriptorFactory> getClassDescriptorFactories() {
        return Collections.singletonList(new BuiltInFictitiousFunctionClassFactory(this.storageManager, this.builtInsModule));
    }

    private kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor createPackage(kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider r7, java.util.Map<kotlin.reflect.jvm.internal.impl.name.FqName, kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor> r8, kotlin.reflect.jvm.internal.impl.name.FqName r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r7_7 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) in PHI: PHI: (r7_8 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) = (r7_2 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor), (r7_6 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor), (r7_7 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) binds: {(r7_2 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor)=B:2:0x000a, (r7_6 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor)=B:5:0x0019, (r7_7 kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor)=B:6:0x0024}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r6 = this;
        r5 = r7.getPackageFragments(r9);
        r7 = r5.isEmpty();
        if (r7 == 0) goto L_0x0012;
    L_0x000a:
        r7 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
        r0 = r6.builtInsModule;
        r7.<init>(r0, r9);
        goto L_0x002f;
    L_0x0012:
        r7 = r5.size();
        r0 = 1;
        if (r7 != r0) goto L_0x0024;
    L_0x0019:
        r7 = r5.iterator();
        r7 = r7.next();
        r7 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r7;
        goto L_0x002f;
    L_0x0024:
        r7 = new kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns$5;
        r2 = r6.builtInsModule;
        r0 = r7;
        r1 = r6;
        r3 = r9;
        r4 = r9;
        r0.<init>(r2, r3, r4, r5);
    L_0x002f:
        if (r8 == 0) goto L_0x0034;
    L_0x0031:
        r8.put(r9, r7);
    L_0x0034:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.createPackage(kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider, java.util.Map, kotlin.reflect.jvm.internal.impl.name.FqName):kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor");
    }

    protected StorageManager getStorageManager() {
        return this.storageManager;
    }

    public ModuleDescriptorImpl getBuiltInsModule() {
        return this.builtInsModule;
    }

    public PackageFragmentDescriptor getBuiltInsPackageFragment() {
        return ((PackageFragments) this.packageFragments.invoke()).builtInsPackageFragment;
    }

    public static boolean isBuiltIn(DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getParentOfType(declarationDescriptor, BuiltInsPackageFragment.class, false) != null;
    }

    public static boolean isUnderKotlinPackage(DeclarationDescriptor declarationDescriptor) {
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof PackageFragmentDescriptor) {
                return ((PackageFragmentDescriptor) declarationDescriptor).getFqName().startsWith(BUILT_INS_PACKAGE_NAME);
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    public ClassDescriptor getBuiltInClassByName(Name name) {
        return (ClassDescriptor) this.builtInClassesByName.invoke(name);
    }

    private static ClassDescriptor getBuiltInClassByName(Name name, PackageFragmentDescriptor packageFragmentDescriptor) {
        ClassDescriptor builtInClassByNameNullable = getBuiltInClassByNameNullable(name, packageFragmentDescriptor);
        if (builtInClassByNameNullable != null) {
            return builtInClassByNameNullable;
        }
        StringBuilder stringBuilder = new StringBuilder("Built-in class ");
        stringBuilder.append(packageFragmentDescriptor.getFqName().child(name).asString());
        stringBuilder.append(" is not found");
        throw new AssertionError(stringBuilder.toString());
    }

    public ClassDescriptor getBuiltInClassByFqNameNullable(FqName fqName) {
        return DescriptorUtilKt.resolveClassByFqName(this.builtInsModule, fqName, NoLookupLocation.FROM_BUILTINS);
    }

    public ClassDescriptor getBuiltInClassByFqName(FqName fqName) {
        return getBuiltInClassByFqNameNullable(fqName);
    }

    private static ClassDescriptor getBuiltInClassByNameNullable(Name name, PackageFragmentDescriptor packageFragmentDescriptor) {
        return (ClassDescriptor) packageFragmentDescriptor.getMemberScope().getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
    }

    private ClassDescriptor getBuiltInClassByName(String str) {
        return getBuiltInClassByName(Name.identifier(str));
    }

    private static ClassDescriptor getBuiltInClassByName(String str, PackageFragmentDescriptor packageFragmentDescriptor) {
        return getBuiltInClassByName(Name.identifier(str), packageFragmentDescriptor);
    }

    public ClassDescriptor getAny() {
        return getBuiltInClassByName("Any");
    }

    public ClassDescriptor getNothing() {
        return getBuiltInClassByName("Nothing");
    }

    private ClassDescriptor getPrimitiveClassDescriptor(PrimitiveType primitiveType) {
        return getBuiltInClassByName(primitiveType.getTypeName().asString());
    }

    public ClassDescriptor getArray() {
        return getBuiltInClassByName("Array");
    }

    public ClassDescriptor getNumber() {
        return getBuiltInClassByName("Number");
    }

    public ClassDescriptor getUnit() {
        return getBuiltInClassByName("Unit");
    }

    public static String getFunctionName(int i) {
        return "Function".concat(String.valueOf(i));
    }

    public static ClassId getFunctionClassId(int i) {
        return new ClassId(BUILT_INS_PACKAGE_FQ_NAME, Name.identifier(getFunctionName(i)));
    }

    public ClassDescriptor getFunction(int i) {
        return getBuiltInClassByName(getFunctionName(i));
    }

    public ClassDescriptor getSuspendFunction(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Kind.SuspendFunction.getClassNamePrefix());
        stringBuilder.append(i);
        return getBuiltInClassByFqName(DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier(stringBuilder.toString())));
    }

    public ClassDescriptor getString() {
        return getBuiltInClassByName("String");
    }

    public ClassDescriptor getKClass() {
        return getBuiltInClassByFqName(FQ_NAMES.kClass.toSafe());
    }

    private ClassDescriptor getCollectionClassByName(String str) {
        return getBuiltInClassByName(str, ((PackageFragments) this.packageFragments.invoke()).collectionsPackageFragment);
    }

    public ClassDescriptor getCollection() {
        return getCollectionClassByName("Collection");
    }

    private SimpleType getBuiltInTypeByClassName(String str) {
        return getBuiltInClassByName(str).getDefaultType();
    }

    public SimpleType getNothingType() {
        return getNothing().getDefaultType();
    }

    public SimpleType getNullableNothingType() {
        return getNothingType().makeNullableAsSpecified(true);
    }

    public SimpleType getAnyType() {
        return getAny().getDefaultType();
    }

    public SimpleType getNullableAnyType() {
        return getAnyType().makeNullableAsSpecified(true);
    }

    public SimpleType getDefaultBound() {
        return getNullableAnyType();
    }

    public SimpleType getPrimitiveKotlinType(PrimitiveType primitiveType) {
        return getPrimitiveClassDescriptor(primitiveType).getDefaultType();
    }

    public SimpleType getByteType() {
        return getPrimitiveKotlinType(PrimitiveType.BYTE);
    }

    public SimpleType getShortType() {
        return getPrimitiveKotlinType(PrimitiveType.SHORT);
    }

    public SimpleType getIntType() {
        return getPrimitiveKotlinType(PrimitiveType.INT);
    }

    public SimpleType getLongType() {
        return getPrimitiveKotlinType(PrimitiveType.LONG);
    }

    public SimpleType getFloatType() {
        return getPrimitiveKotlinType(PrimitiveType.FLOAT);
    }

    public SimpleType getDoubleType() {
        return getPrimitiveKotlinType(PrimitiveType.DOUBLE);
    }

    public SimpleType getCharType() {
        return getPrimitiveKotlinType(PrimitiveType.CHAR);
    }

    public SimpleType getBooleanType() {
        return getPrimitiveKotlinType(PrimitiveType.BOOLEAN);
    }

    public SimpleType getUnitType() {
        return getUnit().getDefaultType();
    }

    public SimpleType getStringType() {
        return getString().getDefaultType();
    }

    public KotlinType getArrayElementType(KotlinType kotlinType) {
        if (!isArray(kotlinType)) {
            KotlinType makeNotNullable = TypeUtils.makeNotNullable(kotlinType);
            KotlinType kotlinType2 = (KotlinType) ((Primitives) this.primitives.invoke()).kotlinArrayTypeToPrimitiveKotlinType.get(makeNotNullable);
            if (kotlinType2 != null) {
                return kotlinType2;
            }
            ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(makeNotNullable);
            if (containingModuleOrNull != null) {
                makeNotNullable = getElementTypeForUnsignedArray(makeNotNullable, containingModuleOrNull);
                if (makeNotNullable != null) {
                    return makeNotNullable;
                }
            }
            throw new IllegalStateException("not array: ".concat(String.valueOf(kotlinType)));
        } else if (kotlinType.getArguments().size() == 1) {
            return ((TypeProjection) kotlinType.getArguments().get(0)).getType();
        } else {
            throw new IllegalStateException();
        }
    }

    private static KotlinType getElementTypeForUnsignedArray(KotlinType kotlinType, ModuleDescriptor moduleDescriptor) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null || !UnsignedTypes.INSTANCE.isShortNameOfUnsignedArray(declarationDescriptor.getName())) {
            return null;
        }
        ClassId classId = DescriptorUtilsKt.getClassId(declarationDescriptor);
        if (classId == null) {
            return null;
        }
        classId = UnsignedTypes.INSTANCE.getUnsignedClassIdByArrayClassId(classId);
        if (classId == null) {
            return null;
        }
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, classId);
        if (findClassAcrossModuleDependencies == null) {
            return null;
        }
        return findClassAcrossModuleDependencies.getDefaultType();
    }

    public SimpleType getPrimitiveArrayKotlinType(PrimitiveType primitiveType) {
        return (SimpleType) ((Primitives) this.primitives.invoke()).primitiveTypeToArrayKotlinType.get(primitiveType);
    }

    public SimpleType getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(KotlinType kotlinType) {
        SimpleType simpleType = (SimpleType) ((Primitives) this.primitives.invoke()).primitiveKotlinTypeToKotlinArrayType.get(kotlinType);
        if (simpleType != null) {
            return simpleType;
        }
        if (!UnsignedTypes.INSTANCE.isUnsignedType(kotlinType) || TypeUtils.isNullableType(kotlinType)) {
            return null;
        }
        ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(kotlinType);
        if (containingModuleOrNull == null) {
            return null;
        }
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(containingModuleOrNull, UnsignedTypes.INSTANCE.getUnsignedArrayClassIdByUnsignedClassId(DescriptorUtilsKt.getClassId(kotlinType.getConstructor().getDeclarationDescriptor())));
        if (findClassAcrossModuleDependencies == null) {
            return null;
        }
        return findClassAcrossModuleDependencies.getDefaultType();
    }

    public static boolean isPrimitiveArray(FqNameUnsafe fqNameUnsafe) {
        return FQ_NAMES.arrayClassFqNameToPrimitiveType.get(fqNameUnsafe) != null;
    }

    public static PrimitiveType getPrimitiveType(DeclarationDescriptor declarationDescriptor) {
        return FQ_NAMES.primitiveTypeShortNames.contains(declarationDescriptor.getName()) ? (PrimitiveType) FQ_NAMES.fqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor)) : null;
    }

    public static PrimitiveType getPrimitiveArrayType(DeclarationDescriptor declarationDescriptor) {
        return FQ_NAMES.primitiveArrayTypeShortNames.contains(declarationDescriptor.getName()) ? (PrimitiveType) FQ_NAMES.arrayClassFqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor)) : null;
    }

    public SimpleType getArrayType(Variance variance, KotlinType kotlinType) {
        return KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), getArray(), Collections.singletonList(new TypeProjectionImpl(variance, kotlinType)));
    }

    public static boolean isArray(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.array);
    }

    public static boolean isArrayOrPrimitiveArray(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.array) || getPrimitiveArrayType(classDescriptor) != null;
    }

    public static boolean isPrimitiveArray(KotlinType kotlinType) {
        DeclarationDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor == null || getPrimitiveArrayType(declarationDescriptor) == null) ? false : true;
    }

    public static boolean isPrimitiveType(KotlinType kotlinType) {
        return !kotlinType.isMarkedNullable() && isPrimitiveTypeOrNullablePrimitiveType(kotlinType);
    }

    public static boolean isPrimitiveTypeOrNullablePrimitiveType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor instanceof ClassDescriptor) && isPrimitiveClass((ClassDescriptor) declarationDescriptor);
    }

    public static boolean isPrimitiveClass(ClassDescriptor classDescriptor) {
        return getPrimitiveType(classDescriptor) != null;
    }

    public static boolean isConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor instanceof ClassDescriptor) && classFqNameEquals(declarationDescriptor, fqNameUnsafe);
    }

    private static boolean classFqNameEquals(ClassifierDescriptor classifierDescriptor, FqNameUnsafe fqNameUnsafe) {
        return classifierDescriptor.getName().equals(fqNameUnsafe.shortName()) && fqNameUnsafe.equals(DescriptorUtils.getFqName(classifierDescriptor));
    }

    private static boolean isNotNullConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        return !kotlinType.isMarkedNullable() && isConstructedFromGivenClass(kotlinType, fqNameUnsafe);
    }

    public static boolean isSpecialClassWithNoSupertypes(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.any) || classFqNameEquals(classDescriptor, FQ_NAMES.nothing);
    }

    public static boolean isAny(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.any);
    }

    public static boolean isBoolean(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._boolean);
    }

    public static boolean isChar(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._char);
    }

    public static boolean isInt(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._int);
    }

    public static boolean isByte(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._byte);
    }

    public static boolean isLong(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._long);
    }

    public static boolean isShort(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._short);
    }

    public static boolean isFloat(KotlinType kotlinType) {
        return isFloatOrNullableFloat(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isFloatOrNullableFloat(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._float);
    }

    public static boolean isDouble(KotlinType kotlinType) {
        return isDoubleOrNullableDouble(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isDoubleOrNullableDouble(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._double);
    }

    private static boolean isConstructedFromGivenClassAndNotNullable(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        return isConstructedFromGivenClass(kotlinType, fqNameUnsafe) && !kotlinType.isMarkedNullable();
    }

    public static boolean isNothing(KotlinType kotlinType) {
        return isNothingOrNullableNothing(kotlinType) && !TypeUtils.isNullableType(kotlinType);
    }

    public static boolean isNothingOrNullableNothing(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.nothing);
    }

    public static boolean isAnyOrNullableAny(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.any);
    }

    public static boolean isNullableAny(KotlinType kotlinType) {
        return isAnyOrNullableAny(kotlinType) && kotlinType.isMarkedNullable();
    }

    public static boolean isDefaultBound(KotlinType kotlinType) {
        return isNullableAny(kotlinType);
    }

    public static boolean isUnit(KotlinType kotlinType) {
        return isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.unit);
    }

    public static boolean isString(KotlinType kotlinType) {
        return kotlinType != null && isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.string);
    }

    public static boolean isKClass(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.kClass);
    }

    public static boolean isDeprecated(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor.getOriginal().getAnnotations().hasAnnotation(FQ_NAMES.deprecated)) {
            return true;
        }
        if (!(declarationDescriptor instanceof PropertyDescriptor)) {
            return false;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) declarationDescriptor;
        boolean isVar = propertyDescriptor.isVar();
        DeclarationDescriptor getter = propertyDescriptor.getGetter();
        declarationDescriptor = propertyDescriptor.getSetter();
        if (getter == null || !isDeprecated(getter) || (isVar && (declarationDescriptor == null || !isDeprecated(declarationDescriptor)))) {
            return false;
        }
        return true;
    }

    public static FqName getPrimitiveFqName(PrimitiveType primitiveType) {
        return BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
    }
}
