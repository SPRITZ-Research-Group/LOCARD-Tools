package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acru;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public abstract class ProtoContainer {
    private final NameResolver nameResolver;
    private final SourceElement source;
    private final TypeTable typeTable;

    public final class Class extends ProtoContainer {
        private final ClassId classId;
        private final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class classProto;
        private final boolean isInner;
        private final Kind kind;
        private final Class outerClass;

        public final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class getClassProto() {
            return this.classProto;
        }

        public final Class getOuterClass() {
            return this.outerClass;
        }

        public Class(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class classR, NameResolver nameResolver, TypeTable typeTable, SourceElement sourceElement, Class classR2) {
            super(nameResolver, typeTable, sourceElement, null);
            this.classProto = classR;
            this.outerClass = classR2;
            this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
            Kind kind = (Kind) Flags.CLASS_KIND.get(this.classProto.getFlags());
            if (kind == null) {
                kind = Kind.CLASS;
            }
            this.kind = kind;
            this.isInner = Flags.IS_INNER.get(this.classProto.getFlags()).booleanValue();
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final Kind getKind() {
            return this.kind;
        }

        public final boolean isInner() {
            return this.isInner;
        }

        public final FqName debugFqName() {
            return this.classId.asSingleFqName();
        }
    }

    public final class Package extends ProtoContainer {
        private final FqName fqName;

        public Package(FqName fqName, NameResolver nameResolver, TypeTable typeTable, SourceElement sourceElement) {
            super(nameResolver, typeTable, sourceElement, null);
            this.fqName = fqName;
        }

        public final FqName debugFqName() {
            return this.fqName;
        }
    }

    public abstract FqName debugFqName();

    private ProtoContainer(NameResolver nameResolver, TypeTable typeTable, SourceElement sourceElement) {
        this.nameResolver = nameResolver;
        this.typeTable = typeTable;
        this.source = sourceElement;
    }

    public /* synthetic */ ProtoContainer(NameResolver nameResolver, TypeTable typeTable, SourceElement sourceElement, acru acru) {
        this(nameResolver, typeTable, sourceElement);
    }

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    public final SourceElement getSource() {
        return this.source;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(": ");
        stringBuilder.append(debugFqName());
        return stringBuilder.toString();
    }
}
