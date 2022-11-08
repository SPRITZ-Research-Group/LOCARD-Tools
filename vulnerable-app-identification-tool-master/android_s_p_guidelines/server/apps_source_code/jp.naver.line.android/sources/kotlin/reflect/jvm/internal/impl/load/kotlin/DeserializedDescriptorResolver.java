package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acot;
import defpackage.acru;
import defpackage.acry;
import java.util.Collections;
import java.util.Set;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;

public final class DeserializedDescriptorResolver {
    public static final Companion Companion = new Companion();
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(1, 1, 2);
    private static final JvmMetadataVersion KOTLIN_1_3_M1_METADATA_VERSION = new JvmMetadataVersion(1, 1, 11);
    private static final JvmMetadataVersion KOTLIN_1_3_RC_METADATA_VERSION = new JvmMetadataVersion(1, 1, 13);
    private static final Set<Kind> KOTLIN_CLASS = Collections.singleton(Kind.CLASS);
    private static final Set<Kind> KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = acot.a((Object[]) new Kind[]{Kind.FILE_FACADE, Kind.MULTIFILE_CLASS_PART});
    public DeserializationComponents components;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final JvmMetadataVersion getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm() {
            return DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION;
        }
    }

    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return deserializationComponents;
    }

    public final void setComponents(DeserializationComponentsForJava deserializationComponentsForJava) {
        this.components = deserializationComponentsForJava.getComponents();
    }

    private final boolean getSkipMetadataVersionCheck() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return deserializationComponents.getConfiguration().getSkipMetadataVersionCheck();
    }

    public final ClassDescriptor resolveClass(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        ClassData readClassData$descriptors_jvm = readClassData$descriptors_jvm(kotlinJvmBinaryClass);
        if (readClassData$descriptors_jvm == null) {
            return null;
        }
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return deserializationComponents.getClassDeserializer().deserializeClass(kotlinJvmBinaryClass.getClassId(), readClassData$descriptors_jvm);
    }

    public final ClassData readClassData$descriptors_jvm(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        String[] readData$descriptors_jvm = readData$descriptors_jvm(kotlinJvmBinaryClass, KOTLIN_CLASS);
        if (readData$descriptors_jvm == null) {
            return null;
        }
        String[] strings = kotlinJvmBinaryClass.getClassHeader().getStrings();
        if (strings == null) {
            return null;
        }
        m readClassDataFrom;
        try {
            readClassDataFrom = JvmProtoBufUtil.readClassDataFrom(readData$descriptors_jvm, strings);
        } catch (InvalidProtocolBufferException e) {
            StringBuilder stringBuilder = new StringBuilder("Could not read data from ");
            stringBuilder.append(kotlinJvmBinaryClass.getLocation());
            throw new IllegalStateException(stringBuilder.toString(), e);
        } catch (Throwable th) {
            if (!getSkipMetadataVersionCheck() && !kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
                readClassDataFrom = null;
            }
        }
        if (readClassDataFrom == null) {
            return null;
        }
        JvmNameResolver jvmNameResolver = (JvmNameResolver) readClassDataFrom.c();
        return new ClassData(jvmNameResolver, (Class) readClassDataFrom.d(), kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), new KotlinJvmBinarySourceElement(kotlinJvmBinaryClass, getIncompatibility(kotlinJvmBinaryClass), isPreReleaseInvisible(kotlinJvmBinaryClass)));
    }

    public final MemberScope createKotlinPackagePartScope(PackageFragmentDescriptor packageFragmentDescriptor, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        String[] readData$descriptors_jvm = readData$descriptors_jvm(kotlinJvmBinaryClass, KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if (readData$descriptors_jvm == null) {
            return null;
        }
        String[] strings = kotlinJvmBinaryClass.getClassHeader().getStrings();
        if (strings == null) {
            return null;
        }
        m readPackageDataFrom;
        try {
            readPackageDataFrom = JvmProtoBufUtil.readPackageDataFrom(readData$descriptors_jvm, strings);
        } catch (InvalidProtocolBufferException e) {
            StringBuilder stringBuilder = new StringBuilder("Could not read data from ");
            stringBuilder.append(kotlinJvmBinaryClass.getLocation());
            throw new IllegalStateException(stringBuilder.toString(), e);
        } catch (Throwable th) {
            if (!getSkipMetadataVersionCheck() && !kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
                readPackageDataFrom = null;
            }
        }
        if (readPackageDataFrom == null) {
            return null;
        }
        JvmNameResolver jvmNameResolver = (JvmNameResolver) readPackageDataFrom.c();
        Package packageR = (Package) readPackageDataFrom.d();
        NameResolver nameResolver = jvmNameResolver;
        BinaryVersion metadataVersion = kotlinJvmBinaryClass.getClassHeader().getMetadataVersion();
        DeserializedContainerSource jvmPackagePartSource = new JvmPackagePartSource(kotlinJvmBinaryClass, packageR, nameResolver, getIncompatibility(kotlinJvmBinaryClass), isPreReleaseInvisible(kotlinJvmBinaryClass));
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return new DeserializedPackageMemberScope(packageFragmentDescriptor, packageR, nameResolver, metadataVersion, jvmPackagePartSource, deserializationComponents, DeserializedDescriptorResolver$createKotlinPackagePartScope$2.INSTANCE);
    }

    private final IncompatibleVersionErrorData<JvmMetadataVersion> getIncompatibility(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        return (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) ? null : new IncompatibleVersionErrorData(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), JvmMetadataVersion.INSTANCE, kotlinJvmBinaryClass.getLocation(), kotlinJvmBinaryClass.getClassId());
    }

    private final boolean isPreReleaseInvisible(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return (deserializationComponents.getConfiguration().getReportErrorsOnPreReleaseDependencies() && (kotlinJvmBinaryClass.getClassHeader().isPreRelease() || acry.a(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), KOTLIN_1_1_EAP_METADATA_VERSION))) || isCompiledWith13M1(kotlinJvmBinaryClass);
    }

    private final boolean isCompiledWith13M1(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            acry.a("components");
        }
        return !deserializationComponents.getConfiguration().getSkipMetadataVersionCheck() && kotlinJvmBinaryClass.getClassHeader().isPreRelease() && acry.a(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), KOTLIN_1_3_M1_METADATA_VERSION);
    }

    public final String[] readData$descriptors_jvm(KotlinJvmBinaryClass kotlinJvmBinaryClass, Set<? extends Kind> set) {
        KotlinClassHeader classHeader = kotlinJvmBinaryClass.getClassHeader();
        String[] data = classHeader.getData();
        if (data == null) {
            data = classHeader.getIncompatibleData();
        }
        return (data == null || !set.contains(classHeader.getKind())) ? null : data;
    }
}
