package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;

public final class ClassData {
    private final Class classProto;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;
    private final SourceElement sourceElement;

    public final NameResolver component1() {
        return this.nameResolver;
    }

    public final Class component2() {
        return this.classProto;
    }

    public final BinaryVersion component3() {
        return this.metadataVersion;
    }

    public final SourceElement component4() {
        return this.sourceElement;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ClassData) {
                ClassData classData = (ClassData) obj;
                if (acry.a(this.nameResolver, classData.nameResolver)) {
                    if (acry.a(this.classProto, classData.classProto)) {
                        if (acry.a(this.metadataVersion, classData.metadataVersion)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        NameResolver nameResolver = this.nameResolver;
        int i = 0;
        int hashCode = (nameResolver != null ? nameResolver.hashCode() : 0) * 31;
        Class classR = this.classProto;
        hashCode = (hashCode + (classR != null ? classR.hashCode() : 0)) * 31;
        BinaryVersion binaryVersion = this.metadataVersion;
        hashCode = (hashCode + (binaryVersion != null ? binaryVersion.hashCode() : 0)) * 31;
        SourceElement sourceElement = this.sourceElement;
        if (sourceElement != null) {
            i = sourceElement.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("ClassData(nameResolver=");
        stringBuilder.append(this.nameResolver);
        stringBuilder.append(", classProto=");
        stringBuilder.append(this.classProto);
        stringBuilder.append(", metadataVersion=");
        stringBuilder.append(this.metadataVersion);
        stringBuilder.append(", sourceElement=");
        stringBuilder.append(this.sourceElement);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public ClassData(NameResolver nameResolver, Class classR, BinaryVersion binaryVersion, SourceElement sourceElement) {
        this.nameResolver = nameResolver;
        this.classProto = classR;
        this.metadataVersion = binaryVersion;
        this.sourceElement = sourceElement;
    }
}
