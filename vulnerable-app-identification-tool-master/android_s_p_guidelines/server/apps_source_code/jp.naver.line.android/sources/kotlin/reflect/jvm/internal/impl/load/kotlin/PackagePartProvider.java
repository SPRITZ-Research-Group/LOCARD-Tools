package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;

public interface PackagePartProvider {
    List<String> findPackageParts(String str);
}
