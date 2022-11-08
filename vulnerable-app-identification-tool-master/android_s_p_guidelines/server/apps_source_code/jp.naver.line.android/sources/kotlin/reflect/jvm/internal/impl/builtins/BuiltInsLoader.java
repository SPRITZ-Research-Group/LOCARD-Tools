package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import kotlin.e;
import kotlin.j;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public interface BuiltInsLoader {
    public static final Companion Companion = Companion.$$INSTANCE;

    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        static final /* synthetic */ acuo[] $$delegatedProperties = new acuo[]{acso.a(new acsi(acso.a(Companion.class), "Instance", "getInstance()Lorg/jetbrains/kotlin/builtins/BuiltInsLoader;"))};
        private static final e Instance$delegate = h.a(j.PUBLICATION, BuiltInsLoader$Companion$Instance$2.INSTANCE);

        public final BuiltInsLoader getInstance() {
            return (BuiltInsLoader) Instance$delegate.d();
        }

        private Companion() {
        }
    }

    PackageFragmentProvider createPackageFragmentProvider(StorageManager storageManager, ModuleDescriptor moduleDescriptor, Iterable<? extends ClassDescriptorFactory> iterable, PlatformDependentDeclarationFilter platformDependentDeclarationFilter, AdditionalClassPartsProvider additionalClassPartsProvider);
}
