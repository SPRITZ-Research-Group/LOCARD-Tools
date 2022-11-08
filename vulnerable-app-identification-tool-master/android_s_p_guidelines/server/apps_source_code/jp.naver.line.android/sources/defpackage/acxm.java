package defpackage;

import com.linecorp.linelive.player.component.videoplayer.LiveVideoPlayerService;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.u;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000°\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001an\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\b\b\u0000\u0010\u0010*\u00020\u0011\"\b\b\u0001\u0010\u000f*\u00020\u00062\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u0002H\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u001d\u0010\u001b\u001a\u0019\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000f0\u001c¢\u0006\u0002\b\u001eH\u0000¢\u0006\u0002\u0010\u001f\u001a.\u0010 \u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&H\u0002\u001a(\u0010 \u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010%\u001a\u00020&H\u0002\u001a\"\u0010)\u001a\u0002H*\"\u0004\b\u0000\u0010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H*0,H\b¢\u0006\u0002\u0010-\u001a\u0014\u0010.\u001a\b\u0012\u0002\b\u0003\u0018\u00010/*\u0004\u0018\u000100H\u0000\u001a\u0010\u00101\u001a\u0004\u0018\u000102*\u0004\u0018\u000100H\u0000\u001a\u0014\u00103\u001a\b\u0012\u0002\b\u0003\u0018\u000104*\u0004\u0018\u000100H\u0000\u001a\u0012\u00105\u001a\b\u0012\u0004\u0012\u00020706*\u000208H\u0000\u001a\u000e\u00109\u001a\u0004\u0018\u000107*\u00020:H\u0002\u001a\u0012\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013*\u00020<H\u0000\u001a\u000e\u0010=\u001a\u0004\u0018\u00010>*\u00020?H\u0000\u001a\u001a\u0010@\u001a\u0004\u0018\u000100*\u0006\u0012\u0002\b\u00030A2\u0006\u0010!\u001a\u00020\"H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b8@X\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006B"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "packageModuleName", "", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;", "getPackageModuleName", "(Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;)Ljava/lang/String;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", "packageName", "className", "arrayDimensions", "", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/Visibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acxm */
public final class acxm {
    private static final FqName a = new FqName("kotlin.jvm.JvmStatic");

    public static final FqName a() {
        return a;
    }

    public static final Class<?> a(ClassDescriptor classDescriptor) {
        SourceElement source = classDescriptor.getSource();
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            if (binaryClass != null) {
                return ((aczh) binaryClass).a();
            }
            throw new v("null cannot be cast to non-null type kotlin.reflect.jvm.internal.components.ReflectKotlinClass");
        } else if (source instanceof aczr) {
            adap a = ((aczr) source).a();
            if (a != null) {
                return ((adal) a).b();
            }
            throw new v("null cannot be cast to non-null type kotlin.reflect.jvm.internal.structure.ReflectJavaClass");
        } else {
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (classId == null) {
                return null;
            }
            return acxm.a(adab.a(classDescriptor.getClass()), classId, 0);
        }
    }

    private static final Class<?> a(ClassLoader classLoader, ClassId classId, int i) {
        ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
        if (mapKotlinToJava != null) {
            classId = mapKotlinToJava;
        }
        return acxm.a(classLoader, classId.getPackageFqName().asString(), classId.getRelativeClassName().asString(), i);
    }

    private static final Class<?> a(ClassLoader classLoader, String str, String str2, int i) {
        if (acry.a((Object) str, (Object) "kotlin")) {
            switch (str2.hashCode()) {
                case -901856463:
                    if (str2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (str2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (str2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (str2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (str2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (str2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (str2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (str2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (str2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append('.');
        stringBuilder.append(str2.replace('.', '$'));
        str = stringBuilder.toString();
        for (int i2 = 0; i2 < i; i2++) {
            str = "[".concat(String.valueOf(str));
        }
        return aczg.a(classLoader, str);
    }

    public static final acvb a(Visibility visibility) {
        if (acry.a((Object) visibility, Visibilities.PUBLIC)) {
            return acvb.PUBLIC;
        }
        if (acry.a((Object) visibility, Visibilities.PROTECTED)) {
            return acvb.PROTECTED;
        }
        if (acry.a((Object) visibility, Visibilities.INTERNAL)) {
            return acvb.INTERNAL;
        }
        return (acry.a((Object) visibility, Visibilities.PRIVATE) || acry.a((Object) visibility, Visibilities.PRIVATE_TO_THIS)) ? acvb.PRIVATE : null;
    }

    public static final List<Annotation> a(Annotated annotated) {
        Collection arrayList = new ArrayList();
        for (AnnotationDescriptor annotationDescriptor : annotated.getAnnotations()) {
            SourceElement source = annotationDescriptor.getSource();
            Object obj = null;
            if (source instanceof aczd) {
                obj = ((aczd) source).a();
            } else if (source instanceof aczr) {
                adap a = ((aczr) source).a();
                if (!(a instanceof adac)) {
                    a = null;
                }
                adac adac = (adac) a;
                if (adac != null) {
                    obj = adac.a();
                }
            } else {
                obj = acxm.a(annotationDescriptor);
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    private static final Annotation a(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Class a = annotationClass != null ? acxm.a(annotationClass) : null;
        if (!(a instanceof Class)) {
            a = null;
        }
        if (a == null) {
            return null;
        }
        Collection arrayList = new ArrayList();
        for (Entry entry : annotationDescriptor.getAllValueArguments().entrySet()) {
            Name name = (Name) entry.getKey();
            Object a2 = acxm.a((ConstantValue) entry.getValue(), a.getClassLoader());
            a2 = a2 != null ? u.a(name.asString(), a2) : null;
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return (Annotation) acxs.a(a, acom.a((Iterable) (List) arrayList));
    }

    private static final Object a(ConstantValue<?> constantValue, ClassLoader classLoader) {
        if (constantValue instanceof AnnotationValue) {
            return acxm.a((AnnotationDescriptor) ((AnnotationValue) constantValue).getValue());
        }
        if (constantValue instanceof ArrayValue) {
            Iterable<ConstantValue> iterable = (Iterable) ((ArrayValue) constantValue).getValue();
            Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
            for (ConstantValue a : iterable) {
                arrayList.add(acxm.a(a, classLoader));
            }
            Object toArray = ((List) arrayList).toArray(new Object[0]);
            if (toArray != null) {
                return toArray;
            }
            throw new v("null cannot be cast to non-null type kotlin.Array<T>");
        } else if (constantValue instanceof EnumValue) {
            m mVar = (m) ((EnumValue) constantValue).getValue();
            ClassId classId = (ClassId) mVar.c();
            Name name = (Name) mVar.d();
            Class a2 = acxm.a(classLoader, classId, 0);
            if (a2 == null) {
                return null;
            }
            if (a2 != null) {
                return Enum.valueOf(a2, name.asString());
            }
            throw new v("null cannot be cast to non-null type java.lang.Class<out kotlin.Enum<*>>");
        } else if (constantValue instanceof KClassValue) {
            ClassLiteralValue classLiteralValue = (ClassLiteralValue) ((KClassValue) constantValue).getValue();
            return acxm.a(classLoader, classLiteralValue.component1(), classLiteralValue.component2());
        } else if ((constantValue instanceof ErrorValue) || (constantValue instanceof NullValue)) {
            return null;
        } else {
            return constantValue.getValue();
        }
    }

    public static final acwc a(Object obj) {
        acwc acwc = (acwc) (!(obj instanceof acwc) ? null : obj);
        if (acwc != null) {
            return acwc;
        }
        if (!(obj instanceof acrx)) {
            obj = null;
        }
        acrx acrx = (acrx) obj;
        actz compute = acrx != null ? acrx.compute() : null;
        if (!(compute instanceof acwc)) {
            compute = null;
        }
        return (acwc) compute;
    }

    public static final acws<?> b(Object obj) {
        acws<?> acws = (acws) (!(obj instanceof acws) ? null : obj);
        if (acws != null) {
            return acws;
        }
        if (!(obj instanceof acse)) {
            obj = null;
        }
        acse acse = (acse) obj;
        actz compute = acse != null ? acse.compute() : null;
        if (!(compute instanceof acws)) {
            compute = null;
        }
        return (acws) compute;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final String a(aczh aczh) {
        String str;
        while (true) {
            KotlinClassHeader classHeader = aczh.getClassHeader();
            str = null;
            if (classHeader.getMetadataVersion().isCompatible()) {
                switch (acxn.a[classHeader.getKind().ordinal()]) {
                    case 1:
                    case 2:
                        String[] data = classHeader.getData();
                        if (data == null) {
                            acry.a();
                        }
                        String[] strings = classHeader.getStrings();
                        if (strings == null) {
                            acry.a();
                        }
                        m readPackageDataFrom = JvmProtoBufUtil.readPackageDataFrom(data, strings);
                        JvmNameResolver jvmNameResolver = (JvmNameResolver) readPackageDataFrom.c();
                        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull((Package) readPackageDataFrom.d(), JvmProtoBuf.packageModuleName);
                        if (num != null) {
                            str = jvmNameResolver.getString(num.intValue());
                            break;
                        }
                        return LiveVideoPlayerService.TAG_MAIN;
                    case 3:
                        String str2 = (String) acnz.f(classHeader.getMultifilePartNames());
                        if (str2 != null) {
                            aczi aczi = aczh.a;
                            aczh = aczi.a(aczh.a().getClassLoader().loadClass(str2.replace('/', '.')));
                            if (aczh == null) {
                                break;
                            }
                        } else {
                            return null;
                        }
                    default:
                        break;
                }
            }
            return null;
        }
        return str;
    }

    public static final ReceiverParameterDescriptor a(CallableDescriptor callableDescriptor) {
        if (callableDescriptor.getDispatchReceiverParameter() == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            return ((ClassDescriptor) containingDeclaration).getThisAsReceiverParameter();
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    public static final <M extends MessageLite, D extends CallableDescriptor> D a(Class<?> cls, M m, NameResolver nameResolver, TypeTable typeTable, BinaryVersion binaryVersion, acrc<? super MemberDeserializer, ? super M, ? extends D> acrc) {
        List typeParameterList;
        M m2 = m;
        aczm a = acxc.a(cls);
        if (m2 instanceof Function) {
            typeParameterList = ((Function) m2).getTypeParameterList();
        } else if (m2 instanceof Property) {
            typeParameterList = ((Property) m2).getTypeParameterList();
        } else {
            throw new IllegalStateException("Unsupported message: ".concat(String.valueOf(m)).toString());
        }
        List list = typeParameterList;
        return (CallableDescriptor) acrc.invoke(new MemberDeserializer(new DeserializationContext(a.b(), nameResolver, a.a(), typeTable, VersionRequirementTable.Companion.getEMPTY(), binaryVersion, null, null, list)), m);
    }
}
