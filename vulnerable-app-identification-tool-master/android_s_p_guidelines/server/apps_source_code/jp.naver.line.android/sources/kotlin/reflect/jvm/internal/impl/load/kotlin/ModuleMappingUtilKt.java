package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acqr;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.Companion;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.y;

public final class ModuleMappingUtilKt {
    public static final ModuleMapping loadModuleMapping(Companion companion, byte[] bArr, String str, DeserializationConfiguration deserializationConfiguration, acqr<? super JvmMetadataVersion, y> acqr) {
        return companion.loadModuleMapping(bArr, str, deserializationConfiguration.getSkipMetadataVersionCheck(), deserializationConfiguration.isJvmPackageNameSupported(), acqr);
    }
}
