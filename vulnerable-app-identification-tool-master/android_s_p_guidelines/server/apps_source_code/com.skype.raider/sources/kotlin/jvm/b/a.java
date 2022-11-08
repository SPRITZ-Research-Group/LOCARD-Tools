package kotlin.jvm.b;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.e;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-runtime"}, k = 2, mv = {1, 1, 10})
@JvmName(name = "CollectionToArray")
public final class a {
    private static final Object[] a = new Object[0];

    @NotNull
    @JvmName(name = "toArray")
    public static final Object[] a(@NotNull Collection<?> collection, @Nullable Object[] a) {
        c.b(collection, "collection");
        if (a == null) {
            throw new NullPointerException();
        }
        int size$iv = collection.size();
        if (size$iv != 0) {
            Iterator iter$iv = collection.iterator();
            if (iter$iv.hasNext()) {
                Object[] result$iv;
                if (size$iv <= a.length) {
                    result$iv = a;
                } else {
                    Object newInstance = Array.newInstance(a.getClass().getComponentType(), size$iv);
                    if (newInstance == null) {
                        throw new e("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                    }
                    result$iv = (Object[]) newInstance;
                }
                int i = 0;
                while (true) {
                    int i$iv = i + 1;
                    result$iv[i] = iter$iv.next();
                    if (i$iv >= result$iv.length) {
                        if (!iter$iv.hasNext()) {
                            return result$iv;
                        }
                        int newSize$iv = ((i$iv * 3) + 1) >>> 1;
                        if (newSize$iv <= i$iv) {
                            if (i$iv >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                            newSize$iv = 2147483645;
                        }
                        result$iv = Arrays.copyOf(result$iv, newSize$iv);
                        c.a((Object) result$iv, "Arrays.copyOf(result, newSize)");
                        i = i$iv;
                    } else if (iter$iv.hasNext()) {
                        i = i$iv;
                    } else {
                        Object[] result = result$iv;
                        if (result$iv == a) {
                            a[i$iv] = null;
                            return a;
                        }
                        Object a2 = Arrays.copyOf(result, i$iv);
                        c.a(a2, "Arrays.copyOf(result, size)");
                        return a2;
                    }
                }
            } else if (a.length <= 0) {
                return a;
            } else {
                a[0] = null;
                return a;
            }
        } else if (a.length <= 0) {
            return a;
        } else {
            a[0] = null;
            return a;
        }
    }

    @NotNull
    @JvmName(name = "toArray")
    public static final Object[] a(@NotNull Collection<?> collection) {
        c.b(collection, "collection");
        int size$iv = collection.size();
        if (size$iv == 0) {
            return a;
        }
        Iterator iter$iv = collection.iterator();
        if (!iter$iv.hasNext()) {
            return a;
        }
        Object[] result$iv = new Object[size$iv];
        int i = 0;
        while (true) {
            int i$iv = i + 1;
            result$iv[i] = iter$iv.next();
            if (i$iv >= result$iv.length) {
                if (!iter$iv.hasNext()) {
                    return result$iv;
                }
                int newSize$iv = ((i$iv * 3) + 1) >>> 1;
                if (newSize$iv <= i$iv) {
                    if (i$iv >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                    newSize$iv = 2147483645;
                }
                result$iv = Arrays.copyOf(result$iv, newSize$iv);
                c.a((Object) result$iv, "Arrays.copyOf(result, newSize)");
                i = i$iv;
            } else if (iter$iv.hasNext()) {
                i = i$iv;
            } else {
                Object copyOf = Arrays.copyOf(result$iv, i$iv);
                c.a(copyOf, "Arrays.copyOf(result, size)");
                return copyOf;
            }
        }
    }
}
