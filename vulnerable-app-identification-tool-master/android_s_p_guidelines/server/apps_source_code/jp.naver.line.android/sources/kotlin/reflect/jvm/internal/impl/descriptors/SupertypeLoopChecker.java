package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acqr;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.y;

public interface SupertypeLoopChecker {

    public final class EMPTY implements SupertypeLoopChecker {
        public static final EMPTY INSTANCE = new EMPTY();

        public final Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor, Collection<? extends KotlinType> collection, acqr<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> acqr, acqr<? super KotlinType, y> acqr2) {
            return collection;
        }

        private EMPTY() {
        }
    }

    Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor, Collection<? extends KotlinType> collection, acqr<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> acqr, acqr<? super KotlinType, y> acqr2);
}
