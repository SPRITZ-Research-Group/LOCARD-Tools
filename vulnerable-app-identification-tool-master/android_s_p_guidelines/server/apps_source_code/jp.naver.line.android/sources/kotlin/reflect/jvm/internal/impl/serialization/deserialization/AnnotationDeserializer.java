package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acom;
import defpackage.actx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Type;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class AnnotationDeserializer {
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Type.BYTE.ordinal()] = 1;
            $EnumSwitchMapping$0[Type.CHAR.ordinal()] = 2;
            $EnumSwitchMapping$0[Type.SHORT.ordinal()] = 3;
            $EnumSwitchMapping$0[Type.INT.ordinal()] = 4;
            $EnumSwitchMapping$0[Type.LONG.ordinal()] = 5;
            $EnumSwitchMapping$0[Type.FLOAT.ordinal()] = 6;
            $EnumSwitchMapping$0[Type.DOUBLE.ordinal()] = 7;
            $EnumSwitchMapping$0[Type.BOOLEAN.ordinal()] = 8;
            $EnumSwitchMapping$0[Type.STRING.ordinal()] = 9;
            $EnumSwitchMapping$0[Type.CLASS.ordinal()] = 10;
            $EnumSwitchMapping$0[Type.ENUM.ordinal()] = 11;
            $EnumSwitchMapping$0[Type.ANNOTATION.ordinal()] = 12;
            $EnumSwitchMapping$0[Type.ARRAY.ordinal()] = 13;
            iArr = new int[Type.values().length];
            $EnumSwitchMapping$1 = iArr;
            iArr[Type.BYTE.ordinal()] = 1;
            $EnumSwitchMapping$1[Type.CHAR.ordinal()] = 2;
            $EnumSwitchMapping$1[Type.SHORT.ordinal()] = 3;
            $EnumSwitchMapping$1[Type.INT.ordinal()] = 4;
            $EnumSwitchMapping$1[Type.LONG.ordinal()] = 5;
            $EnumSwitchMapping$1[Type.FLOAT.ordinal()] = 6;
            $EnumSwitchMapping$1[Type.DOUBLE.ordinal()] = 7;
            $EnumSwitchMapping$1[Type.BOOLEAN.ordinal()] = 8;
            $EnumSwitchMapping$1[Type.STRING.ordinal()] = 9;
            $EnumSwitchMapping$1[Type.CLASS.ordinal()] = 10;
            $EnumSwitchMapping$1[Type.ENUM.ordinal()] = 11;
            $EnumSwitchMapping$1[Type.ANNOTATION.ordinal()] = 12;
            $EnumSwitchMapping$1[Type.ARRAY.ordinal()] = 13;
        }
    }

    public AnnotationDeserializer(ModuleDescriptor moduleDescriptor, NotFoundClasses notFoundClasses) {
        this.module = moduleDescriptor;
        this.notFoundClasses = notFoundClasses;
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    public final AnnotationDescriptor deserializeAnnotation(Annotation annotation, NameResolver nameResolver) {
        ClassDescriptor resolveClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId()));
        Map a = acom.a();
        if (annotation.getArgumentCount() != 0) {
            DeclarationDescriptor declarationDescriptor = resolveClass;
            if (!ErrorUtils.isError(declarationDescriptor) && DescriptorUtils.isAnnotationClass(declarationDescriptor)) {
                ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) acnz.f((Iterable) resolveClass.getConstructors());
                if (classConstructorDescriptor != null) {
                    Iterable valueParameters = classConstructorDescriptor.getValueParameters();
                    Map linkedHashMap = new LinkedHashMap(actx.c(acom.a(acns.a(valueParameters, 10)), 16));
                    for (Object next : valueParameters) {
                        linkedHashMap.put(((ValueParameterDescriptor) next).getName(), next);
                    }
                    Collection arrayList = new ArrayList();
                    for (Argument resolveArgument : annotation.getArgumentList()) {
                        m resolveArgument2 = resolveArgument(resolveArgument, linkedHashMap, nameResolver);
                        if (resolveArgument2 != null) {
                            arrayList.add(resolveArgument2);
                        }
                    }
                    a = acom.a((Iterable) (List) arrayList);
                }
            }
        }
        return new AnnotationDescriptorImpl(resolveClass.getDefaultType(), a, SourceElement.NO_SOURCE);
    }

    private final m<Name, ConstantValue<?>> resolveArgument(Argument argument, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) map.get(NameResolverUtilKt.getName(nameResolver, argument.getNameId()));
        if (valueParameterDescriptor == null) {
            return null;
        }
        return new m(NameResolverUtilKt.getName(nameResolver, argument.getNameId()), resolveValue(valueParameterDescriptor.getType(), argument.getValue(), nameResolver));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final ConstantValue<?> resolveValue(KotlinType kotlinType, Value value, NameResolver nameResolver) {
        boolean booleanValue = Flags.IS_UNSIGNED.get(value.getFlags()).booleanValue();
        Type type = value.getType();
        if (type != null) {
            boolean z = false;
            ConstantValue<?> constantValue;
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    UByteValue uByteValue;
                    byte intValue = (byte) ((int) value.getIntValue());
                    if (booleanValue) {
                        uByteValue = new UByteValue(intValue);
                    } else {
                        uByteValue = new ByteValue(intValue);
                    }
                    constantValue = uByteValue;
                    break;
                case 2:
                    constantValue = new CharValue((char) ((int) value.getIntValue()));
                    break;
                case 3:
                    short intValue2 = (short) ((int) value.getIntValue());
                    constantValue = booleanValue ? new UShortValue(intValue2) : new ShortValue(intValue2);
                    break;
                case 4:
                    int intValue3 = (int) value.getIntValue();
                    constantValue = booleanValue ? new UIntValue(intValue3) : new IntValue(intValue3);
                    break;
                case 5:
                    long intValue4 = value.getIntValue();
                    constantValue = booleanValue ? new ULongValue(intValue4) : new LongValue(intValue4);
                    break;
                case 6:
                    constantValue = new FloatValue(value.getFloatValue());
                    break;
                case 7:
                    constantValue = new DoubleValue(value.getDoubleValue());
                    break;
                case 8:
                    if (value.getIntValue() != 0) {
                        z = true;
                    }
                    constantValue = new BooleanValue(z);
                    break;
                case 9:
                    constantValue = new StringValue(nameResolver.getString(value.getStringValue()));
                    break;
                case 10:
                    constantValue = new KClassValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), value.getArrayDimensionCount());
                    break;
                case 11:
                    constantValue = new EnumValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), NameResolverUtilKt.getName(nameResolver, value.getEnumValueId()));
                    break;
                case 12:
                    constantValue = new AnnotationValue(deserializeAnnotation(value.getAnnotation(), nameResolver));
                    break;
                case 13:
                    KotlinType resolveArrayElementType;
                    if (KotlinBuiltIns.isArray(kotlinType) || KotlinBuiltIns.isPrimitiveArray(kotlinType)) {
                        z = true;
                    }
                    List arrayElementList = value.getArrayElementList();
                    if ((arrayElementList.isEmpty() ^ 1) != 0) {
                        resolveArrayElementType = resolveArrayElementType((Value) acnz.e(arrayElementList), nameResolver);
                        SimpleType primitiveArrayKotlinTypeByPrimitiveKotlinType = getBuiltIns().getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(resolveArrayElementType);
                        resolveArrayElementType = primitiveArrayKotlinTypeByPrimitiveKotlinType != null ? primitiveArrayKotlinTypeByPrimitiveKotlinType : getBuiltIns().getArrayType(Variance.INVARIANT, resolveArrayElementType);
                    } else {
                        resolveArrayElementType = z ? kotlinType : getBuiltIns().getArrayType(Variance.INVARIANT, getBuiltIns().getAnyType());
                    }
                    KotlinType arrayElementType = getBuiltIns().getArrayElementType(z ? kotlinType : resolveArrayElementType);
                    ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
                    Iterable<Value> iterable = arrayElementList;
                    Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
                    for (Value resolveValue : iterable) {
                        arrayList.add(resolveValue(arrayElementType, resolveValue, nameResolver));
                    }
                    constantValue = constantValueFactory.createArrayValue((List) arrayList, resolveArrayElementType);
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Unsupported annotation argument type: ");
        stringBuilder.append(value.getType());
        stringBuilder.append(" (expected ");
        stringBuilder.append(kotlinType);
        stringBuilder.append(')');
        throw new IllegalStateException(stringBuilder.toString().toString());
    }

    private final SimpleType resolveArrayElementType(Value value, NameResolver nameResolver) {
        KotlinBuiltIns builtIns = getBuiltIns();
        Type type = value.getType();
        if (type != null) {
            switch (WhenMappings.$EnumSwitchMapping$1[type.ordinal()]) {
                case 1:
                    return builtIns.getByteType();
                case 2:
                    return builtIns.getCharType();
                case 3:
                    return builtIns.getShortType();
                case 4:
                    return builtIns.getIntType();
                case 5:
                    return builtIns.getLongType();
                case 6:
                    return builtIns.getFloatType();
                case 7:
                    return builtIns.getDoubleType();
                case 8:
                    return builtIns.getBooleanType();
                case 9:
                    return builtIns.getStringType();
                case 10:
                    throw new IllegalStateException("Arrays of class literals are not supported yet".toString());
                case 11:
                    return resolveClass(NameResolverUtilKt.getClassId(nameResolver, value.getClassId())).getDefaultType();
                case 12:
                    return resolveClass(NameResolverUtilKt.getClassId(nameResolver, value.getAnnotation().getId())).getDefaultType();
                case 13:
                    throw new IllegalStateException("Array of arrays is impossible".toString());
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Unknown type: ");
        stringBuilder.append(value.getType());
        throw new IllegalStateException(stringBuilder.toString().toString());
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
