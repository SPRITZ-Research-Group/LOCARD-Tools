package com.google.android.gms.location;

import java.util.Comparator;

final class v implements Comparator<ActivityTransition> {
    v() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        ActivityTransition activityTransition = (ActivityTransition) obj;
        ActivityTransition activityTransition2 = (ActivityTransition) obj2;
        int a = activityTransition.a();
        int a2 = activityTransition2.a();
        if (a != a2) {
            return a < a2 ? -1 : 1;
        } else {
            a = activityTransition.b();
            a2 = activityTransition2.b();
            return a == a2 ? 0 : a >= a2 ? 1 : -1;
        }
    }
}
