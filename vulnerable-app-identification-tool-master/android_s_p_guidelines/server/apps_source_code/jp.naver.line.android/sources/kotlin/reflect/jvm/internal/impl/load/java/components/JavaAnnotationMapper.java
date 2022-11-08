package kotlin.reflect.jvm.internal.impl.load.java.components;

import defpackage.acom;
import defpackage.acry;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import jp.naver.android.npush.common.NPushIntent;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.u;

public final class JavaAnnotationMapper {
    private static final Name DEPRECATED_ANNOTATION_MESSAGE = Name.identifier(NPushIntent.PARAM_MESSAGE);
    public static final JavaAnnotationMapper INSTANCE = new JavaAnnotationMapper();
    private static final FqName JAVA_DEPRECATED_FQ_NAME = new FqName(Deprecated.class.getCanonicalName());
    private static final FqName JAVA_DOCUMENTED_FQ_NAME = new FqName(Documented.class.getCanonicalName());
    private static final FqName JAVA_REPEATABLE_FQ_NAME = new FqName("java.lang.annotation.Repeatable");
    private static final FqName JAVA_RETENTION_FQ_NAME = new FqName(Retention.class.getCanonicalName());
    private static final FqName JAVA_TARGET_FQ_NAME = new FqName(Target.class.getCanonicalName());
    private static final Name RETENTION_ANNOTATION_VALUE = Name.identifier("value");
    private static final Name TARGET_ANNOTATION_ALLOWED_TARGETS = Name.identifier("allowedTargets");
    private static final Map<FqName, FqName> javaToKotlinNameMap = acom.a(u.a(JAVA_TARGET_FQ_NAME, KotlinBuiltIns.FQ_NAMES.target), u.a(JAVA_RETENTION_FQ_NAME, KotlinBuiltIns.FQ_NAMES.retention), u.a(JAVA_DEPRECATED_FQ_NAME, KotlinBuiltIns.FQ_NAMES.deprecated), u.a(JAVA_REPEATABLE_FQ_NAME, KotlinBuiltIns.FQ_NAMES.repeatable), u.a(JAVA_DOCUMENTED_FQ_NAME, KotlinBuiltIns.FQ_NAMES.mustBeDocumented));
    private static final Map<FqName, FqName> kotlinToJavaNameMap = acom.a(u.a(KotlinBuiltIns.FQ_NAMES.target, JAVA_TARGET_FQ_NAME), u.a(KotlinBuiltIns.FQ_NAMES.retention, JAVA_RETENTION_FQ_NAME), u.a(KotlinBuiltIns.FQ_NAMES.repeatable, JAVA_REPEATABLE_FQ_NAME), u.a(KotlinBuiltIns.FQ_NAMES.mustBeDocumented, JAVA_DOCUMENTED_FQ_NAME));

    private JavaAnnotationMapper() {
    }

    public final Name getDEPRECATED_ANNOTATION_MESSAGE$descriptors_jvm() {
        return DEPRECATED_ANNOTATION_MESSAGE;
    }

    public final Name getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm() {
        return TARGET_ANNOTATION_ALLOWED_TARGETS;
    }

    public final Name getRETENTION_ANNOTATION_VALUE$descriptors_jvm() {
        return RETENTION_ANNOTATION_VALUE;
    }

    public final AnnotationDescriptor mapOrResolveJavaAnnotation(JavaAnnotation javaAnnotation, LazyJavaResolverContext lazyJavaResolverContext) {
        Object classId = javaAnnotation.getClassId();
        if (acry.a(classId, ClassId.topLevel(JAVA_TARGET_FQ_NAME))) {
            return new JavaTargetAnnotationDescriptor(javaAnnotation, lazyJavaResolverContext);
        }
        if (acry.a(classId, ClassId.topLevel(JAVA_RETENTION_FQ_NAME))) {
            return new JavaRetentionAnnotationDescriptor(javaAnnotation, lazyJavaResolverContext);
        }
        if (acry.a(classId, ClassId.topLevel(JAVA_REPEATABLE_FQ_NAME))) {
            return new JavaAnnotationDescriptor(lazyJavaResolverContext, javaAnnotation, KotlinBuiltIns.FQ_NAMES.repeatable);
        }
        if (acry.a(classId, ClassId.topLevel(JAVA_DOCUMENTED_FQ_NAME))) {
            return new JavaAnnotationDescriptor(lazyJavaResolverContext, javaAnnotation, KotlinBuiltIns.FQ_NAMES.mustBeDocumented);
        }
        if (acry.a(classId, ClassId.topLevel(JAVA_DEPRECATED_FQ_NAME))) {
            return null;
        }
        return new LazyJavaAnnotationDescriptor(lazyJavaResolverContext, javaAnnotation);
    }

    public final AnnotationDescriptor findMappedJavaAnnotation(FqName fqName, JavaAnnotationOwner javaAnnotationOwner, LazyJavaResolverContext lazyJavaResolverContext) {
        if (acry.a((Object) fqName, KotlinBuiltIns.FQ_NAMES.deprecated)) {
            JavaAnnotation findAnnotation = javaAnnotationOwner.findAnnotation(JAVA_DEPRECATED_FQ_NAME);
            if (findAnnotation != null || javaAnnotationOwner.isDeprecatedInJavaDoc()) {
                return new JavaDeprecatedAnnotationDescriptor(findAnnotation, lazyJavaResolverContext);
            }
        }
        fqName = (FqName) kotlinToJavaNameMap.get(fqName);
        if (fqName != null) {
            JavaAnnotation findAnnotation2 = javaAnnotationOwner.findAnnotation(fqName);
            if (findAnnotation2 != null) {
                return INSTANCE.mapOrResolveJavaAnnotation(findAnnotation2, lazyJavaResolverContext);
            }
        }
        return null;
    }
}
