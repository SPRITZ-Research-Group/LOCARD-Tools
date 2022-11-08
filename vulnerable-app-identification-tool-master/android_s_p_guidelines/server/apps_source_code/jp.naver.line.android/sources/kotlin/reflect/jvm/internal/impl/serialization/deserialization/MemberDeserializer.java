package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acnr;
import defpackage.acns;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acom;
import defpackage.acoz;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement.Version;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Package;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.v;

public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer = new AnnotationDeserializer(this.c.getComponents().getModuleDescriptor(), this.c.getComponents().getNotFoundClasses());
    private final DeserializationContext c;

    private final int loadOldFlags(int i) {
        return (i & 63) + ((i >> 8) << 6);
    }

    public MemberDeserializer(DeserializationContext deserializationContext) {
        this.c = deserializationContext;
    }

    public final kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor loadProperty(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property r34) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r7_7 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl) in PHI: PHI: (r7_9 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl) = (r7_7 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl), (r7_8 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl) binds: {(r7_7 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl)=B:25:0x01ab, (r7_8 kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl)=B:26:0x01df}
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
        r33 = this;
        r0 = r33;
        r15 = r34;
        r1 = r34.hasFlags();
        if (r1 == 0) goto L_0x0010;
    L_0x000a:
        r1 = r34.getFlags();
    L_0x000e:
        r14 = r1;
        goto L_0x0019;
    L_0x0010:
        r1 = r34.getOldFlags();
        r1 = r0.loadOldFlags(r1);
        goto L_0x000e;
    L_0x0019:
        r13 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
        r1 = r13;
        r2 = r0.c;
        r2 = r2.getContainingDeclaration();
        r3 = 0;
        r12 = r15;
        r12 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r12;
        r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY;
        r4 = r0.getAnnotations(r12, r14, r4);
        r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY;
        r6 = r6.get(r14);
        r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r6;
        r5 = r5.modality(r6);
        r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY;
        r7 = r7.get(r14);
        r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r7;
        r6 = r6.visibility(r7);
        r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_VAR;
        r7 = r7.get(r14);
        r7 = r7.booleanValue();
        r8 = r0.c;
        r8 = r8.getNameResolver();
        r9 = r34.getName();
        r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r8, r9);
        r9 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MEMBER_KIND;
        r10 = r10.get(r14);
        r10 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind) r10;
        r9 = r9.memberKind(r10);
        r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_LATEINIT;
        r10 = r10.get(r14);
        r10 = r10.booleanValue();
        r11 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_CONST;
        r11 = r11.get(r14);
        r11 = r11.booleanValue();
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_PROPERTY;
        r3 = r3.get(r14);
        r3 = r3.booleanValue();
        r21 = r12;
        r12 = r3;
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_DELEGATED;
        r3 = r3.get(r14);
        r3 = r3.booleanValue();
        r22 = r13;
        r13 = r3;
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXPECT_PROPERTY;
        r3 = r3.get(r14);
        r3 = r3.booleanValue();
        r23 = r14;
        r14 = r3;
        r3 = r0.c;
        r16 = r3.getNameResolver();
        r3 = r0.c;
        r17 = r3.getTypeTable();
        r3 = r0.c;
        r18 = r3.getVersionRequirementTable();
        r3 = r0.c;
        r19 = r3.getContainerSource();
        r3 = r15;
        r15 = r34;
        r3 = 0;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);
        r1 = r0.c;
        r2 = r22;
        r25 = r2;
        r25 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r25;
        r26 = r34.getTypeParameterList();
        r27 = 0;
        r28 = 0;
        r29 = 0;
        r30 = 0;
        r31 = 60;
        r32 = 0;
        r24 = r1;
        r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r24, r25, r26, r27, r28, r29, r30, r31, r32);
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_GETTER;
        r12 = r23;
        r3 = r3.get(r12);
        r3 = r3.booleanValue();
        if (r3 == 0) goto L_0x0103;
    L_0x00f4:
        r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.hasReceiver(r34);
        if (r4 == 0) goto L_0x0103;
    L_0x00fa:
        r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER;
        r13 = r21;
        r4 = r0.getReceiverParameterAnnotations(r13, r4);
        goto L_0x010b;
    L_0x0103:
        r13 = r21;
        r4 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion;
        r4 = r4.getEMPTY();
    L_0x010b:
        r5 = r1.getTypeDeserializer();
        r6 = r0.c;
        r6 = r6.getTypeTable();
        r14 = r34;
        r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.returnType(r14, r6);
        r5 = r5.type(r6);
        r6 = r1.getTypeDeserializer();
        r6 = r6.getOwnTypeParameters();
        r7 = r33.getDispatchReceiverParameter();
        r8 = r0.c;
        r8 = r8.getTypeTable();
        r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.receiverType(r14, r8);
        r9 = 0;
        if (r8 == 0) goto L_0x014a;
    L_0x0138:
        r10 = r1.getTypeDeserializer();
        r8 = r10.type(r8);
        if (r8 == 0) goto L_0x014a;
    L_0x0142:
        r10 = r2;
        r10 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r10;
        r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createExtensionReceiverParameterForCallable(r10, r8, r4);
        goto L_0x014b;
    L_0x014a:
        r4 = r9;
    L_0x014b:
        r2.setType(r5, r6, r7, r4);
        r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_ANNOTATIONS;
        r4 = r4.get(r12);
        r15 = r4.booleanValue();
        r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY;
        r4 = r4.get(r12);
        r16 = r4;
        r16 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r16;
        r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY;
        r4 = r4.get(r12);
        r17 = r4;
        r17 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r17;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.getAccessorFlags(r15, r16, r17, r18, r19, r20);
        r15 = 1;
        if (r3 == 0) goto L_0x01f0;
    L_0x0179:
        r3 = r34.hasGetterFlags();
        if (r3 == 0) goto L_0x0184;
    L_0x017f:
        r3 = r34.getGetterFlags();
        goto L_0x0185;
    L_0x0184:
        r3 = r4;
    L_0x0185:
        r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT;
        r5 = r5.get(r3);
        r5 = r5.booleanValue();
        r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR;
        r6 = r6.get(r3);
        r22 = r6.booleanValue();
        r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR;
        r6 = r6.get(r3);
        r23 = r6.booleanValue();
        r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER;
        r6 = r0.getAnnotations(r13, r3, r6);
        if (r5 == 0) goto L_0x01df;
    L_0x01ab:
        r7 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
        r17 = r2;
        r17 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r17;
        r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY;
        r10 = r10.get(r3);
        r10 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r10;
        r19 = r8.modality(r10);
        r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY;
        r3 = r10.get(r3);
        r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r3;
        r20 = r8.visibility(r3);
        r21 = r5 ^ 1;
        r24 = r2.getKind();
        r25 = 0;
        r26 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE;
        r16 = r7;
        r18 = r6;
        r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26);
        goto L_0x01e6;
    L_0x01df:
        r3 = r2;
        r3 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r3;
        r7 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultGetter(r3, r6);
    L_0x01e6:
        r3 = r2.getReturnType();
        r7.initialize(r3);
        r16 = r7;
        goto L_0x01f2;
    L_0x01f0:
        r16 = r9;
    L_0x01f2:
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_SETTER;
        r3 = r3.get(r12);
        r3 = r3.booleanValue();
        if (r3 == 0) goto L_0x02a3;
    L_0x01fe:
        r3 = r34.hasSetterFlags();
        if (r3 == 0) goto L_0x0208;
    L_0x0204:
        r4 = r34.getSetterFlags();
    L_0x0208:
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT;
        r3 = r3.get(r4);
        r3 = r3.booleanValue();
        r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR;
        r5 = r5.get(r4);
        r23 = r5.booleanValue();
        r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR;
        r5 = r5.get(r4);
        r24 = r5.booleanValue();
        r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER;
        r5 = r0.getAnnotations(r13, r4, r5);
        if (r3 == 0) goto L_0x0296;
    L_0x022e:
        r11 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
        r18 = r2;
        r18 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r18;
        r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY;
        r7 = r7.get(r4);
        r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r7;
        r20 = r6.modality(r7);
        r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE;
        r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY;
        r4 = r7.get(r4);
        r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r4;
        r21 = r6.visibility(r4);
        r22 = r3 ^ 1;
        r25 = r2.getKind();
        r26 = 0;
        r27 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE;
        r17 = r11;
        r19 = r5;
        r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27);
        r4 = r11;
        r4 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r4;
        r3 = defpackage.acob.a;
        r5 = r3;
        r5 = (java.util.List) r5;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 60;
        r17 = 0;
        r3 = r1;
        r15 = r11;
        r11 = r17;
        r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r3, r4, r5, r6, r7, r8, r9, r10, r11);
        r3 = r3.getMemberDeserializer();
        r4 = r34.getSetterValueParameter();
        r4 = java.util.Collections.singletonList(r4);
        r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER;
        r3 = r3.valueParameters(r4, r13, r5);
        r3 = defpackage.acnz.i(r3);
        r3 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r3;
        r15.initialize(r3);
        r9 = r15;
        goto L_0x02a3;
    L_0x0296:
        r13 = r2;
        r13 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r13;
        r3 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion;
        r3 = r3.getEMPTY();
        r9 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultSetter(r13, r5, r3);
    L_0x02a3:
        r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_CONSTANT;
        r3 = r3.get(r12);
        r3 = r3.booleanValue();
        if (r3 == 0) goto L_0x02c3;
    L_0x02af:
        r3 = r0.c;
        r3 = r3.getStorageManager();
        r4 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$3;
        r4.<init>(r0, r14, r2);
        r4 = (defpackage.acqq) r4;
        r3 = r3.createNullableLazyValue(r4);
        r2.setCompileTimeInitializer(r3);
    L_0x02c3:
        r3 = r9;
        r3 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor) r3;
        r4 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl;
        r5 = 0;
        r5 = r0.getPropertyFieldAnnotations(r14, r5);
        r13 = r2;
        r13 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r13;
        r4.<init>(r5, r13);
        r4 = (kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor) r4;
        r5 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl;
        r6 = 1;
        r6 = r0.getPropertyFieldAnnotations(r14, r6);
        r5.<init>(r6, r13);
        r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor) r5;
        r6 = r2;
        r6 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor) r6;
        r1 = r1.getTypeDeserializer();
        r6 = r0.checkExperimentalCoroutine(r6, r1);
        r1 = r2;
        r2 = r16;
        r1.initialize(r2, r3, r4, r5, r6);
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadProperty(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property):kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor");
    }

    private final CoroutinesCompatibilityMode checkExperimentalCoroutine(DeserializedMemberDescriptor deserializedMemberDescriptor, TypeDeserializer typeDeserializer) {
        if (!versionAndReleaseCoroutinesMismatch(deserializedMemberDescriptor)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        forceUpperBoundsComputation(typeDeserializer);
        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
            return CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        return CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final void forceUpperBoundsComputation(TypeDeserializer typeDeserializer) {
        for (TypeParameterDescriptor upperBounds : typeDeserializer.getOwnTypeParameters()) {
            upperBounds.getUpperBounds();
        }
    }

    private final void initializeWithCoroutinesExperimentalityStatus(DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor2, List<? extends TypeParameterDescriptor> list, List<? extends ValueParameterDescriptor> list2, KotlinType kotlinType, Modality modality, Visibility visibility, Map<? extends UserDataKey<?>, ?> map, boolean z) {
        deserializedSimpleFunctionDescriptor.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility, map, computeExperimentalityModeForFunctions(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor, list2, list, kotlinType, z));
    }

    private final CoroutinesCompatibilityMode computeExperimentalityModeForFunctions(DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, Collection<? extends ValueParameterDescriptor> collection, Collection<? extends TypeParameterDescriptor> collection2, KotlinType kotlinType, boolean z) {
        if (!versionAndReleaseCoroutinesMismatch(deserializedCallableMemberDescriptor)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        if (acry.a(DescriptorUtilsKt.fqNameOrNull(deserializedCallableMemberDescriptor), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        Iterable<ValueParameterDescriptor> iterable = collection;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (ValueParameterDescriptor type : iterable) {
            arrayList.add(type.getType());
        }
        List b = acnz.b((Collection) (List) arrayList, (Iterable) acnr.a(receiverParameterDescriptor != null ? receiverParameterDescriptor.getType() : null));
        if (kotlinType != null && containsSuspendFunctionType(kotlinType)) {
            return CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        Object obj;
        Object obj2;
        Iterable<TypeParameterDescriptor> iterable2 = collection2;
        if (!((iterable2 instanceof Collection) && ((Collection) iterable2).isEmpty())) {
            for (TypeParameterDescriptor upperBounds : iterable2) {
                Iterable<KotlinType> upperBounds2 = upperBounds.getUpperBounds();
                if (!((upperBounds2 instanceof Collection) && ((Collection) upperBounds2).isEmpty())) {
                    for (KotlinType containsSuspendFunctionType : upperBounds2) {
                        if (containsSuspendFunctionType(containsSuspendFunctionType)) {
                            obj = 1;
                            continue;
                            break;
                        }
                    }
                }
                obj = null;
                continue;
                if (obj != null) {
                    obj2 = 1;
                    break;
                }
            }
        }
        obj2 = null;
        if (obj2 != null) {
            return CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        Iterable<KotlinType> iterable3 = b;
        Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable3, 10));
        for (KotlinType kotlinType2 : iterable3) {
            if (FunctionTypesKt.isSuspendFunctionType(kotlinType2) && kotlinType2.getArguments().size() <= 3) {
                Iterable<TypeProjection> arguments = kotlinType2.getArguments();
                if (!((arguments instanceof Collection) && ((Collection) arguments).isEmpty())) {
                    for (TypeProjection type2 : arguments) {
                        if (containsSuspendFunctionType(type2.getType())) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj != null) {
                    obj = CoroutinesCompatibilityMode.INCOMPATIBLE;
                } else {
                    obj = CoroutinesCompatibilityMode.NEEDS_WRAPPER;
                }
            } else if (containsSuspendFunctionType(kotlinType2)) {
                obj = CoroutinesCompatibilityMode.INCOMPATIBLE;
            } else {
                obj = CoroutinesCompatibilityMode.COMPATIBLE;
            }
            arrayList2.add(obj);
        }
        CoroutinesCompatibilityMode coroutinesCompatibilityMode2 = (CoroutinesCompatibilityMode) acnz.q((List) arrayList2);
        if (coroutinesCompatibilityMode2 == null) {
            coroutinesCompatibilityMode2 = CoroutinesCompatibilityMode.COMPATIBLE;
        }
        if (z) {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.NEEDS_WRAPPER;
        } else {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
        }
        return (CoroutinesCompatibilityMode) acoz.b(coroutinesCompatibilityMode, coroutinesCompatibilityMode2);
    }

    private final boolean containsSuspendFunctionType(KotlinType kotlinType) {
        return TypeUtilsKt.contains(kotlinType, MemberDeserializer$containsSuspendFunctionType$1.INSTANCE);
    }

    private final boolean versionAndReleaseCoroutinesMismatch(DeserializedMemberDescriptor deserializedMemberDescriptor) {
        if (this.c.getComponents().getConfiguration().getReleaseCoroutines()) {
            Object obj;
            Iterable<VersionRequirement> versionRequirements = deserializedMemberDescriptor.getVersionRequirements();
            if (!((versionRequirements instanceof Collection) && ((Collection) versionRequirements).isEmpty())) {
                for (VersionRequirement versionRequirement : versionRequirements) {
                    Object obj2;
                    if (acry.a(versionRequirement.getVersion(), (Object) new Version(1, 3, 0, 4, null)) && versionRequirement.getKind() == VersionKind.LANGUAGE_VERSION) {
                        obj2 = 1;
                        continue;
                    } else {
                        obj2 = null;
                        continue;
                    }
                    if (obj2 != null) {
                        obj = null;
                        break;
                    }
                }
            }
            obj = 1;
            if (obj != null) {
                return true;
            }
        }
        return false;
    }

    public final SimpleFunctionDescriptor loadFunction(Function function) {
        Annotations receiverParameterAnnotations;
        VersionRequirementTable empty;
        ReceiverParameterDescriptor createExtensionReceiverParameterForCallable;
        ReceiverParameterDescriptor receiverParameterDescriptor;
        ReceiverParameterDescriptor dispatchReceiverParameter;
        List ownTypeParameters;
        List valueParameters;
        KotlinType type;
        Modality modality;
        Visibility visibility;
        Map a;
        boolean booleanValue;
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor;
        ReceiverParameterDescriptor receiverParameterDescriptor2;
        List list;
        KotlinType kotlinType;
        Modality modality2;
        Visibility visibility2;
        int i;
        Map map;
        Function function2;
        m deserializeContractFromFunction;
        Function function3 = function;
        int flags = function.hasFlags() ? function.getFlags() : loadOldFlags(function.getOldFlags());
        MessageLite messageLite = function3;
        Annotations annotations = getAnnotations(messageLite, flags, AnnotatedCallableKind.FUNCTION);
        if (ProtoTypeTableUtilKt.hasReceiver(function)) {
            receiverParameterAnnotations = getReceiverParameterAnnotations(messageLite, AnnotatedCallableKind.FUNCTION);
        } else {
            receiverParameterAnnotations = Annotations.Companion.getEMPTY();
        }
        if (acry.a(DescriptorUtilsKt.getFqNameSafe(r11.c.getContainingDeclaration()).child(NameResolverUtilKt.getName(r11.c.getNameResolver(), function.getName())), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            empty = VersionRequirementTable.Companion.getEMPTY();
        } else {
            empty = r11.c.getVersionRequirementTable();
        }
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor2 = new DeserializedSimpleFunctionDescriptor(r11.c.getContainingDeclaration(), null, annotations, NameResolverUtilKt.getName(r11.c.getNameResolver(), function.getName()), ProtoEnumFlags.INSTANCE.memberKind((MemberKind) Flags.MEMBER_KIND.get(flags)), function, r11.c.getNameResolver(), r11.c.getTypeTable(), empty, r11.c.getContainerSource(), null, 1024, null);
        DeserializationContext childContext$default = DeserializationContext.childContext$default(r11.c, deserializedSimpleFunctionDescriptor2, function.getTypeParameterList(), null, null, null, null, 60, null);
        Type receiverType = ProtoTypeTableUtilKt.receiverType(function3, r11.c.getTypeTable());
        if (receiverType != null) {
            KotlinType type2 = childContext$default.getTypeDeserializer().type(receiverType);
            if (type2 != null) {
                createExtensionReceiverParameterForCallable = DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedSimpleFunctionDescriptor2, type2, receiverParameterAnnotations);
                receiverParameterDescriptor = createExtensionReceiverParameterForCallable;
                dispatchReceiverParameter = getDispatchReceiverParameter();
                ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
                valueParameters = childContext$default.getMemberDeserializer().valueParameters(function.getValueParameterList(), messageLite, AnnotatedCallableKind.FUNCTION);
                type = childContext$default.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(function3, r11.c.getTypeTable()));
                modality = ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get(flags));
                visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
                a = acom.a();
                booleanValue = Flags.IS_SUSPEND.get(flags).booleanValue();
                deserializedSimpleFunctionDescriptor = deserializedSimpleFunctionDescriptor2;
                receiverParameterDescriptor2 = receiverParameterDescriptor;
                receiverParameterDescriptor = dispatchReceiverParameter;
                list = ownTypeParameters;
                ownTypeParameters = valueParameters;
                kotlinType = type;
                modality2 = modality;
                deserializedSimpleFunctionDescriptor2 = deserializedSimpleFunctionDescriptor2;
                visibility2 = visibility;
                i = flags;
                map = a;
                function2 = function3;
                initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor2, receiverParameterDescriptor, list, ownTypeParameters, kotlinType, modality2, visibility2, map, booleanValue);
                deserializedSimpleFunctionDescriptor2.setOperator(Flags.IS_OPERATOR.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setInfix(Flags.IS_INFIX.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setExternal(Flags.IS_EXTERNAL_FUNCTION.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setInline(Flags.IS_INLINE.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setTailrec(Flags.IS_TAILREC.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setSuspend(Flags.IS_SUSPEND.get(i).booleanValue());
                deserializedSimpleFunctionDescriptor2.setExpect(Flags.IS_EXPECT_FUNCTION.get(i).booleanValue());
                deserializeContractFromFunction = r11.c.getComponents().getContractDeserializer().deserializeContractFromFunction(function2, deserializedSimpleFunctionDescriptor2, r11.c.getTypeTable(), r11.c.getTypeDeserializer());
                if (deserializeContractFromFunction != null) {
                    deserializedSimpleFunctionDescriptor2.putInUserDataMap((UserDataKey) deserializeContractFromFunction.a(), deserializeContractFromFunction.b());
                }
                return deserializedSimpleFunctionDescriptor2;
            }
        }
        createExtensionReceiverParameterForCallable = null;
        receiverParameterDescriptor = createExtensionReceiverParameterForCallable;
        dispatchReceiverParameter = getDispatchReceiverParameter();
        ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
        valueParameters = childContext$default.getMemberDeserializer().valueParameters(function.getValueParameterList(), messageLite, AnnotatedCallableKind.FUNCTION);
        type = childContext$default.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(function3, r11.c.getTypeTable()));
        modality = ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get(flags));
        visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
        a = acom.a();
        booleanValue = Flags.IS_SUSPEND.get(flags).booleanValue();
        deserializedSimpleFunctionDescriptor = deserializedSimpleFunctionDescriptor2;
        receiverParameterDescriptor2 = receiverParameterDescriptor;
        receiverParameterDescriptor = dispatchReceiverParameter;
        list = ownTypeParameters;
        ownTypeParameters = valueParameters;
        kotlinType = type;
        modality2 = modality;
        deserializedSimpleFunctionDescriptor2 = deserializedSimpleFunctionDescriptor2;
        visibility2 = visibility;
        i = flags;
        map = a;
        function2 = function3;
        initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor2, receiverParameterDescriptor, list, ownTypeParameters, kotlinType, modality2, visibility2, map, booleanValue);
        deserializedSimpleFunctionDescriptor2.setOperator(Flags.IS_OPERATOR.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setInfix(Flags.IS_INFIX.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setExternal(Flags.IS_EXTERNAL_FUNCTION.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setInline(Flags.IS_INLINE.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setTailrec(Flags.IS_TAILREC.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setSuspend(Flags.IS_SUSPEND.get(i).booleanValue());
        deserializedSimpleFunctionDescriptor2.setExpect(Flags.IS_EXPECT_FUNCTION.get(i).booleanValue());
        deserializeContractFromFunction = r11.c.getComponents().getContractDeserializer().deserializeContractFromFunction(function2, deserializedSimpleFunctionDescriptor2, r11.c.getTypeTable(), r11.c.getTypeDeserializer());
        if (deserializeContractFromFunction != null) {
            deserializedSimpleFunctionDescriptor2.putInUserDataMap((UserDataKey) deserializeContractFromFunction.a(), deserializeContractFromFunction.b());
        }
        return deserializedSimpleFunctionDescriptor2;
    }

    public final TypeAliasDescriptor loadTypeAlias(TypeAlias typeAlias) {
        MemberDeserializer memberDeserializer = this;
        TypeAlias typeAlias2 = typeAlias;
        Companion companion = Annotations.Companion;
        Iterable<Annotation> annotationList = typeAlias.getAnnotationList();
        Collection arrayList = new ArrayList(acns.a((Iterable) annotationList, 10));
        for (Annotation deserializeAnnotation : annotationList) {
            arrayList.add(memberDeserializer.annotationDeserializer.deserializeAnnotation(deserializeAnnotation, memberDeserializer.c.getNameResolver()));
        }
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(memberDeserializer.c.getStorageManager(), memberDeserializer.c.getContainingDeclaration(), companion.create((List) arrayList), NameResolverUtilKt.getName(memberDeserializer.c.getNameResolver(), typeAlias.getName()), ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(typeAlias.getFlags())), typeAlias, memberDeserializer.c.getNameResolver(), memberDeserializer.c.getTypeTable(), memberDeserializer.c.getVersionRequirementTable(), memberDeserializer.c.getContainerSource());
        DeserializationContext childContext$default = DeserializationContext.childContext$default(memberDeserializer.c, deserializedTypeAliasDescriptor, typeAlias.getTypeParameterList(), null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor.initialize(childContext$default.getTypeDeserializer().getOwnTypeParameters(), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(typeAlias2, memberDeserializer.c.getTypeTable())), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(typeAlias2, memberDeserializer.c.getTypeTable())), checkExperimentalCoroutine(deserializedTypeAliasDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedTypeAliasDescriptor;
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        return classDescriptor != null ? classDescriptor.getThisAsReceiverParameter() : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final ClassConstructorDescriptor loadConstructor(Constructor constructor, boolean z) {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (containingDeclaration != null) {
            CoroutinesCompatibilityMode coroutinesCompatibilityMode;
            DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor;
            ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
            MessageLite messageLite = constructor;
            DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor2 = new DeserializedClassConstructorDescriptor(classDescriptor, null, getAnnotations(messageLite, constructor.getFlags(), AnnotatedCallableKind.FUNCTION), z, Kind.DECLARATION, constructor, r7.c.getNameResolver(), r7.c.getTypeTable(), r7.c.getVersionRequirementTable(), r7.c.getContainerSource(), null, 1024, null);
            deserializedClassConstructorDescriptor2.initialize(DeserializationContext.childContext$default(r7.c, deserializedClassConstructorDescriptor2, acob.a, null, null, null, null, 60, null).getMemberDeserializer().valueParameters(constructor.getValueParameterList(), messageLite, AnnotatedCallableKind.FUNCTION), ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(constructor.getFlags())));
            deserializedClassConstructorDescriptor2.setReturnType(classDescriptor.getDefaultType());
            containingDeclaration = r7.c.getContainingDeclaration();
            if (!(containingDeclaration instanceof DeserializedClassDescriptor)) {
                containingDeclaration = null;
            }
            DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) containingDeclaration;
            Object obj = 1;
            if (deserializedClassDescriptor != null) {
                DeserializationContext c = deserializedClassDescriptor.getC();
                if (c != null) {
                    TypeDeserializer typeDeserializer = c.getTypeDeserializer();
                    if (typeDeserializer != null) {
                        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
                        }
                    }
                }
            }
            obj = null;
            if (obj != null) {
                coroutinesCompatibilityMode = CoroutinesCompatibilityMode.INCOMPATIBLE;
                deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
            } else {
                deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
                coroutinesCompatibilityMode = computeExperimentalityModeForFunctions(deserializedClassConstructorDescriptor2, null, deserializedClassConstructorDescriptor2.getValueParameters(), deserializedClassConstructorDescriptor2.getTypeParameters(), deserializedClassConstructorDescriptor2.getReturnType(), false);
            }
            deserializedClassConstructorDescriptor.setCoroutinesExperimentalCompatibilityMode$deserialization(coroutinesCompatibilityMode);
            return deserializedClassConstructorDescriptor;
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    private final Annotations getAnnotations(MessageLite messageLite, int i, AnnotatedCallableKind annotatedCallableKind) {
        if (Flags.HAS_ANNOTATIONS.get(i).booleanValue()) {
            return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getAnnotations$1(this, messageLite, annotatedCallableKind));
        }
        return Annotations.Companion.getEMPTY();
    }

    private final Annotations getPropertyFieldAnnotations(Property property, boolean z) {
        if (Flags.HAS_ANNOTATIONS.get(property.getFlags()).booleanValue()) {
            return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getPropertyFieldAnnotations$1(this, z, property));
        }
        return Annotations.Companion.getEMPTY();
    }

    private final Annotations getReceiverParameterAnnotations(MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        return new DeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getReceiverParameterAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    private final List<ValueParameterDescriptor> valueParameters(List<ValueParameter> list, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (containingDeclaration != null) {
            CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
            ProtoContainer asProtoContainer = asProtoContainer(callableDescriptor.getContainingDeclaration());
            Iterable iterable = list;
            Collection arrayList = new ArrayList(acns.a(iterable, 10));
            int i = 0;
            for (Object next : iterable) {
                Annotations empty;
                int i2 = i + 1;
                if (i < 0) {
                    acnr.a();
                }
                ValueParameter valueParameter = (ValueParameter) next;
                int flags = valueParameter.hasFlags() ? valueParameter.getFlags() : 0;
                if (asProtoContainer == null || !Flags.HAS_ANNOTATIONS.get(flags).booleanValue()) {
                    empty = Annotations.Companion.getEMPTY();
                } else {
                    empty = new NonEmptyDeserializedAnnotations(r8.c.getStorageManager(), new MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1(i, valueParameter, this, asProtoContainer, messageLite, annotatedCallableKind, callableDescriptor));
                }
                Name name = NameResolverUtilKt.getName(r8.c.getNameResolver(), valueParameter.getName());
                KotlinType type = r8.c.getTypeDeserializer().type(ProtoTypeTableUtilKt.type(valueParameter, r8.c.getTypeTable()));
                boolean booleanValue = Flags.DECLARES_DEFAULT_VALUE.get(flags).booleanValue();
                boolean booleanValue2 = Flags.IS_CROSSINLINE.get(flags).booleanValue();
                boolean booleanValue3 = Flags.IS_NOINLINE.get(flags).booleanValue();
                Type varargElementType = ProtoTypeTableUtilKt.varargElementType(valueParameter, r8.c.getTypeTable());
                Collection collection = arrayList;
                collection.add(new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, name, type, booleanValue, booleanValue2, booleanValue3, varargElementType != null ? r8.c.getTypeDeserializer().type(varargElementType) : null, SourceElement.NO_SOURCE));
                arrayList = collection;
                i = i2;
            }
            return acnz.k((Iterable) (List) arrayList);
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
    }

    private final ProtoContainer asProtoContainer(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return new Package(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), this.c.getNameResolver(), this.c.getTypeTable(), this.c.getContainerSource());
        }
        return declarationDescriptor instanceof DeserializedClassDescriptor ? ((DeserializedClassDescriptor) declarationDescriptor).getThisAsProtoContainer$deserialization() : null;
    }
}
