package kotlin.reflect.jvm.internal.impl.load.java.components;

import defpackage.acqr;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.y;

public final class DescriptorResolverUtils {
    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForNonStaticMembers(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, ErrorReporter errorReporter) {
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, false);
    }

    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForStaticMembers(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, ErrorReporter errorReporter) {
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, true);
    }

    private static <D extends CallableMemberDescriptor> Collection<D> resolveOverrides(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, final ErrorReporter errorReporter, final boolean z) {
        final Collection linkedHashSet = new LinkedHashSet();
        OverridingUtil.generateOverridesInFunctionGroup(name, collection, collection2, classDescriptor, new NonReportingOverrideStrategy() {
            public final void conflict(CallableMemberDescriptor callableMemberDescriptor, CallableMemberDescriptor callableMemberDescriptor2) {
            }

            public final void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor) {
                OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor, new acqr<CallableMemberDescriptor, y>() {
                    public y invoke(CallableMemberDescriptor callableMemberDescriptor) {
                        errorReporter.reportCannotInferVisibility(callableMemberDescriptor);
                        return y.a;
                    }
                });
                linkedHashSet.add(callableMemberDescriptor);
            }

            public final void setOverriddenDescriptors(CallableMemberDescriptor callableMemberDescriptor, Collection<? extends CallableMemberDescriptor> collection) {
                if (!z || callableMemberDescriptor.getKind() == Kind.FAKE_OVERRIDE) {
                    super.setOverriddenDescriptors(callableMemberDescriptor, collection);
                }
            }
        });
        return linkedHashSet;
    }

    public static ValueParameterDescriptor getAnnotationParameterByName(Name name, ClassDescriptor classDescriptor) {
        Collection constructors = classDescriptor.getConstructors();
        if (constructors.size() != 1) {
            return null;
        }
        for (ValueParameterDescriptor valueParameterDescriptor : ((ClassConstructorDescriptor) constructors.iterator().next()).getValueParameters()) {
            if (valueParameterDescriptor.getName().equals(name)) {
                return valueParameterDescriptor;
            }
        }
        return null;
    }

    public static boolean isObjectMethodInInterface(JavaMember javaMember) {
        return javaMember.getContainingClass().isInterface() && (javaMember instanceof JavaMethod) && isObjectMethod((JavaMethod) javaMember);
    }

    public static boolean isObjectMethod(JavaMethod javaMethod) {
        String asString = javaMethod.getName().asString();
        if (asString.equals("toString") || asString.equals("hashCode")) {
            return javaMethod.getValueParameters().isEmpty();
        }
        return asString.equals("equals") ? isMethodWithOneParameterWithFqName(javaMethod, "java.lang.Object") : false;
    }

    private static boolean isMethodWithOneParameterWithFqName(JavaMethod javaMethod, String str) {
        List valueParameters = javaMethod.getValueParameters();
        if (valueParameters.size() == 1) {
            JavaType type = ((JavaValueParameter) valueParameters.get(0)).getType();
            if (type instanceof JavaClassifierType) {
                JavaClassifier classifier = ((JavaClassifierType) type).getClassifier();
                if (classifier instanceof JavaClass) {
                    FqName fqName = ((JavaClass) classifier).getFqName();
                    return fqName != null && fqName.asString().equals(str);
                }
            }
        }
        return false;
    }
}
