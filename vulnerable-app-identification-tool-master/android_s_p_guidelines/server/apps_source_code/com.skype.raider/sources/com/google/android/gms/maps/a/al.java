package com.google.android.gms.maps.a;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public final class al {
    private al() {
    }

    private static <T extends Parcelable> T a(@Nullable Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(al.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(al.class.getClassLoader());
        return bundle2.getParcelable(str);
    }

    public static void a(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            Parcelable a = a(bundle, "MapOptions");
            if (a != null) {
                a(bundle2, "MapOptions", a);
            }
            a = a(bundle, "StreetViewPanoramaOptions");
            if (a != null) {
                a(bundle2, "StreetViewPanoramaOptions", a);
            }
            a = a(bundle, "camera");
            if (a != null) {
                a(bundle2, "camera", a);
            }
            if (bundle.containsKey("position")) {
                bundle2.putString("position", bundle.getString("position"));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    private static void a(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(al.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(al.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
