package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acnr;
import defpackage.acns;
import defpackage.acnz;
import defpackage.acom;
import defpackage.acqr;
import defpackage.acru;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeBasedStarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class TypeDeserializer {
    private final DeserializationContext c;
    private final acqr<Integer, ClassDescriptor> classDescriptors;
    private final String debugName;
    private boolean experimentalSuspendFunctionTypeEncountered;
    private final TypeDeserializer parent;
    private final acqr<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    public TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List<TypeParameter> list, String str, boolean z) {
        Map a;
        this.c = deserializationContext;
        this.parent = typeDeserializer;
        this.debugName = str;
        this.experimentalSuspendFunctionTypeEncountered = z;
        this.classDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$classDescriptors$1(this));
        this.typeAliasDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$typeAliasDescriptors$1(this));
        if (list.isEmpty()) {
            a = acom.a();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            for (TypeParameter typeParameter : list) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.c, typeParameter, i));
                i++;
            }
            a = linkedHashMap;
        }
        this.typeParameterDescriptors = a;
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, boolean z, int i, acru acru) {
        this(deserializationContext, typeDeserializer, list, str, (i & 16) != 0 ? false : z);
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return acnz.k((Iterable) this.typeParameterDescriptors.values());
    }

    public final KotlinType type(Type type) {
        if (!type.hasFlexibleTypeCapabilitiesId()) {
            return simpleType(type);
        }
        String string = this.c.getNameResolver().getString(type.getFlexibleTypeCapabilitiesId());
        SimpleType simpleType = simpleType(type);
        Type flexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(type, this.c.getTypeTable());
        if (flexibleUpperBound == null) {
            acry.a();
        }
        return this.c.getComponents().getFlexibleTypeDeserializer().create(type, string, simpleType, simpleType(flexibleUpperBound));
    }

    public final SimpleType simpleType(Type type) {
        SimpleType computeLocalClassifierReplacementType = type.hasClassName() ? computeLocalClassifierReplacementType(type.getClassName()) : type.hasTypeAliasName() ? computeLocalClassifierReplacementType(type.getTypeAliasName()) : null;
        if (computeLocalClassifierReplacementType != null) {
            return computeLocalClassifierReplacementType;
        }
        TypeConstructor typeConstructor = typeConstructor(type);
        if (ErrorUtils.isError(typeConstructor.getDeclarationDescriptor())) {
            return ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
        }
        DeserializedAnnotations deserializedAnnotations = new DeserializedAnnotations(this.c.getStorageManager(), new TypeDeserializer$simpleType$annotations$1(this, type));
        Iterable invoke = new TypeDeserializer$simpleType$1(this).invoke(type);
        Collection arrayList = new ArrayList(acns.a(invoke, 10));
        int i = 0;
        for (Object next : invoke) {
            int i2 = i + 1;
            if (i < 0) {
                acnr.a();
            }
            arrayList.add(typeArgument((TypeParameterDescriptor) acnz.b(typeConstructor.getParameters(), i), (Argument) next));
            i = i2;
        }
        List k = acnz.k((Iterable) (List) arrayList);
        if (Flags.SUSPEND_TYPE.get(type.getFlags()).booleanValue()) {
            computeLocalClassifierReplacementType = createSuspendFunctionType(deserializedAnnotations, typeConstructor, k, type.getNullable());
        } else {
            computeLocalClassifierReplacementType = KotlinTypeFactory.simpleType(deserializedAnnotations, typeConstructor, k, type.getNullable());
        }
        type = ProtoTypeTableUtilKt.abbreviatedType(type, this.c.getTypeTable());
        if (type == null) {
            return computeLocalClassifierReplacementType;
        }
        return SpecialTypesKt.withAbbreviation(computeLocalClassifierReplacementType, simpleType(type));
    }

    private final TypeConstructor typeConstructor(Type type) {
        TypeDeserializer$typeConstructor$1 typeDeserializer$typeConstructor$1 = new TypeDeserializer$typeConstructor$1(this, type);
        if (type.hasClassName()) {
            ClassDescriptor classDescriptor = (ClassDescriptor) this.classDescriptors.invoke(Integer.valueOf(type.getClassName()));
            if (classDescriptor == null) {
                classDescriptor = typeDeserializer$typeConstructor$1.invoke(type.getClassName());
            }
            return classDescriptor.getTypeConstructor();
        }
        TypeConstructor typeParameterTypeConstructor;
        if (type.hasTypeParameter()) {
            typeParameterTypeConstructor = typeParameterTypeConstructor(type.getTypeParameter());
            if (typeParameterTypeConstructor == null) {
                StringBuilder stringBuilder = new StringBuilder("Unknown type parameter ");
                stringBuilder.append(type.getTypeParameter());
                return ErrorUtils.createErrorTypeConstructor(stringBuilder.toString());
            }
        } else if (type.hasTypeParameterName()) {
            DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
            Object string = this.c.getNameResolver().getString(type.getTypeParameterName());
            for (Object next : getOwnTypeParameters()) {
                if (acry.a(((TypeParameterDescriptor) next).getName().asString(), string)) {
                    break;
                }
            }
            Object next2 = null;
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) next2;
            if (typeParameterDescriptor != null) {
                TypeConstructor typeConstructor = typeParameterDescriptor.getTypeConstructor();
                if (typeConstructor != null) {
                    typeParameterTypeConstructor = typeConstructor;
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder("Deserialized type parameter ");
            stringBuilder2.append(string);
            stringBuilder2.append(" in ");
            stringBuilder2.append(containingDeclaration);
            return ErrorUtils.createErrorTypeConstructor(stringBuilder2.toString());
        } else if (type.hasTypeAliasName()) {
            ClassifierDescriptor classifierDescriptor = (ClassifierDescriptor) this.typeAliasDescriptors.invoke(Integer.valueOf(type.getTypeAliasName()));
            if (classifierDescriptor == null) {
                classifierDescriptor = typeDeserializer$typeConstructor$1.invoke(type.getTypeAliasName());
            }
            return classifierDescriptor.getTypeConstructor();
        } else {
            typeParameterTypeConstructor = ErrorUtils.createErrorTypeConstructor("Unknown type");
        }
        return typeParameterTypeConstructor;
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        SimpleType simpleType = null;
        switch (typeConstructor.getParameters().size() - list.size()) {
            case 0:
                simpleType = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
                break;
            case 1:
                int size = list.size() - 1;
                if (size >= 0) {
                    simpleType = KotlinTypeFactory.simpleType(annotations, typeConstructor.getBuiltIns().getSuspendFunction(size).getTypeConstructor(), list, z);
                    break;
                }
                break;
        }
        return simpleType == null ? ErrorUtils.createErrorTypeWithArguments("Bad suspend function in metadata with constructor: ".concat(String.valueOf(typeConstructor)), list) : simpleType;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        KotlinType simpleType = KotlinTypeFactory.simpleType(annotations, typeConstructor, list, z);
        if (FunctionTypesKt.isFunctionType(simpleType)) {
            return transformRuntimeFunctionTypeToSuspendFunction(simpleType);
        }
        return null;
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        boolean releaseCoroutines = this.c.getComponents().getConfiguration().getReleaseCoroutines();
        TypeProjection typeProjection = (TypeProjection) acnz.h(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        Object obj = null;
        if (typeProjection != null) {
            KotlinType type = typeProjection.getType();
            if (type != null) {
                ClassifierDescriptor declarationDescriptor = type.getConstructor().getDeclarationDescriptor();
                FqName fqNameSafe = declarationDescriptor != null ? DescriptorUtilsKt.getFqNameSafe(declarationDescriptor) : null;
                boolean z = true;
                if (type.getArguments().size() != 1 || (!SuspendFunctionTypesKt.isContinuation(fqNameSafe, true) && !SuspendFunctionTypesKt.isContinuation(fqNameSafe, false))) {
                    return (SimpleType) kotlinType;
                }
                type = ((TypeProjection) acnz.i(type.getArguments())).getType();
                DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
                if (!(containingDeclaration instanceof CallableDescriptor)) {
                    containingDeclaration = null;
                }
                CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
                if (callableDescriptor != null) {
                    obj = DescriptorUtilsKt.fqNameOrNull(callableDescriptor);
                }
                if (acry.a(obj, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
                    return createSimpleSuspendFunctionType(kotlinType, type);
                }
                if (!(this.experimentalSuspendFunctionTypeEncountered || (releaseCoroutines && SuspendFunctionTypesKt.isContinuation(fqNameSafe, releaseCoroutines ^ true)))) {
                    z = false;
                }
                this.experimentalSuspendFunctionTypeEncountered = z;
                return createSimpleSuspendFunctionType(kotlinType, type);
            }
        }
        return null;
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations annotations = kotlinType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        Iterable<TypeProjection> k = acnz.k(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        Collection arrayList = new ArrayList(acns.a((Iterable) k, 10));
        for (TypeProjection type : k) {
            arrayList.add(type.getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, (List) arrayList, null, kotlinType2, true).makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final TypeConstructor typeParameterTypeConstructor(int i) {
        TypeDeserializer typeDeserializer = this;
        while (true) {
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) typeDeserializer.typeParameterDescriptors.get(Integer.valueOf(i));
            if (typeParameterDescriptor != null) {
                TypeConstructor typeConstructor = typeParameterDescriptor.getTypeConstructor();
                if (typeConstructor != null) {
                    return typeConstructor;
                }
            }
            typeDeserializer = typeDeserializer.parent;
            if (typeDeserializer == null) {
                return null;
            }
        }
    }

    private final ClassDescriptor computeClassDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        return NameResolverUtilKt.getClassId(this.c.getNameResolver(), i).isLocal() ? this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers() : null;
    }

    private final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, Argument argument) {
        if (argument.getProjection() != Projection.STAR) {
            Variance variance = ProtoEnumFlags.INSTANCE.variance(argument.getProjection());
            Type type = ProtoTypeTableUtilKt.type(argument, this.c.getTypeTable());
            if (type == null) {
                return new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded"));
            }
            return new TypeProjectionImpl(variance, type(type));
        } else if (typeParameterDescriptor == null) {
            return new TypeBasedStarProjectionImpl(this.c.getComponents().getModuleDescriptor().getBuiltIns().getNullableAnyType());
        } else {
            return new StarProjectionImpl(typeParameterDescriptor);
        }
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.debugName);
        if (this.parent == null) {
            str = "";
        } else {
            StringBuilder stringBuilder2 = new StringBuilder(". Child of ");
            stringBuilder2.append(this.parent.debugName);
            str = stringBuilder2.toString();
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
