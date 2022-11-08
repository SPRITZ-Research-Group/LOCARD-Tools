package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.k;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;

public final class ProtoEnumFlags {
    public static final ProtoEnumFlags INSTANCE = new ProtoEnumFlags();

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;

        static {
            int[] iArr = new int[MemberKind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MemberKind.DECLARATION.ordinal()] = 1;
            $EnumSwitchMapping$0[MemberKind.FAKE_OVERRIDE.ordinal()] = 2;
            $EnumSwitchMapping$0[MemberKind.DELEGATION.ordinal()] = 3;
            $EnumSwitchMapping$0[MemberKind.SYNTHESIZED.ordinal()] = 4;
            iArr = new int[Kind.values().length];
            $EnumSwitchMapping$1 = iArr;
            iArr[Kind.DECLARATION.ordinal()] = 1;
            $EnumSwitchMapping$1[Kind.FAKE_OVERRIDE.ordinal()] = 2;
            $EnumSwitchMapping$1[Kind.DELEGATION.ordinal()] = 3;
            $EnumSwitchMapping$1[Kind.SYNTHESIZED.ordinal()] = 4;
            iArr = new int[Modality.values().length];
            $EnumSwitchMapping$2 = iArr;
            iArr[Modality.FINAL.ordinal()] = 1;
            $EnumSwitchMapping$2[Modality.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$2[Modality.ABSTRACT.ordinal()] = 3;
            $EnumSwitchMapping$2[Modality.SEALED.ordinal()] = 4;
            iArr = new int[kotlin.reflect.jvm.internal.impl.descriptors.Modality.values().length];
            $EnumSwitchMapping$3 = iArr;
            iArr[kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL.ordinal()] = 1;
            $EnumSwitchMapping$3[kotlin.reflect.jvm.internal.impl.descriptors.Modality.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$3[kotlin.reflect.jvm.internal.impl.descriptors.Modality.ABSTRACT.ordinal()] = 3;
            $EnumSwitchMapping$3[kotlin.reflect.jvm.internal.impl.descriptors.Modality.SEALED.ordinal()] = 4;
            iArr = new int[Visibility.values().length];
            $EnumSwitchMapping$4 = iArr;
            iArr[Visibility.INTERNAL.ordinal()] = 1;
            $EnumSwitchMapping$4[Visibility.PRIVATE.ordinal()] = 2;
            $EnumSwitchMapping$4[Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            $EnumSwitchMapping$4[Visibility.PROTECTED.ordinal()] = 4;
            $EnumSwitchMapping$4[Visibility.PUBLIC.ordinal()] = 5;
            $EnumSwitchMapping$4[Visibility.LOCAL.ordinal()] = 6;
            iArr = new int[Class.Kind.values().length];
            $EnumSwitchMapping$5 = iArr;
            iArr[Class.Kind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$5[Class.Kind.INTERFACE.ordinal()] = 2;
            $EnumSwitchMapping$5[Class.Kind.ENUM_CLASS.ordinal()] = 3;
            $EnumSwitchMapping$5[Class.Kind.ENUM_ENTRY.ordinal()] = 4;
            $EnumSwitchMapping$5[Class.Kind.ANNOTATION_CLASS.ordinal()] = 5;
            $EnumSwitchMapping$5[Class.Kind.OBJECT.ordinal()] = 6;
            $EnumSwitchMapping$5[Class.Kind.COMPANION_OBJECT.ordinal()] = 7;
            iArr = new int[ClassKind.values().length];
            $EnumSwitchMapping$6 = iArr;
            iArr[ClassKind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$6[ClassKind.INTERFACE.ordinal()] = 2;
            $EnumSwitchMapping$6[ClassKind.ENUM_CLASS.ordinal()] = 3;
            $EnumSwitchMapping$6[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            $EnumSwitchMapping$6[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            $EnumSwitchMapping$6[ClassKind.OBJECT.ordinal()] = 6;
            iArr = new int[Variance.values().length];
            $EnumSwitchMapping$7 = iArr;
            iArr[Variance.IN.ordinal()] = 1;
            $EnumSwitchMapping$7[Variance.OUT.ordinal()] = 2;
            $EnumSwitchMapping$7[Variance.INV.ordinal()] = 3;
            iArr = new int[Projection.values().length];
            $EnumSwitchMapping$8 = iArr;
            iArr[Projection.IN.ordinal()] = 1;
            $EnumSwitchMapping$8[Projection.OUT.ordinal()] = 2;
            $EnumSwitchMapping$8[Projection.INV.ordinal()] = 3;
            $EnumSwitchMapping$8[Projection.STAR.ordinal()] = 4;
        }
    }

    private ProtoEnumFlags() {
    }

    public final Kind memberKind(MemberKind memberKind) {
        if (memberKind != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[memberKind.ordinal()]) {
                case 1:
                    return Kind.DECLARATION;
                case 2:
                    return Kind.FAKE_OVERRIDE;
                case 3:
                    return Kind.DELEGATION;
                case 4:
                    return Kind.SYNTHESIZED;
            }
        }
        return Kind.DECLARATION;
    }

    public final kotlin.reflect.jvm.internal.impl.descriptors.Modality modality(Modality modality) {
        if (modality != null) {
            switch (WhenMappings.$EnumSwitchMapping$2[modality.ordinal()]) {
                case 1:
                    return kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL;
                case 2:
                    return kotlin.reflect.jvm.internal.impl.descriptors.Modality.OPEN;
                case 3:
                    return kotlin.reflect.jvm.internal.impl.descriptors.Modality.ABSTRACT;
                case 4:
                    return kotlin.reflect.jvm.internal.impl.descriptors.Modality.SEALED;
            }
        }
        return kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL;
    }

    public final kotlin.reflect.jvm.internal.impl.descriptors.Visibility visibility(Visibility visibility) {
        if (visibility != null) {
            switch (WhenMappings.$EnumSwitchMapping$4[visibility.ordinal()]) {
                case 1:
                    return Visibilities.INTERNAL;
                case 2:
                    return Visibilities.PRIVATE;
                case 3:
                    return Visibilities.PRIVATE_TO_THIS;
                case 4:
                    return Visibilities.PROTECTED;
                case 5:
                    return Visibilities.PUBLIC;
                case 6:
                    return Visibilities.LOCAL;
            }
        }
        return Visibilities.PRIVATE;
    }

    public final ClassKind classKind(Class.Kind kind) {
        if (kind != null) {
            switch (WhenMappings.$EnumSwitchMapping$5[kind.ordinal()]) {
                case 1:
                    return ClassKind.CLASS;
                case 2:
                    return ClassKind.INTERFACE;
                case 3:
                    return ClassKind.ENUM_CLASS;
                case 4:
                    return ClassKind.ENUM_ENTRY;
                case 5:
                    return ClassKind.ANNOTATION_CLASS;
                case 6:
                case 7:
                    return ClassKind.OBJECT;
            }
        }
        return ClassKind.CLASS;
    }

    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(Variance variance) {
        switch (WhenMappings.$EnumSwitchMapping$7[variance.ordinal()]) {
            case 1:
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            case 2:
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            case 3:
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            default:
                throw new k();
        }
    }

    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(Projection projection) {
        switch (WhenMappings.$EnumSwitchMapping$8[projection.ordinal()]) {
            case 1:
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            case 2:
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            case 3:
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            case 4:
                throw new IllegalArgumentException("Only IN, OUT and INV are supported. Actual argument: ".concat(String.valueOf(projection)));
            default:
                throw new k();
        }
    }
}
