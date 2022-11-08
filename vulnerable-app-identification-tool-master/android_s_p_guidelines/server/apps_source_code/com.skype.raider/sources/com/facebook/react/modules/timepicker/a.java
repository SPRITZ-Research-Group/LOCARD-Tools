package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.DialogFragment;
import javax.annotation.Nullable;

public final class a extends DialogFragment {
    @Nullable
    private OnTimeSetListener j;
    @Nullable
    private OnDismissListener k;

    public final Dialog c() {
        return b.a(getArguments(), getActivity(), this.j);
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.k != null) {
            this.k.onDismiss(dialog);
        }
    }

    public final void a(@Nullable OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public final void a(@Nullable OnTimeSetListener onTimeSetListener) {
        this.j = onTimeSetListener;
    }
}
