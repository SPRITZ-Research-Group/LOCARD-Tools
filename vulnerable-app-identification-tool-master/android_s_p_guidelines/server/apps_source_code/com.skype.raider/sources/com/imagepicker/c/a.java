package com.imagepicker.c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import java.util.LinkedList;
import java.util.List;

public final class a {
    @Nullable
    public final a a;
    @Nullable
    public final a b;
    @Nullable
    public final a c;
    public final List<a> d;

    public static class a {
        public final String a;
        public final String b;

        public a(@NonNull String title, @NonNull String action) {
            this.a = title;
            this.b = action;
        }
    }

    private a(@Nullable a btnCamera, @Nullable a btnLibrary, @Nullable a btnCancel, @NonNull LinkedList<a> customButtons) {
        this.a = btnCamera;
        this.b = btnLibrary;
        this.c = btnCancel;
        this.d = customButtons;
    }

    public final List<String> a() {
        List<String> result = new LinkedList();
        if (this.a != null) {
            result.add(this.a.a);
        }
        if (this.b != null) {
            result.add(this.b.a);
        }
        for (int i = 0; i < this.d.size(); i++) {
            result.add(((a) this.d.get(i)).a);
        }
        return result;
    }

    public final List<String> b() {
        List<String> result = new LinkedList();
        if (this.a != null) {
            result.add(this.a.b);
        }
        if (this.b != null) {
            result.add(this.b.b);
        }
        for (int i = 0; i < this.d.size(); i++) {
            result.add(((a) this.d.get(i)).b);
        }
        return result;
    }

    public static a a(@NonNull am options) {
        a btnCamera = a(options, "takePhotoButtonTitle", "photo");
        a btnLibrary = a(options, "chooseFromLibraryButtonTitle", "library");
        a btnCancel = a(options, "cancelButtonTitle", "cancel");
        LinkedList<a> customButtons = new LinkedList();
        if (options.hasKey("customButtons")) {
            al array = options.getArray("customButtons");
            for (int i = 0; i < array.size(); i++) {
                am map = array.getMap(i);
                customButtons.add(new a(map.getString("title"), map.getString("name")));
            }
        }
        return new a(btnCamera, btnLibrary, btnCancel, customButtons);
    }

    @Nullable
    private static a a(@NonNull am options, @NonNull String key, @NonNull String action) {
        if (c.a(String.class, options, key)) {
            return new a(options.getString(key), action);
        }
        return null;
    }
}
