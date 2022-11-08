package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.i;
import com.google.android.gms.common.internal.ab;

public final class n extends DialogFragment {
    private Dialog j = null;
    private OnCancelListener k = null;

    public static n a(Dialog dialog, OnCancelListener onCancelListener) {
        n nVar = new n();
        Dialog dialog2 = (Dialog) ab.a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        nVar.j = dialog2;
        if (onCancelListener != null) {
            nVar.k = onCancelListener;
        }
        return nVar;
    }

    public final void a(i iVar, String str) {
        super.a(iVar, str);
    }

    public final Dialog c() {
        if (this.j == null) {
            b();
        }
        return this.j;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        if (this.k != null) {
            this.k.onCancel(dialogInterface);
        }
    }
}
