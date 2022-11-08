package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acob;
import defpackage.acqr;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.DefaultBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl;

public class ErrorUtils {
    private static final ErrorClassDescriptor ERROR_CLASS = new ErrorClassDescriptor(Name.special("<ERROR CLASS>"));
    private static final ModuleDescriptor ERROR_MODULE = new ModuleDescriptor() {
        public final <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
            return null;
        }

        public final DeclarationDescriptor getContainingDeclaration() {
            return null;
        }

        public final DeclarationDescriptor getOriginal() {
            return this;
        }

        public final boolean shouldSeeInternalsOf(ModuleDescriptor moduleDescriptor) {
            return false;
        }

        public final Annotations getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        public final Name getName() {
            return Name.special("<ERROR MODULE>");
        }

        public final PackageViewDescriptor getPackage(FqName fqName) {
            throw new IllegalStateException("Should not be called!");
        }

        public final KotlinBuiltIns getBuiltIns() {
            return DefaultBuiltIns.getInstance();
        }

        public final Collection<FqName> getSubPackagesOf(FqName fqName, acqr<? super Name, Boolean> acqr) {
            return acob.a;
        }
    };
    private static final PropertyDescriptor ERROR_PROPERTY;
    private static final Set<PropertyDescriptor> ERROR_PROPERTY_GROUP;
    private static final KotlinType ERROR_PROPERTY_TYPE = createErrorType("<ERROR PROPERTY TYPE>");
    public static final SimpleType ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES = createErrorType("<LOOP IN SUPERTYPES>");

    public class UninferredParameterTypeConstructor implements TypeConstructor {
        private final TypeConstructor errorTypeConstructor;
        private final TypeParameterDescriptor typeParameterDescriptor;

        public TypeParameterDescriptor getTypeParameterDescriptor() {
            return this.typeParameterDescriptor;
        }

        public List<TypeParameterDescriptor> getParameters() {
            return this.errorTypeConstructor.getParameters();
        }

        public Collection<KotlinType> getSupertypes() {
            return this.errorTypeConstructor.getSupertypes();
        }

        public boolean isDenotable() {
            return this.errorTypeConstructor.isDenotable();
        }

        public ClassifierDescriptor getDeclarationDescriptor() {
            return this.errorTypeConstructor.getDeclarationDescriptor();
        }

        public KotlinBuiltIns getBuiltIns() {
            return DescriptorUtilsKt.getBuiltIns(this.typeParameterDescriptor);
        }
    }

    public class ErrorScope implements MemberScope {
        private final String debugMessage;

        /* synthetic */ ErrorScope(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private ErrorScope(String str) {
            this.debugMessage = str;
        }

        public ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation) {
            return ErrorUtils.createErrorClass(name.asString());
        }

        public Set<? extends PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
            return ErrorUtils.ERROR_PROPERTY_GROUP;
        }

        public Set<? extends SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
            return Collections.singleton(ErrorUtils.createErrorFunction(this));
        }

        public Set<Name> getFunctionNames() {
            return Collections.emptySet();
        }

        public Set<Name> getVariableNames() {
            return Collections.emptySet();
        }

        public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, acqr<? super Name, Boolean> acqr) {
            return Collections.emptyList();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ErrorScope{");
            stringBuilder.append(this.debugMessage);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    class ThrowingScope implements MemberScope {
        private final String debugMessage;

        /* synthetic */ ThrowingScope(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private ThrowingScope(String str) {
            this.debugMessage = str;
        }

        public ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.debugMessage);
            stringBuilder.append(", required name: ");
            stringBuilder.append(name);
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Collection<? extends PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.debugMessage);
            stringBuilder.append(", required name: ");
            stringBuilder.append(name);
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Collection<? extends SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.debugMessage);
            stringBuilder.append(", required name: ");
            stringBuilder.append(name);
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, acqr<? super Name, Boolean> acqr) {
            throw new IllegalStateException(this.debugMessage);
        }

        public Set<Name> getFunctionNames() {
            throw new IllegalStateException();
        }

        public Set<Name> getVariableNames() {
            throw new IllegalStateException();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ThrowingScope{");
            stringBuilder.append(this.debugMessage);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    class ErrorClassDescriptor extends ClassDescriptorImpl {
        public ClassDescriptor substitute(TypeSubstitutor typeSubstitutor) {
            return this;
        }

        public ErrorClassDescriptor(Name name) {
            super(ErrorUtils.getErrorModule(), name, Modality.OPEN, ClassKind.CLASS, Collections.emptyList(), SourceElement.NO_SOURCE, false, LockBasedStorageManager.NO_LOCKS);
            ClassConstructorDescriptor create = ClassConstructorDescriptorImpl.create(this, Annotations.Companion.getEMPTY(), true, SourceElement.NO_SOURCE);
            create.initialize(Collections.emptyList(), Visibilities.INTERNAL);
            MemberScope createErrorScope = ErrorUtils.createErrorScope(getName().asString());
            create.setReturnType(new ErrorType(ErrorUtils.createErrorTypeConstructorWithCustomDebugName("<ERROR>", this), createErrorScope));
            initialize(createErrorScope, Collections.singleton(create), create);
        }

        public String toString() {
            return getName().asString();
        }

        public MemberScope getMemberScope(TypeSubstitution typeSubstitution) {
            StringBuilder stringBuilder = new StringBuilder("Error scope for class ");
            stringBuilder.append(getName());
            stringBuilder.append(" with arguments: ");
            stringBuilder.append(typeSubstitution);
            return ErrorUtils.createErrorScope(stringBuilder.toString());
        }
    }

    static {
        PropertyDescriptor createErrorProperty = createErrorProperty();
        ERROR_PROPERTY = createErrorProperty;
        ERROR_PROPERTY_GROUP = Collections.singleton(createErrorProperty);
    }

    public static ClassDescriptor createErrorClass(String str) {
        StringBuilder stringBuilder = new StringBuilder("<ERROR CLASS: ");
        stringBuilder.append(str);
        stringBuilder.append(">");
        return new ErrorClassDescriptor(Name.special(stringBuilder.toString()));
    }

    public static MemberScope createErrorScope(String str) {
        return createErrorScope(str, false);
    }

    public static MemberScope createErrorScope(String str, boolean z) {
        if (z) {
            return new ThrowingScope(str, null);
        }
        return new ErrorScope(str, null);
    }

    private static PropertyDescriptorImpl createErrorProperty() {
        PropertyDescriptorImpl create = PropertyDescriptorImpl.create(ERROR_CLASS, Annotations.Companion.getEMPTY(), Modality.OPEN, Visibilities.PUBLIC, true, Name.special("<ERROR PROPERTY>"), Kind.DECLARATION, SourceElement.NO_SOURCE, false, false, false, false, false, false);
        create.setType(ERROR_PROPERTY_TYPE, Collections.emptyList(), null, null);
        return create;
    }

    private static SimpleFunctionDescriptor createErrorFunction(ErrorScope errorScope) {
        SimpleFunctionDescriptor errorSimpleFunctionDescriptorImpl = new ErrorSimpleFunctionDescriptorImpl(ERROR_CLASS, errorScope);
        errorSimpleFunctionDescriptorImpl.initialize(null, null, Collections.emptyList(), Collections.emptyList(), createErrorType("<ERROR FUNCTION RETURN TYPE>"), Modality.OPEN, Visibilities.PUBLIC);
        return errorSimpleFunctionDescriptorImpl;
    }

    public static SimpleType createErrorType(String str) {
        return createErrorTypeWithArguments(str, Collections.emptyList());
    }

    public static SimpleType createErrorTypeWithCustomDebugName(String str) {
        return createErrorTypeWithCustomConstructor(str, createErrorTypeConstructorWithCustomDebugName(str));
    }

    public static SimpleType createErrorTypeWithCustomConstructor(String str, TypeConstructor typeConstructor) {
        return new ErrorType(typeConstructor, createErrorScope(str));
    }

    public static SimpleType createErrorTypeWithArguments(String str, List<TypeProjection> list) {
        return new ErrorType(createErrorTypeConstructor(str), createErrorScope(str), list, false);
    }

    public static TypeConstructor createErrorTypeConstructor(String str) {
        StringBuilder stringBuilder = new StringBuilder("[ERROR : ");
        stringBuilder.append(str);
        stringBuilder.append("]");
        return createErrorTypeConstructorWithCustomDebugName(stringBuilder.toString(), ERROR_CLASS);
    }

    public static TypeConstructor createErrorTypeConstructorWithCustomDebugName(String str) {
        return createErrorTypeConstructorWithCustomDebugName(str, ERROR_CLASS);
    }

    private static TypeConstructor createErrorTypeConstructorWithCustomDebugName(final String str, final ErrorClassDescriptor errorClassDescriptor) {
        return new TypeConstructor() {
            public final boolean isDenotable() {
                return false;
            }

            public final ClassifierDescriptor getDeclarationDescriptor() {
                return errorClassDescriptor;
            }

            public final KotlinBuiltIns getBuiltIns() {
                return DefaultBuiltIns.getInstance();
            }

            public final String toString() {
                return str;
            }

            public final List<TypeParameterDescriptor> getParameters() {
                return acob.a;
            }

            public final Collection<KotlinType> getSupertypes() {
                return acob.a;
            }
        };
    }

    public static boolean isError(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            return false;
        }
        return isErrorClass(declarationDescriptor) || isErrorClass(declarationDescriptor.getContainingDeclaration()) || declarationDescriptor == ERROR_MODULE;
    }

    private static boolean isErrorClass(DeclarationDescriptor declarationDescriptor) {
        return declarationDescriptor instanceof ErrorClassDescriptor;
    }

    public static ModuleDescriptor getErrorModule() {
        return ERROR_MODULE;
    }

    public static boolean isUninferredParameter(KotlinType kotlinType) {
        return kotlinType != null && (kotlinType.getConstructor() instanceof UninferredParameterTypeConstructor);
    }
}
