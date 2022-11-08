package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acqr;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface PackageFragmentProvider {
    List<PackageFragmentDescriptor> getPackageFragments(FqName fqName);

    Collection<FqName> getSubPackagesOf(FqName fqName, acqr<? super Name, Boolean> acqr);
}
