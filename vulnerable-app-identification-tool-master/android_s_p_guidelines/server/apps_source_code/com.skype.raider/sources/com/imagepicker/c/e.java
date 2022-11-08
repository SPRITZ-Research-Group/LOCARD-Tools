package com.imagepicker.c;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.facebook.react.bridge.am;
import com.imagepicker.ImagePickerModule;
import java.lang.ref.WeakReference;
import java.util.List;

public final class e {

    public interface a {
        void a(@Nullable ImagePickerModule imagePickerModule);

        void a(@Nullable ImagePickerModule imagePickerModule, String str);

        void b(@Nullable ImagePickerModule imagePickerModule);

        void c(@Nullable ImagePickerModule imagePickerModule);
    }

    @NonNull
    public static AlertDialog a(@Nullable ImagePickerModule module, @NonNull am options, @Nullable final a callback) {
        Context context = module.getActivity();
        if (context == null) {
            return null;
        }
        final WeakReference<ImagePickerModule> reference = new WeakReference(module);
        a buttons = a.a(options);
        List<String> titles = buttons.a();
        final List<String> actions = buttons.b();
        ListAdapter adapter = new ArrayAdapter(context, com.imagepicker.b.a.list_item, titles);
        android.support.v7.app.AlertDialog.a builder = new android.support.v7.app.AlertDialog.a(context, module.getDialogThemeId());
        if (c.a(String.class, options, "title")) {
            builder.a(options.getString("title"));
        }
        builder.a(adapter, new OnClickListener() {
            public final void onClick(DialogInterface dialog, int index) {
                String action = (String) actions.get(index);
                Object obj = -1;
                switch (action.hashCode()) {
                    case -1367724422:
                        if (action.equals("cancel")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 106642994:
                        if (action.equals("photo")) {
                            obj = null;
                            break;
                        }
                        break;
                    case 166208699:
                        if (action.equals("library")) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        callback.a((ImagePickerModule) reference.get());
                        return;
                    case 1:
                        callback.b((ImagePickerModule) reference.get());
                        return;
                    case 2:
                        callback.c((ImagePickerModule) reference.get());
                        return;
                    default:
                        callback.a((ImagePickerModule) reference.get(), action);
                        return;
                }
            }
        });
        builder.b(buttons.c.a, new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                callback.c((ImagePickerModule) reference.get());
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.c();
        dialog.setOnCancelListener(new OnCancelListener() {
            public final void onCancel(@NonNull DialogInterface dialog) {
                callback.c((ImagePickerModule) reference.get());
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
