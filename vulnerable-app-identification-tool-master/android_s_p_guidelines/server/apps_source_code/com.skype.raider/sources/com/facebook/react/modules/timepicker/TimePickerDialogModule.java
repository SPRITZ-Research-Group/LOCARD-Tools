package com.facebook.react.modules.timepicker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.i;
import android.widget.TimePicker;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "TimePickerAndroid")
public class TimePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ACTION_TIME_SET = "timeSetAction";
    static final String ARG_HOUR = "hour";
    static final String ARG_IS24HOUR = "is24Hour";
    static final String ARG_MINUTE = "minute";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    @VisibleForTesting
    public static final String FRAGMENT_TAG = "TimePickerAndroid";

    private class a implements OnTimeSetListener, OnDismissListener {
        final /* synthetic */ TimePickerDialogModule a;
        private final ae b;
        private boolean c = false;

        public a(TimePickerDialogModule timePickerDialogModule, ae promise) {
            this.a = timePickerDialogModule;
            this.b = promise;
        }

        public final void onTimeSet(TimePicker view, int hour, int minute) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                Object result = new WritableNativeMap();
                result.putString("action", TimePickerDialogModule.ACTION_TIME_SET);
                result.putInt(TimePickerDialogModule.ARG_HOUR, hour);
                result.putInt(TimePickerDialogModule.ARG_MINUTE, minute);
                this.b.a(result);
                this.c = true;
            }
        }

        public final void onDismiss(DialogInterface dialog) {
            if (!this.c && this.a.getReactApplicationContext().b()) {
                Object result = new WritableNativeMap();
                result.putString("action", TimePickerDialogModule.ACTION_DISMISSED);
                this.b.a(result);
                this.c = true;
            }
        }
    }

    public TimePickerDialogModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(@Nullable am options, ae promise) {
        Activity activity = getCurrentActivity();
        OnTimeSetListener listener;
        if (activity == null) {
            promise.a(ERROR_NO_ACTIVITY, "Tried to open a TimePicker dialog while not attached to an Activity");
        } else if (activity instanceof FragmentActivity) {
            i fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            DialogFragment oldFragment = (DialogFragment) fragmentManager.a(FRAGMENT_TAG);
            if (oldFragment != null) {
                oldFragment.a();
            }
            a fragment = new a();
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
            b fragment2 = new b();
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
        if (options.hasKey(ARG_HOUR) && !options.isNull(ARG_HOUR)) {
            args.putInt(ARG_HOUR, options.getInt(ARG_HOUR));
        }
        if (options.hasKey(ARG_MINUTE) && !options.isNull(ARG_MINUTE)) {
            args.putInt(ARG_MINUTE, options.getInt(ARG_MINUTE));
        }
        if (options.hasKey(ARG_IS24HOUR) && !options.isNull(ARG_IS24HOUR)) {
            args.putBoolean(ARG_IS24HOUR, options.getBoolean(ARG_IS24HOUR));
        }
        return args;
    }
}
