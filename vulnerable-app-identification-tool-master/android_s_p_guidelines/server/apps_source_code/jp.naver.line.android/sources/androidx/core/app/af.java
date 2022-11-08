package androidx.core.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bouncycastle.i18n.MessageBundle;

final class af {
    private static final Object a = new Object();
    private static Field b;
    private static boolean c;
    private static final Object d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle a(Notification notification) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                if (b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (Bundle.class.isAssignableFrom(declaredField.getType())) {
                        declaredField.setAccessible(true);
                        b = declaredField;
                    } else {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        c = true;
                        return null;
                    }
                }
                Bundle bundle = (Bundle) b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    b.set(notification, bundle);
                }
                return bundle;
            } catch (Throwable e) {
                Log.e("NotificationCompat", "Unable to access notification extras", e);
                c = true;
                return null;
            } catch (Throwable e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                c = true;
                return null;
            }
        }
    }

    static Bundle a(x xVar) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("icon", xVar.c);
        bundle2.putCharSequence(MessageBundle.TITLE_ENTRY, xVar.d);
        bundle2.putParcelable("actionIntent", xVar.e);
        if (xVar.a != null) {
            bundle = new Bundle(xVar.a);
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", xVar.a());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", a(xVar.b()));
        bundle2.putBoolean("showsUserInterface", xVar.b);
        bundle2.putInt("semanticAction", xVar.c());
        return bundle2;
    }

    private static Bundle[] a(an[] anVarArr) {
        if (anVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[anVarArr.length];
        for (int i = 0; i < anVarArr.length; i++) {
            an anVar = anVarArr[i];
            Bundle bundle = new Bundle();
            bundle.putString("resultKey", anVar.a());
            bundle.putCharSequence("label", anVar.b());
            bundle.putCharSequenceArray("choices", anVar.c());
            bundle.putBoolean("allowFreeFormInput", anVar.e());
            bundle.putBundle("extras", anVar.f());
            Set<String> d = anVar.d();
            if (!(d == null || d.isEmpty())) {
                ArrayList arrayList = new ArrayList(d.size());
                for (String add : d) {
                    arrayList.add(add);
                }
                bundle.putStringArrayList("allowedDataTypes", arrayList);
            }
            bundleArr[i] = bundle;
        }
        return bundleArr;
    }

    public static Bundle a(Builder builder, x xVar) {
        builder.addAction(xVar.c, xVar.d, xVar.e);
        Bundle bundle = new Bundle(xVar.a);
        if (xVar.b() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", a(xVar.b()));
        }
        if (xVar.d() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(xVar.d()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", xVar.a());
        return bundle;
    }
}
