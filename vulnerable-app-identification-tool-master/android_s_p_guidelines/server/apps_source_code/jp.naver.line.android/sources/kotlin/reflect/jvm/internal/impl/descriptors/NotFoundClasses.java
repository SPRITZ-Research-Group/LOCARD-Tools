package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acns;
import defpackage.acob;
import defpackage.acod;
import defpackage.acoh;
import defpackage.acry;
import defpackage.actx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class NotFoundClasses {
    private final MemoizedFunctionToNotNull<ClassRequest, ClassDescriptor> classes = this.storageManager.createMemoizedFunction(new NotFoundClasses$classes$1(this));
    private final ModuleDescriptor module;
    private final MemoizedFunctionToNotNull<FqName, PackageFragmentDescriptor> packageFragments = this.storageManager.createMemoizedFunction(new NotFoundClasses$packageFragments$1(this));
    private final StorageManager storageManager;

    final class ClassRequest {
        private final ClassId classId;
        private final List<Integer> typeParametersCount;

        public final ClassId component1() {
            return this.classId;
        }

        public final List<Integer> component2() {
            return this.typeParametersCount;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof ClassRequest) {
                    ClassRequest classRequest = (ClassRequest) obj;
                    if (acry.a(this.classId, classRequest.classId)) {
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            ClassId classId = this.classId;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            List list = this.typeParametersCount;
            if (list != null) {
                i = list.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("ClassRequest(classId=");
            stringBuilder.append(this.classId);
            stringBuilder.append(", typeParametersCount=");
            stringBuilder.append(this.typeParametersCount);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public ClassRequest(ClassId classId, List<Integer> list) {
            this.classId = classId;
            this.typeParametersCount = list;
        }
    }

    public final class MockClassDescriptor extends ClassDescriptorBase {
        private final boolean isInner;
        private final ClassTypeConstructorImpl typeConstructor;
        private final List<TypeParameterDescriptor> typeParameters;

        public final ClassDescriptor getCompanionObjectDescriptor() {
            return null;
        }

        public final ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
            return null;
        }

        public final boolean isActual() {
            return false;
        }

        public final boolean isCompanionObject() {
            return false;
        }

        public final boolean isData() {
            return false;
        }

        public final boolean isExpect() {
            return false;
        }

        public final boolean isExternal() {
            return false;
        }

        public final boolean isInline() {
            return false;
        }

        public MockClassDescriptor(StorageManager storageManager, DeclarationDescriptor declarationDescriptor, Name name, boolean z, int i) {
            super(storageManager, declarationDescriptor, name, SourceElement.NO_SOURCE, false);
            this.isInner = z;
            Iterable b = actx.b(0, i);
            Collection arrayList = new ArrayList(acns.a(b, 10));
            Iterator it = b.iterator();
            while (it.hasNext()) {
                int a = ((acoh) it).a();
                arrayList.add(TypeParameterDescriptorImpl.createWithDefaultBound(this, Annotations.Companion.getEMPTY(), false, Variance.INVARIANT, Name.identifier("T".concat(String.valueOf(a))), a));
            }
            this.typeParameters = (List) arrayList;
            this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeParameters, Collections.singleton(DescriptorUtilsKt.getModule(this).getBuiltIns().getAnyType()), storageManager);
        }

        public final ClassKind getKind() {
            return ClassKind.CLASS;
        }

        public final Modality getModality() {
            return Modality.FINAL;
        }

        public final Visibility getVisibility() {
            return Visibilities.PUBLIC;
        }

        public final ClassTypeConstructorImpl getTypeConstructor() {
            return this.typeConstructor;
        }

        public final List<TypeParameterDescriptor> getDeclaredTypeParameters() {
            return this.typeParameters;
        }

        public final boolean isInner() {
            return this.isInner;
        }

        public final Annotations getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        public final Empty getUnsubstitutedMemberScope() {
            return Empty.INSTANCE;
        }

        public final Empty getStaticScope() {
            return Empty.INSTANCE;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("class ");
            stringBuilder.append(getName());
            stringBuilder.append(" (not found)");
            return stringBuilder.toString();
        }

        public final Collection<ClassConstructorDescriptor> getConstructors() {
            return acod.a;
        }

        public final Collection<ClassDescriptor> getSealedSubclasses() {
            return acob.a;
        }
    }

    public NotFoundClasses(StorageManager storageManager, ModuleDescriptor moduleDescriptor) {
        this.storageManager = storageManager;
        this.module = moduleDescriptor;
    }

    public final ClassDescriptor getClass(ClassId classId, List<Integer> list) {
        return (ClassDescriptor) this.classes.invoke(new ClassRequest(classId, list));
    }
}
