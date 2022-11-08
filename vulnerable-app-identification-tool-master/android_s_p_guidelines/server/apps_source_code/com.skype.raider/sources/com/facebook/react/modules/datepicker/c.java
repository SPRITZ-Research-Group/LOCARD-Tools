package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.DialogFragment;
import javax.annotation.Nullable;

@SuppressLint({"ValidFragment"})
public final class c extends DialogFragment {
    @Nullable
    private OnDateSetListener j;
    @Nullable
    private OnDismissListener k;

    public final Dialog c() {
        return a.a(getArguments(), getActivity(), this.j);
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.k != null) {
            this.k.onDismiss(dialog);
        }
    }

    final void a(@Nullable OnDateSetListener onDateSetListener) {
        this.j = onDateSetListener;
    }

    final void a(@Nullable OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }
}
