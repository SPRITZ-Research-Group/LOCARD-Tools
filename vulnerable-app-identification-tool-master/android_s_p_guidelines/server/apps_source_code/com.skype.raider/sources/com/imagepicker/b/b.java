package com.imagepicker.b;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import com.facebook.react.bridge.am;
import com.imagepicker.ImagePickerModule;
import java.lang.ref.WeakReference;

public final class b {

    public interface a {
        void a(WeakReference<ImagePickerModule> weakReference);

        void b(WeakReference<ImagePickerModule> weakReference);
    }

    @Nullable
    public static AlertDialog a(@NonNull ImagePickerModule module, @NonNull am options, @NonNull final a callback) {
        if (module.getContext() == null) {
            return null;
        }
        am permissionDenied = options.getMap("permissionDenied");
        CharSequence title = permissionDenied.getString("title");
        String text = permissionDenied.getString("text");
        CharSequence btnReTryTitle = permissionDenied.getString("reTryTitle");
        String btnOkTitle = permissionDenied.getString("okTitle");
        final WeakReference<ImagePickerModule> reference = new WeakReference(module);
        Activity activity = module.getActivity();
        if (activity == null) {
            return null;
        }
        android.support.v7.app.AlertDialog.a builder = new android.support.v7.app.AlertDialog.a(activity, module.getDialogThemeId());
        builder.a(title).b(text).b().b(btnOkTitle, new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                callback.a(reference);
            }
        }).a(btnReTryTitle, new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                callback.b(reference);
            }
        });
        return builder.c();
    }
}
