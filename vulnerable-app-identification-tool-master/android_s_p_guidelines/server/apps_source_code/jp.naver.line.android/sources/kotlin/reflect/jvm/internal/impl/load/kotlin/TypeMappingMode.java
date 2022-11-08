package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acru;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.apache.http.HttpStatus;

public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION = new TypeMappingMode(false, true, false, false, false, GENERIC_ARGUMENT, false, null, null, 476, null);
    public static final Companion Companion = new Companion();
    public static final TypeMappingMode DEFAULT = new TypeMappingMode(false, false, false, false, false, GENERIC_ARGUMENT, false, null, null, 476, null);
    public static final TypeMappingMode GENERIC_ARGUMENT = new TypeMappingMode(false, false, false, false, false, null, false, null, null, 511, null);
    public static final TypeMappingMode RETURN_TYPE_BOXED = new TypeMappingMode(false, true, false, false, false, null, false, null, null, 509, null);
    public static final TypeMappingMode SUPER_TYPE = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, 471, null);
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, null);
    public static final TypeMappingMode VALUE_FOR_ANNOTATION = new TypeMappingMode(false, false, true, false, false, new TypeMappingMode(false, false, true, false, false, GENERIC_ARGUMENT, false, null, null, 475, null), false, null, null, 472, null);
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 2;
        }
    }

    private TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    /* synthetic */ TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, int i, acru acru) {
        int i2 = i;
        boolean z7 = true;
        boolean z8 = (i2 & 1) != 0 ? true : z;
        boolean z9 = (i2 & 2) != 0 ? true : z2;
        boolean z10 = false;
        boolean z11 = (i2 & 4) != 0 ? false : z3;
        boolean z12 = (i2 & 8) != 0 ? false : z4;
        if ((i2 & 16) == 0) {
            z10 = z5;
        }
        TypeMappingMode typeMappingMode4 = (i2 & 32) != 0 ? null : typeMappingMode;
        if ((i2 & 64) == 0) {
            z7 = z6;
        }
        this(z8, z9, z11, z12, z10, typeMappingMode4, z7, (i2 & 128) != 0 ? typeMappingMode4 : typeMappingMode2, (i2 & 256) != 0 ? typeMappingMode4 : typeMappingMode3);
    }

    public final TypeMappingMode toGenericArgumentMode(Variance variance) {
        TypeMappingMode typeMappingMode;
        switch (WhenMappings.$EnumSwitchMapping$0[variance.ordinal()]) {
            case 1:
                typeMappingMode = this.genericContravariantArgumentMode;
                if (typeMappingMode == null) {
                    return this;
                }
                break;
            case 2:
                typeMappingMode = this.genericInvariantArgumentMode;
                if (typeMappingMode == null) {
                    return this;
                }
                break;
            default:
                typeMappingMode = this.genericArgumentMode;
                if (typeMappingMode == null) {
                    typeMappingMode = this;
                    break;
                }
                break;
        }
        return typeMappingMode;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode);
    }
}
