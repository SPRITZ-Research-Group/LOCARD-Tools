package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import defpackage.acqr;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface ResolutionScope {

    public final class DefaultImpls {
        public static /* synthetic */ Collection getContributedDescriptors$default(ResolutionScope resolutionScope, DescriptorKindFilter descriptorKindFilter, acqr acqr, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    descriptorKindFilter = DescriptorKindFilter.ALL;
                }
                if ((i & 2) != 0) {
                    acqr = MemberScope.Companion.getALL_NAME_FILTER();
                }
                return resolutionScope.getContributedDescriptors(descriptorKindFilter, acqr);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContributedDescriptors");
        }

        public static void recordLookup(ResolutionScope resolutionScope, Name name, LookupLocation lookupLocation) {
            resolutionScope.getContributedFunctions(name, lookupLocation);
        }
    }

    ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation);

    Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, acqr<? super Name, Boolean> acqr);

    Collection<? extends FunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation);
}
