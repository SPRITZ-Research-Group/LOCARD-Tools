package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import defpackage.acob;
import defpackage.acod;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface DeclaredMemberIndex {

    public final class Empty implements DeclaredMemberIndex {
        public static final Empty INSTANCE = new Empty();

        public final JavaField findFieldByName(Name name) {
            return null;
        }

        private Empty() {
        }

        public final List<JavaMethod> findMethodsByName(Name name) {
            return acob.a;
        }

        public final Set<Name> getMethodNames() {
            return acod.a;
        }

        public final Set<Name> getFieldNames() {
            return acod.a;
        }
    }

    JavaField findFieldByName(Name name);

    Collection<JavaMethod> findMethodsByName(Name name);

    Set<Name> getFieldNames();

    Set<Name> getMethodNames();
}
