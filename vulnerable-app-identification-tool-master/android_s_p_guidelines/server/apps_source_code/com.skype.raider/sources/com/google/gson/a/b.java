package com.google.gson.a;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class b {
    static final Type[] a = new Type[0];

    private static final class a implements Serializable, GenericArrayType {
        private final Type a;

        public a(Type componentType) {
            this.a = b.a(componentType);
        }

        public final Type getGenericComponentType() {
            return this.a;
        }

        public final boolean equals(Object o) {
            return (o instanceof GenericArrayType) && b.a((Type) this, (GenericArrayType) o);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final String toString() {
            return b.c(this.a) + "[]";
        }
    }

    private static final class b implements Serializable, ParameterizedType {
        private final Type a;
        private final Type b;
        private final Type[] c;

        public b(Type ownerType, Type rawType, Type... typeArguments) {
            boolean z = false;
            if (rawType instanceof Class) {
                Class<?> rawTypeAsClass = (Class) rawType;
                boolean isStaticOrTopLevelClass;
                if (Modifier.isStatic(rawTypeAsClass.getModifiers()) || rawTypeAsClass.getEnclosingClass() == null) {
                    isStaticOrTopLevelClass = true;
                } else {
                    isStaticOrTopLevelClass = false;
                }
                if (ownerType != null || isStaticOrTopLevelClass) {
                    z = true;
                }
                a.a(z);
            }
            this.a = ownerType == null ? null : b.a(ownerType);
            this.b = b.a(rawType);
            this.c = (Type[]) typeArguments.clone();
            for (int t = 0; t < this.c.length; t++) {
                a.a(this.c[t]);
                b.e(this.c[t]);
                this.c[t] = b.a(this.c[t]);
            }
        }

        public final Type[] getActualTypeArguments() {
            return (Type[]) this.c.clone();
        }

        public final Type getRawType() {
            return this.b;
        }

        public final Type getOwnerType() {
            return this.a;
        }

        public final boolean equals(Object other) {
            return (other instanceof ParameterizedType) && b.a((Type) this, (ParameterizedType) other);
        }

        public final int hashCode() {
            return (Arrays.hashCode(this.c) ^ this.b.hashCode()) ^ b.a(this.a);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder((this.c.length + 1) * 30);
            stringBuilder.append(b.c(this.b));
            if (this.c.length == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append("<").append(b.c(this.c[0]));
            for (int i = 1; i < this.c.length; i++) {
                stringBuilder.append(", ").append(b.c(this.c[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    private static final class c implements Serializable, WildcardType {
        private final Type a;
        private final Type b;

        public c(Type[] upperBounds, Type[] lowerBounds) {
            boolean z;
            boolean z2 = true;
            a.a(lowerBounds.length <= 1);
            if (upperBounds.length == 1) {
                z = true;
            } else {
                z = false;
            }
            a.a(z);
            if (lowerBounds.length == 1) {
                a.a(lowerBounds[0]);
                b.e(lowerBounds[0]);
                if (upperBounds[0] != Object.class) {
                    z2 = false;
                }
                a.a(z2);
                this.b = b.a(lowerBounds[0]);
                this.a = Object.class;
                return;
            }
            a.a(upperBounds[0]);
            b.e(upperBounds[0]);
            this.b = null;
            this.a = b.a(upperBounds[0]);
        }

        public final Type[] getUpperBounds() {
            return new Type[]{this.a};
        }

        public final Type[] getLowerBounds() {
            if (this.b == null) {
                return b.a;
            }
            return new Type[]{this.b};
        }

        public final boolean equals(Object other) {
            return (other instanceof WildcardType) && b.a((Type) this, (WildcardType) other);
        }

        public final int hashCode() {
            return (this.b != null ? this.b.hashCode() + 31 : 1) ^ (this.a.hashCode() + 31);
        }

        public final String toString() {
            if (this.b != null) {
                return "? super " + b.c(this.b);
            }
            if (this.a == Object.class) {
                return "?";
            }
            return "? extends " + b.c(this.a);
        }
    }

    private static GenericArrayType f(Type componentType) {
        return new a(componentType);
    }

    public static Type a(Type type) {
        if (type instanceof Class) {
            a aVar;
            Class<?> c = (Class) type;
            if (c.isArray()) {
                aVar = new a(a(c.getComponentType()));
            } else {
                Object aVar2 = c;
            }
            return aVar2;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) type;
            return new b(p.getOwnerType(), p.getRawType(), p.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType w = (WildcardType) type;
            return new c(w.getUpperBounds(), w.getLowerBounds());
        }
    }

    public static Class<?> b(Type type) {
        while (!(type instanceof Class)) {
            if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                a.a(rawType instanceof Class);
                return (Class) rawType;
            } else if (type instanceof GenericArrayType) {
                return Array.newInstance(b(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } else {
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    type = ((WildcardType) type).getUpperBounds()[0];
                } else {
                    throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
                }
            }
        }
        return (Class) type;
    }

    public static boolean a(Type a, Type b) {
        while (a != b) {
            if (a instanceof Class) {
                return a.equals(b);
            }
            if (a instanceof ParameterizedType) {
                if (!(b instanceof ParameterizedType)) {
                    return false;
                }
                boolean z;
                ParameterizedType pa = (ParameterizedType) a;
                ParameterizedType pb = (ParameterizedType) b;
                Type ownerType = pa.getOwnerType();
                Type ownerType2 = pb.getOwnerType();
                if (ownerType == ownerType2 || (ownerType != null && ownerType.equals(ownerType2))) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && pa.getRawType().equals(pb.getRawType()) && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments())) {
                    return true;
                }
                return false;
            } else if (a instanceof GenericArrayType) {
                if (!(b instanceof GenericArrayType)) {
                    return false;
                }
                GenericArrayType gb = (GenericArrayType) b;
                a = ((GenericArrayType) a).getGenericComponentType();
                b = gb.getGenericComponentType();
            } else if (a instanceof WildcardType) {
                if (!(b instanceof WildcardType)) {
                    return false;
                }
                WildcardType wa = (WildcardType) a;
                WildcardType wb = (WildcardType) b;
                if (Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds()) && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds())) {
                    return true;
                }
                return false;
            } else if (!(a instanceof TypeVariable)) {
                return false;
            } else {
                if (!(b instanceof TypeVariable)) {
                    return false;
                }
                TypeVariable<?> va = (TypeVariable) a;
                TypeVariable<?> vb = (TypeVariable) b;
                if (va.getGenericDeclaration() == vb.getGenericDeclaration() && va.getName().equals(vb.getName())) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    static int a(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static String c(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    private static Type a(Type context, Class<?> rawType, Class<?> toResolve) {
        while (toResolve != rawType) {
            if (toResolve.isInterface()) {
                Class<?>[] interfaces = rawType.getInterfaces();
                int length = interfaces.length;
                for (int i = 0; i < length; i++) {
                    if (interfaces[i] == toResolve) {
                        return rawType.getGenericInterfaces()[i];
                    }
                    if (toResolve.isAssignableFrom(interfaces[i])) {
                        context = rawType.getGenericInterfaces()[i];
                        rawType = interfaces[i];
                        break;
                    }
                }
            }
            if (!rawType.isInterface()) {
                while (rawType != Object.class) {
                    Class<?> rawSupertype = rawType.getSuperclass();
                    if (rawSupertype == toResolve) {
                        return rawType.getGenericSuperclass();
                    }
                    if (toResolve.isAssignableFrom(rawSupertype)) {
                        context = rawType.getGenericSuperclass();
                        rawType = rawSupertype;
                    } else {
                        rawType = rawSupertype;
                    }
                }
            }
            return toResolve;
        }
        return context;
    }

    private static Type b(Type context, Class<?> contextRawType, Class<?> supertype) {
        a.a(supertype.isAssignableFrom(contextRawType));
        return a(context, (Class) contextRawType, a(context, (Class) contextRawType, (Class) supertype));
    }

    public static Type d(Type array) {
        if (array instanceof GenericArrayType) {
            return ((GenericArrayType) array).getGenericComponentType();
        }
        return ((Class) array).getComponentType();
    }

    public static Type a(Type context, Class<?> contextRawType) {
        Type collectionType = b(context, contextRawType, Collection.class);
        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType) collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] b(Type context, Class<?> contextRawType) {
        if (context == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type mapType = b(context, contextRawType, Map.class);
        if (mapType instanceof ParameterizedType) {
            return ((ParameterizedType) mapType).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type a(Type context, Class<?> contextRawType, Type toResolve) {
        while (toResolve instanceof TypeVariable) {
            Class cls;
            TypeVariable<?> typeVariable = (TypeVariable) toResolve;
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
            if (genericDeclaration instanceof Class) {
                cls = (Class) genericDeclaration;
            } else {
                cls = null;
            }
            if (cls != null) {
                Type a = a(context, (Class) contextRawType, cls);
                if (a instanceof ParameterizedType) {
                    TypeVariable[] typeParameters = cls.getTypeParameters();
                    for (int i = 0; i < typeParameters.length; i++) {
                        if (typeVariable.equals(typeParameters[i])) {
                            toResolve = ((ParameterizedType) a).getActualTypeArguments()[i];
                            if (toResolve == typeVariable) {
                                return toResolve;
                            }
                        }
                    }
                    throw new NoSuchElementException();
                }
            }
            Object toResolve2 = typeVariable;
            if (toResolve2 == typeVariable) {
                return toResolve2;
            }
        }
        Type componentType;
        Type newComponentType;
        if ((toResolve2 instanceof Class) && ((Class) toResolve2).isArray()) {
            Class<?> original = (Class) toResolve2;
            componentType = original.getComponentType();
            newComponentType = a(context, (Class) contextRawType, componentType);
            if (componentType == newComponentType) {
                return original;
            }
            return f(newComponentType);
        } else if (toResolve2 instanceof GenericArrayType) {
            GenericArrayType original2 = (GenericArrayType) toResolve2;
            componentType = original2.getGenericComponentType();
            newComponentType = a(context, (Class) contextRawType, componentType);
            if (componentType != newComponentType) {
                return f(newComponentType);
            }
            return original2;
        } else if (toResolve2 instanceof ParameterizedType) {
            ParameterizedType original3 = (ParameterizedType) toResolve2;
            Type ownerType = original3.getOwnerType();
            Type newOwnerType = a(context, (Class) contextRawType, ownerType);
            boolean changed = newOwnerType != ownerType;
            Type[] args = original3.getActualTypeArguments();
            int length = args.length;
            for (int t = 0; t < length; t++) {
                Type resolvedTypeArgument = a(context, (Class) contextRawType, args[t]);
                if (resolvedTypeArgument != args[t]) {
                    if (!changed) {
                        args = (Type[]) args.clone();
                        changed = true;
                    }
                    args[t] = resolvedTypeArgument;
                }
            }
            if (changed) {
                return new b(newOwnerType, original3.getRawType(), args);
            }
            return original3;
        } else if (!(toResolve2 instanceof WildcardType)) {
            return toResolve2;
        } else {
            WildcardType original4 = (WildcardType) toResolve2;
            Type[] originalLowerBound = original4.getLowerBounds();
            Type[] originalUpperBound = original4.getUpperBounds();
            if (originalLowerBound.length == 1) {
                if (a(context, (Class) contextRawType, originalLowerBound[0]) == originalLowerBound[0]) {
                    return original4;
                }
                return new c(new Type[]{Object.class}, new Type[]{a(context, (Class) contextRawType, originalLowerBound[0])});
            } else if (originalUpperBound.length != 1 || a(context, (Class) contextRawType, originalUpperBound[0]) == originalUpperBound[0]) {
                return original4;
            } else {
                return new c(new Type[]{a(context, (Class) contextRawType, originalUpperBound[0])}, a);
            }
        }
    }

    static void e(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        a.a(z);
    }
}
