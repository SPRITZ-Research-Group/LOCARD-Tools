package com.facebook.react.uimanager.b;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.h.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public final class a {
    private static final List<a> a = new ArrayList();

    public interface a {
        String a();
    }

    @Nullable
    public static View a(View root, String nativeId) {
        String tag = b(root);
        if (tag != null && tag.equals(nativeId)) {
            return root;
        }
        if (root instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) root;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = a(viewGroup.getChildAt(i), nativeId);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }

    public static void a(View view) {
        String nativeId = b(view);
        if (nativeId != null) {
            Iterator<a> iterator = a.iterator();
            while (iterator.hasNext()) {
                a listener = (a) iterator.next();
                if (nativeId != null && nativeId.equals(listener.a())) {
                    iterator.remove();
                }
            }
        }
    }

    @Nullable
    private static String b(View view) {
        Object tag = view.getTag(b.view_tag_native_id);
        return tag instanceof String ? (String) tag : null;
    }
}
