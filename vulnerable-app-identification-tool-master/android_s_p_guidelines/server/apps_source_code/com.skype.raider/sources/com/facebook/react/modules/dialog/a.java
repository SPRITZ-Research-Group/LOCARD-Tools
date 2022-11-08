package com.facebook.react.modules.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import javax.annotation.Nullable;

public final class a extends DialogFragment implements OnClickListener {
    @Nullable
    private final a a;

    public a() {
        this.a = null;
    }

    public a(@Nullable a listener, Bundle arguments) {
        this.a = listener;
        setArguments(arguments);
    }

    public static Dialog a(Context activityContext, Bundle arguments, OnClickListener fragment) {
        Builder builder = new Builder(activityContext).setTitle(arguments.getString("title"));
        if (arguments.containsKey("button_positive")) {
            builder.setPositiveButton(arguments.getString("button_positive"), fragment);
        }
        if (arguments.containsKey("button_negative")) {
            builder.setNegativeButton(arguments.getString("button_negative"), fragment);
        }
        if (arguments.containsKey("button_neutral")) {
            builder.setNeutralButton(arguments.getString("button_neutral"), fragment);
        }
        if (arguments.containsKey("message")) {
            builder.setMessage(arguments.getString("message"));
        }
        if (arguments.containsKey("items")) {
            builder.setItems(arguments.getCharSequenceArray("items"), fragment);
        }
        return builder.create();
    }

    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        return a(getActivity(), getArguments(), this);
    }

    public final void onClick(DialogInterface dialog, int which) {
        if (this.a != null) {
            this.a.onClick(dialog, which);
        }
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.a != null) {
            this.a.onDismiss(dialog);
        }
    }
}
