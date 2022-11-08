package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import kotlin.e;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class LazyJavaResolverContext {
    static final /* synthetic */ acuo[] $$delegatedProperties = new acuo[]{acso.a(new acsi(acso.a(LazyJavaResolverContext.class), "defaultTypeQualifiers", "getDefaultTypeQualifiers()Lorg/jetbrains/kotlin/load/java/lazy/JavaTypeQualifiersByElementType;"))};
    private final JavaResolverComponents components;
    private final e defaultTypeQualifiers$delegate = this.delegateForDefaultTypeQualifiers;
    private final e<JavaTypeQualifiersByElementType> delegateForDefaultTypeQualifiers;
    private final TypeParameterResolver typeParameterResolver;
    private final JavaTypeResolver typeResolver = new JavaTypeResolver(this, this.typeParameterResolver);

    public final JavaTypeQualifiersByElementType getDefaultTypeQualifiers() {
        return (JavaTypeQualifiersByElementType) this.defaultTypeQualifiers$delegate.d();
    }

    public LazyJavaResolverContext(JavaResolverComponents javaResolverComponents, TypeParameterResolver typeParameterResolver, e<JavaTypeQualifiersByElementType> eVar) {
        this.components = javaResolverComponents;
        this.typeParameterResolver = typeParameterResolver;
        this.delegateForDefaultTypeQualifiers = eVar;
    }

    public final JavaResolverComponents getComponents() {
        return this.components;
    }

    public final TypeParameterResolver getTypeParameterResolver() {
        return this.typeParameterResolver;
    }

    public final e<JavaTypeQualifiersByElementType> getDelegateForDefaultTypeQualifiers$descriptors_jvm() {
        return this.delegateForDefaultTypeQualifiers;
    }

    public final JavaTypeResolver getTypeResolver() {
        return this.typeResolver;
    }

    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    public final ModuleDescriptor getModule() {
        return this.components.getModule();
    }
}
