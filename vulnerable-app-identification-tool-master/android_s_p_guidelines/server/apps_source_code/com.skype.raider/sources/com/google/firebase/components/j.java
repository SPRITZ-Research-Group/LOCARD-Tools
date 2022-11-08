package com.google.firebase.components;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class j {
    private final Context a;
    private final l b;

    public j(Context context) {
        this(context, new k());
    }

    @VisibleForTesting
    private j(Context context, l lVar) {
        this.a = context;
        this.b = lVar;
    }

    private static List<d> a(List<String> list) {
        List<d> arrayList = new ArrayList();
        for (String cls : list) {
            try {
                Class cls2 = Class.forName(cls);
                if (d.class.isAssignableFrom(cls2)) {
                    arrayList.add((d) cls2.newInstance());
                } else {
                    String.format("Class %s is not an instance of %s", new Object[]{cls, "com.google.firebase.components.ComponentRegistrar"});
                }
            } catch (ClassNotFoundException e) {
                String.format("Class %s is not an found.", new Object[]{cls});
            } catch (IllegalAccessException e2) {
                String.format("Could not instantiate %s.", new Object[]{cls});
            } catch (InstantiationException e3) {
                String.format("Could not instantiate %s.", new Object[]{cls});
            }
        }
        return arrayList;
    }

    public final List<d> a() {
        return a(this.b.a(this.a));
    }
}
