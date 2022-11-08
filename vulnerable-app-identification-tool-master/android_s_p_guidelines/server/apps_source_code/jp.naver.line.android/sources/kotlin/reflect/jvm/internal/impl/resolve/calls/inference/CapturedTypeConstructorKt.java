package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import defpackage.acns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.u;
import kotlin.v;

public final class CapturedTypeConstructorKt {
    public static final KotlinType createCapturedType(TypeProjection typeProjection) {
        return new CapturedType(typeProjection, null, false, null, 14, null);
    }

    public static final boolean isCaptured(KotlinType kotlinType) {
        return kotlinType.getConstructor() instanceof CapturedTypeConstructor;
    }

    public static /* synthetic */ TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return wrapWithCapturingSubstitution(typeSubstitution, z);
    }

    public static final TypeSubstitution wrapWithCapturingSubstitution(TypeSubstitution typeSubstitution, boolean z) {
        if (!(typeSubstitution instanceof IndexedParametersSubstitution)) {
            return new CapturedTypeConstructorKt$wrapWithCapturingSubstitution$2(typeSubstitution, z, typeSubstitution);
        }
        IndexedParametersSubstitution indexedParametersSubstitution = (IndexedParametersSubstitution) typeSubstitution;
        TypeParameterDescriptor[] parameters = indexedParametersSubstitution.getParameters();
        TypeProjection[] arguments = indexedParametersSubstitution.getArguments();
        TypeParameterDescriptor[] parameters2 = indexedParametersSubstitution.getParameters();
        int min = Math.min(arguments.length, parameters2.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(u.a(arguments[i], parameters2[i]));
        }
        Iterable<m> iterable = arrayList;
        Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable, 10));
        for (m mVar : iterable) {
            arrayList2.add(createCapturedIfNeeded((TypeProjection) mVar.a(), (TypeParameterDescriptor) mVar.b()));
        }
        Object[] toArray = ((List) arrayList2).toArray(new TypeProjection[0]);
        if (toArray != null) {
            return new IndexedParametersSubstitution(parameters, (TypeProjection[]) toArray, z);
        }
        throw new v("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private static final TypeProjection createCapturedIfNeeded(TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor == null || typeProjection.getProjectionKind() == Variance.INVARIANT) {
            return typeProjection;
        }
        if (typeParameterDescriptor.getVariance() != typeProjection.getProjectionKind()) {
            return new TypeProjectionImpl(createCapturedType(typeProjection));
        }
        if (typeProjection.isStarProjection()) {
            return new TypeProjectionImpl(new LazyWrappedType(LockBasedStorageManager.NO_LOCKS, new CapturedTypeConstructorKt$createCapturedIfNeeded$1(typeProjection)));
        }
        return new TypeProjectionImpl(typeProjection.getType());
    }
}
