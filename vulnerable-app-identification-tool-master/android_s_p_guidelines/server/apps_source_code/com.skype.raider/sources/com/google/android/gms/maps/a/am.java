package com.google.android.gms.maps.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.b.d;
import com.google.android.gms.common.h;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.j;
import com.google.android.gms.common.k;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.g;

public class am {
    private static final String a = am.class.getSimpleName();
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static Context b = null;
    private static ap c;

    public static ap a(Context context) throws h {
        ab.a((Object) context);
        if (c != null) {
            return c;
        }
        int a = k.a(context, 12451000);
        switch (a) {
            case 0:
                ap apVar;
                IBinder iBinder = (IBinder) a(b(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
                if (iBinder == null) {
                    apVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                    if (queryLocalInterface instanceof ap) {
                        apVar = (ap) queryLocalInterface;
                    } else {
                        Object apVar2 = new aq(iBinder);
                    }
                }
                c = apVar2;
                try {
                    c.a(d.a(b(context).getResources()), j.a);
                    return c;
                } catch (RemoteException e) {
                    throw new g(e);
                }
            default:
                throw new h(a);
        }
    }

    private static <T> T a(Class<?> cls) {
        String str;
        String valueOf;
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            str = "Unable to instantiate the dynamic class ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (IllegalAccessException e2) {
            str = "Unable to call the default constructor of ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private static <T> T a(ClassLoader classLoader, String str) {
        try {
            return a(((ClassLoader) ab.a((Object) classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            String str2 = "Unable to find dynamic class ";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    @Nullable
    private static Context b(Context context) {
        if (b != null) {
            return b;
        }
        Context c = c(context);
        b = c;
        return c;
    }

    @Nullable
    private static Context c(Context context) {
        try {
            return DynamiteModule.a(context, DynamiteModule.a, "com.google.android.gms.maps_dynamite").a();
        } catch (Throwable th) {
            return j.b(context);
        }
    }
}
