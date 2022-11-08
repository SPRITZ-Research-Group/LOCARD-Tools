package com.facebook.react.modules.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import javax.annotation.Nullable;

public final class b extends DialogFragment implements OnClickListener {
    @Nullable
    private final a j;

    public b() {
        this.j = null;
    }

    public b(@Nullable a listener, Bundle arguments) {
        this.j = listener;
        setArguments(arguments);
    }

    public final Dialog c() {
        return a.a(getActivity(), getArguments(), this);
    }

    public final void onClick(DialogInterface dialog, int which) {
        if (this.j != null) {
            this.j.onClick(dialog, which);
        }
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.j != null) {
            this.j.onDismiss(dialog);
        }
    }
}
