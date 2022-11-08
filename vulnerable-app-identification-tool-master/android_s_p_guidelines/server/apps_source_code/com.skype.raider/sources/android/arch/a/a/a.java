package android.arch.a.a;

import android.support.annotation.RestrictTo;
import java.util.HashMap;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class a<K, V> extends b<K, V> {
    private HashMap<K, c<K, V>> a = new HashMap();

    public final boolean a(K key) {
        return this.a.containsKey(key);
    }
}
