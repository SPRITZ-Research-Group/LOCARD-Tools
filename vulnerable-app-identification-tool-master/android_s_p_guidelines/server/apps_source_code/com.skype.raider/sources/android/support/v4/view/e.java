package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import java.lang.reflect.Field;

public final class e {
    static final b a;
    private static Field b;
    private static boolean c;

    static class b {
        b() {
        }

        public void a(LayoutInflater inflater, Factory2 factory) {
            inflater.setFactory2(factory);
            Factory f = inflater.getFactory();
            if (f instanceof Factory2) {
                e.a(inflater, (Factory2) f);
            } else {
                e.a(inflater, factory);
            }
        }
    }

    @RequiresApi(21)
    static class a extends b {
        a() {
        }

        public final void a(LayoutInflater inflater, Factory2 factory) {
            inflater.setFactory2(factory);
        }
    }

    static void a(LayoutInflater inflater, Factory2 factory) {
        if (!c) {
            try {
                Field declaredField = LayoutInflater.class.getDeclaredField("mFactory2");
                b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                new StringBuilder("forceSetFactory2 Could not find field 'mFactory2' on class ").append(LayoutInflater.class.getName()).append("; inflation may have unexpected results.");
            }
            c = true;
        }
        if (b != null) {
            try {
                b.set(inflater, factory);
            } catch (IllegalAccessException e2) {
                new StringBuilder("forceSetFactory2 could not set the Factory2 on LayoutInflater ").append(inflater).append("; inflation may have unexpected results.");
            }
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static void b(@NonNull LayoutInflater inflater, @NonNull Factory2 factory) {
        a.a(inflater, factory);
    }
}
