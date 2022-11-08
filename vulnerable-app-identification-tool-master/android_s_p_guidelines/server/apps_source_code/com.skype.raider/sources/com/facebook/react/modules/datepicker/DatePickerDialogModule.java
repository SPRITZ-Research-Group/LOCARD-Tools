package com.facebook.react.modules.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.i;
import android.widget.DatePicker;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "DatePickerAndroid")
public class DatePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DATE_SET = "dateSetAction";
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ARG_DATE = "date";
    static final String ARG_MAXDATE = "maxDate";
    static final String ARG_MINDATE = "minDate";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    @VisibleForTesting
    public static final String FRAGMENT_TAG = "DatePickerAndroid";

    private class a implements OnDateSetListener, OnDismissListener {
        final /* synthetic */ DatePickerDialogModule a;
        private final ae b;
        private boolean c = false;

        public a(DatePickerDialogModule datePickerDialogModule, ae promise) {
            this.a = datePickerDialogModule;
            this.b = promise;
        }

        public final void onDateSet(DatePicker view, int year, int month, int day) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                Object result = new WritableNativeMap();
                result.putString("action", DatePickerDialogModule.ACTION_DATE_SET);
                result.putInt("year", year);
                result.putInt("month", month);
                result.putInt("day", day);
                this.b.a(result);
                this.c = true;
            }
        }

        public final void onDismiss(DialogInterface dialog) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                Object result = new WritableNativeMap();
                result.putString("action", DatePickerDialogModule.ACTION_DISMISSED);
                this.b.a(result);
                this.c = true;
            }
        }
    }

    public DatePickerDialogModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(@Nullable am options, ae promise) {
        Activity activity = getCurrentActivity();
        OnDateSetListener listener;
        if (activity == null) {
            promise.a(ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to an Activity");
        } else if (activity instanceof FragmentActivity) {
            i fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            DialogFragment oldFragment = (DialogFragment) fragmentManager.a(FRAGMENT_TAG);
            if (oldFragment != null) {
                oldFragment.a();
            }
            c fragment = new c();
            if (options != null) {
                fragment.setArguments(createFragmentArguments(options));
            }
            listener = new a(this, promise);
            fragment.a((OnDismissListener) listener);
            fragment.a(listener);
            fragment.a(fragmentManager, FRAGMENT_TAG);
        } else {
            FragmentManager fragmentManager2 = activity.getFragmentManager();
            android.app.DialogFragment oldFragment2 = (android.app.DialogFragment) fragmentManager2.findFragmentByTag(FRAGMENT_TAG);
            if (oldFragment2 != null) {
                oldFragment2.dismiss();
            }
            a fragment2 = new a();
            if (options != null) {
                fragment2.setArguments(createFragmentArguments(options));
            }
            listener = new a(this, promise);
            fragment2.a((OnDismissListener) listener);
            fragment2.a(listener);
            fragment2.show(fragmentManager2, FRAGMENT_TAG);
        }
    }

    private Bundle createFragmentArguments(am options) {
        Bundle args = new Bundle();
        if (options.hasKey(ARG_DATE) && !options.isNull(ARG_DATE)) {
            args.putLong(ARG_DATE, (long) options.getDouble(ARG_DATE));
        }
        if (options.hasKey(ARG_MINDATE) && !options.isNull(ARG_MINDATE)) {
            args.putLong(ARG_MINDATE, (long) options.getDouble(ARG_MINDATE));
        }
        if (options.hasKey(ARG_MAXDATE) && !options.isNull(ARG_MAXDATE)) {
            args.putLong(ARG_MAXDATE, (long) options.getDouble(ARG_MAXDATE));
        }
        if (options.hasKey(ARG_MODE) && !options.isNull(ARG_MODE)) {
            args.putString(ARG_MODE, options.getString(ARG_MODE));
        }
        return args;
    }
}
