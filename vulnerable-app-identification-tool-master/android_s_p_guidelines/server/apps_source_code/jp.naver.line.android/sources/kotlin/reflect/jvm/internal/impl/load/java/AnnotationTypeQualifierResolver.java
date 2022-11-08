package kotlin.reflect.jvm.internal.impl.load.java;

import defpackage.acnr;
import defpackage.acnw;
import defpackage.acob;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

public final class AnnotationTypeQualifierResolver {
    private final boolean disabled = this.jsr305State.getDisabled();
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    public enum QualifierApplicabilityType {
    }

    public final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor annotationDescriptor, int i) {
            this.typeQualifier = annotationDescriptor;
            this.applicability = i;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            Collection arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return (List) arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State) {
        this.jsr305State = jsr305State;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
    }

    private final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_NICKNAME_FQNAME)) {
            return null;
        }
        for (AnnotationDescriptor resolveTypeQualifierAnnotation : classDescriptor.getAnnotations()) {
            AnnotationDescriptor resolveTypeQualifierAnnotation2 = resolveTypeQualifierAnnotation(resolveTypeQualifierAnnotation2);
            if (resolveTypeQualifierAnnotation2 != null) {
                return resolveTypeQualifierAnnotation2;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return (AnnotationDescriptor) this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null) {
            return null;
        }
        if (AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass)) {
            return annotationDescriptor;
        }
        return resolveTypeQualifierNickname(annotationClass);
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) AnnotationTypeQualifierResolverKt.BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS.get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
        Collection component2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if ((resolveJsr305AnnotationState != ReportLevel.IGNORE ? 1 : null) == null) {
            resolveJsr305AnnotationState = null;
        }
        if (resolveJsr305AnnotationState == null) {
            return null;
        }
        return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, null, resolveJsr305AnnotationState.isWarning(), 1, null), component2);
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME)) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                Object obj;
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    acry.a();
                }
                annotationDescriptor = annotationClass2.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.TYPE_QUALIFIER_DEFAULT_FQNAME);
                if (annotationDescriptor == null) {
                    acry.a();
                }
                Collection arrayList = new ArrayList();
                for (Entry entry : annotationDescriptor.getAllValueArguments().entrySet()) {
                    List mapConstantToQualifierApplicabilityTypes;
                    obj = (Name) entry.getKey();
                    ConstantValue constantValue = (ConstantValue) entry.getValue();
                    if (acry.a(obj, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        mapConstantToQualifierApplicabilityTypes = mapConstantToQualifierApplicabilityTypes(constantValue);
                    } else {
                        mapConstantToQualifierApplicabilityTypes = acob.a;
                    }
                    acnw.a(arrayList, (Iterable) mapConstantToQualifierApplicabilityTypes);
                }
                int i = 0;
                for (QualifierApplicabilityType ordinal : (List) arrayList) {
                    i |= 1 << ordinal.ordinal();
                }
                for (Object next : annotationClass.getAnnotations()) {
                    if (resolveTypeQualifierAnnotation((AnnotationDescriptor) next) != null) {
                        obj = 1;
                        continue;
                    } else {
                        obj = null;
                        continue;
                    }
                    if (obj != null) {
                        break;
                    }
                }
                Object next2 = null;
                AnnotationDescriptor annotationDescriptor2 = (AnnotationDescriptor) next2;
                if (annotationDescriptor2 == null) {
                    return null;
                }
                return new TypeQualifierWithApplicability(annotationDescriptor2, i);
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState != null) {
            return resolveJsr305CustomState;
        }
        return this.jsr305State.getGlobal();
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Map user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = (ReportLevel) user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor findAnnotation = classDescriptor.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.MIGRATION_ANNOTATION_FQNAME);
        ConstantValue firstArgument = findAnnotation != null ? DescriptorUtilsKt.firstArgument(findAnnotation) : null;
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode != -2137067054) {
            if (hashCode != -1838656823) {
                if (hashCode == 2656902 && asString.equals("WARN")) {
                    return ReportLevel.WARN;
                }
            } else if (asString.equals("STRICT")) {
                return ReportLevel.STRICT;
            }
        } else if (asString.equals("IGNORE")) {
            return ReportLevel.IGNORE;
        }
        return null;
    }

    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        if (constantValue instanceof ArrayValue) {
            Collection arrayList = new ArrayList();
            for (ConstantValue mapConstantToQualifierApplicabilityTypes : (Iterable) ((ArrayValue) constantValue).getValue()) {
                acnw.a(arrayList, (Iterable) mapConstantToQualifierApplicabilityTypes(mapConstantToQualifierApplicabilityTypes));
            }
            return (List) arrayList;
        } else if (!(constantValue instanceof EnumValue)) {
            return acob.a;
        } else {
            Object obj;
            String identifier = ((EnumValue) constantValue).getEnumEntryName().getIdentifier();
            int hashCode = identifier.hashCode();
            if (hashCode != -2024225567) {
                if (hashCode != 66889946) {
                    if (hashCode != 107598562) {
                        if (hashCode == 446088073 && identifier.equals("PARAMETER")) {
                            obj = QualifierApplicabilityType.VALUE_PARAMETER;
                            return acnr.a(obj);
                        }
                    } else if (identifier.equals("TYPE_USE")) {
                        obj = QualifierApplicabilityType.TYPE_USE;
                        return acnr.a(obj);
                    }
                } else if (identifier.equals("FIELD")) {
                    obj = QualifierApplicabilityType.FIELD;
                    return acnr.a(obj);
                }
            } else if (identifier.equals("METHOD")) {
                obj = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                return acnr.a(obj);
            }
            obj = null;
            return acnr.a(obj);
        }
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}
