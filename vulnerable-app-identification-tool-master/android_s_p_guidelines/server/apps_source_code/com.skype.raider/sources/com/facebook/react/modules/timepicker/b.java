package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;
import javax.annotation.Nullable;

public final class b extends DialogFragment {
    @Nullable
    private OnTimeSetListener a;
    @Nullable
    private OnDismissListener b;

    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        return a(getArguments(), getActivity(), this.a);
    }

    static Dialog a(Bundle args, Context activityContext, @Nullable OnTimeSetListener onTimeSetListener) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(11);
        int minute = now.get(12);
        boolean is24hour = DateFormat.is24HourFormat(activityContext);
        if (args != null) {
            hour = args.getInt("hour", now.get(11));
            minute = args.getInt("minute", now.get(12));
            is24hour = args.getBoolean("is24Hour", DateFormat.is24HourFormat(activityContext));
        }
        return new DismissableTimePickerDialog(activityContext, onTimeSetListener, hour, minute, is24hour);
    }

    public final void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.b != null) {
            this.b.onDismiss(dialog);
        }
    }

    public final void a(@Nullable OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }

    public final void a(@Nullable OnTimeSetListener onTimeSetListener) {
        this.a = onTimeSetListener;
    }
}
