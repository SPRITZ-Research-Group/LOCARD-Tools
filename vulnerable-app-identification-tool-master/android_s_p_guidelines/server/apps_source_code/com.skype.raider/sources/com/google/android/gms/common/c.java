package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.ab;

public final class c extends DialogFragment {
    private Dialog a = null;
    private OnCancelListener b = null;

    public static c a(Dialog dialog, OnCancelListener onCancelListener) {
        c cVar = new c();
        Dialog dialog2 = (Dialog) ab.a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        cVar.a = dialog2;
        if (onCancelListener != null) {
            cVar.b = onCancelListener;
        }
        return cVar;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        if (this.b != null) {
            this.b.onCancel(dialogInterface);
        }
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        if (this.a == null) {
            setShowsDialog(false);
        }
        return this.a;
    }

    public final void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
