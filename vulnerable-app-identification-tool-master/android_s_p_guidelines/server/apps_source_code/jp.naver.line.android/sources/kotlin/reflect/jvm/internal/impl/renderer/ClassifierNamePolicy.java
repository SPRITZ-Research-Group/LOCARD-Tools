package kotlin.reflect.jvm.internal.impl.renderer;

import com.google.obf.ly;
import defpackage.acnx;
import defpackage.acry;
import java.util.ArrayList;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

public interface ClassifierNamePolicy {

    public final class FULLY_QUALIFIED implements ClassifierNamePolicy {
        public static final FULLY_QUALIFIED INSTANCE = new FULLY_QUALIFIED();

        private FULLY_QUALIFIED() {
        }

        public final String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                return descriptorRenderer.renderName(((TypeParameterDescriptor) classifierDescriptor).getName(), false);
            }
            return descriptorRenderer.renderFqName(DescriptorUtils.getFqName(classifierDescriptor));
        }
    }

    public final class SHORT implements ClassifierNamePolicy {
        public static final SHORT INSTANCE = new SHORT();

        private SHORT() {
        }

        public final String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                return descriptorRenderer.renderName(((TypeParameterDescriptor) classifierDescriptor).getName(), false);
            }
            ArrayList arrayList = new ArrayList();
            DeclarationDescriptor declarationDescriptor = classifierDescriptor;
            do {
                arrayList.add(declarationDescriptor.getName());
                declarationDescriptor = declarationDescriptor.getContainingDeclaration();
            } while (declarationDescriptor instanceof ClassDescriptor);
            return RenderingUtilsKt.renderFqName(acnx.d(arrayList));
        }
    }

    public final class SOURCE_CODE_QUALIFIED implements ClassifierNamePolicy {
        public static final SOURCE_CODE_QUALIFIED INSTANCE = new SOURCE_CODE_QUALIFIED();

        private SOURCE_CODE_QUALIFIED() {
        }

        public final String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            return qualifiedNameForSourceCode(classifierDescriptor);
        }

        private final String qualifiedNameForSourceCode(ClassifierDescriptor classifierDescriptor) {
            String render = RenderingUtilsKt.render(classifierDescriptor.getName());
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                return render;
            }
            Object qualifierName = qualifierName(classifierDescriptor.getContainingDeclaration());
            if (qualifierName == null || (acry.a(qualifierName, (Object) "") ^ 1) == 0) {
                return render;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(qualifierName);
            stringBuilder.append(ly.a);
            stringBuilder.append(render);
            return stringBuilder.toString();
        }

        private final String qualifierName(DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                return qualifiedNameForSourceCode((ClassifierDescriptor) declarationDescriptor);
            }
            return declarationDescriptor instanceof PackageFragmentDescriptor ? RenderingUtilsKt.render(((PackageFragmentDescriptor) declarationDescriptor).getFqName().toUnsafe()) : null;
        }
    }

    String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer);
}
