package defpackage;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u00042\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lkotlin/reflect/jvm/internal/RuntimeTypeMapper;", "", "()V", "JAVA_LANG_VOID", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "primitiveType", "Lkotlin/reflect/jvm/internal/impl/builtins/PrimitiveType;", "Ljava/lang/Class;", "getPrimitiveType", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "mapJvmClassToKotlinClassId", "klass", "mapJvmFunctionSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "mapName", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "mapPropertySignature", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "possiblyOverriddenProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "mapSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "possiblySubstitutedFunction", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* renamed from: acxk */
public final class acxk {
    public static final acxk a = new acxk();
    private static final ClassId b = ClassId.topLevel(new FqName("java.lang.Void"));

    private acxk() {
    }

    public static acvi a(FunctionDescriptor functionDescriptor) {
        FunctionDescriptor original = ((FunctionDescriptor) DescriptorUtils.unwrapFakeOverride(functionDescriptor)).getOriginal();
        if (original instanceof DeserializedCallableMemberDescriptor) {
            DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor = (DeserializedCallableMemberDescriptor) original;
            MessageLite proto = deserializedCallableMemberDescriptor.getProto();
            if (proto instanceof Function) {
                Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((Function) proto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable());
                if (jvmMethodSignature != null) {
                    return new acvn(jvmMethodSignature);
                }
            }
            if (proto instanceof Constructor) {
                Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((Constructor) proto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable());
                if (jvmConstructorSignature != null) {
                    if (InlineClassesUtilsKt.isInlineClass(functionDescriptor.getContainingDeclaration())) {
                        return new acvn(jvmConstructorSignature);
                    }
                    return new acvm(jvmConstructorSignature);
                }
            }
            return acxk.b(original);
        }
        Object obj = null;
        SourceElement source;
        JavaSourceElement javaSourceElement;
        if (original instanceof JavaMethodDescriptor) {
            source = ((JavaMethodDescriptor) original).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            javaSourceElement = (JavaSourceElement) source;
            JavaElement javaElement = javaSourceElement != null ? javaSourceElement.getJavaElement() : null;
            if (!(javaElement instanceof adau)) {
                javaElement = null;
            }
            adau adau = (adau) javaElement;
            if (adau != null) {
                java.lang.reflect.Method b = adau.b();
                if (b != null) {
                    return new acvl(b);
                }
            }
            throw new acxb("Incorrect resolution sequence for Java method ".concat(String.valueOf(original)));
        } else if (original instanceof JavaClassConstructorDescriptor) {
            source = ((JavaClassConstructorDescriptor) original).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            javaSourceElement = (JavaSourceElement) source;
            if (javaSourceElement != null) {
                obj = javaSourceElement.getJavaElement();
            }
            if (obj instanceof adao) {
                return new acvk(((adao) obj).b());
            }
            if (obj instanceof adal) {
                adal adal = (adal) obj;
                if (adal.isAnnotationType()) {
                    return new acvj(adal.b());
                }
            }
            StringBuilder stringBuilder = new StringBuilder("Incorrect resolution sequence for Java constructor ");
            stringBuilder.append(original);
            stringBuilder.append(" (");
            stringBuilder.append(obj);
            stringBuilder.append(')');
            throw new acxb(stringBuilder.toString());
        } else if (DescriptorFactory.isEnumValueOfMethod(original) || DescriptorFactory.isEnumValuesMethod(original)) {
            return acxk.b(original);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder("Unknown origin of ");
            stringBuilder2.append(original);
            stringBuilder2.append(" (");
            stringBuilder2.append(original.getClass());
            stringBuilder2.append(')');
            throw new acxb(stringBuilder2.toString());
        }
    }

    public static acvo a(PropertyDescriptor propertyDescriptor) {
        PropertySetterDescriptor setter;
        PropertyDescriptor original = ((PropertyDescriptor) DescriptorUtils.unwrapFakeOverride(propertyDescriptor)).getOriginal();
        acvn acvn = null;
        if (original instanceof DeserializedPropertyDescriptor) {
            DeserializedPropertyDescriptor deserializedPropertyDescriptor = (DeserializedPropertyDescriptor) original;
            Property proto = deserializedPropertyDescriptor.getProto();
            JvmPropertySignature jvmPropertySignature = (JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, JvmProtoBuf.propertySignature);
            if (jvmPropertySignature != null) {
                return new acvr(original, proto, jvmPropertySignature, deserializedPropertyDescriptor.getNameResolver(), deserializedPropertyDescriptor.getTypeTable());
            }
        } else if (original instanceof JavaPropertyDescriptor) {
            SourceElement source = ((JavaPropertyDescriptor) original).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            Object javaElement = javaSourceElement != null ? javaSourceElement.getJavaElement() : null;
            if (javaElement instanceof adar) {
                return new acvp(((adar) javaElement).b());
            }
            if (javaElement instanceof adau) {
                java.lang.reflect.Method b;
                java.lang.reflect.Method b2 = ((adau) javaElement).b();
                setter = original.getSetter();
                SourceElement source2 = setter != null ? setter.getSource() : null;
                if (!(source2 instanceof JavaSourceElement)) {
                    source2 = null;
                }
                JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
                JavaElement javaElement2 = javaSourceElement2 != null ? javaSourceElement2.getJavaElement() : null;
                if (!(javaElement2 instanceof adau)) {
                    javaElement2 = null;
                }
                adau adau = (adau) javaElement2;
                if (adau != null) {
                    b = adau.b();
                }
                return new acvq(b2, b);
            }
            StringBuilder stringBuilder = new StringBuilder("Incorrect resolution sequence for Java field ");
            stringBuilder.append(original);
            stringBuilder.append(" (source = ");
            stringBuilder.append(javaElement);
            stringBuilder.append(')');
            throw new acxb(stringBuilder.toString());
        }
        PropertyGetterDescriptor getter = original.getGetter();
        if (getter == null) {
            acry.a();
        }
        acvn b3 = acxk.b((FunctionDescriptor) getter);
        setter = original.getSetter();
        if (setter != null) {
            acvn = acxk.b((FunctionDescriptor) setter);
        }
        return new acvs(b3, acvn);
    }

    private static acvn b(FunctionDescriptor functionDescriptor) {
        return new acvn(new Method(acxk.a((CallableMemberDescriptor) functionDescriptor), MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 1, null)));
    }

    private static String a(CallableMemberDescriptor callableMemberDescriptor) {
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(callableMemberDescriptor);
        if (jvmMethodNameIfSpecial == null) {
            if (callableMemberDescriptor instanceof PropertyGetterDescriptor) {
                return JvmAbi.getterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString());
            }
            if (callableMemberDescriptor instanceof PropertySetterDescriptor) {
                return JvmAbi.setterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString());
            }
            jvmMethodNameIfSpecial = callableMemberDescriptor.getName().asString();
        }
        return jvmMethodNameIfSpecial;
    }

    public static ClassId a(Class<?> cls) {
        if (cls.isArray()) {
            PrimitiveType b = acxk.b(cls.getComponentType());
            if (b != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, b.getArrayTypeName());
            }
            return ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.array.toSafe());
        } else if (acry.a((Object) cls, Void.TYPE)) {
            return b;
        } else {
            PrimitiveType b2 = acxk.b((Class) cls);
            if (b2 != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, b2.getTypeName());
            }
            ClassId e = adab.e(cls);
            if (!e.isLocal()) {
                ClassId mapJavaToKotlin = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(e.asSingleFqName());
                if (mapJavaToKotlin != null) {
                    return mapJavaToKotlin;
                }
            }
            return e;
        }
    }

    private static PrimitiveType b(Class<?> cls) {
        return cls.isPrimitive() ? JvmPrimitiveType.get(cls.getSimpleName()).getPrimitiveType() : null;
    }
}
